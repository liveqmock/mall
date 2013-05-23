package com.hnfealean.sport.web.actions.shopping;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.hnfealean.sport.enums.ConstantString;
import com.hnfealean.sport.managers.deliver.DeliverModuleManager;
import com.hnfealean.sport.managers.deliver.DeliverTypeManager;
import com.hnfealean.sport.managers.deliver.GlobalDeliverModuleManager;
import com.hnfealean.sport.managers.shopping.PaymentMethodManager;
import com.hnfealean.sport.model.deliver.DeliverModule;
import com.hnfealean.sport.model.deliver.DeliverType;
import com.hnfealean.sport.model.deliver.GlobalDeliverModule;
import com.hnfealean.sport.model.shopping.DeliverInfo;
import com.hnfealean.sport.model.shopping.PaymentAndDeliverMethod;
import com.hnfealean.sport.model.shopping.PaymentMethod;
import com.hnfealean.sport.model.shopping.ShoppingCart;
import com.hnfealean.sport.model.shopping.ShoppingCartItem;
import com.hnfealean.sport.web.SystemException;
import com.hnfealean.sport.web.WebUtil;
import com.hnfealean.sport.web.forms.shopping.FrontPayAndDeliverForm;
import com.sun.org.apache.commons.beanutils.BeanUtils;

public class FrontPayAndDeliverAction extends DispatchAction {
	private PaymentMethodManager paymentMethodManager;
	private GlobalDeliverModuleManager globalDeliverModuleManager;
	private DeliverTypeManager deliverTypeManager;

	private DeliverModuleManager deliverModuleManager;
	public void setDeliverTypeManager(DeliverTypeManager deliverTypeManager) {
		this.deliverTypeManager = deliverTypeManager;
	}

	public void setGlobalDeliverModuleManager(
			GlobalDeliverModuleManager globalDeliverModuleManager) {
		this.globalDeliverModuleManager = globalDeliverModuleManager;
	}

	

	public void setPaymentMethodManager(PaymentMethodManager paymentMethodManager) {
		this.paymentMethodManager = paymentMethodManager;
	}

	public void setDeliverModuleManager(DeliverModuleManager deliverModuleManager) {
		this.deliverModuleManager = deliverModuleManager;
	}

	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		estimateFees(request);
		//List<DeliverType> deliverTypes = deliverTypeManager.getAllDeliverTypes();
		//request.setAttribute("deliverTypes", deliverTypes);
		//PaymentMethodForm pmf =(PaymentMethodForm) form;
		List<PaymentMethod> paymentMethods = paymentMethodManager.getAllPaymentMethods();
		request.setAttribute("paymentMethods", paymentMethods);
		return mapping.findForward("index");
	}
	public ActionForward savePaymentway(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FrontPayAndDeliverForm pmf =(FrontPayAndDeliverForm) form;
		if(pmf==null){
			throw new SystemException("请不要试图违规操作！");
		}
		PaymentAndDeliverMethod paymentAndDeliverMethod=new PaymentAndDeliverMethod();
		BeanUtils.copyProperties(paymentAndDeliverMethod, pmf);
		PaymentMethod method = paymentMethodManager.getPaymentMethod(pmf.getPaymentMethod());
		if(method==null||method.getId()==0){	throw new SystemException("请不要试图违规操作！");}
		paymentAndDeliverMethod.setPaymentTypeName(method.getName());
		DeliverType deliverType = deliverTypeManager.getDeliverTypeById(paymentAndDeliverMethod.getDeliverMethod());
		if(deliverType==null||deliverType.getId()==0){	throw new SystemException("请不要试图违规操作！");}
		paymentAndDeliverMethod.setDeliverTypeName(deliverType.getName());
		request.getSession().setAttribute("paymentMethod", paymentAndDeliverMethod);
		request.setAttribute("directUrl", "customer/order-confirm.do");
		return mapping.findForward("redirectTo");
	}
	
	private void estimateFees(HttpServletRequest request) {
		ShoppingCart cart = WebUtil.getShoppingCart(true,request);
		DeliverInfo deliverInfo = WebUtil.getDeliverInfo(request);
		if(cart==null||cart.getShoppingCartItems()==null||cart.getShoppingCartItems().size()==0){
			List<DeliverType> deliverTypes = deliverTypeManager.getAllDeliverTypes();
			request.setAttribute("deliverTypes", deliverTypes);
			return;
		}
		List<DeliverType> deliverTypes = deliverTypeManager.getAllDeliverTypes();
		for(DeliverType type:deliverTypes){
			GlobalDeliverModule globalDeliverModule =globalDeliverModuleManager.getGlobalDeliverModule(deliverInfo.getState(), type.getId());
		if(globalDeliverModule==null){
			globalDeliverModule =globalDeliverModuleManager.getGlobalDeliverModule(ConstantString.OTHERZONES, type.getId());
		}
		if(globalDeliverModule==null||globalDeliverModule.getFreeShippingOrderMinTotal()<=cart.getTotalSellPrice()){
			//cart.setDeliveFee(0);
			type.setFee(0);
			continue;
			//request.setAttribute("deliverTypes", deliverTypes);
		}
		float globalDeliverFee = globalDeliverModule.getDeliverFee();
		float productFee =0;
		if(globalDeliverModule.getGlobalTemplate().getIgnoreProductDeliverFee()==false){
			//true时为禁用产品本身的运费,不用做操作
			List<ShoppingCartItem> items = cart.getShoppingCartItems();
			for(ShoppingCartItem item:items){
				DeliverModule deliverModule =deliverModuleManager.getDeliverModule(deliverInfo.getState(),  type.getId(), item.getProduct().getId());
				if(deliverModule==null){
					deliverModule=deliverModuleManager.getDeliverModule(ConstantString.OTHERZONES, type.getId(), item.getProduct().getId());
					
				}
				float baseFee=0,oneMoreFee=0;
				if(deliverModule!=null) {
					baseFee = deliverModule.getBaseFee();
					oneMoreFee = deliverModule.getOneMoreFee();
				}
				productFee=baseFee+oneMoreFee*item.getAmount();
			}
		}
		type.setFee(globalDeliverFee+productFee);
		}
		request.setAttribute("deliverTypes", deliverTypes);
	}

}
