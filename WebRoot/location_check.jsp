<%@ page contentType="text/html;charset=utf-8" 
%><%@ page import="com.hnfealean.sport.web.WebProperty"%>
<!-- <base href="<%=WebProperty.readUrl("site.url") %>"> -->

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setAttribute("basePath",basePath);
%>
<base href="<%=basePath %>"/>
<link rel="icon" href="images/favicon.ico" type="image/x-icon" /> 
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon" />
<script language=JavaScript src="js/base.js"></script>
<link href="css/templates/default/base.css" rel="stylesheet" type="text/css" />
<link href="css/templates/default/front.css" rel="stylesheet" type="text/css"/>