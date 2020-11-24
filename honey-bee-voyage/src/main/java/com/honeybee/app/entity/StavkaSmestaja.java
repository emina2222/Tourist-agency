package com.honeybee.app.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="STAVKA_PONUDE_SMESTAJA")
public class StavkaSmestaja {

	@EmbeddedId
	private StavkaSmestajaID id;
	
	private int idSmestaja;
	
	private int idSmJce;
	
	private int idPonude;
	
	private String nazivSmestaja;
	
	@Column(name="PANSION")
	private String pansion;
	
	@Column(name="CENA")
	private double cena;
		
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="ID_SMESTAJNE_JEDINICE",insertable=false, updatable=false),
		@JoinColumn(name="ID_SMESTAJA",insertable=false, updatable=false)
	})
	private SmestajnaJedinica smjca;
	
	@ManyToOne
	@JoinColumn(name="ID_PONUDE",insertable=false, updatable=false)
	private PonudaSmestaja ponuda;
	
	public StavkaSmestaja() {}
	
	

	public StavkaSmestaja(int idSmestaja, int idSmJce, int idPonude, String pansion, double cena) {
		super();
		this.idSmestaja = idSmestaja;
		this.idSmJce = idSmJce;
		this.idPonude = idPonude;
		this.pansion = pansion;
		this.cena = cena;
	}

	public StavkaSmestaja(int idSmestaja, int idSmJce, int idPonude, String pansion, double cena, String nazivSmestaja) {
		super();
		this.idSmestaja = idSmestaja;
		this.idSmJce = idSmJce;
		this.idPonude = idPonude;
		this.pansion = pansion;
		this.cena = cena;
		this.nazivSmestaja=nazivSmestaja;
	}



	public int getIdSmestaja() {
		return idSmestaja;
	}



	public void setIdSmestaja(int idSmestaja) {
		this.idSmestaja = idSmestaja;
	}



	public int getIdSmJce() {
		return idSmJce;
	}



	public void setIdSmJce(int idSmJce) {
		this.idSmJce = idSmJce;
	}



	public int getIdPonude() {
		return idPonude;
	}



	public void setIdPonude(int idPonude) {
		this.idPonude = idPonude;
	}



	public StavkaSmestaja(SmestajnaJedinica smjca,PonudaSmestaja ponuda) {
		this.smjca = smjca;
		this.ponuda = ponuda;
	}

	public StavkaSmestajaID getId() {
		return id;
	}

	public void setId(StavkaSmestajaID id) {
		this.id = id;
	}

	public SmestajnaJedinica getSmjca() {
		return smjca;
	}

	public void setSmjca(SmestajnaJedinica smjca) {
		this.smjca = smjca;
	}

	public PonudaSmestaja getPonuda() {
		return ponuda;
	}

	public void setPonuda(PonudaSmestaja ponuda) {
		this.ponuda = ponuda;
	}

	public String getPansion() {
		return pansion;
	}

	public void setPansion(String pansion) {
		this.pansion = pansion;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}



	public String getNazivSmestaja() {
		return nazivSmestaja;
	}



	public void setNazivSmestaja(String nazivSmestaja) {
		this.nazivSmestaja = nazivSmestaja;
	}
	
	
	
	
}
