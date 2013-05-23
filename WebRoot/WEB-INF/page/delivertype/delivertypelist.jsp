<%@ page contentType="text/html;charset=utf-8" %>
<%@ include file="/WEB-INF/page/share/taglib.jsp" %>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<html>
<head>
<title>配送方式列表</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<jsp:include page="/location_check.jsp"></jsp:include>
<link rel="stylesheet" href="css/vip.css" type="text/css">
<link href="css/product/list.css" rel="stylesheet" type="text/css" /></head>

<body bgcolor="#FFFFFF" text="#000000" marginwidth="0" marginheight="0">

<form action="control/center/delivertype/manage.do" method="post">
 <table width="100%" border="0" cellspacing="1" cellpadding="2" align="center">
 <tr bgcolor="6f8ac4"><td width="5%">ID</td><td width="20%"> 名称</td><td width="30%"> 简短描述</td><td>操作</td></tr>
 <c:forEach items="${deliverTypes}" var="type">
    <tr>
    <td align="right">${type.id }</td>
    <td align="right">
  ${type.name }</td><td>${type.description }
</td><td><a href="control/center/delivertype/manage.do?method=editinput&id=${type.id }">编辑</a>
<a href="control/center/delivertype/manage.do?method=delete&id=${type.id }">删除</a>
   </td></tr>
   </c:forEach>
   <tr><td colspan="3">
<a href="control/center/delivertype/manage.do?method=addinput">添加新的配送方式</a>
</td></tr>
</form></body></html>