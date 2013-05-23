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
		alert("名称不能为空!");
		form.name.focus();
		return false;
	}
	if (trim(form.description.value)>200){
		alert("描述不能为空!");
		form.note.focus();
		return false;
	}	
	return true;
}
</script>
</head>
<body bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<html:form action="control/product/category/manage" method="post" enctype="multipart/form-data"   onsubmit="return checkfm(this)">
<html:hidden property="parentId"/>

<input type="hidden" name="method" value="add">
<br>
  <table width="90%" border="0" cellspacing="2" cellpadding="3" align="center">
    <tr bgcolor="6f8ac4"><td colspan="2"  > <font color="#FFFFFF"></font></td>
    </tr>
    <tr bgcolor="f5f5f5"> 
      <td width="22%" > <div align="right">分类名称</div></td>
      <td width="78%"> <html:text property="name" size="50" maxlength="50"/>
        <font color="#FF0000">*</font></td>
    </tr>
	<tr bgcolor="f5f5f5"> 
      <td width="22%" > <div align="right">描述(100)</div></td>
      <td width="78%"> <html:text property="description" size="80" maxlength="100"/>
        </td>
    </tr>
	<tr bgcolor="f5f5f5"> 
      <td width="22%" > <div align="right">meta_KeyWords(100)</div></td>
      <td width="78%"> <html:text property="meta_KeyWords" size="80" maxlength="100"/>
        </td>
    </tr>
	<tr bgcolor="f5f5f5"> 
      <td width="22%" > <div align="right">meta_description(100)</div></td>
      <td width="78%"> <html:text property="meta_Description" size="80" maxlength="100"/>
        </td>
    </tr>
	<tr bgcolor="f5f5f5"> 
      <td width="22%" > <div align="right">页面显示名称(100)</div></td>
      <td width="78%"> <html:text property="titleInPage4category" size="80" maxlength="100"/>
        </td>
    </tr>        
	<tr bgcolor="f5f5f5"> 
      <td width="22%" > <div align="right">URL</div></td>
      <td width="78%"> <html:text property="url" size="80" maxlength="100"/>
        </td>
    </tr>    
	<tr bgcolor="f5f5f5"> 
      <td width="22%" > <div align="right">图片</div></td>
      <td width="78%"> <input type="file" name="imageFile" size="50">
        </td>
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