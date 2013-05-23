package com.hnfealean.sport.managers.impl;

import com.hnfealean.sport.managers.user_acl_module.ACLManager;

public class SecurityHandlerImpl{
	
	private ACLManager myAclManager;
	private void checkSecurity() {
		System.out.println("----------checkSecurity()---------------");
	}
	private void checkSecurity(String moduleUrl) {
		System.out.println("----------checkSecurity()---------------");
	}	
/*	public boolean checkSecurity(String moduleUrl,int permission,int administratorId){
		
		int moduleId = (Integer) getSession().createQuery("select id from Module where url=?").setParameter(0, moduleUrl).uniqueResult();
		//如果查找不到module，说明不在模块之中，直接放行
		if(moduleId==0)
			return true;
		//查找管理员的直接授权
		int directACL =(Integer)getSession().createQuery("select aclState from ACL where principalType=? and principalSn=? and resourceSn=?")
		.setParameter(0, "Administrator")
		.setParameter(1, administratorId)
		.setParameter(2, moduleId).uniqueResult();
		if(directACL!=0){
			if(permission==Permission.CREATE){
				//return (aclState%2==1?true:false);
				if(directACL%2==1) return true;
			}else if(permission==Permission.READ){
				//return (aclState/2%2==1?true:false);
				if(directACL/2%2==1) return true;
			}else if(permission==Permission.UPDATE){
				//return (aclState/4%2==1?true:false);
				if(directACL/4%2==1) return true;
			}else if(permission==Permission.DELETE){
				//return (aclState/8%2==1?true:false);
				if(directACL/8%2==1) return true;
			}	
		}
		//查找管理员的角色
		List<Role> roles =getSession().createQuery("select role from AdministratorRoles where administrator.id=?")
								.setParameter(0, administratorId).list();
		for(Role role:roles){
			//对管理员拥有的每个角色查找对该模块的权限
			int aclState = (Integer)getSession().createQuery("select aclState from ACL where principalType=? and principalSn=? and resourceSn=?")
						.setParameter(0, "Role")
						.setParameter(1, role.getId())
						.setParameter(2, moduleId).uniqueResult();
			if(aclState!=0){
				if(permission==Permission.CREATE){
					//return (aclState%2==1?true:false);
					if(aclState%2==1) return true;
				}else if(permission==Permission.READ){
					//return (aclState/2%2==1?true:false);
					if(aclState/2%2==1) return true;
				}else if(permission==Permission.UPDATE){
					//return (aclState/4%2==1?true:false);
					if(aclState/4%2==1) return true;
				}else if(permission==Permission.DELETE){
					//return (aclState/8%2==1?true:false);
					if(aclState/8%2==1) return true;
				}
			}
		}
		return false;
		
	}  */
	public void setMyAclManager(ACLManager myAclManager) {
		this.myAclManager = myAclManager;
	}
	public ACLManager getMyAclManager() {
		return myAclManager;
	}  	
}
