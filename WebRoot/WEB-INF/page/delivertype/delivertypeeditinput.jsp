<%@ page contentType="text/html;charset=utf-8" %>
<%@ include file="/WEB-INF/page/share/taglib.jsp" %>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<html>
<head>
<title>配送方式修改</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<jsp:include page="/location_check.jsp"></jsp:include>
<link rel="stylesheet" href="css/vip.css" type="text/css">
<link href="css/product/list.css" rel="stylesheet" type="text/css" /></head>

<body bgcolor="#FFFFFF" text="#000000" marginwidth="0" marginheight="0">

<form action="control/center/delivertype/manage.do" method="post">
<input type="hidden" value="update" name="method" />
<input type="hidden" value="${delivertype.id }" name="id" />
 <table width="100%" border="0" cellspacing="1" cellpadding="2" align="center">
    <tr><td bgcolor="6f8ac4" align="right">
 名称</td><td bgcolor="6f8ac4"><input type="text" name="name" value="${delivertype.name }"/>
   </td></tr>
    <tr><td bgcolor="6f8ac4" align="right">
简短描述</td><td bgcolor="6f8ac4"><input type="text" name="description" value="${delivertype.description }" />
   </td></tr>
   <tr><td colspan="2"><input type="submit" value="确定" ></td></tr>
</form></body></html>