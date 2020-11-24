package com.honeybee.app.entity;

public class UkupnaCena {
	
	private int idAranzmana;
	private int idSmestaja;
	private int idPonude;
	private int brojKreveta;
	
	public UkupnaCena(int idAranzmana, int idSmestaja, int idPonude, int brojKreveta) {
		this.idAranzmana = idAranzmana;
		this.idSmestaja = idSmestaja;
		this.idPonude = idPonude;
		this.brojKreveta = brojKreveta;
	}
	
	public UkupnaCena() {}

	public int getIdAranzmana() {
		return idAranzmana;
	}

	public void setIdAranzmana(int idAranzmana) {
		this.idAranzmana = idAranzmana;
	}

	public int getIdSmestaja() {
		return idSmestaja;
	}

	public void setIdSmestaja(int idSmestaja) {
		this.idSmestaja = idSmestaja;
	}

	public int getIdPonude() {
		return idPonude;
	}

	public void setIdPonude(int idPonude) {
		this.idPonude = idPonude;
	}

	public int getBrojKreveta() {
		return brojKreveta;
	}

	public void setBrojKreveta(int brojKreveta) {
		this.brojKreveta = brojKreveta;
	}
	
	
	

}
