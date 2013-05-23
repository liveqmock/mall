package com.hnfealean.sport.enums;

import com.hnfealean.sport.pageset.SystemContext;

public class ConstantString {
	private static class InnerInstance{
		private static final ConstantString INSTANCE=new ConstantString();
	
	}
	public static ConstantString getInstance(){
		return InnerInstance.INSTANCE;	
	}
	public static int NEWSVALUE =0;
	public static int BLOGVALUE =1;
	public static int WIKIVALUE =2;
	public static int THEMEVALUE =3;
	public static int CATEGORYVALUE = 4;
	public static int PRODUCTVALUE=5;
	public static int QUESTIONVALUE=6;
	public static int HELPCENTERVALUE=7;
	public static int INITIALPAGESIZE=20;
	public static String CATEGORY = "category";
	public static String  PRODUCT="product";
	public static String  PRODUCT_IMAGE="product_image";
	public static String  PRODUCT_ATTRIBUTES="product_attributes";
	public static String  PRODUCT_BREADCRUMB="product_breadcrumb";
	public static String RELATEDPRODUCT="relatedproduct";
	public static String NEWS = "news";
	public static String BLOG = "blog";
	public static String WIKI = "wiki";
	public static String THEME = "theme";
	public static String HELPCENTER="helpcenter";
	public static String QUESTION = "question";
	public static String RELATEDPRODUCTFILESUFFIX =".html";//相关产品文件后缀
	public static String PRODUCTFILESUFFIX =".shtml";//产品文件后缀
	public static String NEWSFILESUFFIX =".shtml";//新闻文件后缀
	public static String BLOGFILESUFFIX =".shtml";//博客文件后缀
	public static String WIKIFILESUFFIX =".shtml";//维基文件后缀
	public static String QUESTIONFILESUFFIX =".shtml";//问答文件后缀
	public static String CATEGORYURLSUFFIX="-wholesale";//产品分类页面的url的后缀
	public static String SEPERATORSLASH="/";//分隔符斜杠/	
	public static String SERVERROOTURL="http://127.0.0.1/";//网站网址
	public static String RELATEDARTICLE ="relatedarticle";//相关文章
	public static String RELATEDARTICLESUFFIX =".html";//相关文章文件后缀
	public static String IPFileName="QQWry.dat";//ip库
	public static String IMAGEFOLDERNAME="images";//图片文件夹名称
	public static boolean POSTWELCOMEEMAIL=false;
	public static String RECOMMEND="recommend";			//推荐产品
	public static String HOTSELL="hotsell";				//热门产品
	public static String TOPTEN="top10";				//TOP10
	public static String NOPICTURE="/images/nopic.jpg";					//产品没有图片时的替代url
	public static String OTHERZONES="其他地区";
	private static int AOPCACHETIME=120000;
	private static int AOPCACHEMAXVISIT=10000;
	
	public static String getNOPICTURE() {
		return NOPICTURE;
	}
	public static void setNOPICTURE(String nopicture) {
		NOPICTURE = nopicture;
	}
	public static int getHELPCENTERVALUE() {
		return HELPCENTERVALUE;
	}
	public static void setHELPCENTERVALUE(int helpcentervalue) {
		HELPCENTERVALUE = helpcentervalue;
	}
	public static String getHELPCENTER() {
		return HELPCENTER;
	}
	public static void setHELPCENTER(String helpcenter) {
		HELPCENTER = helpcenter;
	}
	public static String getOTHERZONES() {
		return OTHERZONES;
	}
	public static void setOTHERZONES(String otherzones) {
		OTHERZONES = otherzones;
	}
	public static int getAOPCACHETIME() {
		return AOPCACHETIME;
	}
	public static void setAOPCACHETIME(int aopcachetime) {
		AOPCACHETIME = aopcachetime;
	}
	public static int getAOPCACHEMAXVISIT() {
		return AOPCACHEMAXVISIT;
	}
	public static void setAOPCACHEMAXVISIT(int aopcachemaxvisit) {
		AOPCACHEMAXVISIT = aopcachemaxvisit;
	}
	public static String SUBJECTWELCOME="欢迎注册";
	public static String SUBJECTGETNEWPASSWORD="取回密码";
	/** 已取消 **/
	public static String ORDER_CANCELED="已取消";
	/** 待审核 **/
	public static String ORDER_WAITCONFIRM="待审核";
	/** 已审核 **/
	public static String ORDER_ADMINCONFIRMED="已审核";
	/** 等待付款 **/
	public static String ORDER_WAITPAYMENT="等待付款";
	/** 正在配货 **/
	public static String ORDER_ADMEASUREPRODUCT="正在配货";
	/** 等待发货 **/
	public static String ORDER_WAITDELIVER="等待发货 ";
	/** 已发货 **/
	public static String ORDER_DELIVERED="已发货";
	/** 已收货 **/
	public static String ORDER_RECEIVED="已收货";
	
