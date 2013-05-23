<%@ page import="com.hnfealean.sport.web.MD5" 
%><%@ 
page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"
%><%@ 
include file="/WEB-INF/page/share/taglib.jsp" 
%><%@ page import="java.util.*"
%><%@ page import="java.text.*"
%><%@ page import="java.lang.StringBuffer" %><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
response.addHeader("Etag",new SimpleDateFormat("MMddHHmm").format(Calendar.getInstance().getTime()));
response.addHeader("ggg",request.getContextPath()+MD5.MD5Encode(request.getContextPath()));
%><html xmlns="http://www.w3.org/1999/xhtml">
<head><meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<title>${category.titleInPage4category} <c:set var="title" value=""
/><c:forEach items="${position}" var="category" varStatus="statu"
><c:if test="${statu.index==0}"><c:set var="title" value="${category.name }${out}"
/> </c:if></c:forEach><c:out value="${title}" escapeXml="false"
></c:out> AngelInTheBox电子商务系统</title>
<%@ include file="/location_check.jsp" %><%
//=request.getContextPath() 
%><%
//out.println("request.getContextPath():"+request.getContextPath());
//out.println("Protocol: " + request.getProtocol() + " "); out.println("Scheme: " + request.getScheme() + " "); out.println("Server Name: " + request.getServerName() + " " ); out.println("Server Port: " + request.getServerPort() + " "); out.println("Protocol: " + request.getProtocol() + " "); out.println("Server Info: " + getServletConfig().getServletContext().getServerInfo() + " "); out.println("Remote Addr: " + request.getRemoteAddr() + " "); out.println("Remote Host: " + request.getRemoteHost() + " "); out.println("Character Encoding: " + request.getCharacterEncoding() + " "); out.println("Content Length: " + request.getContentLength() + " "); out.println("Content Type: "+ request.getContentType() + " "); out.println("Auth Type: " + request.getAuthType() + " "); out.println("HTTP Method: " + request.getMethod() + " "); out.println("Path Info: " + request.getPathInfo() + " "); out.println("Path Trans: " + request.getPathTranslated() + " "); out.println("Query String: " + request.getQueryString() + " "); out.println("Remote User: " + request.getRemoteUser() + " "); out.println("Session Id: " + request.getRequestedSessionId() + " "); out.println("Request URI: " + request.getRequestURI() + " "); out.println("Servlet Path: " + request.getServletPath() + " "); out.println("Accept: " + request.getHeader("Accept") + " "); out.println("Host: " + request.getHeader("Host") + " "); out.println("Referer : " + request.getHeader("Referer") + " "); out.println("Accept-Language : " + request.getHeader("Accept-Language") + " "); out.println("Accept-Encoding : " + request.getHeader("Accept-Encoding") + " "); out.println("User-Agent : " + request.getHeader("User-Agent") + " "); out.println("Connection : " + request.getHeader("Connection") + " "); out.println("Cookie : " + request.getHeader("Cookie") + " "); out.println("Created : " + session.getCreationTime() + " "); out.println("LastAccessed : " + session.getLastAccessedTime() + " "); 
%>
<link rel="canonical" href="<%=ConstantString.SERVERROOTURL %><c:if test="${!empty category}">${category.url}<%=ConstantString.CATEGORYURLSUFFIX  %></c:if>"/>
<link href="css/style.css" rel="stylesheet" type="text/css">
<link href="css/global/header01.css" rel="stylesheet" type="text/css">
<link href="css/product/list.css" rel="stylesheet" type="text/css" />
<link href="css/global/topsell.css" rel="stylesheet" type="text/css">
<meta name="keyWords" content="${category.meta_KeyWords}">
<META name="description" content="${category.meta_Description}">
<SCRIPT language=JavaScript src="js/xmlhttp.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript">
<!--
	function getTopSell(typeid){
		var salespromotion = document.getElementById('salespromotion');		
		if(salespromotion && typeid!=""){
			salespromotion.innerHTML= "数据正在加载...";
			send_request(function(value){salespromotion.innerHTML=value},
					 "<html:rewrite action="product/switch"/>?method=topsell&typeid="+ typeid, true);
		}
	}
	function getViewHistory(){
		var viewHistoryUI = document.getElementById('viewHistory');		
		if(viewHistoryUI){
			viewHistoryUI.innerHTML= "数据正在加载...";
			send_request(function(value){viewHistoryUI.innerHTML=value},
					 "<html:rewrite action="product/switch"/>?method=getViewHistory", true);
		}
	}
	function pageInit(){
		getTopSell("${producttype.typeid}");
		getViewHistory();
	}
