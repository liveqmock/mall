<%@ page contentType="text/html;charset=utf-8" %>
<%@ include file="/WEB-INF/page/share/taglib.jsp" %>
<html>
<head>
<title>类别Meta信息编辑</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<jsp:include page="/location_check.jsp"></jsp:include>
<link rel="stylesheet" href="css/vip.css" type="text/css">


</head>
<body bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<html:form action="control/product/category/manage" method="post">
<input type="hidden" name="method" value="updateMetaTags"/>
<input type="hidden" name="id" value="${category.id }"/>
<c:if test="${!empty category.parent}"><input type="hidden" name="parentId" value="${category.parent.id}"/></c:if>

<br>
  <table width="90%" border="0" cellspacing="2" cellpadding="3" align="center">
    <tr bgcolor="6f8ac4"><td colspan="2"  > <font color="#FFFFFF"></font></td>
    </tr>
    <tr bgcolor="f5f5f5"> 
      <td width="22%" > <div align="right">类别名称</div></td>
      <td width="78%"> <input type="text" name="name" size="50" maxlength="50" value="${ category.name}"/>
        <font color="#FF0000">*</font></td>
    </tr>
     <tr bgcolor="f5f5f5"> 
      <td width="22%" > <div align="right">类别标题</div></td>
      <td width="78%"> <input type="text" name="titleInPage4category" size="50" maxlength="50" value="${ category.titleInPage4category}"/>
        <font color="#FF0000">*</font></td>
    </tr>
         <tr bgcolor="f5f5f5"> 
      <td width="22%" > <div align="right">meta_description</div></td>
      <td width="78%"> <input type="text" name="meta_Description" size="50" maxlength="50" value="${ category.meta_Description}"/>
        <font color="#FF0000">*</font></td>
    </tr>
         <tr bgcolor="f5f5f5"> 
      <td width="22%" > <div align="right">meta_keywords</div></td>
      <td width="78%"> <input type="text" name="meta_KeyWords" size="50" maxlength="50" value="${ category.meta_KeyWords}"/>
        <font color="#FF0000">*</font></td>
    </tr>
         <tr bgcolor="f5f5f5"> 
      <td width="22%" > <div align="right">类别url</div></td>
      <td width="78%"> <input type="text" name="url" size="50" maxlength="50" value="${ category.url}"/>
        <font color="#FF0000">*</font></td>
    </tr>
    <tr bgcolor="f5f5f5"> 
      <td colspan="2"> <div align="center"> 
          <input type="submit" name="SYS_SET" value=" 确  定  " class="frm_btn">
         <input type="button" value=" 后  退  " class="frm_btn" onclick="javascript:history.back(-1)">
        </div></td>
    </tr>
  </table>
</html:form>
 
<br>
</body>
</html>