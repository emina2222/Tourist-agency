package com.honeybee.app.dao;

import java.util.List;

import com.honeybee.app.entity.Kategorija;

public interface KategorijaDAO {

	public List<Kategorija> getAllCategories();
	
	public String getCategoryName(int id);
	
	public Integer getCategoryGroupForArrangement(int idAranzmana);
	
	public Integer getCategoryGroup(int id);

	public List<Kategorija> sveKategorije();
	
	//public String addNewCategory(String naziv, String opis);
	
	public String addNewCategoryWithGroup(String naziv, String opis, int grupa);
		
}
