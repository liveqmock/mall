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
<TITLE>订单详细信息</TITLE>
<SCRIPT LANGUAGE="JavaScript" src="js/Country.js" type="text/javascript"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" src="js/provinceArea.js" type="text/javascript"></SCRIPT>

  </head>
  
  <body>
  
  
  <table width="90%" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#333333">
  <tr>
    <td><table width="100%" height="25" border="0" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
      <tr>
        <td width="59%"><strong>订单号:</strong>${order.orderId } <font color="red">(${order.orderState })</font></td>
        <td width="41%" align="right"><strong>订购时间:</strong>${order.createDate }</td>
      </tr>
    </table>
      <table width="100%" border="0" align="center" cellpadding="3" cellspacing="2">
              <tr>
          <td bgcolor="#FFFFFF"><strong>网站用户信息 </strong></td>
          <td align="center" bgcolor="#FFFFFF">用户名</td>
          <td bgcolor="#FFFFFF">${order.user.username }</td>
          <td align="center" bgcolor="#FFFFFF">电子邮件地址</td>
          <td bgcolor="#FFFFFF">${order.user.email}</td>
          <td colspan="3" bgcolor="#FFFFFF">&nbsp;</td>
        </tr>
       <%-- <tr>
          <td colspan="4" bgcolor="#FFFFFF"><strong>订购者信息 </strong> <!-- <a href="control/shopping/ordermanage.do?method=modifyContactInfoUI&contactid=${order.user.contactInfo.id }">修改</a> --></td>
          <td align="center" bgcolor="#FFFFFF">支付方式</td>
          <td colspan="2" bgcolor="#FFFFFF"><!-- ${order.paymentAndDeliverMethod.id} -->${order.paymentAndDeliverMethod.paymentTypeName}
           <a href="control/shopping/ordermanage.do?method=editPaymentType&orderId=${order.orderId }">修改</a></td>
        </tr>
        <tr>
          <td width="13%" align="center" bgcolor="#FFFFFF">姓名</td>
          <td width="24%" bgcolor="#FFFFFF">${order.user.username }
    ${contactInfo.name}      
          （${contactInfo.gender }）</td>
          <td width="12%" align="center" bgcolor="#FFFFFF">联系电话</td>
          <td width="18%" bgcolor="#FFFFFF">${contactInfo.phone }</td>
          <td width="12%" align="center" bgcolor="#FFFFFF">联系手机</td>
          <td colspan="2" bgcolor="#FFFFFF">${contactInfo.mobile }</td>
        </tr>
        <tr>
          <td align="center" bgcolor="#FFFFFF">地址</td>
          <td colspan="3" bgcolor="#FFFFFF">${contactInfo.country},	${contactInfo.state}, ${contactInfo.city}, ${contactInfo.county},${contactInfo.street_address }</td>
          <td align="center" bgcolor="#FFFFFF">邮编</td>
          <td colspan="2" bgcolor="#FFFFFF">${contactInfo.postCode }</td>
        </tr>
        <tr>
          <td align="center" bgcolor="#FFFFFF">其他要求</td>
          <td colspan="6" bgcolor="#FFFFFF">${order.message}</td>
        </tr> --%> 
        <!-- <tr>
          <td colspan="4" bgcolor="#FFFFFF"><strong>收货人信息</strong> <a href="control/shopping/ordermanage.do?method=editDeliverInfo&orderId=${order.orderId }">修改</a></td>
          <td align="center" bgcolor="#FFFFFF">送货方式</td>
          <td colspan="2" bgcolor="#FFFFFF">  ${order.paymentAndDeliverMethod.deliverTypeName } <a href="/control/shopping/ordermanage.do?method=editDeliverType&orderId=${order.orderId }">修改</a></td>
        </tr> -->
        <tr>
          <td colspan="7" bgcolor="#FFFFFF"><strong>配送信息</strong> <a href="control/shopping/ordermanage.do?method=editDeliverInfo&orderId=${order.orderId }">修改</a></td>
           </tr>

        <tr>
          <td align="center" bgcolor="#FFFFFF">姓名</td>
          <td bgcolor="#FFFFFF">${order.deliverInfo.name }</td>
          <td align="center" bgcolor="#FFFFFF">联系电话</td>
          <td bgcolor="#FFFFFF">${order.deliverInfo.phone }</td>
          <td align="center" bgcolor="#FFFFFF">联系手机</td>
          <td colspan="2" bgcolor="#FFFFFF">${order.deliverInfo.mobile }</td>
        </tr>
        <tr>
          <td align="center" bgcolor="#FFFFFF">地址</td>
          <td colspan="3" bgcolor="#FFFFFF">${order.deliverInfo.country},${order.deliverInfo.state},${order.deliverInfo.city},${order.deliverInfo.county},${order.deliverInfo.street_address}</td>
          <td align="center" bgcolor="#FFFFFF">邮编</td>
          <td colspan="2" bgcolor="#FFFFFF">${order.deliverInfo.postCode }</td>
        </tr>
        <tr>
          <td align="center" bgcolor="#FFFFFF">时间要求</td>
          <td colspan="6" bgcolor="#FFFFFF"></td>
        </tr>
                <tr><td align="center" bgcolor="#FFFFFF"><b>支付方式</b></td>
          <td colspan="3" bgcolor="#FFFFFF"><!-- ${order.paymentAndDeliverMethod.id} -->${order.paymentAndDeliverMethod.paymentTypeName}
           <a href="control/shopping/ordermanage.do?method=editPaymentType&orderId=${order.orderId }">修改</a></td>
           <td align="center" bgcolor="#FFFFFF"><b>送货方式</b></td>
          <td colspan="2" bgcolor="#FFFFFF">  ${order.paymentAndDeliverMethod.deliverTypeName } <a href="control/shopping/ordermanage.do?method=editDeliverType&orderId=${order.orderId }">修改</a></td>
       
           </tr>
        <tr>
          <td colspan="4" bgcolor="#FFFFFF"><strong>订购的商品</strong></td>
          <td align="center" bgcolor="#FFFFFF"></td>
          <td colspan="2" bgcolor="#FFFFFF">
		  </td>
        </tr>
        <tr>
          <td align="center" bgcolor="#FFFFFF">商品编号</td>
          <td colspan="3" align="center" bgcolor="#FFFFFF">商品名称</td>
          <td align="center" bgcolor="#FFFFFF">单价</td>
          <td width="16%" align="center" bgcolor="#FFFFFF">数量</td>
          <td width="5%" align="center" bgcolor="#FFFFFF">&nbsp;</td>
        </tr>
