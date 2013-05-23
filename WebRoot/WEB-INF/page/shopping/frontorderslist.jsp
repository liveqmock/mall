<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/page/share/taglib.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML><HEAD>
<META http-equiv="pragma" content="no-cache">
<META http-equiv="Cache-Control" content="no-cache, must-revalidate">
<META http-equiv="Content-TYPE" content="text/html;charset=utf-8">
<%@ include file="/location_check.jsp" %>
<link href="css/global/address.css" rel="stylesheet" type="text/css">
<link href="css/global/bottom.css" rel="stylesheet" type="text/css">
<TITLE>Angel In The Box：结算中心：填写收货地址</TITLE>
<SCRIPT LANGUAGE="JavaScript" src="js/Country.js" type="text/javascript"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" src="js/provinceArea.js" type="text/javascript"></SCRIPT>

  </head>
  
  <body>
  <table width="98%" border="0" cellspacing="1" cellpadding="2" align="center">
    <tr ><td colspan="4" bgcolor="6f8ac4" align="right">
    总共：${pm.total }条
   <div class="pagesplit">  <%
  
String pageSplits = ConstantString.generatePageSplit(
		((PageModel)request.getAttribute("pm")).getTotal(),
		"ordermanage.do?");
out.println(pageSplits);
%>  </div>
   </td></tr>
    <tr>
      <td width="30%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">代号</font></div></td>
      <td width="5%" nowrap bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">修改</font></div></td>
      <td width="35%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">名称</font></div></td>
	  <td width="30%" nowrap bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">Logo</font></div></td>
    </tr>
<!---------------------------LOOP START------------------------------>
<c:forEach items="${pm.datas}" var="order">
    <tr>
      <td bgcolor="f5f5f5" height="50" width="30"> ${order.orderId}</td>
      <td bgcolor="f5f5f5" height="50" width="30"> <a href="">
	  <img src="images/edit.gif" width="15" border="0"></a></td>
      <td bgcolor="f5f5f5" height="50"> ${order.totalPrice}</td>
	  <td bgcolor="f5f5f5"> <div align="center">${order.customerName}</div></td>
	</tr>
</c:forEach>
    <!----------------------LOOP END------------------------------->
    <tr> 
      <td bgcolor="f5f5f5" colspan="4" align="center"><table width="100%" border="0" cellspacing="1" cellpadding="3">
          <tr> 
            <td width="5%"></td>
              <td width="85%">
              <input type="button" class="frm_btn" onClick="javascript:window.location.href='<html:rewrite action="control/product/brand/manage"/>?method=addInput'" value="添加品牌"> &nbsp;&nbsp;
			  <input name="query" type="button" class="frm_btn" id="query" onClick="javascript:window.location.href='<html:rewrite action="control/product/brand/manage"/>?method=queryInput'" value=" 查 询 "> &nbsp;&nbsp;
            </td>
          </tr>
        </table></td>
    </tr>
  </table>
  </body>
</html>
