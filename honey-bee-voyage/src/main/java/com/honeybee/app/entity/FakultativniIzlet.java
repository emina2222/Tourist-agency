package com.honeybee.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="FAKULTATIVNI_IZLETI")
public class FakultativniIzlet {

	@Id
	@Column(name="ID_IZLETA")
	private int idIzleta;
	
	@Column(name="NAZIV")
	private String naziv;
	
	@Column(name="CENA")
	private double cena;
	
	@Column(name="OPIS")
	private String opis;
	
	@Column(name="ID_DESTINACIJE")
	private int id_destinacije;
	
	public FakultativniIzlet() {}
	
	

	public FakultativniIzlet(int idIzleta, String naziv, double cena, String opis) {
		this.idIzleta = idIzleta;
		this.naziv = naziv;
		this.cena = cena;
		this.opis = opis;
	}



	public FakultativniIzlet(int idIzleta, String naziv, double cena, String opis, int id_destinacije) {
		this.idIzleta = idIzleta;
		this.naziv = naziv;
		this.cena = cena;
		this.opis = opis;
		this.id_destinacije = id_destinacije;
	}



	public int getIdIzleta() {
		return idIzleta;
	}



	public void setIdIzleta(int idIzleta) {
		this.idIzleta = idIzleta;
	}



	public String getNaziv() {
		return naziv;
	}



	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}



	public double getCena() {
		return cena;
	}



	public void setCena(double cena) {
		this.cena = cena;
	}



	public String getOpis() {
		return opis;
	}



	public void setOpis(String opis) {
		this.opis = opis;
	}



	public int getId_destinacije() {
		return id_destinacije;
	}



	public void setId_destinacije(int id_destinacije) {
		this.id_destinacije = id_destinacije;
	}
	
	
	
	
	
}