//-->
</SCRIPT>
</head>

<body class="ProducTypeHome2" onload="">


<jsp:include page="/WEB-INF/page/share/head.jsp"/>

     <div id="position">您现在的位置: &nbsp;<a href="" name="linkHome" title="回到首页" alt="AngelInTheBox首页">Angel In The Box</a>  

   <c:set var="out" value=""/>
   <c:forEach items="${position}" var="category" varStatus="statu">
  <c:set var="out" value=" &gt;&gt;<a href='${category.url}${categoryurlsuffix }'>${category.name }</a>${out}"/>
   </c:forEach>
    <c:out value="${out}" escapeXml="false"></c:out>（${childProducts.total}个）  
	</div>
<div class="main_content">
   <table><tr><td style="vertical-align:top;background:#F3F3F3 none repeat scroll 0 0">
   <div class="browse_left">
         <div class="browse">
            <div class="browse_t"><center>${currentCategoryName }</center></div>
<ul class="left_category">
<c:choose>
	<c:when test="${empty childCategories}">
<c:forEach items="${sameLevelCategories }" var="sameLevelCategory">

<c:choose>
	<c:when test="${sameLevelCategory.id == category.id}">
	<li class="this"><a href="${sameLevelCategory.url}<%=ConstantString.CATEGORYURLSUFFIX %>">${sameLevelCategory.name}</a></li>
<c:forEach items="${directChildCategories}" var="childCategory">
<li class="child"><a href="${childCategory.url}<%=ConstantString.CATEGORYURLSUFFIX %>">${childCategory.name}</a>
</li>
</c:forEach>
	</c:when>	
<c:otherwise>
	<li><a href="${sameLevelCategory.url}<%=ConstantString.CATEGORYURLSUFFIX %>">${sameLevelCategory.name}</a></li>
</c:otherwise>
</c:choose>

</c:forEach>	
</c:when>
<c:otherwise>
<c:forEach items="${childCategories}" var="childCategory">
<li><a href="${childCategory.url}<%=ConstantString.CATEGORYURLSUFFIX %>" alt="${childCategory.name}" title="${childCategory.name}">
${childCategory.name}</a></li>

</c:forEach>
</c:otherwise>
</c:choose>
</ul>
	     </div>
<div>
<c:if test="${!empty categoryNews}">
<c:forEach items="${categoryNews}" var="article">
<li><a href="">${article.title}</a>
</li>
</c:forEach>

</c:if>
</div>

</div>
</td><td class="righttd">	    
    <!--页面右侧分类列表部分开始-->
    <div class="browse_right">
<%--
<c:if test="${!empty childCategories}">
<div class="childCategorys">	
<c:forEach items="${childCategories}" var="childCategory">
<div class="childCategory">
<!-- 
<c:if test="${!empty childCategory.imageUrl}">
<div class="childCategoryImage">

