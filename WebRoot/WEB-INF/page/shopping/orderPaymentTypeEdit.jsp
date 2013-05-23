<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/page/share/taglib.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML><HEAD><META http-equiv="Content-TYPE" content="text/html;charset=utf-8">
<%@ include file="/location_check.jsp" %><link href="css/product/list.css" rel="stylesheet" type="text/css" />
<SCRIPT src="country/1.js" type=text/javascript></SCRIPT>
<link href="css/global/address.css" rel="stylesheet" type="text/css">
<link href="css/global/bottom.css" rel="stylesheet" type="text/css">

<TITLE>支付方式更改</TITLE>
  </head>
  
  <body>
  <form action="control/shopping/ordermanage.do?method=updatePaymentType" method="post">
  <input type="hidden" name="orderId" value="${order.orderId}"/>
 
            <TABLE cellSpacing=0 cellPadding=4 width="100%" border=0>
              <TBODY>
              <TR bgColor=#eeeecc>
                <TD colSpan=2><STRONG>&nbsp;支付方式更改</STRONG></TD>
			 </TR>
			 <c:forEach items="${paymentMethods}" var="method">
			 <c:if test=""></c:if>
			 			 <TR>
                <TD class="big14" vAlign="middle" align="right" width="10%"> 
                <input type="radio" name="paymentMethod" value="${method.id }"<c:if test="${order.paymentAndDeliverMethod.paymentMethod==method.id}"> checked</c:if>/> </TD>
                <TD vAlign="middle" ><B>${method.name }</B></TD>
			  </TR>
			  </c:forEach>
 <TR> <TD colspan="2"><input type="submit" value="确定"/></TD> </TR>
		  </TBODY></TABLE>
  </form>

  </body>
</html>