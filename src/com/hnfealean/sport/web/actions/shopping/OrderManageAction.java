package com.hnfealean.sport.web.actions.shopping;

import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.hnfealean.sport.managers.area.CountryManager;
import com.hnfealean.sport.managers.area.ZoneManager;
import com.hnfealean.sport.managers.deliver.DeliverTypeManager;
import com.hnfealean.sport.managers.product.ProductManager;
import com.hnfealean.sport.managers.shopping.OrderManager;
import com.hnfealean.sport.managers.shopping.OrderStatusManager;
import com.hnfealean.sport.managers.shopping.PaymentMethodManager;
import com.hnfealean.sport.model.deliver.DeliverType;
import com.hnfealean.sport.model.product.AttributeOption;
import com.hnfealean.sport.model.product.Product;
import com.hnfealean.sport.model.shopping.DeliverInfo;
import com.hnfealean.sport.model.shopping.Order;
import com.hnfealean.sport.model.shopping.OrderItem;
import com.hnfealean.sport.model.shopping.OrderItemAttribute;
import com.hnfealean.sport.model.shopping.PaymentAndDeliverMethod;
import com.hnfealean.sport.model.shopping.PaymentMethod;
import com.hnfealean.sport.model.user_acl_module.Administrator;
import com.hnfealean.sport.model.user_acl_module.ContactInfo;
import com.hnfealean.sport.pageset.PageModel;
import com.hnfealean.sport.pageset.SystemContext;
import com.hnfealean.sport.web.SystemException;
import com.hnfealean.sport.web.WebUtil;
import com.hnfealean.sport.web.forms.shopping.OrderForm;
import com.sun.org.apache.commons.beanutils.BeanUtils;

public class OrderManageAction extends DispatchAction {
	private OrderManager orderManager;
	private ZoneManager zoneManager;
	private CountryManager countryManager;
	private DeliverTypeManager deliverTypeManager;
	private PaymentMethodManager paymentMethodManager;
	
	private ProductManager productManager;
	private OrderStatusManager orderStatusManager;

	public void setOrderStatusManager(OrderStatusManager orderStatusManager) {
		this.orderStatusManager = orderStatusManager;
	}
	public void setProductManager(ProductManager productManager) {
		this.productManager = productManager;
	}

	public void setDeliverTypeManager(DeliverTypeManager deliverTypeManager) {
		this.deliverTypeManager = deliverTypeManager;
	}

	public void setPaymentMethodManager(PaymentMethodManager paymentMethodManager) {
		this.paymentMethodManager = paymentMethodManager;
	}

	public void setZoneManager(ZoneManager zoneManager) {
		this.zoneManager = zoneManager;
	}

	public void setCountryManager(CountryManager countryManager) {
		this.countryManager = countryManager;
	}

