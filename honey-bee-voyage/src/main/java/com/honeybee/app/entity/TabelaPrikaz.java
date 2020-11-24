package com.honeybee.app.entity;

import java.util.List;

public class TabelaPrikaz {

	private int brojKreveta;
	private List<Double> cene;
	public TabelaPrikaz() {}
	
	public TabelaPrikaz(int brojKreveta, List<Double> cene) {
		this.brojKreveta = brojKreveta;
		this.cene = cene;
	}

	public int getBrojKreveta() {
		return brojKreveta;
	}

	public void setBrojKreveta(int brojKreveta) {
		this.brojKreveta = brojKreveta;
	}

	public List<Double> getCene() {
		return cene;
	}

	public void setCene(List<Double> cene) {
		this.cene = cene;
	}
	
	
	
	
	
	
}
