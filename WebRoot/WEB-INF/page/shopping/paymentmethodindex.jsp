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
<TITLE>支付方式列表</TITLE><link type="text/css" rel="stylesheet" href="css/product/list.css">
<SCRIPT LANGUAGE="JavaScript" src="js/Country.js" type="text/javascript"></SCRIPT>
  </head>
  
  <body>
  
  <table width="98%" border="0" cellspacing="1" cellpadding="2" align="center">
    <tr><td colspan="12" bgcolor="6f8ac4" align="right">
     总记录数:${fn:length(paymentMethods)}条  
   </td></tr>
    <tr>
      <td width="3%" bgcolor="6f8ac4"> <div align="center">ID</div></td>
      <td width="10%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">名称</font></div></td>
      <td width="10%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">状态</font></div></td>
      <td nowrap bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">操作</font></div></td>
    </tr>
<!---------------------------LOOP START------------------------------>
<c:forEach items="${paymentMethods}" var="paymentMethod">
    <tr style="background:#6f8ac4;text-align:center">
      <td> <INPUT TYPE="checkbox" NAME="orderIds" value="${paymentMethod.id}">${paymentMethod.id}</td>
      <td>${paymentMethod.name}</td>
      <td>${paymentMethod.enable}</td>
      <td>
    <a href="control/center/paymentmethod/manage.do?method=edit&id=${paymentMethod.id}">编辑</a>
     <a href="control/center/paymentmethod/manage.do?method=delete&id=${paymentMethod.id}">删除</a>
      </td>
	</tr>
</c:forEach>
    <!----------------------LOOP END------------------------------->
      <tr>
      <td colspan="3"> <div align="center">
<a href="control/center/paymentmethod/manage.do?method=addInput">添加</a>
</div></td>
    </tr>
</table>
  </body>
</html>
