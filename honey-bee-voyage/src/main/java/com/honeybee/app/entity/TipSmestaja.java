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
@Table(name="TIP_SMESTAJA")
public class TipSmestaja { 

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_TIPA_SMESTAJA")
	private int idTipaSmestaja;
	
	@Column(name="NAZIV")
	private String naziv;
	
	@OneToMany(mappedBy="tipSmestaja")
	private List<Smestaj> smestaji;
	
	public TipSmestaja() {}

	public TipSmestaja(int id,String naziv) {
		this.idTipaSmestaja=id;
		this.naziv = naziv;
	}

	public int getIdTipaSmestaja() {
		return idTipaSmestaja;
	}

	public void setIdTipaSmestaja(int idTipaSmestaja) {
		this.idTipaSmestaja = idTipaSmestaja;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	
	
}
