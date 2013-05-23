<%@ page contentType="text/html;charset=utf-8" %>
<%@ include file="/WEB-INF/page/share/taglib.jsp" %>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<html>
<head>
<title>角色列表</title>
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

<form action="control/center/role/manage.do" method="post">

  <table width="100%" border="0" cellspacing="1" cellpadding="2" align="center">
    <tr><td colspan="4"  bgcolor="6f8ac4" align="right">
   <font color="#FFFFFF">总记录数:条 |</font>
   </td></tr>
    <tr>
      <td width="5%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">角色ID</font></div></td>
      <td width="10%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">角色名称</font></div></td>
 	  <td nowrap bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">操作</font></div></td>
    </tr>
<!---------------------------LOOP START------------------------------>
<c:forEach items="${roles}" var="role">
    <tr>
      <td bgcolor="f5f5f5"> <div align="center"><INPUT TYPE="checkbox" NAME="productIds" value="${role.id}">${role.id }</div></td>
	  <td bgcolor="f5f5f5"> <div align="center">${role.roleName }</div></td>
	  <td bgcolor="f5f5f5"> 
	  <a href="control/center/role/manage.do?method=updateInput&id=${role.id}" title="编辑角色">编辑角色</a>
	  <a href="control/center/role/manage.do?method=delete&id=${role.id}" title="删除角色">删除角色</a>
	  <!-- <a href="control/center/acl/manage.do?method=roleAclList&roleId=${role.id}" title="权限设置">权限设置</a> -->
	  <a href="control/center/acl/manage.do?method=aclList&roleId=${role.id}" title="权限设置">权限设置</a>
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