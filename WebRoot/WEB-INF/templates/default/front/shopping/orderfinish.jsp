<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/templates/default/share/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<META http-equiv=Content-Language content=zh-CN>
<TITLE>订单完成</title> 
<%@ include file="/location_check.jsp" %><!-- 
<link href="css/style.css" rel="stylesheet" type="text/css">
<link href="css/global/header01.css" rel="stylesheet" type="text/css">
<link href="css/product/list.css" rel="stylesheet" type="text/css" />
<link href="css/global/topsell.css" rel="stylesheet" type="text/css">
<LINK href="css/new_cart.css" rel="stylesheet" type="text/css">
 -->
</HEAD>
<BODY>
<jsp:include page="/WEB-INF/templates/default/share/head.jsp"/>
<table class="orderfinish"><tr><td>

<p><strong><span class="h1">        感谢您的订购  <br>
</span></strong><br>
您的订单编号为&nbsp;&nbsp;&nbsp;${orderId }&nbsp;&nbsp;&nbsp; ，已经送交处理中心，很快您会收到一封确认邮件。	
<br>
</p>
<span>你选择的付款方式为"${payment.name }".${ payment.description}.</span><br><c:if test="${!empty payment.accessUrl}">
<span>您现在就可以去<a href="${ payment.accessUrl}?orderId=${orderId }">支付</a>了。</span></c:if>
<br>
您需要支付的金额为<span style="font-weight: bold; color: blue;">${shouldToPay }</span>元，祝您购物愉快！<br>
<br>
<font color="#f47a22"><span></span></font>
 
<table width="100%" border="0" cellpadding="0" cellspacing="0">
<tbody><tr>
<td bgcolor="#3881a9" height="26">&nbsp;<strong class="font14"><font color="#ffffff">如果您在付款过程中遇到问题，您可以拨打客服热线寻求帮助。如果7天没有支付成功，您的订单将被取消。</font></strong></td>
</tr>
</tbody></table>



</td></tr></table>

<br/>
<jsp:include page="/WEB-INF/templates/default/share/foot.jsp"/>
</BODY>
</html>
