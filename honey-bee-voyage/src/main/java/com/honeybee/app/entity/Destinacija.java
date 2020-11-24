package com.honeybee.app.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="DESTINACIJA")
public class Destinacija {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_DESTINACIJE")
	private int idDestinacije;
	
	@Column(name="LOKACIJA")
	private String lokacija;
	
	@Column(name="DRZAVA")
	private String drzava;
	
	@Column(name="OPIS")
	private String opis;
	
	@OneToMany(mappedBy="destinacija")
	private List<Atrakcija> atrakcije;
	
	@OneToMany(mappedBy="destinacija")
	private List<SpisakDestinacija> spisakDestinacija;
	
	public Destinacija() {}

	public Destinacija(String lokacija, String drzava, String opis) {
		super();
		this.lokacija = lokacija;
		this.drzava = drzava;
		this.opis = opis;
	}
	
	public Destinacija(int id,String lokacija, String drzava, String opis) {
		this.idDestinacije=id;
		this.lokacija = lokacija;
		this.drzava = drzava;
		this.opis = opis;
	}

	public int getIdDestinacije() {
		return idDestinacije;
	}

	public void setIdDestinacije(int idDestinacije) {
		this.idDestinacije = idDestinacije;
	}

	public String getLokacija() {
		return lokacija;
	}

	public void setLokacija(String lokacija) {
		this.lokacija = lokacija;
	}

	public String getDrzava() {
		return drzava;
	}

	public void setDrzava(String drzava) {
		this.drzava = drzava;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public List<Atrakcija> getAtrakcije() {
		return atrakcije;
	}

	public void setAtrakcije(List<Atrakcija> atrakcije) {
		this.atrakcije = atrakcije;
	}

	public List<SpisakDestinacija> getSpisakDestinacija() {
		return spisakDestinacija;
	}

	public void setSpisakDestinacija(List<SpisakDestinacija> spisakDestinacija) {
		this.spisakDestinacija = spisakDestinacija;
	}
	
	
	
}
