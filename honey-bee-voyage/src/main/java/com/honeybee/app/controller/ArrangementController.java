package com.honeybee.app.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.honeybee.app.dao.AranzmanDAO;
import com.honeybee.app.dao.KategorijaDAO;
import com.honeybee.app.dao.PutnikDAO;
import com.honeybee.app.entity.Aranzman;
import com.honeybee.app.entity.Atrakcija;
import com.honeybee.app.entity.Destinacija;
import com.honeybee.app.entity.FakultativniIzlet;
import com.honeybee.app.entity.FullSmestajOrganizacija;
import com.honeybee.app.entity.GlobalRezervacija;
import com.honeybee.app.entity.Kategorija;
import com.honeybee.app.entity.PonudaSmestaja;
import com.honeybee.app.entity.PrikazAtrakcija;
import com.honeybee.app.entity.Putnik;
import com.honeybee.app.entity.Smestaj;
import com.honeybee.app.entity.TabelaPrikaz;
import com.honeybee.app.entity.TipAtrakcije;

@Controller
public class ArrangementController {
	
	@Autowired
	private AranzmanDAO arDAO;
	
	@Autowired
	private KategorijaDAO katDAO;
	
	@Autowired
	private PutnikDAO putnikDAO;
		
	/*
	 * @Autowired private PutnikDAO putnikDAO;
	 */

	//iz view-a ce dolaziti preko url: /aranzmani/{id}
	//ubaciti da prikazuje ikonicu lokacije i naziv lokacije/lokacija pored, ukupnu cenu ili najmanju cenu za aranzmane sa izborom smestaja
	//ubaciti opis destinacije/destinacija ispod naziva aranzmana
	//prikazati bus/avion ikonicu
	//iteracija kroz listu destinacija - izvlacenje opisa i naziva destinacije odvojeno
	
