package com.honeybee.app.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="ARANZMAN")
public class Aranzman {

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_ARANZMANA")
	private int idAranzmana;
	
	@Column(name="NAZIV")
	private String naziv;
	
	@Column(name="PROFIT_AGENCIJE")
	private int profitAgencije;
	
	@Column(name="POPUST")
	private int popust;
	
	@Column(name="DATUM_POLASKA")
	private Date datumPolaska;
	
	@Column(name="DATUM_DOLASKA")
	private Date datumDolaska;
	
	@Column(name="KAPACITET")
	private int kapacitet;
	
	private int idKat;
	
	private double cenaOsiguranja;
	
	private int objavljen;
	
	@ManyToOne(cascade= {CascadeType.DETACH,CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
	@JoinColumn(name="ID_KATEGORIJE")
	private Kategorija kategorija;
	
	@OneToMany(mappedBy="aranzman",cascade= {CascadeType.DETACH,CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
	private List<Rezervacija> rezervacije;
	
	@OneToMany(mappedBy="aranzman",cascade= {CascadeType.DETACH,CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
	private List<SpisakDestinacija> spisakDestinacija;
	
	@OneToMany(mappedBy="aranzman",cascade= {CascadeType.DETACH,CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
	private List<PonudaSmestaja> ponudeSmestaja;
	
	@OneToMany(mappedBy="aranzman",cascade= {CascadeType.DETACH,CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
	private List<PonudaPrevoznika> ponudePrevoznika;
	
	@OneToMany(mappedBy="aranzman",cascade= {CascadeType.DETACH,CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
	private List<AngazovanjeVodica> angazovanja;
	
	public Aranzman() {}
	
	

	public Aranzman(int idAranzmana, int idKat,String naziv, int profitAgencije, Date datumPolaska, Date datumDolaska,int popust, 
			double cenaOsiguranja, int kapacitet, int objavljen) {
		super();
		this.idAranzmana = idAranzmana;
		this.naziv = naziv;
		this.profitAgencije = profitAgencije;
		this.popust = popust;
		this.datumPolaska = datumPolaska;
		this.datumDolaska = datumDolaska;
		this.kapacitet = kapacitet;
		this.cenaOsiguranja=cenaOsiguranja;
		this.idKat = idKat;
		this.objavljen=objavljen;
	}



	public Aranzman(String naziv, int profitAgencije, int popust, Date datumPolaska, Date datumDolaska) {
		super();
		this.naziv = naziv;
		this.profitAgencije = profitAgencije;
		this.popust = popust;
		this.datumPolaska = datumPolaska;
		this.datumDolaska = datumDolaska;
	}
	
	public Aranzman(int idAranzmana,String naziv, int profitAgencije, int popust, Date datumPolaska, Date datumDolaska, int kapacitet) {
		this.idAranzmana=idAranzmana;
		this.naziv = naziv;
		this.profitAgencije = profitAgencije;
		this.popust = popust;
		this.datumPolaska = datumPolaska;
		this.datumDolaska = datumDolaska;
		this.kapacitet=kapacitet;
	}
	
	public Aranzman(int idAranzmana, String naziv, Date datumPolaska, Date datumDolaska, int kapacitet) {
		super();
		this.idAranzmana = idAranzmana;
		this.naziv = naziv;
		this.datumPolaska = datumPolaska;
		this.datumDolaska = datumDolaska;
		this.kapacitet=kapacitet;
	}

	public Aranzman(int id,String naziv) {
		this.idAranzmana=id;
		this.naziv=naziv;
	}

	public int getIdAranzmana() {
		return idAranzmana;
	}

	public void setIdAranzmana(int idAranzmana) {
		this.idAranzmana = idAranzmana;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public int getProfitAgencije() {
		return profitAgencije;
	}

	public void setProfitAgencije(int profitAgencije) {
		this.profitAgencije = profitAgencije;
	}

	public int getPopust() {
		return popust;
	}

	public void setPopust(int popust) {
		this.popust = popust;
	}

	public Date getDatumPolaska() {
		return datumPolaska;
	}

	public void setDatumPolaska(Date datumPolaska) {
		this.datumPolaska = datumPolaska;
	}

	public Date getDatumDolaska() {
		return datumDolaska;
	}

	public void setDatumDolaska(Date datumDolaska) {
		this.datumDolaska = datumDolaska;
	}
	

	public Kategorija getKategorija() {
		return kategorija;
	}

	public void setKategorija(Kategorija kategorija) {
		this.kategorija = kategorija;
	}
	
	
	

	public int getKapacitet() {
		return kapacitet;
	}

	public void setKapacitet(int kapacitet) {
		this.kapacitet = kapacitet;
	}

	public List<SpisakDestinacija> getSpisakDestinacija() {
		return spisakDestinacija;
	}

	public void setSpisakDestinacija(List<SpisakDestinacija> spisakDestinacija) {
		this.spisakDestinacija = spisakDestinacija;
	}

	public List<PonudaSmestaja> getPonudeSmestaja() {
		return ponudeSmestaja;
	}

	public void setPonudeSmestaja(List<PonudaSmestaja> ponudeSmestaja) {
		this.ponudeSmestaja = ponudeSmestaja;
	}

	public List<PonudaPrevoznika> getPonudePrevoznika() {
		return ponudePrevoznika;
	}

	public void setPonudePrevoznika(List<PonudaPrevoznika> ponudePrevoznika) {
		this.ponudePrevoznika = ponudePrevoznika;
	}

	public List<AngazovanjeVodica> getAngazovanja() {
		return angazovanja;
	}

	public void setAngazovanja(List<AngazovanjeVodica> angazovanja) {
		this.angazovanja = angazovanja;
	}

	public List<Rezervacija> getRezervacije() {
		return rezervacije;
	}

	public void setRezervacije(List<Rezervacija> rezervacije) {
		this.rezervacije = rezervacije;
	}

	@Override
	public String toString() {
		return "Aranzman [idAranzmana=" + idAranzmana + ", naziv=" + naziv + ", profitAgencije=" + profitAgencije
				+ ", popust=" + popust + ", datumPolaska=" + datumPolaska + ", datumDolaska=" + datumDolaska + "]";
	}

	public int getIdKat() {
		return idKat;
	}

	public void setIdKat(int idKat) {
		this.idKat = idKat;
	}



	public double getCenaOsiguranja() {
		return cenaOsiguranja;
	}



	public void setCenaOsiguranja(double cenaOsiguranja) {
		this.cenaOsiguranja = cenaOsiguranja;
	}



	public int getObjavljen() {
		return objavljen;
	}



	public void setObjavljen(int objavljen) {
		this.objavljen = objavljen;
	}
	
	
	
	
}
