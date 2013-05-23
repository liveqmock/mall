<%@ page contentType="text/html;charset=utf-8" %>
<%@ include file="/WEB-INF/templates/default/share/taglib.jsp" %>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<html>
<head>
<title>新增全局区域运费</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<jsp:include page="/WEB-INF/templates/default/share/backstage_location_check.jsp"></jsp:include>
<link rel="stylesheet" href="css/vip.css" type="text/css">
<link href="css/product/list.css" rel="stylesheet" type="text/css" /></head>

<body bgcolor="#FFFFFF" text="#000000" marginwidth="0" marginheight="0">

${message}

<form action="control/center/globaldistribution/manage.do" method="post">
<input type="hidden" value="addGlobalDeliverModule" name="method" />
<input type="hidden" name="globalDistributionTemplateId" value="${globalDeliverModuleActionForm.globalDistributionTemplateId }" />
  <table width="100%" border="0" cellspacing="1" cellpadding="2" align="center">
    <tr bgcolor="6f8ac4">
    <th width="20%" scope="col">运送到</th>
              <th width="15%" scope="col">运送方式</th>
              <th width="15%" scope="col">运费</th> <th scope="col">免运费最小订单金额</th> 
               
            </tr>
    <tr>
    <td bgcolor="6f8ac4">
   <select name="zoneId">
   <option>请选择地区</option>
   <c:forEach items="${zones}" var="zone">
<option value="${zone.id}">${zone.name}</option>
</c:forEach>
</select>
   </td>
    <td bgcolor="6f8ac4" align="right">${module.deliverType.name}
    <select name="deliverTypeId">
   <option>请选择送货方式</option>
    <c:forEach items="${deliverTypes}" var="deliverType">
    <option value="${deliverType.id }">${deliverType.name }</option>
    </c:forEach>
    </select>
</td><td bgcolor="6f8ac4"><input type="text" name="deliverFee"/>
   </td>
   <td bgcolor="6f8ac4"><input type="text" name="freeShippingOrderMinTotal"/>
   </td>
   </tr>

   
   <tr><td colspan="3"><input type="submit" value="确定" ></td></tr>
   </table>
</form>
<div id="Content">
  <div id="Main">
    <div id="FreTopBox"></div>
    <h1></h1>
		<div id="AddButton">
	</div>
	    <div id="FreContent">
      <ul id="FreList">

		
              <li>
         <table cellspacing="0" cellpadding="0" border="0" width="690">
            <caption><span<c:if test="${template.enable}"> class="enable"</c:if>>${template.name }</span><em> <a href="">修改</a></em></caption>
			            <tbody><tr>
              <th width="15%" scope="col">运送方式</th>
              <th width="15%" scope="col">运费</th>
              <th width="50%" scope="col">运送到</th> 
              <th scope="col">免运费最小订单金额</th> 
              <th width="20%" scope="col">操作</th>
            </tr>
            <c:forEach items="${deliverTypes}" var="type">
<c:forEach items="${template.modules}" var="module">
<c:if test="${type.name==module.deliverType.name}">
<tr>
<td align="center">${module.deliverType.name }</td>
<td align="center">${module.deliverFee }</td>
<td>${module.zoneName }</td><td>${module.freeShippingOrderMinTotal}</td>
<td align="center"><a href="control/center/globaldistribution/manage.do?method=editGlobalDeliverModule&globalDeliverModuleId=${module.id }&globalDistributionTemplateId=${template.id }">修改</a>
<a href="control/center/globaldistribution/manage.do?method=deleteGlobalDeliverModule&globalDeliverModuleId=${module.id }&globalDistributionTemplateId=${template.id }">删除</a></td>
</tr>
</c:if>
</c:forEach></c:forEach>
<tr>
<td class="RemarkList" colspan="4"><div class="Remark"><span class="B G"></span></div>

</td>
</tr>
          </tbody></table>
        </li></ul></div><div id="FreBottomBox"></div></div></div>  
</body></html>