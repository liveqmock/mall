<%@ page contentType="text/html;charset=utf-8" %>
<%@ include file="/WEB-INF/templates/default/share/taglib.jsp" %>
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<jsp:include page="/WEB-INF/templates/default/share/backstage_location_check.jsp"></jsp:include>
<link rel="stylesheet" href="css/vip.css" type="text/css">
<script type="text/javascript" src="js/jscripts/fckeditor/fckeditor.js"></script>
<script type="text/javascript">
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
<input type="hidden" name="method" value="update"/>
<input type="hidden" name="id" value="${article.id }"/>
<input type="hidden" name="typeId" value="${article.type.id }"/>
<input type="hidden" name="contentId" value="${article.content.id }"/>
<br>
  <table width="90%" border="0" cellspacing="2" cellpadding="3" align="center">
    <tr bgcolor="6f8ac4"><td colspan="2"  > <font color="#FFFFFF"></font></td>
    </tr>
    <tr bgcolor="f5f5f5"> 
      <td width="22%" > <div align="right">文章标题</div></td>
      <td width="78%"> <input type="text" name="name" size="50" maxlength="50" value="${article.name}"/>
        <font color="#FF0000">*</font></td>
    </tr>
    <tr bgcolor="f5f5f5"> 
      <td width="22%" > <div align="right">页面title</div></td>
      <td width="78%"> <input type="text" name="title" size="50" maxlength="50" value="${article.title}"/>
        <font color="#FF0000">*</font></td>
    </tr>
    <tr bgcolor="f5f5f5"> 
      <td width="22%" > <div align="right">URL,置空则使用标题作为url</div></td>
      <td width="78%"> <input type="text" name="url" size="50" maxlength="50" value="${article.url}"/>
        <font color="#FF0000">*</font></td>
    </tr>       
    <tr bgcolor="f5f5f5"> 
      <td width="22%" > <div align="right">文章关键词，以,分隔</div></td>
      <td width="78%"><input type="text" name="keywords" size="80" maxlength="100"  value="${article.keywords }"/>
        </td>
    </tr>
    <tr bgcolor="f5f5f5"> 
      <td width="22%" > <div align="right">文章描述</div></td>
      <td width="78%"><input type="text" name="description" size="80" maxlength="100"  value="${article.description}"/>
        </td>
    </tr>
 	<tr bgcolor="f5f5f5"> 
      <td width="22%" > <div align="right">模板</div></td>
      <td width="78%"><input type="text" name="templateUrl" size="80" maxlength="100"  value="${article.templateUrl}"/>
        </td>
    </tr>	
<tr bgcolor="f5f5f5"> 
      <td width="22%" > <div align="right">允许评论</div></td>
      
      <td width="78%"><input type="radio" name="commentPermission" value="1"<c:if test="${article.commentPermission}"> checked</c:if>/>允许&nbsp;&nbsp;&nbsp;&nbsp;
      <input type="radio" name="commentPermission" value="0"<c:if test="${article.commentPermission==false}"> checked</c:if>/>禁止
        </td>
    </tr>    
	<tr bgcolor="f5f5f5"> 
      <td width="22%" > <div align="right">文章内容</div></td>
      <td width="78%">
      <textarea name="content" cols="80" rows="23">${article.content.content}</textarea>
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