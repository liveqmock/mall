<%@ page contentType="text/html;charset=utf-8" %>
<%@ include file="/WEB-INF/templates/default/share/taglib.jsp" %>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<html>
<head>
<title>产品运费模板设置</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<jsp:include page="/WEB-INF/templates/default/share/backstage_location_check.jsp"></jsp:include>
<link rel="stylesheet" href="css/vip.css" type="text/css">
<link href="css/product/list.css" rel="stylesheet" type="text/css" />

</head>

<body bgcolor="#FFFFFF" text="#000000" marginwidth="0" marginheight="0">

   <table width="100%" border="0" cellspacing="1" cellpadding="2" align="center">
    <tr><td colspan="4"  bgcolor="6f8ac4" align="right"><font color="#FFFFFF">总记录数:${pm.total}条 |</font>
 
   </td></tr>
    <tr>
      <td width="5%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">产品ID</font></div></td>
      <td width="10%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">产品名称</font></div></td>
      <td width="50%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">使用的运费模板</font></div></td>
	  <td bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">操作</font></div></td> 
    </tr>
    <tr><td>
    ${product.id }
    </td><td>${product.name }</td>
    <td><c:if test="${!empty product.distributionTemplate}">${product.distributionTemplate.name }</c:if></td>
    <td><a href="control/center/distribution/manage.do?method=deleteProductDistribution&productId=${product.id }&distributionTemplateId=${product.distributionTemplate.id}">删除</a></td></tr>
    </table>
 <div id="Content">
  <div id="Main">
    <div id="FreTopBox"></div>
    <h1>我的运费:</h1>
		<div id="AddButton">
	  <input type="button" onclick="window.location='control/center/distribution/manage.do?method=addDistributionTemplateInput'" value="新增运费模板">
	</div>
	    <div id="FreContent">
      <ul id="FreList">
<c:forEach items="${pm.datas}" var="template">
		
              <li>
          <table cellspacing="0" cellpadding="0" border="0" width="690">
            <caption><span>${template.name }</span><em> <a href="control/center/distribution/manage.do?method=deleteDistributionTemplate&id=${template.id}">删除</a></em></caption>
			            <tbody><tr>
              <th width="15%" scope="col">运送方式</th>
              <th width="15%" scope="col">运费</th>
              <th width="25%" scope="col">运送到</th> 
              <th width="20%" scope="col">每多一件宝贝</th>
                <th width="25%" scope="col">操作</th>
            </tr>



<c:forEach items="${deliverTypes}" var="type">
<c:forEach items="${template.delievrModules}" var="deliverModule">
<c:if test="${type.name==deliverModule.deliverType.name}">

<tr>
<td align="center">${deliverModule.deliverType.name }</td>
<td align="center">${deliverModule.baseFee }</td>
<td>${deliverModule.zoneName }</td>
<td align="center">＋${deliverModule.oneMoreFee }元</td>
<td><a href="control/center/distribution/manage.do?method=editDeliverModule&deliverModuleId=${deliverModule.id}&distributionTemplateId=${template.id}">修改</a>
<a href="control/center/distribution/manage.do?method=deleteDeliverModule&deliverModuleId=${deliverModule.id}&distributionTemplateId=${template.id}">删除</a></td>
</tr>
</c:if>
</c:forEach>
</c:forEach>
<tr>
<td class="RemarkList" colspan="4"><div class="Remark"><span class="B G">运费说明：</span>${template.description}</div>
<a href="control/center/distribution/manage.do?method=addDeliverModuleInput&distributionTemplateId=${template.id}">添加新的地区运费</a>
<a href="control/center/distribution/manage.do?method=applyProductDistribution&productId=${product.id }&distributionTemplateId=${template.id}">
<div class="ApplyButton">
<span>应用该模板</span>
</div>
</a>
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