package com.hnfealean.sport.managers.area;

import com.hnfealean.sport.model.area.Country;
import com.hnfealean.sport.pageset.PageModel;

public interface CountryManager {
	public Country getByName(String name);
	public PageModel searchAll();
	public boolean addCountry(Country country);
	public boolean deleteCountry(int id);
	public boolean deleteCountries(int[] ids);
	public Country getCountryById(int id);
	public boolean updateCountry(int id,Country country);
}
