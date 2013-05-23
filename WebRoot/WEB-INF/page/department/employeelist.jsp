<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/page/share/taglib.jsp" %>
<html>
<head>
<title>员工显示</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<link rel="stylesheet" href="css/vip.css" type="text/css">
<script language="JavaScript">
<!--
	//到指定的分页页面
	function topage(page){
		var form = document.forms[0];
		form.page.value=page;
		form.submit();
	}
//-->
</script>
<SCRIPT language=JavaScript src="/js/FoshanRen.js"></SCRIPT>
</head>

<body bgcolor="#FFFFFF" text="#000000" marginwidth="0" marginheight="0">
<html:form action="control/employee/list" method="post">
<html:hidden property="page"/>
  <table width="98%" border="0" cellspacing="1" cellpadding="2" align="center">
    <tr ><td colspan="10" bgcolor="6f8ac4" align="right">
    	<%@ include file="/WEB-INF/page/share/fenye.jsp" %>
   </td></tr>
    <tr>
      <td width="5%" nowrap bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">修改</font></div></td>
      <td width="10%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">用户名</font></div></td>
      <td width="10%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">姓名</font></div></td>
      <td width="5%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">性别</font></div></td>
      <td width="12%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">联系电话</font></div></td>
      <td width="10%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">电子邮件</font></div></td>
      <td width="10%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">身份证号</font></div></td>
      <td width="10%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">照片</font></div></td>
      <td bgcolor="6f8ac4"><div align="center"><font color="#FFFFFF">所属部门</font></div></td>
      <td width="5%" bgcolor="6f8ac4"></td>
    </tr>
<!---------------------------LOOP START------------------------------>
<c:forEach items="${pageView.records}" var="entry">
    <tr>
      <td bgcolor="f5f5f5"> <div align="center"><a href="<html:rewrite action="control/employee/manage"/>?method=editUI&username=${entry.username}">
	  <img src="/images/edit.gif" width="15" height="16" border="0"></a></div></td>
      <td bgcolor="f5f5f5"> <div align="center">${entry.username}</div></td>
      <td bgcolor="f5f5f5"> <div align="center">${entry.realname}</div></td>
      <td bgcolor="f5f5f5"> <div align="center">${entry.gender.name}</div></td>
      <td bgcolor="f5f5f5"> <div align="center">${entry.phone}</div></td>
      <td bgcolor="f5f5f5"> <div align="center">${entry.email}</div></td>
      <td bgcolor="f5f5f5"> <div align="center">${entry.idcard.cardno}</div></td>
      <td bgcolor="f5f5f5"> <div align="center"><c:if test="${!empty entry.picture}"><img src="${entry.picturePath}" width="80" border="0"></c:if></div></td>
      <td bgcolor="f5f5f5"> <div align="center"><c:forEach items="${entry.departments}" var="department">
      ${department.name }</c:forEach></div></td>
      <td bgcolor="f5f5f5"> <div align="center"><a href="<html:rewrite action="control/employee/manage"/>?method=delete&username=${entry.username}">删除</a></div></td>
	</tr>
</c:forEach>
    <!----------------------LOOP END------------------------------->
    <tr> 
      <td bgcolor="f5f5f5" colspan="10" align="center"><table width="100%" border="0" cellspacing="1" cellpadding="3">
          <tr> 
            <td width="5%"></td>
              <td width="85%">
              <input type="button" class="frm_btn" onClick="javascript:window.location.href='<html:rewrite action="control/employee/manage"/>?method=addUI'" value="添加员工"> &nbsp;&nbsp;
            </td>
          </tr>
        </table></td>
    </tr>
  </table>
</html:form>
</body>
</html>