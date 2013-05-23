<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/page/share/taglib.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML><HEAD>
<META http-equiv="pragma" content="no-cache">
<META http-equiv="Cache-Control" content="no-cache, must-revalidate">
<META http-equiv="Content-TYPE" content="text/html;charset=utf-8">
<%@ include file="/location_check.jsp" %>
<link href="css/global/address.css" rel="stylesheet" type="text/css">
<link href="css/global/bottom.css" rel="stylesheet" type="text/css">
<TITLE>订单状态编辑</TITLE><link type="text/css" rel="stylesheet" href="css/product/list.css">
<SCRIPT LANGUAGE="JavaScript" src="js/Country.js" type="text/javascript"></SCRIPT>
  </head>
  
  <body>
  <form action="control/shopping/orderstatus/manage.do" method="post">
  <input  type="hidden" name="method" value="update"/>
  <input type="hidden" name="id" value="${status.id }"/>
  <table width="98%" border="0" cellspacing="1" cellpadding="2" align="center">
  <tr><td>名称</td><td><input type="text" name="name" value="${status.name }"/></td></tr>
  <tr><td>描述</td><td><textarea name="description">${status.description }</textarea> </td></tr>
  <tr><td colspan="2"><input type="submit" value="更改"/>
</td></tr>
  
</table>
</form>
  </body>
</html>
