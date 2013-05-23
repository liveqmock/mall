package com.hnfealean.sport.managers.impl.user_acl_module;

import java.util.List;

import com.hnfealean.sport.managers.impl.product.CommonManager;
import com.hnfealean.sport.managers.user_acl_module.AdministratorManager;
import com.hnfealean.sport.model.user_acl_module.Administrator;
import com.hnfealean.sport.model.user_acl_module.AdministratorRoles;
import com.hnfealean.sport.model.user_acl_module.Role;
import com.hnfealean.sport.pageset.PageModel;

public class AdministratorManagerImpl extends CommonManager implements
		AdministratorManager {

	public void addAdministrator(Administrator admin) {
		getHibernateTemplate().save(admin);
	}

	public boolean deleteAdministrator(int id) {
		getSession().createQuery("delete from Administrator where id =?").setParameter(0, id).executeUpdate();
		return false;
	}

	public PageModel searchAdministratorsAll() {
		PageModel pg = searchPaginated("from Administrator");
		//List<Administrator> administrators =(List<Administrator>) getSession().createQuery("from Administrator").list();
		return pg;
	}

	public void updateAdministrator(Administrator admin, int id) {
		Administrator administrator = (Administrator) getHibernateTemplate().load(Administrator.class, id);
		administrator.setName(admin.getName());
		administrator.setPassword(admin.getPassword());
		getHibernateTemplate().update(administrator);
	
	}
	public Administrator findAdministratorById(int id){
		Administrator admin = (Administrator) getSession().createQuery("from Administrator where id=?")
		.setParameter(0, id).uniqueResult();
	//	getHibernateTemplate().load(Administrator.class, id);
		return admin;// (Administrator) getHibernateTemplate().load(Administrator.class, id);
	}
	
	public boolean findAdministrator(Administrator admin) {
		int id = (Integer)getSession().createQuery("select id from Administrator where name=? and password = ?")
										.setParameter(0, admin.getName())		
										.setParameter(1, admin.getPassword()).uniqueResult();
		if(id>0)
			return true;
		return false;
	}

	public List searchRolesByAdministratorId(int id) {
		return getSession().createQuery("from AdministratorRoles").list();
	}
	public void addRole4Administrator(int id) {
		
		
	}

	public void addRole4Administrator(int[] ids) {
	
		
		
	}

	public void addRole4Administrator(int[] roleIds, int administratorId) {
		
	//	List<AdministratorRoles> administratorRoles = new ArrayList<AdministratorRoles>(roleIds.length);
		int i ; 
		for(i=0;i<roleIds.length;i++){
			AdministratorRoles administratorRole = new AdministratorRoles();
			administratorRole.setAdministrator(new Administrator(administratorId));
			administratorRole.setRole(new Role(roleIds[i]));
			List list = getSession().createQuery("from AdministratorRoles where administrator.id=? and role.id=?")
			.setParameter(0, administratorId)
			.setParameter(1, roleIds[i]).list();
			if(list==null || list.size()==0)
			getHibernateTemplate().save(administratorRole);
		}
		

	}
	public void deleteAdministratorRole(int administratorId,int roleId) {
		getSession().createQuery("delete from AdministratorRoles where administrator.id=? and role.id=?")
		.setParameter(0, administratorId)
		.setParameter(1, roleId).executeUpdate();
	}
}
