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
@Table(name="TIP_ATRAKCIJE")
public class TipAtrakcije {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_TIPA_ATRAKCIJE")
	private int idTipaAtrakcije;
	
	@Column(name="NAZIV")
	private String naziv;
	
	@OneToMany(mappedBy="tipAtrakcije")
	private List<Atrakcija> atrakcije;
	
	public TipAtrakcije() {}

	public TipAtrakcije(String naziv) {
		super();
		this.naziv = naziv;
	}
	
	public TipAtrakcije(int idTipaAtrakcije,String naziv) {
		this.idTipaAtrakcije=idTipaAtrakcije;
		this.naziv=naziv;
	}

	public int getIdTipaAtrakcije() {
		return idTipaAtrakcije;
	}

	public void setIdTipaAtrakcije(int idTipaAtrakcije) {
		this.idTipaAtrakcije = idTipaAtrakcije;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public List<Atrakcija> getAtrakcije() {
		return atrakcije;
	}

	public void setAtrakcije(List<Atrakcija> atrakcije) {
		this.atrakcije = atrakcije;
	}
	
	
	

}
