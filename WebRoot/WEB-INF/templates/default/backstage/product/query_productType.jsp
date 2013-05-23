<%@ page contentType="text/html;charset=utf-8" %>
<%@ include file="/WEB-INF/templates/default/share/taglib.jsp" %>
<html>
<head>
<title>询</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<link rel="stylesheet" href="../../../css/vip.css" type="text/css">
<SCRIPT language=JavaScript src="../../../js/FoshanRen.js"></SCRIPT>
<script language="JavaScript">
function checkfm(form){
	if (trim(form.name.value)==""){
		alert("撇为眨");
		form.name.focus();
		return false;
	}	
	return true;
}
</script>
</head>
<body bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<html:form action="control/product/category/manage" method="post"  onsubmit="return checkfm(this)">

  <table width="90%" border="0" cellspacing="2" cellpadding="3" align="center">
    <tr bgcolor="6f8ac4"><td colspan="2"> <font color="#FFFFFF">询</font></td>
    </tr>
    <tr bgcolor="f5f5f5"> 
      <td width="22%" > <div align="right">疲</div></td>
      <td width="78%"> <html:text property="name" size="50" maxlength="50"/>
        <font color="#FF0000">*</font></td>
    </tr>
    <tr bgcolor="f5f5f5"> 
      <td colspan="2"> <div align="center"> 
          <input type="submit" name="SYS_SET" value=" 确  " class="frm_btn">
        </div></td>
    </tr>
  </table>
</html:form>
<br>
</body>
</html>