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


<table cellspacing="15" cellpadding="0" border="0" align="center" width="100%">
  <tbody>
  <tr>
    <td align="middle" valign="top">

      <table cellspacing="0" cellpadding="0" border="0" align="center" width="65%">
        <tbody>
        <tr>
          <td width="99%" valign="top" colspan="4">
            <table cellspacing="0" cellpadding="0" border="0" align="center" width="100%">
              <tbody>
              <tr>
                <td bgcolor="#ddddcc" align="left" width="10" valign="top"><img height="28" border="0" width="10" src="images/global/az-tan-top-left-round-corner.gif"></td>
                <td nowrap="" height="28" bgcolor="#ddddcc" width="20%" valign="bottom"><span class="title">重设新密码&nbsp;&nbsp;</span></td>
                <td bgcolor="#ddddcc" align="right" width="79%" valign="bottom">&nbsp;</td>
                <td bgcolor="#ddddcc" align="right" width="10" valign="top"><img height="28" border="0" width="10" src="images/global/az-tan-top-right-round-corner.gif"></td></tr></tbody></table></td></tr></tbody></table>
      <table cellspacing="0" cellpadding="0" border="0" bgcolor="#ddddcc" align="center" width="65%" id="">
        <tbody>
        <tr bgcolor="#ddddcc">
          <td bgcolor="#ddddcc" valign="top">
            <table cellspacing="3" cellpadding="0" border="0" bgcolor="#ddddcc" align="center" width="100%">
              <tbody>
              <tr>
                <td bgcolor="#ffffff" valign="top">
                  <table height="200" cellspacing="10" cellpadding="0" border="0" align="center" width="100%" class="font9">
                    <tbody>
                    <tr>
                      <td valign="top">
                        <form onsubmit="javascript:return validateForm(this)" method="post" action="user/manage.do">
						<input type="hidden" name="method" value="getPassword"/>
                        <table cellspacing="4" cellpadding="4" border="0" width="100%">
                          <tbody>
                          <tr align="left">
                            <td colspan="3" class="font-error"></td></tr>
                          <tr align="left">
                            <td colspan="3" class="font14"></td>
                          </tr>
                          <tr align="left">
                            <td colspan="3"><span class="font-step">第一步：</span><strong class="font14b">请查看您帐户设定的电子邮件信箱</strong></td></tr>
                           <tr align="left">
                            <td colspan="3">为了验证您的身份，我们已发出了通知信，请在24小时内点击邮件内的链接继续设置新密码。</td></tr>
                          <tr align="left">
                            <td colspan="3">
                              <hr size="1" noshade="" class="dashes">
                            </td></tr>
                          <tr align="left">
                            <td colspan="3" class="font9">
                              <p class="font9"> 如果您忘记密码且不再使用注册时的E-mail，<a href="user/userReg.do" class="a-your-account">建议创建一个新帐户</a>。</p></td></tr></tbody></table>
</form>
</td></tr></tbody></table></td></tr></tbody></table></td></tr></tbody></table>
      <table cellspacing="0" cellpadding="0" border="0" bgcolor="#ddddcc" align="center" width="65%">
        <tbody>
        <tr valign="bottom">
          <td height="10" bgcolor="#ddddcc" align="left"><img height="10" border="0" width="10" src="images/global/az-tan-bottom-left-round-corner.gif"></td>
          <td height="10" bgcolor="#ddddcc" align="right"><img height="10" border="0" width="10" src="images/global/az-tan-bottom-right-round-corner.gif"></td></tr></tbody></table></td></tr></tbody></table>

</div>		
		<jsp:include page="/WEB-INF/page/share/foot.jsp"/>						
	</BODY>
</html>