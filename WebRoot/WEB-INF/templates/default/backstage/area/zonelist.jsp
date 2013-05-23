<%@ page contentType="text/html;charset=utf-8" %>
<%@ include file="/WEB-INF/templates/default/share/taglib.jsp" %>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<html>
<head>
<title>国家列表</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8"><jsp:include page="/WEB-INF/templates/default/share/backstage_location_check.jsp"></jsp:include>
<link rel="stylesheet" href="css/vip.css" type="text/css">


<script>
function actionEvent(methodname,methods){
	var form = document.forms[0];
	//if(validateIsSelect(form.all, form.productIds)){
		form.action='/control/center/country/manage.do';
		form.method.value=methodname;
		//form.yesOrNo.value=methods;
		//alert(form.yesOrNo.value);
		form.submit();
	//}else{
	//	alert("请选择要操作的记录");
	//}
}
</script>
</head>

<body bgcolor="#FFFFFF" text="#000000" marginwidth="0" marginheight="0">

<form action="control/center/zone/manage.do" method="post">
<input type="hidden" name="method"> 
  <table width="100%" border="0" cellspacing="1" cellpadding="2" align="center">
    <tr><td colspan="6"  bgcolor="6f8ac4" align="right">
   <font color="#FFFFFF">总记录数:${fn:length(pm)}条 |</font>
   </td></tr>
    <tr>
      <td width="5%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">地区 ID</font></div></td>
      <td width="30%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">地区名称</font></div></td>
	<td width="10%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">所属国家</font></div></td>      
    <!--   <td width="10%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">英文名称</font></div></td>
      <td width="10%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">简称2位</font></div></td>
      <td width="10%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">简称</font></div></td> -->
 	  <td nowrap bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">操作</font></div></td>
    </tr>
<!---------------------------LOOP START------------------------------>
  <%/*
 <c:forEach items="${pm.datas}" var="zone0" varStatus="z0">
  
 <tr>
  <td bgcolor="f5f5f5" colspan="6">

   {  "t":"${zone0.name }",<br/>
   "id":"${zone0.id}",
   "s":[
    <c:if test="${!empty zone0.children}">
    <c:forEach items="${zone0.children}" var="zone1" varStatus="z1">
   
     	{"t":"${zone1.name }",<br/>
     	"id":"${zone1.id}",
     	 "s":[
     	  <c:if test="${!empty zone1.children}">
      	  <c:forEach items="${zone1.children}" var="zone2" varStatus="z2">
    		{"t":" ${zone2.name }","id":"${zone2.id}"} <c:if test="${z2.index <(fn:length(zone1.children)-1 )}">,</c:if>
          </c:forEach>
          </c:if><!-- end of 区县 -->
          ]} <c:if test="${z1.index <(fn:length(zone0.children)-1 )}">,</c:if>
    </c:forEach>
    </c:if><!-- end of 城市 -->
    ]}
    <c:if test="${z0.index <(fn:length(pm.datas)-1 )}">,
<!-- ${z0.index}${fn:length(zone0.children) }${fn:length(pm.datas)} -->
    </c:if>

  </td>
 </tr>
</c:forEach>
*/
%>
<c:forEach items="${pm}" var="zone">

    <tr>
      <td bgcolor="f5f5f5"> <div><INPUT TYPE="checkbox" NAME="ids" value="${zone.id}">${zone.id }</div></td>
	  <td bgcolor="f5f5f5"> <div class="areaname">${zone.name }(${fn:length(zone.children) }个子地区)</div></td>
	   <td bgcolor="f5f5f5"> <div align="center">${zone.country.cnName }</div></td>
	  <!--   <td bgcolor="f5f5f5"> <div align="center">${country.enName }</div></td>
	  <td bgcolor="f5f5f5"> <div align="center">${country.countries_iso_code_2 }</div></td>
	  <td bgcolor="f5f5f5"> <div align="center">${country.countries_iso_code_3 }</div></td> -->
	  <td bgcolor="f5f5f5"> 
	  <a href="control/center/zone/manage.do?method=editinput&id=${zone.id}" title="编辑">编辑</a>
	  <a href="control/center/zone/manage.do?method=delete&id=${zone.id}" title="删除">删除</a>
	   <a href="control/center/zone/manage.do?pId=${zone.id}&countryId=${zone.country.id}&returnto=<c:if test="${!empty zone.parent}">${zone.parent.id }</c:if>" title="下级地区">下级地区</a>
	  </td>
</tr>
</c:forEach>
    <!----------------------LOOP END------------------------------->
    <tr> 
      <td bgcolor="f5f5f5" colspan="6" align="center"><table width="100%" border="0" cellspacing="1" cellpadding="3">
          <tr> 
            <td width="8%"><INPUT TYPE="checkbox" NAME="all" onclick="javascript:allselect(this, this.form.productids)">全选</td>
              <td width="85%">
              <input type="button" class="frm_btn" onClick="javascript:window.location.href='/control/center/zone/manage.do?method=addinput&countryId=${countryId}&pId=${parentId }'" value="添加地区信息"> &nbsp;&nbsp;
			  <input name="query" type="button" class="frm_btn" id="query" onClick="javascript:window.location.href='/control/center/role/manage.do?method=queryInput'" value="查 询"> &nbsp;&nbsp;
		  <input name="query" type="button" class="frm_btn" id="query" onClick="javascript:actionEvent('deleteZones','')" value="删 除"> &nbsp;&nbsp;
		  <input  type="button" class="frm_btn" onClick="javascript:window.location.href='/control/center/zone/manage.do?pId=${returnto}'" value="返 回"> &nbsp;&nbsp;
				  

            </td>
          </tr>
        </table></td>
    </tr>
  </table>
 </form>
</body>
</html>