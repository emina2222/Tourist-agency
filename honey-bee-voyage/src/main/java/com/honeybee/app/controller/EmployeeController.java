package com.honeybee.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.honeybee.app.dao.AranzmanDAO;
import com.honeybee.app.dao.ZaposleniDAO;
import com.honeybee.app.entity.AngazovanjeVodica;
import com.honeybee.app.entity.Aranzman;
import com.honeybee.app.entity.PrikazAngazovanja;
import com.honeybee.app.entity.TuristickiVodic;
import com.honeybee.app.entity.Zaposleni;

@Controller
public class EmployeeController {

	@Autowired
	private ZaposleniDAO zapDAO;
		
	@Autowired
	private AranzmanDAO arDAO;
	
	@GetMapping("/zaposleni")
	public String showAllEmployees(Model model) {
		
		List<Zaposleni> zaposleni=zapDAO.showAllEmployees();
		model.addAttribute("zaposleni",zaposleni);
		
		return "zaposleni";
	}
	
	@GetMapping("/turisticki-vodici")
	public String showAllTouristGuides(Model model) {
		List<TuristickiVodic> vodici=zapDAO.showAllGuides();
		model.addAttribute("vodici", vodici);
		return "turisticki-vodic";
	}
	
	@GetMapping("/angazovanje-vodica")
	public String showAllEngagements(Model model,@ModelAttribute("poruka") String poruka) {
		
		List<AngazovanjeVodica> ang=zapDAO.getAllEngagements();
		List<PrikazAngazovanja> celaLista=new ArrayList<>();
		
		for(AngazovanjeVodica a:ang) {
			Aranzman ar=arDAO.getArrangement(a.getIdAranzmana());
			TuristickiVodic t=zapDAO.getGuide(a.getIdVodica());
			String imePrezime=t.getIme()+" "+t.getPrezime();
			
			PrikazAngazovanja prikaz=new PrikazAngazovanja(a.getIdAranzmana(),ar.getNaziv(),
					a.getIdVodica(),imePrezime,a.getDnevnica(),a.getDatumPocetka(),a.getDatumZavrsetka());
			
			celaLista.add(prikaz);
			
		}
		model.addAttribute("ang", celaLista);
		model.addAttribute("poruka", poruka);
		return "angazovanje";
	}
	
	@GetMapping("/obrisi-angazovanje-{idAr}-{idVodica}")
	public String deleteEngagement(@PathVariable("idAr") int idAr,@PathVariable("idVodica") int idVodica,RedirectAttributes redirectAttributes) {
		
		String rezultat=zapDAO.deleteEngagement(idAr, idVodica);
		
		if(rezultat.equals("Uspesno")) {
			rezultat="Uspešno brisanje.";
		}
		else if(rezultat.equals("Neuspesno")){
			rezultat="Neuspešno brisanje.";
		}
		
		redirectAttributes.addFlashAttribute("poruka",rezultat);
		System.out.println(rezultat);
		
		return "redirect:/angazovanje-vodica";
	}
	
}
