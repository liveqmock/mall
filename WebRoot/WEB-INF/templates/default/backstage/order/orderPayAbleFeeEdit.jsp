<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/templates/default/share/taglib.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML><HEAD><META http-equiv="Content-TYPE" content="text/html;charset=utf-8">
<%@ include file="/WEB-INF/templates/default/share/backstage_location_check.jsp" %><link href="css/product/list.css" rel="stylesheet" type="text/css" />
<SCRIPT src="country/1.js" type=text/javascript></SCRIPT>
<link href="css/global/address.css" rel="stylesheet" type="text/css">
<link href="css/global/bottom.css" rel="stylesheet" type="text/css">

<TITLE>订单应付金额更改</TITLE>
  </head>
  
  <body>
  <form action="control/shopping/ordermanage.do?method=updateOrderPayAbleFee" method="post">
  <input type="hidden" name="orderId" value="${order.orderId}"/>
            <TABLE cellSpacing=0 cellPadding=4 width="100%" border=0>
              <TBODY>
              <TR bgColor=#eeeecc>
                <TD colSpan=2><STRONG>&nbsp;订单应付金额更改</STRONG></TD>
			 </TR>
	              <TR bgColor=#eeeecc>
                <td>订单应付金额</td><td><input type="text" name="payAbleFee" value="${order.payAbleFee }"/></TD>
			 </TR>
 <TR> <TD colspan="2"><input type="submit" value="确定"/></TD> </TR>
		  </TBODY></TABLE>
  </form>

  </body>
</html>