package com.honeybee.app.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="PLACANJA")
public class Placanja {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_PLACANJA")
	private int idPlacanja;
	
	@Column(name="NACIN_PLACANJA")
	private String nacinPlacanja;
	
	@Column(name="DATUM_PLACANJA")
	private Date datumPlacanja;
	
	@Column(name="IZNOS")
	private double iznos;
	
	@Column(name="STATUS")
	private String status;
	
	@ManyToOne(cascade= {CascadeType.DETACH,CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
	@JoinColumn(name="ID_REZERVACIJE")
	private Rezervacija rezervacija;
	
	public Placanja() {}

	public Placanja(String nacinPlacanja, Date datumPlacanja, double iznos, String status) {
		super();
		this.nacinPlacanja = nacinPlacanja;
		this.datumPlacanja = datumPlacanja;
		this.iznos = iznos;
		this.status = status;
	}

	public int getIdPlacanja() {
		return idPlacanja;
	}

	public void setIdPlacanja(int idPlacanja) {
		this.idPlacanja = idPlacanja;
	}

	public String getNacinPlacanja() {
		return nacinPlacanja;
	}

	public void setNacinPlacanja(String nacinPlacanja) {
		this.nacinPlacanja = nacinPlacanja;
	}

	public Date getDatumPlacanja() {
		return datumPlacanja;
	}

	public void setDatumPlacanja(Date datumPlacanja) {
		this.datumPlacanja = datumPlacanja;
	}

	public double getIznos() {
		return iznos;
	}

	public void setIznos(double iznos) {
		this.iznos = iznos;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	

	public Rezervacija getRezervacija() {
		return rezervacija;
	}

	public void setRezervacija(Rezervacija rezervacija) {
		this.rezervacija = rezervacija;
	}

	@Override
	public String toString() {
		return "Placanja [idPlacanja=" + idPlacanja + ", nacinPlacanja=" + nacinPlacanja + ", datumPlacanja="
				+ datumPlacanja + ", iznos=" + iznos + ", status=" + status + "]";
	}
	
	
}
