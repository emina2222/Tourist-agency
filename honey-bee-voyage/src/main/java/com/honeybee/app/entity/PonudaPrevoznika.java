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
@Table(name="PONUDA_PREVOZNIKA")
public class PonudaPrevoznika {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_PONUDE")
	private int id;
	
	private int idAr;
	
	@Column(name="NAZIV")
	private String naziv;
	
	private String nazivAranzmana;
	
	@Column(name="DATUM_POCETKA")
	private Date datumPocetka;
	
	@Column(name="DATUM_ZAVRSETKA")
	private Date datumZavrsetka;
	
	@ManyToOne(cascade= {CascadeType.DETACH,CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
	@JoinColumn(name="ID_ARANZMANA")
	private Aranzman aranzman;
	
	public PonudaPrevoznika() {}

	public PonudaPrevoznika(String naziv, Date datumPocetka, Date datumZavrsetka, Aranzman aranzman) {
		this.naziv = naziv;
		this.datumPocetka = datumPocetka;
		this.datumZavrsetka = datumZavrsetka;
		this.aranzman = aranzman;
	}
	
	

	public PonudaPrevoznika(int id, int idAr, String naziv, Date datumPocetka, Date datumZavrsetka) {
		super();
		this.id = id;
		this.idAr = idAr;
		this.naziv = naziv;
		this.datumPocetka = datumPocetka;
		this.datumZavrsetka = datumZavrsetka;
	}
	
	public PonudaPrevoznika(int id, int idAr, String naziv, Date datumPocetka, Date datumZavrsetka,String nazivAranzmana) {
		super();
		this.id = id;
		this.idAr = idAr;
		this.naziv = naziv;
		this.datumPocetka = datumPocetka;
		this.datumZavrsetka = datumZavrsetka;
		this.nazivAranzmana=nazivAranzmana;
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
