<%@ page contentType="text/html;charset=utf-8" %>
<%@ include file="/WEB-INF/templates/default/share/taglib.jsp" %>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<html>
<head>
<title>类别属性列表</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<jsp:include page="/WEB-INF/templates/default/share/backstage_location_check.jsp"></jsp:include>
<link rel="stylesheet" href="css/vip.css" type="text/css">
<script language="JavaScript">
<!--
	//到指定的分页页面
	function topage(page){
		var form = document.forms[0];
		form.page.value=page;
		form.submit();
	}
function checkConfirm(){

	return confirm("确定?");
}
//-->
</script>

</head>

<body bgcolor="#FFFFFF" text="#000000" marginwidth="0" marginheight="0">

<form action="control/product/categoryattribute/manage.do" method="post">

  <table width="98%" border="0" cellspacing="1" cellpadding="2" align="center">
    <tr ><td colspan="5"  bgcolor="6f8ac4" align="right">
	<font color="#FFFFFF">总记录数:${fn:length(pm) }条 </font>
  
   </td></tr>
    <tr>
      <td width="5%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">ID</font></div></td>
   
      <td width="10%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">属性名称</font></div></td>
          <td bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">属性值</font></div></td>
        <td bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">操作</font></div></td>
         <td width="20%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">所属分类</font></div></td>
    </tr>

<!---------------------------LOOP START------------------------------>
<c:forEach items="${pm}" var="attribute">
    <tr>
      <td bgcolor="f5f5f5"> <div align="center">${attribute.id }</div></td>
      <td bgcolor="f5f5f5"> ${attribute.name }</td>

         <td bgcolor="f5f5f5">
         <c:forEach items="${attribute.options}" var="option">
       <!--  ${attribute.id }_${option.id} --> &nbsp;&nbsp;${option.value }
         &nbsp;&nbsp;&nbsp;&nbsp;
         <a href="control/product/categoryattribute/manage.do?method=editcategoryattributeoption&attributeId=${attribute.id}&optionId=${option.id }">编辑</a>
  <span class="right">
  <a href="control/product/categoryattribute/manage.do?method=deletecategoryattributeoption&optionId=${option.id }&attributeId=${attribute.id}" 
 onclick="javascript:return checkConfirm()">删除</a></span>
 		<br />	

         </c:forEach>
         	<br />
         
         </td> <td bgcolor="f5f5f5">
          <a href="control/product/categoryattribute/manage.do?method=addoptioninput&attributeId=${attribute.id}&categoryId=${attribute.category.id}">添加属性值</a>
        
       <!--   <a href="/control/product/categoryattribute/manage.do?method=listoptions&attributeId=${attribute.id }">所有属性值</a>
         <a href="/control/product/categoryattribute/manage.do?method=edit&id=${attribute.id }">编辑</a> 
         -->
          <a onclick="javascript:return checkConfirm()" href="control/product/categoryattribute/manage.do?method=deletecategoryattribute&attributeId=${attribute.id }&categoryId=${attribute.category.id}">删除该属性及所有属性值</a> 
         </td>
                 <td bgcolor="f5f5f5"> 
        
        <a href="control/product/category/manage.do?parentId=<c:if test="${! empty attribute.category.parent}">
        ${attribute.category.parent.id}
        </c:if>">${attribute.category.name}</a></td>
	</tr>
</c:forEach>
    <!----------------------LOOP END------------------------------->
    <tr> 
      <td bgcolor="f5f5f5" colspan="5" align="center"><table width="100%" border="0" cellspacing="1" cellpadding="5">
          <tr> 
            <td width="5%"></td>
              <td width="85%">
              	
              <c:if test="${categoryAttributeForm.categoryId>0 }">
     	    <input name="AddDic" type="button" class="frm_btn" id="AddDic" onClick="javascript:window.location.href='control/product/categoryattribute/manage.do?method=addcategoryattributeinput&categoryId=${categoryAttributeForm.categoryId}'" value="添加属性"> &nbsp;&nbsp;
     	      <input name="query" type="button" class="frm_btn" id="query" onClick="javascript:window.location.href='<html:rewrite action="control/product/category/manage"/>?parentId=<c:if test="${! empty attribute.category.parent}">
        ${attribute.category.parent.id}
        </c:if>'" value="返回产品分类列表"> &nbsp;&nbsp;
        
     	    <a href="control/product/categoryattribute/manage.do?method=initAllProductCategoryAttributeByCategoryId&categoryId=${categoryAttributeForm.categoryId}">初始化该分类下所有产品的类别属性</a>
           </c:if>
            </td>
          </tr>
        </table></td>
    </tr>
  </table>
</form>

</body>
</html>