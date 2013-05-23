package com.hnfealean.sport.pageset;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.app.Velocity;

import com.hnfealean.sport.enums.ConstantString;
import com.hnfealean.sport.web.WebProperty;
import com.hnfealean.sport.web.WebUtil;

public class PagerFilter implements Filter {

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
	//	System.out.println("filter done"+httpRequest.getRequestURI());

		String status304 = httpRequest.getHeader("If-None-Match");
		if(status304!=null)
			try {
			//	;
				if(Integer.valueOf((new SimpleDateFormat("MMddHHmm").format(Calendar.getInstance().getTime())).toString())
						-Integer.valueOf(status304)<200){
					HttpServletResponse httpResponse =(HttpServletResponse) response;
					httpResponse.setStatus(304);
					return;  
				}
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
			//	e.printStackTrace();
			}
		SystemContext.setPage(getPage(httpRequest));
		SystemContext.setPagesize(getPagesize(httpRequest));
		SystemContext.setOffset(getOffset(httpRequest));
		String url = httpRequest.getServletPath();
		

		String categorySuffix = WebProperty.readUrl("site.category.suffix");
		String productSuffix =  WebProperty.readUrl("site.product.seosuffix");
		String articleSuffix =  WebProperty.readUrl("site.article.seosuffix");
		boolean seoCategory = Integer.valueOf(WebProperty.readUrl("site.category.seo"))>0?true:false;
		boolean seoProduct = Integer.valueOf(WebProperty.readUrl("site.product.seo"))>0?true:false;
		boolean seoArticle = Integer.valueOf(WebProperty.readUrl("site.article.seo"))>0?true:false;
		if(seoCategory){
			if(url.endsWith(categorySuffix)){
				httpRequest.getRequestDispatcher("/product/manage.do?url="+url.substring(1,url.indexOf(categorySuffix))+"&"+httpRequest.getQueryString()).forward(request, response);
				return;
			}
		}else{
			if(url.matches("^/category/[0-9]+$")){
				httpRequest.getRequestDispatcher("/product/manage.do?id="+url.replaceAll("/category/", "")+"&"+httpRequest.getQueryString()).forward(request, response);
				return;	
			}
		}
		if(seoProduct){
			if(url.endsWith(productSuffix)){
				request.getRequestDispatcher("/product/manage.do?method=getproduct&url="+url.substring(1, url.indexOf(productSuffix))+"&"+httpRequest.getQueryString()).forward(request, response);
				return;
			}
		}else{
			if(url.matches("^/product/[0-9]+$")){
				request.getRequestDispatcher("/product/manage.do?method=getproduct&id="+url.replaceAll("/product/", "")+"&"+httpRequest.getQueryString()).forward(request, response);
				return;
			}
		}
		if(seoArticle){
			if(url.endsWith(articleSuffix)){
				System.out.println("dispatcher");
				url = url.substring(1);
				String dispatcher= "/frontarticle.do?type="+
				url.substring(0, url.indexOf("/"))+"&url="+url.substring(url.indexOf("/")+1).replaceAll(articleSuffix, "")+
				"&"+httpRequest.getQueryString();
				System.out.println(dispatcher);
				httpRequest.getRequestDispatcher(dispatcher).forward(request, response);
				return;
			}
		}else{
			if(url.matches("^/article/[0-9]+$")){
				System.out.println("dispatcher");
				String dispatcher="/frontarticle.do?id="+
				url.replaceAll("/article/", "")+
				"&"+httpRequest.getQueryString();
				System.out.println(dispatcher);
				httpRequest.getRequestDispatcher(dispatcher).forward(request, response);
				return;
			}
		}
		/*
		if(seoCategory==true&&url.endsWith(categorySuffix)){
			//httpRequest.setAttribute("url", url.substring(0, url.indexOf(categorySuffix)));
			httpRequest.getRequestDispatcher("/product/manage.do?url="+url.substring(1,url.indexOf(categorySuffix))+"&"+httpRequest.getQueryString()).forward(request, response);
			return;
		}else if(seoProduct==true&&url.endsWith(productSuffix)){
			//httpRequest.setAttribute("url", url.substring(0, url.indexOf(productSuffix)));
			
			request.getRequestDispatcher("/product/manage.do?method=getproduct&url="+url.substring(1, url.indexOf(productSuffix))).forward(request, response);
			return;
		}else if(seoArticle==true&&url.matches("^/(.*)/(.*)"+articleSuffix+"$")){
			request.getRequestDispatcher("/frontarticle.do?"+httpRequest.getQueryString()+url.substring(1, url.indexOf(productSuffix))).forward(request, response);
			return;
			
		}
		*/
	//	LoadPropertiesTool configuration = new LoadPropertiesTool();
	//	configuration.loadProperties("C:/systemConfig.properties");
	//	ApplicationContext context = new ClassPathXmlApplicationContext("beanConfig.xml"); 
		
