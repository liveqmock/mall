package com.hnfealean.sport.web.actions.shopping;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.hnfealean.sport.managers.area.CountryManager;
import com.hnfealean.sport.managers.area.ZoneManager;
import com.hnfealean.sport.managers.shopping.DeliverManager;
import com.hnfealean.sport.model.shopping.DeliverInfo;
import com.hnfealean.sport.model.user_acl_module.ContactInfo;
import com.hnfealean.sport.web.WebUtil;
import com.hnfealean.sport.web.forms.shopping.DeliverForm;
import com.sun.org.apache.commons.beanutils.BeanUtils;

public class DeliverAction extends DispatchAction {
	private DeliverManager deliverManager;
	private ZoneManager zoneManager;
	private CountryManager countryManager;
	
	public void setZoneManager(ZoneManager zoneManager) {
		this.zoneManager = zoneManager;
	}


	public void setCountryManager(CountryManager countryManager) {
		this.countryManager = countryManager;
	}


	public void setDeliverManager(DeliverManager deliverManager) {
		this.deliverManager = deliverManager;
	}


	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	
		String url = (String) request.getParameter("directUrl");
	
		if(url!=null && url.length()>0){
			request.setAttribute("directUrl",WebUtil.decodeStringByBase64(url));

			//String s = (String)request.getSession().getAttribute("user");
		//	User u =deliverManager.loadUserByEmail(WebUtil.getUserEmail(request));

		//	ContactInfo contactInfo =u.getContactInfo();
		//request.setAttribute("usercontactInfo", contactInfo);
		}else{
			DeliverInfo deliverInfo =(DeliverInfo)request.getSession().getAttribute("deliverInfo");
			if(deliverInfo!=null)
			if(deliverInfo.getId()>0){
				response.reset();
				response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
				response.setHeader("Location",basePath+"/customer/payment-method.do");
				return null;
			}
		}
		List<DeliverInfo> deliverInfos = deliverManager.getUserDiliverInfos(WebUtil.getUserId(request));
		request.setAttribute("deliverInfos", deliverInfos);
		return mapping.findForward("index");
	}
	public ActionForward gotopayment(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		DeliverForm dlf = (DeliverForm) form;
		DeliverInfo deliverInfo = new DeliverInfo();
	//	if(WebUtil.getDeliverInfoId(request)>0){
			
		//}else{//session中不存在deliverInfo，新建
		if(dlf.getDeliverInfoId()>0){
			DeliverInfo temp=deliverManager.checkDeliverInfoBelongsToUser(dlf.getDeliverInfoId(),WebUtil.getUserId(request));
			if(temp!=null){
				deliverInfo = temp;
			}
		}else{
				BeanUtils.copyProperties(deliverInfo, dlf);
				if(dlf.getCountry()>0)
				deliverInfo.setCountry(countryManager.getCountryById(dlf.getCountry()).getCnName());
				if(dlf.getState()>0)
					deliverInfo.setState(zoneManager.getZoneById(dlf.getState()).getName());
				if(dlf.getCity()>0)
					deliverInfo.setCity(zoneManager.getZoneById(dlf.getCity()).getName());
				if(dlf.getCounty()>0)
					deliverInfo.setCounty(zoneManager.getZoneById(dlf.getCounty()).getName());
				
				if(deliverManager.checkCompletedContactInfo(WebUtil.getUserId(request))==false){
					ContactInfo completeContactInfo = new ContactInfo(
							deliverInfo.getName(),
							deliverInfo.getStreet_address(),
							deliverInfo.getCountry(),
							deliverInfo.getState(),
							deliverInfo.getCity(),
							deliverInfo.getCounty(),
							deliverInfo.getPostCode(),
							deliverInfo.getPhone(),
							deliverInfo.getMobile(),
							deliverInfo.getGender()
					);
					deliverManager.updateUserContactInfo(completeContactInfo, WebUtil.getUserId(request));
				}
				//deliverManager.addDeliverInfo(deliverInfo);
		}
		request.getSession().setAttribute("deliverInfo", deliverInfo);
		//}
		String url = (String) request.getParameter("directUrl");
		
		if(url!=null && url.length()>0){
			request.setAttribute("directUrl",WebUtil.decodeStringByBase64(url));
		}else{
			request.setAttribute("directUrl", "customer/payment-method.do");
		}
		
		return mapping.findForward("redirectTo");
	}

}
