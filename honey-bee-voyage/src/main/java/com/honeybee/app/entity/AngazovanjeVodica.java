package com.honeybee.app.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="ANGAZOVANJE_VODICA")
public class AngazovanjeVodica {

	@EmbeddedId
	private VodicAranzmanID id;
	
	@Column(name="DNEVNICA")
	private double dnevnica;
	
	private int idVodica;
	
	private int idAranzmana;
	
	@Column(name="DATUM_POCETKA")
	private Date datumPocetka;
	
	@Column(name="DATUM_KRAJA")
	private Date datumZavrsetka;
	
	@ManyToOne
	@JoinColumn(name="ID_ARANZMANA",insertable=false, updatable=false) //insertable-updatable su setovani, da ne bi izlazila greska
	//dupliranja
	private Aranzman aranzman;
	
	@ManyToOne
	@JoinColumn(name="ID_VODICA",insertable=false, updatable=false)
	private TuristickiVodic vodic;
	
	public AngazovanjeVodica() {}

	public AngazovanjeVodica(Aranzman aranzman, TuristickiVodic vodic) {
		super();
		this.aranzman = aranzman;
		this.vodic = vodic;
	}
	
	

	public AngazovanjeVodica(double dnevnica, int idVodica, int idAranzmana, Date datumPocetka, Date datumZavrsetka) {
		super();
		this.dnevnica = dnevnica;
		this.idVodica = idVodica;
		this.idAranzmana = idAranzmana;
		this.datumPocetka = datumPocetka;
		this.datumZavrsetka = datumZavrsetka;
	}

	public AngazovanjeVodica(double dnevnica, Date datumPocetka, Date datumZavrsetka, Aranzman aranzman,
			TuristickiVodic vodic) {
		super();
		this.dnevnica = dnevnica;
		this.datumPocetka = datumPocetka;
		this.datumZavrsetka = datumZavrsetka;
		this.aranzman = aranzman;
		this.vodic = vodic;
	}

	public VodicAranzmanID getId() {
		return id;
	}

	public void setId(VodicAranzmanID id) {
		this.id = id;
	}

	public double getDnevnica() {
		return dnevnica;
	}

	public void setDnevnica(double dnevnica) {
		this.dnevnica = dnevnica;
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

	public TuristickiVodic getVodic() {
		return vodic;
	}

	public void setVodic(TuristickiVodic vodic) {
		this.vodic = vodic;
	}

	public int getIdVodica() {
		return idVodica;
	}

	public void setIdVodica(int idVodica) {
		this.idVodica = idVodica;
	}

	public int getIdAranzmana() {
		return idAranzmana;
	}

	public void setIdAranzmana(int idAranzmana) {
		this.idAranzmana = idAranzmana;
	}
	
	
	
	
}
