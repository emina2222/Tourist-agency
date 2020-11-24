package com.honeybee.app.dao;

import java.util.List;

import com.honeybee.app.entity.DostupnoVozilo;
import com.honeybee.app.entity.PonudaPrevoznika;
import com.honeybee.app.entity.Prevoznik;
import com.honeybee.app.entity.TipVozila;

public interface PrevozDAO {
	
	public List<Prevoznik> getAllTransporters();
	
	public Prevoznik getTransporter(int id);
	
	public TipVozila getType(int id);
	
	public String addNewTransporter(String naziv, String opis);
	
	public List<DostupnoVozilo> getAllVehicles(int idPrevoznika);
	
	public List<PonudaPrevoznika> getAllOffers();
	
	public String deleteTransportOffer(int id);
	
	public String addNewTransportOffer(int idAranzmana, int idPrevoznika, int idVozila, String naziv, String datumOd, String datumDo, double cena);

	public String addNewVehicle(int idPrevoznika, int idTipa, String naziv, String tablice);
	
	public List<DostupnoVozilo> getAllVehicles();
	
	public List<TipVozila> getAllTypesOfTransport();
}
