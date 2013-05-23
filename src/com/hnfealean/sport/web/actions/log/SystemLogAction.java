package com.hnfealean.sport.web.actions.log;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.hnfealean.sport.managers.log.SystemLogManager;
import com.hnfealean.sport.model.log.UserViewLog;
import com.hnfealean.sport.web.WebUtil;
import com.hnfealean.sport.web.forms.log.LogForm;
import com.hnfealean.sport.web.ipaddressutil.IPSeeker;

public class SystemLogAction extends DispatchAction {

	private SystemLogManager systemLogManager;
	
	public void setSystemLogManager(SystemLogManager systemLogManager) {
		this.systemLogManager = systemLogManager;
	}

	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		LogForm lf =(LogForm) form;
		if(lf==null||lf.getUrl()==null||lf.getUrl().trim().length()==0){
			return null;
		}
		String logReferer = request.getHeader("Referer");
		if(logReferer==null||logReferer.trim().length()==0||!logReferer.startsWith("http://mall.59124.com")){
			response.sendError(404);
			return null;
		}
		if(lf.getUrl().indexOf("/control/")>0){//后台
			//AdministratorViewLog log = new AdministratorViewLog();
			//log.setAccessUrl(lf.getUrl());
			//log.set
			//systemLogManager.addAdministratorViewLog(log);
		}else{//前台
			UserViewLog log = new UserViewLog();
			log.setInTime(new Date());
			log.setInUrl(lf.getUrl());
			String ip = request.getRemoteHost();
			log.setIp(ip);
			log.setReferer(lf.getReferrer());
			log.setUserEmail(WebUtil.getUserEmail(request));
			log.setUserId(WebUtil.getUserId(request));
			log.setZone(IPSeeker.getInstance().getAddress(ip));
			systemLogManager.addUserLog(log);
		}
		
		return null;
	}

}
