<%@ page contentType="text/html;charset=utf-8" %>
<%@ include file="/WEB-INF/templates/default/share/taglib.jsp" %>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<html>
<head>
<title>用户订单列表</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<jsp:include page="/WEB-INF/templates/default/share/backstage_location_check.jsp"></jsp:include>
<link rel="stylesheet" href="css/vip.css" type="text/css">


</head>

<body bgcolor="#FFFFFF" text="#000000" marginwidth="0" marginheight="0">

<form action="control/center/user/manage.do" method="post">
  <table width="100%" border="0" cellspacing="1" cellpadding="2" align="center">
    <tr><td colspan="4"  bgcolor="6f8ac4" align="right">
总共${fn:length(orders) }条订单
   </td></tr>
    <tr>
      <td width="5%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">订单ID</font></div></td>
      <td width="15%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">订单状态</font></div></td>
	  <td width="6%" bgcolor="6f8ac4"><div align="center"><font color="#FFFFFF">总金额</font></div></td>
	  <td nowrap bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">支付状态</font></div></td>
    </tr>
<!---------------------------LOOP START------------------------------>
<c:forEach items="${orders}" var="order">
    <tr>
      <td bgcolor="f5f5f5"> <div align="center">${order.orderId }</div></td>
	  <td bgcolor="f5f5f5"> <div align="center">${order.orderState}</div></td>
	  <td bgcolor="f5f5f5"> <div align="center">
	 ${order.payAbleFee}
	  </div></td>
	  <td bgcolor="f5f5f5"> <div align="center"> ${order.paymentState}</div></td>
</tr>
</c:forEach>
    <!----------------------LOOP END------------------------------->
    <tr> 
      <td bgcolor="f5f5f5" colspan="4" align="center"></td>
    </tr>
  </table>
 </form>
</body>
</html>