<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/page/share/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<META http-equiv=Content-Language content=zh-CN>
<TITLE>结算中心：订单确认</title> 
<%@ include file="/location_check.jsp" %><!-- 
<link href="css/style.css" rel="stylesheet" type="text/css">
<link href="css/global/header01.css" rel="stylesheet" type="text/css">
<link href="css/product/list.css" rel="stylesheet" type="text/css" />
<link href="css/global/topsell.css" rel="stylesheet" type="text/css">
<LINK href="css/new_cart.css" rel="stylesheet" type="text/css"> -->

<SCRIPT LANGUAGE="JavaScript">
<!--
// 表单验证
function validateForm(){
	var form = document.forms[0];
	if(form.note.value.trim()!="" ){
		if(byteLength(form.note.value)>200){
			alert("\n附言必须在100字以内");
			return false;
		}
	}
	return true;
}
function createOrder(){

	document.forms['frontOrderForm'].action='customer/ordermanage.do';
	document.forms['frontOrderForm'].elements['method'].value='saveOrder';
	document.forms['frontOrderForm'].submit();
}
//-->
</SCRIPT>
</HEAD>
<BODY>
<TABLE cellSpacing=0 cellPadding=0 align=center border=0>
  <TBODY>
  <TR>
    <TD><IMG src="images/global/logo.gif" >&nbsp;&nbsp;<IMG height=36 src="/images/buy/az-s-checkout-confirm-banne.gif" >
  </TD></TR>
  </TBODY>
</TABLE>
<BR>

<form action="customer/order-confirm.do" method="post" name="frontOrderForm" id="frontOrderForm">
<input type="hidden" name="method" value="leaveMessage"/>
<TABLE cellSpacing=0 cellPadding=0 width="90%" align=center border=0>
  <TBODY>
  <TR>
    <TD>
      <TABLE cellSpacing=0 cellPadding=4 width="100%" border=0>
        <TBODY>
        <TR>
          <TD>
            <DIV align=left><SPAN 
            class=h1><STRONG>请查看您的订单,点击“订单确认”后提交订单</STRONG></SPAN><BR>
            <BR>
            <TABLE cellSpacing=0 cellPadding=0 width="100%" align=center 
            border=0>
              <TBODY>
              <TR>
                <TD vAlign=top width=7 bgColor=#3163ce height=30><img height=17 
                  src="images/buy/az-s-top-left-blue-corner.gif" width=17/></TD>
                <TD bgColor=#3163ce Align="top">
                  <DIV align=center><IMG height=21 src="images/buy/az-s-click-place-your-order.gif" width=420> </DIV></TD>
                <TD bgColor=#3163ce Align=right>
                  <DIV id=layer_finish1><INPUT onClick="return validateForm()" type="image" height=37 alt=订单确认 width=116 src="images/buy/az-s-place-order_01.gif" border=0> </DIV></TD>
                <TD vAlign=top width=7 bgColor=#3163ce><IMG height=17 src="images/buy/az-s-top-right-blue-corner.gif"  width=17></TD></TR></TBODY></TABLE>
            <TABLE cellSpacing=17 cellPadding=0 width="100%" align=center 
            bgColor=#F4F4EC border=0>
              <TBODY>
              <TR>
                <TD vAlign=top>
                  <TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
                    <TBODY>
                    <TR>
                      <TD width=6><IMG height=20 src="images/buy/az-s-spc-tl-inside-drksnd.gif" width=6></TD>
                      <TD bgColor=#bbbb9e>
                        <DIV class=font14 align=center><STRONG>配送详情</STRONG></DIV></TD>
                      <TD width=6><IMG height=20 src="images/buy/az-s-spc-tr-inside-drksnd.gif" width=6></TD></TR></TBODY></TABLE>
                  <TABLE cellSpacing=0 cellPadding=3 width="100%" border=0>
                    <TBODY>
                      <TR>
                        <TD width=84 
                        bgColor=#eeeeee>&nbsp;<STRONG>商品信息：</STRONG></TD>
                        <TD bgColor=#eeeeee><A href="<html:rewrite action="user/shoppingcart"/>?directUrl=${directUrl }"><IMG height=17 src="images/buy/az-s-change.gif" width=45 border=0></A></TD>
                      </TR>
                    </TBODY>
                  </TABLE>
                  <TABLE cellSpacing=0 cellPadding=4 width="100%" 
                  bgColor=#ffffff border=0>
                    <TBODY>
                      <TR>
                        <TD><table width="96%" border="0" align="right" cellpadding="5" cellspacing="0">
                          <tr>
                            <td height="1" colspan="3" bgcolor="#3163CE"></td>
                          </tr>
