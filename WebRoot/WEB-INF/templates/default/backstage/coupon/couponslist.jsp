<%@ page contentType="text/html;charset=utf-8" %>
<%@ include file="/WEB-INF/templates/default/share/taglib.jsp" %>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<html>
<head>
<title>优惠券列表</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<jsp:include page="/WEB-INF/templates/default/share/backstage_location_check.jsp"></jsp:include>
<link rel="stylesheet" href="css/vip.css" type="text/css">


</head>

<body bgcolor="#FFFFFF" text="#000000" marginwidth="0" marginheight="0">

<form action="control/center/coupon/manage.do" method="post">
<input type="hidden" value="deletes" name="method" />
  <table width="100%" border="0" cellspacing="1" cellpadding="2" align="center">
    <tr><td colspan="8"  bgcolor="6f8ac4" align="right"><font color="#FFFFFF">总记录数:${pm.total}条 |</font>
 
   </td></tr>
    <tr>
      <td width="45%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">ID</font></div></td>
      <td width="10%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">优惠券名称</font></div></td>
      <td width="5%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">抵价</font></div></td>
	  <td bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">最大使用次数</font></div></td>
	  <td bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">是否限制使用次数</font></div></td>
	  <td bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">已使用次数</font></div></td>
	    <td bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">是否可用</font></div></td>
	  <td bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">操作</font></div></td> 
    </tr>
<!---------------------------LOOP START------------------------------>
<c:forEach items="${pm.datas}" var="coupon">
<tr>
      <td bgcolor="f5f5f5"> <div align="center">
      <INPUT TYPE="checkbox" NAME="ids" value="${coupon.id}">${coupon.id }</div></td>
	  <td bgcolor="f5f5f5"> <div align="left">
	  <a href="control/center/coupon/manage.do?method=edit&id=${coupon.id}">${coupon.name }</a></div></td>
	  <td bgcolor="f5f5f5"> <div align="left">${coupon.discountPrice}</div></td>	  
	  <td bgcolor="f5f5f5">  ${coupon.maxTimes}</td>
	  <td bgcolor="f5f5f5">${coupon.limitTimes}  </td>
	  <td bgcolor="f5f5f5">${coupon.usedTimes}  </td>
	   <td bgcolor="f5f5f5">${coupon.avaliable}  </td>
	  <td bgcolor="f5f5f5">
	  <a href="control/center/coupon/manage.do?method=edit&id=${coupon.id}" >编辑</a>
	  <a href="control/center/coupon/manage.do?method=deleteCoupon&id=${coupon.id}" >删除</a>  </td>
</tr>
</c:forEach>
    <!----------------------LOOP END------------------------------->
    <tr> 
      <td bgcolor="f5f5f5" colspan="8" align="center">
      <input type="submit" value="删除" />
        <a href="control/center/coupon/manage.do?method=addCouponInput" >添加新优惠券</a>
      </td>
    </tr>
  </table>
 </form>
</body>
</html>