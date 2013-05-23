package com.hnfealean.sport.managers.global;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import com.hnfealean.sport.managers.log.SystemLogManager;

@Aspect
public class MyInterceptor {
	private SystemLogManager systemLogManager;

	public void setSystemLogManager(SystemLogManager systemLogManager) {
		this.systemLogManager = systemLogManager;
	}

	// execution(*
	// com.hnfealean.sport.web.actions.article.FrontArticleAction.execute(..))
	// ("execution(* com.hnfealean.sport.web.actions.*.*.*(..))")
	// @Pointcut("execution(* org.apache.struts.actions.Action.execute(..))")
	@Pointcut("execution(* org.apache.struts.action.Action.execute(..))")
	public void anyMethod() {
	};

	@Before("anyMethod()&& args(mapping,form,request,response)")
	public void doAccessCheck(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		/*Enumeration headNames = request.getHeaderNames();
		while (headNames.hasMoreElements()) {
			String n = (String) headNames.nextElement();
			System.out.println(n + ": " + request.getHeaders(n).nextElement());
		}
		System.out.println(request.getRequestURI());
		System.out.println(request.getRequestURL());
		System.out.println(request.getServletPath());
		System.out.println(request.getQueryString());*/
		System.out.println("可用内存" + Runtime.getRuntime().freeMemory() / 1024
				/ 1024.0 + "M" + "\t系统总内存："
				+ Runtime.getRuntime().maxMemory() / 1024 / 1024.0 + "M" +

				"\t已使用的内存：" + Runtime.getRuntime().totalMemory() / 1024
				/ 1024.0 + "M");
	}

}
