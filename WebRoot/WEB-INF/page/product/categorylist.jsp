<%@ page contentType="text/html;charset=utf-8" %>
<%@ include file="/WEB-INF/page/share/taglib.jsp" %>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<html>
<head>
<title>产品类别管理</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<jsp:include page="/location_check.jsp"></jsp:include>
<link rel="stylesheet" href="css/vip.css" type="text/css">
<link href="css/product/list.css" rel="stylesheet" type="text/css" />

</head>

<body bgcolor="#FFFFFF" text="#000000" marginwidth="0" marginheight="0">

<html:form action="control/product/category/manage" method="post">

  <table width="100%" border="0" cellspacing="1" cellpadding="2" align="center">
    <tr ><td colspan="7"  bgcolor="6f8ac4" align="right">
 总记录数:${pm.total}条 | <div class="pagesplit">  <%
  
String pageSplits = ConstantString.generatePageSplit(
		((PageModel)request.getAttribute("pm")).getTotal(),
		"control/product/category/manage.do?"+(request.getAttribute("parentId")==null?"":"parentId="+request.getAttribute("parentId")));
out.println(pageSplits);
%>  </div>
   </td></tr>
    <tr>
      <td width="2%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">ID</font></div></td>
      <td width="25%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">名称</font></div></td>
      <td width="2%" nowrap="nowrap" bgcolor="6f8ac4">排序</td>
	  <td width="6%" nowrap="nowrap" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">添加子类别 </font></div></td>
	  <td width="15%" bgcolor="6f8ac4"><div align="center"><font color="#FFFFFF">父类别 </font></div></td>

	  <td width="2%" nowrap="nowrap" bgcolor="6f8ac4">状态</td>
	  <td nowrap="nowrap" bgcolor="6f8ac4"><div align="center"><font color="#FFFFFF">操作</font></div></td>

    </tr>

<!---------------------------LOOP START------------------------------>
<c:forEach items="${pm.datas}" var="category">
    <tr>
      <td bgcolor="f5f5f5"> <div align="center">${category.id }</div></td>
      <td bgcolor="f5f5f5"> <div align="center">
	  	<a href='<html:rewrite action="control/product/category/manage"/>?parentId=${category.id }'>${category.name }</a> 
    	  <c:if test="${fn:length(category.children)>0}"></br>&nbsp;<font color=red>(有${fn:length(category.children)}个子类)</font></c:if></div></td>
	  <td bgcolor="f5f5f5">${category.orderNo } </td>
	  <td bgcolor="f5f5f5"> 
	  	<div align="center"><a href="<html:rewrite action="control/product/category/manage"/>?method=addInput&parentId=${category.id}">
		  <img src="images/add.png" alt="Add Child Category" title="Add Child Category" border="0"/>	  </a></div></td>
	  <td bgcolor="f5f5f5" align="center"><c:if test="${!empty category.parent}">${category.parent.name}</c:if></td>
	  <td bgcolor="f5f5f5" align="center"><c:if test="${category.visible==true}">正常</c:if><c:if test="${category.visible==false}">已失效</c:if></td>
	  <td bgcolor="f5f5f5"><div align="center"><a href="<html:rewrite action="control/product/category/manage"/>?method=edit&id=${category.id }"> <img src="images/edit.gif" width="15" height="16" border="0" alt="Edit This Category" title="Edit This Category"></a>
<a href="<html:rewrite action="control/product/category/manage"/>?method=editMetaTags&id=${category.id }"> <img src="images/icon_edit.gif" width="15" height="16" border="0" alt="Edit Meta Tags" title="Edit Meta Tags"></a>	  
	<a href="<html:rewrite action="control/product/category/manage"/>?method=delete&id=${category.id }"> <img src="images/icon_delete.gif" width="15" height="16" border="0" alt="Delete This Category" title="Delete This Category"></a>	  
	<a href="<html:rewrite action="control/product/category/manage"/>?method=modelManage&id=${category.id }"> <img src="images/anchor.png" width="15" height="16" border="0" alt="分类页模板管理" title="分类页模板管理"></a>	 	
	<!-- <a href="<html:rewrite action="control/product/category/manage"/>?method=editFolderName&id=${category.id }"> <img src="images/folder_edit.gif" width="15" height="16" border="0" alt="分类页文件夹名称编辑" title="分类页文件夹名称编辑"></a> -->	
		<a href="<html:rewrite action="control/product/category/manage"/>?method=createHTML4P&id=${category.id }"> <img src="images/cog_add.png" width="15" height="16" border="0" alt="创建该目录下的产品html文件" title="创建该目录下的产品html文件"></a>
	<a href="<html:rewrite action="control/product/category/manage"/>?&method=listaddedProductsModule&id=${category.id }"> TOP10管理</a>	 	
	<a href="control/center/article/manage.do?type=<%=ConstantString.CATEGORYVALUE %>&categoryId=${category.id }"> 类别新闻管理</a>
		<a href="control/product/categoryattribute/manage.do?categoryId=${category.id }"> 类别属性管理</a>
	  </div></td>
	  
	</tr>
</c:forEach>
    <!----------------------LOOP END------------------------------->
    <tr> 
      <td bgcolor="f5f5f5" colspan="7" align="center"><table width="100%" border="0" cellspacing="1" cellpadding="3">
          <tr> 
            <td width="5%"></td>
              <td width="85%">
         <%--   <input name="AddDic" type="button" class="frm_btn" id="AddDic" onClick="javascript:window.location.href='control/product/type/manage?method=addInput&parentId=${pid}'" value="添加类别"> &nbsp;&nbsp;--%>
              <input name="AddDic" type="button" class="frm_btn" id="AddDic" onClick="javascript:window.location.href='<html:rewrite action="control/product/category/manage"/>?method=addInput&parentId=${pid}'" value="添加类别"> &nbsp;&nbsp;
			  <input name="query" type="button" class="frm_btn" id="query" onClick="javascript:window.location.href='<html:rewrite action="control/product/category/manage"/>?method=queryInput'" value=" 查 询 "> &nbsp;&nbsp;
			  <input name="query" type="button" class="frm_btn" id="query" onClick="javascript:window.location.href='<html:rewrite action="control/product/category/manage"/>?parentId=${ppid }'" value=" 返回">&nbsp;&nbsp;            </td>
          </tr>
        </table></td>
    </tr>
  </table>
</html:form>

</body>
</html>