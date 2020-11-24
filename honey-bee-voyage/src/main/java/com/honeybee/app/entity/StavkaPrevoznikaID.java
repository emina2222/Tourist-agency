package com.honeybee.app.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class StavkaPrevoznikaID implements Serializable{
	
	@Column(name="ID_VOZILA")
	private int idVozila;
	
	@Column(name="ID_PREVOZNIKA")
	private int idPrevoznika;
	
	@Column(name="ID_PONUDE_PREVOZNIKA")
	private int idPonudePrevoznika;

}
