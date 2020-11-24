package com.honeybee.app.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.honeybee.app.dao.AranzmanDAO;
import com.honeybee.app.dao.KategorijaDAO;
import com.honeybee.app.dao.PutnikDAO;
import com.honeybee.app.entity.Aranzman;
import com.honeybee.app.entity.Destinacija;
import com.honeybee.app.entity.FullAranzman;
import com.honeybee.app.entity.Kategorija;
import com.honeybee.app.entity.PonudaSmestaja;
import com.honeybee.app.entity.PrikazRezervacije;
import com.honeybee.app.entity.Putnik;

@Controller
public class HomeController {
	
	@Autowired private KategorijaDAO katDAO;
	@Autowired
	private PutnikDAO putnikDAO;
	@Autowired
	private AranzmanDAO arDAO;
		 	
	@GetMapping("/")
	public String showHome(Model model, @ModelAttribute("poruka") String poruka) {
		List<Kategorija> kategorije = katDAO.getAllCategories();
		model.addAttribute("kategorije", kategorije);
		model.addAttribute("poruka", poruka);
		return "home";
	}
	
	@GetMapping("/profil")
	public String showProfile(Model model) {
		String username=SecurityContextHolder.getContext().getAuthentication().getName();
		Putnik p=putnikDAO.getProfile(username);
		List<PrikazRezervacije> rezervacije=putnikDAO.getReservationList(username); //prikazuje rezervacije za tog datog korisnika
		List<Kategorija> kategorije = katDAO.getAllCategories();
		
		String pattern = "dd/MM/yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

		
		List<PrikazRezervacije> celaLista=new ArrayList<PrikazRezervacije>();
		
		for(PrikazRezervacije pr:rezervacije) {
			Aranzman ar=arDAO.getArrangement(pr.getId_aranzmana());
			PonudaSmestaja ponuda=arDAO.getOffer(pr.getId_ponude());
			String nazivSmestaja=arDAO.getNameOfAccomodation(pr.getId_smestaja());
			Double ukupnaCena=pr.getCena();
			int brojPutnika=pr.getBroj_putnika();
			String nazivKategorije=arDAO.getNameOfTheCategory(pr.getId_aranzmana());
			int grupa=katDAO.getCategoryGroupForArrangement(pr.getId_aranzmana());
			
			String datumPolaska="";
			String datumOdlaska="";
			String datumOdAr="";
			String datumDoAr="";
			
			
			
			if (ponuda != null) {
				datumPolaska = simpleDateFormat.format(ponuda.getDatumPocetka());
				datumOdlaska = simpleDateFormat.format(ponuda.getDatumZavrsetka());
			}
			  
			if (ar.getDatumDolaska() != null && ar.getDatumPolaska() != null) {
				datumOdAr = simpleDateFormat.format(ar.getDatumPolaska());
				datumDoAr = simpleDateFormat.format(ar.getDatumDolaska());
			}
			 
			
			PrikazRezervacije novi=new PrikazRezervacije(brojPutnika,ukupnaCena,ar.getNaziv(),nazivSmestaja,
					datumPolaska,datumOdlaska, datumOdAr,datumDoAr,ar.getIdAranzmana(),pr.getId_rezervacije(),
					pr.getId_smestaja(),pr.getId_ponude(), pr.getId_putnika(),nazivKategorije, pr.getBroj_dece(),grupa);
			celaLista.add(novi);
		}
		
		String poruka="";
		
		if(rezervacije.isEmpty()) { //ako nema rezervacija kod ovog korisnika, pokazuje ovu poruku
			poruka="Trenutno nemate rezervacije.";
		}
		HashMap<String,Object> mapa=new HashMap<>();
		
		mapa.put("putnik", p);
		mapa.put("korIme",username);
		mapa.put("kategorije", kategorije);
		mapa.put("rez", celaLista);
		mapa.put("poruka", poruka);
		
		model.addAllAttributes(mapa);
		return "profil";
	}
	
	@PostMapping("/otkaziRezervaciju")
	public String otkaziRezervaciju(@RequestParam("idAranzmana") String idAranzmana, @RequestParam("idPutnika") String idPutnika,
			@RequestParam("idSmestaja") String idSmestaja, @RequestParam("idPonude") String idPonude, 
			@RequestParam("idRezervacije") String idRezervacije, @RequestParam("grupa") String gr, 
			RedirectAttributes redirectAttributes) {
		
		int idAr=Integer.parseInt(idAranzmana);
		int idPu=Integer.parseInt(idPutnika);
		int idRez=Integer.parseInt(idRezervacije);
		String rezultat="";
		int grupa=Integer.parseInt(gr);
		
		if(grupa==1 || grupa==0) { //provera da li pripada grupi jednodnevnih izleta i tura
			rezultat=arDAO.cancelTourReservation(idAr, idRez, idPu);
		}
		else {
			rezultat=arDAO.cancelFullReservation(idAr, idRez, idPu);
		}

		if(rezultat.equals("Uspesno")) { //uzima output element iz transakcije i salje flash attribut drugom kontroleru da ga obradi
			redirectAttributes.addFlashAttribute("poruka1", "Uspesno otkazivanje rezervacije!");
			redirectAttributes.addFlashAttribute("poruka2", "Ukoliko se predomislite, mozete ponovo rezervisati.");
		}
		else if(rezultat.equals("Neuspesno")) {
			redirectAttributes.addFlashAttribute("poruka1", "Neuspesno otkazivanje rezervacije.");
			redirectAttributes.addFlashAttribute("poruka2", "Doslo je do greske sa bazom podataka.");
		}
		
		return "redirect:/uspesna-rezervacija";
	}
	
	
	@GetMapping("/kategorije-{id}")
	public String showCategories(@PathVariable("id") int id,Model model) {
		List<Aranzman> aranzmani=arDAO.getArrangementsInCategory(id);
		List<Destinacija> dest=null;
		ArrayList<FullAranzman> full=new ArrayList<FullAranzman>();
		double cena=0;
		String tipPrevoza="";
		List<Kategorija> kategorije = katDAO.getAllCategories();

		HashMap<String,Object> mapa=new HashMap<>();
		//String nazivKategorije=katDAO.getCategoryName(id);
		int grupa=katDAO.getCategoryGroup(id);
		
		for(Aranzman a:aranzmani) {
			dest=arDAO.getDestinationInArrangement(a.getIdAranzmana());
			if(grupa==1) {
				cena=arDAO.getPriceForTour(a.getIdAranzmana());
			}
			else if(grupa==0) {
				cena=arDAO.getPriceForOneDayTrip(a.getIdAranzmana());
			}
			else {
				cena=arDAO.getLowestPriceForArrangement(a.getIdAranzmana());
			}
			tipPrevoza=arDAO.getTypeOfTransport(a.getIdAranzmana());
			FullAranzman fullAr=new FullAranzman(a,dest,tipPrevoza,cena);
			full.add(fullAr);
		}
		
		String poruka="";
		
		if(aranzmani.isEmpty()) {
			poruka="U ovoj kategoriji trenutno nema aranžmana.";
		}
		
		mapa.put("aranzmani", full);
		mapa.put("kategorije",kategorije);
		mapa.put("poruka", poruka);
		model.addAllAttributes(mapa);
		return "kategorije";
	}

	@GetMapping("/admin")
	public String showAdmin() {
		return "admin-home";
	}
	
	@GetMapping("/usluge")
	public String showServices() {
		return "client-home";
	}
	
	
}