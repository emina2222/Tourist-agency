package com.honeybee.app.dao;

import java.util.List;

import com.honeybee.app.entity.AngazovanjeVodica;
import com.honeybee.app.entity.PonudaSmestaja;
import com.honeybee.app.entity.TuristickiVodic;
import com.honeybee.app.entity.Zaposleni;

public interface ZaposleniDAO {
	
	public List<Zaposleni> showAllEmployees();

	public List<TuristickiVodic> showAllGuides();
	
	public TuristickiVodic getGuide(int id);
	
	public List<TuristickiVodic> showAllNonengagedGuides(int id);
	
	public List<PonudaSmestaja> showAllAccommodationOffers(int idAranzmana);
	
	public String addNewEngagement(int idAr, int idVodica, int idPonude , double dnevnica);
		
	public String addNewEngagementGroup10(int idAr, int idVodica, double dnevnica);
	
	public List<AngazovanjeVodica> getAllEngagements();
	
	public String deleteEngagement(int idAr, int idVodica);
}
