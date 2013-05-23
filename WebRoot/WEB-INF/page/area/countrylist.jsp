<%@ page contentType="text/html;charset=utf-8" %>
<%@ include file="/WEB-INF/page/share/taglib.jsp" %>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<html>
<head>
<title>国家列表</title>
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

<form action="control/center/country/manage.do" method="post">
<input type="hidden" name="method"> 
  <table width="100%" border="0" cellspacing="1" cellpadding="2" align="center">
    <tr><td colspan="6"  bgcolor="6f8ac4" align="right">
   <font color="#FFFFFF">总记录数:${pm.total}条 |</font>
   </td></tr>
    <tr>
      <td width="5%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">国家 ID</font></div></td>
      <td width="10%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">国家名称</font></div></td>
      <td width="10%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">英文名称</font></div></td>
      <td width="10%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">简称2位</font></div></td>
      <td width="10%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">简称</font></div></td>
 	  <td nowrap bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">操作</font></div></td>
    </tr>
<!---------------------------LOOP START------------------------------>
<c:forEach items="${pm.datas}" var="country">
    <tr>
      <td bgcolor="f5f5f5"> <div align="center"><INPUT TYPE="checkbox" NAME="ids" value="${country.id}">${country.id }</div></td>
	  <td bgcolor="f5f5f5"> <div align="center">${country.cnName }</div></td>
	  <td bgcolor="f5f5f5"> <div align="center">${country.enName }</div></td>
	  <td bgcolor="f5f5f5"> <div align="center">${country.countries_iso_code_2 }</div></td>
	  <td bgcolor="f5f5f5"> <div align="center">${country.countries_iso_code_3 }</div></td>
	  <td bgcolor="f5f5f5"> 
	  <a href="control/center/country/manage.do?method=editInput&id=${country.id}" title="编辑">编辑</a>
	  <a href="control/center/country/manage.do?method=delete&id=${country.id}" title="删除">删除</a>
	  	  <a href="control/center/zone/manage.do?countryId=${country.id}" title="编辑下属地区">编辑下属地区</a>
	  	    <a href="control/center/zone/manage.do?method=generatejs&countryId=${country.id}" title="编辑下属地区">生成JSON格式文件</a>
	  </td>
</tr>
</c:forEach>
    <!----------------------LOOP END------------------------------->
    <tr> 
      <td bgcolor="f5f5f5" colspan="6" align="center"><table width="100%" border="0" cellspacing="1" cellpadding="3">
          <tr> 
            <td width="8%"><INPUT TYPE="checkbox" NAME="all" onclick="javascript:allselect(this, this.form.productids)">全选</td>
              <td width="85%">
              <input type="button" class="frm_btn" onClick="javascript:window.location.href='control/center/country/manage.do?method=addInput'" value="添加国家信息"> &nbsp;&nbsp;
			  <input name="query" type="button" class="frm_btn" id="query" onClick="javascript:window.location.href='control/center/role/manage.do?method=queryInput'" value=" 查 询 "> &nbsp;&nbsp;
		  <input name="query" type="button" class="frm_btn" id="query" onClick="javascript:actionEvent('deleteCountries','')" value=" 删除"> &nbsp;&nbsp;
				  

            </td>
          </tr>
        </table></td>
    </tr>
  </table>
 </form>
</body>
</html>