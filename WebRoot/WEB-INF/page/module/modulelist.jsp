<%@ page contentType="text/html;charset=utf-8" %>
<%@ include file="/WEB-INF/page/share/taglib.jsp" %>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<html>
<head>
<title>模块列表</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<jsp:include page="/location_check.jsp"></jsp:include>
<link rel="stylesheet" href="css/vip.css" type="text/css">
<link href="css/product/list.css" rel="stylesheet" type="text/css" />
</head>

<body bgcolor="#FFFFFF" text="#000000" marginwidth="0" marginheight="0">

<form action="control/center/module/manage.do" method="post">
  <table width="100%" border="0" cellspacing="1" cellpadding="2" align="center">
    <tr><td colspan="4"  bgcolor="6f8ac4" align="right">

   <div class="pagesplit">  <%
  
String pageSplits = ConstantString.generatePageSplit(
		((PageModel)request.getAttribute("pm")).getTotal(),
		"control/center/module/manage.do?");
out.println(pageSplits);
%>  </div>
   </td></tr>
    <tr>
      <td width="5%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">模块ID</font></div></td>
      <td width="10%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">模块名称</font></div></td>
      <td width="30%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">模块路径</font></div></td>
	  <td nowrap bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">操作</font></div></td>
    </tr>
<!---------------------------LOOP START------------------------------>
<c:forEach items="${pm.datas}" var="module">
    <tr>
      <td bgcolor="f5f5f5"> <div align="center">
      <INPUT TYPE="checkbox" NAME="moduleIds" value="${module.id}">${module.id }</div></td>
	  <td bgcolor="f5f5f5"> <div align="left">
	  <a href="control/center/module/manage.do?parentId=${module.id}">${module.name }</a></div></td>
	  <td bgcolor="f5f5f5"> <div align="left">${module.url}</div></td>	  
	  <td bgcolor="f5f5f5"> 
	  <div align="left">
	  	   <c:if test="${!empty module.functions}"><c:forEach items="${module.functions}" var="function" varStatus="status" begin="0" step="1">
			&nbsp;&nbsp;
	  	   ${function.functionName}(${function.functionDescription})&nbsp;
	  	   <a href="control/center/module/manage.do?method=editModuleFunction&id=${function.id}" class="whitelink">编辑</a>&nbsp;
	  	   <a href="control/center/module/manage.do?method=deleteModuleFunction&id=${function.id}" class="whitelink">删除</a>&nbsp;&nbsp;
	  	   	  	   <c:if test="${status.count%2==0 }"><br>
				</c:if>
	  	 
	  	   </c:forEach>
	   </c:if><br>
	  <a href="control/center/module/manage.do?method=addFunctionInput&id=${module.id}">添加方法</a>
	  <a href="control/center/module/manage.do?method=edit&id=${module.id}">
	   <img width="15" height="16" border="0" title="Edit This Module" alt="Edit This Module" src="images/edit.gif"></a>
	   <a href="control/center/module/manage.do?method=delete&id=${module.id}">
	   	<img width="15" height="16" border="0" title="Delete This Module" alt="Delete This Module" src="images/icon_delete.gif">
	   </a>

	  </div>

	   </td>
</tr>
</c:forEach>
    <!----------------------LOOP END------------------------------->
    <tr> 
      <td bgcolor="f5f5f5" colspan="4" align="center"><table width="100%" border="0" cellspacing="1" cellpadding="3">
          <tr> 
            <td width="8%"><INPUT TYPE="checkbox" NAME="all" onclick="javascript:allselect(this, this.form.productids)">全选</td>
              <td width="85%">
              <input type="button" class="frm_btn" onClick="javascript:window.location.href='control/center/module/manage.do?method=addInput&parentId=${parentId}'" value="添加模块"> &nbsp;&nbsp;
			  <input name="query" type="button" class="frm_btn" id="query" onClick="javascript:window.location.href='control/center/module/manage.do?method=queryInput'" value="查 询"> &nbsp;&nbsp;
              <input name="visible" type="button" 
               class="frm_btn" onClick="javascript:window.location.href='control/center/module/manage.do?id=${parentId}'" value="返   回"> &nbsp;&nbsp;
              <input name="disable" type="button" class="frm_btn" 
              onClick="javascript:actionEvent('updateVisible','false')" value="停 用"> &nbsp;&nbsp;

            </td>
          </tr>
        </table></td>
    </tr>
  </table>
 </form>
</body>
</html>