<%@ page import="com.hnfealean.sport.web.MD5" 
%><%@ 
page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"
%><%@ 
include file="/WEB-INF/page/share/taglib.jsp" 
%><%@ page import="java.util.*,java.text.*,java.lang.StringBuffer" %><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
response.addHeader("Etag",new SimpleDateFormat("MMddHHmm").format(Calendar.getInstance().getTime()));
//response.addHeader("ggg",request.getContextPath()+MD5.MD5Encode(request.getContextPath()));
response.addHeader("Cache-Control", "max-age=7200");
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
<link rel="canonical" href="<%=WebProperty.readUrl("site.url") %><c:if test="${!empty category}">${category.url}</c:if>"/>


<!-- <link href="css/global/header01.css" rel="stylesheet" type="text/css"/>
<link href="css/global/topsell.css" rel="stylesheet" type="text/css"/>
<link href="css/style.css" rel="stylesheet" type="text/css"/>
<link href="css/product/list.css" rel="stylesheet" type="text/css" />

<link href="css/front.css" rel="stylesheet" type="text/css"/>
 -->

<meta name="keyWords" content="${category.meta_KeyWords}">
<META name="description" content="${category.meta_Description}"><!-- 
<SCRIPT language=JavaScript src="js/xmlhttp.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript">

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

</SCRIPT> -->
</head>

<body class="ProducTypeHome2" onload="">


<jsp:include page="/WEB-INF/page/share/head.jsp"/>

     <div class="w position">您现在的位置: &nbsp;<a href="" name="linkHome" title="回到首页" alt="AngelInTheBox首页">Angel In The Box</a>  

   <c:set var="out" value=""/>
   <c:forEach items="${position}" var="category" varStatus="statu">
  <c:set var="out" value=" &gt;&gt;<a href='${category.url}'>${category.name }</a>${out}"/>
   </c:forEach>
    <c:out value="${out}" escapeXml="false"></c:out>（${childProducts.total}个）  
	</div>
<div class="w">
   <div class="left">
   <div class="browse_left">
         <div class="browse">
            <div class="browse_t"><center>${currentCategoryName }</center></div>
<ul class="left_category">
<c:choose>
	<c:when test="${empty childCategories}">
<c:forEach items="${sameLevelCategories }" var="sameLevelCategory">

<c:choose>
	<c:when test="${sameLevelCategory.id == category.id}">
	<li class="this"><a href="${sameLevelCategory.url}">${sameLevelCategory.name}</a></li>
<c:forEach items="${directChildCategories}" var="childCategory">
<li class="child"><a href="${childCategory.url}">${childCategory.name}</a>
</li>
</c:forEach>
	</c:when>	
<c:otherwise>
	<li><a href="${sameLevelCategory.url}">${sameLevelCategory.name}</a></li>
</c:otherwise>
</c:choose>

</c:forEach>	
</c:when>
<c:otherwise>
<c:forEach items="${childCategories}" var="childCategory">
<li><a href="${childCategory.url}" alt="${childCategory.name}" title="${childCategory.name}">
${childCategory.name}</a></li>

</c:forEach>
</c:otherwise>
</c:choose>
</ul>
	     </div>
	     <c:if test="${!empty hotsell}">
<div class="lefthotsell"><div class="title">${category.name }热卖</div>
<div class="content">
<ul class="addedcontainer">
<c:forEach items="${hotsell}" var="pro">
<li>
<div class="image"><a href="${pro.shtml_File_Name}">
<c:if test="${!empty pro.tempImageUrl}"><img src="${pro.tempImageUrl }"/></c:if>
<c:if test="${empty pro.tempImageUrl}"><img src="images/nopic.jpg"/></c:if>
</a></div>
<div class="name"><a href="${pro.shtml_File_Name}">${pro.name }</a></div>
<div class="price">￥${pro.sellPrice }</div>
</li>
</c:forEach></ul>
</div></div>
</c:if>
<c:if test="${!empty categoryNews}">
<div class="categorynews">
<c:forEach items="${categoryNews}" var="article">
<li><a href="">${article.title}</a>
</li>
</c:forEach>
</div>
</c:if>


