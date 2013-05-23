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

public class NullShoppingCartCheck implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		if(WebUtil.getShoppingCart(req)==false){
			//res.sendRedirect("/user/userLogin.do");
			req.setAttribute("message", "购物车中没有商品,请添加商品后再执行该操作！");
			String path = req.getContextPath();
			String basePath = req.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

			req.setAttribute("urladdress", basePath);
			//req.getRequestDispatcher("/WEB-INF/page/share/message.jsp").forward(request, response);
			req.getRequestDispatcher("/WEB-INF/templates/default/share/message.jsp").forward(request, response);
			return;
		}
		
		//WEB-INF/page/share/message.jsp
		chain.doFilter(request,response);
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
