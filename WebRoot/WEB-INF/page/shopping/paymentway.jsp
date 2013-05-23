<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/page/share/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<META http-equiv=Content-Language content=zh-CN>
<TITLE>结算中心：选择支付方式</title> 
<%@ include file="/location_check.jsp" %>
<!-- 
<link href="css/style.css" rel="stylesheet" type="text/css">
<link href="css/global/header01.css" rel="stylesheet" type="text/css">
<link href="css/product/list.css" rel="stylesheet" type="text/css" />
<link href="css/global/topsell.css" rel="stylesheet" type="text/css">
<LINK href="css/new_cart.css" rel="stylesheet" type="text/css">
 -->
<SCRIPT LANGUAGE="JavaScript">
<!--
hnfealean.coupon = {};
hnfealean.coupon =function(s){
var coupon = eval(s);
//alert(coupon);
if(coupon[0]==true){
	document.getElementById("checkcoupon").innerHTML="恭喜，您的代金券可用！代金券价值为￥<strong>"+coupon[1]+"</strong>";
}else{
	document.getElementById("checkcoupon").innerHTML="抱歉，您输入的代金券码不可用";	
}
}
function checkCoupon(){
	var v =document.getElementById("couponId").value;
	if(v==""){
		alert("请输入代金券码！");
		return;
	}
	if(v.length!=64){
		alert('代金券码为64位字符串，请检查您的输入是否正确！');
		return;
	}
	document.getElementById("checkcoupon").innerHTML="<div class='checking'>检测中，请稍等...</div>";
	document.getElementById("checkcoupon").style.display="block";
	//alert("");
	setTimeout('addJson("/user/coupon/manage.do?id='+v+'")',2000);
}
function checkNull(){
	var dMethods = document.getElementsByName("deliverMethod");
	for(var i=0;i<=dMethods.length;i++){
		if(i==dMethods.length){alert('请选择送货方式！');return false;}
		if(dMethods[i].checked)break;
	}
	var pMethods = document.getElementsByName("paymentMethod");
	for(var i=0;i<=pMethods.length;i++){
		if(i==pMethods.length){alert('请选择送货方式！');return false;}
		if(pMethods[i].checked)break;
	}
	return true;	
}
//-->
</SCRIPT>
</HEAD>

<BODY>
<TABLE cellSpacing=0 cellPadding=0 align=center border=0>
  <TBODY>
  <TR>
    <TD><a href="" title="回到首页"><IMG src="/images/global/logo.gif" border=0 /></a>
	&nbsp;&nbsp;<IMG height=36 src="/images/buy/az-s-checkout-payment-banne.gif" > 
	</TD>
  </TR>
  </TBODY>
</TABLE>
<BR>
<form action="customer/payment-method.do" method="post" name="paymentMethodForm">
<INPUT TYPE="hidden" NAME="method" value="savePaymentway">
<TABLE cellSpacing=0 cellPadding=0 width="90%" align="center" border=0>
  <TBODY>
  <TR>
    <TD>
<SPAN class=h1><STRONG>请选择您的送货与支付方式:</STRONG></SPAN> 
      <TABLE height=31 cellSpacing=0 cellPadding=0 width="100%" border=0>
        <TBODY>
        <TR>
          <TD>
            <DIV align=right><input onclick="return checkNull()" type="image" height="22" src="/images/buy/az-sfl-shipping-to-this-boo.gif" vspace=5 border=0 style="CURSOR: hand;">
        </DIV></TD></TR>
	 </TBODY></TABLE>