	@GetMapping("/aranzmani-{id}")
	public String showArrangementDetails(@PathVariable("id") int id,Model model) {
		int grupa=katDAO.getCategoryGroupForArrangement(id);
		List<Kategorija> kategorije = katDAO.getAllCategories();
		Aranzman ar=arDAO.getArrangement(id);
		List<Smestaj> smestaji=arDAO.getAccommodation(id);
		List<PonudaSmestaja> ponude=arDAO.getOffers(id);
		List<Integer> krevetneSobe=null;
		List<Double> cene=new ArrayList<Double>();
		List<FullSmestajOrganizacija> full=new ArrayList<FullSmestajOrganizacija>();
		List<TabelaPrikaz> tabelarniPrikaz=new ArrayList<TabelaPrikaz>();
		List<Destinacija> destinacije=arDAO.getDestinationInArrangement(id);
		
		List<FakultativniIzlet> listaFakIzleta=arDAO.prikaziFakultativneIzlete(id);
		String poruka="";
		
		if(listaFakIzleta.isEmpty()) {
			poruka="Na ovom aranžmanu nema fakultativnih izleta.";
		}
		
		List<PrikazAtrakcija> listaAtrakcija=new ArrayList<PrikazAtrakcija>();
		List<TipAtrakcije> tipoviAtrakcija=arDAO.getTypesOfAttractions(id);
		List<Atrakcija> atrakcije=new ArrayList<Atrakcija>();
		
		for(TipAtrakcije tip:tipoviAtrakcija) {
			atrakcije=arDAO.getAttractions(id, tip.getIdTipaAtrakcije());
			PrikazAtrakcija prikaz=new PrikazAtrakcija(tip,atrakcije);
			listaAtrakcija.add(prikaz);
		}
		
		System.out.println("Lista: "+listaAtrakcija);
		
		double cena=0;
		int brKreveta=0;
		
		for(Smestaj s:smestaji) {
			krevetneSobe=arDAO.getNumberOfBedsUnit(id, s.getIdSmestaja());
			FullSmestajOrganizacija fso=new FullSmestajOrganizacija(s,ponude,krevetneSobe);
			full.add(fso);
		}
		
		for(FullSmestajOrganizacija f:full) {
			tabelarniPrikaz.clear();
			for(int i=0;i<f.getJedinice().size();i++) {
				cene.clear();
				for(int j=0;j<f.getPonude().size();j++) {
					cena=arDAO.getPriceForSpecificAccomodation(id, f.getSmestaj().getIdSmestaja(), f.getPonude().get(j).getId(), 
							f.getJedinice().get(i));
					
					cene.add(cena);
					brKreveta=f.getJedinice().get(i);
				}
				  List<Double> copy=new ArrayList<Double>();
				  copy.addAll(cene);
				  TabelaPrikaz tp=new TabelaPrikaz(brKreveta,copy);
				  System.out.println(tp.getBrojKreveta()); System.out.println(tp.getCene());
				  tabelarniPrikaz.add(tp);
			}
			List<TabelaPrikaz> copy=new ArrayList<TabelaPrikaz>();
			copy.addAll(tabelarniPrikaz); //mora da se kopira sadrzaj tabelarnog prikaza (lista), zato sto se u sledecoj iteraciji brise sadrzaj
			f.setTp(copy);
		}
		
		Double cenaTure=arDAO.getPriceForTour(id);
		Double cenaIzleta=arDAO.getPriceForOneDayTrip(id);
		
		Date datumPolaska=ar.getDatumPolaska();
		Date datumDolaska=ar.getDatumDolaska();
		String datumiTure="";
		String datumIzleta="";
		
		String pattern = "dd/MM/yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		
		if(datumPolaska!=null && datumDolaska!=null) {
			datumiTure=simpleDateFormat.format(datumPolaska)+" - "+simpleDateFormat.format(datumDolaska);
			datumIzleta=simpleDateFormat.format(datumPolaska);
		}
		
		//String putanja="aranzman";
		
		String programPutovanja="";
		String nazivFajla="program-"+id+".txt";
		StringBuilder sb=new StringBuilder();
		try {
			InputStream inputStream = getClass().getResourceAsStream("/"+nazivFajla);//uzimanje fajla iz resources foldera
			if (inputStream != null) {
				sb = new StringBuilder();
				try (Reader reader = new BufferedReader(
						new InputStreamReader(inputStream, Charset.forName(StandardCharsets.UTF_8.name())))) {
					int c = 0;
					while ((c = reader.read()) != -1) {
						sb.append((char) c);
					}
				}
			}
		}
		catch(NullPointerException e) {
			System.out.println("U procesu...");
			//putanja="redirect:/uspesna-rezervacija";
		}
		catch (FileNotFoundException e) {
			System.out.println("Aranzman je u procesu.");
			//e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Aranzman je u procesu.");
			//e.printStackTrace();
		}
		
		programPutovanja=sb.toString(); //dodati sliku na osnovu id, pored teksta programa
		
		String nazivSlike="slika-"+id+".jpg";
						
		HashMap<String,Object> mapa=new HashMap<>();
		mapa.put("kategorije", kategorije);
		mapa.put("aranzman", ar);
		mapa.put("full", full);
		mapa.put("kat", grupa);
		mapa.put("destinacije", destinacije);
		mapa.put("atrakcije",listaAtrakcija);
		mapa.put("fakIzleti", listaFakIzleta);
		mapa.put("cenaTure",cenaTure);
		mapa.put("cenaIzleta", cenaIzleta);
		mapa.put("datumiTure",datumiTure);
		mapa.put("datumIzleta", datumIzleta);
		mapa.put("poruka", poruka);
		mapa.put("programPutovanja", programPutovanja);
		mapa.put("nazivSlike", nazivSlike);
		
		model.addAllAttributes(mapa);
		
		return "aranzman";
	}
	
