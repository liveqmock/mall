package com.hnfealean.sport.web.actions.product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.hnfealean.sport.managers.article.FrontArticleManager;
import com.hnfealean.sport.managers.product.CategoryAddedProductListsManager;
import com.hnfealean.sport.managers.product.CategoryAttributeManager;
import com.hnfealean.sport.managers.product.FrontProductManager;
import com.hnfealean.sport.model.product.Category;
import com.hnfealean.sport.model.product.CategoryAttribute;
import com.hnfealean.sport.model.product.CategoryAttributeOption;
import com.hnfealean.sport.model.product.ImagesAndStyle;
import com.hnfealean.sport.model.product.Product;
import com.hnfealean.sport.pageset.PageModel;
import com.hnfealean.sport.pageset.SystemContext;
import com.hnfealean.sport.web.SeoUrl;
import com.hnfealean.sport.web.WebProperty;
import com.hnfealean.sport.web.WebUtil;
import com.hnfealean.sport.web.cache.CacheOperation;
import com.hnfealean.sport.web.forms.product.front.FrontProductActionForm;

public class FrontProductAction extends DispatchAction {
	private static int cacheTime =Integer.valueOf(WebProperty.readUrl("site.cache.maxtime"));
	private static int cacheVisit=Integer.valueOf(WebProperty.readUrl("site.cache.maxvisit"));
	private CategoryAttributeManager categoryAttributeManager;
	private FrontProductManager frontProductManager;
	private FrontArticleManager frontArticleManager;
	private CategoryAddedProductListsManager categoryAddedProductsListManager;

	public void setCategoryAddedProductsListManager(
			CategoryAddedProductListsManager categoryAddedProductsListManager) {
		this.categoryAddedProductsListManager = categoryAddedProductsListManager;
	}

	public void setFrontArticleManager(FrontArticleManager frontArticleManager) {
		this.frontArticleManager = frontArticleManager;
	}

	public void setCategoryAttributeManager(
			CategoryAttributeManager categoryAttributeManager) {
		this.categoryAttributeManager = categoryAttributeManager;
	}

//	private List pImageUrl;
//	private List pSavedPrice;


	public void setFrontProductManager(FrontProductManager frontProductManager) {
		this.frontProductManager = frontProductManager;
	}
	
	public ActionForward getproduct(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		FrontProductActionForm fpaf =(FrontProductActionForm) form;
                boolean seo = Integer.valueOf(WebProperty.readUrl("site.product.seo"))>0?true:false;
                Product p;
                if(seo){
                    if(fpaf.getUrl()==null||fpaf.getUrl().trim().length()==0){
                            response.sendError(404);
                            return null;
                    }
                    p =(Product)CacheOperation.getInstance().getCacheData(frontProductManager,"getProductByUrl",
				new Object[]{fpaf.getUrl()},cacheTime,cacheVisit);
                }else{
                   // p=frontProductManager.getProductById(fpaf.getId());
                    p = (Product)CacheOperation.getInstance().getCacheData(frontProductManager,"getProductById",
				new Object[]{fpaf.getId()},cacheTime,cacheVisit);
                }
		//	下面的是缓存Cache	Product p = frontProductManager.getProductByUrl(fpaf.getUrl());
		//注意：Product为缓存中的内容，所以在jsp页面上，对于Product的属性如options、imagesAndStyles等直接取会出错
		//两种解决方法：
		//一、将所有的属性lazy="true"去掉，后台操作会导致性能下降
		//二、不使用cache，前台查询操作会有较大延迟
		//三、将Product的各个属性也缓存起来，放到request范围中。在jsp页面不使用${product.options}这种形式来取数据
		//		而使用${options}来取
		//四、取category可直接用${position[i]}
		//Product p =(Product)CacheOperation.getInstance().getCacheData(frontProductManager,"getProductByUrl",
		//		new Object[]{fpaf.getUrl()},cacheTime,cacheVisit);
		if(p==null){
			response.sendError(404);
			return null;
		}
		
		//下面使用了cache 	request.setAttribute("attributeoptions", frontProductManager.getAttributeOptionsByProductId(p.getId()));
		
		request.setAttribute("attributeoptions", CacheOperation.getInstance().getCacheData(frontProductManager,"getAttributeOptionsByProductId",new Object[]{p.getId()},cacheTime,cacheVisit));
		//下面使用了cache	request.setAttribute("styles", frontProductManager.getImagesAndStylesByProductId(p.getId()));
		request.setAttribute("images",CacheOperation.getInstance().getCacheData(frontProductManager,"getImagesAndStylesByProductId",new Object[]{p.getId()},cacheTime,cacheVisit));
		//下面使用了cache	request.setAttribute("brand", frontProductManager.getBrandByProductId(p.getId()));
		request.setAttribute("brand", CacheOperation.getInstance().getCacheData(frontProductManager,"getBrandByProductId",new Object[]{p.getId()},cacheTime,cacheVisit));
		//下面使用了cache	request.setAttribute("detailgroups", frontProductManager.getDetailGroupsByProductId(p.getId()));
		request.setAttribute("detailgroups", CacheOperation.getInstance().getCacheData(frontProductManager,"getDetailGroupsByProductId",new Object[]{p.getId()},cacheTime,cacheVisit));
		
		
		
		//下面使用了cache	Category category = frontProductManager.getCategoryByProductId(p.getId());
		Category category =(Category) CacheOperation.getInstance().getCacheData(
				frontProductManager,"getCategoryByProductId",new Object[]{p.getId()},cacheTime,cacheVisit);
		
		//下面使用了cache		List<Category> breadcrumbs = frontProductManager.getPositon(category.getUrl());
		List<Category> breadcrumbs =(List<Category>) CacheOperation.getInstance().getCacheData(
				frontProductManager,"getPositon",new Object[]{category.getId()},cacheTime,cacheVisit);	
				request.setAttribute("position", breadcrumbs);
		request.setAttribute("product", p);
		//request.setAttribute("category", category);
		request.setAttribute("options", p.getOptions());
		
		List<Category> sameLevelCategories = (List<Category>)CacheOperation.getInstance().getCacheData(
				frontProductManager,
				"searchSameLevelCategories",
				new Object[]{(int)category.getId()}
				,cacheTime,cacheVisit);
		request.setAttribute("sameLevelCategories",sameLevelCategories);
		setHotsell(request, category);
		
		return mapping.findForward("productview");
		
	}
	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		FrontProductActionForm fpaf =(FrontProductActionForm) form;
/*	     
 * 			格式化输入的filter，可以去掉的
 * if(fpaf.getFilter()!=null&&fpaf.getFilter().trim().length()>0){
	    	 	String filstr=fpaf.getFilter().trim(); 
	    		String[] inputArray = filstr.split("-");
	    		Arrays.sort(inputArray, new CompareUtil());
	    		 StringBuffer inputStr = new StringBuffer();
	    		 if(inputArray.length>0){
	    		 for (int i = 0; i < inputArray.length-1; i++) {  
	    	        // System.out.println(inputArray[i]); 
	    	         inputStr.append(inputArray[i]).append("-");
	    	     }  
	    		 inputStr.append(inputArray[inputArray.length-1]);
	    		 }
	    		 fpaf.setFilter(inputStr.toString());
//	    if(request.getParameter("filter").equals(inputStr.toString())){
//	    	((HttpServletRequest) request).setParameter( "filter ",inputStr.toString()); 
//	    }
	    }*/
		
