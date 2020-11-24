package com.honeybee.app.entity.views;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="prikazSvihKategorija")
public class GetAllCategories {

	@Id
	@Column(name="id_kategorije")
	private String id_kategorije;
	
	@Column(name="naziv")
	private String naziv;
	
	
	public String getId_kategorije() {
		return id_kategorije;
	}

	public void setId_kategorije(String id_kategorije) {
		this.id_kategorije = id_kategorije;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	 
	
}
