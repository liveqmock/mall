<%@ page contentType="text/html;charset=utf-8" %>
<%@ include file="/WEB-INF/page/share/taglib.jsp" %>
<html>
<head>
<title>TOP10.HOTSELL.RECOMMND显示</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
 <%@ include file="/location_check.jsp" %>  
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


  <table width="98%" border="0" cellspacing="1" cellpadding="2" align="center">
   <tr ><td colspan="4" bgcolor="6f8ac4" align="left">
   TOP10</td></tr>

   <tr><td colspan="4" bgcolor="ffffff" align="right">
   <table>
   <c:forEach items="${top10}" var="pro">
   <tr><td> ${pro.id }</td><td>   ${pro.name }</td><td><input type="checkbox"></td></tr>
   </c:forEach>
   
   </table>
   
   </td></tr>
    <tr ><td colspan="4" bgcolor="6f8ac4" align="left">
   HotSell</td></tr>
   <tr><td colspan="4" bgcolor="ffffff" align="right">
      <table>
   
   <c:forEach items="${hotsell}" var="pro">
  <tr><td> ${pro.id }</td><td>   ${pro.name }</td><td><input type="checkbox"></td></tr>
   </c:forEach>
   
   </table>

   </td></tr>
<tr ><td colspan="4" bgcolor="6f8ac4" align="left">
   recommend</td></tr>
   <tr><td colspan="4" bgcolor="ffffff" align="right">
   <table>
   <c:forEach items="${recommend}" var="pro">
  <tr><td> ${pro.id }</td><td>   ${pro.name }</td><td><input type="checkbox"></td></tr>
   </c:forEach>
   
   </table>
   </td></tr>
  </table>
<a href="control/product/category/manage.do?method=addedProductsModuleInput&id=${categoryForm.id }">添加</a>
</body>
</html>