		String orderBy =" order by createDate desc";
		if(fpaf.getOrderBy() == null ||fpaf.getOrderBy().trim().length()==0||fpaf.getOrderBy().equals(WebProperty.readUrl("site.order.by_createdatedesc"))){
			//orderBy=" order by createDate desc";
			fpaf.setOrderBy(WebProperty.readUrl("site.order.by_createdatedesc"));
		}else if(fpaf.getOrderBy().equals(WebProperty.readUrl("site.order.by_sellcountdesc"))){
			orderBy=" order by sellCount desc";
		}else if(fpaf.getOrderBy().equals(WebProperty.readUrl("site.order.by_sellpriceasc"))){
			orderBy=" order by sellPrice asc";
		}else if(fpaf.getOrderBy().equals(WebProperty.readUrl("site.order.by_sellpricedesc"))){
			orderBy=" order by sellPrice desc";
		}else{
			//throw new SystemException("无效参数！");
			fpaf.setOrderBy(WebProperty.readUrl("site.order.by_createdatedesc"));
		}
		request.setAttribute("orderBy", fpaf.getOrderBy());
		String show=WebProperty.readUrl("site.productlist.showmode_list");
		if(fpaf.getShow()==null){
		}else if(fpaf.getShow().equals(WebProperty.readUrl("site.productlist.showmode_image"))){
			show=WebProperty.readUrl("site.productlist.showmode_image");
		}
		fpaf.setShow(show);
		request.setAttribute("show", show);
		//System.out.println(request.getQueryString().indexOf("url"));
	//	if(request.getContextPath()!=null&&request.getContextPath().length()>0)
		String url = fpaf.getUrl();

		if((url==null||url.trim().length()==0)&&fpaf.getId()==0)
		return displayMainPage(mapping, request, response, fpaf, orderBy);
		String seoUrl = WebProperty.readUrl("site.category.seo");
		
