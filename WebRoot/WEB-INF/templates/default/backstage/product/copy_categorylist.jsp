<%@ page contentType="text/html;charset=utf-8" %>
<%@ include file="/WEB-INF/templates/default/share/taglib.jsp" %>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<html>
<head>
<title>产品类别管理</title>
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
//-->
</script>

</head>

<body bgcolor="#FFFFFF" text="#000000" marginwidth="0" marginheight="0">

<html:form action="control/product/category/manage" method="post">

  <table width="98%" border="0" cellspacing="1" cellpadding="2" align="center">
    <tr ><td colspan="10"  bgcolor="6f8ac4" align="right">



<pg:pager items="${pm.total }" url="manage.do" export="currentPageNumber=pageNumber">
<pg:param name="parentId"/>
	<font color="#FFFFFF">总记录数:${pm.total}条 |</font>
	<pg:first><a href="${pageUrl}">首页</a></pg:first>
	<pg:prev><a href="${pageUrl }">前页</a></pg:prev>
	<pg:pages>
		<c:choose>
		<c:when test="${ currentPageNumber eq pageNumber}">
		
		<font color="#FFFFFF">当前页:第</font><font color="red">${pageNumber}</font><font color="#FFFFFF">页  |</font>
		</c:when>
		<c:otherwise>
		<a href="${pageUrl }">${pageNumber }</a>
		</c:otherwise>
		</c:choose>
	</pg:pages>
	<pg:next><a href="${pageUrl }">下页</a></pg:next>
	<pg:last><a href="${pageUrl }">尾页</a></pg:last>
	
</pg:pager>   
   </td></tr>
    <tr>
      <td width="2%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">代号</font></div></td>
      <td width="2%" nowrap="nowrap" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">修改</font></div></td>
      <td width="10%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">产品类别名称</font></div></td>
	  <td width="6%" nowrap="nowrap" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">创建下级类别</font></div></td>
	  <td width="10%" bgcolor="6f8ac4"><div align="center"><font color="#FFFFFF">所属父类</font></div></td>
	  <td width="15%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">描述</font></div></td>
	  <td width="10%" nowrap="nowrap" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">文件夹名称</font></div></td>
	  <td width="15%" nowrap="nowrap" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">meta_KeyWords</font></div></td>
	  <td width="15%" nowrap="nowrap" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">meta_Description</font></div></td>
	  <td width="15%" nowrap="nowrap" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">titleInPage</font></div></td>
    </tr>

<!---------------------------LOOP START------------------------------>
<c:forEach items="${pm.datas}" var="category">
    <tr>
      <td bgcolor="f5f5f5"> <div align="center">${category.id }</div></td>
      <td bgcolor="f5f5f5"> <div align="center"><a href="<html:rewrite action="control/product/category/manage"/>?method=edit&id=${category.id }">
	  <img src="images/edit.gif" width="15" height="16" border="0"></a></div></td>
      <td bgcolor="f5f5f5"> <div align="center"><a href='<html:rewrite action="control/product/category/manage"/>?parentId=${category.id }'>${category.name }</a> 
      <c:if test="${fn:length(category.children)>0}"></br>&nbsp;<font color=red>(有${fn:length(category.children)}个子类)</font></c:if></div></td>
	  <td bgcolor="f5f5f5"> <div align="center"><a href="<html:rewrite action="control/product/category/manage"/>?method=addInput&parentId=${category.id}">创建子类别</a></div></td>
	  <td bgcolor="f5f5f5" align="center"><c:if test="${!empty category.parent}">${category.parent.name}</c:if></td>
	  <td bgcolor="f5f5f5">${category.description }</td>
	  <td bgcolor="f5f5f5">${category.folderName}</td>
	  <td bgcolor="f5f5f5">${category.meta_KeyWords}</td>
	  <td bgcolor="f5f5f5">${category.meta_Description}</td>
	  <td bgcolor="f5f5f5">${category.titleInPage4category}</td>
	</tr>
</c:forEach>
    <!----------------------LOOP END------------------------------->
    <tr> 
      <td bgcolor="f5f5f5" colspan="10" align="center"><table width="100%" border="0" cellspacing="1" cellpadding="3">
          <tr> 
            <td width="5%"></td>
              <td width="85%">
         <%--   <input name="AddDic" type="button" class="frm_btn" id="AddDic" onClick="javascript:window.location.href='control/product/type/manage?method=addInput&parentId=${pid}'" value="添加类别"> &nbsp;&nbsp;--%>
              <input name="AddDic" type="button" class="frm_btn" id="AddDic" onClick="javascript:window.location.href='<html:rewrite action="control/product/category/manage"/>?method=addInput&parentId=${pid}'" value="添加类别"> &nbsp;&nbsp;
			  <input name="query" type="button" class="frm_btn" id="query" onClick="javascript:window.location.href='<html:rewrite action="control/product/category/manage"/>?method=queryInput'" value=" 查 询 "> &nbsp;&nbsp;
			  <input name="query" type="button" class="frm_btn" id="query" onClick="javascript:window.location.href='<html:rewrite action="control/product/category/manage"/>?parentId=${ppid }'" value=" 返回 "> &nbsp;&nbsp;
            </td>
          </tr>
        </table></td>
    </tr>
  </table>
</html:form>

</body>
</html>