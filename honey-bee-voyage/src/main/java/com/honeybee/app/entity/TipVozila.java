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
@Table(name="TIP_VOZILA")
public class TipVozila {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_TIPA_VOZILA")
	private int idTipaVozila;
	
	@Column(name="NAZIV")
	private String naziv;
	
	@Column(name="OPIS")
	private String opis;
	
	@OneToMany(mappedBy="tipVozila")
	private List<DostupnoVozilo> vozila;
	
	public TipVozila() {}

	public TipVozila(int id,String naziv, String opis) {
		super();
		this.idTipaVozila=id;
		this.naziv = naziv;
		this.opis = opis;
	}
	
	public TipVozila(int id,String naziv) {
		super();
		this.idTipaVozila=id;
		this.naziv = naziv;
	}

	public int getIdTipaVozila() {
		return idTipaVozila;
	}

	public void setIdTipaVozila(int idTipaVozila) {
		this.idTipaVozila = idTipaVozila;
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
