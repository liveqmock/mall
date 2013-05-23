package com.hnfealean.sport.web.actions.payment;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.hnfealean.sport.managers.payment.AlipayManager;
import com.hnfealean.sport.managers.shopping.OrderManager;
import com.hnfealean.sport.model.payment.alipay.AlipayResponse;
import com.hnfealean.sport.payment.alipay.AlipayConfig;
import com.hnfealean.sport.payment.alipay.AlipayNotify;
import com.hnfealean.sport.web.forms.payment.AlipayForm;

public class AlipayReturnAction extends DispatchAction {
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
		AlipayForm af = (AlipayForm) form;
		AlipayResponse alipayResponse = new AlipayResponse();
		alipayResponse.setBody(af.getBody());
		alipayResponse.setBuyer_email(af.getBuyer_email());
		alipayResponse.setNotify_id(af.getNotify_id());
		alipayResponse.setOut_trade_no(af.getOut_trade_no());
		alipayResponse.setPrice(af.getPrice());
		alipayResponse.setReceive_address(af.getReceive_address());
		alipayResponse.setReceive_mobile(af.getReceive_mobile());
		alipayResponse.setReceive_name(af.getReceive_name());
		alipayResponse.setReceive_phone(af.getReceive_phone());
		alipayResponse.setReceive_zip(af.getReceive_zip());
		alipayResponse.setSign(af.getSign());
		alipayResponse.setSubject(af.getSubject());
		alipayResponse.setTrade_no(af.getTrade_no());
		alipayResponse.setTrade_status(af.getTrade_status());
		String key = AlipayConfig.key;
		//获取支付宝POST过来反馈信息
		Map params = new HashMap();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "UTF-8");
			params.put(name, valueStr);
		}
		
		//判断responsetTxt是否为ture，生成的签名结果mysign与获得的签名结果sign是否一致
		//responsetTxt的结果不是true，与服务器设置问题、合作身份者ID、notify_id一分钟失效有关
		//mysign与sign不等，与安全校验码、请求时的参数格式（如：带自定义参数等）、编码格式有关
		String mysign = AlipayNotify.GetMysign(params,key);
		String responseTxt = AlipayNotify.Verify(alipayResponse.getNotify_id());
		String sign = alipayResponse.getSign();
		if(mysign.equals(sign) && responseTxt.equals("true")){//验证成功
			//////////////////////////////////////////////////////////////////////////////////////////
			//请在这里加上商户的业务逻辑程序代码
			
			if(alipayResponse.getTrade_status().equals("TRADE_FINISHED")){
				orderManager.updatePaymentState(alipayResponse.getTrade_no(), true);
				
			}
			//response.getWriter().write("success");
			alipayManager.addAlipayResponse(alipayResponse);
			request.setAttribute("alipayResponse", alipayResponse);
			return mapping.findForward("alipaysuccess");
			
/*			//——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
			if(alipayResponse.getTrade_status().equals("WAIT_BUYER_PAY")){
			//该判断表示买家已在支付宝交易管理中产生了交易记录，但没有付款
			
				//判断该笔订单是否在商户网站中已经做过处理（可参考“集成教程”中“3.4返回数据处理”）
					//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					//如果有做过处理，不执行商户的业务程序
				
				response.getWriter().write("success");	//请不要修改或删除
			} else if(alipayResponse.getTrade_status().equals("WAIT_SELLER_SEND_GOODS")){
			//该判断表示买家已在支付宝交易管理中产生了交易记录且付款成功，但卖家没有发货
			
				//判断该笔订单是否在商户网站中已经做过处理（可参考“集成教程”中“3.4返回数据处理”）
					//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					//如果有做过处理，不执行商户的业务程序
				
				response.getWriter().write("success");	//请不要修改或删除
			} else if(alipayResponse.getTrade_status().equals("WAIT_BUYER_CONFIRM_GOODS")){
			//该判断表示卖家已经发了货，但买家还没有做确认收货的操作
			
				//判断该笔订单是否在商户网站中已经做过处理（可参考“集成教程”中“3.4返回数据处理”）
					//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					//如果有做过处理，不执行商户的业务程序
				
				response.getWriter().write("success");	//请不要修改或删除
			} else if(alipayResponse.getTrade_status().equals("TRADE_FINISHED")){
			//该判断表示买家已经确认收货，这笔交易完成
			
				//判断该笔订单是否在商户网站中已经做过处理（可参考“集成教程”中“3.4返回数据处理”）
					//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					//如果有做过处理，不执行商户的业务程序
				
				response.getWriter().write("success");	//请不要修改或删除
			}
			else {
				response.getWriter().write("success");	//请不要修改或删除
			}
			//——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
*/
			//////////////////////////////////////////////////////////////////////////////////////////
		}else{//验证失败
			//response.getWriter().write("fail");
			return mapping.findForward("alipayfail");
		}

	}
}
