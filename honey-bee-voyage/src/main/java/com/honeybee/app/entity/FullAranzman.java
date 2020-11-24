package com.honeybee.app.entity;

import java.util.List;

public class FullAranzman {

	private Aranzman aranzman;
	private List<Destinacija> destinacija;
	private double cena;
	private String tipPrevoza;
	
	
	public FullAranzman() {}
	
	
	public FullAranzman(Aranzman a,List<Destinacija> destinacija,String tipPrevoza,double cena) {
		this.aranzman=a;
		this.destinacija=destinacija;
		this.tipPrevoza=tipPrevoza;
		this.cena=cena;
	}
	public double getCena() {
		return cena;
	}
	public void setCena(double cena) {
		this.cena = cena;
	}
	public String getTipPrevoza() {
		return tipPrevoza;
	}
	public void setTipPrevoza(String tipPrevoza) {
		this.tipPrevoza = tipPrevoza;
	}


	public List<Destinacija> getDestinacija() {
		return destinacija;
	}

	public Aranzman getAranzman() {
		return aranzman;
	}


	public void setAranzman(Aranzman aranzman) {
		this.aranzman = aranzman;
	}


	public void setDestinacija(List<Destinacija> destinacija) {
		this.destinacija = destinacija;
	}
	
	
}