	public static String ORDERBY_SELLCOUNTDESC ="sellcountdesc";
	
	public static String ORDERBY_SELLPRICEASC="sellpriceasc";
	public static String ORDERBY_SELLPRICEDESC="sellpricedesc";
	public static String ORDERBY_CREATEDATEDESC="createdatedesc";
	public static String SHOWMODE_LIST="list";
	public static String SHOWMODE_IMAGE="image";
	
	public static String getSHOWMODE_LIST() {
		return SHOWMODE_LIST;
	}
	public static void setSHOWMODE_LIST(String showmode_list) {
		SHOWMODE_LIST = showmode_list;
	}
	public static String getSHOWMODE_IMAGE() {
		return SHOWMODE_IMAGE;
	}
	public static String getSUBJECTGETNEWPASSWORD() {
		return SUBJECTGETNEWPASSWORD;
	}
	public static void setSUBJECTGETNEWPASSWORD(String subjectgetnewpassword) {
		SUBJECTGETNEWPASSWORD = subjectgetnewpassword;
	}
	public static void setSHOWMODE_IMAGE(String showmode_image) {
		SHOWMODE_IMAGE = showmode_image;
	}
	public static String getORDERBY_SELLCOUNTDESC() {
		return ORDERBY_SELLCOUNTDESC;
	}
	public static void setORDERBY_SELLCOUNTDESC(String orderby_sellcountdesc) {
		ORDERBY_SELLCOUNTDESC = orderby_sellcountdesc;
	}
	public static String getORDERBY_SELLPRICEASC() {
		return ORDERBY_SELLPRICEASC;
	}
	public static void setORDERBY_SELLPRICEASC(String orderby_sellpriceasc) {
		ORDERBY_SELLPRICEASC = orderby_sellpriceasc;
	}
	public static String getORDERBY_SELLPRICEDESC() {
		return ORDERBY_SELLPRICEDESC;
	}
	public static void setORDERBY_SELLPRICEDESC(String orderby_sellpricedesc) {
		ORDERBY_SELLPRICEDESC = orderby_sellpricedesc;
	}
	public static String getORDERBY_CREATEDATEDESC() {
		return ORDERBY_CREATEDATEDESC;
	}
	public static void setORDERBY_CREATEDATEDESC(String orderby_createdatedesc) {
		ORDERBY_CREATEDATEDESC = orderby_createdatedesc;
	}
	public static int getINITIALPAGESIZE() {
		return INITIALPAGESIZE;
	}
	public static void setINITIALPAGESIZE(int initialpagesize) {
		INITIALPAGESIZE = initialpagesize;
	}
	public static String getRECOMMEND() {
		return RECOMMEND;
	}
	public static void setRECOMMEND(String recommend) {
		RECOMMEND = recommend;
	}
	public static String getHOTSELL() {
		return HOTSELL;
	}
	public static void setHOTSELL(String hotsell) {
		HOTSELL = hotsell;
	}
	public static String getTOPTEN() {
		return TOPTEN;
	}
	public static void setTOPTEN(String topten) {
		TOPTEN = topten;
	}
	public static String getIMAGEFOLDERNAME() {
		return IMAGEFOLDERNAME;
	}
	public static void setIMAGEFOLDERNAME(String imagefoldername) {
		IMAGEFOLDERNAME = imagefoldername;
	}
	public static String getORDER_ADMINCONFIRMED() {
		return ORDER_ADMINCONFIRMED;
	}
	public static void setORDER_ADMINCONFIRMED(String order_adminconfirmed) {
		ORDER_ADMINCONFIRMED = order_adminconfirmed;
	}
	public static String getSUBJECTWELCOME() {
		return SUBJECTWELCOME;
	}
	public static void setSUBJECTWELCOME(String subjectwelcome) {
		SUBJECTWELCOME = subjectwelcome;
	}
	public static String getRELATEDPRODUCTFILESUFFIX() {
		return RELATEDPRODUCTFILESUFFIX;
	}
	public static void setRELATEDPRODUCTFILESUFFIX(String relatedproductfilesuffix) {
		RELATEDPRODUCTFILESUFFIX = relatedproductfilesuffix;
	}
	public static String getORDER_CANCELED() {
		return ORDER_CANCELED;
	}
	public static void setORDER_CANCELED(String order_cancel) {
		ORDER_CANCELED = order_cancel;
	}
	public static String getORDER_WAITCONFIRM() {
		return ORDER_WAITCONFIRM;
	}
	public static void setORDER_WAITCONFIRM(String order_waitconfirm) {
		ORDER_WAITCONFIRM = order_waitconfirm;
	}
	public static String getORDER_WAITPAYMENT() {
		return ORDER_WAITPAYMENT;
	}
	public static void setORDER_WAITPAYMENT(String order_waitpayment) {
		ORDER_WAITPAYMENT = order_waitpayment;
	}
	public static String getORDER_ADMEASUREPRODUCT() {
		return ORDER_ADMEASUREPRODUCT;
	}
	public static void setORDER_ADMEASUREPRODUCT(String order_admeasureproduct) {
		ORDER_ADMEASUREPRODUCT = order_admeasureproduct;
	}
	public static String getORDER_WAITDELIVER() {
		return ORDER_WAITDELIVER;
	}
	public static void setORDER_WAITDELIVER(String order_waitdeliver) {
		ORDER_WAITDELIVER = order_waitdeliver;
	}
	public static String getORDER_DELIVERED() {
		return ORDER_DELIVERED;
	}
	public static void setORDER_DELIVERED(String order_delivered) {
		ORDER_DELIVERED = order_delivered;
	}
	public static String getORDER_RECEIVED() {
		return ORDER_RECEIVED;
	}
	public static void setORDER_RECEIVED(String order_received) {
		ORDER_RECEIVED = order_received;
	}
	public static String getRELATEDPRODUCT() {
		return RELATEDPRODUCT;
	}
	public static void setRELATEDPRODUCT(String relatedproduct) {
		RELATEDPRODUCT = relatedproduct;
	}
	public static String getRELATEDARTICLE() {
		return RELATEDARTICLE;
	}
	public static void setRELATEDARTICLE(String relatedarticle) {
		RELATEDARTICLE = relatedarticle;
	}
	public static String getRELATEDARTICLESUFFIX() {
		return RELATEDARTICLESUFFIX;
	}
	public static void setRELATEDARTICLESUFFIX(String relatedarticlesuffix) {
		RELATEDARTICLESUFFIX = relatedarticlesuffix;
	}
	public static String getIPFileName() {
		return IPFileName;
	}
	public static void setIPFileName(String fileName) {
		IPFileName = fileName;
	}
	public static String getSERVERROOTURL() {
		return SERVERROOTURL;
	}
	public static void setSERVERROOTURL(String serverrooturl) {
		SERVERROOTURL = serverrooturl;
	}
	public static String getPRODUCT() {
		return PRODUCT;
	}
	public static void setPRODUCT(String product) {
		PRODUCT = product;
	}
	public static String getPRODUCT_IMAGE() {
		return PRODUCT_IMAGE;
	}
	public static void setPRODUCT_IMAGE(String product_image) {
		PRODUCT_IMAGE = product_image;
	}
	public static String getPRODUCT_ATTRIBUTES() {
		return PRODUCT_ATTRIBUTES;
	}
	public static void setPRODUCT_ATTRIBUTES(String product_attributes) {
		PRODUCT_ATTRIBUTES = product_attributes;
	}
	public static String getPRODUCT_BREADCRUMB() {
		return PRODUCT_BREADCRUMB;
	}
	public static void setPRODUCT_BREADCRUMB(String product_breadcrumb) {
		PRODUCT_BREADCRUMB = product_breadcrumb;
	}
	public static String getNEWS() {
		return NEWS;
	}
	public static void setNEWS(String news) {
		NEWS = news;
	}
	public static String getBLOG() {
		return BLOG;
	}
	public static void setBLOG(String blog) {
		BLOG = blog;
	}
	public static String getWIKI() {
		return WIKI;
	}
	public static void setWIKI(String wiki) {
		WIKI = wiki;
	}
	public static String getTHEME() {
		return THEME;
	}
	public static void setTHEME(String theme) {
		THEME = theme;
	}
	public static String getQUESTION() {
		return QUESTION;
	}
	public static void setQUESTION(String question) {
		QUESTION = question;
	}
	public static String getPRODUCTFILESUFFIX() {
		return PRODUCTFILESUFFIX;
	}
	public static void setPRODUCTFILESUFFIX(String productfilesuffix) {
		PRODUCTFILESUFFIX = productfilesuffix;
	}
	public static String getNEWSFILESUFFIX() {
		return NEWSFILESUFFIX;
	}
	public static void setNEWSFILESUFFIX(String newsfilesuffix) {
		NEWSFILESUFFIX = newsfilesuffix;
	}
	public static String getBLOGFILESUFFIX() {
		return BLOGFILESUFFIX;
	}
	public static void setBLOGFILESUFFIX(String blogfilesuffix) {
		BLOGFILESUFFIX = blogfilesuffix;
	}
	public static String getWIKIFILESUFFIX() {
		return WIKIFILESUFFIX;
	}
	public static void setWIKIFILESUFFIX(String wikifilesuffix) {
		WIKIFILESUFFIX = wikifilesuffix;
	}
	public static String getQUESTIONFILESUFFIX() {
		return QUESTIONFILESUFFIX;
	}
	public static void setQUESTIONFILESUFFIX(String questionfilesuffix) {
		QUESTIONFILESUFFIX = questionfilesuffix;
	}
	public static String getCATEGORYURLSUFFIX() {
		return CATEGORYURLSUFFIX;
	}
	public static void setCATEGORYURLSUFFIX(String categoryurlsuffix) {
		CATEGORYURLSUFFIX = categoryurlsuffix;
	}
	public static String getSEPERATORSLASH() {
		return SEPERATORSLASH;
	}
	public static void setSEPERATORSLASH(String seperatorslash) {
		SEPERATORSLASH = seperatorslash;
	}
	public static int getNEWSVALUE() {
		return NEWSVALUE;
	}
	public static void setNEWSVALUE(int newsvalue) {
		NEWSVALUE = newsvalue;
	}
	public static int getBLOGVALUE() {
		return BLOGVALUE;
	}
	public static void setBLOGVALUE(int blogvalue) {
		BLOGVALUE = blogvalue;
	}
	public static int getWIKIVALUE() {
		return WIKIVALUE;
	}
	public static void setWIKIVALUE(int wikivalue) {
		WIKIVALUE = wikivalue;
	}
	public static int getTHEMEVALUE() {
		return THEMEVALUE;
	}
	public static void setTHEMEVALUE(int themevalue) {
		THEMEVALUE = themevalue;
	}
	public static int getCATEGORYVALUE() {
		return CATEGORYVALUE;
	}
	public static void setCATEGORYVALUE(int categoryvalue) {
		CATEGORYVALUE = categoryvalue;
	}
	public static String getCATEGORY() {
		return CATEGORY;
	}
	public static void setCATEGORY(String category) {
		CATEGORY = category;
	}
	public static int getPRODUCTVALUE() {
		return PRODUCTVALUE;
	}
	public static void setPRODUCTVALUE(int productvalue) {
		PRODUCTVALUE = productvalue;
	}
	
