package com.honeybee.app.entity;

public class GlobalRezervacija {
	
	private int idAranzmana;
	private String nazivAranzmana;
	private int idSmestaja;
	private String nazivSmestaja;
	private int idPonude;
	private String datumi;
	private int brojKreveta;
	private Putnik putnik;
	public double cena;
	
	
	public GlobalRezervacija() {}
	
	
	
	public GlobalRezervacija(int idAranzmana, String nazivAranzmana, int idSmestaja, String nazivSmestaja, int idPonude,
			String datumi, int brojKreveta, Putnik putnik, double cena) {
		super();
		this.idAranzmana = idAranzmana;
		this.nazivAranzmana = nazivAranzmana;
		this.idSmestaja = idSmestaja;
		this.nazivSmestaja = nazivSmestaja;
		this.idPonude = idPonude;
		this.datumi = datumi;
		this.brojKreveta = brojKreveta;
		this.putnik = putnik;
		this.cena = cena;
	}



	public int getIdAranzmana() {
		return idAranzmana;
	}
	public void setIdAranzmana(int idAranzmana) {
		this.idAranzmana = idAranzmana;
	}
	public String getNazivAranzmana() {
		return nazivAranzmana;
	}
	public void setNazivAranzmana(String nazivAranzmana) {
		this.nazivAranzmana = nazivAranzmana;
	}
	public int getIdSmestaja() {
		return idSmestaja;
	}
	public void setIdSmestaja(int idSmestaja) {
		this.idSmestaja = idSmestaja;
	}
	public String getNazivSmestaja() {
		return nazivSmestaja;
	}
	public void setNazivSmestaja(String nazivSmestaja) {
		this.nazivSmestaja = nazivSmestaja;
	}
	public int getIdPonude() {
		return idPonude;
	}
	public void setIdPonude(int idPonude) {
		this.idPonude = idPonude;
	}
	public String getDatumi() {
		return datumi;
	}
	public void setDatumi(String datumi) {
		this.datumi = datumi;
	}
	public int getBrojKreveta() {
		return brojKreveta;
	}
	public void setBrojKreveta(int brojKreveta) {
		this.brojKreveta = brojKreveta;
	}
	public Putnik getPutnik() {
		return putnik;
	}
	public void setPutnik(Putnik putnik) {
		this.putnik = putnik;
	}
	public double getCena() {
		return cena;
	}
	public void setCena(double cena) {
		this.cena = cena;
	}
	
	
	
}
