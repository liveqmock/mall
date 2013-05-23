package com.hnfealean.sport.web.actions.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.hnfealean.sport.managers.user_acl_module.AdministratorManager;
import com.hnfealean.sport.managers.user_acl_module.RoleManager;
import com.hnfealean.sport.model.user_acl_module.Administrator;
import com.hnfealean.sport.pageset.PageModel;
import com.hnfealean.sport.web.MD5;
import com.hnfealean.sport.web.SystemException;
import com.hnfealean.sport.web.forms.user.AdministratorForm;

public class AdministratorAction extends DispatchAction {
	private RoleManager roleManager;
	public void setRoleManager(RoleManager roleManager) {
		this.roleManager = roleManager;
	}

	private AdministratorManager administratorManager;
	public void setAdministratorManager(AdministratorManager administratorManager) {
		this.administratorManager = administratorManager;
	}
	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PageModel pm = administratorManager.searchAdministratorsAll();
		request.setAttribute("pm", pm);
		return mapping.findForward("index");
	}
	public ActionForward addInput(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("input");
	}
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		AdministratorForm af = (AdministratorForm) form;
		if(af.getName()== null || af.getName().trim().length()==0 ||af.getPassword()==null || af.getPassword().trim().length()==0){
			//return mapping.findForward("index");
			throw new SystemException("请勿提交非法数据！");
		}	
		Administrator admin = new Administrator();
		admin.setName(af.getName());
		admin.setPassword(MD5.MD5Encode(af.getPassword().trim()));
		administratorManager.addAdministrator(admin);
		request.setAttribute("message", "添加管理员成功!");
		request.setAttribute("urladdress", "control/center/administrator/manage.do");
		return mapping.findForward("success");
	}	
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		AdministratorForm af = (AdministratorForm) form;
		if(af.getId()!=0){
			administratorManager.deleteAdministrator(af.getId());
			request.setAttribute("message", "删除管理员成功!");
			request.setAttribute("urladdress", "control/center/administrator/manage.do");
			return mapping.findForward("success");
		}else{
			return mapping.findForward("wrong");
		}
	}
	public ActionForward updateInput(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		AdministratorForm af = (AdministratorForm) form;
		if(af.getId()!=0){
			
		Administrator admin = administratorManager.findAdministratorById(af.getId());
		//admin.setPassword(MD5.MD5Encode(admin.getPassword()));
		request.setAttribute("administrator", admin);
		}
		return mapping.findForward("updateInput");
	}
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		AdministratorForm af = (AdministratorForm) form;
		Administrator admin = new Administrator();
		admin.setName(af.getName());
		admin.setPassword(MD5.MD5Encode(af.getPassword()));
		if(administratorManager.findAdministrator(admin)){
			admin.setPassword(MD5.MD5Encode(af.getNewPassword()));
			administratorManager.updateAdministrator(admin, af.getId());
			request.setAttribute("message", "更新管理员成功!");
			request.setAttribute("urladdress", "control/center/administrator/manage.do");
			return mapping.findForward("success");
		}
		return null;
		}
		public ActionForward roleManager(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			AdministratorForm af = (AdministratorForm) form;
			if(af.getId()==0)
				throw new SystemException("非法参数！");
			List list = administratorManager.searchRolesByAdministratorId(af.getId());
			request.setAttribute("roles", list);
			request.setAttribute("administrator", administratorManager.findAdministratorById(af.getId()));
			return mapping.findForward("administratorRolesList");
		}
		public ActionForward addRole4AdministratorInput(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			AdministratorForm af = (AdministratorForm) form;
			if(af.getId()==0)
				throw new SystemException("非法参数！");
			List list = roleManager.searchAllRoles();
			request.setAttribute("roles", list);
			request.setAttribute("administrator", administratorManager.findAdministratorById(af.getId()));
			return mapping.findForward("addRoles4AdministratorInput");
		}
		public ActionForward addRole4Administrator(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			AdministratorForm af = (AdministratorForm) form;
			if(af.getId()==0)
				throw new SystemException("非法参数！");
			int[] roleIds = af.getRolesId();
			
			administratorManager.addRole4Administrator(roleIds, af.getId());
			request.setAttribute("message", "管理员角色更新成功!");
			request.setAttribute("urladdress", "control/center/administrator/manage.do?method=roleManager&id="+af.getId());
			return mapping.findForward("success");
		}
		public ActionForward deleteAdministratorRole(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			AdministratorForm af = (AdministratorForm) form;
			if(af.getRoleId()==0 || af.getId()==0)
				throw new SystemException("非法参数！");
			administratorManager.deleteAdministratorRole(af.getId(), af.getRoleId());
			request.setAttribute("message", "管理员角色删除成功!");
			request.setAttribute("urladdress", "control/center/administrator/manage.do?method=roleManager&id="+af.getId());
			return mapping.findForward("success");
		}
}
