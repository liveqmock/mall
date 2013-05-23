package com.hnfealean.sport.web.actions.shopping;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.hnfealean.sport.managers.shopping.FrontOrderManager;
import com.hnfealean.sport.managers.shopping.PaymentMethodManager;
import com.hnfealean.sport.model.shopping.DeliverInfo;
import com.hnfealean.sport.model.shopping.Order;
import com.hnfealean.sport.model.shopping.PaymentAndDeliverMethod;
import com.hnfealean.sport.model.shopping.PaymentMethod;
import com.hnfealean.sport.model.shopping.ShoppingCart;
import com.hnfealean.sport.model.shopping.ShoppingCartItem;
import com.hnfealean.sport.pageset.PageModel;
import com.hnfealean.sport.web.SystemException;
import com.hnfealean.sport.web.WebUtil;
public class FrontOrderAction extends DispatchAction {
	private PaymentMethodManager paymentMethodManager;
	public void setPaymentMethodManager(PaymentMethodManager paymentMethodManager) {
		this.paymentMethodManager = paymentMethodManager;
	}
	private FrontOrderManager frontOrderManager;

	public void setFrontOrderManager(FrontOrderManager frontOrderManager) {
		this.frontOrderManager = frontOrderManager;
	}

	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("index");
	}
	public ActionForward saveOrder(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		ShoppingCart shoppingCart =(ShoppingCart) request.getSession().getAttribute("shoppingCart");
		if(shoppingCart==null||shoppingCart.getShoppingCartItems()==null||shoppingCart.getShoppingCartItems().size()==0){
			response.reset();
			response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
			response.setHeader("Location","/user/shoppingcart.do");
			return null;
	
		}
		DeliverInfo deliverInfo =(DeliverInfo)request.getSession().getAttribute("deliverInfo");
		if(deliverInfo==null){
			response.reset();
		response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
		response.setHeader("Location","/customer/deliver.do");
		return null;
		}
		PaymentAndDeliverMethod paymentAndDeliverMethod =(PaymentAndDeliverMethod) request.getSession().getAttribute("paymentMethod");
		if(paymentAndDeliverMethod==null||
				paymentAndDeliverMethod.getPaymentMethod()==0||
				paymentAndDeliverMethod.getDeliverMethod()==0){
			response.reset();
			response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
			response.setHeader("Location","/customer/payment-method.do");
			return null;
		}
		PaymentMethod method = paymentMethodManager.getPaymentMethod(paymentAndDeliverMethod.getPaymentMethod());
		if(method==null||method.getId()==0){
			throw new SystemException("系统出错！");
		}

		//	String userName = WebUtil.getUserName(request);
		Order order = new Order();
		order.setDeliverMethod((String)request.getSession().getAttribute("deliverMethod"));
		order.setTimeLimit((String)request.getSession().getAttribute("timeLimit"));
		order =	frontOrderManager.addOrder(shoppingCart, order, deliverInfo,WebUtil.getUserId(request),paymentAndDeliverMethod);
		request.getSession().setAttribute("orderId", order.getOrderId());
		request.getSession().setAttribute("shouldToPay",order.getTotalOrderPrice());
		
		
		shoppingCart = new ShoppingCart();
		List<ShoppingCartItem> items = new ArrayList<ShoppingCartItem>();
		
		shoppingCart.setShoppingCartItems(items);
		request.getSession().setAttribute("shoppingCart",shoppingCart);
		//request.getSession().setAttribute("shoppingCart", new ShoppingCart());
		//WebUtil.addCookie(response, "JSESSIONID", request.getSession().getId(), request.getSession().getMaxInactiveInterval());
		
	
		//ShoppingCart shoppingCart = 
		//List l =shoppingCart.getShoppingCartItems();
		//shoppingCart.setShoppingCartItems(l);
		//shoppingCart.addItem(shoppingCartItem,2);
		request.setAttribute("payment", method);
/*
		if(paymentAndDeliverMethod.getPaymentMethod()==1){
			return mapping.findForward("net");//在线支付
		}else if(paymentAndDeliverMethod.getPaymentMethod()==2){
			return mapping.findForward("cod");//货到付款
		}else if(paymentAndDeliverMethod.getPaymentMethod()==3){
			return mapping.findForward("bankremittance");//银行电汇
		}else if(paymentAndDeliverMethod.getPaymentMethod()==4){
			return mapping.findForward("postofficeremittance");//邮局汇款
		}*/
		return mapping.findForward("finish");
	}
	public ActionForward searchAllOrders(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String userName = WebUtil.getUserName(request);
		PageModel pm = frontOrderManager.searchAllOrders(userName);
		request.setAttribute("pm", pm);
		return mapping.findForward("ordersList");
	}

}
