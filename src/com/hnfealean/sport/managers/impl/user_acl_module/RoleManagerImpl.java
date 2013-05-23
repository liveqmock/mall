package com.hnfealean.sport.managers.impl.user_acl_module;

import java.util.List;

import com.hnfealean.sport.managers.impl.product.CommonManager;
import com.hnfealean.sport.managers.user_acl_module.RoleManager;
import com.hnfealean.sport.model.user_acl_module.Role;

public class RoleManagerImpl extends CommonManager implements RoleManager {

	public void addRole(Role role) {
		getHibernateTemplate().save(role);
		
	}

	public void deleteRole(int id) {
		getHibernateTemplate().delete((Role) getHibernateTemplate().load(Role.class, id));
		
	}

	public List<Role> searchAllRoles() {
	
		List<Role> roles = getSession().createQuery("from Role").list();
		return roles;
	}

	public void updateRole(Role role, int id) {
		Role preRole = (Role) getHibernateTemplate().load(Role.class, id);
		preRole.setRoleName(role.getRoleName());
		getHibernateTemplate().update(preRole);
	}

	public Role findRole(int id) {
		Role role = (Role) getSession().createQuery("from Role where id =?").setParameter(0, id).uniqueResult();
		return role;
	}
}