<A name="deliverMethod"></A>
      <TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor="#eeeecc" border=0><TBODY>
        <TR>
          <TD bgColor="#ffffff">
            <TABLE cellSpacing=0 cellPadding=4 width="100%" border=0>
              <TBODY>
              <TR bgColor="#eeeecc">
                <TD colSpan=2><STRONG>&nbsp;送货方式</STRONG></TD></TR>
                 <c:forEach items="${deliverTypes}" var="type">
				 <tr>
				 <TD class="big14" vAlign="middle" align="right" width="10%">
				  <input <c:if test="${paymentMethod.deliverMethod==type.id}">checked</c:if> type="radio" name="deliverMethod" value="${type.id }"/></TD>
				   <TD vAlign="middle" ><B>${type.name }</B> (费用:${type.fee}元)&nbsp;&nbsp;</TD>
				   </tr>
				 </c:forEach>
			<!-- 	<tr>
			 <TD class="big14" vAlign="middle" align="right" width="10%">
				 
				
				 <input <c:if test="${paymentMethod.deliverMethod==1}">checked</c:if> type="radio" name="deliverMethod" value="1"/></TD>
				 <TD vAlign="middle" ><B>平邮</B> (费用:0.0元)&nbsp;&nbsp;不支持货到付款，注:费用最低，需要到附近邮局自提，时间稍长</TD>
				</tr>
				<tr>
				<TD class="big14" vAlign="middle" align="right" width="10%"> 
				<input <c:if test="${paymentMethod.deliverMethod==2}">checked</c:if> type="radio" name="deliverMethod" value="2"/></TD>
				 <TD vAlign="middle" ><B>快递送货上门 </B> (费用:0.0元)&nbsp;&nbsp;支持货到付款 &nbsp;&nbsp;注:200个城市可以到达，部分城市不能到达</TD>
				</tr>
				<tr>
				<TD class="big14" vAlign="middle" align="right" width="10%"> 
				<input <c:if test="${paymentMethod.deliverMethod==3}">checked</c:if> type="radio" name="deliverMethod" value="3" /></TD>
				 <TD vAlign="middle" ><B>加急快递送货上门</B> (费用:0.0元)&nbsp;&nbsp;支持货到付款&nbsp;&nbsp;注:200个城市可以到达，部分城市不能到达</TD>
				</tr>
				<tr>
				 <TD class="big14" vAlign="middle" align="right" width="10%"> 
				 <input <c:if test="${paymentMethod.deliverMethod==4}">checked</c:if> type="radio" name="deliverMethod" value="4"/></TD>
				 <TD vAlign="middle" ><B>国内特快专递EMS</B> (费用:0.0
				 元)&nbsp;&nbsp;不支持货到付款&nbsp;&nbsp;注:适合其他快运无法到达的城市，时间3-5个工作日</TD>
				</tr>
				<tr>
				  <TD colspan="2" vAlign="middle" class="big14">
				  
				  <TABLE cellSpacing=0 cellPadding=3 width="86%" align="center" id="timerequirement" border=0 style="display:none">
                    <TBODY>
                      <TR>
                        <TD align=left colSpan=2 style="FONT-WEIGHT: bold; PADDING-BOTTOM: 2px; PADDING-TOP: 2px; BORDER-BOTTOM: #000000 1px solid">时间要求(注:如对送货时间有特别要求请注明)</TD>
                      </TR>
                      <TR>
                        <TD align=right>
                         <input <c:if test="${paymentMethod.timeLimit==1}">checked</c:if> type="radio" name="timeLimit" value="1"/></TD>
                        <TD align=left width="96%">工作日、双休日与假日均可送货</TD>
                      </TR>
                      <TR class=category-row-shaded>
                        <TD align=right>  <input <c:if test="${paymentMethod.timeLimit==2}">checked</c:if>  type="radio" name="timeLimit" value="2"/></TD>
                        <TD align=left>只双休日、假日送货(工作日不用送)</TD>
                      </TR>
                      <TR>
                        <TD align=right> <input <c:if test="${paymentMethod.timeLimit==3}">checked</c:if> type="radio" name="timeLimit" value="3"/></TD>
                        <TD align=left>只工作日送货(双休日、假日不用送) (注：写字楼/商用地址客户请选择)</TD>
                      </TR>
                      <TR class=category-row-shaded>
                        <TD align=right> <input <c:if test="${paymentMethod.timeLimit==4}">checked</c:if>  type="radio" name="timeLimit" value="4"/></TD>
                        <TD align=left>学校地址/地址白天没人，请尽量安排其他时间送货 (注：特别安排可能会超出预计送货天数)</TD>
                      </TR>
                      <TR>
                        <TD align=right> <input <c:if test="${paymentMethod.timeLimit==0}">checked</c:if> type="radio" name="timeLimit" value="0"/></TD>
                        <TD align=left><P>特殊说明：
                           <input type="text" name="messageRequire" <c:if test="${paymentMethod.timeLimit==0}"> value="${paymentMethod.messageRequire }"</c:if> maxlength="100" size="40" onfocus="javascript:setSelectRadioByValue(this.form.requirement,'other')"/>
                        </P></TD>
                      </TR>
                    </TBODY>
                  </TABLE></TD>
				  </tr>-->
			</TBODY></TABLE>
		  </TD>
		</TR>
	 </TBODY></TABLE>
