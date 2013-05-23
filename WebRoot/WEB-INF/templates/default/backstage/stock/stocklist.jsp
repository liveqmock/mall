<%@ page contentType="text/html;charset=utf-8" %>
<%@ include file="/WEB-INF/templates/default/share/taglib.jsp" %>
<html>
<head>
<title>库存列表</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
 <%@ include file="/WEB-INF/templates/default/share/backstage_location_check.jsp" %>  
<link rel="stylesheet" href="css/vip.css" type="text/css">
<style type="text/css">
.ok{color:#339900}</style>
<script language="JavaScript">
<!--
	//到指定的分页页面
	function topage(page){
		var form = document.forms[0];
		form.page.value=page;
		form.submit();
	}
if (typeof window.hnfealean != "object" || window.hnfealean == null) {
    window.hnfealean = {}
}
hnfealean.stock = {};
window.hnfealean.stock =function(s){
var stock = eval(s);
if(stock.ok==1){
	//alert("");
	document.getElementById("updatestock"+stock.id).innerHTML="更新成功";
	var o =document.getElementById("script"+stock.id);
	o.parentNode.removeChild(o);
}
}
function updatestock(id,value){
	document.getElementById("updatestock"+id).innerHTML="";
	C=document.createElement("SCRIPT");
	C.src="<html:rewrite action="control/product/stock/manage"/>"+"?method=update&id="+id+"&stock="+value;
	C.charset="gb2312";
	C.id="script"+id;
	document.body.appendChild(C);
	//window.open(C.src);
	//document.getElementById("updatebutton"+id).innerHTML+=
}
//-->
</script>

</head>

<body bgcolor="#FFFFFF" text="#000000" marginwidth="0" marginheight="0">
  <table width="98%" border="0" cellspacing="1" cellpadding="2" align="center">
    <tr ><td colspan="4" bgcolor="6f8ac4" align="right">
  总共 ${pm.total }条记录
   <div class="pagesplit">  <%
  
String pageSplits = ConstantString.generatePageSplit(
		((PageModel)request.getAttribute("pm")).getTotal(),
		"control/product/stock/manage.do?");
out.println(pageSplits);
%>  </div>
   </td></tr>
    <tr>
      <td width="5%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">代号</font></div></td>
    
      <td width="35%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">名称</font></div></td>
      <td width="20%" nowrap bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">库存</font></div></td>
        <td nowrap bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">更新</font></div></td>
	  
    </tr>
<!---------------------------LOOP START------------------------------>
<c:forEach items="${pm.datas}" var="product">
    <tr>
      <td bgcolor="f5f5f5" height="50" width="30"><input type="checkbox" name="ids" value="${product.id}"/> ${product.id}</td>
           <td bgcolor="f5f5f5" height="50"> ${product.name}</td>
            
	  <td bgcolor="f5f5f5"> <input type="text" value="${product.quantity }" id="stock${product.id}"/></td>
      <td bgcolor="f5f5f5" height="50">
	 <input type="button" value="更新" id="update${product.id}" onclick="updatestock(${product.id},document.getElementById('stock${product.id}').value)"/>
	 <span id="updatestock${product.id}" class="ok"></span>
	  
	  </td>

	</tr>
</c:forEach>
    <!----------------------LOOP END------------------------------->
    <tr> 
      <td bgcolor="f5f5f5" colspan="4" align="center"></td>
    </tr>
  </table>

</body>
</html>