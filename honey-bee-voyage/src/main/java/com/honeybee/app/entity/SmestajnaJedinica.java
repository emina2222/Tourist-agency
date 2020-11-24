package com.honeybee.app.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name="SMESTAJNA_JEDINICA")
public class SmestajnaJedinica {

	@EmbeddedId
	private SmJedinicaID id;
	
	@MapsId("smestajID") //uzima naziv klase PrevoznikID (prvo slovo malo)
	@JoinColumn(name="ID_SMESTAJA",referencedColumnName="id_smestaja")//name=>ime iz tabele u bazi; referencedColumnName=>naziv iz tabele PrevoznikID
	@ManyToOne
	private Smestaj smestaj;
	
	private int idSmestaja;
	
	private String nazivSmestaja;
	
	private int idSobe;
	
	@Column(name="BROJ_KREVETA")
	private int brojKreveta;
	
	@Column(name="OPSTE_KARAKTERISTIKE")
	private String opis;
	
	public SmestajnaJedinica() {}

	public SmestajnaJedinica(Smestaj smestaj, int brojKreveta, String opis) {
		super();
		this.smestaj = smestaj;
		this.brojKreveta = brojKreveta;
		this.opis = opis;
	}
	
	

	public SmestajnaJedinica(int idSmestaja, int idSobe, int brojKreveta, String opis) {
		super();
		this.idSmestaja = idSmestaja;
		this.idSobe = idSobe;
		this.brojKreveta = brojKreveta;
		this.opis = opis;
	}
	
	

	public SmestajnaJedinica(int idSmestaja, String nazivSmestaja, int idSobe, int brojKreveta, String opis) {
		super();
		this.idSmestaja = idSmestaja;
		this.nazivSmestaja = nazivSmestaja;
		this.idSobe = idSobe;
		this.brojKreveta = brojKreveta;
		this.opis = opis;
	}

	public SmJedinicaID getId() {
		return id;
	}

	public void setId(SmJedinicaID id) {
		this.id = id;
	}

	public Smestaj getSmestaj() {
		return smestaj;
	}

	public void setSmestaj(Smestaj smestaj) {
		this.smestaj = smestaj;
	}

	public int getBrojKreveta() {
		return brojKreveta;
	}

	public void setBrojKreveta(int brojKreveta) {
		this.brojKreveta = brojKreveta;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public int getIdSmestaja() {
		return idSmestaja;
	}

	public void setIdSmestaja(int idSmestaja) {
		this.idSmestaja = idSmestaja;
	}

	public int getIdSobe() {
		return idSobe;
	}

	public void setIdSobe(int idSobe) {
		this.idSobe = idSobe;
	}

	public String getNazivSmestaja() {
		return nazivSmestaja;
	}

	public void setNazivSmestaja(String nazivSmestaja) {
		this.nazivSmestaja = nazivSmestaja;
	}
	
	
}
