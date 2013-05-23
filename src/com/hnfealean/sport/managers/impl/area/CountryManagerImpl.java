package com.hnfealean.sport.managers.impl.area;

import com.hnfealean.sport.managers.area.CountryManager;
import com.hnfealean.sport.managers.impl.product.CommonManager;
import com.hnfealean.sport.model.area.Country;
import com.hnfealean.sport.pageset.PageModel;
import com.hnfealean.sport.web.SystemException;

public class CountryManagerImpl extends CommonManager implements CountryManager {

	public PageModel searchAll() {
		return searchPaginated("from Country");
	}

	public boolean addCountry(Country country) {
		getHibernateTemplate().save(country);
		
		return false;
	}

	public boolean deleteCountry(int id) {

		getSession().createQuery("delete from Country where id=?").setParameter(0, id).executeUpdate();
		return false;
	}
	public boolean deleteCountries(int[] ids) {
		if(ids == null){
			throw new SystemException("非法操作！");
		}
		StringBuffer s = new StringBuffer();
		for(int i=0; i<ids.length-1;i++){
			s.append(ids[i]);
			s.append(",");
		}
		s.append(ids[ids.length-1]);
		getSession().createQuery("delete from Country where id in ("+ s.toString()+")").executeUpdate();
		return false;
	}

	public Country getCountryById(int id) {
		return (Country)getHibernateTemplate().get(Country.class, id);
		
	}

	public boolean updateCountry(int id, Country country) {
		Country ini = (Country) getHibernateTemplate().load(Country.class, id);
		ini.setCnName(country.getCnName());
		ini.setEnName(country.getEnName());
		ini.setCountries_iso_code_2(country.getCountries_iso_code_2());
		ini.setCountries_iso_code_3(country.getCountries_iso_code_3());
		getHibernateTemplate().update(ini);
		return false;
	}

	public Country getByName(String name) {
		Country country =(Country)getSession().createQuery("from Country where cnName=?")
		.setParameter(0, name).uniqueResult();
		return country;
	}
}