	public static boolean isPOSTWELCOMEEMAIL() {
		return POSTWELCOMEEMAIL;
	}
	public static void setPOSTWELCOMEEMAIL(boolean postwelcomeemail) {
		POSTWELCOMEEMAIL = postwelcomeemail;
	}
	public static int getQUESTIONVALUE() {
		return QUESTIONVALUE;
	}
	public static void setQUESTIONVALUE(int questionvalue) {
		QUESTIONVALUE = questionvalue;
	}
	
	public static String generatePageSplit(int total,String baseHref){
			return generatePageSplit(total, SystemContext.getPagesize(), SystemContext.getPage(), baseHref);
	}
	public static String generatePageSplit(int total,int pageSize,String baseHref){
		return generatePageSplit(total, pageSize, SystemContext.getPage(), baseHref);
}
	public static String generatePageSplit(int total,int pageSize,int page,String baseHref){
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
			if(page==1){
				outStr.append(link(1,true,"1",baseHref)).append(link(2,false,"2",baseHref));
				return outStr.toString();
			}
			outStr.append(link(1,false,"1",baseHref)).append(link(2,true,"2",baseHref));
			return outStr.toString();
		}
		//以下的pageTotal>2,pageTotal:总页数，>2		page：当前页
		if(page>pageTotal||page<=0)return "";
		if(page!=1)outStr.append(link(page-1,false,"上一页",baseHref)).append(link(1,false,"1",baseHref));

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
			if(page>3)
			outStr.append(link(page-2,false,String.valueOf(page-2),baseHref));
			outStr.append(link(page-1,false,String.valueOf(page-1),baseHref))
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
	public static String link(int page,boolean thisLink,String words,String baseHref){
		if(thisLink) return "<a class='thisLink' href='"+baseHref+"page="+page+"'>"+words+"</a>";
		return "<a href='"+baseHref+"page="+page+"'>"+words+"</a>";
	}
}
