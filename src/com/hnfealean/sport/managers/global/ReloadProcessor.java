package com.hnfealean.sport.managers.global;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.struts.DelegatingRequestProcessor;

import com.hnfealean.sport.web.JdbcUtils;
import com.hnfealean.sport.web.WebUtil;
public class ReloadProcessor extends DelegatingRequestProcessor {
	@Override
	protected ActionForward processActionPerform(HttpServletRequest request,
			HttpServletResponse response, Action action, ActionForm form,
			ActionMapping mapping) throws IOException, ServletException {
	
	
		//String module = mapping.getPath();//取得当前的请求路径，如	/control/product/category/manage，
		//Method currentMethod = 	getCurrentMethod(request, action, mapping);
		
		String parameter = mapping.getParameter();
	   if (parameter == null) { // 非DispatchAction, 无需检查权限
		   return super.processActionPerform(request, response, action, form, mapping);
	   }
	   String functionName = request.getParameter("method")==null?"unspecified":request.getParameter("method");
	   
//		String methodName = request.getParameter(parameter);//取得dispatchAction的method的值，如add,addInput,del等
//		int permission = -1;
//		if (methodName == null) { // unspecified()方法
//			methodName= "unspecified";
//			permission = Permission.READ;
//		} else if (methodName.startsWith("add")) {
//		   permission = Permission.CREATE;
//		} else if (methodName.startsWith("update")) {
//		   permission = Permission.UPDATE;
//		} else if (methodName.startsWith("del")) {
//		   permission = Permission.DELETE;
//		} else {
//			permission = Permission.READ;
//		}
		
		 String moduleUrl = request.getServletPath();
		 if(!moduleUrl.startsWith("/control")){
				return super.processActionPerform(request, response, action, form, mapping);
		 }
		//取得管理员ID
		int administratorId = WebUtil.getAdministrator(request).getId();
		request.setAttribute("moduleUrl", moduleUrl);
		//request.setAttribute("permission", permission);
		request.setAttribute("administratorId", administratorId);
		//System.out.println(myAclManager.checkPermission(moduleUrl, permission, administratorId));
		//if(JdbcUtils.checkSecurity(moduleUrl, permission, administratorId)==false){
			if(JdbcUtils.checkSecurity(moduleUrl, functionName, administratorId)==false){
			//if(myAclManager.checkPermission(moduleUrl, permission, administratorId)==false){
			request.setAttribute("message", "You don't have permission to access!");
			request.setAttribute("directUrl", moduleUrl);
			return mapping.findForward("noPermission");
		}

		
		return super.processActionPerform(request, response, action, form, mapping);

	}
	
}

