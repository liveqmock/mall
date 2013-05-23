package com.hnfealean.sport.managers.impl.user_acl_module;

import java.util.List;

import com.hnfealean.sport.managers.impl.product.CommonManager;
import com.hnfealean.sport.managers.user_acl_module.ACLManager;
import com.hnfealean.sport.model.user_acl_module.ACL;
import com.hnfealean.sport.model.user_acl_module.Module;
import com.hnfealean.sport.model.user_acl_module.ModuleFunction;
import com.hnfealean.sport.model.user_acl_module.Role;
import com.hnfealean.sport.pageset.PageModel;

public class ACLManagerImpl extends CommonManager implements ACLManager {
	public boolean addACL(int roleId, String moduleUrl,
			String functionName, boolean permission) {
		
		ACL acl = new ACL();
		acl.setFunctionName(functionName);
		acl.setPermission(permission);
		acl.setRoleId(roleId);
		acl.setUrl(moduleUrl);
		getHibernateTemplate().save(acl);
		return true;
	}
	
	public void deleteInvalidAcl(String moduleUrl, String moduleFunctionName) {
		getSession().createQuery("delete from ACL where url=? and functionName=?")
		.setParameter(0, moduleUrl)
		.setParameter(1, moduleFunctionName).executeUpdate();
		
	}
	public void updateAclForModuleInfoChanged(Module module,ModuleFunction moduleFunction) {
		getSession().createQuery("update ACL set url=?,moduleName=?,functionName=?,functionDescription=? where url=? and functionName=?")
		.setParameter(0, module.getUrl())
		.setParameter(1, module.getName())
		.setParameter(2, moduleFunction.getFunctionName())
		.setParameter(3, moduleFunction.getFunctionDescription())
		.setParameter(4, module.getUrl())
		.setParameter(5, moduleFunction.getFunctionName())
		.executeUpdate();
	}
	public boolean addACL(ACL acl){
		getHibernateTemplate().save(acl);
		
		return true;
		
	}
	public void addOrUpdatePermission(int roleId, String moduleUrl,
			String functionName, boolean permission) {
		
		
	}

	public void delPermission(int roleId, String moduleUrl,
			String functionName, boolean permission) {
		// TODO Auto-generated method stub
		
	}

	public boolean hasPermission(int roleId, String moduleUrl,
			String functionName) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean hasPermission( 
			String moduleUrl, String functionName,int administratorId) {
		List<Role> roles = (List<Role>)getSession().createQuery("select administrators.role from Administrators administrators where administrators.administrator.id=?")
					.setParameter(0, administratorId).list();
		if(roles==null||roles.size()==0){
			return false;
		}
		for(Role role:roles){
			Boolean yes = (Boolean)getSession().createQuery("select permission from ACL where roleId=? and url=? and functionName=?")
						.setParameter(0, role.getId())
						.setParameter(1, moduleUrl)
						.setParameter(2, functionName)
						.uniqueResult();
			if(yes==null) continue;
			if(yes==true)	return true;
		}
		return false;
	}

	public List<ACL> searchAllACLItems() {
		// TODO Auto-generated method stub
		return null;
	}

	public List searchModules(int administratorId) {
		// TODO Auto-generated method stub
		return null;
	}
	public boolean hasPermission(int administratorId, int roleId,
			String moduleUrl, String functionName) {
		
		return false;
	}
	public List<ACL> searchAllACLItems(int roleId) {
		List<ACL> acls =(List<ACL>)	getSession().createQuery("from ACL where roleId=? order by url")
		.setParameter(0, roleId).list();
		return acls;
	}
	public PageModel getAllACLItemsByRoleId(int roleId) {
		return searchPaginated("from ACL where roleId=?", roleId);
		
	}
	public void addInitialAcl(int roleId){
		List<ModuleFunction> functions = (List<ModuleFunction>)getSession().createQuery("from ModuleFunction").list();
		for(ModuleFunction function:functions){
			Module module = function.getModule();
			Long count = (Long)getSession().createQuery("select count(id) from ACL where roleId=? and url=? and functionName=?")
				.setParameter(0, roleId)
				.setParameter(1, module.getUrl().trim())
				.setParameter(2, function.getFunctionName().trim())
				.uniqueResult();
			if(count==null||count==0){
				ACL acl = new ACL();
				acl.setUrl(module.getUrl().trim());
				acl.setPermission(false);
				acl.setRoleId(roleId);
				acl.setFunctionName(function.getFunctionName().trim());
				getHibernateTemplate().save(acl);				
			}
			
		}
	}
	public boolean updateAclById(int aclId, boolean permission) {
		getSession().createQuery("update ACL set permission=? where id=?")
		.setParameter(0, permission)
		.setParameter(1, aclId)
		.executeUpdate();
		return true;
	}

	public void addOrUpdatePermission(int roleId, String moduleUrl,
			String moduleName, String functionName, String functionDescription,
			boolean permission) {
		if(checkACLExist(roleId, moduleUrl, functionName)){
			getSession().createQuery(
		"update ACL set moduleName=? and functionDescription=? and permission=? where roleId=? and url=? and functionName=?")
		.setParameter(0, moduleName)
		.setParameter(1, functionDescription)
		.setParameter(2, permission)
		.setParameter(3, roleId)
		.setParameter(4, moduleUrl)
		.setParameter(5, functionName)
		.executeUpdate();
			return;
		}
		ACL acl = new ACL();
		acl.setFunctionDescription(functionDescription);
		acl.setFunctionName(functionName);
		acl.setModuleName(moduleName);
		acl.setPermission(permission);
		acl.setRoleId(roleId);
		acl.setUrl(moduleUrl);
		getHibernateTemplate().save(acl);
	}

	private boolean checkACLExist(int roleId, String moduleUrl,String functionName){
		if(functionName==null){
			List l = getSession().createQuery("select id from ACL where roleId=? and url=? and functionName is null")
			.setParameter(0, roleId)
			.setParameter(1, moduleUrl)
			.list();
			if(l.size()>0){
				return true;
			}
			return false;
		}
		List l = getSession().createQuery("select id from ACL where roleId=? and url=? and functionName=?")
		.setParameter(0, roleId)
		.setParameter(1, moduleUrl)
		.setParameter(2, functionName)
		.list();
		if(l.size()>0){
			return true;
		}
		return false;
		
	}

	public void addOrUpdatePermission(ACL acl) {
		if(checkACLExist(acl.getRoleId(), acl.getUrl(), acl.getFunctionName())){
			getSession().createQuery(
		"update ACL set moduleName=? ,functionDescription=? ,permission=? where roleId=? and url=? and functionName=?")
		.setParameter(0, acl.getModuleName())
		.setParameter(1, acl.getFunctionDescription())
		.setParameter(2, acl.isPermission())
		.setParameter(3, acl.getRoleId())
		.setParameter(4, acl.getUrl())
		.setParameter(5, acl.getFunctionName())
		.executeUpdate();
			return;
		}
		getHibernateTemplate().save(acl);
		
	}
}
