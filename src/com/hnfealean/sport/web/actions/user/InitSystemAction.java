package com.hnfealean.sport.web.actions.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.hnfealean.sport.managers.user_acl_module.AdministratorInitManager;
import com.hnfealean.sport.web.SystemException;

public class InitSystemAction extends DispatchAction {
	private AdministratorInitManager initSystemManager;


	public void setInitSystemManager(AdministratorInitManager initSystemManager) {
		this.initSystemManager = initSystemManager;
	}


	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if(initSystemManager.getAdministrator()){//return true表示已初始化
			throw new SystemException("您的系统已经初始化，请勿重复提交初始化操作！");
		}
		initSystemManager.addAdministrator();
		throw new SystemException("您的系统初始化成功,初始管理员账号为：angel</br>angelinthebox");
		
		//return mapping.findForward("index");
	}
	
	

}
