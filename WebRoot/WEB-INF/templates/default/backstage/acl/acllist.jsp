<%@ page contentType="text/html;charset=utf-8" %>
<%@ include file="/WEB-INF/templates/default/share/taglib.jsp" %>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<html>
<head>
<title>权限分配列表</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<jsp:include page="/WEB-INF/templates/default/share/backstage_location_check.jsp"></jsp:include>
<link rel="stylesheet" href="css/vip.css" type="text/css">
<script language="JavaScript">
<!--
var aclRecords = new Array();
var moduleIds = document.getElementsByName("moduleId");
function ini(){

for(var i=0;i< moduleIds.length;i++){
	//alert(moduleIds[i].value);
	//alert(aclRecords[i]%2);
	document.getElementById("module_"+moduleIds[i].value+"_C").checked=aclRecords[i+1]%2;
	document.getElementById("module_"+moduleIds[i].value+"_R").checked=parseInt(aclRecords[i+1]/2)%2;
	document.getElementById("module_"+moduleIds[i].value+"_U").checked=parseInt(aclRecords[i+1]/4)%2;	
	document.getElementById("module_"+moduleIds[i].value+"_D").checked=parseInt(aclRecords[i+1]/8)%2;
	//alert(parseInt(aclRecords[i]/8)%2);
}	
}
function computeAclState(){
	var moduleIds = document.getElementsByName("moduleId");
	//document.getElementsByName("aclState")
	for(var i=0;i<moduleIds.length;i++){
		document.getElementsByName("aclState")[i].value=
			document.getElementById('module_'+moduleIds[i].value+'_C').checked+
			document.getElementById('module_'+moduleIds[i].value+'_R').checked*2+
			document.getElementById('module_'+moduleIds[i].value+'_U').checked*4+
			document.getElementById('module_'+moduleIds[i].value+'_D').checked*8;
		//alert(document.getElementsByName("aclState")[i].value);	
		}
	//alert(aclRecords);
return true;
}
function checkLine(id){
	//alert('module_'+id+'_C');
	document.getElementById('module_'+id+'_C').checked=
	document.getElementById('module_'+id+'_R').checked=
	document.getElementById('module_'+id+'_U').checked=
	document.getElementById('module_'+id+'_D').checked=true;	
}
function unCheckLine(id){
	//alert('module_'+id+'_C');
	document.getElementById('module_'+id+'_C').checked=
	document.getElementById('module_'+id+'_R').checked=
	document.getElementById('module_'+id+'_U').checked=
	document.getElementById('module_'+id+'_D').checked=false;	
}
function checkAll(){
	//var moduleIds = document.getElementsByName("moduleId");
	for(var i=0;i<moduleIds.length;i++){
		document.getElementById("module_"+moduleIds[i].value+"_C").checked=
		document.getElementById("module_"+moduleIds[i].value+"_R").checked=
		document.getElementById("module_"+moduleIds[i].value+"_U").checked=
		document.getElementById("module_"+moduleIds[i].value+"_D").checked=true;
	}	
}
function unCheckAll(){
	for(var i=0;i<moduleIds.length;i++){
		document.getElementById("module_"+moduleIds[i].value+"_C").checked=
		document.getElementById("module_"+moduleIds[i].value+"_R").checked=
		document.getElementById("module_"+moduleIds[i].value+"_U").checked=
		document.getElementById("module_"+moduleIds[i].value+"_D").checked=false;
	}	
}

function updateACL(id,permission){
	C=document.createElement("SCRIPT");
	C.src="/control/center/acl/manage.do?method=updateAcl&aclId="+id+"&permission="+permission;
	alert(C.src);
	C.charset="gb2312";
	//C.id="script"+id;
	document.body.appendChild(C);
	//window.open(C.src);
	//document.getElementById("updatebutton"+id).innerHTML+=
}

//-->
</script>

</head>

<body bgcolor="#FFFFFF" text="#000000" marginwidth="0" marginheight="0" onload="ini()">

<form action="control/center/acl/manage.do" method="post">
<input type="hidden" name="method" value="addOrUpdateACL"/>
<input type="hidden" name="principalType" value="${principalType }"/>
<input type="hidden" name="principalSn" value="${principalSn }"/>
  <table width="100%" border="0" cellspacing="1" cellpadding="2" align="center">
     <tr>
      <td width="5%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">模块ID</font></div></td>
      <td width="25%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">顶级模块</font></div></td>
      <td width="25%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">二级模块</font></div></td>
 	  <td nowrap bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">权限</font></div></td>
    </tr>
<!---------------------------LOOP START------------------------------>
<c:forEach items="${items}" var="acl">
    <tr>
     <td width="5%" bgcolor="f5f5f5"> <div align="center">${acl.id }</div></td>
      <td bgcolor="f5f5f5"> 
      <div style="font-size:12px;text-align:left;padding-left:80px">${acl.url }</div>
      </td>
	  <td bgcolor="f5f5f5"></td>
	  <td bgcolor="f5f5f5"> 
	
	 <input onclick="updateACL(${acl.id},this.checked)" type="checkbox" <c:if test="${acl.permission==true}">checked</c:if>/>${acl.functionName }
	<!--   <input type="hidden" name="aclState" />
	  <input type="checkbox" id="module_${module.id }_C" /> C
	  <input type="checkbox" id="module_${module.id }_R" /> R
	  <input type="checkbox" id="module_${module.id }_U" /> U
	  <input type="checkbox" id="module_${module.id }_D" /> D
	
	  <input type="button" value="Check All" 
	  onclick="checkLine(${acl.id })" 
	   />
	   <input type="button" value="UnCheck All" 
	   onclick="unCheckLine(${acl.id })" 
	   />	
	    -->
	  </td>
</tr>

</c:forEach>
    <!----------------------LOOP END------------------------------->
    <tr> 
      <td bgcolor="f5f5f5" colspan="4" align="center"><table width="100%" border="0" cellspacing="1" cellpadding="3">
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