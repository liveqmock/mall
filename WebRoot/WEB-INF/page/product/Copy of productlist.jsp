<%@ page contentType="text/html;charset=utf-8" %>
<%@ include file="/WEB-INF/page/share/taglib.jsp" %>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<html>
<head>
<title>产品列表</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<jsp:include page="/location_check.jsp"></jsp:include>
<link rel="stylesheet" href="css/vip.css" type="text/css">
<script language="JavaScript">
<!--
	//到指定的分页页面
	function topage(page){
		var form = document.forms[0];
		form.page.value=page;
		form.submit();
	}
	
	function actionEvent(methodname,methods){
		var form = document.forms[0];
		if(validateIsSelect(form.all, form.productIds)){
		//	form.action='<html:rewrite action="control/product/manage"/>';
			form.method.value=methodname;
			form.yesOrNo.value=methods;
			//alert(form.yesOrNo.value);
			form.submit();
		}else{
			alert("请选择要操作的记录");
		}
	}
	
	function allselect(allobj,items){
	    var state = allobj.checked;
	    if(items.length){
	    	for(var i=0;i<items.length;i++){
	    		if(!items[i].disabled) items[i].checked=state;
	    	}
	    }else{
	    	if(!items[i].disabled) items.checked=state;
	    }
	}
	/*
	 * 判断是否选择了记录
     */
	function validateIsSelect(allobj,items){
	    var state = allobj.checked;
	    if(items.length){
	    	for(var i=0;i<items.length;i++){
	    		if(items[i].checked) return true;
	    	}
	    }else{
	    	if(items.checked) return true;
	    }
	    return false;
	}
//-->
</script>

</head>

<body bgcolor="#FFFFFF" text="#000000" marginwidth="0" marginheight="0">

<%--
<html:form action="control/product/product/manage" method="post">
<input type="hidden" name="method" value="">
<html:hidden property="page"/>
<html:hidden property="query"/>
<html:hidden property="name"/>
<html:hidden property="categoryId"/>
<html:hidden property="startsellprice"/>
<html:hidden property="endsellprice"/>
<html:hidden property="startbaseprice"/>
<html:hidden property="endbaseprice"/>
<html:hidden property="code"/>
<html:hidden property="brandid"/>
--%>
<html:form action="control/product/product/manage" method="post">

<input type="hidden" name="method"/>
<input type="hidden" name="yesOrNo"/>

  <table width="98%" border="0" cellspacing="1" cellpadding="2" align="center">
    <tr ><td colspan="10"  bgcolor="6f8ac4" align="right">
  <pg:pager items="${pm.total }" url="manage.do" export="currentPageNumber=pageNumber">
<pg:param name="parentId"/>
	<font color="#FFFFFF">总记录数:${pm.total}条 |</font>
	<pg:first><a href="${pageUrl}">首页</a></pg:first>
	<pg:prev><a href="${pageUrl }">前页</a></pg:prev>
	<pg:pages>
		<c:choose>
		<c:when test="${ currentPageNumber eq pageNumber}">
		
		<font color="#FFFFFF">当前页:第</font><font color="red">${pageNumber}</font><font color="#FFFFFF">页  |</font>
		</c:when>
		<c:otherwise>
		<a href="${pageUrl }">${pageNumber }</a>
		</c:otherwise>
		</c:choose>
	</pg:pages>
	<pg:next><a href="${pageUrl }">下页</a></pg:next>
	<pg:last><a href="${pageUrl }">尾页</a></pg:last>
</pg:pager> 
   </td></tr>
    <tr>
      <td width="7%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">产品ID</font></div></td>
      <td width="8%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">货号</font></div></td>
      <td width="5%" nowrap bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">修改</font></div></td>
      <td width="30%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">产品名称</font></div></td>
	  <td width="12%" nowrap bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">所属分类</font></div></td>
	  <td width="7%" bgcolor="6f8ac4"><div align="center"><font color="#FFFFFF">底价</font></div></td>
	  <td width="7%" bgcolor="6f8ac4"><div align="center"><font color="#FFFFFF">销售价</font></div></td>
	  <td width="6%" bgcolor="6f8ac4"><div align="center"><font color="#FFFFFF">销售情况</font></div></td>
	  <td width="6%" bgcolor="6f8ac4"><div align="center"><font color="#FFFFFF">推荐</font></div></td>
	  <td width="12%" bgcolor="6f8ac4"><div align="center"><font color="#FFFFFF"></font></div></td>
    </tr>
<!---------------------------LOOP START------------------------------>
<c:forEach items="${pm.datas}" var="product">
    <tr>
      <td bgcolor="f5f5f5"> <div align="center"><INPUT TYPE="checkbox" NAME="productIds" value="${product.id}">${product.id }</div></td>
      <td bgcolor="f5f5f5"> <div align="center">${product.code }</div></td>
      <td bgcolor="f5f5f5"> <div align="center"><a href="<html:rewrite action="control/product/product/manage"/>?method=edit&productId=${product.id}">
	  <img src="images/edit.gif" width="15" height="16" border="0"></a></div></td>
	  <td bgcolor="f5f5f5"> <div align="center">${product.name }</div></td>
	  <td bgcolor="f5f5f5"> <div align="center">${product.category.name }</div></td>
	  <td bgcolor="f5f5f5"> <div align="center">${product.basePrice }</div></td>
	  <td bgcolor="f5f5f5"> <div align="center">${product.sellPrice }</div></td>
	  <td bgcolor="f5f5f5" align="center"><c:if test="${product.visible}">在售</c:if><c:if test="${!product.visible}">停售</c:if></td>
	  <td bgcolor="f5f5f5" align="center"><c:if test="${product.commend}">推荐</c:if><c:if test="${!product.commend}">--</c:if></td>
	   <td bgcolor="f5f5f5"> <div align="center"><a href="<html:rewrite action="control/product/style/manage"/>?productId=${product.id}">产品图片管理</a></div></td>
	</tr>
</c:forEach>
    <!----------------------LOOP END------------------------------->
    <tr> 
      <td bgcolor="f5f5f5" colspan="10" align="center"><table width="100%" border="0" cellspacing="1" cellpadding="3">
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
            </td>
          </tr>
        </table></td>
    </tr>
  </table>
 </html:form>
</body>
</html>