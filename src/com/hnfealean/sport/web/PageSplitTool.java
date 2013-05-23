package com.hnfealean.sport.web;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class PageSplitTool extends SimpleTagSupport {
	private int total;
	private int pageSize;
	private int page;
	private String baseHref;
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public String getBaseHref() {
		return baseHref;
	}
	public void setBaseHref(String baseHref) {
		this.baseHref = baseHref;
	}
	@Override
	public void doTag() throws JspException, IOException {
	
		//super.doTag();
	}
	public String generatePageSplit(int total,int pageSize,int page,String baseHref){
		int pageTotal;
	
		pageTotal = total/pageSize;
		if((total-pageTotal*pageSize)>0)pageTotal++;
		StringBuffer outStr = new StringBuffer();
		outStr.append("");
		if(pageTotal==0)	return "";	
		if(pageTotal==1){
			return "";	
//			outStr.append(link(1,true,"1",baseHref));
//			return outStr.toString();
		}
		if(pageTotal==2){
			outStr.append(link(1,false,"1",baseHref)).append(link(2,true,"2",baseHref));
			return outStr.toString();
		}
		//以下的pageTotal>2
		if(page>pageTotal||page<=0)return "";
		if(page!=1)outStr.append(link(page-1,false,"上一页",baseHref)).append(link(1,false,"1",baseHref));
	//	if(page==1){	;return outStr.toString();}
		//if(page==2)	{outStr.append("2");return outStr.toString();}
		if(page==1){
			if(pageTotal>5){
				outStr.append(link(page,true,String.valueOf(page),baseHref))
						.append(link(page+1,false,String.valueOf(page+1),baseHref))
						.append(link(page+2,false,String.valueOf(page+2),baseHref))
						.append(link(page+3,false,String.valueOf(page+3),baseHref))
						.append(link(page+4,false,String.valueOf(page+4),baseHref))
						;
				if(pageTotal>6)
					outStr.append("..");
			}else if(pageTotal>4)
				outStr.append(link(page,true,String.valueOf(page),baseHref))
						.append(link(page+1,false,String.valueOf(page+1),baseHref))
						.append(link(page+2,false,String.valueOf(page+2),baseHref))
						.append(link(page+3,false,String.valueOf(page+3),baseHref));
			else if(pageTotal>3)
				outStr.append(link(page,true,String.valueOf(page),baseHref))
							.append(link(page+1,false,String.valueOf(page+1),baseHref))
							.append(link(page+2,false,String.valueOf(page+2),baseHref));
			else if(pageTotal>2)
				outStr.append(link(page,true,String.valueOf(page),baseHref))
				.append(link(page+1,false,String.valueOf(page+1),baseHref));
		}else if(page==2){
			if(pageTotal>5){
				outStr.append(link(page,true,String.valueOf(page),baseHref))
				.append(link(page+1,false,String.valueOf(page+1),baseHref))
				.append(link(page+2,false,String.valueOf(page+2),baseHref))
				.append(link(page+3,false,String.valueOf(page+3),baseHref));
				if(pageTotal>6)	outStr.append("..");
			}else if(pageTotal>4)
					outStr.append(link(page,true,String.valueOf(page),baseHref))
					.append(link(page+1,false,String.valueOf(page+1),baseHref))
					.append(link(page+2,false,String.valueOf(page+2),baseHref));
			else if(pageTotal>3)
					outStr.append(link(page,true,String.valueOf(page),baseHref))
					.append(link(page+1,false,String.valueOf(page+1),baseHref));
			else if(pageTotal>2)
					outStr.append(link(page,true,String.valueOf(page),baseHref));
		}else{
			if(page-2>2)outStr.append("..");
		if(page<pageTotal-2){//2<page<7	page = 3,4,5,6	page>3
			if(page-2>1)outStr.append(link(page-2,false,String.valueOf(page-2),baseHref));
			outStr.append(link(page-1,false,String.valueOf(page-1),baseHref))
			.append(link(page,true,String.valueOf(page),baseHref))
			.append(link(page+1,false,String.valueOf(page+1),baseHref))
			.append(link(page+2,false,String.valueOf(page+2),baseHref))
			.append("..");
		}
		if(page==pageTotal-2){//page:		page==7
			outStr.append(link(page-2,false,String.valueOf(page-2),baseHref))
			.append(link(page-1,false,String.valueOf(page-1),baseHref))
			.append(link(page,true,String.valueOf(page),baseHref))
			.append(link(page+1,false,String.valueOf(page+1),baseHref));	
		}
		if(page==pageTotal-1){//page==8
			outStr.append(link(page-2,false,String.valueOf(page-2),baseHref))
			.append(link(page-1,false,String.valueOf(page-1),baseHref))
			.append(link(page,true,String.valueOf(page),baseHref));	
		}
		if(page==pageTotal){//page==9
			outStr.append(link(page-2,false,String.valueOf(page-2),baseHref))
			.append(link(page-1,false,String.valueOf(page-1),baseHref))
			.append(link(page,true,String.valueOf(page),baseHref));	
		}
		}
		if(page!=pageTotal) outStr.append(link(pageTotal,false,String.valueOf(pageTotal),baseHref))
		.append(link(page+1,false,"下一页",baseHref));
		return outStr.toString();
	}
	public String link(int page,boolean thisLink,String words,String baseHref){
		if(thisLink) return "<a class='thisLink' href='"+baseHref+"page="+page+"'>"+words+"</a>";
		return "<a href='"+baseHref+"page="+page+"'>"+words+"</a>";
	}
}
