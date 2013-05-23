<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/templates/default/share/taglib.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML><HEAD>
<META http-equiv="pragma" content="no-cache">
<META http-equiv="Cache-Control" content="no-cache, must-revalidate">
<META http-equiv="Content-TYPE" content="text/html;charset=utf-8">
<%@ include file="/WEB-INF/templates/default/share/backstage_location_check.jsp" %>
<link href="css/global/address.css" rel="stylesheet" type="text/css">
<link href="css/global/bottom.css" rel="stylesheet" type="text/css">
<TITLE>订单列表</TITLE><link type="text/css" rel="stylesheet" href="css/product/list.css">
<SCRIPT LANGUAGE="JavaScript" src="js/Country.js" type="text/javascript"></SCRIPT>
  </head>
  
  <body>
  
  <table width="98%" border="0" cellspacing="1" cellpadding="2" align="center">
    <tr><td colspan="12" bgcolor="6f8ac4" align="right">
     总记录数:${pm.total}条  <div class="pagesplit">  <%
  
String pageSplits = ConstantString.generatePageSplit(
		((PageModel)request.getAttribute("pm")).getTotal(),
		"control/shopping/ordermanage.do?");
out.println(pageSplits);
%>  </div>
   </td></tr>
    <tr>
      <td bgcolor="6f8ac4"> <div align="center">选择</div></td>
      <td bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">订单号</font></div></td>
      <td bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">商品总金额</font></div></td>
      <td bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">订单总金额</font></div></td>
      <td bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">配送费用</font></div></td>
      <td bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">应付费用</font></div></td>
      <td bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">下单时间</font></div></td>
      <td bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">支付状态</font></div></td>
      <td bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">支付方式</font></div></td>
      <td bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">用户</font></div></td>
      <td bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">状态</font></div></td>
      <td nowrap bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">操作</font></div></td>
    </tr>
<!---------------------------LOOP START------------------------------>
<c:forEach items="${pm.datas}" var="order">
    <tr style="background:#6f8ac4;text-align:center">
      <td> <INPUT TYPE="checkbox" NAME="orderIds" value="${order.orderId}"></td>
      <td>${order.orderId}</td>
      <td>${order.totalPrice}</td>
      <td>${order.totalOrderPrice}</td>
      <td>${order.deliverFee}</td>
      <td>${order.totalPrice}</td>
      <td>${order.createDate}</td>
      <td>${order.paymentState}</td>
      <td>${order.paymentAndDeliverMethod.paymentTypeName}</td>
      <td>${order.user.username}（${order.user.email}）</td>
      <td>${order.orderState}</td>
      <td>
      <c:if test="${empty order.administratorName || order.administratorName==administrator.name}"><a href="control/shopping/ordermanage.do?method=getLockOrder&orderId=${order.orderId}">载入订单</a></c:if>
    <c:if test="${!empty order.administratorName && order.administratorName!=administrator.name}">  订单被${order.administratorName}锁定</c:if></a>
      </td>
	</tr>
</c:forEach>
    <!----------------------LOOP END------------------------------->
    <tr><td colspan="12">
    <a href="control/shopping/ordermanage.do?method=searchInput">订单查询</a>
    </td></tr>
</table>
  </body>
</html>
