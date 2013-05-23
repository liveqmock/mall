<%@ page contentType="text/html;charset=utf-8" %>
<%@ include file="/WEB-INF/templates/default/share/taglib.jsp" %>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<html>
<head>
<title>产品类别属性过滤列表</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<jsp:include page="/WEB-INF/templates/default/share/backstage_location_check.jsp"></jsp:include>
<link rel="stylesheet" href="css/vip.css" type="text/css">
<script language="JavaScript">
<!--
	//到指定的分页页面
	function topage(page){
		var form = document.forms[0];
		form.page.value=page;
		form.submit();
	}
function checkConfirm(){

	return confirm("确定?");
}
var filterStr="";
var ini_filter ="${product.filter}";
//ss
var ids="";
var idsArray=[];
window.onload = function()
{
	if(ini_filter.length>0&&ini_filter.indexOf("_")>0){
	var ini_filterArray = ini_filter.split("-");
	
	//alert(ini_filterArray[m]);
	//}
	if(ids.charAt(ids.length-1)==","){
			var fa =(ids.substring(0,ids.length-1)).split(",");//fa=[4,6,8,9];
			for(var m in ini_filterArray){	//ini_filterArray=[4_18,6_23];
			for(var i in fa){
			var n =	document.getElementsByName("filter"+fa[i]);
				for(var j=0;j<n.length;j++) {

			 if(n[j].value==ini_filterArray[m]) {
			
				 n[j].checked=true;
			 }
			}
			}
		}
	}
	}
}
function addfilter(){
	filterStr="";
//	alert(ids);
	if(ids.charAt(ids.length-1)==","){
		idsArray =(ids.substring(0,ids.length-1)).split(",");
		//alert(idsArray);
	}
	for(var filterid in idsArray){
		check(idsArray[filterid]);
		//document.getElementsByName(idsArray[filterid])
		}
	if(filterStr.charAt(filterStr.length-1)=="-")
		filterStr=filterStr.substring(0,filterStr.length-1);
	document.getElementById("filter").value=filterStr;
	alert(document.getElementById("filter").value);
}

function check(filterid){
	
	var m = document.getElementsByName("filter"+filterid);
    for(var i=0;i<m.length;i++) {
    
     if(m[i].checked) {
    
    	 filterStr=filterStr + m[i].value +"-";
     }
    }
}
//-->
</script>

</head>

<body bgcolor="#FFFFFF" text="#000000" marginwidth="0" marginheight="0">

<form action="control/product/categoryattribute/manage.do" method="post" onsubmit="addfilter();">
<input  type="hidden" name="method" value="updateproductcategoryattribute" />
<input  type="hidden" name="filter" value="" id="filter"/>
<input  type="hidden" name="productId" value="${product.id}"/>
  <table width="98%" border="0" cellspacing="1" cellpadding="2" align="center">
      <tr ><td colspan="5"  bgcolor="6f8ac4">
	产品信息
  
   </td></tr>
         <tr ><td bgcolor="6f8ac4" align="right">
	名称：</td><td bgcolor="6f8ac4" colspan="2">${product.name }
  
   </td><td bgcolor="6f8ac4">所属分类</td><td bgcolor="6f8ac4">${product.category.name }</td></tr>
    <tr ><td colspan="5"  bgcolor="6f8ac4" align="right">
	<font color="#FFFFFF">总记录数:${fn:length(pm) }条 </font>
  
   </td></tr>
    <tr>
      <td width="5%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">ID</font></div></td>
   
      <td width="10%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">属性名称</font></div></td>
          <td bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">属性值</font></div></td>
        <td bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">操作</font></div></td>
         <td width="20%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">所属分类</font></div></td>
    </tr>

<!---------------------------LOOP START------------------------------>
<c:forEach items="${pm}" var="attribute">
    <tr>
      <td bgcolor="f5f5f5"> <div align="center">${attribute.id }</div></td>
      <td bgcolor="f5f5f5"> ${attribute.name }
     <script>ids+="${attribute.id },";</script>
      </td>

         <td bgcolor="f5f5f5">
         <c:forEach items="${attribute.options}" var="option">
        <input type="radio" name="filter${attribute.id }" value="${attribute.id }_${option.id}"/>${option.value }
 		<br />	

         </c:forEach>
         <input type="radio" name="filter${attribute.id }" value="${attribute.id }_0"/>默认属性
         	<br />
         
         </td> <td bgcolor="f5f5f5">
     
         </td>
                 <td bgcolor="f5f5f5"> 
        
        <a href="control/product/category/manage.do?parentId=<c:if test="${! empty attribute.category.parent}">
        ${attribute.category.parent.id}
        </c:if>">${attribute.category.name}</a></td>
	</tr>
</c:forEach>
    <!----------------------LOOP END------------------------------->
    <tr> 
      <td bgcolor="f5f5f5" colspan="5" align="center"><table width="100%" border="0" cellspacing="1" cellpadding="5">
          <tr> 
            <td width="5%"></td>
              <td width="85%">
              	
            </td>
          </tr>
        </table></td>
    </tr>
  </table>
  <input type="submit" value="确定" />
</form>

</body>
</html>