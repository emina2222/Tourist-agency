package com.honeybee.app.entity;

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
@Table(name="PUTNIK")
public class Putnik{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_PUTNIKA")
	private int idPutnika;
	
	private int idKred;
		
	@Column(name="IME")
	private String ime;
	
	@Column(name="PREZIME")
	private String prezime;
	
	@Column(name="BROJ_PASOSA")
	private String brojPasosa;
	
	@Column(name="BROJ_LICNE_KARTE")
	private String brojLicneKarte;
	
	@Column(name="BROJ_TELEFONA")
	private String brojTelefona;
	
	@Column(name="EMAIL")
	private String email;
	
	@OneToOne
	@JoinColumn(name="ID_KREDENCIJALA")
	private Autentifikacija autentifikacija;
	
	@OneToMany(mappedBy="putnik",cascade= {CascadeType.DETACH,CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
	private List<Rezervacija> rezervacije;
	
	public Putnik() {}
	
	
	
	public Putnik(int idPutnika, String ime, String prezime, String brojTelefona, String email) {
		super();
		this.idPutnika = idPutnika;
		this.ime = ime;
		this.prezime = prezime;
		this.brojTelefona = brojTelefona;
		this.email = email;
	}



	public Putnik(int idPutnika, int idKredencijala, String ime, String prezime,String brojPasosa,String brojLK, String brojTelefona, String email) {
		this.idPutnika = idPutnika;
		this.idKred=idKredencijala;
		this.ime = ime;
		this.prezime = prezime;
		this.brojPasosa=brojPasosa;
		this.brojLicneKarte=brojLK;
		this.brojTelefona = brojTelefona;
		this.email = email;
	}



	public Putnik(String ime,String prezime, String pasos, String lk, String email) {
		this.ime=ime;
		this.prezime=prezime;
		this.brojPasosa=pasos;
		this.brojLicneKarte=lk;
		this.email=email;
	}
	
	
	
	public Putnik(String ime, String prezime) {
		super();
		this.ime = ime;
		this.prezime = prezime;
	}



	public Putnik(String ime,String prezime, String email,String brojTelefona) {
		this.ime=ime;
		this.prezime=prezime;
		this.email=email;
		this.brojTelefona=brojTelefona;
	}

	public int getIdPutnika() {
		return idPutnika;
	}



	public void setIdPutnika(int idPutnika) {
		this.idPutnika = idPutnika;
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



	public String getBrojPasosa() {
		return brojPasosa;
	}



	public void setBrojPasosa(String brojPasosa) {
		this.brojPasosa = brojPasosa;
	}



	public String getBrojLicneKarte() {
		return brojLicneKarte;
	}



	public void setBrojLicneKarte(String brojLicneKarte) {
		this.brojLicneKarte = brojLicneKarte;
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

	
	public List<Rezervacija> getRezervacije() {
		return rezervacije;
	}

	public void setRezervacije(List<Rezervacija> rezervacije) {
		this.rezervacije = rezervacije;
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



	@Override
	public String toString() {
		return "Putnik [idPutnika=" + idPutnika + ", ime=" + ime + ", prezime="
				+ prezime + ", brojPasosa=" + brojPasosa + ", brojLicneKarte=" + brojLicneKarte + ", brojTelefona="
				+ brojTelefona + ", email=" + email + "]";
	}
	
	
	
}
