<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/page/share/taglib.jsp" %>
<html>
<head>
<title>修改员工</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<link rel="stylesheet" href="css/vip.css" type="text/css">
<script language="JavaScript">
function Formfield(name, label){
	this.name=name;
	this.label=label;
}
function checkfm(form){
	var list  = new Array(new Formfield("realname", "员工姓名"),new Formfield("cardno", "身份证号码")
	,new Formfield("birthday", "身份证出生日期"),new Formfield("address", "身份证地址"));
	for(var i=0;i<list.length;i++){
		var objfield = eval("form."+ list[i].name);
		if(trim(objfield.value)==""){
			alert(list[i].label+ "不能为空");
			if(objfield.type!="hidden" && objfield.focus()) objfield.focus();
			return false;
		}
	}
	if(form.cardno.value.length!=15 && form.cardno.value.length!=18){
		alert("身份证号码的长度应该是15位或者18位");
		return false;
	}
	
	var picture = form.picture.value;
	if(trim(picture)!=""){
		var ext = logofile.substring(picture.length-3).toLowerCase();
		if (ext!="jpg" && ext!="gif" && ext!="bmp" && ext!="png"){
			alert("只允许上传gif、jpg、bmp、png！");
			return false; 
		}
	}
	return true;
}
</script>
</head>
<body bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<html:form action="control/employee/manage" method="post" enctype="multipart/form-data" onsubmit="return checkfm(this)">
<input type="hidden" name="method" value="edit">
<html:hidden property="username"/>
  <table width="90%" border="0" cellspacing="2" cellpadding="3" align="center">
    <tr bgcolor="6f8ac4"><td colspan="2"  > <font color="#FFFFFF">修改员工：</font></td>
    </tr>
    <tr bgcolor="f5f5f5"> 
      <td width="22%" > <div align="right">员工姓名：</div></td>
      <td width="78%"> <html:text property="realname" size="10" maxlength="10"/>
        <font color="#FF0000">*</font></td>
    </tr>
    <tr bgcolor="f5f5f5"> 
      <td width="22%" > <div align="right">性别：</div></td>
      <td width="78%"> <html:select property="gender">
      <html:option value="MAN">男</html:option><html:option value="WOMEN">女</html:option>
      </html:select></td>
    </tr> 
	<tr bgcolor="f5f5f5"> 
      <td width="22%" > <div align="right">员工照片：</div></td>
      <td width="78%"> <input type="file" name="picture" size="50">
      </td>
    </tr>
    <tr bgcolor="f5f5f5"> 
      <td width="22%" > <div align="right">身份证号码：</div></td>
      <td width="78%"> <html:text property="cardno" size="20" maxlength="18"/>
        <font color="#FF0000">*</font></td>
    </tr>
    <tr bgcolor="f5f5f5"> 
      <td width="22%" > <div align="right">身份证出生日期：</div></td>
      <td width="78%"> <html:text property="birthday" size="20" maxlength="18"/>
        <font color="#FF0000">*</font></td>
    </tr>
    <tr bgcolor="f5f5f5"> 
      <td width="22%" > <div align="right">身份证地址：</div></td>
      <td width="78%"> <html:text property="address" size="60" maxlength="100"/>
        <font color="#FF0000">*</font></td>
    </tr>  
    <tr bgcolor="f5f5f5"> 
      <td width="22%" > <div align="right">联系电话：</div></td>
      <td width="78%"> <html:text property="phone" size="20" maxlength="18"/></td>
    </tr> 
    <tr bgcolor="f5f5f5"> 
      <td width="22%" > <div align="right">电子邮件：</div></td>
      <td width="78%"> <html:text property="email" size="20" maxlength="18"/></td>
    </tr> 
    <tr bgcolor="f5f5f5"> 
      <td width="22%" > <div align="right">学历：</div></td>
      <td width="78%"> <html:text property="degree" size="10" maxlength="5"/></td>
    </tr>
    <tr bgcolor="f5f5f5"> 
      <td width="22%" > <div align="right">所属部门：</div></td>
      <td width="78%"> 
      <c:forEach items="${departments}" var="department">
		<input type="checkbox" name="departmentids" value="${department.id}" 
		<c:forEach items="${userdepartments}" var="userdepartment">
		<c:if test="${userdepartment==department}">checked</c:if>
		</c:forEach> />${department.name}　
	 </c:forEach></td>
    </tr>
    <tr bgcolor="f5f5f5"> 
      <td colspan="2"> <div align="center"> 
          <input type="submit" name="SYS_SET" value=" 确 定 " class="frm_btn">
        </div></td>
    </tr>
  </table>
</html:form>
<br>
</body>
</html>