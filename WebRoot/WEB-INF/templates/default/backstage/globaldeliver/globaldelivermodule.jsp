<%@ page contentType="text/html;charset=utf-8" %>
<%@ include file="/WEB-INF/templates/default/share/taglib.jsp" %>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<html>
<head>
<title>全局配送运费列表</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<jsp:include page="/WEB-INF/templates/default/share/backstage_location_check.jsp"></jsp:include>
<link rel="stylesheet" href="css/vip.css" type="text/css">
<link href="css/product/list.css" rel="stylesheet" type="text/css" />

</head>

<body bgcolor="#FFFFFF" text="#000000" marginwidth="0" marginheight="0">
<%/**
<form action="control/center/distribution/manage.do" method="post">
<input type="hidden" value="deletes" name="method" />
  <table width="100%" border="0" cellspacing="1" cellpadding="2" align="center">
    <tr><td colspan="6"  bgcolor="6f8ac4" align="right"><font color="#FFFFFF">总记录数:${fn:length(templates)}条 |</font>
 
   </td></tr>
    <tr>
      <td width="5%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">运费ID</font></div></td>
      <td width="30%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">运费类型名称</font></div></td>
      <td width="5%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">是否启用</font></div></td>
      <td width="5%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">是否忽略产品本身的运费</font></div></td>
      <td width="40%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">运费</font></div></td>
	  <td bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">操作</font></div></td> 
    </tr>
<!---------------------------LOOP START------------------------------>
<c:forEach items="${templates}" var="template">
<tr>
      <td bgcolor="f5f5f5"> <div align="center">
      <INPUT TYPE="checkbox" NAME="ids" value="${template.id}">${template.id }</div></td>
	  <td bgcolor="f5f5f5"> <div align="left">
	  <a href="/control/center/distribution/manage.do?method=edit&id=${template.id}">${template.name }</a></div></td>
	   <td bgcolor="f5f5f5"> <div align="left">
	   ${template.enable}
	   </div></td>
	   <td bgcolor="f5f5f5"> <div align="left">
	   ${template.ignoreProductDeliverFee}
	   </div></td>	   
	  <td bgcolor="f5f5f5"> <div align="left">
	  <c:if test="${template.permanent==true}">各地区统一运费</c:if>
	  <c:if test="${template.permanent==false}">
	  <c:forEach items="${template.modules}" var="module">
	  	${module.zoneName }
	  	${module.deliverFee }
	  		${module.deliverType.name }
	  </c:forEach></c:if>
	 </div>
	  
	  
	  </td>	  
	  
	  <td bgcolor="f5f5f5">
	  <a href="control/center/template/manage.do?method=edit&id=${template.id}" >编辑</a>  </td>
</tr>
</c:forEach>
    <!----------------------LOOP END------------------------------->
    <tr> 
      <td bgcolor="f5f5f5" colspan="7" align="center">
      <input type="submit" value="删除" />
        <a href="control/center/distribution/manage.do?method=addDeliverTypeInput" >添加新送货方式</a>
      </td>
    </tr>
  </table>
 </form>
 **/
 %>
 <div id="Content">
  <div id="Main">
    <div id="FreTopBox"></div>
    <h1>全局运费类型</h1>
		<div id="AddButton">
	  
	</div>
	    <div id="FreContent">
      <ul id="FreList">
<c:forEach items="${templates}" var="template">
		
              <li>
          <table cellspacing="0" cellpadding="0" border="0" width="690">
            <caption><span<c:if test="${template.enable}"> class="enable"</c:if><c:if test="${template.enable==false}"> class="disable"</c:if>>${template.name }</span><em style="display:none"> <a href="">修改</a></em></caption>
			            <tbody><tr>
              <th width="15%" scope="col">运送方式</th>
              <th width="15%" scope="col">运费</th>
              <th width="30%" scope="col">运送到</th> 
              <th scope="col">免运费最小订单金额</th> 
              <th width="20%" scope="col">操作</th>
            </tr>
<c:forEach items="${deliverTypes}" var="type">
<c:forEach items="${template.modules}" var="module">
<c:if test="${type.name==module.deliverType.name}">
<tr>
<td align="center">${module.deliverType.name }</td>
<td align="center">${module.deliverFee }</td>
<td>${module.zoneName }</td>
<td>${module.freeShippingOrderMinTotal}</td>
<td align="center">
<a href="control/center/globaldistribution/manage.do?method=editGlobalDeliverModule&globalDeliverModuleId=${module.id }&globalDistributionTemplateId=${template.id }">修改</a>
<a href="control/center/globaldistribution/manage.do?method=deleteGlobalDeliverModule&globalDeliverModuleId=${module.id }&globalDistributionTemplateId=${template.id }">删除</a></td>
</tr>
</c:if>
</c:forEach></c:forEach>
<tr>
<td class="RemarkList" colspan="4"><div class="Remark">
<c:if test="${template.permanent==false}">
<a href="control/center/globaldistribution/manage.do?method=addGlobalDeliverModuleInput&globalDistributionTemplateId=${template.id }">添加新的区域运费</a>
</c:if></div>

<div>
<c:if test="${template.enable==false}">
<a class="ApplyButton" href="control/center/globaldistribution/manage.do?method=applyGlobalDeliverTemplate&globalDistributionTemplateId=${template.id }"><span>应用该模板</span></a>
</c:if>
</div>
</td>
</tr>
          </tbody></table>
        </li>
</c:forEach>
            </ul>
    </div>
    <div id="FreBottomBox"></div>
  </div>
 </div>           
</body>
</html>