	@GetMapping("/rezervacija") //responseBody menja return type iz view-a u objekat koji saljemo success funkciji u ajaxu
	public @ResponseBody String getDataFromAjax(@RequestParam String ar,@RequestParam String smestaj, @RequestParam String ponuda,
			@RequestParam String krevet,RedirectAttributes redirectAttributes) {
		
		System.out.println("Username: "+SecurityContextHolder.getContext().getAuthentication().getName());
		Integer idAranzmana=Integer.parseInt(ar);
		Integer idSmestaja=Integer.parseInt(smestaj);
		Integer idPonude=Integer.parseInt(ponuda);
		Integer brojKreveta=Integer.parseInt(krevet);
		String result="success";
		String username=SecurityContextHolder.getContext().getAuthentication().getName();
		
		if(username.contains("anonymousUser")) {
			result="failure";
		}
		
		String nazivSmestaja=arDAO.getNameOfAccomodation(idSmestaja);
		String nazivAranzmana=arDAO.getNameOfArrangement(idAranzmana);
		Putnik p=putnikDAO.getProfile(username);
		String datumi=arDAO.getDates(idPonude);
		
		Double cena=arDAO.getPriceForSpecificAccomodation(idAranzmana, idSmestaja, idPonude, brojKreveta); //racuna ukupnu cenu na osnovu datih parametara
		
		HashMap<String,Object> mapa=new HashMap<>();
		mapa.put("rezultat", result);
		mapa.put("aranzman", nazivAranzmana);
		mapa.put("idAranzmana",idAranzmana);
		mapa.put("smestaj", nazivSmestaja);
		mapa.put("ponuda", datumi);
		mapa.put("krevet", brojKreveta);
		mapa.put("putnik",p);
		mapa.put("cena",cena);
	
				
		List<GlobalRezervacija> lista=new ArrayList<GlobalRezervacija>();
		GlobalRezervacija gr=new GlobalRezervacija(idAranzmana,nazivAranzmana,idSmestaja,nazivSmestaja,idPonude,datumi,brojKreveta,p,cena);
		lista.add(gr);
		
		Gson gson=new Gson();
		
		//mapa.put("cena", cenaAranzmana);
		//model.addAllAttributes(mapa);
		//redirectAttributes.addAllAttributes(mapa);
		//redirectAttributes.addFlashAttribute("prosledjeniPodaci", kategorije);
		return gson.toJson(lista); //salje u obliku json stringa i parsira se u ajax success metodi
		//return json;
	}
	
	
	@GetMapping("/rezervacija-forma")
	public String getDataForReservation(@RequestParam("nazivAranzmana") String nazivAranzmana, 
			@RequestParam("idAranzmana") String idAranzmana, @RequestParam("nazivSmestaja") String nazivSmestaja,
			@RequestParam("idSmestaja") String idSmestaja, @RequestParam("idPonude") String idPonude, @RequestParam("datumi") String datumi, 
			@RequestParam("rezultat") String rezultat,@RequestParam("brojKreveta") String brojKreveta,@RequestParam("cena") String cena, Model model) {
		List<Kategorija> kategorije=katDAO.getAllCategories();
		
		int idAr=Integer.parseInt(idAranzmana);
		Double cenaOsiguranja=arDAO.getTravelInsurancePrice(idAr);
		
		HashMap<String,Object> mapa=new HashMap<>();
		mapa.put("kategorije", kategorije);
		mapa.put("nazivAranzmana", nazivAranzmana);
		mapa.put("idAranzmana", idAranzmana);
		mapa.put("nazivSmestaja", nazivSmestaja);
		mapa.put("idSmestaja", idSmestaja);
		mapa.put("idPonude", idPonude);
		mapa.put("datumi", datumi);
		mapa.put("rezultat", rezultat);
		mapa.put("brojKreveta", brojKreveta);
		mapa.put("cena", cena);
		mapa.put("cenaOsiguranja", cenaOsiguranja);

		String putanja="";
		
		String username=SecurityContextHolder.getContext().getAuthentication().getName(); //uzima username od ulogovanog ili anonimnog usera
		Putnik p=new Putnik();
				
		if(username.contains("anonymousUser")) {
			putanja="redirect:/showLoginPage"; //ako korisnik nije ulogovan, vraca ga na login stranu
			System.out.println("Username: "+username);
		}
		else {
			putanja="rezervacija-forma"; //ako je ulogovan, dolazi na formu za registraciju
			p=putnikDAO.getProfile(username); //dobija sve podatke o ulogovanom korisniku
			System.out.println("Id putnika: "+p.getIdPutnika()+"Username: "+username);
		}
		
		mapa.put("putnik", p);
		model.addAllAttributes(mapa);
		return putanja;
	}
	
