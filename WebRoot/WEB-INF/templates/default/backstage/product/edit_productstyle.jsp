<%@ page contentType="text/html;charset=utf-8" %>
<%@ include file="/WEB-INF/templates/default/share/taglib.jsp" %>
 <%@ include file="/WEB-INF/templates/default/share/backstage_location_check.jsp" %>  
<html>
<head>
<title>修改产品样式</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<link rel="stylesheet" href="css/vip.css" type="text/css">

<script language="JavaScript">
function checkfm(form){
	if (trim(form.name.value)==""){
		alert("样式名称不能为空！");
		form.name.focus();
		return false;
	}
	var imageFile = form.imageFile.value;
	if(trim(imageFile)!=""){
		var ext = imageFile.substring(imageFile.length-3).toLowerCase();
		if (ext!="jpg" && ext!="gif" && ext!="bmp" && ext!="png"){
			alert("只允许上传gif、jpg、bmp、png！");
			return false; 
		}
	}
	return true;
}
</script>
</head>
<body bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<html:form action="control/product/style/manage" method="post" enctype="multipart/form-data" onsubmit="return checkfm(this)">
<input type="hidden" name="method" value="update">
<html:hidden property="id"/>
<input type="hidden" name="productId" value="${productId }"/>
  <table width="90%" border="0" cellspacing="2" cellpadding="3" align="center">
    <tr bgcolor="6f8ac4"><td colspan="2"  > <font color="#FFFFFF">修改产品图片：</font></td>
    </tr>
    <tr bgcolor="f5f5f5"> 
      <td width="22%" > <div align="right">样式名称：</div></td>
      <td width="78%">
      <input type="text" name="name" size="50" maxlength="40" value="${name }"/>
        <font color="#FF0000">*</font></td>
    </tr>
	<tr bgcolor="f5f5f5"> 
      <td width="22%" > <div align="right">产品图片：</div></td>
      <td width="78%"> <input type="file" name="imageFile" size="50"><br>
      <img src="${imageUrl }" width="100">
      </td>
    </tr>
    <tr bgcolor="f5f5f5"> 
      <td colspan="2"> <div align="center"> 
          <input type="submit" name="SYS_SET" value=" 确 定 " class="frm_btn">
          <input type="button" value=" 返回 " class="frm_btn" onclick="javascript:history.back()">
        </div></td>
    </tr>
  </table>
</html:form>
<br>
</body>
</html>