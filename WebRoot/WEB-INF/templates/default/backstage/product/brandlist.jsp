<%@ page contentType="text/html;charset=utf-8" %>
<%@ include file="/WEB-INF/templates/default/share/taglib.jsp" %>
<html>
<head>
<title>产品品牌显示</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
 <%@ include file="/WEB-INF/templates/default/share/backstage_location_check.jsp" %>  
<link rel="stylesheet" href="css/vip.css" type="text/css">


</head>

<body bgcolor="#FFFFFF" text="#000000" marginwidth="0" marginheight="0">
<%-- <html:form action="control/brand/list" method="post">
<html:hidden property="page"/>
<html:hidden property="query"/>
<html:hidden property="name"/>
--%>
  <table width="98%" border="0" cellspacing="1" cellpadding="2" align="center">
    <tr ><td colspan="4" bgcolor="6f8ac4" align="right">
    总记录数:${pm.total}条 |
     <div class="pagesplit"> 
<%
String pageSplits = ConstantString.generatePageSplit(
		((PageModel)request.getAttribute("pm")).getTotal(),
		"control/product/brand/manage.do?");
out.println(pageSplits);
%>
</div>
  
   </td></tr>
    <tr>
      <td width="30%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">代号</font></div></td>
      <td width="5%" nowrap bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">修改</font></div></td>
      <td width="35%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">名称</font></div></td>
	  <td width="30%" nowrap bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">Logo</font></div></td>
    </tr>
<!---------------------------LOOP START------------------------------>
<c:forEach items="${pm.datas}" var="brand">
    <tr>
      <td bgcolor="f5f5f5" height="50" width="30"> ${brand.id}</td>
      <td bgcolor="f5f5f5" height="50" width="30"> <a href="<html:rewrite action="control/product/brand/manage"/>?method=editInput&id=${brand.id}">
	  <img src="images/edit.gif" width="15" border="0"></a></td>
      <td bgcolor="f5f5f5" height="50"> ${brand.name}</td>
	  <td bgcolor="f5f5f5"> <div align="center"><c:if test="${empty brand.logoUrl}">没有logo</c:if>
	  <c:if test="${!empty brand.logoUrl}"><img src="${brand.logoUrl}" width="80"></c:if></div></td>
	</tr>
</c:forEach>
    <!----------------------LOOP END------------------------------->
    <tr> 
      <td bgcolor="f5f5f5" colspan="4" align="center"><table width="100%" border="0" cellspacing="1" cellpadding="3">
          <tr> 
            <td width="5%"></td>
              <td width="85%">
              <input type="button" class="frm_btn" onClick="javascript:window.location.href='<html:rewrite action="control/product/brand/manage"/>?method=addInput'" value="添加品牌"> &nbsp;&nbsp;
			  <input name="query" type="button" class="frm_btn" id="query" onClick="javascript:window.location.href='<html:rewrite action="control/product/brand/manage"/>?method=queryInput'" value=" 查 询 "> &nbsp;&nbsp;
            </td>
          </tr>
        </table></td>
    </tr>
  </table>

</body>
</html>