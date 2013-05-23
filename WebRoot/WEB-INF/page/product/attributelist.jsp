<%@ page contentType="text/html;charset=utf-8" %>
<%@ include file="/WEB-INF/page/share/taglib.jsp" %>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<html>
<head>
<title>产品属性列表</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<jsp:include page="/location_check.jsp"></jsp:include>
<link rel="stylesheet" href="css/vip.css" type="text/css">


</head>

<body bgcolor="#FFFFFF" text="#000000" marginwidth="0" marginheight="0">

<html:form action="control/product/attribute/manage" method="post">

  <table width="98%" border="0" cellspacing="1" cellpadding="2" align="center">
    <tr ><td colspan="3"  bgcolor="6f8ac4" align="right">
	<font color="#FFFFFF">总记录数:${fn:length(pm) }条 </font>
  
   </td></tr>
    <tr>
      <td width="5%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">ID</font></div></td>
   
      <td width="10%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">属性名称</font></div></td>
        <td bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">操作</font></div></td>
    </tr>

<!---------------------------LOOP START------------------------------>
<c:forEach items="${pm}" var="attribute">
    <tr>
      <td bgcolor="f5f5f5"> <div align="center">${attribute.id }</div></td>
      <td bgcolor="f5f5f5"> ${attribute.name }</td>
         <td bgcolor="f5f5f5"><a href="control/product/attribute/manage.do?method=edit&id=${attribute.id }">编辑</a> 
         <a href="control/product/attribute/manage.do?method=delete&id=${attribute.id }">删除</a>
         </td>
	</tr>
</c:forEach>
    <!----------------------LOOP END------------------------------->
    <tr> 
      <td bgcolor="f5f5f5" colspan="3" align="center"><table width="100%" border="0" cellspacing="1" cellpadding="3">
          <tr> 
            <td width="5%"></td>
              <td width="85%">
     	    <input name="AddDic" type="button" class="frm_btn" id="AddDic" onClick="javascript:window.location.href='control/product/attribute/manage.do?method=addInput'" value="添加属性"> &nbsp;&nbsp;
 			  <input name="query" type="button" class="frm_btn" id="query" onClick="javascript:window.location.href='<html:rewrite action="control/product/attribute/manage"/>?method=queryInput'" value="查 询"> &nbsp;&nbsp;
			  <input name="query" type="button" class="frm_btn" id="query" onClick="javascript:window.location.href='<html:rewrite action="control/product/attribute/manage"/>?parentId=${ppid }'" value="返回"> &nbsp;&nbsp;
            </td>
          </tr>
        </table></td>
    </tr>
  </table>
</html:form>

</body>
</html>