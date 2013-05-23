<%@ page contentType="text/html;charset=utf-8" %>
<%@ include file="/WEB-INF/page/share/taglib.jsp" %>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<html>
<head>
<title>编辑全局区域运费</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<jsp:include page="/location_check.jsp"></jsp:include>
<link rel="stylesheet" href="css/vip.css" type="text/css">
<link href="css/product/list.css" rel="stylesheet" type="text/css" /></head>

<body bgcolor="#FFFFFF" text="#000000" marginwidth="0" marginheight="0">

<form action="control/center/globaldistribution/manage.do" method="post">
<input type="hidden" value="updateGlobalDeliverModule" name="method" />
<input type="hidden" value="${module.id }" name="globalDeliverModuleId"/>
<input type="hidden" value="${globalDeliverModuleActionForm.globalDistributionTemplateId }" name="globalDistributionTemplateId"/>
  <table width="100%" border="0" cellspacing="1" cellpadding="2" align="center">
    <tr bgcolor="6f8ac4">
              <th width="15%" scope="col">运送方式</th>
              <th width="15%" scope="col">运费</th>
              <th scope="col">运送到</th>  <th scope="col">免运费最小订单金额</th> 
            </tr>
    <tr><td bgcolor="6f8ac4" align="right">${module.deliverType.name }
</td><td bgcolor="6f8ac4"><input type="text" name="deliverFee" value="${module.deliverFee }"/>
   </td><td bgcolor="6f8ac4">${module.zoneName }</td><td bgcolor="6f8ac4">
   <input type="text" name="freeShippingOrderMinTotal" value="${module.freeShippingOrderMinTotal }"/>
   </td></tr>

   
   <tr><td colspan="4"><input type="submit" value="确定" ></td></tr><div id="FreBottomBox"></div>
   </table></form></body></html>