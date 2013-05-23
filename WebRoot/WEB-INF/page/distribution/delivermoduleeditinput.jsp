<%@ page contentType="text/html;charset=utf-8" %>
<%@ include file="/WEB-INF/page/share/taglib.jsp" %>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<html>
<head>
<title>编辑该区域的运费</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<jsp:include page="/location_check.jsp"></jsp:include>
<link rel="stylesheet" href="css/vip.css" type="text/css">
<link href="css/product/list.css" rel="stylesheet" type="text/css" /></head>

<body bgcolor="#FFFFFF" text="#000000" marginwidth="0" marginheight="0">

<form action="control/center/distribution/manage.do" method="post">
<input type="hidden" value="updateDeliverModule" name="method" />
<input type="hidden" value="${module.id }" name="deliverModuleId"/>
  <table width="100%" border="0" cellspacing="1" cellpadding="2" align="center">
    <tr bgcolor="6f8ac4">
              <th width="15%" scope="col">运送方式</th>
              <th width="15%" scope="col">运费</th>
              <th width="50%" scope="col">运送到</th> 
              <th width="20%" scope="col">每多一件宝贝</th>
            </tr>
    <tr><td bgcolor="6f8ac4" align="right">${module.deliverType.name }
</td><td bgcolor="6f8ac4"><input type="text" name="deliverModuleBaseFee" value="${module.baseFee }"/>
   </td><td bgcolor="6f8ac4">${module.zoneName }</td><td bgcolor="6f8ac4">
   <input type="text" name="deliverModuleOneMoreFee" value="${module.oneMoreFee }"/></td></tr>

   
   <tr><td colspan="4"><input type="submit" value="确定" ></td></tr>
   </table>
</form></body></html>