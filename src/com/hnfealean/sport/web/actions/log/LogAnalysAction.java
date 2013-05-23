package com.hnfealean.sport.web.actions.log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.hnfealean.sport.managers.log.SystemLogManager;
import com.hnfealean.sport.pageset.SystemContext;
import com.hnfealean.sport.web.SystemException;
import com.hnfealean.sport.web.forms.log.LogForm;

public class LogAnalysAction extends DispatchAction {

	private SystemLogManager systemLogManager;
	public void setSystemLogManager(SystemLogManager systemLogManager) {
		this.systemLogManager = systemLogManager;
	}	
	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SystemContext.setPagesize(50);
		LogForm lf = (LogForm) form;
		Date beginDate;
		Date endDate;
		if(lf.getBeginDate()==null){
			
			beginDate = new SimpleDateFormat("yyyy-MM-dd").parse(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		}else{
			beginDate =lf.getBeginDate();
		}
		if(lf.getEndDate()==null){
			Calendar cal = Calendar.getInstance();
			cal.setTime(beginDate);
			cal.add(Calendar.DATE, 1);
			endDate = cal.getTime();
		}else{
			endDate =  lf.getEndDate();
		}
		lf.setBeginDate(beginDate);
		lf.setEndDate(endDate);
		request.setAttribute("pm", systemLogManager.getDistinctDetail(beginDate,endDate));
		request.setAttribute("ipcount",systemLogManager.getIPCount(beginDate, endDate));
		request.setAttribute("pvcount",systemLogManager.getPVCount(beginDate, endDate));
		return mapping.findForward("index");
	}
	public ActionForward getIpDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SystemContext.setPagesize(50);
		LogForm lf =(LogForm) form;
		Date beginDate,endDate;
		if(lf.getIp()==null||lf.getIp().trim().length()==0){
			throw new SystemException("非法操作，请指定ip!");
		}
		if(lf.getBeginDate()!=null){
			beginDate = lf.getBeginDate();
		}else{
			beginDate = new SimpleDateFormat("yyyy-MM-dd").parse(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		}
		if(lf.getEndDate()==null){
			Calendar cal = Calendar.getInstance();
			cal.setTime(beginDate);
			cal.add(Calendar.DATE, 1);
			endDate = cal.getTime();
		}else{
			endDate=lf.getEndDate();
		}

		lf.setBeginDate(beginDate);
		lf.setEndDate(endDate);
		request.setAttribute("pm", systemLogManager.getDetailByIp(lf.getIp(),beginDate,endDate));
		return mapping.findForward("ip_date_detail");
	}
	
	public ActionForward getDateDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SystemContext.setPagesize(50);
		LogForm lf =(LogForm) form;
		Date beginDate,endDate;
		
		if(lf.getBeginDate()!=null){
			beginDate = lf.getBeginDate();
		}else{
			beginDate = new SimpleDateFormat("yyyy-MM-dd").parse(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		}
		if(lf.getEndDate()==null){
			Calendar cal = Calendar.getInstance();
			cal.setTime(beginDate);
			cal.add(Calendar.DATE, 1);
			endDate = cal.getTime();
		}else{
			endDate=lf.getEndDate();
		}

		lf.setBeginDate(beginDate);
		lf.setEndDate(endDate);
		request.setAttribute("pm", systemLogManager.getAllDetails(beginDate,endDate));
		return mapping.findForward("ip_date_detail");
	}

}
