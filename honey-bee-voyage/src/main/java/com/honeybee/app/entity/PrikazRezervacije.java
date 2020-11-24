package com.honeybee.app.entity;

public class PrikazRezervacije {

	private int id_aranzmana;
	private int broj_putnika;
	private double cena;
	private int id_smestaja;
	private int id_ponude;
	private int id_rezervacije;
	private int id_putnika;
	private String naziv_kategorije;
	private int broj_dece;
	private int grupa;
	
	private String naziv_aranzmana;
	private String naziv_smestaja;
	private String datum_polaska;
	private String datum_dolaska;
	private String datumPocetkaAr;
	private String datumKrajaAr;
	private String ime;
	private String prezime;
	
	public PrikazRezervacije(int broj_putnika, double cena, String naziv_aranzmana, String naziv_smestaja,
			String datum_polaska, String datum_dolaska, String datumPocetkaAr, String datumKrajaAr, int id_aranzmana, int id_rezervacije
			,int id_smestaja, int id_ponude, int id_putnika, String id_kategorije, int broj_dece, String ime, String prezime,
			int grupa) {
		super();
		this.broj_putnika = broj_putnika;
		this.cena = cena;
		this.naziv_aranzmana = naziv_aranzmana;
		this.naziv_smestaja = naziv_smestaja;
		this.datum_polaska = datum_polaska;
		this.datum_dolaska = datum_dolaska;
		this.datumPocetkaAr=datumPocetkaAr;
		this.datumKrajaAr=datumKrajaAr;
		this.id_aranzmana=id_aranzmana;
		this.id_rezervacije=id_rezervacije;
		this.id_smestaja=id_smestaja;
		this.id_ponude=id_ponude;
		this.id_putnika=id_putnika;
		this.naziv_kategorije=id_kategorije;
		this.broj_dece=broj_dece;
		this.ime=ime;
		this.prezime=prezime;
		this.grupa=grupa;
	}

	
	public PrikazRezervacije(int broj_putnika, double cena, String naziv_aranzmana, String naziv_smestaja,
			String datum_polaska, String datum_dolaska, String datumPocetkaAr, String datumKrajaAr, int id_aranzmana, int id_rezervacije
			,int id_smestaja, int id_ponude, int id_putnika, String id_kategorije, int broj_dece,int grupa) {
		super();
		this.broj_putnika = broj_putnika;
		this.cena = cena;
		this.naziv_aranzmana = naziv_aranzmana;
		this.naziv_smestaja = naziv_smestaja;
		this.datum_polaska = datum_polaska;
		this.datum_dolaska = datum_dolaska;
		this.datumPocetkaAr=datumPocetkaAr;
		this.datumKrajaAr=datumKrajaAr;
		this.id_aranzmana=id_aranzmana;
		this.id_rezervacije=id_rezervacije;
		this.id_smestaja=id_smestaja;
		this.id_ponude=id_ponude;
		this.id_putnika=id_putnika;
		this.naziv_kategorije=id_kategorije;
		this.broj_dece=broj_dece;
		this.grupa=grupa;
	}

	public PrikazRezervacije(int id_aranzmana, int broj_putnika, double cena, int id_smestaja, int id_ponude,
			int id_rezervacije, String naziv_aranzmana, String naziv_smestaja, String datum_polaska,
			String datum_dolaska) {
		this.id_aranzmana = id_aranzmana;
		this.broj_putnika = broj_putnika;
		this.cena = cena;
		this.id_smestaja = id_smestaja;
		this.id_ponude = id_ponude;
		this.id_rezervacije = id_rezervacije;
		this.naziv_aranzmana = naziv_aranzmana;
		this.naziv_smestaja = naziv_smestaja;
		this.datum_polaska = datum_polaska;
		this.datum_dolaska = datum_dolaska;
	}

	public PrikazRezervacije() {}
	
	public PrikazRezervacije(int id_aranzmana, int broj_putnika, double cena, int id_smestaja, int id_ponude,
			int id_rezervacije, int id_putnika, int broj_dece) {
		this.id_aranzmana = id_aranzmana;
		this.broj_putnika = broj_putnika;
		this.cena = cena;
		this.id_smestaja = id_smestaja;
		this.id_ponude = id_ponude;
		this.id_rezervacije = id_rezervacije;
		this.id_putnika=id_putnika;
		this.setBroj_dece(broj_dece);
	}
	public int getId_aranzmana() {
		return id_aranzmana;
	}
	public void setId_aranzmana(int id_aranzmana) {
		this.id_aranzmana = id_aranzmana;
	}
	public int getBroj_putnika() {
		return broj_putnika;
	}
	public void setBroj_putnika(int broj_putnika) {
		this.broj_putnika = broj_putnika;
	}
	public double getCena() {
		return cena;
	}
	public void setCena(double cena) {
		this.cena = cena;
	}
	public int getId_smestaja() {
		return id_smestaja;
	}
	public void setId_smestaja(int id_smestaja) {
		this.id_smestaja = id_smestaja;
	}
	public int getId_ponude() {
		return id_ponude;
	}
	public void setId_ponude(int id_ponude) {
		this.id_ponude = id_ponude;
	}
	public int getId_rezervacije() {
		return id_rezervacije;
	}
	public void setId_rezervacije(int id_rezervacije) {
		this.id_rezervacije = id_rezervacije;
	}

	public String getNaziv_aranzmana() {
		return naziv_aranzmana;
	}

	public void setNaziv_aranzmana(String naziv_aranzmana) {
		this.naziv_aranzmana = naziv_aranzmana;
	}

	public String getNaziv_smestaja() {
		return naziv_smestaja;
	}

	public void setNaziv_smestaja(String naziv_smestaja) {
		this.naziv_smestaja = naziv_smestaja;
	}

	public String getDatum_polaska() {
		return datum_polaska;
	}

	public void setDatum_polaska(String datum_polaska) {
		this.datum_polaska = datum_polaska;
	}

	public String getDatum_dolaska() {
		return datum_dolaska;
	}

	public void setDatum_dolaska(String datum_dolaska) {
		this.datum_dolaska = datum_dolaska;
	}

	public String getDatumPocetkaAr() {
		return datumPocetkaAr;
	}

	public void setDatumPocetkaAr(String datumPocetkaAr) {
		this.datumPocetkaAr = datumPocetkaAr;
	}

	public String getDatumKrajaAr() {
		return datumKrajaAr;
	}

	public void setDatumKrajaAr(String datumKrajaAr) {
		this.datumKrajaAr = datumKrajaAr;
	}

	public int getId_putnika() {
		return id_putnika;
	}

	public void setId_putnika(int id_putnika) {
		this.id_putnika = id_putnika;
	}

	public String getNaziv_kategorije() {
		return naziv_kategorije;
	}

	public void setNaziv_kategorije(String id_kategorije) {
		this.naziv_kategorije = id_kategorije;
	}

	public int getBroj_dece() {
		return broj_dece;
	}

	public void setBroj_dece(int broj_dece) {
		this.broj_dece = broj_dece;
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


	public int getGrupa() {
		return grupa;
	}


	public void setGrupa(int grupa) {
		this.grupa = grupa;
	}
	
	
	
	
	
}
