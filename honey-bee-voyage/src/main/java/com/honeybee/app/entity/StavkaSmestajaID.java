package com.honeybee.app.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class StavkaSmestajaID implements Serializable {

	@Column(name="ID_SMESTAJA")
	private int idSmestaja;
	
	@Column(name="ID_SMESTAJNE_JEDINICE")
	private int idSmestajneJedinice;
	
	@Column(name="ID_PONUDE")
	private int idPonude;
}