<c:forEach items="${order.items}" var="item">
        <tr>
          <td align="center" bgcolor="#FFFFFF">${item.productId }</td>
          <td colspan="3" align="center" bgcolor="#FFFFFF">${item.productName } <font color="red">[
<c:forEach items="${item.orderItemAttribues}" var="option">
${ option.attributeName }:${ option.attributeValue } <a href="control/shopping/ordermanage.do?method=editAttribute&orderId=${order.orderId }&productId=${item.productId}&attributeOptionId=${option.id }">编辑</a>  
</c:forEach> 
          ]</font></td>
          <td align="center" bgcolor="#FFFFFF">￥${item.productPrice }</td>
          <td align="center" bgcolor="#FFFFFF"> ${item.amount }<a href="control/shopping/ordermanage.do?method=editOrderItemNumber&orderItemId=${item.id }&orderId=${order.orderId }">修改</a></td>
          <td align="center" bgcolor="#FFFFFF"><a href="control/shopping/ordermanage.do?method=deleteOrderItem&orderItemId=${item.id }&orderId=${order.orderId }">删除</a></td>
        </tr>
</c:forEach>
        <tr>
          <td colspan="7" align="right" bgcolor="#FFFFFF"><p>商品合计：￥${order.totalPrice }元&nbsp;&nbsp;配送费：￥${order.deliverFee }元 <a href="/control/shopping/ordermanage.do?method=editOrderDeliverFee&orderId=${order.orderId}">修改</a>&nbsp;&nbsp;订单合计：￥${order.totalOrderPrice }元<br />
        <c:if test="${order.payAbleFee!=order.totalOrderPrice}">
        <div style="color:#ff0000;float:left">注意，此订单应付金额不等于订单总金额</div>
        </c:if>    
			&nbsp;&nbsp;<strong>应付金额：</strong>￥${order.payAbleFee }元
			<a href="control/shopping/ordermanage.do?method=editOrderPayAbleFee&orderId=${order.orderId }">修改</a>
			</p>          </td>
        </tr>
      </table></td>
  </tr>
  <tr><td bgcolor="#ffffff"><p></p>
  订单状态更新
  <select name="status">
  <option>------</option>
  <c:forEach items="${orderStatuses}" var="sta">
  <option value="${sta.id }"<c:if test="${sta.name eq order.orderState}"> selected</c:if>>${sta.name }</option>
 
  </c:forEach>
  </select><input type="button" value="确定"><input type="button" value="解锁返回"/>
  </td></tr>
</table>

  </body>
</html>