<%@ page import="com.hnfealean.sport.web.MD5" 
%><%@ 
page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"
%>
<%@page import="com.hnfealean.sport.pageset.PageModel"%><%@page import="com.hnfealean.sport.pageset.SystemContext"%><%@ 
include file="/WEB-INF/page/share/taglib.jsp" 
%><%@ page import="java.util.*"
%><%@ page import="java.text.*"
%><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
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
	<div class="filter">${filter }</div>
<%--       <div class="select_reorder">--%>
<%--              <div class="reorder_l">请选择排序方式： <c:if test="${'sellCountDesc'==orderBy}"><strong><em>销量多到少</em></strong></c:if><c:if test="${'sellCountDesc'!=orderBy}">--%>
<%--              <a title='按销量降序' href="${category.url }<%=ConstantString.CATEGORYURLSUFFIX %>?orderBy=sellCountDesc&typeid=${param.typeid}&sex=${param.sex }&brandId=${param.brandId}&style=imagetext">销量多到少</a></c:if>--%>
<%--			  | <c:if test="${'sellPriceDesc'==orderBy}"><strong><em>价格高到低</em></strong></c:if><c:if test="${'sellPriceDesc'!=orderBy}">--%>
<%--			  <a title='价格高到低' href="${category.url }<%=ConstantString.CATEGORYURLSUFFIX %>?orderBy=sellPriceDesc&typeid=${param.typeid}&sex=${param.sex }&brandId=${param.brandId}&style=imagetext">价格高到低</a></c:if>--%>
<%--			  | <c:if test="${'sellPriceAsc'==orderBy}"><strong><em>价格低到高</em></strong></c:if><c:if test="${'sellPriceAsc'!=orderBy}">--%>
<%--			  <a title='价格低到高' href="${category.url }<c:if test="${!empty category.url}"><%=ConstantString.CATEGORYURLSUFFIX %></c:if>?orderBy=sellPriceAsc&typeid=${param.typeid}&sex=${param.sex }&brandId=${param.brandId}&style=imagetext">价格低到高</a></c:if>--%>
<%--			  | <c:if test="${empty orderBy}"><strong><em>最近上架时间</em></strong></c:if><c:if test="${!empty orderBy}">--%>
<%--			  <a title='最近上架时间' href="${category.url }<c:if test="${!empty category.url}"><%=ConstantString.CATEGORYURLSUFFIX %></c:if>?orderBy=createDateDesc&typeid=${param.typeid}&sex=${param.sex }&brandId=${param.brandId}&style=imagetext">最近上架时间</a></c:if> </div>--%>
<%--              --%>
<%--		   <!--    <div class="reorder_r">显示方式：<c:if test="${param.style=='imagetext'}"><strong><em>图文版</em></strong></c:if><c:if test="${param.style!='imagetext'}">--%>
<%--		      <a href="${category.url }<%=ConstantString.CATEGORYURLSUFFIX %>?orderBy=${orderBy}&typeid=${param.typeid}&sex=${param.sex }&brandId=${param.brandId}&style=imagetext">图文版</a></c:if> |--%>
<%--		      <c:if test="${param.style=='imagetext'}"><a href="<html:rewrite action="product/list"/>?orderBy=${orderBy}&typeid=${param.typeid}&sex=${param.sex }&brandId=${param.brandId}&style=image">图片版</a>--%>
<%--		      </c:if><c:if test="${param.style!='imagetext'}"><strong><em>图片版</em></strong></c:if> -->--%>
<%--		      </div>--%>
<%--			<div class="emptybox"></div>--%>
<%--			 <div class="SubCategoryBox">--%>
<%--			 <div class="brand">--%>
<%--				<div class="FindByHint">按<strong>品牌</strong>选择：</div>--%>
<%--				<ul class="CategoryListTableLevel1">--%>
<%--			--%>
<%--			--%>
<%--			<c:forEach items="${brands}" var="brand">--%>
<%--			--%>
<%--<li><a href="${category.url }<%=ConstantString.CATEGORYURLSUFFIX %>?orderBy=${orderBy}&typeid=${param.typeid}&brandId=${brand.id}&sex=${param.sex}">--%>
<%--	<c:if test="${param.brandId==brand.id}"><em class="em"></c:if>--%>
<%--	${brand.name}--%>
<%--<c:if test="${param.brandId==brand.id}"></em></c:if>--%>
<%--	--%>
<%--	</a>--%>
<%--</li>--%>
<%----%>
<%--</c:forEach>--%>
<%----%>
<%----%>
<%--<li><a href="${category.url }<%=ConstantString.CATEGORYURLSUFFIX %>?orderBy=${orderBy}&typeid=&brandId=${brand.id}&sex=${param.sex}">--%>
<%--<c:if test="${param.brandId==''}"><em class="em"></c:if>--%>
<%--全部品牌--%>
<%--<c:if test="${param.brandId==''}"></em></c:if>--%>
<%--</a>--%>
<%--</li>--%>
<%--				</ul>--%>
<%----%>
<%--			 </div>--%>
<%--			 </div>--%>
<%--			 <div class="SubCategoryBox">--%>
<%--				<div class="FindByHint">按<strong>男女款</strong>选择：</div>--%>
<%--				<ul class="CategoryListTableLevel1">--%>
<%--				<li><a href="${category.url }<%=ConstantString.CATEGORYURLSUFFIX %>?orderBy=${orderBy}&typeid=${param.typeid}&sex=MAN&brandId=${param.brandId}&style=${param.style}">--%>
<%--				<c:if test="${param.sex=='MAN'}"><em></c:if>--%>
<%--				男款--%>
<%--				<c:if test="${param.sex=='MAN'}"></em></c:if>--%>
<%--				</a></li>--%>
<%--				<li><a href="${category.url }<%=ConstantString.CATEGORYURLSUFFIX %>?orderBy=${orderBy}&typeid=${param.typeid}&sex=WOMEN&brandId=${param.brandId}&style=${param.style}">--%>
<%--				<c:if test="${param.sex=='WOMEN'}"><em></c:if>--%>
<%--				女款--%>
<%--				<c:if test="${param.sex=='WOMEN'}"></em></c:if>--%>
<%--				</a></li>--%>
<%--				<li><a href="${category.url }<%=ConstantString.CATEGORYURLSUFFIX %>?orderBy=${orderBy}&typeid=${param.typeid}&sex=NONE&brandId=${param.brandId}&style=${param.style}">--%>
<%--				<c:if test="${param.sex=='NONE'}"><em></c:if>--%>
<%--				男女均可--%>
<%--				<c:if test="${param.sex=='NONE'}"></em></c:if>--%>
<%--				</a></li>--%>
<%--				--%>
<%--				</ul>--%>
<%--			 </div>--%>
<%--			 <div class="SubCategoryBox">--%>
<%--				<div class="FindByHint">显示全部：</div>--%>
<%--				<ul class="CategoryListTableLevel1">--%>
<%--			 <li><a class="red" href="${category.url }<%=ConstantString.CATEGORYURLSUFFIX %>?orderBy=${orderBy}&typeid=${param.typeid}">--%>
<%--				全部--%>
<%--				</a></li>--%>
<%--		</div>--%>
	 
	     <div id="divNaviTop" class="number">