</div>
</div><div class="right m_cnt">
<!--<c:if test="${!empty top10}">
<div class="top10">
<div class="title">${category.name }销售排行</div>
<div class="content">
<ul class="addedcontainer">
<c:forEach items="${top10}" var="pro">
<li>
<div class="image"><a href="${pro.shtml_File_Name}"><img src="${pro.tempImageUrl }"/></a></div>
<div class="name"><a href="${pro.shtml_File_Name}">${pro.name }</a></div>
<div class="price">￥${pro.sellPrice }</div>
</li>
</c:forEach><br class="clear">
</ul>
</div>
</div>
</c:if>-->
<!-- 
<c:if test="${!empty hotsell}">
<div class="hotsell"><div class="title">${category.name }热卖</div>
<div class="content">
<ul class="addedcontainer">
<c:forEach items="${hotsell}" var="pro">
<li>
<div class="image"><a href="${pro.shtml_File_Name}"><img src="${pro.tempImageUrl }"/></a></div>
<div class="name"><a href="${pro.shtml_File_Name}">${pro.name }</a></div>
<div class="price">￥${pro.sellPrice }</div>
</li>
</c:forEach><br class="clear"></ul>
</div></div>
</c:if> -->
<c:if test="${!empty recommend}">
<div class="recommend"><div class="title">${category.name } 推荐产品</div>
<div class="content">
<ul class="addedcontainer">
<c:forEach items="${recommend}" var="pro">
<li>
<div class="image"><a href="${pro.shtml_File_Name}"><img src="${pro.tempImageUrl }"/></a></div>
<div class="name"><a href="${pro.shtml_File_Name}">${pro.name }</a></div>
<div class="price">￥${pro.sellPrice }</div>
</li>
</c:forEach><br class="clear"></ul>
</div></div>
</c:if>
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

<a href="${childCategory.url}" alt="${childCategory.name}" title="${childCategory.name}"><img src="${childCategory.imageUrl}" alt="${childCategory.name}" title="${childCategory.name}"/></a>
</div>
</c:if> -->
<div class="childCategoryName">
<a href="${childCategory.url}" alt="${childCategory.name}" title="${childCategory.name}">
${childCategory.name}</a></div>
<!-- <div class="childCategoryDescription">${childCategory.description}</div>-->
</div>
</c:forEach>
</div>
</c:if>
--%>
<c:if test="${(!empty filter) and (!empty category)}">
<div id="select"><div class="mt"><h1>${category.name}</h1><strong>&nbsp;-&nbsp;商品筛选</strong></div></div>
</c:if>
<div class="filter">${filter }</div>
<div>
<div id="order">
<div class="item">排序：</div>
<ul>
<li<c:if test='${orderBy=="sellcountdesc"}'> class="curr"</c:if>><a href='${fn:replace(baseUrl, orderBy,"sellcountdesc")}'>销量</a><span></span></li>
<li<c:if test='${orderBy=="sellpriceasc"}'> class="curr"</c:if>><b></b><a href='${fn:replace(baseUrl, orderBy,"sellpriceasc")}'>价格低到高</a><span></span></li>
<li<c:if test='${orderBy=="sellpricedesc"}'> class="curr"</c:if>><a href='${fn:replace(baseUrl, orderBy, "sellpricedesc")}'>价格高到底</a><span></span></li>
<li<c:if test='${orderBy=="createdatedesc"}'> class="curr"</c:if>><a href='${fn:replace(baseUrl, orderBy, "createdatedesc")}'>上架时间</a><span></span></li>
</ul>
</div>
<div class="pagesplit"> 
<%
String orderby = (String)request.getAttribute("orderBy");
StringBuffer s =new StringBuffer();
String baseUrl= (String)request.getAttribute("baseUrl");//)-wholesale

s.append("?");
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
		baseUrl.substring(0,baseUrl.indexOf("?"))+s.toString());
out.println(pageSplits);
%>
</div>
<div class="extra">
<div class="fr"><span>共<strong>${childProducts.total}</strong>个商品</span></div></div>
</div>
<c:if test="${childProducts.total>0}">
	<div class='goods_pic'>
<!---------------------------LOOP START------------------------------>

<c:forEach items="${childProducts.datas}" var="product" varStatus="pro">


        <div class="detail">
           <a href="${product.shtml_File_Name}" title="${product.name}">
         
          <img class="productImage" src="${product.tempImageUrl}" alt="${product.name}" title="${product.name}"  border="0"/>
           
          
            </a>
            <br class="clearBoth"/>
           <h2><a href="${product.shtml_File_Name}" title="${product.name}">${product.name}</a></h2>
           <div class="save_number"><s>￥${product.marketPrice}</s>　<strong><em>￥${product.sellPrice}</em></strong>　
    <br class="clearBoth" />      
           节省：${product.marketPrice-product.sellPrice}</div>
           <div class="an_img" align="center"><a href="${product.shtml_File_Name}" title="${product.name}"><img src='images/sale.gif' width='84' height='24' border='0' class='a_1' /></a></div>
        </div>
        

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
    </div> 	  
    <span class="clr"></span>  
</div>   
<jsp:include page="/WEB-INF/page/share/foot.jsp"/>
</body>
</html>
