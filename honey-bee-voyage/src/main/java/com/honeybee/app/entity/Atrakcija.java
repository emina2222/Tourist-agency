package com.honeybee.app.entity;

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
@Table(name="ATRAKCIJA")
public class Atrakcija {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_ATRAKCIJE")
	private int idAtrakcije;
	
	@Column(name="NAZIV")
	private String naziv;
	
	@Column(name="OPIS")
	private String opis;
	
	@ManyToOne(cascade= {CascadeType.DETACH,CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
	@JoinColumn(name="ID_TIPA_ATRAKCIJE")
	private TipAtrakcije tipAtrakcije;
	
	@ManyToOne(cascade= {CascadeType.DETACH,CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
	@JoinColumn(name="ID_DESTINACIJE")
	private Destinacija destinacija;
	
	public Atrakcija() {}

	public Atrakcija(String naziv, String opis) {
		super();
		this.naziv = naziv;
		this.opis = opis;
	}
	
	

	public Atrakcija(int idAtrakcije, String naziv, String opis) {
		super();
		this.idAtrakcije = idAtrakcije;
		this.naziv = naziv;
		this.opis = opis;
	}

	public int getIdAtrakcije() {
		return idAtrakcije;
	}

	public void setIdAtrakcije(int idAtrakcije) {
		this.idAtrakcije = idAtrakcije;
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

	public TipAtrakcije getTipAtrakcije() {
		return tipAtrakcije;
	}

	public void setTipAtrakcije(TipAtrakcije tipAtrakcije) {
		this.tipAtrakcije = tipAtrakcije;
	}

	public Destinacija getDestinacija() {
		return destinacija;
	}

	public void setDestinacija(Destinacija destinacija) {
		this.destinacija = destinacija;
	}

	
	
	
}
