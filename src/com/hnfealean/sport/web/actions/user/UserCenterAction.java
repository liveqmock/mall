package com.hnfealean.sport.web.actions.user;

import java.io.StringWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import com.hnfealean.sport.enums.ConstantString;
import com.hnfealean.sport.managers.area.CountryManager;
import com.hnfealean.sport.managers.area.ZoneManager;
import com.hnfealean.sport.managers.email.EmailManager;
import com.hnfealean.sport.managers.shopping.PaymentMethodManager;
import com.hnfealean.sport.managers.user_acl_module.ContactInfoManager;
import com.hnfealean.sport.managers.user_acl_module.FrontUserManager;
import com.hnfealean.sport.model.area.Country;
import com.hnfealean.sport.model.area.Zone;
import com.hnfealean.sport.model.email.Email;
import com.hnfealean.sport.model.shopping.PaymentMethod;
import com.hnfealean.sport.model.user_acl_module.ContactInfo;
import com.hnfealean.sport.model.user_acl_module.User;
import com.hnfealean.sport.pageset.PageModel;
import com.hnfealean.sport.web.MD5;
import com.hnfealean.sport.web.SystemException;
import com.hnfealean.sport.web.WebUtil;
import com.hnfealean.sport.web.email.EmailSender;
import com.hnfealean.sport.web.forms.user.UserCenterActionForm;


public class UserCenterAction extends DispatchAction {
	private PaymentMethodManager paymentMethodManager;
	private FrontUserManager frontUserManager;
	private ContactInfoManager contactInfomanager;
	private ZoneManager zoneManager;
	private CountryManager countryManager;

