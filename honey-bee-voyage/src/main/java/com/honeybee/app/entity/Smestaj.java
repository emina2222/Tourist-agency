package com.honeybee.app.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="SMESTAJ")
public class Smestaj {

	@Id
	@Column(name="ID_SMESTAJA")
	private int idSmestaja;
	
	private int idTipa;
	
	@Column(name="NAZIV")
	private String naziv;
	
	@Column(name="OPIS_DELATNOSTI")
	private String opis;
	
	@Column(name="KONTAKT_TELEFON")
	private String telefon;
	
	@Column(name="ADRESA")
	private String adresa;
	
	@ManyToOne(cascade= {CascadeType.DETACH,CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
	@JoinColumn(name="ID_TIPA_SMESTAJA")
	private TipSmestaja tipSmestaja;
	
	@OneToMany(mappedBy="smestaj",cascade= {CascadeType.DETACH,CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
	private List<SmestajnaJedinica> jedinice;
	
	public Smestaj() {}

	public Smestaj(String naziv, String opis, String telefon, String adresa) {
		super();
		this.naziv = naziv;
		this.opis = opis;
		this.telefon = telefon;
		this.adresa = adresa;
	}
	
	
	

	public Smestaj(int idSmestaja, int idTipa, String naziv, String opis, String telefon, String adresa) {
		super();
		this.idSmestaja = idSmestaja;
		this.idTipa = idTipa;
		this.naziv = naziv;
		this.opis = opis;
		this.telefon = telefon;
		this.adresa = adresa;
	}

	public Smestaj(int idSmestaja, String naziv) {
		super();
		this.idSmestaja = idSmestaja;
		this.naziv = naziv;
	}

	public int getIdSmestaja() {
		return idSmestaja;
	}

	public void setIdSmestaja(int idSmestaja) {
		this.idSmestaja = idSmestaja;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public TipSmestaja getTipSmestaja() {
		return tipSmestaja;
	}

	public void setTipSmestaja(TipSmestaja tipSmestaja) {
		this.tipSmestaja = tipSmestaja;
	}

	public List<SmestajnaJedinica> getJedinice() {
		return jedinice;
	}

	public void setJedinice(List<SmestajnaJedinica> jedinice) {
		this.jedinice = jedinice;
	}

	public int getIdTipa() {
		return idTipa;
	}

	public void setIdTipa(int idTipa) {
		this.idTipa = idTipa;
	}
	
	
	
	
}
