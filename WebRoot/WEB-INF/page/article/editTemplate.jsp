<%@ page contentType="text/html;charset=utf-8" %>
<%@ include file="/WEB-INF/page/share/taglib.jsp" %>
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<jsp:include page="/location_check.jsp"></jsp:include>
<link rel="stylesheet" href="css/vip.css" type="text/css">
<script type="text/javascript" src="js/jscripts/fckeditor/fckeditor.js"></script>
<script type="text/javascript">
/*
window.onload = function()
{
var oFCKeditor = new FCKeditor( 'content','80%','400') ;
oFCKeditor.BasePath = "js/jscripts/fckeditor/" ;
 oFCKeditor.Config["CustomConfigurationsPath"] = "js/jscripts/fckeditor/myconfig.js"  ;


oFCKeditor.ReplaceTextarea() ;
}
function SureSubmit(objForm){
 objForm.submit();
} 
*/
</script>

<script language="JavaScript">
function checkfm(form){
	if (trim(form.name.value)==""){
		alert("模块名称不能为空!");
		form.name.focus();
		return false;
	}
	
	return true;
}
</script>
</head>
<body bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<html:form action="control/center/article/manage" method="post" enctype="multipart/form-data"   onsubmit="return checkfm(this)">
<input type="hidden" name="method" value="updateTemplate"/>
<input type="hidden" name="pId" value="${pId }"/>
<input type="hidden" name="id" value="${id }"/>
<input type="hidden" name="id" value="${id}"/>
<br>
  <table width="90%" border="0" cellspacing="2" cellpadding="3" align="center">
    <tr bgcolor="6f8ac4"><td colspan="2"  > <font color="#FFFFFF"></font></td>
    </tr>
   <tr bgcolor="6f8ac4"><td>模板</td><td> <font color="#FFFFFF"><input type="text" name="templateUrl" value="${templateUrl}"/></font></td>
    </tr>
   
	<tr bgcolor="f5f5f5"> 
      <td width="22%" > <div align="right">模板内容</div></td>
      <td width="78%">
      <textarea name="content" cols="150" rows="50">${content}</textarea>
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