<br>
      <TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#eeeecc border=0><TBODY>
        <TR>
          <TD bgColor=#ffffff>
            <TABLE cellSpacing=0 cellPadding=4 width="100%" border=0>
              <TBODY>
              <TR bgColor=#eeeecc>
                <TD colSpan=2><STRONG>&nbsp;支付方式</STRONG></TD>
			 </TR>
			 <c:forEach items="${paymentMethods}" var="method">
			 <c:if test=""></c:if>
			 			 <TR>
                <TD class="big14" vAlign="middle" align="right" width="10%"> 
                <input type="radio" name="paymentMethod" value="${method.id }"<c:if test="${method.id==paymentMethod.paymentMethod}"> checked</c:if>/> </TD>
                <TD vAlign="middle" ><B>${method.name }</B></TD>
			  </TR>
			  </c:forEach>
			<!--  <TR>
                <TD class="big14" vAlign="middle" align="right" width="10%"> 
                <input type="radio" name="paymentMethod" value="1"  <c:if test="${paymentMethod.paymentMethod==1}">checked</c:if>/> </TD>
                <TD vAlign="middle" ><B>网上支付</B></TD>
			  </TR>
              <TR id="paymentway_COD" >
                <TD class="big14" vAlign="middle" align="right" width="10%"> <input type="radio" <c:if test="${paymentMethod.paymentMethod==2}">checked</c:if> name="paymentMethod" value="2"/> </TD>
                <TD><B>货到付款</B></TD>
			  </TR>
			  <TR>
                <TD class="big14" vAlign="middle" align="right" width="10%"> <input type="radio" name="paymentMethod" value="3"  <c:if test="${paymentMethod.paymentMethod==3}">checked</c:if>/> </TD> 
                <TD><B>银行电汇</B></TD></TR>
              <TR>
                <TD class="big14" vAlign="middle" align="right" width="10%"> <input type="radio" name="paymentMethod" value="4"  <c:if test="${paymentMethod.paymentMethod==4}">checked</c:if>/></TD> 
                <TD><B>邮局汇款</B></TD></TR> -->
			 </TBODY></TABLE>
		 
  </TABLE>
  <br><table cellspacing="1" cellpadding="1" border="0" bgcolor="#eeeecc" width="100%"><tbody>
        <tr>
          <td bgcolor="#ffffff">
		  <table cellspacing="0" cellpadding="4" border="0" width="100%">
<tbody>
<tr bgcolor="#eeeecc">
<td colspan="3"><strong>&nbsp;使用代金券（填入代金券码并验证）</strong></td>
</tr><tr>
<TD class="big14" vAlign="middle" align="right" width="8%">
<td valign="middle" style="width:530px"><b>代金券码：</b>
<input type="text" value="" size="64" maxlength="64" name="id" id="couponId">
<div style="float:right">
<img src="images/global/check.gif" onclick="checkCoupon()"/></div>
</td>
<td>
<div id="checkcoupon" class="OkMsg" style="display:none">
</div></td>
</tr>
</tbody></table>	</TD></TR>
			 </TBODY></TABLE>
			</TD></TR></TBODY></TABLE><BR>

      <TABLE height=31 cellSpacing=0 cellPadding=0 width="90%" border=0 align="center">
        <TBODY>
        <TR>
          <TD>
            <DIV align=right><input onclick="return checkNull()" type="image" height="22" src="/images/buy/az-sfl-shipping-to-this-boo.gif" vspace=5 border=0 style="CURSOR: hand;"> 
        </DIV></TD></TR></TBODY></TABLE>
<form>

</BODY></HTML>