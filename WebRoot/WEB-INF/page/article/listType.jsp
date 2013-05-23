<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/page/share/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<META http-equiv=Content-Language content=zh-CN>
<TITLE>文章类别管理</title> 
<%@ include file="/location_check.jsp" %>
<link href="css/style.css" rel="stylesheet" type="text/css">

<link href="css/product/list.css" rel="stylesheet" type="text/css" />
<link href="css/global/topsell.css" rel="stylesheet" type="text/css">
<LINK href="css/new_cart.css" rel="stylesheet" type="text/css">

<script>
function actionEvent(methodname,methods){
	var form = document.forms[0];
	//if(validateIsSelect(form.all, form.productIds)){
		form.action='control/center/article/manage.do?method=delete&type=${type }';
		//form.method.value=methodname;
		//form.yesOrNo.value=methods;
		//alert(form.yesOrNo.value);
		form.submit();
	//}else{
	//	alert("请选择要操作的记录");
	//}
}

function getDeleteIds(){
var ids="";
	for(var i=0;i<document.forms[0].articleIds.length;i++){
		if(document.forms[0].articleIds[i].checked)
			ids+=document.forms[0].articleIds[i].value;
		}
	//alert(ids);
	document.forms[0].ids[i].value=ids;
	//return ids;
}
</script>
  </head>
  
  <body marginwidth="0" marginheight="0">
<form action="control/center/article/manage.do" method="post">

<table width="100%" cellspacing="1" cellpadding="2" border="0" align="center">
    <tbody><tr><td bgcolor="#6f8ac4" align="right" colspan="4">
	 总记录数:${fn:length(types)}条  <div class="pagesplit">  </div>

   </td></tr>
    <tr>
      <td width="5%" bgcolor="#6f8ac4"> <div align="center"><font color="#FFFFFF">ID</font></div></td>
      <td width="20%" bgcolor="#6f8ac4"> <div align="center"><font color="#FFFFFF">名称</font></div></td>
      	  <td nowrap bgcolor="#6f8ac4"> <div align="center"><font color="#FFFFFF">模板</font></div></td>
	  <td nowrap bgcolor="#6f8ac4"> <div align="center"><font color="#FFFFFF">操作</font></div></td>
    </tr>
<!---------------------------LOOP START------------------------------>
<c:forEach items="${types}" var="type">
    <tr>
      <td bgcolor="#f5f5f5"> <div align="center"><INPUT TYPE="checkbox" NAME="ids" value="${type.id}">${type.id }</div></td>
	  <td bgcolor="#f5f5f5"> <div align="center">${type.name }</div></td>  
	<td nowrap bgcolor="#f5f5f5"> <div align="center">${type.templateUrl }<a href="control/center/article/manage.do?method=editTemplate&templateUrl=${type.templateUrl}&id=${type.id }">编辑</a></div></td>
	  <td bgcolor="#f5f5f5"> <div align="center">
	  <a href="control/center/article/manage.do?method=editArticleType&id=${type.id }">编辑</a>
	  <a href="control/center/article/manage.do?method=deleteArticleType&id=${type.id }">删除</a>
	  </div></td>
</tr>
</c:forEach>
  </table>
  
  <div>
  <a href="control/center/article/manage.do?method=addArticleTypeInput">添加文章类型</a>
<c:choose>
 <c:when test="${articleForm.id == 4}">
<!-- <a href="/control/product/category/manage.do?categoryId=${articleForm.categoryId} -->
 <a href="control/product/category/manage.do">回到产品类别</a>
  
 </c:when>
 <c:otherwise>
 <input type="button" value="删除" onclick="javascript:actionEvent('delete','')"/>&nbsp;&nbsp;<a href="control/center/article/manage.do?id=${articleForm.id }&pId=${returnto}">返回上级</a>
  </c:otherwise>
</c:choose>
 </div>
 </form>
  </body>
</html>
