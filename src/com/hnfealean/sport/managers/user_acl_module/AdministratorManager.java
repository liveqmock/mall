package com.hnfealean.sport.managers.user_acl_module;

import java.util.List;

import com.hnfealean.sport.model.user_acl_module.Administrator;
import com.hnfealean.sport.pageset.PageModel;

public interface AdministratorManager {
	
	public void addAdministrator(Administrator admin);
	
	public PageModel searchAdministratorsAll();
	
	public boolean deleteAdministrator(int id);
	
	public void updateAdministrator(Administrator admin,int id);
	
	public Administrator findAdministratorById(int id);
	public boolean findAdministrator(Administrator admin) ;
	public List searchRolesByAdministratorId(int id) ;
	public void addRole4Administrator(int id);
	public void addRole4Administrator(int[] ids);
	public void addRole4Administrator(int[] roleIds,int administratorId);
	public void deleteAdministratorRole(int administratorId,int roleId);
}
