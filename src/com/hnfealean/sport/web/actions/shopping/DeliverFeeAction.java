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
import com.hnfealean.sport.managers.deliver.GlobalDeliverModuleManager;
import com.hnfealean.sport.model.deliver.DeliverModule;
import com.hnfealean.sport.model.deliver.GlobalDeliverModule;
import com.hnfealean.sport.model.shopping.DeliverInfo;
import com.hnfealean.sport.model.shopping.PaymentAndDeliverMethod;
import com.hnfealean.sport.model.shopping.ShoppingCart;
import com.hnfealean.sport.model.shopping.ShoppingCartItem;
import com.hnfealean.sport.web.WebUtil;

public class DeliverFeeAction extends DispatchAction {
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
			
			return mapping.findForward("index");
		}
		public ActionForward computefee(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			ShoppingCart cart = WebUtil.getShoppingCart(true,request);
			cart.setDeliveFee(computeFee(request));
			return mapping.findForward("index");
		}


		private float computeFee(HttpServletRequest request) {
			ShoppingCart cart = WebUtil.getShoppingCart(true,request);
			DeliverInfo deliverInfo = WebUtil.getDeliverInfo(request);
			if(cart==null)return 0;
			PaymentAndDeliverMethod method = WebUtil.getPaymentMethod(request);
			GlobalDeliverModule globalDeliverModule =globalDeliverModuleManager.getGlobalDeliverModule(deliverInfo.getState(), method.getDeliverMethod());
			if(globalDeliverModule==null){
				globalDeliverModule =globalDeliverModuleManager.getGlobalDeliverModule(ConstantString.OTHERZONES, method.getDeliverMethod());
			}
			if(globalDeliverModule==null||globalDeliverModule.getFreeShippingOrderMinTotal()<=cart.getTotalSellPrice()){
				cart.setDeliveFee(0);
				return 0;
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
						if(deliverModule==null)
						continue;
					}
					float baseFee = deliverModule.getBaseFee();
					float oneMoreFee = deliverModule.getOneMoreFee();
					productFee=baseFee+oneMoreFee*item.getAmount();
				}
			}
		
			return globalDeliverFee+productFee;
		}
	}