package com.honeybee.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.honeybee.app.dao.AranzmanDAO;
import com.honeybee.app.dao.SmestajDAO;
import com.honeybee.app.entity.Aranzman;
import com.honeybee.app.entity.PonudaSmestaja;
import com.honeybee.app.entity.Smestaj;
import com.honeybee.app.entity.SmestajnaJedinica;
import com.honeybee.app.entity.StavkaSmestaja;
import com.honeybee.app.entity.TipSmestaja;

@Controller
public class AccommodationController {

	@Autowired
	private SmestajDAO smDAO;
	
	@Autowired
	private AranzmanDAO arDAO;
	
	@GetMapping("/smestaj")
	public String showAccommodation(Model model,@ModelAttribute("poruka") String poruka) {
		List<Smestaj> smestaj=smDAO.getAllHotels();
		
		model.addAttribute("poruka", poruka);
		model.addAttribute("smestaj",smestaj);
		return "smestaj";
	}
	
	@GetMapping("/smestaj-forma")
	public String showAccommodationForm(Model model) {
		List<TipSmestaja> tipovi=smDAO.getAllTypesOfAccommodation();
		
		model.addAttribute("tipovi", tipovi);
		return "smestaj-forma";
	}
	
	@PostMapping("/sacuvajFormuSmestaja")
	public String processAccommodationForm(@RequestParam("kat") String tipId, @RequestParam("naziv") String naziv,
			@RequestParam("opis") String opis, @RequestParam("telefon") String telefon, @RequestParam("adresa") String adresa,
			RedirectAttributes redirectAttributes) {
		
		int tipSmestaja=Integer.parseInt(tipId);
		
		String rezultat=smDAO.addNewAccommodation(tipSmestaja, naziv, opis, telefon, adresa);
		
		System.out.println(rezultat);
		
		if(rezultat.equals("Uspesno")) {
			rezultat="Uspešno dodavanje.";
		}
		else if(rezultat.equals("Neuspesno")){
			rezultat="Neuspešno dodavanje.";
		}
		
		redirectAttributes.addFlashAttribute("poruka",rezultat);
		
		/*
		 * if(rezultat.equals("Neuspesno")) { putanja="" }
		 */
		return "redirect:/smestaj";
	}
	
	@GetMapping("/smestajne-jedinice")
	public String showAllRoomUnits(Model model) {
		List<SmestajnaJedinica> jedinice=smDAO.getAllRoomUnits();
		
		List<SmestajnaJedinica> celaLista=new ArrayList<>();
		
		for(SmestajnaJedinica s:jedinice) {
			Smestaj smestaj=smDAO.getHotel(s.getIdSmestaja());
			String nazivSmestaja=smestaj.getNaziv();
			
			SmestajnaJedinica sm=new SmestajnaJedinica(s.getIdSmestaja(),nazivSmestaja,s.getIdSobe(),s.getBrojKreveta(),s.getOpis());
			celaLista.add(sm);
		}
		
		model.addAttribute("jedinice", celaLista);
		
		return "smestajne-jedinice";
	}
	
	@GetMapping("/stavke-ponude")
	public String showAllOfferItems(Model model, @ModelAttribute("poruka") String poruka) {
		
		List<StavkaSmestaja> stavke=smDAO.getAllOfferItems();
		List<StavkaSmestaja> celaLista=new ArrayList<>();
		
		for(StavkaSmestaja s:stavke) {
			Smestaj smestaj=smDAO.getHotel(s.getIdSmestaja());
			String nazivSmestaja=smestaj.getNaziv();
			
			StavkaSmestaja stavka=new StavkaSmestaja(s.getIdSmestaja(),s.getIdSmJce(),s.getIdPonude(),s.getPansion(),
					s.getCena(),nazivSmestaja);
			celaLista.add(stavka);
		}
		
		model.addAttribute("stavke", celaLista);
		model.addAttribute("poruka", poruka);
		return "stavke-smestaja";
	}
	
	@GetMapping("/ponudeSmestaja")
	public String showAllOffers(Model model, @ModelAttribute("poruka") String poruka) {
		
		List<PonudaSmestaja> ponude=smDAO.getAllOffers();
		
		List<PonudaSmestaja> celaLista=new ArrayList<>();
		
		for(PonudaSmestaja p:ponude) {
			Aranzman a=arDAO.getArrangement(p.getIdAr());
			String naziv=a.getNaziv();
			
			PonudaSmestaja ponuda=new PonudaSmestaja(p.getId(),p.getIdAr(),p.getNaziv(),p.getDatumPocetka(),p.getDatumZavrsetka(),
					naziv);
			
			celaLista.add(ponuda);
		}
		
		model.addAttribute("ponude", celaLista);
		model.addAttribute("poruka", poruka);
		return "ponude-smestaja";
	}
	
	@GetMapping("/brisanje-stavke-{idSmestaja}-{idSmJce}-{idPonude}")
	public String deleteOfferItem(Model model, @PathVariable("idSmestaja") int idSmestaja,@PathVariable("idSmJce") int idSmJce,
			@PathVariable("idPonude") int idPonude, RedirectAttributes redirectAttributes) {
		
		String rezultat=smDAO.deleteOfferItem(idSmestaja, idSmJce, idPonude);
		
		System.out.println(rezultat);
		if(rezultat.equals("Uspesno")) {
			rezultat="Uspešno brisanje.";
		}
		else if(rezultat.equals("Neuspesno")){
			rezultat="Neuspešno brisanje.";
		}
		
		redirectAttributes.addFlashAttribute("poruka",rezultat);
				
		return "redirect:/stavke-ponude";
	}
	
	@GetMapping("/brisanje-ponude-smestaja-{id}")
	public String deleteOfferAcc(RedirectAttributes ra,@PathVariable("id") int id) {
		
		String rezultat=smDAO.deleteOffer(id);
		
		System.out.println(rezultat);
		if(rezultat.equals("Uspesno")) {
			rezultat="Uspešno brisanje.";
		}
		else if(rezultat.equals("Neuspesno")){
			rezultat="Neuspešno brisanje.";
		}
		
		ra.addFlashAttribute("poruka",rezultat);
		
		return "redirect:/ponudeSmestaja";
	}
}