	@PostMapping("/processReservation")
	public String processReservation(@RequestParam("idAranzmana") String idAranzmana, @RequestParam("idSmestaja") String idSmestaja,
			@RequestParam("idPonude") String idPonude,@RequestParam("idPutnika") String idPutnika,@RequestParam("brojKreveta") String brojKreveta,
			RedirectAttributes redirectAttributes, @RequestParam(value="osiguranjeCheck",required=false) String vrednost) {
		//parsira prosledjene stringove iz hidden input elemenata
		int idAr=Integer.parseInt(idAranzmana);
		int idSm=Integer.parseInt(idSmestaja);
		int idPo=Integer.parseInt(idPonude);
		int brojK=Integer.parseInt(brojKreveta);
		int idPu=Integer.parseInt(idPutnika);
		
		String osiguranje="";
		
		if(vrednost!=null) {
			osiguranje="da";
		}
		else {
			osiguranje="ne";
		}
		
		
		String rezultat=arDAO.reserveWithInsurance(idSm, idPo, brojK, idAr, idPu,osiguranje);
		
		if(rezultat.equals("Uspesno")) { //uzima output element iz transakcije i salje flash attribut drugom kontroleru da ga obradi
			redirectAttributes.addFlashAttribute("poruka1", "Uspesna rezervacija!");
			redirectAttributes.addFlashAttribute("poruka2", "Detalje rezervacije mozete pregledati na svom profilu.");
		}
		else if(rezultat.equals("Neuspesno")) {
			redirectAttributes.addFlashAttribute("poruka1", "Neuspesna rezervacija.");
			redirectAttributes.addFlashAttribute("poruka2", "Doslo je do greske sa bazom podataka.");
		}
		else {
			redirectAttributes.addFlashAttribute("poruka1",rezultat);
			redirectAttributes.addFlashAttribute("poruka2", "Za više informacija kontaktirajte customer support.");
		}
		
		return "redirect:/uspesna-rezervacija"; //redirektuje na thankyou stranicu sa obavestenjem o (ne)uspesnoj rezervaciji
	}
	
	@GetMapping("/uspesna-rezervacija")
	public String thankyou(Model model, @ModelAttribute("poruka1") String poruka1,@ModelAttribute("poruka2") String poruka2) {
		
		List<Kategorija> kategorije=katDAO.getAllCategories();
		
		model.addAttribute("kategorije",kategorije);
		model.addAttribute("poruka1", poruka1);
		model.addAttribute("poruka2", poruka2);
		return "uspesna-rezervacija";
	}
	
	
	 
	@GetMapping("/rezervacija-ture-forma")
	public String reserveTourForm(@RequestParam("idAranzmana") String idAranzmana, @RequestParam("cenaTure") String cenaTure,
			@RequestParam("datumiTure") String datumiTure, Model model) {
		
		int idAr=Integer.parseInt(idAranzmana);
		double cena=Double.parseDouble(cenaTure);
		
		Aranzman ar=arDAO.getArrangement(idAr);
		List<Kategorija> kategorije=katDAO.getAllCategories();
		
		Double cenaOsiguranja=arDAO.getTravelInsurancePrice(idAr);
		
		String username=SecurityContextHolder.getContext().getAuthentication().getName();
		Putnik p=new Putnik();
		String putanja="";
		
		if(username.contains("anonymousUser")) {
			putanja="redirect:/showLoginPage"; //ako korisnik nije ulogovan, vraca ga na login stranu
			System.out.println("Username: "+username);
		}
		else {
			putanja="rezervacija-ture-forma"; //ako je ulogovan, dolazi na formu za registraciju
			p=putnikDAO.getProfile(username); //dobija sve podatke o ulogovanom korisniku
			System.out.println("Id putnika: "+p.getIdPutnika()+"Username: "+username);
		}
		
		HashMap<String,Object> mapa=new HashMap<>();
		
		mapa.put("aranzman", ar);
		mapa.put("cenaTure", cena);
		mapa.put("datumiTure", datumiTure);
		mapa.put("putnik", p);
		mapa.put("kategorije", kategorije);
		mapa.put("cenaOsiguranja", cenaOsiguranja);
		
		model.addAllAttributes(mapa);
		return putanja;
	}
	
