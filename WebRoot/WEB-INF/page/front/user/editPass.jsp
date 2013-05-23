<%@ page contentType="text/html;charset=utf-8"%><%@ include file="/WEB-INF/page/share/taglib.jsp"%><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
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
	<jsp:include page="/WEB-INF/page/share/head.jsp"/>
<div class="editpassword">
<form action="user/manage.do?method=updatePass" method="post">
<input type="hidden" name="id" value="${id }"/>
<input type="hidden" name="validateCode" value="${validateCode }"/>
新密码：<input type="password" name="password" />至少6位，数字、字母、下划线
<input type="submit" value="更改"/> 
</form>
</div>		
						
	</BODY>
</html>