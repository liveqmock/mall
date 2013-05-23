<%@ page contentType="text/html;charset=utf-8" %>
<%@ include file="/WEB-INF/templates/default/share/taglib.jsp" %>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<html>
<head>
<title>优惠券使用记录</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<jsp:include page="/WEB-INF/templates/default/share/backstage_location_check.jsp"></jsp:include>
<link rel="stylesheet" href="css/vip.css" type="text/css">


</head>

<body bgcolor="#FFFFFF" text="#000000" marginwidth="0" marginheight="0">

<form action="control/center/coupon/manage.do" method="post">
<input type="hidden" value="deletes" name="method" />
  <table width="100%" border="0" cellspacing="1" cellpadding="2" align="center">
    <tr><td colspan="6"  bgcolor="6f8ac4" align="right"><font color="#FFFFFF">总记录数:${pm.total}条 |</font>
 
   </td></tr>
    <tr>
      <td width="5%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">ID</font></div></td>
      <td width="10%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">优惠券名称</font></div></td>
      <td width="50%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">用户email</font></div></td>
	  <td bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">用户名</font></div></td>
	  <td bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">使用日期</font></div></td>
	  <td bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">操作</font></div></td> 
    </tr>
<!---------------------------LOOP START------------------------------>
<c:forEach items="${pm.datas}" var="record">
<tr>
      <td bgcolor="f5f5f5"> <div align="center">
      <INPUT TYPE="checkbox" NAME="ids" value="${record.id}">${record.id }</div></td>
	  <td bgcolor="f5f5f5"> <div align="left">
	  <a href="control/center/coupon/manage.do?method=edit&id=${record.coupon.id}">${record.coupon.name }</a></div></td>
	  <td bgcolor="f5f5f5"> <div align="left">${record.userEmail}</div></td>	  
	  <td bgcolor="f5f5f5">  ${record.userName}</td>
	  <td bgcolor="f5f5f5">${record.limitTimes}  </td>
	  <td bgcolor="f5f5f5">
	  <a href="control/center/coupon/manage.do?method=deleteCouponUsedRecord&id=${record.id}" >删除</a>  </td>
</tr>
</c:forEach>
    <!----------------------LOOP END------------------------------->

  </table>
 </form>
</body>
</html>