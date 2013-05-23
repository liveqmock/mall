<%@ page contentType="text/html;charset=utf-8" %>
<%@ include file="/WEB-INF/templates/default/share/taglib.jsp" %>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<html>
<head>
<title>网站用户列表</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<jsp:include page="/WEB-INF/templates/default/share/backstage_location_check.jsp"></jsp:include>
<link rel="stylesheet" href="css/vip.css" type="text/css">


</head>

<body bgcolor="#FFFFFF" text="#000000" marginwidth="0" marginheight="0">

<form action="control/center/user/manage.do" method="post">
  <table width="100%" border="0" cellspacing="1" cellpadding="2" align="center">
    <tr><td colspan="5"  bgcolor="6f8ac4" align="right">
   <div class="pagesplit">  <%
  
String pageSplits = ConstantString.generatePageSplit(
		((PageModel)request.getAttribute("pm")).getTotal(),
		"control/center/user/manage.do?");
out.println(pageSplits);
%>  </div>
   </td></tr>
    <tr>
      <td width="5%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">用户ID</font></div></td>
      <td width="10%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">用户名</font></div></td>
      <td width="15%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">邮箱</font></div></td>
	  <td width="6%" bgcolor="6f8ac4"><div align="center"><font color="#FFFFFF">状态</font></div></td>
	  <td nowrap bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">操作</font></div></td>
    </tr>
<!---------------------------LOOP START------------------------------>
<c:forEach items="${pm.datas}" var="user">
    <tr>
      <td bgcolor="f5f5f5"> <div align="center"><INPUT TYPE="checkbox" NAME="productIds" value="${user.id}">${user.id }</div></td>
	  <td bgcolor="f5f5f5"> <div align="center">${user.username }</div></td>
	  <td bgcolor="f5f5f5"> <div align="center"></div></td>	  
	  <td bgcolor="f5f5f5"> <div align="center"><c:if test="${user.visible}">正常</c:if><c:if test="${!user.visible}">停用</c:if></div></td>
	  <td bgcolor="f5f5f5"> <div align="center">
	  <a href="control/center/user/manage.do?method=getDetail&userId=${user.id }">详细信息</a>
	  <a href="control/center/user/manage.do?method=getOrders&userId=${user.id }">订单信息</a>
	  <a href="control/center/user/manage.do?method=deleteUser&userId=${user.id }">停用</a>
	  <a href="control/center/user/manage.do?method=editUser&userId=${user.id }">帐号信息编辑</a>
	  <a href="control/center/user/manage.do?method=findUserPassword&userId=${user.id }">发送找回密码邮件</a>
	  
	  </div></td>
	  <td bgcolor="f5f5f5"> <div align="center"></div></td>
</tr>
</c:forEach>
    <!----------------------LOOP END------------------------------->
    <tr> 
      <td bgcolor="f5f5f5" colspan="5" align="center"><table width="100%" border="0" cellspacing="1" cellpadding="3">
          <tr> 
            <td width="8%"><INPUT TYPE="checkbox" NAME="all" onclick="javascript:allselect(this, this.form.productids)">全选</td>
              <td width="85%">
              <input type="button" class="frm_btn" onClick="javascript:window.location.href='control/center/user/manage.do?method=addInput'" value="添加用户"> &nbsp;&nbsp;
			  <input name="query" type="button" class="frm_btn" id="query" onClick="javascript:window.location.href='control/center/user/manage.do?method=queryInput'" value=" 查 询 "> &nbsp;&nbsp;
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