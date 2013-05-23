<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@page import="com.hnfealean.sport.pageset.SystemContext,com.hnfealean.sport.web.SeoUrl"%>
<%@ include file="/WEB-INF/page/share/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<META http-equiv=Content-Language content=zh-CN>
<TITLE>查询结果</title> 
<%@ include file="/location_check.jsp" %>
<link href="css/new_cart.css" rel="stylesheet" type="text/css" />
<link href="css/global/header01.css" rel="stylesheet" type="text/css" />
<link href="css/product/list.css" rel="stylesheet" type="text/css" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="css/global/topsell.css" rel="stylesheet" type="text/css" />
</head>
  
  <body>
  <jsp:include page="/WEB-INF/page/share/head.jsp"/>
<div class="searchresult">
<c:forEach items="${pm.datas}" var="entry">

<c:if test="${empty entry.imagesAndStyles}">empty</c:if>
		<div class="goodslist">
          <div class="goods">
		  <a href="${entry.shtml_File_Name}" target="_blank" class="pimage">
            <img src="${entry.tempImageUrl }" alt="${entry.name}"  border="0"/></a></div>
          <div class="goods_right">
                <h2><a href="${entry.shtml_File_Name}" target="_blank" title="${entry.name}">${entry.name}</a></h2>
	           <div class="message"><ul>
			  <c:if test="${!empty entry.brand}"> <li>品牌：${entry.brand.name}</li></c:if>
			  
			  <c:if test="${!empty entry.category}"> <li>所属类别：<a title='${entry.category.name}' class='position' href='${entry.category.url}'>${entry.category.name}</a></li></c:if>
			   </ul></div>
	           <div class="content">
	          ${entry.meta_Description }
	          <!--  <c:out value="${fn:substring(entry.detail,0,200)}" escapeXml="false"/> --></div>
	           <div class="message_bottom">
	                <div class="save"><s>￥${entry.marketPrice}</s>　<strong><em>￥${entry.sellPrice}</em></strong></div>
			        <div class="buy"><a href="${entry.shtml_File_Name}"><img src='/images/sale.gif' width='84' height='24' border='0' /></a></div>
	           </div>
          </div>
          <div class="empty_box"></div>
        </div>
</c:forEach>
<br class="clear"/>
 总记录数:${pm.total}条  <div class="pagesplit">  <%
  
String pageSplits = ConstantString.generatePageSplit(
		((PageModel)request.getAttribute("pm")).getTotal(),SystemContext.getPagesize(),
		"search-products.do?method=search"+(request.getParameter("key")==null?"&":"&key="+request.getParameter("key")+"&"));
out.println(pageSplits);

%>  </div>
</div>	     
<jsp:include page="/WEB-INF/page/share/foot.jsp"/>
  </body>
</html>
