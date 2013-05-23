<%@ page contentType="text/html;charset=utf-8"%><%@ include file="/WEB-INF/templates/default/share/taglib.jsp"%><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head><meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
		<TITLE>我的帐户</TITLE>
		<META http-equiv=Content-Type content="text/html;charset=utf-8">

<%@ include file="/location_check.jsp"%>
<link href="css/style.css" rel="stylesheet" type="text/css"/>
<link href="css/global/header01.css" rel="stylesheet" type="text/css">
<link href="css/product/list.css" rel="stylesheet" type="text/css" />
<link href="css/global/topsell.css" rel="stylesheet" type="text/css">
<link href="css/your-account.css" rel="stylesheet" type="text/css"/>
</HEAD>
<body class="ProducTypeHome2">
	<jsp:include page="/WEB-INF/templates/default/share/head.jsp"/>
<div class='ucenterdiv'>
<div class="message">${message }</div>
<table border="0" cellpadding="10" cellspacing="15" width="100%">
<tbody>
<tr>
<td valign="top">
<nobr>
<span class="font-title">我的帐户</span>
</nobr>
<br>

<br>
<table border="0" cellpadding="5" cellspacing="0" width="100%">
<tbody>
<tr>
<td valign="top">
<div id="accountMenu">
<table border="0" cellpadding="3" cellspacing="0"
width="100%">
<tbody>
<tr>
<td class="font12b" bgcolor="#ddddcc">
&nbsp;订单信息
</td>

</tr>
</tbody>
</table>
<table bgcolor="#cccc99" border="0" cellpadding="0"
cellspacing="1" width="100%">
<tbody>
<tr>
<td bgcolor="#ffffff">
<table border="0" cellpadding="1" cellspacing="0"
width="100%">
<tbody>

<tr>
<td valign="top" width="50%">
<ul>
<li>
<a class="a-your-account"
href="user/manage.do?method=loadAllOrders">查看一个月内下的订单</a>
</li>
<li>
<a class="a-your-account"
href="user/manage.do?method=loadAllOrders">查看所有订单</a>
</li>
</ul>
</td>
<td valign="top" width="50%">
<ul>
<li>
<a class="a-your-account"
href="javascript:switchvieworder('DELIVERED')">查看已发货的订单</a>

</li>
<li>
<a class="a-your-account"
href="javascript:switchvieworder('CANCEL')">查看已取消的订单</a>
</li>
</ul>
</td>
</tr>
<tr>
<td colspan="2">

</td>
</tr>

</tbody>
</table>
</td>
</tr>
</tbody>
</table>
<br>
<table border="0" cellpadding="3" cellspacing="0"
width="100%">
<tbody>
<tr>

<td bgcolor="#ddddcc">
&nbsp;
<strong>帐户设置</strong>
</td>
</tr>
</tbody>
</table>
<table bgcolor="#cccc99" border="0" cellpadding="0"
cellspacing="1" width="100%">
<tbody>
<tr>
<td bgcolor="#ffffff">
<table border="0" cellpadding="3" cellspacing="0"
width="100%">

<tbody>
<tr>
<td colspan="2">
<strong class="style4">&nbsp;个人信息</strong>
</td>
</tr>
<tr>
<td valign="top" width="50%">
<ul>

<li>
<a class="a-your-account"
href="user/manage.do?method=editPassword">修改我的密码</a>
</li>

</ul>
</td>
<td valign="top" width="50%">
<ul>
<li>
<a class="a-your-account"
href="user/manage.do?method=editContactInfo">修改我的联系信息</a>
</li>
</ul>
</td>
</tr>
<tr>
<td colspan="2">
<hr noshade="noshade" size="-1" color="#cccc99">
</td>
</tr>

<tr>
<td colspan="2">
<strong class="style4">&nbsp;付款设置</strong>
</td>
</tr>
<tr>
<td valign="top">
<ul>
<li>
<a class="a-your-account"
href="customer/private/dummybank.go">我的电子货币</a>
</li>
<li>
<a class="a-your-account"
href="customer/private/cashticket.go">我的代金券</a>
</li>

</ul>
</td>
<td valign="top">
<ul>
<li>
<a class="a-your-account"
href="customer/private/integral.go">我的积分</a>
</li>
</ul>
</td>
</tr>
</tbody>
</table>
</td>
</tr>

</tbody>
</table>
</div>
</td>
<td valign="top" width="199">
<table bgcolor="#cccc99" border="0" cellpadding="0"
cellspacing="1" width="100%">
<tbody>
<tr>
<td bgcolor="#ffffff">
<table border="0" cellpadding="2" cellspacing="0"
width="100%">
<tbody>
<tr><td bgcolor="#eeeedd" valign="top"><div align="center">
<img src="images/buy/az-exit.gif"
height="20" width="19">
</div></td>
<td bgcolor="#eeeedd"><strong><a class="a-your-account" href="user/shoppingcart.do">购物车</a></strong></td></tr>
<tr><td bgcolor="#eeeedd" valign="top"><div align="center">
<img src="images/buy/az-exit.gif"
height="20" width="19">
</div></td>
<td bgcolor="#eeeedd"><strong><a class="a-your-account" href="user/shoppingcart.do">退出登录</a></strong></td></tr>
<tr><td bgcolor="#eeeedd" valign="top"><div align="center">
<img src="images/buy/az-exit.gif"
height="20" width="19">
</div></td>
<td bgcolor="#eeeedd"><strong><a class="a-your-account" href="user/userReg.do">注册</a></strong></td></tr>

</tbody>
</table>
</td>
</tr>
</tbody>

</table>
</td>	</tr></tbody></table></td></tr></tbody></table></div>			
						
	</BODY>
</html>