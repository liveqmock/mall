<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/page/share/taglib.jsp" 
%><%@ page import="com.hnfealean.sport.model.payment.alipay.AlipayConfiguration" 
%><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<META http-equiv=Content-Language content=zh-CN>
<TITLE>支付宝帐号设置</title> 
<%@ include file="/location_check.jsp" %>
<link href="css/style.css" rel="stylesheet" type="text/css">
<link href="css/global/header01.css" rel="stylesheet" type="text/css">
<link href="css/product/list.css" rel="stylesheet" type="text/css" />
<link href="css/global/topsell.css" rel="stylesheet" type="text/css">
<LINK href="css/new_cart.css" rel="stylesheet" type="text/css">


  </head>
  

<body bgcolor="#FFFFFF" text="#000000" marginwidth="0" marginheight="0">
<style>
.aliconfigtable .e{width:400px}
</style>
<form action="control/payment/alipay/manage.do" method="post">
<input type="hidden" name="method" value="update"/>

  <table width="98%" border="0" cellspacing="1" cellpadding="2" align="center" class="aliconfigtable">
   <tr><td>支付方式：</td><td>

<%/*
<select>

<option value="${aliconfig.SECUREDTRANSACTIONS }"<c:if test="${aliconfig.paymentType==aliconfig.SECUREDTRANSACTIONS}"> seleted</c:if>>纯担保交易</option>
<option value="${aliconfig.DIRECTTRANSACTIONS }"<c:if test="${aliconfig.paymentType == aliconfig.DIRECTTRANSACTIONS}"> seleted</c:if>>即时到帐</option>
<option value="${aliconfig.BOTHSECUREDANDDIRECT }"<c:if test="${aliconfig.paymentType == aliconfig.BOTHSECUREDANDDIRECT}"> seleted</c:if>>双功能</option>
</select>
*/
%><input type="hidden" name="id" value="${aliconfig.id}"/>
   <input type="text" class="e" value="${aliconfig.paymentType}" name="paymentType"/></td><td></td></tr>
<tr><td>商家ID(合作身份者)：</td><td> <input type="text" class="e" value="${aliconfig.partner}"" name="partner"/></td><td> 
<div class="error" name="partner_description">
${aliconfig.partner_description}</div></td></tr>
<tr><td>交易安全码：</td><td> <input type="text" class="e" class="e" value="${aliconfig.key}" name="key"/></td><td>	<div class="error" name="key_description">${aliconfig.key_description}</div></td></tr>
<tr><td>收款账户：</td><td><input type="text" class="e" value="${aliconfig.seller_email}" name="seller_email"/></td><td> <div class="error" name="seller_email_description">${aliconfig.seller_email_description}</div></td></tr>
<tr><td>交易过程中服务器通知的页面 ：</td><td><input class="e" type="text" value="${aliconfig.notify_url}" name="notify_url"/></td><td> <div class="error" name="notify_url_description">${aliconfig.notify_url_description}</div></td></tr>
<tr><td>付完款后跳转的页面：</td><td><input class="e" type="text" value="${aliconfig.return_url}" name="return_url"/></td><td>	<div class="error" name="">${aliconfig.return_url_description}</div></td></tr>
<tr><td>网站商品的展示地址：</td><td><input class="e" type="text" value="${aliconfig.show_url}" name="show_url"/></td><td>	<div class="error" name="show_url_description">${aliconfig.show_url_description}</div></td></tr>
<tr><td>收款方名称：</td><td><input type="text" class="e" value="${aliconfig.mainname}" name="mainname"/></td><td>	<div class="error" name="mainname_description">${aliconfig.mainname_description}</div></td></tr>
<tr><td>字符编码格式：</td><td><input type="text" class="e" value="${aliconfig.input_charset}" name="input_charset"/></td><td>	<div class="error" name="input_charset_description">${aliconfig.input_charset_description}</div></td></tr>
<tr><td>签名方式：</td><td><input type="text" class="e" value="${aliconfig.sign_type}" name="sign_type"/></td><td>	<div class="error" name="sign_type_description">${aliconfig.sign_type_description}</div></td></tr>
<tr><td>访问模式：</td><td><input type="text" class="e" value="${aliconfig.transport}" name="transport"/></td><td>	<div class="error" name="transport_description">${aliconfig.transport_description}</div></td></tr>
<tr><td>支付宝接口网址：</td><td><input type="text" class="e" value="${aliconfig.alipaysubmit}" name="alipaysubmit"/></td><td>	<div class="error" name="alipaysubmit_description">${aliconfig.alipaysubmit_description}</div></td></tr>
  <tr><td cols="3"><input type="submit" value="确定修改"/></td></tr>
  </table>
</form>
</body>
</html>
