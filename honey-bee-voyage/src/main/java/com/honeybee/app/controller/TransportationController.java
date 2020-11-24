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
import com.honeybee.app.dao.PrevozDAO;
import com.honeybee.app.entity.Aranzman;
import com.honeybee.app.entity.DostupnoVozilo;
import com.honeybee.app.entity.PonudaPrevoznika;
import com.honeybee.app.entity.Prevoznik;
import com.honeybee.app.entity.TipVozila;

@Controller
public class TransportationController {

	@Autowired
	private PrevozDAO pDAO;
	
	@Autowired
	private AranzmanDAO arDAO;
	
	@GetMapping("/prevoznici")
	public String getTransporters(Model model,@ModelAttribute("poruka") String poruka) {
		List<Prevoznik> prevoznici=pDAO.getAllTransporters();
		
		model.addAttribute("prevoz", prevoznici);
		model.addAttribute("poruka", poruka);
		return "prevoz";
	}
	
	@GetMapping("/prevoz-forma")
	public String showTransportForm() {
		return "prevoz-forma";
	}
	
	@PostMapping("/sacuvajFormuPrevoza")
	public String saveTransportForm(@RequestParam("naziv") String naziv, @RequestParam("opis") String opis,RedirectAttributes ra) {
		String rezultat=pDAO.addNewTransporter(naziv, opis);
		System.out.println(rezultat);
		
		if(rezultat.equals("Uspesno")) {
			rezultat="Uspešno dodavanje.";
		}
		else if(rezultat.equals("Neuspesno")){
			rezultat="Neuspešno dodavanje.";
		}
		
		ra.addFlashAttribute("poruka",rezultat);
		
		return "redirect:/prevoznici";
	}
	
	@GetMapping("/ponudePrevoznika")
	public String showTransportOffers(Model model,@ModelAttribute("poruka") String poruka) {
		List<PonudaPrevoznika> ponude=pDAO.getAllOffers();
		
		List<PonudaPrevoznika> celaLista=new ArrayList<>();
		
		for(PonudaPrevoznika p:ponude) {
			Aranzman a=arDAO.getArrangement(p.getIdAr());
			String nazivAr=a.getNaziv();
			
			PonudaPrevoznika ponuda=new PonudaPrevoznika(p.getId(),p.getIdAr(),p.getNaziv(),p.getDatumPocetka(),p.getDatumZavrsetka(),
					nazivAr);
			celaLista.add(ponuda);
		}
		model.addAttribute("ponude", celaLista);
		model.addAttribute("poruka", poruka);
		return "ponude-prevoznika";
	}
	
	@GetMapping("/brisanje-ponude-prevoznika-{id}")
	public String deleteTransportOffer(@PathVariable("id") int id,RedirectAttributes ra) {
		
		String rezultat=pDAO.deleteTransportOffer(id);
		
		if(rezultat.equals("Uspesno")) {
			rezultat="Uspešno brisanje.";
		}
		else if(rezultat.equals("Neuspesno")){
			rezultat="Neuspešno brisanje.";
		}
		
		ra.addFlashAttribute("poruka",rezultat);
		
		System.out.println(rezultat);
		
		return "redirect:/ponudePrevoznika";
	}
	
	@GetMapping("/vozila")
	public String showAllVehicles(Model model) {
		List<DostupnoVozilo> vozila=pDAO.getAllVehicles();
		List<DostupnoVozilo> celaLista=new ArrayList<>();
		
		for(DostupnoVozilo d:vozila) {
			Prevoznik p=pDAO.getTransporter(d.getIdPrevoznika());
			TipVozila tip=pDAO.getType(d.getIdTipaVozila());
			
			DostupnoVozilo dostupno=new DostupnoVozilo(d.getIdVozila(),d.getNaziv(),d.getRegTable(),p.getNaziv(),tip.getNaziv(),
					d.getIdPrevoznika());
			celaLista.add(dostupno);
		}
		model.addAttribute("vozila", celaLista);
		return "vozila";
	}
	
	@GetMapping("/novo-vozilo-{id}")
	public String addNewVehicle(@PathVariable("id") int id,Model model) {
		List<TipVozila> tipovi=pDAO.getAllTypesOfTransport();
		model.addAttribute("tipovi", tipovi);
		model.addAttribute("idPrevoznika", id);

		return "vozila-forma";
	}
	
	@PostMapping("/sacuvajFormuVozila")
	public String saveVehicleForm(Model model, @RequestParam("idPrevoznika") String idPrevoznika, @RequestParam("tipId") String idTip,
			@RequestParam("naziv") String naziv, @RequestParam("tablice") String tablice,RedirectAttributes ra) {
		
		int idPrev=Integer.parseInt(idPrevoznika);
		int idTipa=Integer.parseInt(idTip);
		
		if(tablice.equals("") || tablice.equals(" ")) {
			tablice=null;
		}
		
		String rezultat=pDAO.addNewVehicle(idPrev, idTipa, naziv, tablice);
		
		if(rezultat.equals("Uspesno")) {
			rezultat="Uspešno dodavanje.";
		}
		else if(rezultat.equals("Neuspesno")){
			rezultat="Neuspešno dodavanje.";
		}
		
		ra.addFlashAttribute("poruka",rezultat);
		
		
		return "redirect:/prevoznici";
	}
}
