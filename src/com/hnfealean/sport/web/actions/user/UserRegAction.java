package com.hnfealean.sport.web.actions.user;

import java.io.StringWriter;
import java.util.HashSet;
import java.util.regex.Pattern;

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
import com.hnfealean.sport.managers.email.EmailManager;
import com.hnfealean.sport.managers.user_acl_module.UserRegManager;
import com.hnfealean.sport.model.email.Email;
import com.hnfealean.sport.model.shopping.Order;
import com.hnfealean.sport.model.user_acl_module.ContactInfo;
import com.hnfealean.sport.model.user_acl_module.User;
import com.hnfealean.sport.web.email.EmailSender;
import com.hnfealean.sport.web.forms.user.UserRegForm;

public class UserRegAction extends DispatchAction {

	private UserRegManager userRegManager;

	private EmailManager emailManager;
	public void setEmailManager(EmailManager emailManager) {
		this.emailManager = emailManager;
	}


	public void setUserRegManager(UserRegManager userRegManager) {
		this.userRegManager = userRegManager;
	}

	
	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
		return mapping.findForward("index");
	}
	public ActionForward forgetPassword(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		return mapping.findForward("forgetPassword");
	}
	
	public ActionForward checkAvail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		UserRegForm urg= (UserRegForm) form;
		String email = urg.getEmail();
		if(email==null||email.trim().length()==0){
			response.reset();
			response.getWriter().write("hnfealean.avail(false)");
			return null;
		}
		boolean formatOk = Pattern.matches("^(\\w+)@(\\w+)\\.(\\w+)$", email);
		if(formatOk==false){
			response.reset();
			response.getWriter().write("hnfealean.avail(false)");
			return null;
		}
		boolean avail = userRegManager.checkEmailAvaliable(email.trim());
		if(avail==false){
			response.reset();
			response.getWriter().write("hnfealean.avail(false)");
			return null;
		}
		response.reset();
		response.getWriter().write("hnfealean.avail(true)");
		return null;
	}	
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		UserRegForm urg = (UserRegForm) form;
		//if(urg.getUsername()==null||urg.getEmail()==null||urg.get)
		//String username = urg.getUsername();
		String password = urg.getPassword();
		String email = urg.getEmail();
		if(email==null||password==null||email.trim().length()==0||password.trim().length()==0){
			request.setAttribute("message", "注册失败！您的输入不正确！");
			return mapping.findForward("index");	
		}
		if(password.length()<6)	{
			request.setAttribute("message", "密码至少6位！");	
			return mapping.findForward("index");	
		}
		if(!Pattern.matches("^(\\w+)@(\\w+)\\.(\\w+)$", email)){
			request.setAttribute("message", "您输入的邮箱地址格式不正确！");	
			return mapping.findForward("index");
		}
		String username=null;
		if(userRegManager.checkEmailAvaliable(email)==false){//该邮箱不可用
			request.setAttribute("message", "注册失败！该邮箱已经被使用或者您的输入不正确！");
			return mapping.findForward("index");
		}else{
			User user = new User();
			String tempStr = email.split("@")[0];
			username = tempStr.length()>16?tempStr.substring(0,15):tempStr;
			user.setUsername(username);
			user.setEmail(email);
			user.setPassword(password);
			//Set<Order> orders = new HashSet<Order>();
			user.setOrders(new HashSet<Order>());
			//user.set
			//user.setRealName(username);
			//user.setEmail(urg.getEmail());
			ContactInfo contactinfo = new ContactInfo();
			contactinfo.setPhone("");
			contactinfo.setPostCode("");
			contactinfo.setMobile("");
			contactinfo.setGender("");
			
		//	contactinfo.set
		//	contactinfo.setUser(user);
			user.setContactInfo(contactinfo);
			userRegManager.addUser(user);
			request.setAttribute("message", "注册成功！");		
		}
		//if(ConstantString.POSTWELCOMEEMAIL==false){
	//		return mapping.findForward("add_success");
	//	}
		
		VelocityContext context = new VelocityContext();
		context.put("username",username);
		Template template = Velocity.getTemplate("email/mailWelcome.vm");
		StringWriter sw = new StringWriter();
		template.merge(context, sw);
		sw.flush();
		String content = sw.toString();
		
		Email adminEmail = emailManager.getEmail(emailManager.getDefaultEmail());
		System.out.println("sendmail begin");
		Thread emailsend =new Thread( new EmailSender(adminEmail, new String[]{email}, ConstantString.SUBJECTWELCOME, content, null, null)
		,"hnfealeansender"
		);
		emailsend.start();
		//EmailSender.send(adminEmail, new String[]{email}, ConstantString.SUBJECTWELCOME, content, null, null);
		System.out.println("sendmail ok");
		/*
		user.setCreateDate(new Date());
		user.setEmail("google@gmail.com");
		user.setExpireDate(new Date());
		user.setPassword("google");
		user.setRealName("zhuzhu");
		user.setShopDetail(null);
		user.setUsername("hnfealean");
		user.setVisible(true);*/
		//BeanUtils.copyProperties(user, urg);
		//user.setContactInfo(null);
		//userRegManager.addUser(user);	
		return mapping.findForward("add_success");
	}

}
