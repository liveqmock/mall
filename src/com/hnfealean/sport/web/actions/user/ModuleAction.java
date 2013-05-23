package com.hnfealean.sport.web.actions.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.hnfealean.sport.managers.user_acl_module.ACLManager;
import com.hnfealean.sport.managers.user_acl_module.ModuleManager;
import com.hnfealean.sport.model.user_acl_module.Module;
import com.hnfealean.sport.model.user_acl_module.ModuleFunction;
import com.hnfealean.sport.pageset.PageModel;
import com.hnfealean.sport.web.SystemException;
import com.hnfealean.sport.web.forms.user.ModuleForm;

public class ModuleAction extends DispatchAction {

	private ModuleManager moduleManager;
	private ACLManager myAclManager;
	

	public void setMyAclManager(ACLManager myAclManager) {
		this.myAclManager = myAclManager;
	}

	public void setModuleManager(ModuleManager moduleManager) {
		this.moduleManager = moduleManager;
	}

	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ModuleForm mf = (ModuleForm) form;	
		PageModel pm = new PageModel();
		if(mf.getId()==0){
		pm = moduleManager.searchChildModules(mf.getParentId());
		}else{
			pm= moduleManager.searchModulesByChildId(mf.getId());
		}
		request.setAttribute("pm", pm);
		request.setAttribute("parentId", mf.getParentId());
		return mapping.findForward("index");
	}
	public ActionForward addInput(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ModuleForm mf = (ModuleForm) form;	
		request.setAttribute("parentId", mf.getParentId());
		return mapping.findForward("addInput");
	}
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ModuleForm mf = (ModuleForm) form;	
		
		Module module = new Module();
		module.setName(mf.getName());
		module.setUrl(mf.getUrl());
		module.setParent(moduleManager.findModuleById(mf.getParentId()));
		moduleManager.addModule(module);
		request.setAttribute("message", "添加模块成功!");
		request.setAttribute("urladdress", "control/center/module/manage.do?parentId="+ mf.getParentId());
		return mapping.findForward("success");
	}
	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ModuleForm mf = (ModuleForm) form;	
		if(mf.getId()==0){
			throw new SystemException("非法参数！");
		}
		Module module = moduleManager.findModuleById(mf.getId());
		request.setAttribute("module", module);
		return mapping.findForward("updateInput");
	}
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ModuleForm mf = (ModuleForm) form;	
		if(mf.getId()==0){
			throw new SystemException("非法参数！");
		}
		
		Module module = new Module();
		module.setName(mf.getName());
		module.setUrl(mf.getUrl());
		moduleManager.updateModule(module, mf.getId());
		request.setAttribute("module", module);
		request.setAttribute("message", "更新模块成功!");
		request.setAttribute("urladdress", "control/center/module/manage.do?parentId="+ mf.getParentId());
		return mapping.findForward("success");
	}	
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ModuleForm mf = (ModuleForm) form;	
		if(mf.getId()==0){
			throw new SystemException("非法参数！");
		}

		moduleManager.deleteModule(mf.getId());
		request.setAttribute("message", "删除模块成功!");
		request.setAttribute("urladdress", "control/center/module/manage.do?parentId="+ mf.getParentId());
		return mapping.findForward("success");
	}	
	
	public ActionForward addFunctionInput(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ModuleForm mf  = (ModuleForm) form;
		if(mf.getId()==0)	throw new SystemException("非法访问");
		request.setAttribute("moduleId",mf.getId());
		return mapping.findForward("addFunctionInput");
	}
	public ActionForward addFunction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ModuleForm mf  = (ModuleForm) form;
		if(mf.getId()==0||mf.getFunctionName()==null||mf.getFunctionName().trim().length()==0
				||mf.getModuleFunctionDescription()==null||mf.getModuleFunctionDescription().trim().length()==0
		)
			throw new SystemException("非法访问");
		ModuleFunction function = new ModuleFunction();
		function.setFunctionName(mf.getFunctionName());
		function.setFunctionDescription(mf.getModuleFunctionDescription());
		function.setModule(new Module(mf.getId()));
		moduleManager.addModuleFunction(function);
		request.setAttribute("message", "添加模块方法成功!");
		request.setAttribute("urladdress", "control/center/module/manage.do?parentId="+ mf.getParentId());
		return mapping.findForward("success");
	}
	public ActionForward editModuleFunction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ModuleForm mf = (ModuleForm) form;	
		if(mf.getId()==0){
			throw new SystemException("非法参数！");
		}
		ModuleFunction moduleFunction = moduleManager.getModuleFunctionById(mf.getId());
		request.setAttribute("moduleFunction", moduleFunction);
		return mapping.findForward("editModuleFuncton");
	}
	public ActionForward updateModuleFunction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ModuleForm mf = (ModuleForm) form;	
		if(mf.getId()==0){
			throw new SystemException("非法参数！");
		}
		
		ModuleFunction moduleFunction = moduleManager.getModuleFunctionById(mf.getId());
		if(moduleFunction==null||moduleFunction.getId()==0)	throw new SystemException("非法访问，找不到指定数据！");
		moduleFunction.setFunctionName(mf.getFunctionName());
		moduleFunction.setFunctionDescription(mf.getModuleFunctionDescription());
		moduleManager.updateModuleFunction(moduleFunction);
		Module module = moduleFunction.getModule();
		myAclManager.updateAclForModuleInfoChanged(module, moduleFunction);
		//module.getUrl()
		//moduleFunction.getFunctionName()
		request.setAttribute("message", "更新模块方法成功!");
		request.setAttribute("urladdress", "control/center/module/manage.do?parentId="+ mf.getParentId());
		return mapping.findForward("success");
	}	
	public ActionForward deleteModuleFunction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ModuleForm mf = (ModuleForm) form;	
		if(mf.getId()==0){
			throw new SystemException("非法参数！");
		}
		
		ModuleFunction moduleFunction = moduleManager.getModuleFunctionById(mf.getId());
		if(moduleFunction==null||moduleFunction.getId()==0)	throw new SystemException("非法访问，找不到指定数据！");
		
		Module module = moduleFunction.getModule();
		String moduleUrl = module.getUrl();
		String moduleFunctionName = moduleFunction.getFunctionName();
		moduleManager.deleteModuleFunction(moduleFunction.getId());
		myAclManager.deleteInvalidAcl(moduleUrl, moduleFunctionName);
		request.setAttribute("message", "删除模块方法成功!");
		
		//module.getUrl()
		//moduleFunction.getFunctionName()
		request.setAttribute("urladdress", "control/center/module/manage.do?parentId="+ mf.getParentId());
		return mapping.findForward("success");
	}	
}
