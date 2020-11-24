package com.honeybee.app.entity;

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
@Table(name="SPISAK_DESTINACIJA")
public class SpisakDestinacija {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_STAVKE_DESTINACIJE")
	private int id;
	
	@ManyToOne(cascade= {CascadeType.DETACH,CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
	@JoinColumn(name="ID_ARANZMANA")
	private Aranzman aranzman;
	
	@ManyToOne(cascade= {CascadeType.DETACH,CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
	@JoinColumn(name="ID_DESTINACIJE")
	private Destinacija destinacija;
	
	public SpisakDestinacija() {}
	
	

	public SpisakDestinacija(Aranzman aranzman, Destinacija destinacija) {
		super();
		this.aranzman = aranzman;
		this.destinacija = destinacija;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Aranzman getAranzman() {
		return aranzman;
	}

	public void setAranzman(Aranzman aranzman) {
		this.aranzman = aranzman;
	}

	public Destinacija getDestinacija() {
		return destinacija;
	}

	public void setDestinacija(Destinacija destinacija) {
		this.destinacija = destinacija;
	}
	
	
	//many to one za aranzman i destinacija
	
	
}
