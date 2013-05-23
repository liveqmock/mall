package com.hnfealean.sport.managers.user_acl_module;

import java.util.List;

import com.hnfealean.sport.model.user_acl_module.ACL;
import com.hnfealean.sport.model.user_acl_module.Module;
import com.hnfealean.sport.model.user_acl_module.ModuleFunction;


public interface ACLManager {
	public boolean updateAclById(int aclId,boolean permission);
	public void addInitialAcl(int roleId);
	public List<ACL> searchAllACLItems(int roleId);
	public void addOrUpdatePermission(ACL acl);
	
	/**删除无效acl记录
	 */
	public void deleteInvalidAcl(String moduleUrl,String moduleFunctionName);
	public void updateAclForModuleInfoChanged(Module module,ModuleFunction moduleFunction);
	public void addOrUpdatePermission(int roleId,
			String moduleUrl,String moduleName,String functionName,String functionDescription,
			boolean permission);
	/**
	 * 添加或更新授权
	 * 
	 * 
	 */
	public void addOrUpdatePermission(int roleId,
			String moduleUrl,String functionName,
			boolean permission);
	
	/**
	 * 删除授权
	 * @param principalType
	 * @param principalId
	 * @param moduleId
	 */
	public void delPermission(int roleId,
			String moduleUrl,String functionName,
			boolean permission);
	
	/**
	 * 即时认证，判断某个管理员是否拥有对某个模块的某个操作的权限
	 * @param administratorId 管理员标识
	 * @param moduleId 模块标识
	 * @param permission 操作标识（C/R/U/D）
	 * @return 允许或不允许
	 */
	public boolean hasPermission(int roleId,String moduleUrl,String functionName);
	public boolean hasPermission(int administratorId,int roleId,String moduleUrl,String functionName);
	/**
	 * 查询管理员拥有读取权限的模块列表
	 * @param administratorId 管理员标识
	 * @return 列表元素是Module对象
	 */
	public List searchModules(int administratorId);
	
	//public List searchAclRecord(String principalType,int principalId);
	//public boolean addOrUpdateACL(int[] moduleId,int[] aclState,String principalType,int principalSn);
	//public boolean checkPermission(String moduleUrl,int permission,int administratorId);

}
