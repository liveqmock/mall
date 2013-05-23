<%@ page language="java" contentType="text/html;charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/templates/default/share/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
		<jsp:include page="/location_check.jsp"></jsp:include>
		<title>用户登录 </title>

		<!-- 
<link href="css/product/list.css" rel="stylesheet" type="text/css" />		
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="css/global/header01.css" rel="stylesheet" type="text/css"/>
<link href="css/product/list.css" rel="stylesheet" type="text/css" />
<link href="css/global/topsell.css" rel="stylesheet" type="text/css" />
<link href="css/global/header.css" rel="stylesheet" type="text/css" />
<link href="css/global/reg.css" rel="stylesheet" type="text/css"> 
		<SCRIPT language=JavaScript src="js/base.js"></SCRIPT>-->
		<SCRIPT LANGUAGE="JavaScript">
 <!--
 var prev="";
 hnfealean.avail=function(s){
	var m=eval(s);
	//alert(m);
	if(!m){
		document.getElementById("errorregemail").style.display="block";
		return;
	}
	document.getElementById("errorregemail").style.display="none";
	}
	 
 function checkAvailiable(email){
	 if(email=="") return;
	
	 var emailTest =/^(\w+)@(\w+)\.(\w+)$/;
	 if(emailTest.test(email)==false){
		document.getElementById("errorregemailformat").style.display="block";
		 return;
	 }
	 if(prev==email)	return;
	 document.getElementById("errorregemailformat").style.display="none";
	 C=document.createElement("SCRIPT");
		C.src="user/userReg.do?method=checkAvail&email="+email;
		C.charset="gb2312";
		//C.id="script"+id;
		document.body.appendChild(C);
		prev=email;
 }
	 
function checkForm(){
	var form = document.forms[0];
	if(form.email.value=="" || form.email.value.length<6){
		alert("请输入长度6位以上的邮箱地址");
		return false;
	}
	if(form.password.value=="" || form.password.value.length<6){
		alert("请输入长度6位以上的密码");
		return false;
	}
	if(form.password.value!=form.repassword.value){
		alert("两次输入的密码不正确");
		return false;
	}
	if(/[\W]/g.test(form.username.value)){
		alert("用户名中不能含有中文");
		return false;
	};
	return true;
}
function checkUsername(){
	var form = document.forms[0];
	var username = form.username.value;
	var viewobj = document.getElementById("checkResult");
	viewobj.innerHTML = "正在检测中...";
	send_request(function(value){viewobj.innerHTML=value},
	'<html:rewrite action="user/reg"/>?method=isUserExsit&username='+ username, true);
}
 //-->
 </SCRIPT>
	</head>

	<body>
		<jsp:include page="/WEB-INF/templates/default/share/head.jsp"/>
<c:if test="${!empty message}">
		<DIV id="errorinfo">
			<TABLE cellSpacing=1 cellPadding=8 width="78%" align="center"
				bgColor="#dd9988" border=0>
				<TBODY>
					<TR>
						<TD bgColor="#ffffd5" align="left">
							<IMG height="17" src="images/buy/exclamation-error-red.gif"
								width="17" style="float:left">
							<DIV id="errorMessage">${message}</DIV>
						</TD>
					</TR>
				</TBODY>
			</TABLE>
		</DIV>
</c:if>
		<div id="Content">
<table cellspacing="0" cellpadding="10" border="0" align="center" class="logindiv">
<tbody>
<tr>
<td class="logintd">
<form action="user/userLogin.do?directUrl=${directUrl}" method="post" name="userRegForm">
<input name="method" value="login" type="hidden" />
<table cellspacing="0" cellpadding="5"
border="0" align="center" class="logintable">
<tbody>
<tr>
<td colspan="2"><h3 class="titleInfo">用户登录</h3></td></tr>
<!--  onfocus="this.className='text highlight'" onblur="this.className='text'" -->
<tr><td>邮箱：</td><td><input class="txt" type="text" maxlength="20" size="20" id="email" name="email"></td></tr>
<tr><td>密码：</td><td><input class="txt" type="password" maxlength="20" size="20" id="password" name="password"></td></tr>
<tr><td colspan="2">

<input type="submit" value="登录" class="btnSubmit"/>
<div style="float:right"><a href="user/manage.do?method=forgetPassword" class="other">忘记密码？</a></div></td></tr>
</tbody>
</table>
</form>
</td>
<td class="regtd">
<form action="user/userReg.do" method="post" name="userRegForm" onsubmit="javascript:return checkForm()">
<input name="method" value="add" type="hidden"/>
<table cellspacing="0" cellpadding="5"
border="0" align="center" class="regtable">
<tbody>
<tr>
<td colspan="2"><h3 class="titleInfo">新用户注册</h3></td></tr>
<tr><td>邮箱：</td><td>
<input class="txt" type="text" maxlength="20" size="20" id="regemail" name="email" onblur="checkAvailiable(this.value)"/>
<div class="info" style="display:none" id="errorregemail">该邮箱已经被使用！是否忘记密码？<a href="user/manage.do?method=forgetPassword">密码重设</a></div>
<div class="info" style="display:none" id="errorregemailformat">Email地址格式不正确</div>
</td></tr>
<tr><td>密码：</td><td><input class="txt" type="password" maxlength="20" size="20" id="password" name="password"/></td></tr>
<tr><td colspan="2"><input type="submit" value="注册" class="btnSubmit"/></td></tr>
</tbody>
</table>
</form>
</td>
</tr>
</tbody></table>
		</div>
		<jsp:include page="/WEB-INF/templates/default/share/foot.jsp"/>
</html>