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

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

public class UserValidateFilter implements Filter {

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		System.out.println(req.getServletPath());
		HttpServletResponse res = (HttpServletResponse) response;
		if (WebUtil.getUserLogin(req) == false) {
			String url = (String) request.getParameter("directUrl");

			if (url == null||url.trim().length()==0) {
				url = Base64.encode(req.getServletPath().getBytes());
			}
			String path = req.getContextPath();
			String basePath = req.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

			res.sendRedirect(basePath+"user/userLogin.do?directUrl=" + url);
			return;
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

}
