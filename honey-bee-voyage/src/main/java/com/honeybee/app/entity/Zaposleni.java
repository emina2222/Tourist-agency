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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="ZAPOSLENI")
public class Zaposleni {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="SIFRA_RADNIKA")
	private int idRadnika;
	
	private int idKred;
	
	@Column(name="IME")
	private String ime;
	
	@Column(name="PREZIME")
	private String prezime;
		
	@Column(name="BROJ_TELEFONA")
	private String brojTelefona;
	
	@Column(name="EMAIL")
	private String email;
	
	@Column(name="ADRESA")
	private String adresa;
	
	@Column(name="RADNO_MESTO")
	private String radnoMesto;
	
	@Column(name="PLATA")
	private double plata;
	
	@Column(name="DATUM_ZAPOSLENJA")
	private Date datumZaposlenja;
	
	@OneToOne
	@JoinColumn(name="ID_KREDENCIJALA")
	private Autentifikacija autentifikacija;
	
	@OneToMany(mappedBy="zaposleni",cascade= {CascadeType.DETACH,CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
	private List<Rezervacija> rezervacije;
	
	public Zaposleni() {}

	public Zaposleni(String ime, String prezime, String brojTelefona, String email, String adresa, String radnoMesto,
			double plata, Date datumZaposlenja) {
		this.ime = ime;
		this.prezime = prezime;
		this.brojTelefona = brojTelefona;
		this.email = email;
		this.adresa = adresa;
		this.radnoMesto = radnoMesto;
		this.plata = plata;
		this.datumZaposlenja = datumZaposlenja;
	}
	
	
	public Zaposleni(int idRadnika, int idKred, String ime, String prezime, String brojTelefona, String email,
			String adresa, double plata, Date datumZaposlenja) {
		super();
		this.idRadnika = idRadnika;
		this.idKred = idKred;
		this.ime = ime;
		this.prezime = prezime;
		this.brojTelefona = brojTelefona;
		this.email = email;
		this.adresa = adresa;
		this.plata = plata;
		this.datumZaposlenja = datumZaposlenja;
	}

	public int getIdRadnika() {
		return idRadnika;
	}

	public void setIdRadnika(int idRadnika) {
		this.idRadnika = idRadnika;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getBrojTelefona() {
		return brojTelefona;
	}

	public void setBrojTelefona(String brojTelefona) {
		this.brojTelefona = brojTelefona;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getRadnoMesto() {
		return radnoMesto;
	}

	public void setRadnoMesto(String radnoMesto) {
		this.radnoMesto = radnoMesto;
	}

	public double getPlata() {
		return plata;
	}

	public void setPlata(double plata) {
		this.plata = plata;
	}

	public Date getDatumZaposlenja() {
		return datumZaposlenja;
	}

	public void setDatumZaposlenja(Date datumZaposlenja) {
		this.datumZaposlenja = datumZaposlenja;
	}

	public Autentifikacija getAutentifikacija() {
		return autentifikacija;
	}

	public void setAutentifikacija(Autentifikacija autentifikacija) {
		this.autentifikacija = autentifikacija;
	}

	public int getIdKred() {
		return idKred;
	}

	public void setIdKred(int idKred) {
		this.idKred = idKred;
	}
	
	
}
