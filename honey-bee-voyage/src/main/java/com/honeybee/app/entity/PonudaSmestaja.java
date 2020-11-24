package com.honeybee.app.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="PONUDA_SMESTAJA")
public class PonudaSmestaja {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_PONUDE")
	private int id;
	
	@Column(name="NAZIV")
	private String naziv;
	
	@Column(name="DATUM_POCETKA")
	private Date datumPocetka;
	
	@Column(name="DATUM_ZAVRSETKA")
	private Date datumZavrsetka;
	
	private int idAr;
	
	private String nazivAranzmana;
	
	@ManyToOne(cascade= {CascadeType.DETACH,CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
	@JoinColumn(name="ID_ARANZMANA")
	private Aranzman aranzman;
	
	public PonudaSmestaja() {}

	public PonudaSmestaja(String naziv, Date datumPocetka, Date datumZavrsetka, Aranzman aranzman) {
		super();
		this.naziv = naziv;
		this.datumPocetka = datumPocetka;
		this.datumZavrsetka = datumZavrsetka;
		this.aranzman = aranzman;
	}
	
	

	public PonudaSmestaja(int id, int idAr, String naziv, Date datumPocetka, Date datumZavrsetka) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.datumPocetka = datumPocetka;
		this.datumZavrsetka = datumZavrsetka;
		this.idAr = idAr;
	}
	
	public PonudaSmestaja(int id, int idAr, String naziv, Date datumPocetka, Date datumZavrsetka, String nazivAranzmana) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.datumPocetka = datumPocetka;
		this.datumZavrsetka = datumZavrsetka;
		this.idAr = idAr;
		this.nazivAranzmana=nazivAranzmana;
	}

	public PonudaSmestaja(int id, String naziv, Date datumPocetka, Date datumZavrsetka) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.datumPocetka = datumPocetka;
		this.datumZavrsetka = datumZavrsetka;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public Date getDatumPocetka() {
		return datumPocetka;
	}

	public void setDatumPocetka(Date datumPocetka) {
		this.datumPocetka = datumPocetka;
	}

	public Date getDatumZavrsetka() {
		return datumZavrsetka;
	}

	public void setDatumZavrsetka(Date datumZavrsetka) {
		this.datumZavrsetka = datumZavrsetka;
	}

	public Aranzman getAranzman() {
		return aranzman;
	}

	public void setAranzman(Aranzman aranzman) {
		this.aranzman = aranzman;
	}

	public int getIdAr() {
		return idAr;
	}

	public void setIdAr(int idAr) {
		this.idAr = idAr;
	}

	public String getNazivAranzmana() {
		return nazivAranzmana;
	}

	public void setNazivAranzmana(String nazivAranzmana) {
		this.nazivAranzmana = nazivAranzmana;
	}
	
	
}
