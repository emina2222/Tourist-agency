package com.honeybee.app.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="AUTENTIFIKACIJA")
public class Autentifikacija {

	@Id
	@Column(name="id_kredencijala")
	private int idKredencijala;
	
	@Column(name="korisnicko_ime")
	private String korisnickoIme;
	
	@Column(name="lozinka")
	private String lozinka;
	
	@Column(name="uloga")
	private String uloga;
	
	@Column(name="enabled")
	private int enabled;
	
	@OneToOne(mappedBy="autentifikacija", cascade=CascadeType.ALL)
	private Putnik putnik; //jer 1 autentif. ima samo 1 putnik
	
	@OneToOne(mappedBy="autentifikacija",cascade=CascadeType.ALL)
	private Zaposleni zaposleni; //1 zaposleni ima 1 kredencijal
	
	public Autentifikacija() {}
	
	public Autentifikacija(String korisnickoIme,String lozinka,String uloga,int enabled) {
		this.korisnickoIme=korisnickoIme;
		this.lozinka=lozinka;
		this.uloga=uloga;
		this.enabled=enabled;
	}

	public int getIdKredencijala() {
		return idKredencijala;
	}

	public void setIdKredencijala(int idKredencijala) {
		this.idKredencijala = idKredencijala;
	}

	public String getKorisnickoIme() {
		return korisnickoIme;
	}

	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}

	public String getLozinka() {
		return lozinka;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}

	public String getUloga() {
		return uloga;
	}

	public void setUloga(String uloga) {
		this.uloga = uloga;
	}

	public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return "Autentifikacija [idKredencijala=" + idKredencijala + ", korisnickoIme=" + korisnickoIme + ", lozinka="
				+ lozinka + ", uloga=" + uloga + ", enabled=" + enabled + "]";
	}
	
	
}








