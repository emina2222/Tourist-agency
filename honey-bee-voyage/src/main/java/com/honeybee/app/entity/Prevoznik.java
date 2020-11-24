package com.honeybee.app.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="PREVOZNIK")
public class Prevoznik { 

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_PREVOZNIKA")
	private int idPrevoznika;
	
	@Column(name="NAZIV")
	private String naziv;
	
	@Column(name="OPIS_DELATNOSTI")
	private String opis;
	
	@OneToMany(mappedBy="prevoznik",cascade= {CascadeType.DETACH,CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
	private List<DostupnoVozilo> vozila;
	
	public Prevoznik() {}

	public Prevoznik(int id,String naziv, String opis) {
		this.idPrevoznika=id;
		this.naziv = naziv;
		this.opis = opis;
	}
	
	public Prevoznik(int id,String naziv) {
		this.idPrevoznika=id;
		this.naziv = naziv;
	}
	
	

	public int getIdPrevoznika() {
		return idPrevoznika;
	}

	public void setIdPrevoznika(int idPrevoznika) {
		this.idPrevoznika = idPrevoznika;
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

	public List<DostupnoVozilo> getVozila() {
		return vozila;
	}

	public void setVozila(List<DostupnoVozilo> vozila) {
		this.vozila = vozila;
	}
	
	
	
	
}
