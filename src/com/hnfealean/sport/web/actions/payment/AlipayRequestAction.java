package com.hnfealean.sport.web.actions.payment;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.hnfealean.sport.managers.payment.AlipayManager;
import com.hnfealean.sport.managers.shopping.OrderManager;
import com.hnfealean.sport.model.payment.alipay.AlipayConfiguration;
import com.hnfealean.sport.model.payment.alipay.AlipayRequest;
import com.hnfealean.sport.model.shopping.Order;
import com.hnfealean.sport.payment.alipay.AlipayFunction;
import com.hnfealean.sport.web.SystemException;
import com.hnfealean.sport.web.WebUtil;
import com.hnfealean.sport.web.forms.payment.AlipayForm;

public class AlipayRequestAction extends DispatchAction {

	private AlipayManager alipayManager;	
	private OrderManager orderManager;
	public void setAlipayManager(AlipayManager alipayManager) {
		this.alipayManager = alipayManager;
	}
	public void setOrderManager(OrderManager orderManager) {
		this.orderManager = orderManager;
	}
	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		AlipayConfiguration config = alipayManager.getAlipayConfiguration();
		if(config==null)throw new SystemException("支付宝插件没有配置支付帐号，请联系管理员！");
	//System.out.println(AlipayConfig.key);
	//System.out.println(AlipayConfig.partner);
	AlipayForm af =(AlipayForm) form;
	if(af.getOrderId()==null||af.getOrderId().trim().length()==0)throw new SystemException("非法访问！");
	int userId = WebUtil.getUserId(request);
	if(userId==0)	throw new SystemException("非法访问，请登录!");
	Order order = orderManager.getOrder(af.getOrderId().trim());
	if(order.getUser().getId()!=userId)
		throw new SystemException("只有该订单所属客户才可访问此订单！");
	if(order.getPaymentState()==true)		throw new SystemException("该订单已经支付！");
	AlipayRequest alipayRequest = new AlipayRequest();
	alipayRequest.setBuyer_email("");
	alipayRequest.setDiscount("");
	alipayRequest.setKey(config.getKey());
	alipayRequest.setLogistics_fee_1("");
	alipayRequest.setLogistics_fee_2("");
	alipayRequest.setLogistics_payment_1("");
	alipayRequest.setLogistics_payment_2("");
	alipayRequest.setLogistics_type_1("");
	alipayRequest.setLogistics_type_2("");
	alipayRequest.setPartner(config.getPartner());
	alipayRequest.setSeller_email(config.getSeller_email());
	alipayRequest.setSign_type(config.getSign_type());
	alipayRequest.setSubject("angelinthebox"+order.getOrderId());
	alipayRequest.set_input_charset(config.getInput_charset());
	alipayRequest.setAlipaysubmit(config.getAlipaysubmit());
	alipayRequest.setBody("nobody");
	alipayRequest.setLogistics_fee("0.00");
	alipayRequest.setLogistics_payment("SELLER_PAY");//物流支付方式，两个值可选：SELLER_PAY（卖家承担运费）、BUYER_PAY（买家承担运费）
	alipayRequest.setLogistics_type("EXPRESS");//物流类型，三个值可选：EXPRESS（快递）、POST（平邮）、EMS（EMS）
	alipayRequest.setNotify_url(config.getNotify_url());
	alipayRequest.setOut_trade_no(af.getOrderId());
	alipayRequest.setPayment_type("1");
	alipayRequest.setPrice(order.getTotalOrderPrice()+"");
	alipayRequest.setQuantity("1");
	alipayRequest.setReceive_address("");
	alipayRequest.setReceive_mobile("");
	alipayRequest.setReceive_name("");
	alipayRequest.setReceive_phone("");
	alipayRequest.setReceive_zip("");
	alipayRequest.setReturn_url(config.getReturn_url());
	alipayRequest.setService(config.getPaymentType());
	alipayRequest.setShow_url(config.getShow_url());
	alipayRequest.setSign(AlipayFunction.generateSign(alipayRequest));
	request.setAttribute("alipayRequest", alipayRequest);
	//alipayRequest.setSign("");
	alipayManager.addAlipayRequest(alipayRequest);
	return mapping.findForward("gotoalipay");	
	}
	//public ActionForward gotoalipay(ActionMapping mapping, ActionForm form,
	//HttpServletRequest request, HttpServletResponse response)
	//throws Exception {
	//}	
//
//	public ActionForward response(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response)
//			throws Exception {
//		return mapping.findForward("response");	
//	}	
}
