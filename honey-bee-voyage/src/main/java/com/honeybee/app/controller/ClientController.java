package com.honeybee.app.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.honeybee.app.dao.AranzmanDAO;
import com.honeybee.app.dao.KategorijaDAO;
import com.honeybee.app.dao.KlijentDAO;
import com.honeybee.app.dao.PutnikDAO;
import com.honeybee.app.entity.Aranzman;
import com.honeybee.app.entity.PonudaSmestaja;
import com.honeybee.app.entity.PrikazRezervacije;
import com.honeybee.app.entity.Putnik;

@Controller
public class ClientController {
	
	
	@Autowired
	private KlijentDAO klijentDAO;
	
	@Autowired
	private PutnikDAO putnikDAO;
	
	@Autowired
	private KategorijaDAO katDAO;
	
	@Autowired
	private AranzmanDAO arDAO;
		
	
	@GetMapping("/klijenti")
	public String showAllClients(Model model) {
		List<Putnik> putnici=klijentDAO.showAllClients();
		
		HashMap<String,Object> mapa=new HashMap<>();
		mapa.put("putnici", putnici);
		
		model.addAllAttributes(mapa);
		return "klijenti";
	}
	
	@GetMapping("/pregledRezervacija")
	public String showAllReservations(Model model) {
		List<PrikazRezervacije> rezervacije=putnikDAO.getAllReservations();
		
		List<PrikazRezervacije> celaLista=new ArrayList<PrikazRezervacije>();
		
		String pattern = "dd/MM/yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		
		for(PrikazRezervacije pr:rezervacije) {
			Aranzman ar=arDAO.getArrangement(pr.getId_aranzmana());
			PonudaSmestaja ponuda=arDAO.getOffer(pr.getId_ponude());
			String nazivSmestaja=arDAO.getNameOfAccomodation(pr.getId_smestaja());
			Double ukupnaCena=pr.getCena();
			int brojPutnika=pr.getBroj_putnika();
			int grupa=katDAO.getCategoryGroupForArrangement(pr.getId_aranzmana());
			String nazivKategorije=arDAO.getNameOfTheCategory(pr.getId_aranzmana());
			Putnik putnik=putnikDAO.getPutnik(pr.getId_putnika());
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
					pr.getId_smestaja(),pr.getId_ponude(), pr.getId_putnika(),nazivKategorije, pr.getBroj_dece(),
					putnik.getIme(),putnik.getPrezime(),grupa);
			celaLista.add(novi);
		}
		
		model.addAttribute("rez", celaLista);
		
		return "rezervacije-admin";
	}

}
