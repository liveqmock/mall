<%@ page contentType="text/html;charset=utf-8" %>
<%@ include file="/WEB-INF/page/share/taglib.jsp" %>
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<jsp:include page="/location_check.jsp"></jsp:include>
<link rel="stylesheet" href="css/vip.css" type="text/css">


</head>
<body bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<html:form action="control/center/email/manage" method="post" enctype="multipart/form-data">
<input type="hidden" name="method" value="add"/>
<br>
  <table width="90%" border="0" cellspacing="2" cellpadding="3" align="center">
    <tr bgcolor="6f8ac4"><td colspan="2"  > <font color="#FFFFFF"></font></td>
    </tr>
    <tr bgcolor="f5f5f5"> 
      <td width="22%" > <div align="right">邮件地址</div></td>
      <td width="78%"> <input type="text" name="address" size="50" maxlength="50"/>
        <font color="#FF0000">*</font></td>
    </tr>
	<tr bgcolor="f5f5f5"> 
      <td width="22%" > <div align="right">SMTP主机</div></td>
      <td width="78%"><input type="text" name="smtpHost" size="50" maxlength="100"/>
        <font color="#FF0000">*</font> </td>
    </tr>
    	<tr bgcolor="f5f5f5"> 
      <td width="22%" > <div align="right">SMTP主机端口</div></td>
      <td width="78%"><input type="text" name="smtpPort" maxlength="100"/>
        <font color="#FF0000">*</font> </td>
    </tr>
    	<tr bgcolor="f5f5f5"> 
      <td width="22%" > <div align="right">登录用户名</div></td>
      <td width="78%"><input type="text" name="username" size="80" maxlength="100"/>
        <font color="#FF0000">*</font> </td>
    </tr>
    	<tr bgcolor="f5f5f5"> 
      <td width="22%" > <div align="right">登录密码</div></td>
      <td width="78%"><input type="text" name="password" size="80" maxlength="100"/>
        <font color="#FF0000">*</font> </td>
    </tr>
    <tr bgcolor="f5f5f5"> 
      <td colspan="2"> <div align="center"> 
          <input type="submit" name="SYS_SET" value=" 确  定  " class="frm_btn">
         <input type="button" value=" 后  退   " class="frm_btn" onclick="javascript:history.back(-1)">
        </div></td>
    </tr>
  </table>
</html:form>
 
<br>
</body>
</html>