<pg:pager items="${childProducts.total }" url="" export="currentPageNumber=pageNumber" isOffset="false" maxPageItems="6">

          <div class="number_l">以下<span class='number_white'>${childProducts.total}</span>条结果按<span class="number_white">
				<c:choose>
				  <c:when test="${'sellCountDesc'==orderBy}">销量多到少</c:when>
				  <c:when test="${'sellPriceDesc'==orderBy}">价格高到低</c:when>
				  <c:when test="${'sellPriceAsc'==orderBy}">价格低到高</c:when>

				  <c:otherwise>最近上架时间</c:otherwise>
				</c:choose>
			  </span>排列
			  <!--显示方式是<span class="number_white"><c:if test="${param.style=='imagetext'}">图文版</c:if><c:if test="${param.style!='imagetext'}">图片版</c:if></span> -->　每页显示<span class="number_white">6</span>条</div>
	<%--	<pg:param name="orderBy" value="${orderBy}"/>
<pg:param name="brandId" value="${brandId}"/>--%>
	<pg:first><a href="${category.url }<c:if test="${!empty category.url}"><%=ConstantString.CATEGORYURLSUFFIX %></c:if>${pageUrl}">首页</a></pg:first>
	<pg:prev><a href="${category.url }<c:if test="${!empty category.url}"><%=ConstantString.CATEGORYURLSUFFIX %></c:if>${pageUrl }">前页</a></pg:prev>
	<pg:pages>
		<c:choose>
		<c:when test="${ currentPageNumber eq pageNumber}">
		
		<font color="red">当前页:</font>第${pageNumber}<font color="#red">页  |</font>
		</c:when>
		<c:otherwise>
		<a href="${category.url }<c:if test="${!empty category.url}"><%=ConstantString.CATEGORYURLSUFFIX %></c:if>${pageUrl }">${pageNumber }</a>
		</c:otherwise>
		</c:choose>
	</pg:pages>
	<pg:next><a href="${category.url }<c:if test="${!empty category.url}"><%=ConstantString.CATEGORYURLSUFFIX %></c:if>${pageUrl }">下页</a></pg:next>
	<pg:last><a href="${category.url }<c:if test="${!empty category.url}"><%=ConstantString.CATEGORYURLSUFFIX %></c:if>${pageUrl }">尾页</a></pg:last>
		     
