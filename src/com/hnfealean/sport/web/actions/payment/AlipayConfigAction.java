package com.hnfealean.sport.web.actions.payment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.hnfealean.sport.managers.payment.AlipayManager;
import com.hnfealean.sport.model.payment.alipay.AlipayConfiguration;
import com.hnfealean.sport.web.SystemException;
import com.hnfealean.sport.web.forms.payment.AlipayConfigurationForm;

public class AlipayConfigAction extends DispatchAction {

	private AlipayManager alipayManager;

	public void setAlipayManager(AlipayManager alipayManager) {
		this.alipayManager = alipayManager;
	}
	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		AlipayConfiguration config = alipayManager.getAlipayConfiguration();
		while(config==null){
			initial();
			config = alipayManager.getAlipayConfiguration();
		}
		request.setAttribute("aliconfig", config);
		return mapping.findForward("index");
	}
	private void initial(){
		AlipayConfiguration config = new AlipayConfiguration();
		alipayManager.addAlipayConfiguration(config);
	}
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		AlipayConfigurationForm acf = (AlipayConfigurationForm) form;
		if(acf.getId()==0)	throw new SystemException("非法访问！");
		AlipayConfiguration config = alipayManager.getAlipayConfigurationById(acf.getId());
		if(config==null)throw new SystemException("找不到指定的数据！！！");
		config.setAlipaysubmit(acf.getAlipaysubmit());
		config.setInput_charset(acf.getInput_charset());
		config.setKey(acf.getKey());
		config.setMainname(acf.getMainname());
		config.setNotify_url(acf.getNotify_url());
	    config.setPartner(acf.getPartner());
	    config.setPaymentType(acf.getPaymentType());
	    config.setReturn_url(acf.getReturn_url());
	    config.setSeller_email(acf.getSeller_email());
	    config.setShow_url(acf.getShow_url());
	    config.setSign_type(acf.getSign_type());
	    config.setTransport(acf.getTransport());
	    alipayManager.updateAlipayConfiguration(config);
		request.setAttribute("message", "更新数据成功!");
		request.setAttribute("urladdress", "control/payment/alipay/manage.do");
		return mapping.findForward("success");

	}	
}
