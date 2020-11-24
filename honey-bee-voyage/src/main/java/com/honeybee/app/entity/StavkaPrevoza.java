package com.honeybee.app.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="STAVKA_PONUDE_PREVOZNIKA")
public class StavkaPrevoza {

	@EmbeddedId
	private StavkaPrevoznikaID id;
	
	@Column(name="CENA_IZNAJMLJIVANJA")
	private double cena;
	
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="ID_VOZILA",insertable=false, updatable=false),
		@JoinColumn(name="ID_PREVOZNIKA",insertable=false, updatable=false)
	})
	private DostupnoVozilo vozilo;
	
	
	@ManyToOne
	@JoinColumn(name="ID_PONUDE_PREVOZNIKA",insertable=false, updatable=false)
	private PonudaPrevoznika ponuda;
	
	public StavkaPrevoza() {}

	public StavkaPrevoza(DostupnoVozilo vozilo, PonudaPrevoznika ponuda) {
		super();
		this.vozilo = vozilo;
		this.ponuda = ponuda;
	}

	public StavkaPrevoznikaID getId() {
		return id;
	}

	public void setId(StavkaPrevoznikaID id) {
		this.id = id;
	}

	public DostupnoVozilo getVozilo() {
		return vozilo;
	}

	public void setVozilo(DostupnoVozilo vozilo) {
		this.vozilo = vozilo;
	}

	public PonudaPrevoznika getPonuda() {
		return ponuda;
	}

	public void setPonuda(PonudaPrevoznika ponuda) {
		this.ponuda = ponuda;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}
	
	
	
}
