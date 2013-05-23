package com.hnfealean.sport.web.actions.payment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.hnfealean.sport.managers.payment.YeepayManager;
import com.hnfealean.sport.managers.shopping.OrderManager;
import com.hnfealean.sport.model.payment.yeepay.YeepayRequest;
import com.hnfealean.sport.model.payment.yeepay.YeepayResponse;
import com.hnfealean.sport.model.shopping.Order;
import com.hnfealean.sport.payment.yeepay.YeepayConfigInfo;
import com.hnfealean.sport.payment.yeepay.YeepayUtil;
import com.hnfealean.sport.web.SystemException;
import com.hnfealean.sport.web.WebUtil;
import com.hnfealean.sport.web.forms.payment.YeepayForm;

public class YeepayAction extends DispatchAction {
	private YeepayManager yeepayManager;
	private OrderManager orderManager;
	public void setYeepayManager(YeepayManager yeepayManager) {
		this.yeepayManager = yeepayManager;
	}
	public void setOrderManager(OrderManager orderManager) {
		this.orderManager = orderManager;
	}
	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("GBK");
		YeepayForm yf = (YeepayForm) form;
		if(yf.getOrderId()==null||yf.getOrderId().trim().length()==0)throw new SystemException("非法访问！");
		int userId = WebUtil.getUserId(request);
		if(userId==0)	throw new SystemException("非法访问，请登录!");
		Order order = orderManager.getOrder(yf.getOrderId().trim());
		if(order.getUser().getId()!=userId)
			throw new SystemException("只有该订单所属客户才可访问此订单！");
		if(order.getPaymentState()==true)		throw new SystemException("该订单已经支付！");
		
		
	//	YeepayRequest yeepayRequest = new YeepayRequest();
		request.setAttribute("order", order);
		return mapping.findForward("index");	
	}
	
	public ActionForward gotoyeepay(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		YeepayForm yf = (YeepayForm) form;
		if(yf.getOrderId()==null||yf.getOrderId().trim().length()==0)throw new SystemException("非法访问！");
		int userId = WebUtil.getUserId(request);
		if(userId==0)	throw new SystemException("非法访问，请登录!");
		Order order = orderManager.getOrder(yf.getOrderId().trim());
		if(order.getUser().getId()!=userId)
			throw new SystemException("只有该订单所属客户才可访问此订单！");
		if(order.getPaymentState()==true)		throw new SystemException("该订单已经支付！");
		//p0_Cmd,p1_MerId,p2_Order,p3_Amt,p4_Cur,p5_Pid,p6_Pcat,p7_Pdesc,p8_Url,p9_saf,pa_MP,pd_FrpId,pr_NeedResponse,keyValue,
		YeepayRequest yeepayRequest = new YeepayRequest(
				"Buy", 
				YeepayConfigInfo.getValue("p1_MerId"),  
				order.getOrderId(),
				order.getTotalOrderPrice()+"",
				YeepayConfigInfo.getValue("CUR"),	
				"",
				"",
				"",
				YeepayConfigInfo.getValue("merchantCallbackURL"),
				"0",
				"",
				"0", 
				yf.getFrpId(), 
				YeepayConfigInfo.getValue("keyValue")
											);
		String md5hMac = YeepayUtil.buildHmac(
				yeepayRequest.getP0_Cmd(), 
				yeepayRequest.getP1_MerId(), 
				yeepayRequest.getP2_Order(), 
				yeepayRequest.getP3_Amt(), 
				yeepayRequest.getP4_Cur(), 
				yeepayRequest.getP5_Pid(), 
				yeepayRequest.getP6_Pcat(), 
				yeepayRequest.getP7_Pdesc(), 
				yeepayRequest.getP8_Url(), 
				yeepayRequest.getP9_SAF(), 
				yeepayRequest.getPa_MP(), 
				yeepayRequest.getPd_FrpId(), 
				yeepayRequest.getPr_NeedResponse(), 
				yeepayRequest.getKeyValue()
				);
		yeepayRequest.setMd5hmac(md5hMac);
		yeepayManager.addYeepayRequest(yeepayRequest);
		request.setAttribute("yeepayRequest", yeepayRequest);
		return mapping.findForward("gotoyeepay");	
		
//		YeepayUtil.buildHmac(
//				"Buy", YeepayConfigInfo.getValue("p1_MerId"), order.getOrderId(), (order.getPayAbleFee())+"", "CNY",
//				"", "", "", YeepayConfigInfo.getValue("merchantCallbackURL"), "0", "", "0", yf.getFrpId(), 
//				YeepayConfigInfo.getValue("keyValue")
//				);
		
	
	}
	public ActionForward response(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		request.setCharacterEncoding("GBK");
		YeepayForm yf = (YeepayForm) form;
		if(yf.getOrderId()==null||yf.getOrderId().trim().length()==0)throw new SystemException("非法访问！");
		Order order = orderManager.getOrder(yf.getOrderId().trim());
		if(order.getPaymentState()==true)		throw new SystemException("该订单已经支付！");
//		String merchantID = YeepayConfigInfo.getValue("p1_MerId"); // 商家ID
//		String keyValue = YeepayConfigInfo.getValue("keyValue"); // 商家密钥
//		
//		String sCmd = request.getParameter("r0_Cmd"); //业务类型
//		String sResultCode = request.getParameter("r1_Code"); //扣款结果,该字段值为1时表示扣款成功.
//		String sTrxId = request.getParameter("r2_TrxId"); //YeePay易宝交易订单号
//		String amount = request.getParameter("r3_Amt");//扣款金额,交易结束后,YeePay易宝交易系统将实际扣款金额返回给商户
//		String currency = request.getParameter("r4_Cur");//交易币种,人民币为CNY
//		String productId = request.getParameter("r5_Pid");//商品ID
//		String orderId = request.getParameter("r6_Order");//商户订单号
//		String userId = request.getParameter("r7_Uid");//YeePay易宝会员ID
//		String mp  = request.getParameter("r8_MP");//商户扩展信息,可以任意填写1K 的字符串,交易返回时将原样返回
//		String bType = request.getParameter("r9_BType");//交易结果通知类型,1: 交易成功回调(浏览器重定向)2: 交易成功主动通知(服务器点对点通讯)
//		String rb_BankId  = request.getParameter("rb_BankId");//支付银行
//		String rp_PayDate = request.getParameter("rp_PayDate");//在银行支付时的时间
//		String hmac = request.getParameter("hmac");//MD5交易签名
		
		YeepayResponse yeepayResponse = new YeepayResponse(
				yf.getHmac(), 
				YeepayConfigInfo.getValue("p1_MerId"), 
				yf.getR0_Cmd(), 
				yf.getR1_Code(), 
				yf.getR2_TrxId(),
				yf.getR3_Amt(),
				yf.getR4_Cur(), 
				yf.getR5_Pid(), 
				yf.getR6_Order(), 
				yf.getR7_Uid(), 
				yf.getR8_MP(), 
				yf.getR9_BType(), 
				YeepayConfigInfo.getValue("keyValue")
				);
		//MD5交易签名	商家ID		业务类型		扣款结果,该字段值为1时表示扣款成功.		YeePay易宝交易订单号
		//扣款金额		交易币种		商品ID		商户订单号		YeePay易宝会员ID		商户扩展信息		交易结果通知类型
		//支付银行		在银行支付时的时间
		boolean result = YeepayUtil.verifyCallback(
				yeepayResponse.getHmac(), 
				yeepayResponse.getP1_MerId(),//YeepayConfigInfo.getValue("p1_MerId"),
				yeepayResponse.getR0_Cmd(), 
				yeepayResponse.getR1_Code(), 
				yeepayResponse.getR2_TrxId(),
				yeepayResponse.getR3_Amt(),
				yeepayResponse.getR4_Cur(), 
				yeepayResponse.getR5_Pid(), 
				yeepayResponse.getR6_Order(), 
				yeepayResponse.getR7_Uid(), 
				yeepayResponse.getR8_MP(), 
				yeepayResponse.getR9_BType(), 
				yeepayResponse.getKeyValue()//YeepayConfigInfo.getValue("keyValue")
				);

		
		if(result){
			if("1".equals(yeepayResponse.getR1_Code())){
				//你们这个地方应该把数据库中订单的支付状态设置成已经支付.
				orderManager.updatePaymentState(order.getOrderId(), true);
				yeepayManager.addYeepayResponse(yeepayResponse);
				String message = "订单号为:"+ yeepayResponse.getR6_Order()+ "的订单支付成功了";
				message += ",用户支付了"+ yeepayResponse.getR3_Amt() +"元";
				message +=",交易结果通知类型:";
				if("1".equals(yeepayResponse.getR9_BType())){
					 message += "浏览器重定向";
				}else if("2".equals(yeepayResponse.getR9_BType())){
					 message += "易宝支付网关后台程序通知";
				}
				message += ",易宝订单系统中的订单号为:"+ yf.getR2_TrxId();
				request.setAttribute("message", message);
			}else{
				request.setAttribute("message", "用户支付失败");
			}
		}else{
			
			request.setAttribute("message", "数据来源不合法");
		}

		return mapping.findForward("yeepayresponse");
		
	}
}
