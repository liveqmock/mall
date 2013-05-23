<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/page/share/taglib.jsp" %><%@ page import="com.hnfealean.sport.enums.ConstantString" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML><HEAD>
<META http-equiv="pragma" content="no-cache">
<META http-equiv="Cache-Control" content="no-cache, must-revalidate">
<META http-equiv="Content-TYPE" content="text/html;charset=utf-8">
<%@ include file="/location_check.jsp" %>
<link href="css/global/address.css" rel="stylesheet" type="text/css">
<link href="css/global/bottom.css" rel="stylesheet" type="text/css"><link type="text/css" rel="stylesheet" href="css/product/list.css">
<link rel="stylesheet" href="js/calendar.css" type="text/css"><SCRIPT language=JavaScript src="js/calendar.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" src="js/Country.js" type="text/javascript"></SCRIPT>
    <title>查询订单</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  <form action="control/shopping/ordermanage.do" method="post">
  <input type="hidden" name="method" value="search"/>
   <table>
<tr>
<td>订单号：</td>
<td><input type="text" name="orderId"></td>
</tr>
<tr>
<td>订单状态</td>
<td><select name="orderState">
<option>--所有状态--</option>
<option value="<%=ConstantString.ORDER_WAITCONFIRM %>">待审核</option>
<option value="<%=ConstantString.ORDER_ADMINCONFIRMED %>">已审核</option>
<option value="<%=ConstantString.ORDER_ADMINCONFIRMED %>">已付款</option>
<option value="<%=ConstantString.ORDER_WAITPAYMENT %>">等待付款</option>
<option value="<%=ConstantString.ORDER_ADMEASUREPRODUCT %>">正在配货</option>
<option value="<%=ConstantString.ORDER_WAITDELIVER %>">等待发货</option>
<option value="<%=ConstantString.ORDER_DELIVERED %>">已发货</option>
<option value="<%=ConstantString.ORDER_RECEIVED %>">已收货</option>
<option value="<%=ConstantString.ORDER_CANCELED %>">已取消</option>
</select>
</td>
</tr>
<tr>
<td>用户名</td>
<td><input type="text" name="userName"/></td>
</tr>
<tr>
<td>用户邮箱</td>
<td><input type="text" name="userEmail"/></td>
</tr>
<tr>
<td>订单日期</td>
<td><input type="text" name="date" size="35" maxlength="30" onfocus="HS_setDate(this)"/></td>
</tr>
<tr>
<td colspan="2"><input type="submit" value="查询"/><input type="button" value="返回" onclick="history.back(-1)"/>
<input type="button" value="返回订单列表" onclick="javascript:window.location.href='/control/shopping/ordermanage.do'"/>
</td>
</tr>
</table>
</form>
  </body>
</html>