<a href="${childCategory.url}<%=ConstantString.CATEGORYURLSUFFIX %>" alt="${childCategory.name}" title="${childCategory.name}"><img src="${childCategory.imageUrl}" alt="${childCategory.name}" title="${childCategory.name}"/></a>
</div>
</c:if> -->
<div class="childCategoryName">
<a href="${childCategory.url}<%=ConstantString.CATEGORYURLSUFFIX %>" alt="${childCategory.name}" title="${childCategory.name}">
${childCategory.name}</a></div>
<!-- <div class="childCategoryDescription">${childCategory.description}</div>-->
</div>
</c:forEach>
</div>
</c:if>
--%>
<div class="filter">${filter }</div>
<div>
<div id="order">
<div class="item">排序：</div>
<ul>
<li<c:if test='${orderBy=="sellcountdesc"}'> class="curr"</c:if>><a href='${fn:replace(baseUrl, orderBy,"sellcountdesc")}'>销量</a></li>
<li<c:if test='${orderBy=="sellpriceasc"}'> class="curr"</c:if>><b></b><a href='${fn:replace(baseUrl, orderBy,"sellpriceasc")}'>价格低到高</a></li>
<li<c:if test='${orderBy=="sellpricedesc"}'> class="curr"</c:if>><a href='${fn:replace(baseUrl, orderBy, "sellpricedesc")}'>价格高到底</a><span></span></li>
<li<c:if test='${orderBy=="createdatedesc"}'> class="curr"</c:if>><a href='${fn:replace(baseUrl, orderBy, "createdatedesc")}'>上架时间</a></li>
</ul>
</div>
<div class="pagesplit"> 
<%
String orderby = (String)request.getAttribute("orderBy");
StringBuffer s =new StringBuffer("?");
if(orderby==null||orderby.trim().length()==0){
	
}else{
	s.append("orderBy="+orderby+"&");
}
String show = (String)request.getAttribute("show");
if(show==null||show.trim().length()==0){
	
}else{
	s.append("show="+show+"&");
}
if(s.charAt(s.length()-1)!="&".charAt(0)){
	s.append("&");
}
String pageSplits = ConstantString.generatePageSplit(
		((PageModel)request.getAttribute("childProducts")).getTotal(),
		s.toString());
out.println(pageSplits);
%>
</div>
<div class="extra"><%--
<div id="showmode">
<div class="item">显示方式：</div>
<ul>
<li<c:if test='${show=="list"}'> class="curr"</c:if>><a href='${fn:replace(baseUrl, show,"list")}'>图文</a></li>
<li<c:if test='${show=="image"}'> class="curr"</c:if>><a href='${fn:replace(baseUrl, show,"image")}'>列表</a></li>
</ul>
</div>
--%><div class="fr"><span>共<strong>${childProducts.total}</strong>个商品</span></div></div>
</div>
<c:if test="${childProducts.total>0}">
	<div class='goods_pic'>
<!---------------------------LOOP START------------------------------>

<c:forEach items="${childProducts.datas}" var="product" varStatus="pro">
<div class='list_image'>
<ul>
<li class='image'>
<a href="product/${product.shtml_File_Name}.shtml" title="${product.name}">
<c:forEach items="${product.imagesAndStyles}" var="image" end="0">
<img class="productImage" src="${image.imageUrl}" alt="${product.name}" title="${product.name}"  border="0"/>
</c:forEach>
 </a>
</li>
<li class='des'><dl>
<dt><a href="product/${product.shtml_File_Name}.shtml" title="${product.name}">${product.name}</a></dt>
<dt>Description: ${product.meta_Description }</dt>
</dl>

          
</li>
<li class='price'><s>￥${product.marketPrice}</s>　<strong><em>￥${product.sellPrice}</em></strong>
 节省：${pSavedPrice [pro.index]}
</li>
</ul></div>
</c:forEach>

<!----------------------LOOP END------------------------------->
		<div class='emptybox'></div>
	</div>
 <div class="pagesplit"> 
<%
out.println(pageSplits);
%></div>

</c:if>	
 </div>

 <c:if test="${childProducts.total==0}">
 <div class="sorry_nopage"><p>抱歉！没有找到符合条件的商品。</p><p><a href="javascript:history.go(-1);" name="go_prev_page">[返回上一页]</a></p>
        </div>
 </c:if>
    </td></tr></table> 	    
</div>   

	<jsp:include page="/WEB-INF/page/share/foot.jsp"/>
</body>
</html>
