<%@ page contentType="text/html;charset=utf-8" %>
<%@ include file="/WEB-INF/page/share/taglib.jsp" %>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<html>
<head>
<title>产品属性列表</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<jsp:include page="/location_check.jsp"></jsp:include>
<link rel="stylesheet" href="css/vip.css" type="text/css">


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
    <tr><td colspan="6"  bgcolor="6f8ac4" align="right">

   </td></tr>
    <tr>
      <td width="5%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">属性 ID</font></div></td>
      <td width="30%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">属性名称</font></div></td>
	<td width="10%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">属性值</font></div></td>      
    
 	  <td nowrap bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">操作</font></div></td>
    </tr>
<!---------------------------LOOP START------------------------------>

<c:forEach items="${options}" var="attributeoption">

    <tr>
      <td bgcolor="f5f5f5"> <div><INPUT TYPE="checkbox" NAME="ids" value="${attributeoption.id}">${attributeoption.id }</div></td>
	  <td bgcolor="f5f5f5"> <div class="areaname">${attributeoption.value }</div></td>
	   <td bgcolor="f5f5f5"> <div align="center">${attributeoption.attribute.name }</div></td>
	  <!--   <td bgcolor="f5f5f5"> <div align="center">${country.enName }</div></td>
	  <td bgcolor="f5f5f5"> <div align="center">${country.countries_iso_code_2 }</div></td>
	  <td bgcolor="f5f5f5"> <div align="center">${country.countries_iso_code_3 }</div></td> -->
	  <td bgcolor="f5f5f5"> 
	  <a href="control/center/product/manage.do?method=attributeoptioninput&id=${attributeoption.id}" title="编辑">编辑</a>
	  <a href="control/center/product/manage.do?method=attributeoptioneditinput&id=${attributeoption.id}" title="编辑">编辑</a>
	  <a href="control/center/attributeoption/manage.do?method=delete&id=${attributeoption.id}" title="删除">删除</a>
	  </td>
</tr>
</c:forEach>
    <!----------------------LOOP END------------------------------->
    <tr> 
      <td bgcolor="f5f5f5" colspan="6" align="center"><table width="100%" border="0" cellspacing="1" cellpadding="3">
          <tr> 
            <td width="8%"><INPUT TYPE="checkbox" NAME="all" onclick="javascript:allselect(this, this.form.productids)">全选</td>
              <td width="85%">
              <input type="button" class="frm_btn" onClick="javascript:window.location.href='control/product/product/manage.do?method=attributeoptionaddinput&productId=${productForm.productId}&attributeId=${productForm.attributeId }'" value="添加属性信息"> &nbsp;&nbsp;
			  <input name="query" type="button" class="frm_btn" id="query" onClick="javascript:window.location.href='control/center/role/manage.do?method=queryInput'" value="查 询"> &nbsp;&nbsp;
		  <input name="query" type="button" class="frm_btn" id="query" onClick="javascript:actionEvent('deleteattributeoptions','')" value="删 除"> &nbsp;&nbsp;
		  <input  type="button" class="frm_btn" onClick="javascript:window.location.href='control/product/product/manage.do?method=attributelist&productId=${productForm.productId}'" value="返 回"> &nbsp;&nbsp;
				  

            </td>
          </tr>
        </table></td>
    </tr>
  </table>
 </form>
</body>
</html>