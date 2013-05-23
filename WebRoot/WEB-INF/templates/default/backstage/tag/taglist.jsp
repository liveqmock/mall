<%@ page contentType="text/html;charset=utf-8" %>
<%@ include file="/WEB-INF/templates/default/share/taglib.jsp" %>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<html>
<head>
<title>Tag列表</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<jsp:include page="/WEB-INF/templates/default/share/backstage_location_check.jsp"></jsp:include>
<link rel="stylesheet" href="css/vip.css" type="text/css">


</head>

<body bgcolor="#FFFFFF" text="#000000" marginwidth="0" marginheight="0">

<form action="control/center/tag/manage.do" method="post">

  <table width="100%" border="0" cellspacing="1" cellpadding="2" align="center">
    <tr><td colspan="4"  bgcolor="6f8ac4" align="right">
   <font color="#FFFFFF">总记录数:条 |</font>
   </td></tr>
    <tr>
      <td width="5%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">Tag ID</font></div></td>
      <td width="10%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">Tag名称</font></div></td>
 	  <td nowrap bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">操作</font></div></td>
    </tr>
<!---------------------------LOOP START------------------------------>
<c:forEach items="${pm.datas}" var="tag">
    <tr>
      <td bgcolor="f5f5f5"> <div align="center"><INPUT TYPE="checkbox" NAME="productIds" value="${tag.id}">${tag.id }</div></td>
	  <td bgcolor="f5f5f5"> <div align="center">${tag.name }</div></td>
	  <td bgcolor="f5f5f5"> 
	  <a href="control/center/role/manage.do?method=updateInput&id=${tag.id}" title="编辑Tag">编辑Tag</a>
	  <a href="control/center/role/manage.do?method=delete&id=${tag.id}" title="删除Tag">删除Tag</a>
	  <a href="control/center/acl/manage.do?method=roleAclList&principalType=Role&principalSn=${Tag.id}" title="权限设置">权限设置</a>
	  </td>
</tr>
</c:forEach>
    <!----------------------LOOP END------------------------------->
    <tr> 
      <td bgcolor="f5f5f5" colspan="4" align="center"><table width="100%" border="0" cellspacing="1" cellpadding="3">
          <tr> 
            <td width="8%"><INPUT TYPE="checkbox" NAME="all" onclick="javascript:allselect(this, this.form.productids)">全选</td>
              <td width="85%">
              <input type="button" class="frm_btn" onClick="javascript:window.location.href='control/center/role/manage.do?method=addInput'" value="添加角色"> &nbsp;&nbsp;
			  <input name="query" type="button" class="frm_btn" id="query" onClick="javascript:window.location.href='control/center/role/manage.do?method=queryInput'" value=" 查 询 "> &nbsp;&nbsp;
			  

            </td>
          </tr>
        </table></td>
    </tr>
  </table>
 </form>
</body>
</html>