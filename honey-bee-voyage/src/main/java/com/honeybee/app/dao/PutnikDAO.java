package com.honeybee.app.dao;

import java.util.List;

import com.honeybee.app.entity.PrikazRezervacije;
import com.honeybee.app.entity.Putnik;

public interface PutnikDAO {

	public Putnik getProfile(String username);
	
	public void registracijaPutnika(String korIme,String lozinka);
	
	public String registracija(String korIme,String lozinka);
	
	public void unosPodataka(String username, String ime,String prezime, String brojLicneKarte, String telefon,String email);
	
	public String unosPodatakaZaPutnika(String username, String ime,String prezime, String brojLicneKarte, String telefon,String email);
	
	public int getAuthenticationId(String username);
	
	public List<PrikazRezervacije> getReservationList(String username);
	
	public List<PrikazRezervacije> getAllReservations();
	
	public Putnik getPutnik(int idPutnika);
}
