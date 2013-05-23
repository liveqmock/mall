<%@ page contentType="text/html;charset=utf-8" %>
<%@ include file="/WEB-INF/page/share/taglib.jsp" %>

<html>
<head>
<title>修改类别</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<jsp:include page="/location_check.jsp"></jsp:include>
<link rel="stylesheet" href="css/vip.css" type="text/css">

<script language="JavaScript">
function checkfm(form){
	if (trim(form.name.value)==""){
		alert("类别名称不能为空！");
		form.name.focus();
		return false;
	}
	if (byteLength(form.description.value)>200){
		alert("描述不能大于100字！");
		form.note.focus();
		return false;
	}	
	return true;
}
</script>
</head>
<body bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<html:form action="control/product/category/manage" method="post" enctype="multipart/form-data" onsubmit="return checkfm(this)">
<html:hidden property="id"/>
<c:if test="${!empty category.parent}"><input type="hidden" name="parentId" value="${category.parent.id}"/></c:if>

<input type="hidden" name="method" value="update">
<br>
  <table width="90%" border="0" cellspacing="2" cellpadding="3" align="center">
    <tr bgcolor="6f8ac4"><td colspan="2"  > <font color="#FFFFFF">修改类别：</font></td>
    </tr>
    <tr bgcolor="f5f5f5"> 
      <td width="22%" > <div align="right">类别名称：</div></td>
      <td width="78%"> 
      <input type="text" name="name" size="50" maxlength="50" value="${category.name}"> 

        <font color="#FF0000">*</font></td>
    </tr>
	<tr bgcolor="f5f5f5"> 
      <td width="22%" > <div align="right">描述(100字以内)：</div></td>
      <td width="78%">
       <input type="text" name="description" size="80" maxlength="100" value="${category.description }"> 
      
        </td>
    </tr>
	<tr bgcolor="f5f5f5"> 
      <td width="22%" > <div align="right">meta_KeyWords：</div></td>
      <td width="78%">
       <input type="text" name="meta_KeyWords" size="80" maxlength="100" value="${category.meta_KeyWords }"> 
      
        </td>
    </tr>
<tr bgcolor="f5f5f5"> 
      <td width="22%" > <div align="right">meta_Description：</div></td>
      <td width="78%">
       <input type="text" name="meta_Description" size="80" maxlength="100" value="${category.meta_Description }"> 
      
        </td>
    </tr>

    	<tr bgcolor="f5f5f5"> 
      <td width="22%" > <div align="right">title for category display In url：</div></td>
      <td width="78%">
       <input type="text" name="titleInPage4category" size="80" maxlength="100" value="${category.titleInPage4category }"> 
    
        </td>
    </tr>  
    	<tr bgcolor="f5f5f5"> 
      <td width="22%" > <div align="right">url：</div></td>
      <td width="78%">
       <input type="text" name="url" size="80" maxlength="100" value="${category.url }"> 
    
        </td>
    </tr>      
	<tr bgcolor="f5f5f5"> 
      <td width="22%" > <div align="right">图片：</div></td>
      <td width="78%">
      <input type="file" name="imageFile" size="50"><br/><!-- ${imageUrl} -->
          <c:if test="${!empty category.imageUrl}"><img src="${category.imageUrl}" width="100">
     </c:if>
      
        </td>
    </tr>     
       	<tr bgcolor="f5f5f5"> 
      <td width="22%" > <div align="right">排序：</div></td>
      <td width="78%">
      <input type="text" name="orderNo" size="50">
        </td>
    </tr>  
    <tr bgcolor="f5f5f5"> 
      <td colspan="2"> <div align="center"> 
          <input type="submit" name="SYS_SET" value=" 确 定 " class="frm_btn">
           <input type="button" value=" 返回 " class="frm_btn" onclick="javascript:history.back(-1)">
        </div></td>
    </tr>
  </table>
</html:form>
<br>
</body>
</html>