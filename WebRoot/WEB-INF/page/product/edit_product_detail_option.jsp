<%@ page contentType="text/html;charset=utf-8" %>
<%@ include file="/WEB-INF/page/share/taglib.jsp" %>
<html>
<head>
<title>产品详情属性选项编辑</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<jsp:include page="/location_check.jsp"></jsp:include>
<link rel="stylesheet" href="css/vip.css" type="text/css">

<script language="JavaScript">
function checkfm(form){
	if (trim(form.name.value)==""){
		alert("属性名称不能为空!");
		form.name.focus();
		return false;
	}
	return true;
}
</script>
</head>
<body bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<html:form action="control/product/product/manage" method="post">
<input type="hidden" name="method" value="updateProductDetailOption"/>
<input type="hidden" name="productId" value="${productForm.productId }"/><input type="hidden" name="id" value="${option.id }"/>
<br>
  <table width="90%" border="0" cellspacing="2" cellpadding="3" align="center">
    <tr bgcolor="6f8ac4"><td colspan="2"  > <font color="#FFFFFF"></font></td>
    </tr>
    <tr bgcolor="f5f5f5"> 
      <td width="22%" > <div align="right">产品详情属性选项名称</div></td>
      <td width="78%"> <input type="text" name="name" size="50" maxlength="50" value="${option.name}"/>
    </tr>
    <tr bgcolor="f5f5f5"> 
      <td width="22%" > <div align="right">产品详情属性选项值</div></td>
      <td width="78%"> <input type="text" name="value" size="50" maxlength="50" value="${option.value}"/>
    </tr>
    <tr bgcolor="f5f5f5"> 
      <td colspan="2"> <div align="center"> 
          <input type="submit" name="SYS_SET" value=" 确  定  " class="frm_btn">
         <input type="button" value=" 后  退  " class="frm_btn" onclick="javascript:history.back(-1)">
        </div></td>
    </tr>
  </table>
</html:form>
 
<br>
</body>
</html>