<%@ page contentType="text/html;charset=utf-8" %>
<%@ include file="/WEB-INF/templates/default/share/taglib.jsp" %>
<html>
<head>
<title>添加菜单项</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<jsp:include page="/WEB-INF/templates/default/share/backstage_location_check.jsp"></jsp:include>
<link rel="stylesheet" href="css/vip.css" type="text/css">

</head>
<body bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<html:form action="control/center/adminmenu/manage" method="post" enctype="multipart/form-data" onsubmit="return checkfm(this)">
<input type="hidden" name="method" value="updateItem"/>
<input type="hidden" name="menuItemId" value="${item.id }"/>
<input type="hidden" name="id" value="${menuForm.id }"/>
<br>
  <table width="90%" border="0" cellspacing="2" cellpadding="3" align="center">
    <tr bgcolor="6f8ac4"><td> <font color="#FFFFFF"></font></td>
    </tr>
    <tr bgcolor="f5f5f5"> 
      <td> <div align="left">菜单项名称 ：<font color="#FF0000">*</font><input type="text" name="name" value="${item.name }"></div></td>
      
       
    </tr>
     <tr bgcolor="f5f5f5"> 
      <td> <div align="left">菜单项链接地址 ：<font color="#FF0000">*</font><input type="text" name="link" value="${item.link }"></div></td>
      
       
    </tr>
	<tr bgcolor="f5f5f5"> 
      <td> <div align="left">排序号<input type="text" name="orderNo" value="${item.orderNo }"/></div></td>
     </tr>
    <tr bgcolor="f5f5f5"> 
      <td> <div align="center"> 
          <input type="submit" name="SYS_SET" value="确  定" class="frm_btn">
         <input type="button" value="后  退 " class="frm_btn" onclick="javascript:history.back(-1)">
        </div></td>
    </tr>
  </table>
</html:form>
 
<br>
</body>
</html>