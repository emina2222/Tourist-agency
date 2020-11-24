package com.honeybee.app.entity;

import java.util.List;

public class FullSmestajOrganizacija {
	
	private Smestaj smestaj;
	private List<PonudaSmestaja> ponude;
	private List<Integer> jedinice;
	private List<TabelaPrikaz> tp;
	
	
	
	public FullSmestajOrganizacija(Smestaj smestaj, List<PonudaSmestaja> ponude, List<Integer> jedinice) {
		this.smestaj = smestaj;
		this.ponude = ponude;
		this.jedinice = jedinice;
	}



	public FullSmestajOrganizacija() {}


	public Smestaj getSmestaj() {
		return smestaj;
	}
	public void setSmestaj(Smestaj smestaj) {
		this.smestaj = smestaj;
	}
	public List<PonudaSmestaja> getPonude() {
		return ponude;
	}
	public void setPonude(List<PonudaSmestaja> ponude) {
		this.ponude = ponude;
	}
	public List<Integer> getJedinice() {
		return jedinice;
	}
	public void setJedinice(List<Integer> jedinice) {
		this.jedinice = jedinice;
	}


	public List<TabelaPrikaz> getTp() {
		return tp;
	}



	public void setTp(List<TabelaPrikaz> tp) {
		this.tp = tp;
	}
	
	

}
