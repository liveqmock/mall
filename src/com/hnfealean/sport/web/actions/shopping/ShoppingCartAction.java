package com.hnfealean.sport.web.actions.shopping;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.hnfealean.sport.managers.shopping.ShoppingCartManager;
import com.hnfealean.sport.model.product.AttributeOption;
import com.hnfealean.sport.model.product.ImagesAndStyle;
import com.hnfealean.sport.model.product.Product;
import com.hnfealean.sport.model.shopping.ShoppingCart;
import com.hnfealean.sport.model.shopping.ShoppingCartItem;
import com.hnfealean.sport.web.WebProperty;
import com.hnfealean.sport.web.WebUtil;
import com.hnfealean.sport.web.cache.CacheOperation;
import com.hnfealean.sport.web.forms.shopping.ShoppingCartItemForm;

public class ShoppingCartAction extends DispatchAction {
	private static int cacheTime =Integer.valueOf(WebProperty.readUrl("site.cache.maxtime"));
	private static int cacheVisit=Integer.valueOf(WebProperty.readUrl("site.cache.maxvisit"));
	private ShoppingCartManager shoppingCartManager;
	public void setShoppingCartManager(ShoppingCartManager shoppingCartManager) {
		this.shoppingCartManager = shoppingCartManager;
	}
	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ShoppingCart shoppingCart = (ShoppingCart) request.getSession().getAttribute("shoppingCart");
		if(shoppingCart == null ||shoppingCart.getShoppingCartItems()==null||shoppingCart.getShoppingCartItems().size()==0){// || pg.getDatas().size()==0){
		//	initShoppingCart(mapping, request,response);
			initShoppingCart(request,response);
		}
		String url = (String) request.getParameter("directUrl");
		if(url!=null && url.length()>0){
			request.setAttribute("directUrl",url);
		}
		return mapping.findForward("index");
	}
	public ActionForward ajaxAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ShoppingCartItemForm scif =(ShoppingCartItemForm)form;
		if(scif.getAmount()==0||scif.getImagesAndStyleId()==0||scif.getProductId()==0||scif.getSizeId()==0){
			return mapping.findForward("index");
		}
		addToCart(mapping, scif, request, response);
		response.reset();
		response.getWriter().write(WebUtil.getShoppingCart(request,true));
		response.getWriter().flush();
		return null;
	}
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ShoppingCartItemForm scif =(ShoppingCartItemForm)form;
		if(scif.getAmount()==0||scif.getImagesAndStyleId()==0||scif.getProductId()==0||scif.getSizeId()==0){
			return mapping.findForward("index");
		}
		addToCart(mapping, scif, request, response);
		return mapping.findForward("index");