	@PostMapping("/processTourReservation")
	public String processTourReservation(@RequestParam("idAranzmana") String idAranzmana,
			@RequestParam("idPutnika") String idPutnika, @RequestParam(value="osiguranjeCheck",required=false) String vrednost,
			@RequestParam("brojPutnika") String brojPutnika,@RequestParam("brojDece") String brojDece, RedirectAttributes redirectAttributes) {
		
		
		int idAr=Integer.parseInt(idAranzmana);
		int idP=Integer.parseInt(idPutnika);
		int brojP=Integer.parseInt(brojPutnika);
		int brojD=Integer.parseInt(brojDece);
		
		String osiguranje="";
		
		if(vrednost!=null) {
			osiguranje="da";
		}
		else {
			osiguranje="ne";
		}
		
		String rezultat=arDAO.reserveTourWithKids(idAr, idP, brojP, brojD, osiguranje);
		
		if(rezultat.equals("Uspesno")) { //uzima output element iz transakcije i salje flash attribut drugom kontroleru da ga obradi
			redirectAttributes.addFlashAttribute("poruka1", "Uspesna rezervacija!");
			redirectAttributes.addFlashAttribute("poruka2", "Detalje rezervacije mozete pregledati na svom profilu.");
		}
		else if(rezultat.equals("Neuspesno")) {
			redirectAttributes.addFlashAttribute("poruka1", "Neuspesna rezervacija.");
			redirectAttributes.addFlashAttribute("poruka2", "Doslo je do greske sa bazom podataka.");
		}
		else if(rezultat.equals("Manjak kapaciteta")){
			redirectAttributes.addFlashAttribute("poruka1","Broj putnika je veći od preostalog kapaciteta na aranžmanu.");
			redirectAttributes.addFlashAttribute("poruka2", "Za više informacija kontaktirajte customer support.");
		}
		else {
			redirectAttributes.addFlashAttribute("poruka1",rezultat);
			redirectAttributes.addFlashAttribute("poruka2", "Za više informacija kontaktirajte customer support.");
		}
		
		
		return "redirect:/uspesna-rezervacija";
	}
	
	@GetMapping("/rezervacija-izleta-forma")
	public String showOneDayTripReservationForm(@RequestParam("idAranzmana") String idAranzmana, @RequestParam("cenaIzleta") String cenaIzleta,
			@RequestParam("datumIzleta") String datumIzleta, Model model) {
		
		int idAr=Integer.parseInt(idAranzmana);
		double cena=Double.parseDouble(cenaIzleta);
		
		Aranzman ar=arDAO.getArrangement(idAr);
		List<Kategorija> kategorije=katDAO.getAllCategories();
		
		Double cenaOsiguranja=arDAO.getTravelInsurancePrice(idAr);
		
		String username=SecurityContextHolder.getContext().getAuthentication().getName();
		Putnik p=new Putnik();
		String putanja="";
		
		if(username.contains("anonymousUser")) {
			putanja="redirect:/showLoginPage"; //ako korisnik nije ulogovan, vraca ga na login stranu
			System.out.println("Username: "+username);
		}
		else {
			putanja="rezervacija-izleta-forma"; //ako je ulogovan, dolazi na formu za registraciju
			p=putnikDAO.getProfile(username); //dobija sve podatke o ulogovanom korisniku
			System.out.println("Id putnika: "+p.getIdPutnika()+"Username: "+username);
		}
		
		System.out.println(">> U formi: Id ar: "+idAr+" Cena:"+cena);
		
		HashMap<String,Object> mapa=new HashMap<>();
		mapa.put("aranzman", ar);
		mapa.put("kategorije", kategorije);
		mapa.put("cenaOsiguranja", cenaOsiguranja);
		mapa.put("datumIzleta", datumIzleta);
		mapa.put("putnik", p);
		mapa.put("cena", cena);
		
		model.addAllAttributes(mapa);
		
		return putanja;
	}
	
