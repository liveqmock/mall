package com.hnfealean.sport.web.actions.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.hnfealean.sport.managers.user_acl_module.MenuManager;
import com.hnfealean.sport.model.user_acl_module.Administrator;
import com.hnfealean.sport.model.user_acl_module.MenuGroup;
import com.hnfealean.sport.model.user_acl_module.MenuItem;
import com.hnfealean.sport.web.SystemException;
import com.hnfealean.sport.web.forms.user.MenuForm;

public class MenuAction extends DispatchAction {

	private MenuManager menuManager;

	public void setMenuManager(MenuManager menuManager) {
		this.menuManager = menuManager;
	}

	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		MenuForm mf = (MenuForm) form;
		// Administrator admin = WebUtil.getAdministrator(request);
		// if(admin!=null&&admin.getId()>0){
		// request.setAttribute("menu", admin.getMenuGroups());
		// return mapping.findForward("index");
		// }
		request.setAttribute("menugroups", menuManager.getAllMenuGroups(mf
				.getId()));
		return mapping.findForward("index");
	}

	public ActionForward editItem(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		MenuForm mf = (MenuForm) form;
		if (mf == null || mf.getMenuItemId() == 0)
			throw new SystemException("非法访问!");
		// List<MenuItem> menuItems =
		// menuManager.getAllMenuItems(mf.getMenuItemId());
		request.setAttribute("item", menuManager
				.getMenuItem(mf.getMenuItemId()));
		return mapping.findForward("editItem");
	}

	public ActionForward deleteItem(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		MenuForm mf = (MenuForm) form;
		if (mf == null || mf.getMenuItemId() == 0)
			throw new SystemException("非法访问!");
		menuManager.deleteMenuItem(mf.getMenuItemId());
		request.setAttribute("message", "删除菜单项成功!");
		request.setAttribute("urladdress", "control/center/adminmenu/manage.do?id="+mf.getId());
		return mapping.findForward("success");
	}

	public ActionForward addItemInput(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		MenuForm mf = (MenuForm) form;
		if (mf == null || mf.getMenuGroupId() == 0)
			throw new SystemException("非法访问!");

		return mapping.findForward("addItemInput");
	}

	public ActionForward addItem(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		MenuForm mf = (MenuForm) form;
		if (mf == null || mf.getMenuGroupId() == 0 || mf.getName() == null
				|| mf.getName().length() == 0 || mf.getLink() == null
				|| mf.getLink().length() == 0)
			throw new SystemException("非法访问!");
		MenuGroup group = menuManager.getMenuGroup(mf.getMenuGroupId());
		if (group == null || group.getId() == 0)
			throw new SystemException("非法访问,找不到指定数据!");
		MenuItem item = new MenuItem();
		item.setName(mf.getName());
		item.setLink(mf.getLink());
		item.setOrderNo(mf.getOrderNo());
		item.setGroup(new MenuGroup(mf.getMenuGroupId()));
		menuManager.addMenuItem(item);
		request.setAttribute("message", "添加菜单项成功!");
		request.setAttribute("urladdress", "control/center/adminmenu/manage.do?id="+mf.getId());
		return mapping.findForward("success");
	}
	public ActionForward updateItem(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		MenuForm mf = (MenuForm) form;
		if (mf == null || mf.getMenuItemId() == 0||mf.getName()==null
				|| mf.getName().trim().length() == 0 || mf.getLink() == null
				|| mf.getLink().length() == 0)
			throw new SystemException("非法访问!"); 
		

		MenuItem item = menuManager.getMenuItem(mf.getMenuItemId());
		if (item == null || item.getId() == 0)
			throw new SystemException("非法访问,找不到指定数据!");
		item.setName(mf.getName().trim());
		item.setLink(mf.getLink());
		item.setOrderNo(mf.getOrderNo());
		menuManager.updateMenuItem(item);
		request.setAttribute("message", "更新菜单项成功!");
		request.setAttribute("urladdress", "control/center/adminmenu/manage.do?id="+mf.getId());
		return mapping.findForward("success");
	}
	public ActionForward editGroup(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		MenuForm mf = (MenuForm) form;
		if (mf == null || mf.getMenuGroupId() == 0)
			throw new SystemException("非法访问!");
		MenuGroup group = menuManager.getMenuGroup(mf.getMenuGroupId());
		if (group == null || group.getId() == 0)
			throw new SystemException("非法访问,找不到指定数据!");
		request.setAttribute("group", group);
		return mapping.findForward("editGroup");
	}

	public ActionForward deleteGroup(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		MenuForm mf = (MenuForm) form;
		if (mf == null || mf.getMenuGroupId() == 0)
			throw new SystemException("非法访问!");
		
		menuManager.deleteMenuGroup(mf.getMenuGroupId());
		request.setAttribute("message", "删除菜单组成功!");
		request.setAttribute("urladdress", "control/center/adminmenu/manage.do?id="+mf.getId());
		return mapping.findForward("success");
	}

	public ActionForward addGroupInput(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("addGroupInput");
	}
	public ActionForward addGroup(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		MenuForm mf = (MenuForm) form;
		if (mf == null||mf.getId()==0||mf.getName()==null||mf.getName().trim().length()==0)
			throw new SystemException("非法访问!");
		MenuGroup group = new MenuGroup();
		group.setName(mf.getName().trim());
		group.setOrderNo(mf.getOrderNo());
		group.setAdmin(new Administrator(mf.getId()));
		menuManager.addMenuGroup(group);
		request.setAttribute("message", "添加菜单组成功!");
		request.setAttribute("urladdress", "control/center/adminmenu/manage.do?id="+mf.getId());
		return mapping.findForward("success");
	}
	public ActionForward updateGroup(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		MenuForm mf = (MenuForm) form;
		if (mf == null||mf.getMenuGroupId()==0||mf.getName()==null||mf.getName().trim().length()==0)
			throw new SystemException("非法访问!");
		MenuGroup group = menuManager.getMenuGroup(mf.getMenuGroupId());
		if(group==null||group.getId()==0)throw new SystemException("非法访问,找不到指定数据!");
		group.setName(mf.getName().trim());
		group.setOrderNo(mf.getOrderNo());
		//group.setId(mf.getMenuGroupId());
		menuManager.updateMeuGroup(group);
		request.setAttribute("message", "更新菜单组成功!");
		request.setAttribute("urladdress", "control/center/adminmenu/manage.do?id="+mf.getId());
		return mapping.findForward("success");
	}
}