/*//		if(shoppingCart.getShoppingCartItems()==null||shoppingCart.getShoppingCartItems().size()== 0 ){//|| pg.getDatas().size()==0){
//			
//		
//			List<ShoppingCartItem> items = new ArrayList<ShoppingCartItem>();
//			
//			shoppingCart.setShoppingCartItems(items);
//			//request.getSession().setAttribute("shoppingCart", new ShoppingCart());
//			//WebUtil.addCookie(response, "JSESSIONID", request.getSession().getId(), request.getSession().getMaxInactiveInterval());
//			}
		if(shoppingCart.getShoppingCartItems()==null||shoppingCart.getShoppingCartItems().size()==0)
			shoppingCartItem.setId(0);
		else 
			shoppingCartItem.setId(shoppingCart.getShoppingCartItems().size());*/
			//ShoppingCart shoppingCart = 
			//List l =shoppingCart.getShoppingCartItems();
			//shoppingCart.setShoppingCartItems(l);
			//2010.12.20 22:19注释
			//request.getSession().setAttribute("shoppingCart",shoppingCart);
			//WebUtil.addCookie(response, "JSESSIONID", request.getSession().getId(), request.getSession().getMaxInactiveInterval());
		
		//request.getSession().setAttribute("shoppingCart",shoppingCart);
		//shoppingCartManager.addItem(scif.getProductId(), scif.getImagesAndStyleId(), scif.getSizeId(), scif.getAmount());
		
	}
	private void addToCart(ActionMapping mapping, ShoppingCartItemForm scif,
			HttpServletRequest request, HttpServletResponse response) {
	//	ShoppingCartItemForm scif =(ShoppingCartItemForm)form;

		initShoppingCart(request,response);

		ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
		ImagesAndStyle style = new ImagesAndStyle();
		style.setId(scif.getImagesAndStyleId());
		shoppingCartItem.setAmount(scif.getAmount());
		Set<AttributeOption> attributes = new HashSet<AttributeOption>();
		int[] atts = scif.getOption();
		if(atts!=null)
		for(int i=0;i<atts.length;i++){
			if(atts[i]>0){
			//2011.3.5 9:50 下面是使用Cache的方法	attributes.add(shoppingCartManager.getAttributeOption(atts[i]));
				AttributeOption  option = (AttributeOption)CacheOperation.getInstance().getCacheData(
						shoppingCartManager,"getAttributeOption", new Object[]{atts[i]},
						cacheTime, cacheVisit);
				attributes.add(option);
			}
		}
		
		//2011.3.5 9:50 下面是使用Cache的方法		Product p =shoppingCartManager.loadProduct(scif.getProductId());
		Product p =(Product)CacheOperation.getInstance().getCacheData(
				shoppingCartManager,"loadProduct",new Object[]{scif.getProductId()},
				cacheTime, 
				cacheVisit);

		//p.setOptions(attributes);
		shoppingCartItem.setAttributes(attributes);
		shoppingCartItem.setProduct(p);
		ShoppingCart shoppingCart = (ShoppingCart) request.getSession().getAttribute("shoppingCart");
		shoppingCartItem.setId(shoppingCart.getShoppingCartItems().size());
		shoppingCart.addItem(shoppingCartItem, scif.getAmount());

	}	
	public ActionForward updateAll(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ShoppingCart shoppingCart = (ShoppingCart) request.getSession().getAttribute("shoppingCart");
		String directUrl = request.getParameter("directUrl");
		if(directUrl!=null && directUrl.trim().length()>0){
			request.setAttribute("directUrl",directUrl);
		}
		if(shoppingCart == null ||shoppingCart.getShoppingCartItems().size()== 0 ){//|| pg.getDatas().size()==0){
			
			initShoppingCart(request,response);
			
			return  mapping.findForward("index");
		}
		ShoppingCartItemForm scif = (ShoppingCartItemForm) form;
		
		List items = shoppingCart.getShoppingCartItems();
		Iterator its = items.iterator();
		int tempi = 0;
		while(its.hasNext()){
			
			((ShoppingCartItem) its.next()).setAmount(scif.getAmounts()[tempi]);
			//shoppingCart.setPrices();
			//item.setAmount(scif.getAmounts()[tempi]);
			tempi++;
		}
		//request.getSession().setAttribute("shoppingCart",shoppingCart);
		shoppingCart.setPrices();
		return mapping.findForward("index");
	//	shoppingCart.setShoppingCartItems(pg.getDatas());
		/*
		int number=0;
		try {
			number = Integer.valueOf(request.getParameter("number"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			throw new SystemException("请不要试图提交非法参数!");
		}
		if(number==0){
			return mapping.findForward("index");
		}*/
		/*
		Enumeration m = request.getAttributeNames();

		while(m.hasMoreElements()){
			String jj =(String) m.nextElement();
			System.out.println(jj);
		}
		ShoppingCartItemForm sss= (ShoppingCartItemForm)request.getAttribute("shoppingCartItemForm");
		System.out.print(sss);
		List<ShoppingCartItemForm> scifs = new ArrayList<ShoppingCartItemForm>(scif.getAmounts().length);
		Iterator i = scifs.iterator();
		while(i.hasNext()){
			ShoppingCartItemForm s=(ShoppingCartItemForm)i.next();
			//shoppingCartManager.addItem(s.getProductId(), s.getImagesAndStyleId(), s.getSizeId(), s.getAmount());
			//shoppingCart.getShoppingCartItems().s
			for(ShoppingCartItem sci :shoppingCart.getShoppingCartItems()){
				if(sci.equals(s))
				sci.setAmount(s.getAmount());
			}
		}
		*/

	}
	
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	//	System.out.print(request.getParameter("productId"));
		int productId = Integer.valueOf((String)request.getParameter("productId"));
		//int imagesAndStyleId = Integer.valueOf((String)request.getParameter("imagesAndStyleId"));
		 int itemId = Integer.valueOf((String)request.getParameter("itemId"));
		ShoppingCartItem sci = new ShoppingCartItem();
	//		ImagesAndStyle i = new ImagesAndStyle();
			//i.setId(imagesAndStyleId);
			Product p = new Product();
			p.setId(productId);
	//	sci.setImagesAndStyleId(i);
		sci.setProduct(p);
	//	System.out.println(sci.getProductId().getId());
		ShoppingCart shoppingCart = (ShoppingCart) request.getSession().getAttribute("shoppingCart");
		List<ShoppingCartItem> items = (List<ShoppingCartItem>)	shoppingCart.getShoppingCartItems();
		if(items!=null){
			if(items.size()>0)
		for(ShoppingCartItem it :items){
	//		System.out.println("it.getId()="+it.getId()+"itemId="+itemId+"相等："+(it.getId()==itemId));
			if(it.getId()==itemId) {
	//			System.out.println("开始删除..");
	//			System.out.println("it.getId()="+it.getId()+"itemId="+itemId+"相等："+(it.getId()==itemId));
			//if(it.getId()==itemId)
				items.remove(it);
	//			System.out.println("删除OK..");
				break;
			}
		}
		}
		shoppingCart.setPrices();
		/*if(items.contains(sci)){
			items.remove(sci);
		}*/
	//	request.getSession().setAttribute("shoppingCart", shoppingCart);
		request.setAttribute("directUrl", "user/shoppingcart.do");
		return mapping.findForward("redirectTo");
	}
	
	public ActionForward deleteAll(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		clearShoppingCart(request, response);
		return mapping.findForward("index");
	}

	public ActionForward  settleAccounts(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String url = (String) request.getParameter("directUrl");
		//System.out.println("shoppingcartaction line 229--加密后的url："+url);
		if(url!=null && url.length()>0){
			request.setAttribute("directUrl",WebUtil.decodeStringByBase64(url));
		}else{
			request.setAttribute("directUrl", "customer/deliver.do");
		}
		return mapping.findForward("redirectTo");

	}
	private void initShoppingCart(HttpServletRequest request, HttpServletResponse response){
		ShoppingCart shoppingCart = (ShoppingCart) request.getSession(false).getAttribute("shoppingCart");

		if(shoppingCart!=null){
			return;
		}
		shoppingCart = new ShoppingCart();
		if(shoppingCart.getShoppingCartItems()==null){
			List<ShoppingCartItem> items = new ArrayList<ShoppingCartItem>();
		}
		request.getSession().setAttribute("shoppingCart", shoppingCart);
		WebUtil.addCookie(response, "JSESSIONID", request.getSession().getId(), request.getSession().getMaxInactiveInterval());
			
/*		if(shoppingCart.getShoppingCartItems().size()==0){
			
			ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
			shoppingCartItem.setId(0);
			shoppingCart.addItem(shoppingCartItem,0);
			//items.add(shoppingCartItem);
			//shoppingCart.setShoppingCartItems(items);
			
		}*/
		
	}
	private void clearShoppingCart(HttpServletRequest request, HttpServletResponse response){
		initShoppingCart(request,response);
		ShoppingCart shoppingCart = new ShoppingCart();
	    request.getSession().setAttribute("shoppingCart",shoppingCart);
		//shoppingCart.getShoppingCartItems().clear();
	}
//	private void initShoppingCart(ActionMapping mapping,HttpServletRequest request, HttpServletResponse response){
//		ShoppingCart shoppingCart = new ShoppingCart();
//		request.getSession().setAttribute("shoppingCart", shoppingCart);
//		WebUtil.addCookie(response, "JSESSIONID", request.getSession().getId(), request.getSession().getMaxInactiveInterval());
//		//return mapping.findForward("index");
//	}
}
