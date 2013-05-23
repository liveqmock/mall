package com.hnfealean.sport.managers.user_acl_module;

import java.util.List;

import com.hnfealean.sport.model.user_acl_module.Role;

public interface RoleManager {
	public List<Role> searchAllRoles();
	
	public void addRole(Role role);
	
	public Role findRole(int id);
	
	public void updateRole(Role role,int id);
	
	public void deleteRole(int id);

}
