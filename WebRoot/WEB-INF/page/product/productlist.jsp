<%@ page contentType="text/html;charset=utf-8" %>
<%@ include file="/WEB-INF/page/share/taglib.jsp" %>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<html>
<head>
<title>产品列表</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<jsp:include page="/location_check.jsp"></jsp:include>
<link rel="stylesheet" href="css/vip.css" type="text/css">
<link href="css/product/list.css" rel="stylesheet" type="text/css" />

</head>

<body bgcolor="#FFFFFF" text="#000000" marginwidth="0" marginheight="0">

<html:form action="control/product/product/manage" method="post">

<input type="hidden" name="method"/>
<input type="hidden" name="yesOrNo"/>

  <table width="100%" border="0" cellspacing="1" cellpadding="2" align="center">
    <tr ><td colspan="9"  bgcolor="6f8ac4" align="right">
    总记录数:${pm.total}条 |<div class="pagesplit">  <%
  
String pageSplits = ConstantString.generatePageSplit(
		((PageModel)request.getAttribute("pm")).getTotal(),
		"control/product/product/manage.do?");
out.println(pageSplits);
%>  </div>

   </td></tr>
    <tr>
      <td width="5%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">产品ID</font></div></td>
      <td width="2%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">货号</font></div></td>
      <td width="22%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">产品名称</font></div></td>
	  <td width="12%" nowrap bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">所属分类</font></div></td>
	  <td width="4%" bgcolor="6f8ac4"><div align="center"><font color="#FFFFFF">底价</font></div></td>
	  <td width="5%" bgcolor="6f8ac4"><div align="center"><font color="#FFFFFF">销售价</font></div></td>
	  <td width="4%" bgcolor="6f8ac4"><div align="center"><font color="#FFFFFF">推荐</font></div></td>
	  <td width="13%" nowrap bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">操作</font></div></td>
	  <td bgcolor="6f8ac4"><div align="center"><font color="#FFFFFF"></font></div></td>
    </tr>
<!---------------------------LOOP START------------------------------>
<c:forEach items="${pm.datas}" var="product">
    <tr>
      <td bgcolor="f5f5f5"> <div align="center"><INPUT TYPE="checkbox" NAME="productIds" value="${product.id}">${product.id }</div></td>
      <td bgcolor="f5f5f5"> <div align="center">${product.code }</div></td>
     
	  <td bgcolor="f5f5f5"> <div align="center">${product.name }</div></td>
	  <td bgcolor="f5f5f5"> <div align="center">${product.category.name }</div></td>
	  <td bgcolor="f5f5f5"> <div align="center">${product.basePrice }</div></td>
	  <td bgcolor="f5f5f5"> <div align="center">${product.sellPrice }</div></td>
	  <td bgcolor="f5f5f5" align="center"><c:if test="${product.commend}">推荐</c:if><c:if test="${!product.commend}">--</c:if></td>
	   <td bgcolor="f5f5f5"> 
	   <div align="center"><a href="<html:rewrite action="control/product/product/manage"/>?method=edit&productId=${product.id }"> 
	   <img src="images/edit.gif" width="15" height="16" border="0" alt="Edit This Product" title="Edit This Product"></a>
<a href="<html:rewrite action="control/product/product/manage"/>?method=editMetaTags&productId=${product.id }"> <img src="images/icon_edit.gif" width="15" height="16" border="0" alt="Edit Meta Tags" title="Edit Meta Tags"></a>	  
	<a href="<html:rewrite action="control/product/product/manage"/>?method=deletePermanently&productId=${product.id }"> <img src="images/icon_delete.gif" width="15" height="16" border="0" alt="Delete This product" title="Delete This product"></a>	  
	<a href="<html:rewrite action="control/product/product/manage"/>?method=getProductDetails&productId=${product.id }"> <img src="images/anchor.png" width="15" height="16" border="0" alt="产品详情" title="产品详情"></a>	 	
	<a href="<html:rewrite action="control/product/product/manage"/>?method=updateTag&productId=${product.id }"> <img src="images/folder_edit.gif" width="15" height="16" border="0" alt="更新产品TAG" title="更新产品TAG"></a>	
		<a href="<html:rewrite action="control/product/product/manage"/>?method=createHtml&productId=${product.id }"> <img src="images/cog_edit.png" width="15" height="16" border="0" alt="创建该产品的html文件" title="创建该产品的html文件"></a>	 	
	  
	  <a href="<html:rewrite action="control/center/distribution/manage"/>?method=manageProductDistribution&productId=${product.id }"><img src="images/global/freight_icon.gif" border="0" title="运费设置" alt="运费设置"/></a>
	  </div>
	   
	 </td>
	   <td bgcolor="f5f5f5"> <div align="center"><a href="<html:rewrite action="control/product/style/manage"/>?productId=${product.id}">图片管理</a>
	   	<a href="<html:rewrite action="control/product/product/manage"/>?method=attributelist&productId=${product.id}">产品属性管理</a>
	   <a href="<html:rewrite action="control/product/categoryattribute/manage"/>?method=attributeset&productId=${product.id}&categoryId=${product.category.id}">分类属性过滤管理</a>
	 </div></td>
	</tr>
</c:forEach>
    <!----------------------LOOP END------------------------------->
    <tr> 
      <td bgcolor="f5f5f5" colspan="9" align="center"><table width="100%" border="0" cellspacing="1" cellpadding="3">
          <tr> 
            <td width="8%"><INPUT TYPE="checkbox" NAME="all" <c:if test="${pm.total<1}">disabled="disabled"</c:if>
             onclick="javascript:allselect(this, this.form.productids)">全选</td>
              <td width="85%">
              <input type="button" class="frm_btn" onClick="javascript:window.location.href='<html:rewrite action="control/product/product/manage"/>?method=addInput'" value="添加产品"> &nbsp;&nbsp;
			  <input name="query" type="button" class="frm_btn" id="query" onClick="javascript:window.location.href='<html:rewrite action="control/product/product/manage"/>?method=queryInput'" value=" 查 询 "> &nbsp;&nbsp;
              <input name="visible" type="button"
              <c:if test="${pm.total<1}">disabled="disabled"</c:if>
               class="frm_btn" onClick="javascript:actionEvent('updateVisible','true')" value=" 上 架 "> &nbsp;&nbsp;
              <input name="disable" type="button" class="frm_btn" 
              <c:if test="${pm.total<1}">disabled="disabled"</c:if>
              onClick="javascript:actionEvent('updateVisible','false')" value=" 下 架 "> &nbsp;&nbsp;
              <input name="commend" type="button" class="frm_btn" 
              <c:if test="${pm.total<1}">disabled="disabled"</c:if>
              onClick="javascript:actionEvent('updateCommend','true')" value=" 推 荐 "> &nbsp;&nbsp;
              <input name="uncommend" type="button" class="frm_btn"
				<c:if test="${pm.total<1}">disabled="disabled"</c:if>
				onClick="javascript:actionEvent('updateCommend','false')" value=" 不推荐 "> &nbsp;&nbsp;
				<a href="<html:rewrite action="control/product/product/manage"/>?method=createAllProductHTML">
				更新所有产品静态文件
				</a>
				<a href="<html:rewrite action="control/product/product/manage"/>?method=generateSmallImagesForAllProduct&outputWidth=500&outputHeight=500">
				生成产品小图
				</a>
				<input type="button" value="设置运费"/>
            </td>
          </tr>
        </table></td>
    </tr>
  </table>
 </html:form>
</body>
</html>