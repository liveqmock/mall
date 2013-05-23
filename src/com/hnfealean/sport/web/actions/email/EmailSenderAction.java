package com.hnfealean.sport.web.actions.email;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.hnfealean.sport.managers.email.EmailManager;
import com.hnfealean.sport.model.email.Email;
import com.hnfealean.sport.model.email.EmailSenderActionForm;
import com.hnfealean.sport.web.SystemException;
import com.hnfealean.sport.web.email.EmailSender;

public class EmailSenderAction extends DispatchAction {
	private EmailManager emailManager; 
	public void setEmailManager(EmailManager emailManager) {
		this.emailManager = emailManager;
	}
	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		EmailSenderActionForm esf = (EmailSenderActionForm) form;
		if(esf.getAdminEmailId()==0)	throw new SystemException("非法访问.");
		if(esf.getReceiver()==null&&esf.getReveivers()==null)throw new SystemException("至少需要一个收件人邮箱地址!");
		if(esf.getSubject()==null||esf.getSubject().trim().length()==0)throw new SystemException("没有邮件主题.");
		if(esf.getContent()==null||esf.getContent().trim().length()==0)throw new SystemException("没有邮件主体内容.");
	
		Email email = emailManager.getEmail(esf.getAdminEmailId());
		if(email==null) throw new SystemException("没有找到可用的SMTP邮箱帐号");
		EmailSender.send(email,
				esf.getReceiver()!=null?new String[]{esf.getReceiver()}:esf.getReveivers(),
				esf.getSubject()==null?"":esf.getSubject(),
				esf.getContent()==null?"":esf.getContent(), null, esf.getMimeType());
		return mapping.findForward("");
	}

}
