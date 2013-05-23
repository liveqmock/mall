<%@ page contentType="text/html;charset=utf-8" %>
<%@ include file="/WEB-INF/page/share/taglib.jsp" %>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<html>
<head>
<title>订单列表</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<%@ include file="/location_check.jsp"%>
<link href="css/style.css" rel="stylesheet" type="text/css"/>
<link href="css/global/header01.css" rel="stylesheet" type="text/css">
<link href="css/product/list.css" rel="stylesheet" type="text/css" />
<link href="css/global/topsell.css" rel="stylesheet" type="text/css">
<link href="css/your-account.css" rel="stylesheet" type="text/css"/>
<script language="JavaScript">
<!--
//-->
</script>

</head>

<body bgcolor="#FFFFFF" text="#000000" marginwidth="0" marginheight="0">
<jsp:include page="/WEB-INF/page/share/head.jsp"/>
<form action="control/center/user/manage.do" method="post">
  <table class="userorderlist" border="0" cellspacing="1" cellpadding="2" align="center">
    <tr><td colspan="10" align="right">
总共${fn:length(pm.datas) }条订单
   </td></tr>
   <tr class="userorderlisthead"> 
    <td nowrap bgcolor="#eeeedd" width="8%"> <div align="center"> 订单号 </div></td>
    <td nowrap bgcolor="#eeeedd" width="8%"><div align="center">总金额</div></td>
    <td nowrap bgcolor="#eeeedd" width="12%"><div align="center">应付款</div></td>
    <td width="7%"> <div align="center">配送费</div></td>
    <td width="10%"> <div align="center">订单状态</div></td>
    <td width="10%"> <div align="center">支付方式</div></td>
	<td width="10%"> <div align="center">配送方式</div></td>
    <td width="10%"> <div align="center">付款状态</div></td>
	<td width="16%"> <div align="center">下单日期</div></td>
	<td></td>
  </tr>
<!---------------------------LOOP START------------------------------>
<c:forEach items="${pm.datas}" var="order">
    <tr>
      <td> <div align="center">${order.orderId }</div></td>
      <td> <div align="center">${order.totalOrderPrice }</div></td>
	  <td> <div align="center">${order.payAbleFee}</div></td>
	  <td> <div align="center">
	 ${order.deliverFee}
	  </div></td>
	  <td> <div align="center"> ${order.orderState}</div></td>

	  <td> <div align="center"> 	  
	  <c:forEach items="${paymentMethods}" var="method">
			  <c:if test="${(method.name eq order.paymentAndDeliverMethod.paymentTypeName) && method.enable}">
			  
			  <c:if test="${!empty method.accessUrl && order.paymentState==false}">
			   <a href="${method.accessUrl}?orderId=${order.orderId}"> ${order.paymentAndDeliverMethod.paymentTypeName}</a>
			  </c:if>
			  <c:if test="${empty method.accessUrl}">${order.paymentAndDeliverMethod.paymentTypeName}
			  </c:if>
	 		 </c:if>
	  </c:forEach></div></td>
	  <td> <div align="center"> ${order.paymentAndDeliverMethod.deliverTypeName}</div></td>
	  <td> <div align="center"> <c:if test="${order.paymentState}">已付款</c:if><c:if test="${order.paymentState==false}">未付款</c:if></div></td>
	  <td> <div align="center"> ${order.createDate}</div></td>
	  <td> <div align="center"><a href="customer/order/manage.go?method=view&orderId=${order.orderId}">
	 	 <img border="0" src="images/global/az-want-to-view-orders.gif">
	  </a></div></td>
</tr>
</c:forEach>
    <!----------------------LOOP END------------------------------->
    <tr> 
      <td colspan="10" align="center"></td>
    </tr>
  </table>
 </form>
</body>
</html>