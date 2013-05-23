<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/templates/default/share/taglib.jsp" %><%@ page import="java.util.*"
%><%@ page import="java.text.*"
%><%@ page import="java.lang.StringBuffer" 
%><%@ page import="com.hnfealean.sport.model.product.Product"
%><%@ page import="com.hnfealean.sport.model.product.AttributeOption" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML><HEAD><META http-equiv="Content-TYPE" content="text/html;charset=utf-8">
<%@ include file="/WEB-INF/templates/default/share/backstage_location_check.jsp" %><link href="css/product/list.css" rel="stylesheet" type="text/css" />
<SCRIPT src="country/1.js" type=text/javascript></SCRIPT>
<link href="css/global/address.css" rel="stylesheet" type="text/css">
<link href="css/global/bottom.css" rel="stylesheet" type="text/css">

<TITLE>购物项属性更改</TITLE>
  </head>
  
  <body>
  <form action="control/shopping/ordermanage.do?method=updateAttribute" method="post">
  <input type="hidden" name="orderId" value="${orderId}"/>
 <input type="hidden" name="attributeOptionId" value="${attribute.id}"/>
            <TABLE cellSpacing=0 cellPadding=4 width="100%" border=0>
              <TBODY>
              <TR bgColor=#eeeecc>
                <TD colSpan=2><STRONG>&nbsp;购物项属性更改</STRONG></TD>
			 </TR>
			 <%
List attributeList = new ArrayList();

Product  p = (Product)request.getAttribute("product");
Set<AttributeOption> options =p.getOptions();

for(AttributeOption option:options)
if(!attributeList.contains(option.getAttribute().getName())){
	attributeList.add(option.getAttribute().getName());
}
request.setAttribute("attributeList",attributeList);
%>
<c:forEach items="${attributeList}" var="att">
<c:if test="${att eq attribute.attributeName}">
 <TR bgColor=#eeeecc>
                <td>
	${att }</td><td><select name="productOptionId"><option>请选择</option>
	<c:forEach items="${product.options}" var="option">
	
	<c:if test="${option.attribute.name == att}">
	<option value="${option.id}"<c:if test="${option.value eq attribute.attributeValue}"> selected</c:if> >${option.value}</option>
	</c:if>
	
	</c:forEach>
	</select></TD>
			 </TR>
</c:if>			 
</c:forEach>	
	             
 <TR> <TD colspan="2"><input type="submit" value="确定"/></TD> </TR>
		  </TBODY></TABLE>
  </form>

  </body>
</html>