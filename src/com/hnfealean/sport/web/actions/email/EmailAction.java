package com.hnfealean.sport.web.actions.email;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.hnfealean.sport.managers.email.EmailManager;
import com.hnfealean.sport.model.email.Email;
import com.hnfealean.sport.web.SystemException;
import com.hnfealean.sport.web.forms.email.EmailActionForm;

public class EmailAction extends DispatchAction {
	private EmailManager emailManager;
	
	public void setEmailManager(EmailManager emailManager) {
		this.emailManager = emailManager;
	}
	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		List<Email> emails = emailManager.getAllEmails();
		request.setAttribute("emails", emails);
		return mapping.findForward("list");
	}
	public ActionForward addInput(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		return mapping.findForward("addInput");
	}
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		EmailActionForm eaf = (EmailActionForm) form;
		if(eaf.getAddress()==null||eaf.getAddress().trim().length()==0||
				eaf.getPassword()==null||eaf.getPassword().trim().length()==0||
				eaf.getUsername()==null||eaf.getUsername().trim().length()==0||
				eaf.getSmtpHost()==null||eaf.getSmtpHost().trim().length()==0||
				eaf.getSmtpPort()==null||eaf.getSmtpPort().trim().length()==0
		)
			throw new SystemException("非法访问！");
		Email email = new Email();
		email.setAddress(eaf.getAddress());
		email.setPassword(eaf.getPassword());
		email.setSmtpHost(eaf.getSmtpHost());
		email.setSmtpPort(eaf.getSmtpPort());
		email.setUsername(eaf.getUsername());
		emailManager.addEmail(email);
		request.setAttribute("message", "添加成功！");
		
		request.setAttribute("urladdress", "control/center/email/manage.do");
		return mapping.findForward("success");
	}
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		EmailActionForm eaf = (EmailActionForm) form;
		if(eaf.getId()==0)	throw new SystemException("非法访问！");
		emailManager.deleteEmail(eaf.getId());
		request.setAttribute("message", "删除成功！");
		request.setAttribute("urladdress", "control/center/email/manage.do");
		return mapping.findForward("success");
	}
	public ActionForward deletes(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		EmailActionForm eaf = (EmailActionForm) form;
		if(eaf.getIds()==null||eaf.getIds().length==0)	throw new SystemException("非法访问！");
		StringBuffer ids = new StringBuffer();
		for(int id:eaf.getIds()){
			ids.append(id+",");
		}
		if(ids.length()>0)
		if(ids.charAt(ids.length()-1)==",".charAt(0)){
			ids.deleteCharAt(ids.length()-1);
		}
		emailManager.deleteEmails(ids.toString());
		request.setAttribute("message", "删除成功！");
		request.setAttribute("urladdress", "control/center/email/manage.do");
		return mapping.findForward("success");
	}
	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		EmailActionForm eaf = (EmailActionForm) form;
		if(eaf.getId()==0)	throw new SystemException("非法访问！");
		Email email = emailManager.getEmail(eaf.getId());
		request.setAttribute("email", email);
		return mapping.findForward("edit");
	}
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		EmailActionForm eaf = (EmailActionForm) form;
	
		if(eaf.getAddress()==null||eaf.getAddress().trim().length()==0||
				eaf.getPassword()==null||eaf.getPassword().trim().length()==0||
				eaf.getUsername()==null||eaf.getUsername().trim().length()==0||
				eaf.getSmtpHost()==null||eaf.getSmtpHost().trim().length()==0||
				eaf.getSmtpPort()==null||eaf.getSmtpPort().trim().length()==0
		)
			throw new SystemException("非法访问！");
		Email email = new Email();
		email.setAddress(eaf.getAddress().trim().trim());
		email.setId(eaf.getId());
		email.setPassword(eaf.getPassword().trim());
		email.setSmtpHost(eaf.getSmtpHost().trim());
		email.setSmtpPort(eaf.getSmtpPort());
		email.setUsername(eaf.getUsername().trim());
		emailManager.updateEmail(email);
		request.setAttribute("message", "更新成功！");
		request.setAttribute("urladdress", "control/center/email/manage.do");
		return mapping.findForward("success");
	}
}
