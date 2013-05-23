package com.hnfealean.sport.managers.impl.area;

import java.util.List;

import org.hibernate.Query;

import com.hnfealean.sport.managers.area.ZoneManager;
import com.hnfealean.sport.managers.impl.product.CommonManager;
import com.hnfealean.sport.model.area.Zone;
import com.hnfealean.sport.pageset.PageModel;

public class ZoneManagerImpl extends CommonManager implements ZoneManager {

	public boolean addZone(Zone zone) {
	getHibernateTemplate().save(zone);
		return false;
	}
	public Zone getZoneById(int id) {
		
		Zone zone =(Zone) getSession().createQuery("from Zone where id="+id)
		.uniqueResult();
			
			/*(Zone) getSession().createQuery("from Zone where id=?")
		.setParameter(0, id)
		.uniqueResult();//getHibernateTemplate().load(Zone.class, id);
		*/
		return zone ;
		}
	public PageModel searchZones(int pId,int countryId) {
		PageModel pm ;
		if(countryId==0){
			if(pId==0)
				pm = searchPaginated("from Zone where parent=null");
			else
				pm = searchPaginated("from Zone where parent.id=?",pId);
			return pm;
		}
		if(pId==0)
			pm = searchPaginated("from Zone where parent=null and country.id=?",countryId);
		else
			pm = searchPaginated("from Zone where parent.id=? and country.id="+countryId,pId);
		return pm;
	}
	public boolean updateZone(int id, Zone zone) {
		//getHibernateTemplate().load(Zone, id)
		getHibernateTemplate().update(zone);
		return false;
	}
	public boolean delete(int id) {
		//getHibernateTemplate().load(Zone, id)
		getSession().createQuery("delete from Zone where id = ?").setParameter(0, id).executeUpdate();
		return false;
	}
	public List getZones(int pId, int countryId) {
		List pm ;
		 Query query;
		if(countryId==0){
			
			if(pId==0){
				  query =getSession().createQuery("from Zone where parent=null");
				// query.setCacheable(true);
			
			}else{
				  query = getSession().createQuery("from Zone where parent.id=?").setParameter(0, pId);
				// query.setCacheable(true);
				// pm =query.list();
			}
			 query.setCacheable(true);
			 if (getHibernateTemplate().getQueryCacheRegion() != null) {

		        	query.setCacheRegion(getHibernateTemplate().getQueryCacheRegion());

		        }
			pm = query.list();
			return pm;
		}
		if(pId==0){
			query =getSession().createQuery("from Zone where parent=null and country.id=?").setParameter(0,countryId);
			 //query.setCacheable(true);
				//pm = query.list();
		}else{
			 query = getSession().createQuery("from Zone where parent.id=? and country.id=?").setParameter(0, pId)
				.setParameter(1, countryId);
			//query.setCacheable(true);
			// pm =query.list();
		}
		
		query.setCacheable(true);
        if (getHibernateTemplate().getQueryCacheRegion() != null) {

        	query.setCacheRegion(getHibernateTemplate().getQueryCacheRegion());

        }
		pm =query.list();
		return pm;
	}
	public List getTopZones() {
		return getSession().createQuery("from Zone where parent=null").list();
	}
}
