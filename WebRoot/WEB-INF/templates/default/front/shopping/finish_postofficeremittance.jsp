<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/templates/default/share/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<META http-equiv=Content-Language content=zh-CN>
<TITLE>订单完成</title> 
<%@ include file="/location_check.jsp" %>
<link href="css/style.css" rel="stylesheet" type="text/css">
<link href="css/global/header01.css" rel="stylesheet" type="text/css">
<link href="css/product/list.css" rel="stylesheet" type="text/css" />
<link href="css/global/topsell.css" rel="stylesheet" type="text/css">
<LINK href="css/new_cart.css" rel="stylesheet" type="text/css">

</HEAD>
<BODY><jsp:include page="/WEB-INF/templates/default/share/head.jsp"/>
<BR>
<h1>订单号:${orderId },应付金额:${shouldToPay }元</h1>
<br>
你选择的付款方式为"邮局汇款",在汇款单上务必写上您的订单号.如下是汇款信息:<br>
收款人:朱超云
<br>地址:安徽省蚌埠市宏业路255号
<br/>
<jsp:include page="/WEB-INF/templates/default/share/foot.jsp"/>
</BODY>
</HTML>
