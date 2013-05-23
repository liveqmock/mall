package com.hnfealean.sport.web.actions.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.hnfealean.sport.managers.user_acl_module.UserLoginManager;
import com.hnfealean.sport.model.user_acl_module.User;
import com.hnfealean.sport.web.WebUtil;
import com.hnfealean.sport.web.forms.user.UserRegForm;

public class UserLoginAction extends DispatchAction {

	private UserLoginManager userLoginManager;

	public void setUserLoginManager(UserLoginManager userLoginManager) {
		this.userLoginManager = userLoginManager;
	}
	private Boolean checkExists(String email,String password){
		
		if(password==null|| email==null|| password.trim().length()==0|| email.trim().length()==0){
			return false;
			//throw new SystemException("您的输入不正确!");
		}
		return true;
	}
	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
		String url = (String) request.getParameter("directUrl");
		
		if(url!=null && url.length()>0){
			request.setAttribute("directUrl",url);
		}
		return mapping.findForward("index");
	}
	
	public ActionForward login(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		UserRegForm urg= (UserRegForm) form;
		String password = urg.getPassword();
		String email = urg.getEmail();
		if(checkExists(email,password)==false){
			request.setAttribute("message", "您的输入不正确,请重试!");
			return mapping.findForward("index");
		}
		User user = userLoginManager.Login(email, password);
		if(user !=null)	{
		int userId = user.getId();
		if(userId>0){
			request.getSession().setAttribute("useremail", user.getEmail());
			request.getSession().setAttribute("username", user.getUsername());
			request.getSession().setAttribute("userId", userId);
			String url = (String) request.getParameter("directUrl");
			
			if(url!=null && url.length()>0){
				url= WebUtil.decodeStringByBase64(url);
				request.setAttribute("directUrl",url);
				//request.getRequestDispatcher(url).forward(request, response);
			//	ActionForward forward = new ActionForward("/"+url);
			//	System.out.println(request.getServerName());
						
				response.sendRedirect(request.getContextPath()  + url);
				return null;
			}
		

			request.setAttribute("directUrl", "user/center.do");
			return mapping.findForward("redirectTo");
			//return mapping.findForward("success");
		}
		}
		request.setAttribute("message", "用户名或密码不正确,请重试!");
		return mapping.findForward("index");
		
	}	
}
