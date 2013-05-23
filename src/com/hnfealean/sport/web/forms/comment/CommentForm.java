package com.hnfealean.sport.web.forms.comment;

import org.apache.struts.action.ActionForm;

public class CommentForm extends ActionForm {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 100005545455544L;

	private int id;
	
	private int[] ids;
	private int type;
	
	private int oid;
	private String content;
	private int latestId;
	private String returnTo;

	public String getReturnTo() {
		return returnTo;
	}

	public void setReturnTo(String returnTo) {
		this.returnTo = returnTo;
	}

	public int[] getIds() {
		return ids;
	}

	public void setIds(int[] ids) {
		this.ids = ids;
	}

	public int getLatestId() {
		return latestId;
	}

	public void setLatestId(int latestId) {
		this.latestId = latestId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		
		content= content.replaceAll("\r\n", "")
									//替换所有换行符
						.replaceAll("<div class=\"quote\">.+?</div>(<br>)?(<span class=\"end\">)", "$2")
									//去掉引用的html
						.replaceAll("<(/?(div|span|br|li|ol|b|em|strong|!--)( .+?|[0-9]+--)?)>", "[[$1]]")
									//将所有被允许的html替换成[[....]]的形式
						.replaceAll("<[^>]*>", "")
									//去除所有html标记
						.replace("[[", "<").replace("]]", ">")
									//将被转换的[]]恢复成html
						;
		System.out.println("actionform中的content：\n"+content);
		this.content = content;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getOid() {
		return oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}
	
	
}
