<%@ page contentType="text/html;charset=utf-8" %>
<%@ include file="/WEB-INF/templates/default/share/taglib.jsp" %>
<html>
<head>
<title> 选 </title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<link rel="stylesheet" href="../../../css/vip.css" type="text/css">
<SCRIPT language="JavaScript" src="../../../js/FoshanRen.js"></SCRIPT>
<SCRIPT language="JavaScript" src="../../../js/xmlhttp.js"></SCRIPT>

<SCRIPT language="JavaScript">
function checkIt(){
	// document.getElementsByName("type");
	//alert("hehe");
	for(var i=0;i<document.getElementsByName("category").length;i++){
		if(document.getElementsByName("category")[i].checked)
			{
			//alert("haha");
			var form = opener.document.forms[0];
			if (form){
				form.categoryName.value = document.getElementsByName("category")[i].value;
			//	alert(form.categoryName.value);
				form.categoryId.value = document.getElementsByName("category")[i].title;
			//	alert(form.categoryId.value);
				//alert("tableIndex:"+document.getElementsByName("type")[i].title);
				//alert("parent.categoryId.value:"+ document.getElementsByName("type")[i].title);
			}
			
			break;
		}


	}
	window.close();
}

</SCRIPT>
<style>
<!--
.inputText{
	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-size: 12px;
	color: #666666;
	border: 1px solid #999999;
}
body {
	font-family: Georgia, "Times New Roman", Times, serif;
	font-size: 12px;
	color: #666666;
}

.type  {
	width:19%;display:inline
}

.frm_btn {
	margin:20px;
}
-->
</style>
</head>

<body>

<pg:pager items="${pm.total }" url="manage.do" export="currentPageNumber=pageNumber" isOffset="false" maxPageItems="6">

	<pg:param name="method" value="selectCategory"/>
	
	<pg:first><a href="${pageUrl}">首页</a></pg:first>
	<pg:prev><a href="${pageUrl }">前页</a></pg:prev>
	<pg:pages>
		<c:choose>
		<c:when test="${ currentPageNumber eq pageNumber}">
		
		<font color="red">前页:</font>${pageNumber}<font color="#red">页  |</font>
		</c:when>
		<c:otherwise>
		<a href="${pageUrl }">${pageNumber }</a>
		</c:otherwise>
		</c:choose>
	</pg:pages>
	<pg:next><a href="${pageUrl }">下一页</a></pg:next>
	<pg:last><a href="${pageUrl }">尾页</a></pg:last>
		    
</pg:pager> 

请选择:<br>
:<a href='<html:rewrite action="control/product/product/manage"/>?method=selectCategory'>目录</a> <c:out value="${menu}" escapeXml="false"/>

	<div width="100%" border="0">

	<c:forEach items="${pm.datas}" var="category">		

	<div class="type"><c:if test="${fn:length(category.children)>0}">
		<a href="?method=selectCategory&categoryId=${category.id}"><b>${category.name}</b></a></c:if>
		<c:if test="${fn:length(category.children)==0}"> <INPUT TYPE="radio" NAME="category" value="${category.name}" title="${category.id}">${category.name}</c:if>
	</div>
		<%-- <c:if test="${loop.count%5==0}"></tr><tr></c:if> --%>
	</c:forEach>


</div>

		<input type='button' name='create' value=" 确  定  " class="frm_btn" onClick="javascript:checkIt()">
		<input type='button' name="cancel" class="frm_btn" onClick="javaScript:window.close()" value=" 取  消  "> 
		<input type='button' name="cancel" class="frm_btn" onClick="javaScript:history.back()" value=" 后  退  "> 

</body>
</html>