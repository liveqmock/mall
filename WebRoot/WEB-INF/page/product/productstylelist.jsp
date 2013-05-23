<%@ page contentType="text/html;charset=utf-8" %>
<%@ include file="/WEB-INF/page/share/taglib.jsp" %>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<html>
<head>
<title>产品样式显示</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<jsp:include page="/location_check.jsp"></jsp:include>
<link rel="stylesheet" href="css/vip.css" type="text/css">
<script language="JavaScript">
<!--
<%-- 
	//到指定的分页页面
	function topage(page){
		var form = document.forms[0];
		form.page.value=page;
		form.submit();
	}
	

		--%>
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
	
	function allselect(allobj,items){
	    var state = allobj.checked;
	    if(items.length){
	    	for(var i=0;i<items.length;i++){
	    		if(!items[i].disabled) items[i].checked=state;
	    	}
	    }else{
	    	if(!items.disabled) items.checked=state;
	    }
	}
	
	function actionEvent(methodname,visible){
		var form = document.forms[0];
		if(validateIsSelect(form.all, form.ids)){
			form.method.value=methodname;
			form.visible.value=visible;
			form.submit();
		}else{
			alert("请选择要操作的记录");
		}
	}
//-->
</script>

</head>

<body bgcolor="#FFFFFF" text="#000000" marginwidth="0" marginheight="0">
<form action="control/product/style/manage.do" method="post">
<input type="hidden" name="method" value="add">
<input type="hidden" name="visible" value="">
<input type="hidden" name="productId" value="${productId }"/>
  <table width="98%" border="0" cellspacing="1" cellpadding="2" align="center">
    <tr>
      <td width="8%" nowrap bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">选择</font></div></td>
      <td width="8%" nowrap bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">修改</font></div></td>
      <td width="37%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">名称</font></div></td>
      <td width="10%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">在售状态</font></div></td>
	  <td width="37%" nowrap bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">产品图片</font></div></td>
    </tr>
<!---------------------------LOOP START------------------------------>
<c:forEach items="${pm.datas}" var="productStyle">
    <tr>
      <td bgcolor="f5f5f5"> <div align="center"><INPUT TYPE="checkbox" NAME="ids" value=${productStyle.id}></div></td>
      <td bgcolor="f5f5f5"> <div align="center"><a href="<html:rewrite action="control/product/style/manage"/>?method=editInput&id=${productStyle.id}">
	  <img src="images/edit.gif" width="15" height="16" border="0"></a></div></td>
      <td bgcolor="f5f5f5"> <div align="center">${productStyle.name}</div></td>
      <td bgcolor="f5f5f5"> <div align="center">
      <a href="<html:rewrite action="control/product/style/manage"/>?method=updateVisible&id=${productStyle.id }&productId=${productStyle.product.id }">
      <c:if test="${productStyle.visible}">在售</c:if><c:if test="${!productStyle.visible}">停售</c:if>
       </a>
      </div></td>
	  <td bgcolor="f5f5f5"> <div align="center"><img src="${productStyle.imageUrl}" width="50"></div></td>
	</tr>
</c:forEach>
    <!----------------------LOOP END------------------------------->
    <tr> 
      <td bgcolor="f5f5f5" colspan="10" align="center"><table width="100%" border="0" cellspacing="1" cellpadding="3">
          <tr> 
            <td width="8%"><INPUT TYPE="checkbox" <c:if test="${fn:length(pm.datas)<1}">disabled="disabled"</c:if>
             NAME="all" onclick="javascript:allselect(this, this.form.ids)">全选</td>
              <td width="85%">
              <input type="button" class="frm_btn" onClick="javascript:window.location.href='<html:rewrite action="control/product/style/manage"/>?method=addInput&productId=${productId}'" value="添加产品图片"> &nbsp;&nbsp;
              <input type="button"
              <c:if test="${fn:length(pm.datas)<1}">disabled="disabled"</c:if>
               class="frm_btn" onClick="javascript:actionEvent('updateVisible','true')" value=" 上 架 "> &nbsp;&nbsp;
              <input name="disable" type="button" class="frm_btn" 
              <c:if test="${fn:length(pm.datas)<1}">disabled="disabled"</c:if>
              onClick="javascript:actionEvent('updateVisible','false')" value=" 下 架 "> &nbsp;&nbsp;
              <input type="button" class="frm_btn" onClick="javascript:window.location.href='<html:rewrite action="control/product/product/manage"/>'" value=" 返回 "> &nbsp;&nbsp;
            </td>
          </tr>
        </table></td>
    </tr>
  </table>
</form>
</body>
</html>