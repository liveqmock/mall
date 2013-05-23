<%@ page contentType="text/html;charset=utf-8" %>
<%@ include file="/WEB-INF/templates/default/share/taglib.jsp" %>
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<jsp:include page="/WEB-INF/templates/default/share/backstage_location_check.jsp"></jsp:include>
<link rel="stylesheet" href="css/vip.css" type="text/css">

<script language="JavaScript">
function checkfm(form){
	if (trim(form.name.value)==""){
		alert("管理员名称不能为空!");
		form.name.focus();
		return false;
	}
	return true;
}
</script>
</head>
<body bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<html:form action="control/center/administrator/manage" method="post" enctype="multipart/form-data"   onsubmit="return checkfm(this)">
<input type="hidden" name="method" value="addRole4Administrator"/>
<input type="hidden" name="id" value="${administrator.id}"/>
<br>
  <table width="90%" border="0" cellspacing="2" cellpadding="3" align="center">
    <tr bgcolor="6f8ac4"><td> <font color="#FFFFFF"></font></td>
    </tr>
    <tr bgcolor="f5f5f5"> 
      <td> <div align="left">管理员名称 ：<font color="#FF0000">*</font>${administrator.name }</div></td>
      
       
    </tr>
	<tr bgcolor="f5f5f5"> 
      <td> <div align="left">角色列表</div></td>
    </tr>
    <c:forEach items="${roles}" var="role">
    <tr bgcolor="f5f5f5"> 
      <td> <div align="left">${role.roleName }<input type="checkbox" value="${role.id}" name="rolesId"/></div></td>
     </tr>
    </c:forEach>
    <tr bgcolor="f5f5f5"> 
      <td> <div align="center"> 
          <input type="submit" name="SYS_SET" value="确  定" class="frm_btn">
         <input type="button" value="后  退 " class="frm_btn" onclick="javascript:history.back(-1)">
        </div></td>
    </tr>
  </table>
</html:form>
 
<br>
</body>
</html>