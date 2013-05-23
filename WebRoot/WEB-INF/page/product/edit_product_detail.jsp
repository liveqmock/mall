<%@ page contentType="text/html;charset=utf-8" %>
<%@ include file="/WEB-INF/page/share/taglib.jsp" %>
<html>
<head>
<title>修改产品详情</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
 <%@ include file="/location_check.jsp" %>  
<link rel="stylesheet" href="css/vip.css" type="text/css">

<script language="JavaScript">
function checkfm(form){
	if (trim(form.name.value)==""){
		alert("样式名称不能为空！");
		form.name.focus();
		return false;
	}
	var imageFile = form.imageFile.value;
	if(trim(imageFile)!=""){
		var ext = imageFile.substring(imageFile.length-3).toLowerCase();
		if (ext!="jpg" && ext!="gif" && ext!="bmp" && ext!="png"){
			alert("只允许上传gif、jpg、bmp、png！");
			return false; 
		}
	}
	return true;
}
</script>
</head>
<body bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<html:form action="control/product/product/manage" method="post" enctype="multipart/form-data">
<input type="hidden" name="method" value="edit">

  <table width="90%" border="0" cellspacing="2" cellpadding="3" align="center">
        <c:forEach items="${product.groups}" var="group">
	<tr bgcolor="f5f5f5">
      <td width="10%" valign="top"> <div align="right">${group.name}<br/><a class="whitelink" href="control/product/product/manage.do?method=editProductDetailGroup&id=${group.id}&productId=${product.id }">编辑</a><a class="whitelink" href="control/product/product/manage.do?method=deleteProductDetailGroup&id=${group.id}&productId=${product.id }">删除</a> </div></td>
      <td width="90%">
      <c:if test="${empty group.post}">
      <table cellspacing="1" cellpadding="0" border="0" width="100%" class="Ptable"><tbody>
      <c:forEach items="${group.detailAttributes}" var="attribute">

<tr><th colspan="2" class="tdTitle">${attribute.name }<a class="whitelink" href="control/product/product/manage.do?method=editProductDetailAttribute&id=${attribute.id }&productId=${product.id }">编辑</a><a class="whitelink" href="control/product/product/manage.do?method=deleteProductDetailAttribute&id=${attribute.id }&productId=${product.id }">删除</a></th></tr>
 <c:forEach items="${attribute.options}" var="option">
<tr><td class="tdTitle">${option.name }</td><td>${option.value}<a class="whitelink" href="control/product/product/manage.do?method=editProductDetailOption&id=${option.id}&productId=${product.id }">编辑</a><a class="whitelink" href="control/product/product/manage.do?method=deleteProductDetailOption&id=${option.id }&productId=${product.id }">删除</a></td></tr>
</c:forEach>
<tr><td colspan="2"><a class="whitelink" href="control/product/product/manage.do?method=addProductDetailOptionInput&id=${attribute.id }&productId=${product.id }">添加新的产品详情<b>选项</b></a></td></tr>

</c:forEach>
<tr><td colspan="2"><a class="whitelink" href="control/product/product/manage.do?method=addProductDetailAttributeInput&id=${group.id }&productId=${product.id }">添加新的产品详情<b>属性</b></a></td></tr>

</tbody></table>
      </c:if>
      <c:if test="${!empty group.post}">
      <textarea>${group.post}</textarea>
      </c:if>
      </td>
	</tr>
  
    </c:forEach>
<tr><td class="tdTitle" colspan="2"><a class="whitelink" href="control/product/product/manage.do?method=addProductDetailGroupInput&productId=${product.id }">添加新的产品详情<b>组</b></a></td></tr>
    
  </table>
</html:form>
<br>
</body>
</html>