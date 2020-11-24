package com.honeybee.app.entity;

import java.util.List;

public class PrikazAtrakcija {

	private TipAtrakcije tipAtrakcije;
	private List<Atrakcija> atrakcije;
	
	public PrikazAtrakcija(TipAtrakcije tipAtrakcije, List<Atrakcija> atrakcije) {
		this.tipAtrakcije = tipAtrakcije;
		this.atrakcije = atrakcije;
	}
	
	public PrikazAtrakcija() {}

	public TipAtrakcije getTipAtrakcije() {
		return tipAtrakcije;
	}

	public void setTipAtrakcije(TipAtrakcije tipAtrakcije) {
		this.tipAtrakcije = tipAtrakcije;
	}

	public List<Atrakcija> getAtrakcije() {
		return atrakcije;
	}

	public void setAtrakcije(List<Atrakcija> atrakcije) {
		this.atrakcije = atrakcije;
	}
	
	
}