<!-- loop begin -->
<c:forEach items="${shoppingCart.shoppingCartItems}" var="item">
      <TR>
        <TD width="68%" height="33">
		<STRONG><!--<A href="product/view.do?productid=${item.product.id }"-->
		<a href="${item.product.shtml_File_Name}" target="_blank" title="${item.product.name }">${item.product.name }</a></STRONG>
	<br/>	
		<c:forEach items="${item.attributes}" var="option">

${ option.attribute.name }:${ option.value }<br/>

</c:forEach> 
		<!-- <span class="h3color">[颜色/样式：]</span>  --></TD>
        <TD width="11%" align="center">数量：${item.amount }</TD>
        <TD width="21%">单价：￥<span class="Price">${item.product.sellPrice }元</SPAN></TD>
      </TR>
</c:forEach><!-- loop end -->


                          <tr>
                            <td height="1" colspan="3" bgcolor="#CCCCCC"></td>
                          </tr>
                          <tr>
                            <td colspan="3" align="right">商品总价：￥${shoppingCart.totalSellPrice }元&nbsp; 配送费：￥${shoppingCart.deliveFee }元
							
							&nbsp; <span >订单金额：￥${shoppingCart.orderTotalPrice }元</span></td>
                            </tr>
                          <tr>
                            <td colspan="3" align="right">
							&nbsp;<strong><font color=#cc0000>应付金额：￥${shoppingCart.orderTotalPrice }元</font></strong></td>
                          </tr>
                        </table></TD>
                      </TR>
                    </TBODY>
                  </TABLE>
                  <TABLE cellSpacing=0 cellPadding=3 width="100%" border=0>
                    <TBODY>
                    <TR>
                      <TD width=80 bgColor=#eeeeee>&nbsp;<STRONG>送货地址：</STRONG></TD>
                      <TD bgColor=#eeeeee><A href="<html:rewrite action="customer/deliver.do"/>?directUrl=${directUrl }"><IMG height=17 src="images/buy/az-s-change.gif" width=45 border=0></A></TD></TR>
				  </TBODY></TABLE>
                  <TABLE cellSpacing=0 cellPadding=5 width="100%" border=0>
                    <TBODY>
                    <TR>
                      <TD bgColor=#ffffff>&nbsp;收货人姓名：${deliverInfo.name}<BR>
                      &nbsp;地址：${deliverInfo.country},${deliverInfo.state},${deliverInfo.city},${deliverInfo.county},${deliverInfo.street_address}
						<BR>&nbsp;邮编：${deliverInfo.postCode}
                        <c:if test="${!empty deliverInfo.phone}"> <br/>&nbsp;固定电话：${deliverInfo.phone} </c:if>
                        <c:if test="${!empty deliverInfo.mobile}"> <br/>&nbsp;手机：${deliverInfo.mobile} </c:if>
						</TD></TR>
				  </TBODY></TABLE>
                  <TABLE cellSpacing=0 cellPadding=3 width="100%" border=0>
                    <TBODY>
                    <TR>
                      <TD width=150 bgColor=#eeeeee>&nbsp;<STRONG>送货与付款方式：</STRONG></TD>
                      <TD bgColor=#eeeeee><a href="customer/payment-method.do?directUrl=${directUrl }#deliverway"/><img height=17 src="images/buy/az-s-change.gif" width=45 border=0></a></TD>
                    </TR></TBODY></TABLE>
                  <TABLE cellSpacing=0 cellPadding=4 width="100%" bgColor=#ffffff border=0>
                    <TBODY>
                    <TR>
                      <TD>
                        <TABLE width="60%">
                          <TBODY>
                          <TR>
                            <TD width="30">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</TD>
                            <TD width="250">&nbsp;送货方式：
                            ${paymentMethod.deliverTypeName }
                          <!--   <c:if test="${paymentMethod.deliverMethod==1}">平邮</c:if>
                             <c:if test="${paymentMethod.deliverMethod==2}">快递送货上门</c:if>
                              <c:if test="${paymentMethod.deliverMethod==3}">加急快递送货上门</c:if>
                               <c:if test="${paymentMethod.deliverMethod==4}">国内特快专递EMS</c:if> -->
                            </TD>
                            <TD width="30">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</TD>
                            <TD width="250">&nbsp;付款方式：${paymentMethod.paymentTypeName}<%/*
                              <c:if test="${paymentMethod.paymentMethod==1}">网上支付</c:if>
                                <c:if test="${paymentMethod.paymentMethod==2}">货到付款</c:if>
                                  <c:if test="${paymentMethod.paymentMethod==3}">银行电汇</c:if>
                                    <c:if test="${paymentMethod.paymentMethod==4}">邮局汇款</c:if>*/ %>
                           </TD>
						</TR>
                          <TR>
                            <TD>&nbsp;</TD>
                            <TD colspan="3">
                              &nbsp;时间要求：
                           <!--     <c:if test="${paymentMethod.timeLimit==1}">工作日、双休日与假日均可送货</c:if>
                             <c:if test="${paymentMethod.timeLimit==2}">只双休日、假日送货(工作日不用送)</c:if>
                              <c:if test="${paymentMethod.timeLimit==3}">只工作日送货(双休日、假日不用送)</c:if>
                               <c:if test="${paymentMethod.timeLimit==4}">学校地址/地址白天没人，请尽量安排其他时间送货</c:if>
                               <c:if test="${paymentMethod.timeLimit==0}">${paymentMethod.messageRequire}</c:if>  
						 -->	</TD>
                            </TR>
						</TBODY></TABLE>
					  </TD>
                    </TR>
				 </TBODY></TABLE>
				
                  <TABLE cellSpacing=0 cellPadding=3 width="100%" border=0>
                    <TBODY>
                    <TR>
                      <TD bgColor=#eeeeee>&nbsp;<STRONG>附言</STRONG> (填写您的一些要求,100字以内)：</TD>
                    </TR>
					 <TR>
                      <TD><TEXTAREA NAME="message" ROWS="3" COLS="60">${shoppingCart.message }</TEXTAREA>
                      <br/><span style="text-align:center;width:500px"><input type="submit" value="留言"/>${msg }</span></TD>
                    </TR>
				  </TBODY></TABLE></TBODY></TABLE>
            <TABLE cellSpacing=0 cellPadding=0 width="100%" align="center" bgColor="#4480dd" border=0>
              <TBODY>
              <TR>
                <TD vAlign="bottom" width=7 height=30><IMG height=17 src="images/buy/az-s-bottom-left-blue-corner.gif"  width=17></TD>
                <TD Align="right">
                  <DIV id="layer_finish2">
                 
                  <a href="customer/ordermanage.do?method=saveOrder"><img src="images/buy/az-s-place-order_02.gif" border=0 /></a></TD>
                <TD vAlign=bottom width=7><IMG height=17 
                  src="images/buy/az-s-bottom-right-blue-corner.gif" 
                  width=17></TD></TR></TBODY></TABLE></DIV></TD></TR></TBODY></TABLE>
  <DIV align=center></DIV></TD></TR></TBODY></TABLE>
</form>
</BODY></HTML>