</pg:pager> 
</div>
<c:if test="${childProducts.total>0}">
	<div class='goods_pic'>
<!---------------------------LOOP START------------------------------>

<c:forEach items="${childProducts.datas}" var="product" varStatus="pro">


        <div class="detail">
           <a href="product/${product.shtml_File_Name}.shtml" title="${product.name}" target="_blank">
           <c:forEach items="${product.imagesAndStyles}" var="image">
          <img class="productImage" src="${image.imageUrl}" alt="${product.name}" title="${product.name}"  border="0"/>
           
           </c:forEach>
            </a>
            <br class="clearBoth"/>
           <h2><a href="product/${product.shtml_File_Name}.shtml" title="${product.name}" target="_blank">${product.name}</a></h2>
           <div class="save_number"><s>￥${product.marketPrice}</s>　<strong><em>￥${product.sellPrice}</em></strong>　
    <br class="clearBoth" />      
           节省：${pSavedPrice [pro.index]}</div>
           <div class="an_img" align="center"><a href="product/${product.shtml_File_Name}.shtml" title="${product.name}" target="_blank""><img src='images/sale.gif' width='84' height='24' border='0' class='a_1' /></a></div>
        </div>
        

</c:forEach>

<!----------------------LOOP END------------------------------->
		<div class='emptybox'></div>
	</div>
 
	     <div id="divNaviBottom" class="page_number">
	     <div class="turnpage turnpage_bottom">	
<pg:pager items="${childProducts.total }" url="" export="currentPageNumber=pageNumber" isOffset="false" maxPageItems="<%=SystemContext.getPagesize() %>">
<c:if test="${! empty orderBy}">
	<pg:param name="orderBy" value="${orderBy}"/></c:if>
	<c:if test="${! empty brandId}"><pg:param name="brandId" value="${brandId}"/></c:if>
<c:if test="${! empty frontProductForm.filter}">	<pg:param name="filter" value="${frontProductForm.filter}"/></c:if>
	<pg:first><a href="${category.url }<c:if test="${!empty category.url}"><%=ConstantString.CATEGORYURLSUFFIX %></c:if>${pageUrl}">首页|</a></pg:first>
	<pg:prev><a href="${category.url }<c:if test="${!empty category.url}"><%=ConstantString.CATEGORYURLSUFFIX %></c:if>${pageUrl }">前页</a></pg:prev>
	<pg:pages>
		<c:choose>
		<c:when test="${ currentPageNumber eq pageNumber}">
		<font color="red">当前页:</font>第${pageNumber}<font color="#red">页  |</font>
		</c:when>
		<c:otherwise>
		<a href="${category.url }<c:if test="${!empty category.url}"><%=ConstantString.CATEGORYURLSUFFIX %></c:if>${pageUrl }">${pageNumber }</a>
		</c:otherwise>
		</c:choose>
	</pg:pages>
	<pg:next><a href="${category.url }<c:if test="${!empty category.url}"><%=ConstantString.CATEGORYURLSUFFIX %></c:if>${pageUrl }">下页</a></pg:next>
	<pg:last><a href="${category.url }<c:if test="${!empty category.url}"><%=ConstantString.CATEGORYURLSUFFIX %></c:if>${pageUrl }">尾页</a></pg:last>
		  
</pg:pager>    </div>
         </div>
</c:if>	
 </div>

 <c:if test="${childProducts.total==0}">
 <div class="sorry_nopage"><p>抱歉！没有找到符合条件的商品。</p><p><a href="javascript:history.go(-1);" name="go_prev_page">[返回上一页]</a></p>
        </div>
 </c:if>
    </td></tr></table> 	    
</div>   
<div class="pagesplit"> 
<%

PageModel pm = (PageModel)request.getAttribute("childProducts");
int pageStr = (Integer)request.getAttribute("page");
int pageNow=0;
if(pageStr==0){
	pageNow=1;
}else{
	pageNow=pageStr;
}
int total = pm.getTotal();
//out.println(pageNow);
out.println(ConstantString.generatePageSplit(total,SystemContext.getPagesize(),pageNow,"?"));

%></div>
	<jsp:include page="/WEB-INF/page/share/foot.jsp"/>
</body>
</html>
