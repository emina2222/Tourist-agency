package com.honeybee.app.dao;

import java.util.List;

import com.honeybee.app.entity.Aranzman;
import com.honeybee.app.entity.Atrakcija;
import com.honeybee.app.entity.Destinacija;
import com.honeybee.app.entity.FakultativniIzlet;
import com.honeybee.app.entity.PonudaSmestaja;
import com.honeybee.app.entity.Smestaj;
import com.honeybee.app.entity.TipAtrakcije;

public interface AranzmanDAO {

	public List<Aranzman> getArrangementsInCategory(int id);
	
	public List<Aranzman> getAllArrangements();
	
	public List<Destinacija> getDestinationInArrangement(int id);
	
	public double getPriceForTour(int id);
	
	public double getPriceForOneDayTrip(int id);
	
	public double getLowestPriceForArrangement(int id);
	
	public String getTypeOfTransport(int id);
	
	public List<TipAtrakcije> getTypesOfAttractions(int id);
	
	public List<TipAtrakcije> getAllTypesOfAttractions();
	
	public List<Atrakcija> getAttractions(int idAr,int idTipa);
	
	public Aranzman getArrangement(int id);
	
	public List<Smestaj> getAccommodation(int id);
		
	public List<Integer> getNumberOfBedsUnit(int idAr,int idSmestaj);
	
	public List<PonudaSmestaja> getOffers(int id);
	
	public PonudaSmestaja getOffer(int id);
	
	public double getPriceForSpecificAccomodation(int idAr,int idSmestaja, int idPonude, int brojKreveta);
	
	public String getNameOfTheCategory(int id);
	
	public String getNameOfAccomodation(int id);
	
	public String getNameOfArrangement(int id);
	
	public String getDates(int id);
	
	public String reserve(int idSmestaja,int idPonude, int brojKreveta, int idAranzmana, int idPutnika);
	
	public String reserveWithInsurance(int idSmestaja,int idPonude, int brojKreveta, int idAranzmana, int idPutnika, String insurance);
	
	public String reserveTour(int idAranzmana, int idPutnika, int brojPutnika, String osiguranje);
	
	public String reserveTourWithKids(int idAranzmana, int idPutnika, int brojPutnika, int brojDece, String osiguranje);
	
	public String reserveOneDayTrip(int idAranzmana, int idPutnika, int brojPutnika, String osiguranje);
	
	public String reserveOneDayTripWithKids(int idAranzmana, int idPutnika, int brojPutnika, int brojDece, String osiguranje);
	
	public double getTravelInsurancePrice(int id);
	
	public List<FakultativniIzlet> prikaziFakultativneIzlete(int id);
	
	public String cancelFullReservation(int idAr,int idRezervacije, int idPutnika);
	
	public String cancelTourReservation(int idAr, int idRezervacije, int idPutnika);
	
	public String addNewArrangement(int idKat, int profit, String polazak, String dolazak, String naziv, int popust, double cenaOsiguranja, int kapacitet);
	
	public List<Destinacija> getAllDestinations();
	
	public List<Aranzman> showArrangementsInDestinationList(int idDest);
	
	public String addNewDestination(String lokacija, String drzava, String opis);
	
	public String addDestinationToArrangement(int idAr, int idDest);
	
	public String deleteDestinationInArrangement(int idAr, int idDest);
	
	public String addNewAttraction(int idDest, int idTipa, String naziv, String opis);
	
	public String addPaidTrip(int idDest, String naziv,double cena, String opis);
	
	public String publishArrangement(int id);
	
	public String unpublishArrangement(int id);
}