	@PostMapping("/processOneDayTripReservation")
	public String processOneDayTripReservation(@RequestParam("idAranzmana") String idAranzmana,
			@RequestParam("idPutnika") String idPutnika, @RequestParam(value="osiguranjeCheck",required=false) String vrednost,
			@RequestParam("brojPutnika") String brojPutnika,@RequestParam("brojDece") String brojDece, RedirectAttributes redirectAttributes) {
		
		
		int idAr=Integer.parseInt(idAranzmana);
		int idP=Integer.parseInt(idPutnika);
		int brojP=Integer.parseInt(brojPutnika);
		int brojD=Integer.parseInt(brojDece);
		
		
		String osiguranje="";
		
		if(vrednost!=null) {
			osiguranje="da";
		}
		else {
			osiguranje="ne";
		}
		
		String rezultat=arDAO.reserveOneDayTripWithKids(idAr, idP, brojP, brojD, osiguranje);
		
		
		if(rezultat.equals("Uspesno")) { //uzima output element iz transakcije i salje flash attribut drugom kontroleru da ga obradi
			redirectAttributes.addFlashAttribute("poruka1", "Uspesna rezervacija!");
			redirectAttributes.addFlashAttribute("poruka2", "Detalje rezervacije mozete pregledati na svom profilu.");
		}
		else if(rezultat.equals("Neuspesno")) {
			redirectAttributes.addFlashAttribute("poruka1", "Neuspesna rezervacija.");
			redirectAttributes.addFlashAttribute("poruka2", "Doslo je do greske sa bazom podataka.");
		}
		else if(rezultat.equals("Manjak kapaciteta")){
			redirectAttributes.addFlashAttribute("poruka1","Broj putnika je veći od preostalog kapaciteta na aranžmanu.");
			redirectAttributes.addFlashAttribute("poruka2", "Za više informacija kontaktirajte customer support.");
		}
		else {
			redirectAttributes.addFlashAttribute("poruka1",rezultat);
			redirectAttributes.addFlashAttribute("poruka2", "Za više informacija kontaktirajte customer support.");
		}
		
		return "redirect:/uspesna-rezervacija";
	}
	
	
	@GetMapping("/kategorije")
	public String showCategories(Model model) {
		List<Kategorija> kategorije = katDAO.sveKategorije();
		for(Kategorija k:kategorije) {
			System.out.println("Grupa: "+k.getGrupa());
		}
		model.addAttribute("kat", kategorije);
		return "kategorije-admin";
	}
	
	@GetMapping("/kategorije-forma")
	public String showCategoryForm() {
		
		return "kategorije-forma";
	}
	
	@PostMapping("/sacuvajFormuKategorije")
	public String saveCategoryForm(Model model, @RequestParam("naziv") String naziv, @RequestParam("opis") String opis
			,@RequestParam("grupa") String grupa) {
		
		int gr=Integer.parseInt(grupa);
		System.out.println("Grupa: "+grupa);
		String rezultat=katDAO.addNewCategoryWithGroup(naziv, opis,gr);
		
		System.out.println(rezultat);
		return "redirect:/kategorije";
	}
	
	@GetMapping("/aranzmani")
	public String showArrangements(Model model, @ModelAttribute("poruka") String poruka) {
		List<Aranzman> aranzmani=arDAO.getAllArrangements();

		model.addAttribute("poruka", poruka);
		model.addAttribute("aranzmani", aranzmani);
		return "aranzmani-admin";
	}
	
	@GetMapping("/aranzmani-forma")
	public String showArrForm(Model model) {
		List<Kategorija> kategorije = katDAO.sveKategorije();
		
		model.addAttribute("tipovi", kategorije);
		return "aranzmani-forma";
	}
	
