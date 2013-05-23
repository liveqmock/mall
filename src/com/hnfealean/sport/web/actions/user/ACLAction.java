package com.hnfealean.sport.web.actions.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.hnfealean.sport.managers.user_acl_module.ACLManager;
import com.hnfealean.sport.managers.user_acl_module.ModuleManager;
import com.hnfealean.sport.model.user_acl_module.ACL;
import com.hnfealean.sport.model.user_acl_module.Module;
import com.hnfealean.sport.model.user_acl_module.ModuleFunction;
import com.hnfealean.sport.web.SystemException;
import com.hnfealean.sport.web.forms.user.ACLForm;

public class ACLAction extends DispatchAction {

	private ACLManager myAclManager ;
	

	private ModuleManager moduleManager;

	public void setMyAclManager(ACLManager myAclManager) {
		this.myAclManager = myAclManager;
	}

	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ACLForm af =(ACLForm) form;
		if(af.getRoleId()==0)	throw new SystemException("非法访问");
		List<ACL> aclItems = myAclManager.searchAllACLItems(af.getRoleId());
		request.setAttribute("items", aclItems);
		return mapping.findForward("index");
	}
	public void setModuleManager(ModuleManager moduleManager) {
		this.moduleManager = moduleManager;
	}
	public ActionForward aclList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ACLForm af = (ACLForm) form;
		if(af.getRoleId()==0)	throw new SystemException("非法访问");	
		request.setAttribute("modules",moduleManager.searchTopModules());
		request.setAttribute("acls", myAclManager.searchAllACLItems(af.getRoleId()));
		return mapping.findForward("acllisttest");
	}
	public ActionForward roleAclList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ACLForm af = (ACLForm) form;
		if(af.getRoleId()==0)	throw new SystemException("非法访问");
		//af.getAdministratorId();
		//af.getRoleId();
	//	if(af.getPrincipalSn()==0 || !(af.getPrincipalType().equals("Role") ||af.getPrincipalType().equals("Administrator"))){
	//		throw new SystemException("非法参数！");
	//	}
		//request.setAttribute("principalSn", af.getPrincipalSn());
		//request.setAttribute("principalType", af.getPrincipalType());
		List aclItems = myAclManager.searchAllACLItems(af.getRoleId());
		request.setAttribute("items", aclItems);
		//List modules = moduleManager.searchTopModules();
		//request.setAttribute("modules", modules);
		return mapping.findForward("index");
	}
	
	public ActionForward setACL(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ACLForm af = (ACLForm) form;
		if(af.getRoleId()==0||af.getModuleId()==0)throw new SystemException("非法访问");
		if(af.getModuleId()==0&&af.getModuleFunctionId()==0){
			throw new SystemException("非法访问");
		}
//		Module module =moduleManager.findModuleById(af.getModuleId());
//		if(module==null){
//			throw new SystemException("非法访问");
//		}
		if(af.getModuleFunctionId()==0){
			Module module = moduleManager.findModuleById(af.getModuleId());
			
			if(module==null||module.getId()!=af.getModuleId()||module.getUrl()==null||module.getUrl().trim().length()==0)throw new SystemException("非法访问");
			
			ACL acl = new ACL();
			acl.setUrl(module.getUrl());
			acl.setModuleName(module.getName());
			acl.setRoleId(af.getRoleId());
			acl.setPermission(af.getPermission());
			
			myAclManager.addOrUpdatePermission(acl);
			response.reset();
			response.getWriter().write("true");
			response.getWriter().flush();
			return null;
		}
		ModuleFunction function = moduleManager.getModuleFunctionById(af.getModuleFunctionId());
		Module module = null;
		if(function!=null){
			module = function.getModule();
			if(module==null||module.getId()!=af.getModuleId()||module==null||module.getUrl()==null||module.getUrl().trim().length()==0)throw new SystemException("非法访问");
	}
		ACL acl = new ACL();
		acl.setUrl(module.getUrl());
		acl.setRoleId(af.getRoleId());
		acl.setPermission(af.getPermission());
		acl.setModuleName(module.getName());
		acl.setFunctionName(function.getFunctionName());
		acl.setFunctionDescription(function.getFunctionDescription());
		myAclManager.addOrUpdatePermission(acl);
		response.reset();
		response.getWriter().write("true");
		response.getWriter().flush();
		return null;
	}
	public ActionForward addOrUpdateACL(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ACLForm af = (ACLForm) form;
//		int[] aclState = af.getAclState();
//		int[] moduleId = af.getModuleId();
//		String principalType= af.getPrincipalType();
//		int principalSn = af.getPrincipalSn();
	//	myAclManager.addOrUpdateACL(moduleId, aclState, principalType, principalSn);
		roleAclList(mapping, form, request, response);
		return mapping.findForward("index");
	}
	public ActionForward administratorAclList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ACLForm af = (ACLForm) form;
		//af.getAdministratorId();
		//af.getRoleId();
//		if(af.getPrincipalSn()==0 || !(af.getPrincipalType().equals("Role") ||af.getPrincipalType().equals("Administrator"))){
//			throw new SystemException("非法参数！");
//		}
//		request.setAttribute("principalSn", af.getPrincipalSn());
//		request.setAttribute("principalType", af.getPrincipalType());
	//	List aclItems = myAclManager.searchAclRecord(af.getPrincipalType(), af.getPrincipalSn());
	//	request.setAttribute("items", aclItems);
		List modules = moduleManager.searchTopModules();
		request.setAttribute("modules", modules);
		return mapping.findForward("index");
	}
	public ActionForward initialAcl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ACLForm af = (ACLForm) form;
		if(af.getRoleId()>0){
			myAclManager.addInitialAcl(af.getRoleId());	
		}
		return mapping.findForward("index");
	}
	public void initialAcl(int roleId){
		myAclManager.addInitialAcl(roleId);	
	}
	public ActionForward updateAcl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ACLForm af = (ACLForm) form;
		if(af.getAclId()==0)throw new SystemException("非法参数！");
		myAclManager.updateAclById(af.getAclId(), af.getPermission());
		return null;
	}
}
