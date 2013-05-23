<%@ page contentType="text/html;charset=utf-8" %>
<%@ include file="/WEB-INF/page/share/taglib.jsp" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<title>添加产品:</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<jsp:include page="/location_check.jsp"></jsp:include>
<link rel="stylesheet" href="css/vip.css" type="text/css">

<SCRIPT language=JavaScript src="js/calendar.js"></SCRIPT>
<link rel="stylesheet" href="js/calendar.css" type="text/css">
<script type="text/javascript">
function SureSubmit(objForm){
	 objForm.submit();
}
</script>

<script type="text/javascript" src="js/jscripts/fckeditor/fckeditor.js"></script>
<script type="text/javascript">
window.onload = function()
{
var oFCKeditor = new FCKeditor( 'detail','80%','400') ;
oFCKeditor.BasePath = "js/jscripts/fckeditor/" ;
 oFCKeditor.Config["CustomConfigurationsPath"] = "js/jscripts/fckeditor/myconfig.js"  ;


oFCKeditor.ReplaceTextarea() ;
}
</script>
</head>

<body bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<form action="<html:rewrite action="control/product/product/manage"/>" enctype="multipart/form-data" method="post" name="add_product">
<input type="hidden" name="method" value="add">
<%--<html:hidden property="typeid"/>--%>
  <table width="98%" border="0" cellspacing="1" cellpadding="3" align="center">
    <tr bgcolor="6f8ac4"> 
      <td colspan="2" ><font color="#FFFFFF">添加产品：</font></td>
    </tr>
    <tr bgcolor="f5f5f5"> 
      <td width="10%"> <div align="right">产品名称  ：  </div></td>
      <td width="90%"><input type="text" name="name" size="50" maxlength="40"/><font color="#FF0000">*</font></td>
    </tr>
    <tr bgcolor="f5f5f5"> 
      <td width="10%"> <div align="right">产品类别 ： </div></td>
      <td width="90%"><input type="hidden" name="categoryId"/> <input type="text" name="categoryName" disabled="true" size="30"/> <font color="#FF0000">*</font> 
        <input type="button" name="select" value="选择..." onClick="javaScript:winOpen('<html:rewrite action="control/product/product/manage"/>?method=selectCategory','列表',600,400)">(<a href="<html:rewrite action='/control/product/category/manage'/>?method=addInput">添加产品类别</a>)      </td>
    </tr>
	<tr bgcolor="f5f5f5"> 
      <td width="10%"> <div align="right">底(采购)价 ： </div></td>
      <td width="90%"> <input type="text" name="basePrice" size="10" maxlength="10" onKeyPress="javascript:InputLongNumberCheck()"/>元 <font color="#FF0000">*</font></td>
    </tr>
	<tr bgcolor="f5f5f5"> 
      <td width="10%"> <div align="right">市场价 ： </div></td>
      <td width="90%"> <input type="text" name="marketPrice" size="10" maxlength="10" onKeyPress="javascript:InputLongNumberCheck()"/>元 <font color="#FF0000">*</font></td>
    </tr>
	<tr bgcolor="f5f5f5"> 
      <td width="10%"> <div align="right">销售价 ：</div></td>
      <td width="90%"> <input type="text" name="sellPrice" size="10" maxlength="10" onKeyPress="javascript:InputLongNumberCheck()"/>元 <font color="#FF0000">*</font></td>
    </tr>
    <tr bgcolor="f5f5f5"> 
      <td width="10%"> <div align="right">货号 ： </div></td>
      <td width="90%"> <input type="text" name="code" size="20" maxlength="30"/>(注:供货商提供的便于产品查找的编号)</td>
    </tr>
	<tr bgcolor="f5f5f5"> 
      <td width="10%"> <div align="right">产品图片 ： </div></td>
      <td width="90%"> 样式名称：<input name="productImagesAndStylesName" type="text" size="10">样式图片<input type="file" name="productImagesAndStylesImageFile" size="30"></td>
    </tr>
	<tr bgcolor="f5f5f5"> 
      <td width="10%"> <div align="right">品牌 ： </div></td>
      <td width="90%"> <select name="brandId">
          <option  value="">******</option>
       <c:forEach items="${brands.datas}" var="brand" varStatus="loop">
      	 <option  value="${brand.id}">${brand.name}</option>
       </c:forEach>
        </select>(<a href="<html:rewrite action='/control/product/brand/manage'/>?method=addInput">添加品牌</a>)</td>
    </tr>
	<tr bgcolor="f5f5f5"> 
      <td width="10%"> <div align="right">适用性别 ： </div></td>
      <td width="90%"><select name="sex">   
			<option  value="男女不限">男女不限</option>   
			<option  value="男士">男士</option>   
			<option  value="女士">女士</option>
		</select>		</td>
    </tr>
	<tr bgcolor="f5f5f5"> 
      <td width="10%"> <div align="right">型号 ：</div></td>
      <td width="90%"> <input type="text" name="model" size="35" maxlength="30"/></td>
    </tr>
	<tr bgcolor="f5f5f5">
      <td><div align="right">重量 ：</div></td>
	  <td><input type="text" name="weight" size="10" maxlength="10" onKeyPress="javascript:InputIntNumberCheck()"/></td>
    </tr>
	<tr bgcolor="f5f5f5">
      <td><div align="right">页面标题 ：</div></td>
	  <td><input type="text" name="titleInPage" size="50" maxlength="50"/></td>
    </tr>
	<tr bgcolor="f5f5f5">
      <td><div align="right">页面关键词 ：</div></td>
	  <td><input type="text" name="meta_KeyWords" size="10" maxlength="10"/></td>
    </tr>
		<tr bgcolor="f5f5f5">
      <td><div align="right">页面描述 ：</div></td>
	  <td><input type="text" name="meta_Description" size="100" maxlength="100"/></td>
    </tr>
	<tr bgcolor="f5f5f5">
      <td><div align="right">制造商 ：</div></td>
	  <td><input type="text" name="manufacturersId" size="10" maxlength="10"/></td>
    </tr>	
	<tr bgcolor="f5f5f5">
      <td><div align="right">文件名称 ：</div></td>
	  <td><input type="text" name="shtml_File_Name" size="30" maxlength="80"/></td>
    </tr>			
	<tr bgcolor="f5f5f5"> 
      <td width="10%"> <div align="right">附加信息 ：</div></td>
      <td width="90%"> <input type="text" name="additionInfo" size="35" maxlength="30" /></td>
    </tr>
 	<tr bgcolor="f5f5f5"> 
      <td width="10%"> <div align="right">添加日期 ：</div></td>
      <td width="90%"><input type="text" name="createDate" size="35" maxlength="30" onfocus="HS_setDate(this)"/>
     </td>
    </tr>
  	<tr bgcolor="f5f5f5"> 
      <td width="10%"> <div align="right">有效期至 ：</div></td>
      <td width="90%"><input type="text" name="expireDate" size="35" maxlength="30" onfocus="HS_setDate(this)"/>
     </td>
    </tr>      
	<tr bgcolor="f5f5f5"> 
      <td width="10%" valign="top"> <div align="right">产品简介<font color="#FF0000">*</font> </div></td>
      <td width="90%"><textarea name="detail" cols="80" rows="23"></textarea></td>
	</tr>
    <tr bgcolor="f5f5f5"> 
      <td colspan="2"> <div align="center"> 
          <input type="button" name="Add" value=" 确 认 " class="frm_btn" onClick="javascript:SureSubmit(this.form)">
          &nbsp;&nbsp;<input type="button" name="Button" value=" 返 回 " class="frm_btn" onClick="javascript:history.back()">
        </div></td>
    </tr>
  </table>
</form>

<br>
</body>
</html>