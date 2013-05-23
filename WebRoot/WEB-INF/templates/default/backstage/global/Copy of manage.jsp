<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"
%><%@ 
include file="/WEB-INF/templates/default/share/taglib.jsp" 
%><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN"> 
<HTML><HEAD> 
<title>管理员登录</title>
<META http-equiv="pragma" content="no-cache"> 
<META http-equiv="Cache-Control" content="no-cache, must-revalidate"> 
<META http-equiv="Content-TYPE" content="text/html;charset=utf-8"> 
<jsp:include page="/WEB-INF/templates/default/share/backstage_location_check.jsp"></jsp:include>
<link href="stylesheet.css" rel="stylesheet" type="text/css" /> 
<script>
function init(){
if(top.location!==self.location)
	top.location=self.location;
}
</script>

</HEAD>
<body>
<script>
if(top.location!==self.location)
	top.location=self.location;

</script>
<center>
<div id="login">
<form name="login" method="post" action="manage.do" onsubmit="javascript: return check_null();">
  <fieldset>
  <p><label></label></p>
  <p><label>管理员用户名</label><br>

 <input type="text" size="24" name="name" id="name"/>
</p>
<br class="clearBoth">
  <p><label>管理员密码</label><br>

<input type="password" size="24" name="password"  id="password"/>
<br class="clearBoth"></p>


<p class="submit"> 	
   <input type="submit" value="Login" id="submit" name="submit" style="border-width:1px;">
 
   </p>
 <p>
</p>
</fieldset>
</form>

 <p class="loginbottom"><label>忘记密码？</label></p>
  <p class="loginbottom"><label>密码重设</label></p>
</div>
<div id="b"></div>
</center>
</body></html>