<%@ page contentType="text/html;charset=utf-8" %>
<%@ include file="/WEB-INF/templates/default/share/taglib.jsp" %>
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<jsp:include page="/WEB-INF/templates/default/share/backstage_location_check.jsp"></jsp:include>
<link rel="stylesheet" href="css/vip.css" type="text/css">

<script language="JavaScript">
var hotseller = [${hotsell}];
var top10 = [${top10}];
var recommend = [${recommend}];
function initial(){
	
}
function addOrDel(productId,type,categoryId,addOrDel){
	
	C=document.createElement("SCRIPT");
	C.src="control/product/category/manage.do?method=setAdded&productId="+productId+"&addedType="+type+"&id="+categoryId+"&addOrDel="+addOrDel;
	C.charset="gb2312";
	//C.id="script"+id;
	document.body.appendChild(C);
}
</script>
</head>
<body bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload="initial()">
手工更新
<html:form action="control/product/category/manage" method="post">
<input type="hidden" name="method" value="addedProductsModule">
<input type="hidden" name="id" value="${categoryForm.id }"/>
<input type="hidden" name="type" value="top10">
<input type="text" name="productIds" value=${top10 }>
<input type="submit" value="更新TOP10" />
</html:form>
<html:form action="control/product/category/manage" method="post">
<input type="hidden" name="method" value="addedProductsModule">
<input type="hidden" name="id" value="${categoryForm.id }"/>
<input type="hidden" name="type" value="hotsell">
<input type="text" name="productIds" value=${hotsell }>
<input type="submit" value="更新HotSell" />
</html:form>
<html:form action="control/product/category/manage" method="post">
<input type="hidden" name="method" value="addedProductsModule">
<input type="hidden" name="id" value="${categoryForm.id }"/>
<input type="hidden" name="type" value="recommend">
<input type="text" name="productIds" value=${recommend }>
<input type="submit" value="更新Recommend" />
</html:form>

<br>
   <table width="98%" border="0" cellspacing="1" cellpadding="2" align="center">
   <tr><td>产品ID</td><td>产品名称</td><td>设为HotSeller</td><td>设为TOP10</td><td>设为Recommend</td></tr>
   <c:forEach items="${pm.datas}" var="pro">
   <tr><td> ${pro.id }</td><td>   ${pro.name }</td>
   <td><input type="checkbox" id="hotsell_${pro.id }" onclick='addOrDel(${pro.id },"hotsell",${categoryForm.id },this.checked)'>
   </td>
   <td><input type="checkbox" id="top10_${pro.id }" onclick='addOrDel(${pro.id },"top10",${categoryForm.id },this.checked)'></td>
   <td><input type="checkbox" id="recommend_${pro.id }" onclick='addOrDel(${pro.id },"recommend",${categoryForm.id },this.checked)'></td></tr>
   </c:forEach>
   </table>
 

 
<br>
</body>
</html>