	public void setOrderManager(OrderManager orderManager) {
		this.orderManager = orderManager;
	}

	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PageModel pm ;
		OrderForm of =(OrderForm)form;
		if(of==null||of.getOrderState()==null){
		pm=	orderManager.searchOrdersByState("");
		}else{
		 pm= orderManager.searchOrdersByState(of.getOrderState());
		}
		request.setAttribute("pm", pm);
		return mapping.findForward("index");
	}
	public ActionForward getLockOrder(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	

		String orderId=request.getParameter("orderId").toString().trim();
		if(orderId==null||orderId.length()==0){
			throw new SystemException("非法参数");
		}
		Administrator admin  = WebUtil.getAdministrator(request);
		Order order = orderManager.updateAndGetLockOrder(orderId, admin.getName());
		if(!order.getAdministratorName().equals(admin.getName())){
			request.setAttribute("message", "订单被!"+order.getAdministratorName()+"锁定");
			request.setAttribute("urladdress", "control/shopping/ordermanage.do");
			return mapping.findForward("success");
		}
		ContactInfo contactInfo = orderManager.getContactInfo(order.getUser().getId());
		request.setAttribute("order", order);
		request.setAttribute("contactInfo", contactInfo);
		request.setAttribute("orderStatuses", orderStatusManager.getAllOrderStatus());
		return mapping.findForward("orderdetail");
		
	}
	public ActionForward searchInput(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
				throws Exception {
		
		return mapping.findForward("searchInput");
		
	}
	public ActionForward search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
				throws Exception {
		
		OrderForm of =(OrderForm)form;
		if(of==null)throw new SystemException("非法参数，请输入查询条件");
		StringBuffer sb = new StringBuffer();
		sb.append("where ");
		if(of.getOrderId()!=null&&of.getOrderId().trim().length()>0) 
			sb.append("orderId like '%"+of.getOrderId().trim()+"'% and ");
		if(of.getOrderState()!=null&&of.getOrderState().trim().length()>0){
			sb.append("orderState='"+of.getOrderState()+"' and ");
		}
		if(of.getUserName()!=null&&of.getUserName().trim().length()>0){
			sb.append("customerName like '%"+of.getUserName().trim()+"%' and ");
		}
		if(of.getUserEmail()!=null&&of.getUserEmail().trim().length()>0){
			sb.append("customerName like '%"+of.getUserEmail().trim()+"%' and ");
		}
		//if(of.getDeliverName()!=null&&of.)
		
		//sb.append("createDate like '%"+date+"%' and ");
		if(of.getDate()!=null&&Pattern.matches("^\\d{4}-\\d{2}-\\d{2}$", of.getDate())){
			sb.append("createDate like '%"+of.getDate()+"%' and ");
		}
		sb.delete(sb.length()-4, sb.length());
		//System.out.println(sb.toString());
		PageModel pm = orderManager.searchOrders(sb.toString().length()>6?sb.toString():"", SystemContext.getPage(), SystemContext.getPagesize());
		request.setAttribute("pm", pm);
		return mapping.findForward("searchlist");
	}
	public ActionForward editDeliverInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
				throws Exception {
		OrderForm of =(OrderForm)form;
		if(of.getOrderId()==null||of.getOrderId().trim().length()==0){
			throw new SystemException("非法访问！");
		}
		Order order = orderManager.getOrder(of.getOrderId().trim());
		if(order==null){
			throw new SystemException("找不到指定的订单！");
		}
		request.setAttribute("order", order);
		return mapping.findForward("editDeliverInfo");
		
	}		
	public ActionForward updateDeliverInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
				throws Exception {
		OrderForm of =(OrderForm)form;
		if(of.getOrderId()==null||of.getOrderId().trim().length()==0){
			throw new SystemException("非法访问！");
		}
		Order order = orderManager.getOrder(of.getOrderId().trim());
		if(order==null){
			throw new SystemException("找不到指定的订单！");
		}
		DeliverInfo deliverInfo =order.getDeliverInfo();
		BeanUtils.copyProperties(deliverInfo, of);
		if(of.getCountry()>0)
		deliverInfo.setCountry(countryManager.getCountryById(of.getCountry()).getCnName());
		if(of.getState()>0)
			deliverInfo.setState(zoneManager.getZoneById(of.getState()).getName());
		if(of.getCity()>0)
			deliverInfo.setCity(zoneManager.getZoneById(of.getCity()).getName());
		if(of.getCounty()>0)
			deliverInfo.setCounty(zoneManager.getZoneById(of.getCounty()).getName());
		orderManager.updateDeliverInfo(deliverInfo, order.getOrderId());
		request.setAttribute("message", "订单配送信息修改成功");
		request.setAttribute("urladdress", "control/shopping/ordermanage.do?method=getLockOrder&orderId="+order.getOrderId());
		return mapping.findForward("success");

		
	}
	public ActionForward editDeliverType(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
				throws Exception {
		OrderForm of =(OrderForm)form;
		if(of.getOrderId()==null||of.getOrderId().trim().length()==0){
			throw new SystemException("非法访问！");
		}
		Order order = orderManager.getOrder(of.getOrderId().trim());
		if(order==null){
			throw new SystemException("找不到指定的订单！");
		}
		request.setAttribute("order", order);
		List<DeliverType> deliverTypes = deliverTypeManager.getAllDeliverTypes();
		request.setAttribute("deliverTypes",deliverTypes);
		return mapping.findForward("editDeliverType");
		
	}
	public ActionForward updateDeliverType(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
				throws Exception {
		OrderForm of =(OrderForm)form;
		if(of.getOrderId()==null||of.getOrderId().trim().length()==0){
			throw new SystemException("非法访问！");
		}
		Order order = orderManager.getOrder(of.getOrderId().trim());
		if(order==null){
			throw new SystemException("找不到指定的订单！");
		}
		DeliverType deliverType= deliverTypeManager.getDeliverTypeById(of.getDeliverType());
		if(deliverType==null||deliverType.getId()==0)	throw new SystemException("非法访问！");
		PaymentAndDeliverMethod  m =order.getPaymentAndDeliverMethod();
		m.setDeliverMethod(deliverType.getId());
		m.setDeliverTypeName(deliverType.getName());
		
		orderManager.updatePaymentAndDeliverMethod(m);
		request.setAttribute("message", "支付方式修改成功");
		request.setAttribute("urladdress", "control/shopping/ordermanage.do?method=getLockOrder&orderId="+order.getOrderId());
		return mapping.findForward("success");
		
	}
	public ActionForward editPaymentType(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
				throws Exception {
		OrderForm of =(OrderForm)form;
		if(of.getOrderId()==null||of.getOrderId().trim().length()==0){
			throw new SystemException("非法访问！");
		}
		Order order = orderManager.getOrder(of.getOrderId().trim());
		if(order==null){
			throw new SystemException("找不到指定的订单！");
		}
		request.setAttribute("order", order);
		List<PaymentMethod> paymentMethods = paymentMethodManager.getAllPaymentMethods();
		request.setAttribute("paymentMethods",paymentMethods);
		return mapping.findForward("editPaymentType");
		
	}
	public ActionForward updatePaymentType(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
				throws Exception {
		OrderForm of =(OrderForm)form;
		if(of.getOrderId()==null||of.getOrderId().trim().length()==0){
			throw new SystemException("非法访问！");
		}
		Order order = orderManager.getOrder(of.getOrderId().trim());
		if(order==null){
			throw new SystemException("找不到指定的订单！");
		}
		PaymentMethod p = paymentMethodManager.getPaymentMethod(of.getPaymentMethod());
		if(p==null||p.getId()==0)	throw new SystemException("非法访问！");
		PaymentAndDeliverMethod  m =order.getPaymentAndDeliverMethod();
		m.setPaymentMethod(p.getId());
		m.setPaymentTypeName(p.getName());
		orderManager.updatePaymentAndDeliverMethod(m);
		request.setAttribute("message", "支付方式修改成功");
		request.setAttribute("urladdress", "control/shopping/ordermanage.do?method=getLockOrder&orderId="+order.getOrderId());
		return mapping.findForward("success");
		
	}
	public ActionForward editOrderItemNumber(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
				throws Exception {
		OrderForm of =(OrderForm)form;
		if(of.getOrderId()==null||of.getOrderId().trim().length()==0||of.getOrderItemId()==0){
			throw new SystemException("非法访问！");
		}
		OrderItem item = orderManager.getOrderItem(of.getOrderItemId());
		if(item==null||item.getId()==0)throw new SystemException("非法访问！");
		request.setAttribute("item", item);
		request.setAttribute("orderId", of.getOrderId());
		return mapping.findForward("editOrderItemNumber");
	}
	public ActionForward updateOrderItemNumber(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
				throws Exception {
		OrderForm of =(OrderForm)form;
		if(of.getOrderId()==null||of.getOrderId().trim().length()==0||of.getOrderItemId()==0){
			throw new SystemException("非法访问！");
		}
		//OrderItem orderItem = orderManager.getOrderItem(of.getOrderItemId());
		//if(orderItem==null||orderItem.getId()==0)throw new SystemException("非法访问！");
		Order order = orderManager.getOrder(of.getOrderId().trim());
		if(order==null){
			throw new SystemException("找不到指定的订单！");
		}
		Set<OrderItem> items = order.getItems();
		for(OrderItem item:items){
			if(item.getId()==of.getOrderItemId()){
				item.setAmount(of.getAmount());
				//orderManager.updatePaymentWay(paymentMethod, orderid)
			}
		}
		order.setFee();
		orderManager.updateOrder(order);
		//orderItem.setAmount(of.getAmount());
		
		
		request.setAttribute("message", "订单项数量修改成功");
		request.setAttribute("urladdress", "control/shopping/ordermanage.do?method=getLockOrder&orderId="+of.getOrderId());
		return mapping.findForward("success");

/*		Order order = orderManager.getOrder(of.getOrderId().trim());
		if(order==null){
			throw new SystemException("找不到指定的订单！");
		}
		Set<OrderItem> items = order.getItems();
		for(OrderItem item:items){
			if(item.getId()==of.getOrderItemId()){
				item.setAmount(of.getAmount());
				orderManager.updatePaymentWay(paymentMethod, orderid)
			}
		}*/
		
	}
	public ActionForward deleteOrderItem(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
				throws Exception {
		OrderForm of =(OrderForm)form;
		if(of.getOrderId()==null||of.getOrderId().trim().length()==0){
			throw new SystemException("非法访问！");
		}
		OrderItem orderItem = orderManager.getOrderItem(of.getOrderItemId());
		if(orderItem==null||orderItem.getId()==0)throw new SystemException("非法操作");
		
		Order order = orderManager.getOrder(of.getOrderId());
		if(order==null||order.getOrderId()==null)throw new SystemException("非法操作");
		Set<OrderItem> items = order.getItems();
		items.remove(orderItem);
		order.setFee();
		orderManager.updateOrder(order);
		request.setAttribute("message", "删除成功");
		request.setAttribute("urladdress", "control/shopping/ordermanage.do?method=getLockOrder&orderId="+order.getOrderId());
		return mapping.findForward("success");
	}
	public ActionForward editOrderDeliverFee(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
				throws Exception {
		OrderForm of =(OrderForm)form;
		if(of.getOrderId()==null||of.getOrderId().trim().length()==0){
			throw new SystemException("非法访问！");
		}
		Order order = orderManager.getOrder(of.getOrderId());
		if(order==null||order.getOrderId()==null)throw new SystemException("非法访问！");
		request.setAttribute("order", order);
		return mapping.findForward("editOrderDeliverFee");
	}
	public ActionForward updateOrderDeliverFee(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
				throws Exception {
		OrderForm of =(OrderForm)form;
		if(of.getOrderId()==null||of.getOrderId().trim().length()==0){
			throw new SystemException("非法访问！");
		}
		Order order = orderManager.getOrder(of.getOrderId());
		if(order==null||order.getOrderId()==null)throw new SystemException("非法访问！");
		order.setDeliverFee(of.getDeliverFee());
		//request.setAttribute("order", order);
		order.setFee();
		orderManager.updateOrder(order);
		request.setAttribute("message", "运费更新成功");
		request.setAttribute("urladdress", "control/shopping/ordermanage.do?method=getLockOrder&orderId="+order.getOrderId());
		return mapping.findForward("success");
	}
	public ActionForward editOrderPayAbleFee(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
				throws Exception {
		OrderForm of =(OrderForm)form;
		if(of.getOrderId()==null||of.getOrderId().trim().length()==0){
			throw new SystemException("非法访问！");
		}
		Order order = orderManager.getOrder(of.getOrderId());
		if(order==null||order.getOrderId()==null)throw new SystemException("非法访问！");
		request.setAttribute("order", order);
		return mapping.findForward("editOrderPayAbleFee");
	}
	public ActionForward updateOrderPayAbleFee(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
				throws Exception {
		OrderForm of =(OrderForm)form;
		if(of.getOrderId()==null||of.getOrderId().trim().length()==0){
			throw new SystemException("非法访问！");
		}
		Order order = orderManager.getOrder(of.getOrderId());
		if(order==null||order.getOrderId()==null)throw new SystemException("非法访问！");
		order.changePayAbleFee(of.getPayAbleFee());
		orderManager.updateOrder(order);
		request.setAttribute("message", "订单应付金额修改成功");
		request.setAttribute("urladdress", "control/shopping/ordermanage.do?method=getLockOrder&orderId="+order.getOrderId());
		return mapping.findForward("success");

	}	
	public ActionForward editAttribute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
				throws Exception {
		OrderForm of =(OrderForm)form;
		if(of.getOrderId()==null||of.getOrderId().trim().length()==0||of.getProductId()==0||of.getAttributeOptionId()==0){
			throw new SystemException("非法访问！");
		}
		Product p = productManager.findProduct(of.getProductId());
		if(p==null||p.getId()==0)throw new SystemException("找不到指定的产品！");
		OrderItemAttribute att = orderManager.getOrderItemAttribute(of.getAttributeOptionId());
		if(att==null||att.getId()==0)throw new SystemException("找不到指定的属性！");
		request.setAttribute("product", p);
		request.setAttribute("attribute", att);
		request.setAttribute("orderId", of.getOrderId());
		return mapping.findForward("editAttribute");
		
	}
	public ActionForward updateAttribute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
				throws Exception {
		OrderForm of =(OrderForm)form;
		if(of.getOrderId()==null||of.getOrderId().trim().length()==0||of.getAttributeOptionId()==0||of.getProductOptionId()==0){
			throw new SystemException("非法访问！");
		}
		AttributeOption option = productManager.getAttributeOptionById(of.getProductOptionId());
		if(option==null||option.getId()==0)throw new SystemException("找不到指定的属性！");
		OrderItemAttribute att = orderManager.getOrderItemAttribute(of.getAttributeOptionId());
		if(att==null||att.getId()==0)throw new SystemException("找不到指定的属性！");
		att.setAttributeValue(option.getValue());
		orderManager.updateOrderItemAttribute(att);
		request.setAttribute("message", "订单项属性修改成功");
		request.setAttribute("urladdress", "control/shopping/ordermanage.do?method=getLockOrder&orderId="+of.getOrderId());
		return mapping.findForward("success");
	}
		
}
