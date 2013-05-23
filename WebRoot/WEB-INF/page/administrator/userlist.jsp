<%@ page contentType="text/html;charset=utf-8" %>
<%@ include file="/WEB-INF/page/share/taglib.jsp" %>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<html>
<head>
<title>管理员列表</title>
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

<form action="control/center/administrator/manage.do" method="post">
  <table width="100%" border="0" cellspacing="1" cellpadding="2" align="center">
    <tr><td colspan="4"  bgcolor="6f8ac4" align="right">
	 总记录数:${pm.total}条  <div class="pagesplit">  <%
  
String pageSplits = ConstantString.generatePageSplit(
		((PageModel)request.getAttribute("pm")).getTotal(),
		"control/center/administrator/manage.do?");
out.println(pageSplits);
%>  </div>
  <pg:pager items="${pm.total }" url="manage.do" export="currentPageNumber=pageNumber">
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
      <td width="5%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">管理员ID</font></div></td>
      <td width="10%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">管理员名称</font></div></td>
 	  <td nowrap bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">操作</font></div></td>
    </tr>
<!---------------------------LOOP START------------------------------>
<c:forEach items="${pm.datas}" var="admin">
    <tr>
      <td bgcolor="f5f5f5"> <div align="center"><INPUT TYPE="checkbox" NAME="productIds" value="${admin.id}">${admin.id }</div></td>
	  <td bgcolor="f5f5f5"> <div align="center">${admin.name }</div></td>
	  <td bgcolor="f5f5f5"> 
	  <a href="control/center/administrator/manage.do?method=updateInput&id=${admin.id}" title="编辑管理员帐号">编辑管理员帐号</a>
	  <a href="control/center/administrator/manage.do?method=roleManager&id=${admin.id}" title="角色管理">角色管理</a>
	  <a href="control/center/adminmenu/manage.do?id=${admin.id}" title="左侧菜单管理">左侧菜单管理</a>
<!-- 	 <a href="control/center/acl/manage.do?method=roleAclList&principalType=Administrator&principalSn=${admin.id}" title="权限设置">权限设置</a> -->
	  </td>
</tr>
</c:forEach>
    <!----------------------LOOP END------------------------------->
    <tr> 
      <td bgcolor="f5f5f5" colspan="4" align="center"><table width="100%" border="0" cellspacing="1" cellpadding="3">
          <tr> 
            <td width="8%"><INPUT TYPE="checkbox" NAME="all" onclick="javascript:allselect(this, this.form.productids)">全选</td>
              <td width="85%">
              <input type="button" class="frm_btn" onClick="javascript:window.location.href='control/center/administrator/manage.do?method=addInput'" value="添加管理员"> &nbsp;&nbsp;
			  <input name="query" type="button" class="frm_btn" id="query" onClick="javascript:window.location.href='control/center/administrator/manage.do?method=queryInput'" value=" 查 询 "> &nbsp;&nbsp;
              <input name="visible" type="button" 
               class="frm_btn" onClick="javascript:actionEvent('updateVisible','true')" value=" 启 用  "> &nbsp;&nbsp;
              <input name="disable" type="button" class="frm_btn" 
              onClick="javascript:actionEvent('updateVisible','false')" value=" 停 用  "> &nbsp;&nbsp;

            </td>
          </tr>
        </table></td>
    </tr>
  </table>
 </form>
</body>
</html>