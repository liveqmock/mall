<%@ page contentType="text/html;charset=utf-8" %>
<%@ include file="/WEB-INF/page/share/taglib.jsp" %>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<html>
<head>
<title>网站用户信息编辑</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<jsp:include page="/location_check.jsp"></jsp:include>
<link rel="stylesheet" href="css/vip.css" type="text/css">


</head>

<body bgcolor="#FFFFFF" text="#000000" marginwidth="0" marginheight="0">

<form action="control/center/user/manage.do" method="post">
<input type="hidden" name="method" value="updateUser" />
<input type="hidden" name="userId" value="${user.id}" />
<table width="90%" border="0" cellspacing="2" cellpadding="3" align="center">
    <tr bgcolor="6f8ac4"><td colspan="2"  > <font color="#FFFFFF"></font></td>
    </tr>
    <tr bgcolor="f5f5f5"> 
      <td width="22%" > <div align="right">用户名</div></td>
      <td width="78%"> <input type="text" name="username" size="50" maxlength="50" value="${ user.username}"/>
        <font color="#FF0000">*</font></td>
    </tr>
     
  </table>
 </form>
</body>
</html>