<%@ page contentType="text/html;charset=utf-8" %>
<%@ include file="/WEB-INF/page/share/taglib.jsp" %>
<%@ page import="com.hnfealean.sport.model.user_acl_module.MenuGroup" 
%><%@ page import="java.util.List" %>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<html>
<head>
<title>管理员左侧菜单组列表</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<jsp:include page="/location_check.jsp"></jsp:include>
<link rel="stylesheet" href="css/vip.css" type="text/css">

</head>

<body bgcolor="#FFFFFF" text="#000000" marginwidth="0" marginheight="0">

<form action="control/center/administrator/manage.do" method="post">
  <table width="100%" border="0" cellspacing="1" cellpadding="2" align="center">
    <tr><td colspan="5"  bgcolor="6f8ac4" align="right">
	 总记录数:${fn:length(menugroups)}条  <div class="pagesplit">  <%
  
String pageSplits = ConstantString.generatePageSplit(
		((List<MenuGroup>)request.getAttribute("menugroups")).size(),
		"control/center/adminmenu/manage.do?");
out.println(pageSplits);
%>  </div>
 
   </td></tr>
    <tr>
      <td width="5%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">ID</font></div></td>
      <td width="10%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">组名称</font></div></td>
      <td width="10%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">排序号</font></div></td>
      <td width="30%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">菜单项</font></div></td>
 	  <td nowrap bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">操作</font></div></td>
    </tr>
<!---------------------------LOOP START------------------------------>
<c:forEach items="${menugroups}" var="group">
    <tr>
      <td bgcolor="f5f5f5"> <div align="center"><INPUT TYPE="checkbox" NAME="ids" value="${group.id}">${group.id }</div></td>
	  <td bgcolor="f5f5f5"> <div align="center">${group.name }</div></td>
	   <td bgcolor="f5f5f5"> <div align="center">${group.orderNo }</div></td>
	    <td bgcolor="f5f5f5"> 
	    <c:forEach items="${group.menuItems}" var="item">
	    ${item.name }&nbsp;&nbsp;&nbsp;<a href="control/center/adminmenu/manage.do?method=editItem&menuItemId=${item.id }&id=${menuForm.id }">编辑</a>
	    <a href="control/center/adminmenu/manage.do?method=deleteItem&menuItemId=${item.id }&id=${menuForm.id }">删除</a><br>

	    </c:forEach>
	    	    <a href="control/center/adminmenu/manage.do?method=addItemInput&menuGroupId=${group.id }&id=${menuForm.id }">添加新菜单项</a>
	    </td>
	  <td bgcolor="f5f5f5"> 
	  <a href="control/center/adminmenu/manage.do?method=editGroup&menuGroupId=${group.id}" title="编辑菜单组">编辑菜单组</a>
	  <a href="control/center/adminmenu/manage.do?method=deleteGroup&menuGroupId=${group.id}" title="删除菜单组">删除菜单组</a>
	  </td>
</tr>
</c:forEach>
    <!----------------------LOOP END------------------------------->
    <tr> 
      <td bgcolor="f5f5f5" colspan="5" align="center"><table width="100%" border="0" cellspacing="1" cellpadding="5">
          <tr> 
            <td width="8%"><INPUT TYPE="checkbox" NAME="all" onclick="javascript:allselect(this, this.form.productids)">全选</td>
              <td width="85%">
              <a href="control/center/adminmenu/manage.do?method=addGroupInput&id=${menuForm.id }" title="添加菜单组">添加菜单组</a>
            </td>
          </tr>
        </table></td>
    </tr>
  </table>
 </form>
</body>
</html>