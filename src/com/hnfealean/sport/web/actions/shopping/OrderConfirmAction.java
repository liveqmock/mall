package com.hnfealean.sport.web.actions.shopping;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.hnfealean.sport.enums.ConstantString;
import com.hnfealean.sport.managers.deliver.DeliverModuleManager;
import com.hnfealean.sport.managers.deliver.GlobalDeliverModuleManager;
import com.hnfealean.sport.model.deliver.DeliverModule;
import com.hnfealean.sport.model.deliver.GlobalDeliverModule;
import com.hnfealean.sport.model.shopping.DeliverInfo;
import com.hnfealean.sport.model.shopping.PaymentAndDeliverMethod;
import com.hnfealean.sport.model.shopping.ShoppingCart;
import com.hnfealean.sport.model.shopping.ShoppingCartItem;
import com.hnfealean.sport.web.WebUtil;
import com.hnfealean.sport.web.forms.shopping.FrontPayAndDeliverForm;
import com.hnfealean.sport.web.forms.shopping.MessageForm;

public class OrderConfirmAction extends DispatchAction {
	private GlobalDeliverModuleManager globalDeliverModuleManager;
	private DeliverModuleManager deliverModuleManager;


	public void setGlobalDeliverModuleManager(
			GlobalDeliverModuleManager globalDeliverModuleManager) {
		this.globalDeliverModuleManager = globalDeliverModuleManager;
	}

	public void setDeliverModuleManager(DeliverModuleManager deliverModuleManager) {
		this.deliverModuleManager = deliverModuleManager;
	}

	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//savePaymentway(mapping,form,request,response);
		String url= "customer/order-confirm.do";
		url = WebUtil.encodeStringByBase64(url);
		System.out.println(url);
		request.setAttribute("directUrl", url);
		computeDeliverFee(request);
		return mapping.findForward("index");
	}
	
	public ActionForward saveOrder(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		FrontPayAndDeliverForm pmf =(FrontPayAndDeliverForm) form;
		//pmf.get
		return mapping.findForward("index");
	}
	public ActionForward leaveMessage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		MessageForm fof = (MessageForm) form;
		ShoppingCart shoppingCart =(ShoppingCart) request.getSession().getAttribute("shoppingCart");
		shoppingCart.setMessage(fof.getMessage());
		request.getSession().setAttribute("shoppingCart",shoppingCart);
		request.setAttribute("msg", "留言成功！");
		return mapping.findForward("index");
	}	
	private void computeDeliverFee(HttpServletRequest request) {
		ShoppingCart cart = WebUtil.getShoppingCart(true,request);
		DeliverInfo deliverInfo = WebUtil.getDeliverInfo(request);
		PaymentAndDeliverMethod method= WebUtil.getPaymentMethod(request);
		if(cart==null||cart.getShoppingCartItems()==null||cart.getShoppingCartItems().size()==0){
			cart.setDeliveFee(0);
			return;
		}
		GlobalDeliverModule globalDeliverModule =globalDeliverModuleManager.getGlobalDeliverModule(deliverInfo.getState(), method.getDeliverMethod());
		if(globalDeliverModule==null){
			globalDeliverModule =globalDeliverModuleManager.getGlobalDeliverModule(ConstantString.OTHERZONES, method.getDeliverMethod());
		}
		if(globalDeliverModule==null||globalDeliverModule.getFreeShippingOrderMinTotal()<=cart.getTotalSellPrice()){
			cart.setDeliveFee(0);
			return;
			//request.setAttribute("deliverTypes", deliverTypes);
		}
		float globalDeliverFee = globalDeliverModule.getDeliverFee();
		float productFee =0;
		if(globalDeliverModule.getGlobalTemplate().getIgnoreProductDeliverFee()==false){
			//true时为禁用产品本身的运费,不用做操作
			List<ShoppingCartItem> items = cart.getShoppingCartItems();
			for(ShoppingCartItem item:items){
				DeliverModule deliverModule =deliverModuleManager.getDeliverModule(deliverInfo.getState(), method.getDeliverMethod(), item.getProduct().getId());
				if(deliverModule==null){
					deliverModule=deliverModuleManager.getDeliverModule(ConstantString.OTHERZONES, method.getDeliverMethod(), item.getProduct().getId());
					
				}
				float baseFee=0,oneMoreFee=0;
				if(deliverModule!=null) {
					baseFee = deliverModule.getBaseFee();
					oneMoreFee = deliverModule.getOneMoreFee();
				}
				productFee=baseFee+oneMoreFee*item.getAmount();
			}
		}
		cart.setDeliveFee(WebUtil.round(globalDeliverFee+productFee,2,BigDecimal.ROUND_HALF_UP));
		//WebUtil.round(globalDeliverFee+productFee,2,BigDecimal.ROUND_HALF_UP);
		//cart.setDeliveFee(globalDeliverFee+productFee);
	}
}
