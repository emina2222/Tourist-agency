package com.honeybee.app.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="KATEGORIJA_ARANZMANA")
public class Kategorija {

	@Id
	@Column(name="id_kategorije")
	private int id_kategorije;
	
	@Column(name="naziv")
	private String naziv;
	
	@Column(name="opis")
	private String opis;
	
	private int grupa;
	
	@OneToMany(mappedBy="kategorija",cascade= {CascadeType.DETACH,CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
	private List<Aranzman> aranzmani;
	
	public Kategorija() {}
	
	public Kategorija(int id,String naziv,String opis) {
		this.id_kategorije=id;
		this.naziv=naziv;
		this.opis=opis;
	}
	
	public Kategorija(int id,String naziv,String opis,int grupa) {
		this.id_kategorije=id;
		this.naziv=naziv;
		this.opis=opis;
		this.grupa=grupa;
	}

	public int getId_kategorije() {
		return id_kategorije;
	}

	public void setId_kategorije(int id_kategorije) {
		this.id_kategorije = id_kategorije;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}
	
	
	public List<Aranzman> getAranzmani() {
		return aranzmani;
	}

	public void setAranzmani(List<Aranzman> aranzmani) {
		this.aranzmani = aranzmani;
	}

	@Override
	public String toString() {
		return "Kategorija [id_kategorije=" + id_kategorije + ", naziv=" + naziv + ", opis=" + opis + "]";
	}

	public int getGrupa() {
		return grupa;
	}

	public void setGrupa(int grupa) {
		this.grupa = grupa;
	}
	
	
}
