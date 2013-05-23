<%@ page contentType="text/html;charset=utf-8" %>
<%@ include file="/WEB-INF/templates/default/share/taglib.jsp" %>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<html>
<head>
<title>邮件帐号列表</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<jsp:include page="/WEB-INF/templates/default/share/backstage_location_check.jsp"></jsp:include>
<link rel="stylesheet" href="css/vip.css" type="text/css">


</head>

<body bgcolor="#FFFFFF" text="#000000" marginwidth="0" marginheight="0">

<form action="control/center/email/manage.do" method="post">
<input type="hidden" value="deletes" name="method" />
  <table width="100%" border="0" cellspacing="1" cellpadding="2" align="center">
    <tr><td colspan="7"  bgcolor="6f8ac4" align="right"><font color="#FFFFFF">总记录数:${fn:length(emails)}条 |</font>
 
   </td></tr>
    <tr>
      <td width="5%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">邮件帐号ID</font></div></td>
      <td width="10%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">邮件地址</font></div></td>
      <td width="50%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">SMTP主机</font></div></td>
	  <td bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">SMTP主机端口</font></div></td>
	  <td bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">登录用户名</font></div></td>
	  <td bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">登录密码</font></div></td>
	  <td bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">操作</font></div></td> 
    </tr>
<!---------------------------LOOP START------------------------------>
<c:forEach items="${emails}" var="email">
<tr>
      <td bgcolor="f5f5f5"> <div align="center">
      <INPUT TYPE="checkbox" NAME="ids" value="${email.id}">${email.id }</div></td>
	  <td bgcolor="f5f5f5"> <div align="left">
	  <a href="control/center/email/manage.do?method=edit&id=${email.id}">${email.address }</a></div></td>
	  <td bgcolor="f5f5f5"> <div align="left">${email.smtpHost}</div></td>	  
	  <td bgcolor="f5f5f5">  ${email.smtpPort}</td>
	  <td bgcolor="f5f5f5">${email.username}  </td>
	  <td bgcolor="f5f5f5">${email.password}  </td>
	  <td bgcolor="f5f5f5">
	  <a href="control/center/email/manage.do?method=edit&id=${email.id}" >编辑</a>
	  <a href="control/center/email/manage.do?method=delete&id=${email.id}" >删除</a>  </td>
</tr>
</c:forEach>
    <!----------------------LOOP END------------------------------->
    <tr> 
      <td bgcolor="f5f5f5" colspan="7" align="center">
      <input type="submit" value="删除" />
        <a href="control/center/email/manage.do?method=addInput" >添加新邮件帐号</a>
      </td>
    </tr>
  </table>
 </form>
</body>
</html>