		return displayCatgory(mapping, request, response, fpaf, orderBy,Integer.valueOf(seoUrl)>0?true:false);
	}

	private ActionForward displayMainPage(ActionMapping mapping,
			HttpServletRequest request, HttpServletResponse response,
			FrontProductActionForm fpaf, String orderBy) throws IOException {
		//没有url,首页
		//查询
		//			一级目录
		//			网站所有产品
		//			新闻
		//request.setAttribute("childCategories",	frontProductManager.searchAllTopCategories());
		List<Category> categories =(List<Category> )CacheOperation.getInstance().getCacheData(
				frontProductManager,
				"searchAllTopCategories",
				null,cacheTime,cacheVisit);
		request.setAttribute("childCategories",	categories);
		
		PageModel pageModel =(PageModel)CacheOperation.getInstance().getCacheData(
				frontProductManager,
				"searchProductsAll",
				new Object[]{orderBy,SystemContext.getOffset(),SystemContext.getPagesize()},
				cacheTime,cacheVisit)
				;
		
		if(pageModel==null){
			 response.reset();  
			 response.sendError(404);
			 return null;  
		}
		setImagesAndSaved(pageModel);
		request.setAttribute("childProducts",pageModel);	
		//request.setAttribute("pSavedPrice", pSavedPrice);
		generateFilterStr(request, fpaf);
		if(fpaf.getShow().equals("list")){
			return mapping.findForward("list");
		}else{
			return mapping.findForward("list_image");
		}
	}

	private ActionForward displayCatgory(ActionMapping mapping,
			HttpServletRequest request, HttpServletResponse response,
			FrontProductActionForm fpaf, String orderBy,boolean categorySeo) throws IOException,
			ServletException {
		/**
		 * 下面的为目录
		 * 查询			
		 * 				当前分类				category				frontProductManager.getCategoryByUrl(fpaf.getUrl().trim())
		 * 				当前分类的同级分类	sameLevelCategories		frontProductManager.searchSameLevelCategories(category.getId())
		 * 				当前分类的直接子分类	directChildCategories	frontProductManager.searchDirectChildCategories(category.getId());
		 * 				当前分类的所有子分类ID	categoryIds.append(category.getId()).toString()
		 * 				当前分类及所有子分类的产品	childProducts	pageModel
		 */
		
	//	Class frontProductManagerRealClass = AopUtils.getTargetClass(frontProductManager);
		//Category category = frontProductManager.getCategoryByUrl(fpaf.getUrl().trim());
		Category category ;
		if(fpaf.getId()>0){
			category = frontProductManager.getCategoryById(fpaf.getId());
		}else{
			category = (Category)CacheOperation.getInstance().getCacheData(
				frontProductManager
				, "getCategoryByUrl", new Object[]{fpaf.getUrl().trim()}, 
				cacheTime, cacheVisit);
		}
		if(category==null){
			 response.reset();  
			 response.sendError(404);
			 return null;  
		}
		//Category categoryInCache = (Category)CacheOperation.getInstance().getCacheData(frontProductManager, "getCategoryByUrl", new Object[]{fpaf.getUrl().trim()}, 12000, cacheVisit);
		//Category categoryInCache = (Category)CacheOperation.getInstance().getCacheData(category, category.getName(), new Object[]{category.getName(),category.getId()}, 12000, cacheVisit);
		//System.out.println("categoryInCache.getId()"+category.getId()+"categoryInCache.getName():"+category.getName());
		request.setAttribute("category", category);
		
		//request.setAttribute("sameLevelCategories", frontProductManager.searchSameLevelCategories(category.getId()));
		List<Category> sameLevelCategories = (List<Category>)CacheOperation.getInstance().getCacheData(
				frontProductManager,
				"searchSameLevelCategories",
				new Object[]{(int)category.getId()}
				,cacheTime,cacheVisit);
		request.setAttribute("sameLevelCategories",sameLevelCategories);

		//List<Category> directChildCategories = frontProductManager.searchDirectChildCategories(category.getId());
		List<Category> directChildCategories = 
			(List<Category>)CacheOperation.getInstance().getCacheData(frontProductManager,"searchDirectChildCategories",
					new Object[]{(int)category.getId()},cacheTime,cacheVisit);
		request.setAttribute("directChildCategories", directChildCategories);
	
		String categoryIds = (String)CacheOperation.getInstance().getCacheData(
				frontProductManager,
				"searchAllChildCategoriesId",
				new Object[]{(int)category.getId()},cacheTime,cacheVisit);
		PageModel pageModel =frontProductManager.searchProductByCategoryIds(
				categoryIds,
				fpaf.getFilter(),orderBy);
//				 pageModel =(PageModel)CacheOperation.getInstance().getCacheData(
//						frontProductManager,"searchProductByCategoryIds",
//			new Object[]{categoryIds,fpaf.getFilter(),orderBy},cacheTime,cacheVisit);
		if(pageModel==null){
			 response.reset();  
			 response.sendError(404);
			 return null;  
		}
		setImagesAndSaved(pageModel);
		//request.setAttribute("childProducts",pageModel);	
		//request.setAttribute("categoryNews", frontArticleManager.searchAllCategoryArticle(category.getId(),10));
		request.setAttribute("categoryNews",
				(List)CacheOperation.getInstance().getCacheData(
						frontArticleManager,
						"searchAllCategoryArticle",
						new Object[]{category.getId(),10},cacheTime,cacheVisit));
			
	//	request.setAttribute("pSavedPrice", pSavedPrice);
		//request.setAttribute("brands",frontProductManager.searchChildBrandsAll(fpaf.getUrl()));
		//2011.1.7 14:52注：brands未写sql，查询出来的为null
/*		request.setAttribute("brands",
				(List)CacheOperation.getInstance().getCacheData(
						frontProductManager,
						"searchChildBrandsAll",
						new Object[]{fpaf.getUrl()},WebProperty.readUrl("site.cache.maxtime"),cacheVisit));*/
		//request.setAttribute("position", frontProductManager.getPositon(fpaf.getUrl()));
		List<Category> position = (List<Category>)CacheOperation.getInstance().getCacheData(
				frontProductManager,
				"getPositon",
				new Object[]{category.getId()},cacheTime,cacheVisit);
		request.setAttribute("position",position);
				
		request.setAttribute("childProducts",pageModel);
		
		setTop10(request, category);
		
		setHotsell(request, category);
		//String pRecommendIdStrs = categoryAddedProductsListManager.searchByCategoryId(category.getId(), ConstantString.RECOMMEND);
		String pRecommendIdStrs = (String) CacheOperation.getInstance().getCacheData(
				categoryAddedProductsListManager,
				"searchByCategoryId",
				new Object[]{category.getId(),"recommend"},cacheTime,cacheVisit);
		
		if(pRecommendIdStrs==null||pRecommendIdStrs.trim().length()==0){	
			request.setAttribute("recommend",null);
		}else{
			//List<Product> recommend =frontProductManager.getProductsByIdString(pRecommendIdStrs);
			List<Product> recommend =(List<Product>) CacheOperation.getInstance().getCacheData(
					frontProductManager,
					"getProductsByIdString",
					new Object[]{pRecommendIdStrs},cacheTime,cacheVisit);
			for(Product product:recommend){
				ImagesAndStyle image =(ImagesAndStyle)CacheOperation.getInstance().getCacheData(
						frontProductManager,
						"getImageByProductId",
						new Object[]{product.getId()},cacheTime,cacheVisit);
				if(image==null||image.getImageUrl()==null||image.getImageUrl().trim().length()==0){
					product.setTempImageUrl(WebProperty.readUrl("site.image.nopic"));
				}else{
					product.setTempImageUrl(image.getImageUrl().trim());
				}
			}
			request.setAttribute("recommend",recommend);
		}
		//String filter = fpaf.getFilter();
		request.setAttribute("filter",generateFilterStr(category,/*category.getId(),*/request,fpaf));
	
	//	System.out.println("category.id: "+category.getId());
		
		//request.setAttribute("categoryNews", frontArticleManager.searchAllCategoryArticle(i,10));
		
		//PageModel pageModel = frontProductManager.searchChildProductsAll(fpaf.getUrl(),choose+orderBy);
			//frontProductManager.searchProductsAll(choose.concat(orderBy));


	//	System.out.print(pImageUrl);
	//	request.setAttribute("pImage", pImageUrl);
		
	
		//request.setAttribute("currentCategoryName",frontProductManager.findCategoryName(fpaf.getUrl()));
		
		//request.setAttribute("brands", frontProductManager.searchBrandsAll(""));
		//request.setAttribute("pm", pageModel);
//		if(fpaf.getUrl()==""||fpaf.getUrl()==null){
//			request.setAttribute("childCategories", frontProductManager.searchChildCategories(""));
//			
//			request.setAttribute("brands",frontProductManager.searchBrandsAll(""));			
//		}else{
//			request.setAttribute("childCategories", frontProductManager.searchChildCategories(fpaf.getUrl()));
//			request.setAttribute("brands",frontProductManager.searchChildBrandsAll(fpaf.getUrl()));
//			//request.setAttribute("currentCategoryName",frontProductManager.findCategoryName(fpaf.getUrl()));
//			request.setAttribute("position", frontProductManager.getPositon(fpaf.getUrl()));
//		}
//		
//		request.setAttribute("childProducts",pageModel);//frontProductManager.searchChildProductsAll(fpaf.getUrl()));
		//request.setAttribute("currentCategory",fpaf.getUrl());
		/*
		String Suffix;
		if(fpaf.getUrl()==""||fpaf.getUrl().length()==0){
			Suffix="";
		}else{
			Suffix="-wholesale/";
		}
		request.setAttribute("suffix",Suffix);
		
		String Suffix4ChildCategory="-wholesale/";
		
		request.setAttribute("suffix4ChildCategory",Suffix4ChildCategory);
		
		if(fpaf.getUrl()!=null)
			fpaf.setCategoryId(frontProductManager.getCategoryIdByTitle(fpaf.getUrl()));*/
/*	
 * 此处为使用类别页的模板
 * 	File f = new File(request.getRealPath(request.getContextPath())+"\\WEB-INF\\page\\category\\"+fpaf.getCategoryId()+".jsp");
		System.out.println(request.getRealPath(request.getContextPath())+"\\WEB-INF\\page\\category\\"+fpaf.getCategoryId()+".jsp");
		if(f.exists()){
			response.reset();
			response.addHeader("Etag:","d5736a60-1efd-fa6b3080");
			request.getRequestDispatcher("/WEB-INF/page/category/"+fpaf.getCategoryId()+".jsp").forward(request, response);
			
			return null;
		}*/
	//	System.out.println("-------------------------------分割线--------------------------");
	//	request.setAttribute(name, categoryManager.getCategoryByName(name))
		if(fpaf.getShow().equals("list")){
			return mapping.findForward("list");
		}else{
			return mapping.findForward("list_image");
		}
		
		//return mapping.findForward("list");
	//	" and p.brand.id = " + fpaf.getBrandId()
	}

	private void setTop10(HttpServletRequest request, Category category) {
		//String pTop10IdStrs = categoryAddedProductsListManager.searchByCategoryId(category.getId(), ConstantString.TOPTEN);
		
		String pTop10IdStrs = (String) CacheOperation.getInstance().getCacheData(
				categoryAddedProductsListManager,
				"searchByCategoryId",
				new Object[]{category.getId(),"top10"},cacheTime,cacheVisit);
		//List<Product> top10 =frontProductManager.getProductsByIdString(pTop10IdStrs);
		if(pTop10IdStrs==null||pTop10IdStrs.trim().length()==0){	
			request.setAttribute("hotsell",null);
		}else{
			List<Product> top10 =(List<Product>) CacheOperation.getInstance().getCacheData(
					frontProductManager,
					"getProductsByIdString",
					new Object[]{pTop10IdStrs},cacheTime,cacheVisit);
		for(Product product:top10){
			ImagesAndStyle image =(ImagesAndStyle)CacheOperation.getInstance().getCacheData(
					frontProductManager,
					"getImageByProductId",
					new Object[]{product.getId()},cacheTime,cacheVisit);
			if(image==null||image.getImageUrl()==null||image.getImageUrl().trim().length()==0){
				product.setTempImageUrl(WebProperty.readUrl("site.image.nopic"));
			}else{
				product.setTempImageUrl(image.getImageUrl().trim());
			}	
			request.setAttribute("top10",top10);
		}
		}
	}

	private void setHotsell(HttpServletRequest request, Category category) {
		//String pHotsellIdStrs = categoryAddedProductsListManager.searchByCategoryId(category.getId(), ConstantString.HOTSELL);
		String pHotsellIdStrs =(String) CacheOperation.getInstance().getCacheData(
				categoryAddedProductsListManager,
				"searchByCategoryId",
				new Object[]{category.getId(),"hotsell"},cacheTime,cacheVisit);
		if(pHotsellIdStrs==null||pHotsellIdStrs.trim().length()==0){	
			request.setAttribute("hotsell",null);
		}else{
			//List<Product> hotsell =frontProductManager.getProductsByIdString(pHotsellIdStrs);
			List<Product> hotsell =(List<Product>) CacheOperation.getInstance().getCacheData(
					frontProductManager,
					"getProductsByIdString",
					new Object[]{pHotsellIdStrs},cacheTime,cacheVisit);
			for(Product product:hotsell){
				ImagesAndStyle image =(ImagesAndStyle)CacheOperation.getInstance().getCacheData(
						frontProductManager,
						"getImageByProductId",
						new Object[]{product.getId()},cacheTime,cacheVisit);
				if(image==null||image.getImageUrl()==null||image.getImageUrl().trim().length()==0){
					product.setTempImageUrl(WebProperty.readUrl("site.image.nopic"));
				}else{
					product.setTempImageUrl(image.getImageUrl().trim());
				}
			}
			request.setAttribute("hotsell",hotsell);
		}
	}
	/**
	 * 为首页生成基准路径和filter、page、orderby
	 * @param request
	 * @param form
	 * @return
	 */
	private void generateFilterStr(HttpServletRequest request,FrontProductActionForm form) {
		// String filter = form.getFilter();
	     StringBuffer urlStr = new StringBuffer();
	  //   String urlStrOut = null;
	     StringBuffer urlInner = new StringBuffer();
		 if(form.getOrderBy()==null||form.getOrderBy().trim().length()==0){
			 
		 }else{
			urlInner.append("orderBy=").append(form.getOrderBy().trim()).append("&");
		 }
		 if(form.getShow()==null||form.getShow().trim().length()==0){
			 
		 }else{
			urlInner.append("show=").append(form.getShow().trim()).append("&");
		 }
		 
		 if(form.getPage()>0){
				urlInner.append("page=").append(form.getPage()).append("&");
		 }
		 
		 if(urlInner.charAt(urlInner.length()-1)=='&'){
		    	urlInner.deleteCharAt(urlInner.length()-1);
		 }//去掉urlInner最后的&
	    	urlStr.append("?").append(urlInner.toString());
	    request.setAttribute("baseUrl", urlStr.toString());
	    System.out.println("baseUrl:"+request.getAttribute("baseUrl"));
		
	}
	/**
	 * 为类别页面生成基准路径和filter、page、orderby
	 * @param categoryId
	 * @param request
	 * @param form
	 * @return
	 */
	private String generateFilterStr(Category category,/*int categoryId,*/HttpServletRequest request,FrontProductActionForm form) {
		//Map map = new HashMap();  
	     //Enumeration paramNames = request.getParameterNames();
            boolean seo = WebProperty.readUrl("site.category.seo").equals("1");
		 String filter = form.getFilter();
	     StringBuffer urlStr = new StringBuffer();
	     String urlStrOut = null;
	     StringBuffer urlInner = new StringBuffer();
             if(form.getId()==0&&(form.getUrl()==null||form.getUrl().trim().length()==0)){
                urlStrOut="";//首页，只有首页的url参数与id都为空
             }else{
              //  if(seo){
                    //urlStr.append(form.getUrl()).append(WebProperty.readUrl("site.category.suffix"));//nfl-jersey-wholesale
        urlStr.append(SeoUrl.createCategoryUrl(category.getUrl(), category.getId()));//nfl-jersey-wholesale
               // }else{

              //  }
             }
             System.out.println(urlStr.toString());
	     /*
             if((form.getUrl()==null||form.getUrl().trim().length()==0)){
	    	 
	     }else{
		   //  if(request.getParameter("url")!=null){
		
			  urlStr.append(form.getUrl()).append(WebProperty.readUrl("site.category.suffix"));//nfl-jersey-wholesale
	     }
             */

		 if(form.getOrderBy()==null||form.getOrderBy().trim().length()==0){
			 
		 }else{
			urlInner.append("orderBy=").append(form.getOrderBy().trim()).append("&");
		 }
		 if(form.getShow()==null||form.getShow().trim().length()==0){
			 
		 }else{
			urlInner.append("show=").append(form.getShow().trim()).append("&");
		 }
		 
		 if(form.getPage()>0){
				urlInner.append("page=").append(form.getPage()).append("&");
		 }
		    if(urlInner==null||urlInner.length()==0){
		    	//没有orderBy和page参数
		    	
		    }else{//有orderBy或page参数
		    	if(urlInner.charAt(urlInner.length()-1)=='&'){
			    	urlInner.deleteCharAt(urlInner.length()-1);
			    }//去掉urlInner最后的&
		    	urlStr.append("?").append(urlInner.toString());
		    }
	    

	    if(urlStr.length()==0)
	    	urlStrOut="";
	    else
	    	urlStrOut = urlStr.toString();
	    //-------------------------------------------------------------------------
	    //urlStrOut的格式为：nfl-jersey-wholesale?orderBy=...&show=...&page=..     或者为空
	    //-------------------------------------------------------------------------
	   System.out.println("urlStrOut: "+urlStrOut);
	   if(form.getFilter()==null||form.getFilter().trim().length()==0){
		   if(urlStrOut.charAt(urlStrOut.length()-1)=='&'){
			   request.setAttribute("baseUrl", urlStrOut.substring(0, urlStrOut.length()-1));
		   }else{
			   request.setAttribute("baseUrl", urlStrOut);
		   }
	   }else{
		   if(urlStrOut.charAt(urlStrOut.length()-1)=='&'){
			   request.setAttribute("baseUrl", urlStrOut+"filter="+form.getFilter().trim());
		   }else{
			   request.setAttribute("baseUrl", urlStrOut+"&filter="+form.getFilter().trim());
		   }
		   
	   }
	   System.out.println("baseUrl:"+request.getAttribute("baseUrl"));
	   	/**
	   	 * 查找类别下的所有属性，组成默认字符串filterInitial
	   	 */
	//	List<CategoryAttribute> categoryAttributes = categoryAttributeManager.getAllCategoryAttributes(categoryId);
		List<CategoryAttribute> categoryAttributes = 
			(List<CategoryAttribute>) CacheOperation.getInstance().getCacheData(
					categoryAttributeManager
					, "getAllCategoryAttributes", new Object[]{category.getId()/*categoryId*/}, cacheTime, cacheVisit);
		
		StringBuffer filterInitial =new StringBuffer();
		for(CategoryAttribute attribute:categoryAttributes){
			filterInitial.append(attribute.getId()+"_0-");
		}
		if(filterInitial.length()>0)
		if(filterInitial.charAt(filterInitial.length()-1)=="-".charAt(0))filterInitial.deleteCharAt(filterInitial.length()-1);
		

	   
	   
	   if(filter==null||filter.trim().length()==0){
			StringBuffer outputFilter = new StringBuffer(); 

			for(CategoryAttribute categoryAttribute:categoryAttributes){	//类别分类
				
				StringBuffer innerOptions = new StringBuffer();
			//	for(CategoryAttributeOption option :frontProductManager.getCatrgoryAttributeOptions(categoryAttribute.getId())){//categoryAttribute.getOptions()){
					for(CategoryAttributeOption option :
						(List<CategoryAttributeOption>)
						CacheOperation.getInstance().getCacheData(
												frontProductManager, 
												"getCatrgoryAttributeOptions", 
												new Object[]{categoryAttribute.getId()}, cacheTime, cacheVisit)){
				//for(CategoryAttributeOption option :frontProductManager.getCatrgoryAttributeOptions(categoryAttribute.getId())){//categoryAttribute.getOptions()){
					String temp = categoryAttribute.getId()+"_"+option.getId();
					filterInitial.toString().replaceAll(categoryAttribute.getId()+"_0", temp);
					if(urlStrOut.indexOf("?")>0)
						innerOptions.append("<a href='"+urlStrOut+"&filter="+
								filterInitial.toString().replaceAll(categoryAttribute.getId()+"_0", temp)+
								"'>"+option.getValue()+"</a>");
					else
						innerOptions.append("<a href='"+urlStrOut+"?filter="+
								filterInitial.toString().replaceAll(categoryAttribute.getId()+"_0", temp)+
								"'>"+option.getValue()+"</a>");
				}
				outputFilter.append("<dl><dt>"+categoryAttribute.getName()+":</dt><dd><a href='"+urlStrOut+"' class='this'>"+"全部"+"</a>"+innerOptions+"</dd></dl>");
				}
			return outputFilter.toString();
		}
	   	
		//检查用户的输入是否是   		数字_数字-....
	  // Pattern pattern =  Pattern.compile("^(?:\\d+_\\d+-)*\\d+_\\d+$");
	   String tempPattern = "^"+filterInitial.toString().replace("_0", "_\\d+")+"$";
	   		Pattern pattern =  Pattern.compile(tempPattern);
		Matcher matcher = pattern.matcher(filter);
		if(matcher.find()){//验证filter的格式是    			数字_数字-数字_数字
		//String[] filters = filter.split("-");		//将filter分组,分成[4_19],[6_25]这种
	StringBuffer outputFilter = new StringBuffer(); 
	
	for(CategoryAttribute categoryAttribute:categoryAttributes){	//类别分类
		
		StringBuffer innerOptions = new StringBuffer();
	//	for(CategoryAttributeOption option :frontProductManager.getCatrgoryAttributeOptions(categoryAttribute.getId())){//categoryAttribute.getOptions()){
		for(CategoryAttributeOption option :
			(List<CategoryAttributeOption>)
			CacheOperation.getInstance().getCacheData(
									frontProductManager, 
									"getCatrgoryAttributeOptions", 
									new Object[]{categoryAttribute.getId()}, cacheTime, cacheVisit)){
		
			String temp = categoryAttribute.getId()+"_"+option.getId();
			//filter.replaceAll(categoryAttribute.getId()+"_\\d+", temp).equals(filter)
			String out = filter.replaceAll(categoryAttribute.getId()+"_\\d+", temp);
			if(out.equals(filter))	out=filter+"' class='this";
			//out = out.equals(filter)?filter+"' class='this":out;
			if(urlStrOut.indexOf("?")>0)
				innerOptions.append("<a href='"+urlStrOut+"&filter="+
						out+
						"'>"+option.getValue()+"</a>");
			else
				innerOptions.append("<a href='"+urlStrOut+"?filter="+
						out+
						"'>"+option.getValue()+"</a>");
		}
		String temp =filter.replaceAll(categoryAttribute.getId()+"_\\d+",categoryAttribute.getId()+"_0");
		if(temp.equals(filter))	temp= filter+"' class='this";
		outputFilter.append("<dl><dt>"+categoryAttribute.getName()+":</dt><dd><a href='"+urlStrOut+(urlStrOut==null?"?":"&")+"filter="+
				temp+"'>"+"全部"+"</a>"+innerOptions+"</dd></dl>");
		}
	
	//List<CategoryAttribute> categoryAttributes = categoryAttributeManager.getAllCategoryAttributes(categoryId);
/*	for(CategoryAttribute categoryAttribute:categoryAttributes){	//类别分类的分类属性ID可能为4,6,8,9
		StringBuffer outAttributeStr = new StringBuffer();			//对于每一个类别的属性，需要
		for(int i=0;i<filters.length;i++){							//输入的filter，每一个filter
		if(Integer.valueOf(filters[i].split("_")[0])!=categoryAttribute.getId()){//对于每一个CategoryAttribute，验证其ID，如果ID不存在于filter里，那输出添加这个filter
			outAttributeStr.append(filters[i]+"-");									//比如categoryAttribute为"网络",ID为3，依次对[4_19],[6_25]进行遍历,输出的是4_19-6_25-
			}																		//对于categoryAttribute为"价格",ID为4,遍历后应当输出的是6_25-,因为4_19会对当前的attribute造成影响
		}
		if(outAttributeStr==null||outAttributeStr.length()==0){
			
		}else{
			if(outAttributeStr.charAt(outAttributeStr.length()-1)=="-".charAt(0))
			outAttributeStr.deleteCharAt(outAttributeStr.length()-1);
		}
		//去掉了outAttributeStr的末尾"-"
		//outAttributeStr="4_19-6_25"
		StringBuffer innerOptions = new StringBuffer();
		for(CategoryAttributeOption option :categoryAttribute.getOptions()){
			if(outAttributeStr==null||outAttributeStr.toString().length()==0){//outAttributeStr为空或者""时，说明输入的filter是当前的类别
				if(urlStrOut.indexOf("?")>0)									//比如，输入了4_26,当前的类别属性正好为"价格",ID为4,要按照原样输出当前属性option列表
					innerOptions.append("<a href='"+urlStrOut+"&filter="+categoryAttribute.getId()+"_"+option.getId()+"'>"+option.getValue()+"</a>");
				else
					innerOptions.append("<a href='"+urlStrOut+"?filter="+categoryAttribute.getId()+"_"+option.getId()+"'>"+option.getValue()+"</a>");
			}else{
				if(urlStrOut.indexOf("?")>0)
					innerOptions.append("<a href='"+urlStrOut+"&filter="+outAttributeStr.toString()+"-"+categoryAttribute.getId()+"_"+option.getId()+"'>"+option.getValue()+"</a>");
				else		
					
				innerOptions.append("<a href='"+urlStrOut+"?filter="+outAttributeStr.toString()+"-"+categoryAttribute.getId()+"_"+option.getId()+"'>"+option.getValue()+"</a>");
			}
		}//innerOptions = "<a href='4_19-6_25-1_9'>索尼爱立信</a><a href='4_19-6_25-1_8'>天宇</a><a href='4_19-6_25-1_10'>摩托罗拉</a>"
		//outAttributeStr.append("\">"+categoryAttribute.name+"</a>");
		//需要的是:
		if(urlStrOut.indexOf("?")>0){
			if(outAttributeStr==null||outAttributeStr.toString().length()==0){
				outputFilter.append("<dl><dt>"+categoryAttribute.getName()+":</dt><dd><a href='"+urlStrOut+"'>"+"全部"+"</a>"+innerOptions+"</dd></dl>");
			}else{
				outputFilter.append("<dl><dt>"+categoryAttribute.getName()+":</dt><dd><a href='"+urlStrOut+"&filter="+outAttributeStr+"'>"+"全部"+"</a>"+innerOptions+"</dd></dl>");
			}
		}else{
			if(outAttributeStr==null||outAttributeStr.toString().length()==0){
				outputFilter.append("<dl><dt>"+categoryAttribute.getName()+":</dt><dd><a href='"+urlStrOut+"'>"+"全部"+"</a>"+innerOptions+"</dd></dl>");
				
			}else
			outputFilter.append("<dl><dt>"+categoryAttribute.getName()+":</dt><dd><a href='"+urlStrOut+"?filter="+outAttributeStr+"'>"+"全部"+"</a>"+innerOptions+"</dd></dl>");
		}
		//<a href='4_19-6_25'>品牌</a><a href='4_19-6_25-1_9>索尼爱立信</a><a href='4_19-6_25-1_8'>天宇</a><a href='4_19-6_25-1_10'>摩托罗拉</a>
	}*/
	//System.out.println("outputFilter:"+outputFilter.toString());
		return outputFilter.toString();
		}
		return null;
	}

	private void setImagesAndSaved(PageModel pageModel) throws IOException {

	
		if(pageModel.getDatas()!=null){
			//System.out.print(pageModel.getDatas());
			List<Product> list = pageModel.getDatas();
		
			//ImagesAndStyle productStyle;
			//int i = 0;
			//int[] categoryId;
			//pImageUrl= new ArrayList(); 
			//pSavedPrice = new ArrayList();
			//pImageUrl.toArray()
			for(Product product:list){
				//Set<ImagesAndStyle> images =new HashSet<ImagesAndStyle>();

				//ImagesAndStyle image = frontProductManager.getImageByProductId(product.getId());
				//System.out.println("image of product "+product.getId());
				ImagesAndStyle image =(ImagesAndStyle)CacheOperation.getInstance().getCacheData(
						frontProductManager,
						"getImageByProductId",
						new Object[]{product.getId()},
						cacheTime,
						cacheVisit);
			//	images.add(image);
			//	product.setImagesAndStyles(images);
				if(image!=null)product.setTempImageUrl(image.getImageUrl());
				//pSavedPrice.add(product.getMarketPrice()-product.getSellPrice());
			}
//			for (Iterator iter=list.iterator(); iter.hasNext();i++) {
//				product = (Product)iter.next();
//				productStyle = frontProductManager.findProductImagesAndStyleByProductId(product.getId());
//				pSavedPrice.add(product.getMarketPrice()-product.getSellPrice());
//				if(productStyle.getImageUrl() == null){
//					//product.setMeta_Description("/images/sale.gif");
//					//product.setAdditionInfo("/images/sale.gif");
//					pImageUrl.add("/images/sale.gif");
//				}else{
//					//product.setMeta_Description(productStyle.getImageUrls());
//					pImageUrl.add(productStyle.getImageUrl());
//				}
//			}
		}
	}

	public ActionForward getHistory(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FrontProductActionForm fpaf = (FrontProductActionForm) form;

		//下面的使用Cache	Product p = frontProductManager.getProductById(fpaf.getId());
		Product p =(Product)CacheOperation.getInstance().getCacheData(frontProductManager, "getProductById", new Object[]{fpaf.getId()}, cacheTime, cacheVisit);
		if(p==null){
			return mapping.findForward("history");
		}
		WebUtil.addCookie(response, "recent", genaratorHistoryString(request,p.getId()), 30*24*60*60);
		return mapping.findForward("history");
	}
	private String genaratorHistoryString(HttpServletRequest request,Integer id){
		String cookieValue= WebUtil.getCookieByName(request, "recent");
		LinkedList<Integer> ids =new LinkedList<Integer>();
		if(cookieValue!=null&&!"".equals(cookieValue.trim())){
			String[] temps =cookieValue.split(",");
			for(String temp:temps){
				ids.offer(new Integer(temp.trim()));
			}
			if(ids.contains(id))ids.remove(id);
			
			if(ids.size()>=10)ids.poll();
		}
		ids.offer(id);
		StringBuffer out = new StringBuffer();
		List<Product> recentproducts = new ArrayList<Product>();
		for(Integer t:ids){
			out.append(t).append(",");
			//以下为Cache	frontProductManager.getProductById(t);

			recentproducts.add(
					(Product)CacheOperation.getInstance().getCacheData(
					frontProductManager, "getProductById", new Object[]{t}, cacheTime, cacheVisit)
					);
			for(Product product:recentproducts){
				ImagesAndStyle image =(ImagesAndStyle)CacheOperation.getInstance().getCacheData(
						frontProductManager,
						"getImageByProductId",
						new Object[]{product.getId()},cacheTime,cacheVisit);
				if(image==null||image.getImageUrl()==null||image.getImageUrl().trim().length()==0){
					product.setTempImageUrl(WebProperty.readUrl("site.image.nopic"));
				}else{
					product.setTempImageUrl(image.getImageUrl().trim());
				}
			}
			
		}
		if(out.length()>0)out.deleteCharAt(out.length()-1);
		request.setAttribute("recentproducts", recentproducts);
		return out.toString();
	}



	
}
