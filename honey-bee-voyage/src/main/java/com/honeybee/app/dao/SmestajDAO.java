package com.honeybee.app.dao;

import java.util.List;

import com.honeybee.app.entity.PonudaSmestaja;
import com.honeybee.app.entity.Smestaj;
import com.honeybee.app.entity.SmestajnaJedinica;
import com.honeybee.app.entity.StavkaSmestaja;
import com.honeybee.app.entity.TipSmestaja;

public interface SmestajDAO {
	
	public List<Smestaj> getAllHotels();
	
	public Smestaj getHotel(int id);
	
	public List<TipSmestaja> getAllTypesOfAccommodation();
	
	public List<PonudaSmestaja> getAllOffers();
	
	public String addNewAccommodation(int idTipa, String naziv, String opis, String telefon, String adresa);
	
	public String addNewOffer(int idAranzmana, String naziv, String datumOd, String datumDo);
	
	public List<SmestajnaJedinica> getAllRoomUnits();
	
	public List<SmestajnaJedinica> getRoomUnitsInHotel(int id);
	
	public String addNewRoomUnit(int idSobe, int idSmestaja, int brojKreveta, String opis);
	
	public String addNewAccommodationItem(int idPonude, int idSmJce, int idSmestaja, double cena, String pansion);
	
	public List<StavkaSmestaja> getAllOfferItems();
	
	public String deleteOfferItem(int idSmestaja, int idSmJce, int idPonude);
	
	public String deleteOffer(int idPonude);
}
