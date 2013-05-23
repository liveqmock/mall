package com.hnfealean.sport.web.forms.log;

import java.util.Date;

import org.apache.struts.action.ActionForm;

public class LogForm extends ActionForm {
	private Date beginDate;
	private Date endDate;
	private String url;
	
	private String referrer;
	
	private int id;

	private String ip;
	
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Date getBeginDate() {
		return beginDate;
	}

/*	@SuppressWarnings("deprecation")
	public void setBeginDate(Date beginDate) {
		if(beginDate==null){
			this.beginDate=null;
			return;
		}
		try {
			this.beginDate = new Date(new SimpleDateFormat("yyyy-MM-dd").format(new Date(beginDate.toString())));
		} catch (Exception e) {
			this.beginDate=null;
		}
	}*/

	public Date getEndDate() {
		return endDate;
	}

	
/*	public void setEndDate(Date endDate) {
		if(endDate==null) {
			this.endDate=null;
			return;
		}
		try {
			this.endDate = new Date(new SimpleDateFormat("yyyy-MM-dd").format(new Date(endDate.toString())));
		} catch (Exception e) {
			this.endDate=null;
		}
	}
*/
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getReferrer() {
		return referrer;
	}

	public void setReferrer(String referrer) {
		this.referrer = referrer;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
}