		//configuration.loadProperties(getServletContext()
		//		.getRealPath("/WEB-INF/systemCofig.properties"));
	//	request.setAttribute("config",configuration);
	//	FileCaptureResponseWrapper responseWrapper = new FileCaptureResponseWrapper((HttpServletResponse)response);
	//	HnfealeanFileCaptureResponseWrapper respWrapper = new HnfealeanFileCaptureResponseWrapper((HttpServletResponse)response);
		//FileCaptureResponseWrapper responseWrapper = new FileCaptureResponseWrapper((HttpServletResponse)response); 
	//	HttpServletResponse resp = (HttpServletResponse) response;
	//	WrapperResponse wResponse = new WrapperResponse(resp);
		//HnfealeanWapperedResponse hResponse = new HnfealeanWapperedResponse(resp);
		try {
			chain.doFilter(request, response);
			//chain.doFilter(request, respWrapper);
			//response.getWriter().write(respWrapper.toString());
			//System.out.println("----缓存内容开始输出--");
		//	System.out.println(respWrapper.toString());
			//System.out.println(wResponse.getContent());
			
//			byte[] data =  hResponse.getResponseData();
		//	 ServletOutputStream output=response.getOutputStream();
			// output.write(data);
	//		 output.flush();
	//		File file = new File("C:\\"+(new Date()).getTime()+".txt");
	//		FileOutputStream outStream = new FileOutputStream(file);
	//		OutputStreamWriter writer =  new OutputStreamWriter(outStream,"UTF-8");
	//		BufferedWriter sw = new BufferedWriter(writer);
	//		sw.write(data.toString());
	//		sw.flush();
	//		writer.flush();
	//		sw.close();
	//		writer.close();
			//chain.doFilter(request, responseWrapper);
			//responseWrapper.writeFile("C:\\"+(new Date()).getTime()+".txt");
			//System.out.println(responseWrapper.toString());
		} finally {
			// 清空ThreadLocal中的值
			SystemContext.removeOffset();
			SystemContext.removePagesize();
		}
		//ServletContext application=this.
		/*
		 * HttpServletResponse res = (HttpServletResponse) response;
		 * 
		 * res.addHeader("Etag:", "d5736a60-1efd-fa6b3080");
		 * System.out.println(httpRequest.getHeader("If-None-Match"));
		 */
	}

	protected int getOffset(HttpServletRequest request) {
		return (SystemContext.getPage()-1)*SystemContext.getPagesize();
//		int offset = 0;
//		try {
//			int page = Integer.parseInt(request.getParameter("page"));
//			if(page==0) page=1;
//			
//			offset = (page-1)*SystemContext.getPagesize();
//		} catch (NumberFormatException ignore) {
//		}
//		return offset;
	}
	protected int getPage(HttpServletRequest request) {
		int page;
		try {
			page = Integer.parseInt(request.getParameter("page"));
			if(page==0){
				return 1;
			}
			
		} catch (Exception ignore) {
			return 1;
		}
		return page;
	}
	protected int getPagesize(HttpServletRequest request) {
		
		try {
			if(WebUtil.getAdministratorLogin(request)){
			int pagesize = Integer.parseInt(request.getParameter("pagesize"));
			
			//if(pagesize%10==0&&pagesize/10<3){//10,20
				return pagesize;
		//	}
			}
		} catch (Exception ignore) {
			return ConstantString.INITIALPAGESIZE;
		}
		return ConstantString.INITIALPAGESIZE;
		
	}

	public void init(FilterConfig config) throws ServletException {
		
		try {
			Properties prop = new Properties();
			// prop.put("runtime.log",
			// config.getServletContext().getRealPath("/WEB-INF/log/velocity.log"));
			prop.put("file.resource.loader.path", config.getServletContext()
					.getRealPath("/WEB-INF/vm"));
			prop.put("input.encoding", "UTF-8");
			prop.put("output.encoding", "UTF-8");
			Velocity.init(prop);

			ServletContext context = config.getServletContext();
		    Properties p = new Properties();
		    p.load(new FileInputStream(context.getRealPath("/WEB-INF/systemConfig.properties")));
/*		    Enumeration i = p.elements();
		    System.out.println( p.values());
		    Enumeration keys  =  p.keys();
		    System.out.println( p.keys());
		    System.out.println(  p.entrySet());
		    while(i.hasMoreElements()){
		    	while(i.hasMoreElements()){
		    		System.out.println(i.nextElement());
		    	}
		    }
		    */
		    Set<Entry<Object, Object>> set = p.entrySet();
		    Iterator ite = set.iterator();
		    while(ite.hasNext()){
		    	String obj = ite.next().toString();
		    	String[] att = obj.split("=");
		    	context.setAttribute(att[0], att[1]);
		    	System.out.println(att[0]+att[1]);
		    }

			//context.
			//context.setAttribute(name, object)
			//context.
		//	config.
			//new Constant();
			//Velocity.
		//	SystemCofig.config.load(new FileInputStream(config.getServletContext().getRealPath("/WEB-INF/systemConfig.properties")));
		//	System.out.println(SystemCofig.config.getProperty("swf"));
			//systemConfig.load(new FileInputStream(config.getServletContext().getRealPath("/WEB-INF/systemConfig.properties")));
		//	System.out.println(systemConfig.get("gif"));
			//systemConfig.load(inStream)
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
