<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/templates/default/share/taglib.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML><HEAD><META http-equiv="Content-TYPE" content="text/html;charset=utf-8">
<%@ include file="/WEB-INF/templates/default/share/backstage_location_check.jsp" %><link href="css/product/list.css" rel="stylesheet" type="text/css" />
<SCRIPT src="country/1.js" type=text/javascript></SCRIPT>
<link href="css/global/address.css" rel="stylesheet" type="text/css">
<link href="css/global/bottom.css" rel="stylesheet" type="text/css">

<TITLE>购物项数量更改</TITLE>
  </head>
  
  <body>
  <form action="control/shopping/ordermanage.do?method=updateOrderItemNumber" method="post">
  <input type="hidden" name="orderId" value="${orderId}"/>
 <input type="hidden" name="orderItemId" value="${item.id}"/>
            <TABLE cellSpacing=0 cellPadding=4 width="100%" border=0>
              <TBODY>
              <TR bgColor=#eeeecc>
                <TD colSpan=2><STRONG>&nbsp;购物项数量更改</STRONG></TD>
			 </TR>
	              <TR bgColor=#eeeecc>
                <td>名称</td><td><STRONG>${item.productName }</STRONG></TD>
			 </TR>
			    <TR bgColor=#eeeecc>
                <td>数量</td><td><input type="text" name="amount" value="${item.amount }"/></TD>
			 </TR>
 <TR> <TD colspan="2"><input type="submit" value="确定"/></TD> </TR>
		  </TBODY></TABLE>
  </form>

  </body>
</html>