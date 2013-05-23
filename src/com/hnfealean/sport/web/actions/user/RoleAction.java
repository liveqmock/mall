package com.hnfealean.sport.web.actions.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.hnfealean.sport.managers.user_acl_module.RoleManager;
import com.hnfealean.sport.model.user_acl_module.Role;
import com.hnfealean.sport.web.SystemException;
import com.hnfealean.sport.web.forms.user.RoleForm;

public class RoleAction extends DispatchAction {

	private RoleManager roleManager;
	public void setRoleManager(RoleManager roleManager) {
		this.roleManager = roleManager;
	}
	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		List<Role> roles = roleManager.searchAllRoles();
		request.setAttribute("roles", roles);
		return mapping.findForward("index");
	}
	public ActionForward addInput(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("addInput");
	}
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RoleForm rl = (RoleForm) form;
		Role role = new Role();
		role.setRoleName(rl.getRoleName());
		roleManager.addRole(role);
		request.setAttribute("message", "添加角色成功");
		request.setAttribute("urladdress", "control/center/role/manage.do");
		return mapping.findForward("success");
	}
	public ActionForward updateInput(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RoleForm rf = (RoleForm) form;
		if(rf.getId()==0)
			throw new SystemException("非法参数！");		
		Role role = roleManager.findRole(rf.getId());
		request.setAttribute("role", role);
		return mapping.findForward("updateInput");
	}
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RoleForm rf = (RoleForm) form;
		if(rf.getId()==0)
			throw new SystemException("非法参数！");
		Role role = new Role();
		role.setRoleName(rf.getRoleName());
		roleManager.updateRole(role, rf.getId());
		request.setAttribute("message", "更新角色成功");
		request.setAttribute("urladdress", "control/center/role/manage.do");
		return mapping.findForward("success");
	}
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RoleForm rl = (RoleForm) form;
		if(rl.getId()==0)
			throw new SystemException("非法参数！");
		roleManager.deleteRole(rl.getId());
		request.setAttribute("message", "删除角色成功");
		request.setAttribute("urladdress", "control/center/role/manage.do");
		return mapping.findForward("success");
	}
}
