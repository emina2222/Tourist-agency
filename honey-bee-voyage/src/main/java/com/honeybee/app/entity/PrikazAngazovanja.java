package com.honeybee.app.entity;

import java.util.Date;

public class PrikazAngazovanja {
	
	private int idAranzmana;
	private String nazivAranzmana;
	private int idVodica;
	private String imePrezime;
	private double dnevnica;
	private Date datum_pocetka;
	private Date datum_kraja;
	
	
	
	public PrikazAngazovanja(int idAranzmana, String nazivAranzmana, int idVodica, String imePrezime, double dnevnica,
			Date datum_pocetka, Date datum_kraja) {
		super();
		this.idAranzmana = idAranzmana;
		this.nazivAranzmana = nazivAranzmana;
		this.idVodica = idVodica;
		this.imePrezime = imePrezime;
		this.dnevnica = dnevnica;
		this.datum_pocetka = datum_pocetka;
		this.datum_kraja = datum_kraja;
	}
	public double getDnevnica() {
		return dnevnica;
	}
	public void setDnevnica(double dnevnica) {
		this.dnevnica = dnevnica;
	}
	public Date getDatum_pocetka() {
		return datum_pocetka;
	}
	public void setDatum_pocetka(Date datum_pocetka) {
		this.datum_pocetka = datum_pocetka;
	}
	public Date getDatum_kraja() {
		return datum_kraja;
	}
	public void setDatum_kraja(Date datum_kraja) {
		this.datum_kraja = datum_kraja;
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
	public int getIdVodica() {
		return idVodica;
	}
	public void setIdVodica(int idVodica) {
		this.idVodica = idVodica;
	}
	public String getImePrezime() {
		return imePrezime;
	}
	public void setImePrezime(String imePrezime) {
		this.imePrezime = imePrezime;
	}
	
	
}
