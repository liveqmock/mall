<%@ page contentType="text/html;charset=utf-8" %>
<%@ include file="/WEB-INF/page/share/taglib.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<title>修改产品：</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<jsp:include page="/location_check.jsp"></jsp:include>
<link rel="stylesheet" href="css/vip.css" type="text/css">

<script type="text/javascript" src="js/jscripts/fckeditor/fckeditor.js"></script>
<script type="text/javascript">
window.onload = function()
{
var oFCKeditor = new FCKeditor( 'detail','80%','400') ;
oFCKeditor.BasePath = "js/jscripts/fckeditor/" ;
 oFCKeditor.Config["CustomConfigurationsPath"] = "js/jscripts/fckeditor/myconfig.js"  ;


oFCKeditor.ReplaceTextarea() ;
}
function SureSubmit(objForm){
 objForm.submit();
} 

</script>
</head>

<body bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<html:form action="control/product/product/manage" enctype="multipart/form-data" method="post">
<input type="hidden" name="method" value="update">
<input type="hidden" name="productId" value="${product.id }">
  <table width="98%" border="0" cellspacing="1" cellpadding="3" align="center">
    <tr bgcolor="6f8ac4"> 
      <td colspan="2" ><font color="#FFFFFF">添加产品：</font></td>
    </tr>
    <tr bgcolor="f5f5f5"> 
      <td width="25%"> <div align="right">产品名称 ：  </div></td>
      <td width="75%"> <html:text property="name" size="50" maxlength="40" value="${product.name}"/><font color="#FF0000">*</font></td>
    </tr>
    <tr bgcolor="f5f5f5"> 
      <td width="25%"> <div align="right">产品类别 ：<font color="#FF0000">*</font>  </div></td>
      <td width="75%"> <input type="hidden" name="categoryId" value="${product.category.id}"/>
      <input type="text" name="categoryName" disabled="true" size="30" value="${product.category.name}"/> 
        <input type="button" name="select" value="选择.." onClick="javaScript:winOpen('<html:rewrite action="control/product/product/manage"/>?method=selectCategory','new',600,400)">(<a href="<html:rewrite action='/control/product/category/manage'/>?method=addInput">添加产品类别</a>)
      </td>
    </tr>
	<tr bgcolor="f5f5f5"> 
      <td width="25%"> <div align="right">底(采购)价 ： </div></td>
      <td width="75%"> <html:text property="basePrice" value="${product.basePrice}" size="10" maxlength="10" onkeypress="javascript:InputLongNumberCheck()"/>元 <font color="#FF0000">*</font></td>
    </tr>
	<tr bgcolor="f5f5f5"> 
      <td width="25%"> <div align="right">市场价 ： </div></td>
      <td width="75%"> <html:text property="marketPrice" value="${product.marketPrice}" size="10" maxlength="10" onkeypress="javascript:InputLongNumberCheck()"/>元 <font color="#FF0000">*</font></td>
    </tr>
	<tr bgcolor="f5f5f5"> 
      <td width="25%"> <div align="right">销售价 ： </div></td>
      <td width="75%"> <html:text property="sellPrice" value="${product.sellPrice}" size="10" maxlength="10" onkeypress="javascript:InputLongNumberCheck()"/>元 <font color="#FF0000">*</font></td>
    </tr>
    <tr bgcolor="f5f5f5"> 
      <td width="25%"> <div align="right">货号 ：	</div></td>
      <td width="75%"> <html:text property="code" value="${product.code}" size="20" maxlength="30"/>(注:供货商提供的便于产品查找的编号)</td>
    </tr>
	<!-- <tr bgcolor="f5f5f5"> 
      <td width="25%"> <div align="right">产品图片 ： </div></td>
            <td width="75%"></td>
    </tr> -->
	<tr bgcolor="f5f5f5"> 
	<td width="25%"> <div align="right">品牌： </div></td>
      <td width="75%"> <%--<html:select property="brandId">
          <html:option value="">******</html:option>
          <html:optionsCollection name="brands" label="name" value="code"/>
        </html:select>--%>
        
         <select name="brandId">
			<option>******</option>
       <c:forEach items="${brands.datas}" var="brand" varStatus="loop">
      	 <option <c:if test="${brand.id==product.brand.id}">selected</c:if> value="${brand.id}">${brand.name}</option>
      	 
       </c:forEach>
        (<a href="<html:rewrite action='/control/brand/manage'/>?method=addUI">品牌</a>)</td>
    </tr>
    
	<tr bgcolor="f5f5f5"> 
      <td width="25%"> <div align="right">适用性别 ： </div></td>
      <td width="75%"><html:select property="sex">   
			<html:option value="男女不限">男女不限</html:option>   
			<html:option value="男士">男士</html:option>   
			<html:option value="女士">女士</html:option>
		</html:select>
		</td>
    </tr>
	<tr bgcolor="f5f5f5"> 
      <td width="25%"> <div align="right">型号 ： </div></td>
      <td width="75%"> <html:text property="model" value="${product.model}" size="35" maxlength="30"/></td>
    </tr>
	<tr bgcolor="f5f5f5"> 
      <td width="25%"> <div align="right">重量 ： </div></td>
      <td width="75%"> <html:text property="weight" value="${product.weight}" size="10" maxlength="10" onkeypress="javascript:InputIntNumberCheck()"/></td>
    </tr>
	<tr bgcolor="f5f5f5"> 
      <td width="25%"> <div align="right">附加信息 ： </div></td>
      <td width="75%"> <html:text property="additionInfo" value="${product.additionInfo}" size="35" maxlength="30" /></td>
    </tr>
	<tr bgcolor="f5f5f5">
      <td width="25%" valign="top"> <div align="right">产品简介<font color="#FF0000">*</font> </div></td>
      <td width="75%"><html:textarea property="detail" value="${product.detail}" cols="80" rows="23"></html:textarea></td>
	</tr>
    <tr bgcolor="f5f5f5"> 
      <td colspan="2"> <div align="center"> 
          <input type="button" name="edit" value=" 确 认 " class="frm_btn" onClick="javascript:SureSubmit(this.form)">
          &nbsp;&nbsp;<input type="button" name="Button" value=" 返 回  " class="frm_btn" onclick="javascript:history.back()">
        </div></td>
    </tr>
   <%-- <c:forEach items="${product.groups}" var="group">
	<tr bgcolor="f5f5f5">
      <td width="25%" valign="top"> <div align="right">${group.name}<font color="#FF0000">*</font> </div></td>
      <td width="75%">
      <c:if test="${empty group.post}">
      <table cellspacing="1" cellpadding="0" border="0" width="100%" class="Ptable"><tbody>
      <c:forEach items="${group.detailAttributes}" var="attribute">
      
<tr><th colspan="2" class="tdTitle">${attribute.name }</th></tr>
 <c:forEach items="${attribute.options}" var="option">
<tr><td class="tdTitle">${option.name }</td><td>${option.value}</td></tr>
</c:forEach>
</c:forEach>
</tbody></table>
      </c:if>
      </td>
	</tr>    
    </c:forEach> --%>
  </table>
<html:hidden property="shtml_File_Name" value="${product.shtml_File_Name}"/>
<html:hidden property="titleInPage" value="${product.titleInPage}"/>
<html:hidden property="meta_KeyWords" value="${product.meta_KeyWords}"/>
<html:hidden property="meta_Description" value="${product.meta_Description}"/>
<html:hidden property="manufacturersId" value=""/>
</html:form>
<br>
</body>
</html>