	@PostMapping("/sacuvajFormuAranzmana")
	public String saveArrangementForm(Model model, @RequestParam("naziv") String naziv, @RequestParam("profit") String profit,
			@RequestParam("popust") String popust, @RequestParam("datumOd") String datumOd,@RequestParam("datumDo") String datumDo,
			@RequestParam("kapacitet") String kapacitet,
			@RequestParam("cenaOsiguranja") String cenaOsiguranja, @RequestParam("tipId") String tipId,
			RedirectAttributes redirectAttributes) {
		
		int tip=Integer.parseInt(tipId);
		int prof=Integer.parseInt(profit);
		int pop=Integer.parseInt(popust);
		int kap=Integer.parseInt(kapacitet);
		double cena=Double.parseDouble(cenaOsiguranja);
		
		if(datumOd.equals("") && datumDo.equals("") || datumOd.equals(" ") && datumDo.equals(" ") || datumOd==null && datumDo==null) {
			datumOd=null;
			datumDo=null;
		}
		
		final String OLD_FORMAT = "dd/MM/yyyy";
		final String NEW_FORMAT = "yyyy/MM/dd";
		
		String greskaDatumi="";

		String datum1 = datumOd;
		String datum2= datumDo;
		String novi1="";
		String novi2="";

		SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);
		Date d1 = null;
		Date d2 = null;
		if (datum1 != null & datum2 != null) {
			try {
				d1 = sdf.parse(datum1);
				d2 = sdf.parse(datum2);
			} catch (ParseException e) {
				greskaDatumi = "Datumi nisu uneseni u ispravnom obliku.";
			}

			sdf.applyPattern(NEW_FORMAT);
			try {
				novi1 = sdf.format(d1); // prebacuje datume u format prilagodjen SQL Serveru
				novi2 = sdf.format(d2);
			} catch (NullPointerException ex) {
				// greskaDatumi="Datumi nisu uneseni u ispravnom obliku. Koristite predložene
				// formate.";
				novi1 = null;
				novi2 = null;
			}

			datumOd = novi1; // prebacuje stringove u promenljive koje se koriste u proceduri
			datumDo = novi2;
		}
		
		String rezultat=arDAO.addNewArrangement(tip, prof, datumOd, datumDo, naziv, pop, cena, kap);
		
		if(rezultat.equals("Uspesno")) {
			rezultat="Uspešno dodavanje.";
		}
		else if(rezultat.equals("Neuspesno")){
			rezultat="Neuspešno dodavanje.";
		}
		else if(rezultat.equals("Greska sa datumima")) {
			rezultat="Datumi početka je veći od datuma kraja.";
		}
		else if(greskaDatumi!="") {
			rezultat=greskaDatumi;
		}
		
		redirectAttributes.addFlashAttribute("poruka",rezultat);
		
		System.out.println(rezultat);
		return "redirect:/aranzmani";
	}
	
	@GetMapping("/destinacije")
	public String showDestinations(Model model,@ModelAttribute("poruka") String poruka) {
		List<Destinacija> destinacije=arDAO.getAllDestinations();
		
		model.addAttribute("destinacije", destinacije);
		model.addAttribute("poruka", poruka);
		return "destinacije";
	}
	
	@GetMapping("/destinacije-forma")
	public String showDestinationForm(Model model) {
		List<Aranzman> aranzmani = arDAO.getAllArrangements();
		
		model.addAttribute("aranzmani", aranzmani);
		return "destinacije-forma";
	}
	
	@PostMapping("/sacuvajFormuDestinacije")
	public String saveDestinationForm(Model model, @RequestParam("lokacija") String lokacija, @RequestParam("drzava") String drzava,
			@RequestParam("opis") String opis,RedirectAttributes redirectAttributes) {
						
		String rezultat=arDAO.addNewDestination(lokacija, drzava, opis);
		
		if(rezultat.equals("Uspesno")) {
			rezultat="Uspešno dodavanje.";
		}
		else if(rezultat.equals("Neuspesno")){
			rezultat="Neuspešno dodavanje.";
		}
		
		redirectAttributes.addFlashAttribute("poruka",rezultat);
		System.out.println(rezultat);
		return "redirect:/destinacije";
	}
	
	//Poenta je da klijenti ne mogu da vide aranzmane koji nisu kompletirani, ili zastarele aranzmane
	@GetMapping("/objaviAranzman")
	public String publishArrangement(Model model,@RequestParam("idAr") String idA) {
		int id=Integer.parseInt(idA);
		
		String rezultat=arDAO.publishArrangement(id); //postavlja aranzman na stranicu za kategorije
		System.out.println(rezultat);
		return "redirect:/aranzmani";
	}
	
	@GetMapping("/sakrijAranzman")
	public String unpublishArrangement(Model model,@RequestParam("idAr") String idA) {
		int id=Integer.parseInt(idA);
		
		String rezultat=arDAO.unpublishArrangement(id);//uklanja aranzman iz prikaza za klijente
		System.out.println(rezultat);
		return "redirect:/aranzmani";
	}
}
