package com.honeybee.app.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name="DOSTUPNO_VOZILO")
public class DostupnoVozilo { 

	@EmbeddedId
	private VoziloID id;
	
	@MapsId("prevoznikID") //uzima naziv klase PrevoznikID (prvo slovo malo)
	@JoinColumn(name="ID_PREVOZNIKA",referencedColumnName="id_prevoznika")//name=>ime iz tabele u bazi; referencedColumnName=>naziv iz tabele PrevoznikID
	@ManyToOne
	private Prevoznik prevoznik;
	
	@Column(name="NAZIV")
	private String naziv;
	
	@Column(name="REGISTARSKE_TABLE")
	private String regTable;
	
	private String nazivPrevoznika;
	
	private String nazivTipa;
	
	private int idPrevoznika;
	
	private int idVozila;
	
	private int idTipaVozila;
	
	@ManyToOne(cascade= {CascadeType.DETACH,CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
	@JoinColumn(name="ID_TIPA_VOZILA")
	private TipVozila tipVozila;
	
	public DostupnoVozilo() {}

	public DostupnoVozilo(String naziv, String regTable) {
		super();
		this.naziv = naziv;
		this.regTable = regTable;
	}
	
	
	public DostupnoVozilo(int id,String naziv, String regTable, String nazivPrevoznika, String nazivTipa,
			int idPrevoznika) {
		super();
		this.idVozila = id;
		this.naziv = naziv;
		this.regTable = regTable;
		this.nazivPrevoznika = nazivPrevoznika;
		this.nazivTipa = nazivTipa;
		this.idPrevoznika = idPrevoznika;
	}

	public DostupnoVozilo(int id, int idTipaVozila,int idPrevoznika, String naziv, String regTable) {
		this.idVozila = id;
		this.idTipaVozila=idTipaVozila;
		this.naziv = naziv;
		this.regTable = regTable;
		this.idPrevoznika = idPrevoznika;
	}
	

	public DostupnoVozilo(int id, int idPrevoznika, String naziv, String regTable) {
		super();
		this.idVozila = id;
		this.naziv = naziv;
		this.regTable = regTable;
		this.idPrevoznika = idPrevoznika;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getRegTable() {
		return regTable;
	}

	public void setRegTable(String regTable) {
		this.regTable = regTable;
	}

	public TipVozila getTipVozila() {
		return tipVozila;
	}

	public void setTipVozila(TipVozila tipVozila) {
		this.tipVozila = tipVozila;
	}

	public VoziloID getId() {
		return id;
	}

	public void setId(VoziloID id) {
		this.id = id;
	}

	public Prevoznik getPrevoznik() {
		return prevoznik;
	}

	public void setPrevoznik(Prevoznik prevoznik) {
		this.prevoznik = prevoznik;
	}

	public int getIdPrevoznika() {
		return idPrevoznika;
	}

	public void setIdPrevoznika(int idPrevoznika) {
		this.idPrevoznika = idPrevoznika;
	}

	public int getIdVozila() {
		return idVozila;
	}

	public void setIdVozila(int idVozila) {
		this.idVozila = idVozila;
	}

	public String getNazivPrevoznika() {
		return nazivPrevoznika;
	}

	public void setNazivPrevoznika(String nazivPrevoznika) {
		this.nazivPrevoznika = nazivPrevoznika;
	}

	public String getNazivTipa() {
		return nazivTipa;
	}

	public void setNazivTipa(String nazivTipa) {
		this.nazivTipa = nazivTipa;
	}

	public int getIdTipaVozila() {
		return idTipaVozila;
	}

	public void setIdTipaVozila(int idTipaVozila) {
		this.idTipaVozila = idTipaVozila;
	}
	
	


}
