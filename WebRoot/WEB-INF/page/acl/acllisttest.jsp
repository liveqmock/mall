<%@ page contentType="text/html;charset=utf-8" %>
<%@ include file="/WEB-INF/page/share/taglib.jsp" %>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<html>
<head>
<title>权限分配列表</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<jsp:include page="/location_check.jsp"></jsp:include>
<link rel="stylesheet" href="css/vip.css" type="text/css">
<link href="css/product/list.css" rel="stylesheet" type="text/css" />

  <script src="/js/base.js" type="text/javascript"></script>

<script language="JavaScript">
<!--
var acls=[
          <c:forEach items="${acls}" var="acl">
          '${acl.url}->${acl.functionName}',${acl.permission},</c:forEach>

          ''];
function ini(){
	if(acls.length==1)	return;
	var checksomethings = document.getElementsByName("checksomething");
	var checks = document.getElementsByName("check");
	for(var i=0;i<acls.length-1;i=i+2){

		for(var j=0;j<checksomethings.length;j++){
		if(checksomethings[j].value==acls[i]){
			checks[j].checked=acls[i+1];
			//alert(acls[i+1]+" "+checks[j].checked);
		}
		}
	}
	}
function updateACL(moduleId,moduleFunctionId,permission){
	C=document.createElement("SCRIPT");
	C.src="/control/center/acl/manage.do?method=setACL&roleId=${aclForm.roleId}&moduleId="+moduleId+"&moduleFunctionId="+moduleFunctionId+"&permission="+permission;
	//alert(C.src);

	C.charset="gb2312";
	//C.id="script"+id;
	document.body.appendChild(C);
	var maskInnerHTML="成功";
	addMask(maskInnerHTML);
	//window.open(C.src);
	//document.getElementById("updatebutton"+id).innerHTML+=
}

//-->
</script>

</head>

<body bgcolor="#FFFFFF" text="#000000" marginwidth="0" marginheight="0" onload="ini()">

<form action="control/center/acl/manage.do" method="post">
<input type="hidden" name="method" value="addOrUpdateACL"/>
  <table width="100%" border="0" cellspacing="1" cellpadding="2" align="center">
     <tr>
      <td width="5%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">模块ID</font></div></td>
      <td width="25%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">模块</font></div></td>
 	  <td nowrap bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">权限</font></div></td>
    </tr>
<!---------------------------LOOP START------------------------------>
<c:forEach items="${modules}" var="module">
    <tr>
     <td width="5%" bgcolor="f5f5f5"> <div align="center"><input type="hidden" value="${module.id }" name="moduleId"/>${module.id }</div></td>
      <td bgcolor="f5f5f5"> <div style="text-align:left;padding-left:20px">${module.name }</div>
      <div style="font-size:12px;text-align:left;padding-left:80px">${module.url }</div>
      </td>
	 
	  <td bgcolor="f5f5f5"> 
	 
	<c:forEach items="${module.functions}" var="function" varStatus="status" begin="0" step="1">
	<input type="hidden"
	name="checksomething"
	value="${module.url }->${function.functionName }"
	/><input name="check" type="checkbox" onclick="updateACL(${module.id},${function.id },this.checked)">${function.functionName}(${function.functionDescription})
	&nbsp;&nbsp;&nbsp;&nbsp;
	<c:if test="${status.count%2==0 }"><br></c:if>
	</c:forEach>
	<c:if test="${empty module.functions}">
			  	<c:if test="${empty module.url}">
		  	<input type="hidden"
	name="checksomething"
	value="${module.url}->"
	/><input name="check" type="checkbox" checked>
		  	</c:if><c:if test="${!empty module.url}">
	<input type="hidden"
	name="checksomething"
	value="${module.url}->${function.functionName}"
	/><input name="check" type="checkbox" onclick="updateACL(${module.id},'',this.checked)">unspecified方法
	</c:if>
	</c:if>
	  </td>
</tr>
	<c:forEach items="${module.children}" var="child" varStatus="status" begin="0" step="1">>
    <tr>
    <td width="5%" bgcolor="f5f5f5"> <div align="center"><input type="hidden" value="${child.id }" name="moduleId"/>${child.id }</div></td>
      
	  <td bgcolor="f5f5f5"><div style="text-align:left;padding-left:20px">${child.name }</div>
	   <div style="font-size:12px;text-align:left;padding-left:80px">${child.url }</div>
	  </td>
	  <td bgcolor="f5f5f5"> 
	 
		<c:forEach items="${child.functions}" var="function">
		<input type="hidden"
	name="checksomething"
	value="${child.url }->${function.functionName }"
	/>
	<input name="check" type="checkbox" onclick="updateACL(${child.id},${function.id },this.checked)">${function.functionName}(${function.functionDescription})
	
	</c:forEach>
		  	<c:if test="${empty child.functions}">
		  	<c:if test="${empty child.url}">
		  		<input type="hidden"
	name="checksomething"
	value="${child.url}->"
	/><input name="check" type="checkbox" checked>
		  	</c:if>
	 	<c:if test="${!empty child.url}"><input type="hidden"
	name="checksomething"
	value="${child.url}->"
	/><input name="check" type="checkbox" onclick="updateACL(${child.id},'',this.checked)">unspecified方法
		</c:if>
	</c:if>
	  </td>
</tr>
	</c:forEach>
</c:forEach>
    <!----------------------LOOP END------------------------------->
    <tr> 
      <td bgcolor="f5f5f5" colspan="3" align="center"><table width="100%" border="0" cellspacing="1" cellpadding="3">
          <tr> 
            <td width="8%"></td>
              <td width="85%">
               <input type="button" class="frm_btn" value="Check All" onclick="checkAll();"> &nbsp;&nbsp;
                <input type="button" class="frm_btn" value="UnCheck All" onclick="unCheckAll();"> &nbsp;&nbsp;
              <input type="submit" class="frm_btn" value="确 定" onclick="return computeAclState();"> &nbsp;&nbsp;
			  <input name="query" type="button" class="frm_btn" id="query" onClick="javascript:window.location.href='control/center/role/manage.do?method=queryInput'" value="查 询 "> &nbsp;&nbsp;
<input name="query" type="button" class="frm_btn" id="query" onClick="javascript:window.location.href='control/center/role/manage.do'" value="返回角色列表"> &nbsp;&nbsp;
            </td>
          </tr>
        </table></td>
    </tr>
  </table>
 </form>
</body>
</html>