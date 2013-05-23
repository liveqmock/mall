<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/page/share/taglib.jsp" %>
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


  </head>
  
  <body>
<form action="https://www.yeepay.com/app-merchant-proxy/node" method="post" name="yeepay"/>
<input type="hidden" name="p8_Url" value="http://www.angelinthebox.com/payment.do?method=response">
<input type="hidden" name="hmac" value=""/>
<input type="hidden" name="p2_Order" value="${shouldToPay}"/>
<input type="image" src="" alt="支付"> 
</form>
  </body>
</html>