	private EmailManager emailManager;
	public void setEmailManager(EmailManager emailManager) {
		this.emailManager = emailManager;
	}
	public void setPaymentMethodManager(PaymentMethodManager paymentMethodManager) {
		this.paymentMethodManager = paymentMethodManager;
	}
	public void setContactInfomanager(ContactInfoManager contactInfomanager) {
		this.contactInfomanager = contactInfomanager;
	}
	public void setZoneManager(ZoneManager zoneManager) {
		this.zoneManager = zoneManager;
	}
	public void setCountryManager(CountryManager countryManager) {
		this.countryManager = countryManager;
	}
	public void setFrontUserManager(FrontUserManager frontUserManager) {
		this.frontUserManager = frontUserManager;
	}
	
	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		return mapping.findForward("index");
	}
	public ActionForward loadAllOrders(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if(WebUtil.getUserId(request)==null||WebUtil.getUserId(request)==0){
			response.sendRedirect("/user/userLogin.do");
			return null;
		}
		loadPaymentMethods(request);
		PageModel pm = frontUserManager.getAllOrdersByUserId(WebUtil.getUserId(request));
		request.setAttribute("pm", pm);
		return mapping.findForward("orderslist");
	}
	private void loadPaymentMethods(HttpServletRequest request) {
		List<PaymentMethod> methods =paymentMethodManager.getAllPaymentMethods();
		request.setAttribute("paymentMethods", methods);
	}
	public ActionForward loadAllDeliveredOrders(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if(WebUtil.getUserId(request)==null||WebUtil.getUserId(request)==0){
			response.sendRedirect("/user/userLogin.do");
			return null;
		}
		PageModel pm = frontUserManager.getDeliveredOrdersByUserId(WebUtil.getUserId(request));
		request.setAttribute("pm", pm);
		return mapping.findForward("orderslist");
	}
	public ActionForward editContactInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if(WebUtil.getUserId(request)==null||WebUtil.getUserId(request)==0){
			response.sendRedirect("/user/userLogin.do");
			return null;
		}
		ContactInfo contactInfo = frontUserManager.getContactInfoByUserId(WebUtil.getUserId(request));
		request.setAttribute("contactInfo", contactInfo);
		return mapping.findForward("contactInfoedit");
	}
	public ActionForward updateContactInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if(WebUtil.getUserId(request)==null){
			response.sendRedirect("/user/userLogin.do");
			return null;
		}
		UserCenterActionForm ucf = (UserCenterActionForm) form;
		if(ucf.getConatctInfoId()==0)	throw new SystemException("非法访问");
		//if(ucf.get)
		ContactInfo contactInfo = frontUserManager.getContactInfoByUserId(WebUtil.getUserId(request));
		if(contactInfo.getId()!=ucf.getConatctInfoId())throw new SystemException("非法访问");
		if(ucf.getCountry()>0)	{
			Country country =countryManager.getCountryById(ucf.getCountry());
			if(country!=null)
			contactInfo.setCountry(country.getCnName());
		}
		if(ucf.getState()>0)	{
			//Country country =countryManager.getCountryById(ucf.getCountry());
			Zone zone = zoneManager.getZoneById(ucf.getState());
			if(zone!=null)
			contactInfo.setState(zone.getName());
		}
		if(ucf.getCounty()>0){
			Zone zone = zoneManager.getZoneById(ucf.getCounty());
			if(zone!=null)
			contactInfo.setCounty(zone.getName());
	
		}
		if(ucf.getStreet_address()!=null){
			if(ucf.getStreet_address().trim().length()>0){
				contactInfo.setStreet_address(ucf.getStreet_address());
			}
		}
		if(ucf.getMobile()==null||ucf.getMobile().trim().length()==0){
			
		}else{
			contactInfo.setMobile(ucf.getMobile().trim());
		}
	if(frontUserManager.updateContactInfo(contactInfo)==false){
		request.setAttribute("message", "您的输入错误");
		request.setAttribute("urladdress","" );
		return mapping.findForward("success");
	}
		return mapping.findForward("success");
	}
	public ActionForward editPassword(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if(WebUtil.getUserId(request)==null||WebUtil.getUserId(request)==0){
			response.sendRedirect("/user/userLogin.do");
			return null;
		}
		return mapping.findForward("passwordedit");
	}
	public ActionForward updatePassword(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if(WebUtil.getUserId(request)==null||WebUtil.getUserId(request)==0){
			response.sendRedirect("/user/userLogin.do");
			return null;
		}
		UserCenterActionForm ucf = (UserCenterActionForm) form;
		if(ucf.getPassword()==null||ucf.getPassword().trim().length()==0||ucf.getOldPassword()==null||ucf.getOldPassword().trim().length()==0){
			throw new SystemException("非法访问");
		}
		if(frontUserManager.updatePassword(ucf.getOldPassword().trim(), ucf.getPassword().trim(), WebUtil.getUserId(request))==false){
			request.setAttribute("message", "您输入的原密码错误");
			return mapping.findForward("success");
		}
		request.setAttribute("message", "成功修改密码");
		return mapping.findForward("success");
	}
	public ActionForward forgetPassword(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		return mapping.findForward("forgetPassword");
		
	}
	public ActionForward getPassword(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		UserCenterActionForm ucf = (UserCenterActionForm) form;
		if(ucf.getEmail()==null||ucf.getEmail().trim().length()==0){
			throw new SystemException("非法访问");
		}
		User user  = frontUserManager.getUserByEmail(ucf.getEmail());
		if(user==null||user.getId()==0){
			request.setAttribute("message", "您输入的邮箱不存在！");
			return mapping.findForward("forgetPassword");
		}
		String md5 = MD5.MD5Encode(user.getEmail()+user.getPassword()+(new Date()).getDate());
		request.setAttribute("md5", md5);
		Email adminEmail = emailManager.getEmail(emailManager.getDefaultEmail());
		VelocityContext context = new VelocityContext();
		context.put("user",user);
		context.put("validateCode",md5);
		Template template = Velocity.getTemplate("email/mailFindpass.vm");
		StringWriter sw = new StringWriter();
		template.merge(context, sw);
		sw.flush();
		String content = sw.toString();
		Thread emailsend =new Thread( new EmailSender(adminEmail, new String[]{user.getEmail()}, ConstantString.SUBJECTGETNEWPASSWORD, content, null, "text/html")
		,"hnfealeansender"
		);
		emailsend.start();
		return mapping.findForward("getPassword");
		
	}
	public ActionForward editPass(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		UserCenterActionForm ucf = (UserCenterActionForm) form;
		if(ucf.getId()==0||ucf.getValidateCode()==null||ucf.getValidateCode().trim().length()==0){
			throw new SystemException("非法访问");
		}
		User user  = frontUserManager.getUserById(ucf.getId());
		String md5 = MD5.MD5Encode(user.getEmail()+user.getPassword()+(new Date()).getDate());
		if(md5.equals(ucf.getValidateCode().trim())){
			request.setAttribute("validateCode", ucf.getValidateCode().trim());
			request.setAttribute("id", user.getId());
			return mapping.findForward("editPass");
		}
		return mapping.findForward("expireTime");
		
	}
	public ActionForward updatePass(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		UserCenterActionForm ucf = (UserCenterActionForm) form;
		if(ucf.getId()==0||ucf.getValidateCode()==null||
				ucf.getValidateCode().trim().length()==0||
				ucf.getPassword()==null||
				ucf.getPassword().trim().length()==0){
			throw new SystemException("非法访问");
		}
		if(ucf.getPassword().trim().length()<6){
			throw new SystemException("密码至少6位");
		}
		User user  = frontUserManager.getUserById(ucf.getId());
		String md5 = MD5.MD5Encode(user.getEmail()+user.getPassword()+(new Date()).getDate());
		if(md5.equals(ucf.getValidateCode().trim())){
			//request.setAttribute("validateCode", ucf.getValidateCode().trim());
			user.setPassword(MD5.MD5Encode(ucf.getPassword().trim()));
			request.setAttribute("message", "修改密码成功！");
			//request.setAttribute("urladdress", "user/manage.do");
			frontUserManager.updateUser(user);
			return mapping.findForward("editPassSuccess");
		}
		return mapping.findForward("expireTime");
		
	}
}
