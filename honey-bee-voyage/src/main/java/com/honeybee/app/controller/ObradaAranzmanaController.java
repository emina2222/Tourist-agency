package com.honeybee.app.controller;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.honeybee.app.dao.PrevozDAO;
import com.honeybee.app.dao.SmestajDAO;
import com.honeybee.app.dao.ZaposleniDAO;
import com.honeybee.app.entity.Aranzman;
import com.honeybee.app.entity.Destinacija;
import com.honeybee.app.entity.DostupnoVozilo;
import com.honeybee.app.entity.PonudaSmestaja;
import com.honeybee.app.entity.Prevoznik;
import com.honeybee.app.entity.Smestaj;
import com.honeybee.app.entity.SmestajnaJedinica;
import com.honeybee.app.entity.TipAtrakcije;
import com.honeybee.app.entity.TuristickiVodic;

@Controller
public class ObradaAranzmanaController {

	@Autowired
	private AranzmanDAO arDAO;
	
	@Autowired
	private KategorijaDAO katDAO;
	
	@Autowired
	private PrevozDAO prevozDAO;
	
	@Autowired
	private SmestajDAO smDAO;
	
	@Autowired
	private ZaposleniDAO zapDAO;
	
	@GetMapping("/opcije-{id}")
	public String showOptions(Model model,@PathVariable("id") int id,@ModelAttribute("poruka") String poruka) {
		Aranzman a=arDAO.getArrangement(id);
		Integer grupa=katDAO.getCategoryGroupForArrangement(a.getIdAranzmana());
				
		HashMap<String,Object> mapa=new HashMap<>();
		mapa.put("aranzman", a);
		mapa.put("grupa", grupa);
		mapa.put("poruka", poruka);
		
		
		model.addAllAttributes(mapa);
		return "opcije";
	}
	
	@GetMapping("/unos-ponude-prevoznika-{id}")
	public String showTransportOffer(Model model, @PathVariable("id") int id) {
		
		List<Prevoznik> prevoznici=prevozDAO.getAllTransporters();
		
		model.addAttribute("tipovi", prevoznici);
		model.addAttribute("idAranzmana", id);
		return "ponuda-prevoznika-forma";
	}
	
