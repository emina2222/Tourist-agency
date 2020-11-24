package com.honeybee.app.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="TURISTICKI_VODIC")
public class TuristickiVodic {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_VODICA")
	private int id;
	
	@Column(name="IME")
	private String ime;
	
	@Column(name="PREZIME")
	private String prezime;
	
	@Column(name="SLUZBENI_TELEFON")
	private String telefon;
	
	@OneToMany(mappedBy="vodic",cascade= {CascadeType.DETACH,CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
	private List<AngazovanjeVodica> angazovanja;
	
	public TuristickiVodic() {}

	public TuristickiVodic(int idVodica,String ime, String prezime, String telefon) {
		this.id=idVodica;
		this.ime = ime;
		this.prezime = prezime;
		this.telefon = telefon;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public List<AngazovanjeVodica> getAngazovanja() {
		return angazovanja;
	}

	public void setAngazovanja(List<AngazovanjeVodica> angazovanja) {
		this.angazovanja = angazovanja;
	}
	
	
	
	
}
