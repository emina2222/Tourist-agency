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
@Table(name="REZERVACIJA")
public class Rezervacija {

	/*ZA DODAVANJE FOREIGN KEYS
	 * MAPPED BY - naziv polja u primarnoj tabeli (npr Zaposleni-> sifraRadnika)	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_REZERVACIJE")
	private int idRezervacije;
	
	@Column(name="DATUM_REZERVACIJE")
	private Date datumRezervacije;
	
	@Column(name="BROJ_PUTNIKA")
	private int brojPutnika;
	
	@Column(name="CENA")
	private double cena;
	
	@ManyToOne(cascade= {CascadeType.DETACH,CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
	@JoinColumn(name="ID_ARANZMANA")
	private Aranzman aranzman;
	
	@ManyToOne(cascade= {CascadeType.DETACH,CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
	@JoinColumn(name="ID_PUTNIKA")
	private Putnik putnik;
	
	@ManyToOne(cascade= {CascadeType.DETACH,CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
	@JoinColumn(name="SIFRA_RADNIKA")
	private Zaposleni zaposleni;
	
	@OneToMany(mappedBy="rezervacija")
	private List<Placanja> placanja;
	
	public Rezervacija() {}
	
	

	public Rezervacija(int idRezervacije, Date datumRezervacije, int brojPutnika, double cena) {
		this.idRezervacije = idRezervacije;
		this.datumRezervacije = datumRezervacije;
		this.brojPutnika = brojPutnika;
		this.cena = cena;
	}



	public Rezervacija(Date datumRezervacije, int brojPutnika, double cena) {
		this.datumRezervacije = datumRezervacije;
		this.brojPutnika = brojPutnika;
		this.cena = cena;
	}

	public int getIdRezervacije() {
		return idRezervacije;
	}

	public void setIdRezervacije(int idRezervacije) {
		this.idRezervacije = idRezervacije;
	}

	public Date getDatumRezervacije() {
		return datumRezervacije;
	}

	public void setDatumRezervacije(Date datumRezervacije) {
		this.datumRezervacije = datumRezervacije;
	}

	public int getBrojPutnika() {
		return brojPutnika;
	}

	public void setBrojPutnika(int brojPutnika) {
		this.brojPutnika = brojPutnika;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}
	
	

	public Aranzman getAranzman() {
		return aranzman;
	}

	public void setAranzman(Aranzman aranzman) {
		this.aranzman = aranzman;
	}
	

	public Putnik getPutnik() {
		return putnik;
	}

	public void setPutnik(Putnik putnik) {
		this.putnik = putnik;
	}

	
	public Zaposleni getZaposleni() {
		return zaposleni;
	}

	public void setZaposleni(Zaposleni zaposleni) {
		this.zaposleni = zaposleni;
	}

	public List<Placanja> getPlacanja() {
		return placanja;
	}

	public void setPlacanja(List<Placanja> placanja) {
		this.placanja = placanja;
	}
	
	

	@Override
	public String toString() {
		return "Rezervacija [idRezervacije=" + idRezervacije + ", datumRezervacije=" + datumRezervacije
				+ ", brojPutnika=" + brojPutnika + ", cena=" + cena + "]";
	}
	
	
	
}
