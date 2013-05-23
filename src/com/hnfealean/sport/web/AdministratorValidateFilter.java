package com.hnfealean.sport.web;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdministratorValidateFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
			HttpServletRequest req = (HttpServletRequest) request;
			HttpServletResponse res = (HttpServletResponse) response;
			if(WebUtil.getAdministratorLogin(req)==false){
				String url =(String) request.getParameter("directUrl");
				if(url==null ){
					url="";
				}
				String path = req.getContextPath();
				String basePath = req.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

				res.sendRedirect(basePath+"manage.do");
				return;
			}
			chain.doFilter(request,response);		
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
