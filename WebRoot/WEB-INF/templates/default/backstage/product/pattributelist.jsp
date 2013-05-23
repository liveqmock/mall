<%@ page contentType="text/html;charset=utf-8" %>
<%@ include file="/WEB-INF/templates/default/share/taglib.jsp" %>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<html>
<head>
<title>产品属性列表</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<jsp:include page="/WEB-INF/templates/default/share/backstage_location_check.jsp"></jsp:include>
<link rel="stylesheet" href="css/vip.css" type="text/css">
<style type="text/css">
.optionname{width:100px;float:left;padding-left:20px;}
</style>

<script>

function actionEvent(methodname,methods){
	var form = document.forms[0];
	//if(validateIsSelect(form.all, form.productIds)){
		form.action='/control/center/country/manage.do';
		form.method.value=methodname;
		//form.yesOrNo.value=methods;
		//alert(form.yesOrNo.value);
		form.submit();
	//}else{
	//	alert("请选择要操作的记录");
	//}
}
</script>
</head>

<body bgcolor="#FFFFFF" text="#000000" marginwidth="0" marginheight="0">

<form action="control/product/product/manage.do" method="post">
<input type="hidden" name="method"> 
  <table width="100%" border="0" cellspacing="1" cellpadding="2" align="center">
    <tr><td colspan="4"  bgcolor="6f8ac4" align="right">

   </td></tr>
    <tr>
      <td width="5%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">属性 ID</font></div></td>
      <td width="15%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">属性名称</font></div></td>
      <td bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">属性值列表</font></div></td>
     <td bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">操作</font></div></td> 
    </tr>
    <!---------------------------LOOP START------------------------------>

<c:forEach items="${pm}" var="attribute">

    <tr>
      <td bgcolor="f5f5f5"> <div><INPUT TYPE="checkbox" NAME="ids" value="${attribute.id}">${attribute.id }</div></td>

	  <td bgcolor="f5f5f5"> 	   
	  <a href="control/product/product/manage.do?method=attributeoptionlist&productId=${productForm.productId}&attributeId=${attribute.id}" title="查看${attribute.name }属性">${attribute.name }</a>
	</td> <td bgcolor="f5f5f5"> 
	  <c:if test="${!empty options}">
	  	<c:forEach items="${options}" var="option">
	  	  <c:if test="${option.attribute.name eq attribute.name}">
	  	 <div class="optionname"> ${option.value }</div>  	  <div class="optionname">	<a href="control/product/product/manage.do?method=attributeoptioneditinput&attributeOptionId=${option.id}" title="编辑">编辑</a></div>  
	  <br/>

	  	  </c:if>
	  	</c:forEach>
	  </c:if>
	     </td>
	  <td bgcolor="f5f5f5">    <a href="control/product/product/manage.do?method=attributeoptionaddinput&productId=${productForm.productId}&attributeId=${attribute.id }">添加属性信息</a>

	   </td>
</tr>
</c:forEach>
    <!----------------------LOOP END------------------------------->

  </table>
 </form>
</body>
</html>