	@PostMapping("sacuvajFormuPonudePrevoznika")
	public String saveTransportOfferForm(Model model, @RequestParam("idAranzmana") String idAr, @RequestParam("tipId") String idPrevoznika,
			@RequestParam("voziloId") String idVozila, @RequestParam("naziv") String naziv, @RequestParam("cena") String cena,
			@RequestParam("datumOd") String datumOd, @RequestParam("datumDo") String datumDo, RedirectAttributes ra)
	{
		int idAranzmana=Integer.parseInt(idAr);
		int idPrev=Integer.parseInt(idPrevoznika);
		int idVozilo=Integer.parseInt(idVozila);
		double cenaPrevoza=Double.parseDouble(cena);
		
		if(datumOd.equals("") && datumDo.equals("") || datumOd.equals(" ") && datumDo.equals(" ")) {
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
		try {
			d1 = sdf.parse(datum1);
			d2=sdf.parse(datum2);
		} catch (ParseException e) {
			greskaDatumi="Datumi nisu uneseni u ispravnom obliku.";
		}
		
		sdf.applyPattern(NEW_FORMAT);
		try {
			novi1 = sdf.format(d1); //prebacuje datume u format prilagodjen SQL Serveru
			novi2=sdf.format(d2);
		}
		catch(NullPointerException ex) {
			greskaDatumi="Datumi nisu uneseni u ispravnom obliku. Koristite predložene formate.";
			novi1=null;
			novi2=null;
		}
		
		datumOd=novi1; //prebacuje stringove u promenljive koje se koriste u proceduri
		datumDo=novi2;
		
		String rezultat="";
		
		if(datumOd!=null && datumDo!=null) {
			rezultat=prevozDAO.addNewTransportOffer(idAranzmana, idPrev, idVozilo, naziv, datumOd, datumDo, cenaPrevoza);
		}
		else {
			rezultat="Datumi nisu uneseni u ispravnom obliku. Koristite predložene formate.";
		}
		
		System.out.println(rezultat);
		
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
		
		ra.addFlashAttribute("poruka",rezultat);
		
		String putanja="redirect:/opcije-"+idAranzmana;
		return putanja;
	}
	
	@GetMapping("/unos-ponude-smestaja-{id}")
	public String showAccommodationOffer(Model model, @PathVariable("id") int id) {
		
		model.addAttribute("idAranzmana", id);
		return "ponuda-smestaja-forma";
	}
	
	@PostMapping("/sacuvajFormuPonudeSmestaja")
	public String saveAccommodationFormOffer(Model model,@RequestParam("idAranzmana") String idAr,
			 @RequestParam("naziv") String naziv,@RequestParam("datumOd") String datumOd, @RequestParam("datumDo") String datumDo,
			 RedirectAttributes ra) {
		
		int idAranzmana=Integer.parseInt(idAr);
		
		if(datumOd.equals("") && datumDo.equals("") || datumOd.equals(" ") && datumDo.equals(" ")) {
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
		try {
			d1 = sdf.parse(datum1);
			d2=sdf.parse(datum2);
		} catch (ParseException e) {
			greskaDatumi="Datumi nisu uneseni u ispravnom obliku.";
		}
		
		sdf.applyPattern(NEW_FORMAT);
		try {
			novi1 = sdf.format(d1); //prebacuje datume u format prilagodjen SQL Serveru
			novi2=sdf.format(d2);
		}
		catch(NullPointerException ex) {
			greskaDatumi="Datumi nisu uneseni u ispravnom obliku. Koristite predložene formate.";
			novi1=null;
			novi2=null;
		}
		
		datumOd=novi1; //prebacuje stringove u promenljive koje se koriste u proceduri
		datumDo=novi2;
		
		String rezultat="";
		
		if(datumOd!=null && datumDo!=null) {
			rezultat=smDAO.addNewOffer(idAranzmana, naziv, datumOd, datumDo);
		}
		else {
			rezultat="Datumi nisu uneseni u ispravnom obliku. Koristite predložene formate.";
		}
		System.out.println(rezultat);
		
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
		
		ra.addFlashAttribute("poruka",rezultat);
		
		return "redirect:/opcije-"+idAranzmana;
	}
	

	@GetMapping("/unos-smestajnih-jca-{id}")
	public String showUnitOffer(Model model, @PathVariable("id") int id) {
		List<Smestaj> smestaj=smDAO.getAllHotels();
		
		model.addAttribute("smestaj", smestaj);
		model.addAttribute("idAranzmana", id);
		return "smestajne-jce-forma";
	}
	
	@GetMapping("/unos-stavki-smestaja-{id}")
	public String show(Model model, @PathVariable("id") int id) {
		List<Smestaj> smestaj=smDAO.getAllHotels();
		List<PonudaSmestaja> ponude=zapDAO.showAllAccommodationOffers(id);
		
		model.addAttribute("smestaj", smestaj);
		model.addAttribute("idSmestaja", id);
		model.addAttribute("ponude", ponude);
		model.addAttribute("idAranzmana", id);
		return "stavka-smestaja-forma";
	}
	
	@PostMapping("/sacuvajFormuJedinice")
	public String saveUnitForm(Model model,@RequestParam("idAranzmana") String idAr,@RequestParam("smestaj") String smestaj,
			 @RequestParam("brojSobe") String id,@RequestParam("kreveti") String kreveti, @RequestParam("opis") String opis,
			 RedirectAttributes ra) {
		
		int idAranzmana=Integer.parseInt(idAr);
		int idSobe=Integer.parseInt(id);
		int brojKreveta=Integer.parseInt(kreveti);
		int idSmestaja=Integer.parseInt(smestaj);
		
		String rezultat=smDAO.addNewRoomUnit(idSobe, idSmestaja, brojKreveta, opis);
		System.out.println(rezultat);
		
		if(rezultat.equals("Uspesno")) {
			rezultat="Uspešno dodavanje.";
		}
		else if(rezultat.equals("Neuspesno")){
			rezultat="Neuspešno dodavanje.";
		}
		
		ra.addFlashAttribute("poruka",rezultat);
		
		return "redirect:/opcije-"+idAranzmana;
	}
	
	@PostMapping("/sacuvajFormuStavke")
	public String saveItemForm(Model model,@RequestParam("idAranzmana") String idAr,@RequestParam("smestaj") String smestaj,
			 @RequestParam("ponuda") String idPonude,@RequestParam("soba") String soba, @RequestParam("cena") String cena,
			 @RequestParam("pansion") String pansion,RedirectAttributes ra) {
		
		int idAranzmana=Integer.parseInt(idAr);
		int idSobe=Integer.parseInt(soba);
		int idPon=Integer.parseInt(idPonude);
		int idSmestaja=Integer.parseInt(smestaj);
		double cenaS=Double.parseDouble(cena);
		
		String rezultat=smDAO.addNewAccommodationItem(idPon, idSobe, idSmestaja, cenaS, pansion);
		System.out.println(rezultat);
		
		if(rezultat.equals("Uspesno")) {
			rezultat="Uspešno dodavanje.";
		}
		else if(rezultat.equals("Neuspesno")){
			rezultat="Neuspešno dodavanje.";
		}
		
		ra.addFlashAttribute("poruka",rezultat);
		
		return "redirect:/opcije-"+idAranzmana;
	}
	
	@GetMapping("/unos-angazovanja-{id}")
	public String showEngagement(Model model, @PathVariable("id") int id) {
		
		List<TuristickiVodic> vodici=zapDAO.showAllNonengagedGuides(id);
		List<PonudaSmestaja> ponude=zapDAO.showAllAccommodationOffers(id);
		Integer grupa=katDAO.getCategoryGroupForArrangement(id);
		
		Aranzman ar=arDAO.getArrangement(id);
		

		HashMap<String,Object> mapa=new HashMap<>();

		mapa.put("idAranzmana", id);
		mapa.put("vodici", vodici);
		mapa.put("datumi", ar);
		mapa.put("ponude", ponude);
		mapa.put("grupa", grupa);


		model.addAllAttributes(mapa);
		return "angazovanje-forma";
	}
	
	@PostMapping("/sacuvajFormuAngazovanja")
	public String showEngagementForm(Model model, @RequestParam("idAranzmana") String idAr,@RequestParam("vodicId") String idVodica,
			@RequestParam("datumId") String idPonude, @RequestParam("dnevnica") String dnev,@RequestParam("grupa") String grupa,
			RedirectAttributes ra) {
		
		int idAranzmana=Integer.parseInt(idAr);
		int idVodic=Integer.parseInt(idVodica);
		int idPonuda=Integer.parseInt(idPonude);
		double dnevnica=Double.parseDouble(dnev);
		int grupaKat=Integer.parseInt(grupa);
		
		String rezultat="";
		if(grupaKat==2) {
			rezultat=zapDAO.addNewEngagement(idAranzmana, idVodic, idPonuda, dnevnica);
		}
		else {
			rezultat=zapDAO.addNewEngagementGroup10(idAranzmana, idVodic, dnevnica);
		}
		
		if(rezultat.equals("Uspesno")) {
			rezultat="Uspešno dodavanje.";
		}
		else if(rezultat.equals("Neuspesno")){
			rezultat="Neuspešno dodavanje.";
		}
		
		ra.addFlashAttribute("poruka",rezultat);
		
		System.out.println(rezultat);
		
		return "redirect:/opcije-"+idAranzmana;
	}
	
	
	@GetMapping("/nadji-vozila-prevoznika")
	public @ResponseBody String getVehicles(@RequestParam("prevoznik") String prevoznik) {
		Integer idPrevoznika=Integer.parseInt(prevoznik);
		List<DostupnoVozilo> vozila=prevozDAO.getAllVehicles(idPrevoznika);

		for(DostupnoVozilo d:vozila) {
			System.out.println(d.getNaziv());
		}
		
		Gson gson=new Gson();
		return gson.toJson(vozila);
	}
	
	@GetMapping("/nadji-sobe-smestaja")
	public @ResponseBody String getRooms(@RequestParam("smestaj") String smestaj) {
		
		Integer idSmestaja=Integer.parseInt(smestaj);
		List<SmestajnaJedinica> sobe=smDAO.getRoomUnitsInHotel(idSmestaja);
		
		for(SmestajnaJedinica s:sobe) {
			System.out.println(s.getOpis());
		}
		
		Gson gson=new Gson();
		return gson.toJson(sobe);
	}
	
	@GetMapping("/unos-destinacije-{id}")
	public String showDestForm(Model model, @PathVariable("id") int id) {
		Aranzman a=arDAO.getArrangement(id);
		List<Destinacija> destinacije=arDAO.getAllDestinations();
		
		model.addAttribute("nazivAranzmana", a.getNaziv());
		model.addAttribute("destinacije", destinacije);
		model.addAttribute("idAranzmana", id);
		return "spisak-destinacija-forma";
	}
	
	@PostMapping("/sacuvajFormuDest")
	public String saveDestForm(@RequestParam("idAranzmana") String id, @RequestParam("destinacija") String idDestinacije,
			RedirectAttributes ra) {
		
		int idAranzmana=Integer.parseInt(id);
		int idDest=Integer.parseInt(idDestinacije);
		
		String rezultat=arDAO.addDestinationToArrangement(idAranzmana, idDest);
		
		System.out.println(rezultat);
		
		if(rezultat.equals("Uspesno")) {
			rezultat="Uspešno dodavanje.";
		}
		else if(rezultat.equals("Neuspesno")){
			rezultat="Neuspešno dodavanje.";
		}
		
		ra.addFlashAttribute("poruka",rezultat);
		
		return "redirect:/opcije-"+id;
	}
	
	@GetMapping("/atrakcije-{id}")
	public String showAttractionForm(@PathVariable("id") int id, Model model) {
		List<TipAtrakcije> tipovi=arDAO.getAllTypesOfAttractions();
		
		model.addAttribute("tipovi", tipovi);
		model.addAttribute("idDestinacije", id);
		return "atrakcije-forma";
	}
	
	@PostMapping("/sacuvajFormuAtrakcije")
	public String saveAttractionForm(@RequestParam("idDestinacije") String id, @RequestParam("tipId") String idTipa,
			@RequestParam("naziv") String naziv, @RequestParam("opis") String opis,RedirectAttributes ra) {
		
		int idDestinacije=Integer.parseInt(id);
		int idTipaAtr=Integer.parseInt(idTipa);
		
		String rezultat=arDAO.addNewAttraction(idDestinacije, idTipaAtr, naziv, opis);
		
		System.out.println(rezultat);
		
		if(rezultat.equals("Uspesno")) {
			rezultat="Uspešno dodavanje.";
		}
		else if(rezultat.equals("Neuspesno")){
			rezultat="Neuspešno dodavanje.";
		}
		
		ra.addFlashAttribute("poruka",rezultat);
		
		return "redirect:/destinacije";
	}
	
	@GetMapping("/izleti-{id}")
	public String showTripForm(@PathVariable("id") int id, Model model) {
		List<TipAtrakcije> tipovi=arDAO.getAllTypesOfAttractions();
		
		model.addAttribute("tipovi", tipovi);
		model.addAttribute("idDestinacije", id);
		return "fakultativni-izleti-forma";
	}
	
	@PostMapping("/sacuvajFormuIzleta")
	public String saveTripForm(@RequestParam("idDestinacije") String id,
			@RequestParam("naziv") String naziv, @RequestParam("opis") String opis,@RequestParam("cena") String cena,
			RedirectAttributes ra) {
		
		int idDestinacije=Integer.parseInt(id);
		double cenaIzleta=Double.parseDouble(cena);
		
		String rezultat=arDAO.addPaidTrip(idDestinacije, naziv, cenaIzleta, opis);
		
		System.out.println(rezultat);
		
		if(rezultat.equals("Uspesno")) {
			rezultat="Uspešno dodavanje.";
		}
		else if(rezultat.equals("Neuspesno")){
			rezultat="Neuspešno dodavanje.";
		}
		
		ra.addFlashAttribute("poruka",rezultat);
		
		return "redirect:/destinacije";
	}
	
	@GetMapping("/brisanje-dest-{id}")
	public String deleteDestination(@PathVariable("id") int idDest, Model model) {
		List<Aranzman> aranzmani=arDAO.showArrangementsInDestinationList(idDest);
		
		model.addAttribute("aranzmani", aranzmani);
		model.addAttribute("idDest", idDest);
		return "destinacija-brisanje";
	}
	
	@PostMapping("/izbrisiDestinacijuIzSpiska")
	public String processDeleteFormDestination(@RequestParam("idDest") String idDest,@RequestParam("aranzman") String idAr) {
		
		int idAranzmana=Integer.parseInt(idAr);
		int idDestinacije=Integer.parseInt(idDest);
		
		String rezultat=arDAO.deleteDestinationInArrangement(idAranzmana, idDestinacije);
		
		System.out.println(rezultat);
				
		return "redirect:/destinacije";
	}
	
	
}
