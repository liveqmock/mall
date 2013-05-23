package com.hnfealean.sport.web.actions.payment;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Enumeration;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.hnfealean.sport.managers.payment.PaypalManager;
import com.hnfealean.sport.managers.shopping.OrderManager;
import com.hnfealean.sport.model.payment.paypal.Paypal;
import com.hnfealean.sport.model.shopping.Order;
import com.hnfealean.sport.web.SystemException;
import com.hnfealean.sport.web.TrustAnyHostnameVerifier;
import com.hnfealean.sport.web.WebUtil;
import com.hnfealean.sport.web.forms.payment.PaypalForm;

public class PaypalAction extends DispatchAction {
	private PaypalManager paypalManager; 
	private OrderManager orderManager;
	public void setOrderManager(OrderManager orderManager) {
		this.orderManager = orderManager;
	}
	public void setPaypalManager(PaypalManager paypalManager) {
		this.paypalManager = paypalManager;
	}
	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PaypalForm paypalForm =(PaypalForm) form;
		if(paypalForm.getOrderId()==null||paypalForm.getOrderId().trim().equals(""))	throw new SystemException("非法访问！");
		String userName = WebUtil.getUserName(request);
		int userId = WebUtil.getUserId(request);
		if(userName==null||userName.trim().length()==0)	throw new SystemException("非法访问，请登录!");
		//orderManager.checkExists(userId, paypalForm.getOrderId());
		Order order = orderManager.getOrder(paypalForm.getOrderId());
		if(order.getUser().getId()!=userId)
			throw new SystemException("只有该订单所属客户才可访问此订单！");
		if(order.getPaymentState()==true)		throw new SystemException("该订单已经支付！");
		request.setAttribute("order", order);
		return mapping.findForward("gotopaypal");
	}
	public ActionForward pay(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		return super.unspecified(mapping, form, request, response);
	}
	public ActionForward handler(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PaypalForm paypalForm = (PaypalForm) form;
		Enumeration en = request.getParameterNames();
		String str = "cmd=_notify-validate";
		while(en.hasMoreElements()){
		String paramName = (String)en.nextElement();
		String paramValue = request.getParameter(paramName);
		str = str + "&" + paramName + "=" + URLEncoder.encode(paramValue);
		System.out.print(paramName+" : "+paramValue+"<br>");
		}
		URL url=new URL("https://sandbox.paypal.com/cgi-bin/webscr");
		HttpsURLConnection conn=(HttpsURLConnection)url.openConnection();

		conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
		conn.setDoOutput(true);
		conn.connect();
		PrintWriter pw = new PrintWriter(conn.getOutputStream());
		pw.println(str);
		pw.close();

		BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String line;
		StringBuffer strb = new StringBuffer();
		while ((line = br.readLine()) != null) {
		    strb.append(line);
		}
		String ss = strb.toString();
		System.out.println("paypal return:"+ss);
		br.close();
		if(ss.equals("VERIFIED")) {
			// check that paymentStatus=Completed
			// check that txnId has not been previously processed
			// check that receiverEmail is your Primary PayPal email
			// check that paymentAmount/paymentCurrency are correct
			// process payment
		//	String itemName = request.getParameter("item_name");
		//	String itemNumber = request.getParameter("item_number");
			String paymentStatus =paypalForm.getPayment_status();// request.getParameter("payment_status");
			float paymentAmount = paypalForm.getMc_gross();//request.getParameter("mc_gross");
			String paymentCurrency = paypalForm.getMc_currency();//request.getParameter("mc_currency");
			String txnId = paypalForm.getTxn_id();// request.getParameter("txn_id");
			String receiverEmail = paypalForm.getReceiver_email();//request.getParameter("receiver_email");
			String payerEmail = paypalForm.getPayer_email();// request.getParameter("payer_email");
			if(paymentStatus.equals("Completed")){
				Paypal paypal = new Paypal();
				BeanUtils.copyProperties(paypal,paypalForm);
				paypalManager.addPaypal(paypal);
				return mapping.findForward("paypalpaycomplete");
			}else 	if(paymentStatus.equals("Pending")){
				Paypal paypal = new Paypal();
				BeanUtils.copyProperties(paypal,paypalForm);
				paypalManager.addPaypal(paypal);
				return mapping.findForward("paypalpaycomplete");
			}
		}
		else if(ss.equals("INVALID")) {
		// log for investigation
		}
		else {
		// error
		}
		
	///	request.getRequestDispatcher("/WEB-INF/page/category/"+fpaf.getCategoryId()+".jsp").forward(request, response);
		
		return null;
		
	}
}
