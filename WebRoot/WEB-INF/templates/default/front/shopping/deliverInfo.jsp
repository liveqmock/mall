<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/templates/default/share/taglib.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML><HEAD>
<META http-equiv="pragma" content="no-cache">
<META http-equiv="Cache-Control" content="no-cache, must-revalidate">
<META http-equiv="Content-TYPE" content="text/html;charset=utf-8">
<%@ include file="/location_check.jsp" %>
<TITLE>结算中心：填写收货地址</TITLE>
<SCRIPT src="country/1.js" type=text/javascript></SCRIPT>
<SCRIPT type="text/javascript">
<!--
var trimReg=/^\s*(.*)\s*$/;
var emailTest =/^(\w+)@(\w+)\.(\w+)$/;
var postCodeTest=/^[1-9]\d{5}$/;
var phoneTest=/^(\d{3,4}-)?\d{7,10}$/;
var mobileTest=/^1\d{10}$/;
window.onload=function(){
	initial();
	document.getElementById("topayment").onclick=function(){
		return checkFormat();
	}
	addBlurHandler();
	initialAreaSelection();
	attachClick();
	//changeBg();
		//hnfealean.addEventHandler(document.getElementById("topayment"),"click",checkFormat);
}
function changeBg(){
var m =document.getElementsByName("deliverInfoId");
if(m==undefined||m.length==0)	return;
for(var i=0;i<m.length;i++){
	hnfealean.addEventHandler(m[i],"click",function(){
	window.event?o=window.event.srcElement:o=this;
		if(o.parentNode.className.indexOf("selected")>=0){
		}else{
			o.parentNode.className="selected "+o.parentNode.className;
		}
		});
	hnfealean.addEventHandler(m[i],"mouseover",function(){
		window.event?o=window.event.srcElement:o=this;
		if(o.parentNode.className.indexOf("hover")>=0){
		}else{
			o.parentNode.className="hover "+o.parentNode.className;
		}
		});
}
	
}
function initialAreaSelection(){
	var state0 =document.getElementById("state");

	var stateId,cityId;
	for(var i=0;i<state0.length;i++){
		if(state0[i].text=="${deliverInfo.state }"){
			state0.selectedIndex=i;
			stateId=i-1;
			initialcities(stateId);
			}
	}
	var city0 = document.getElementById("city");
	if(city0!=undefined)
	for(var i=0;i<city0.length;i++){
		if(city0[i].text=="${deliverInfo.city }")
			{city0.selectedIndex=i;
			cityId=i-1;
			initialcounties(stateId,cityId);
			}
	}
	var county0 = document.getElementById("county");
	if(county0!=undefined)
	for(var i=0;i<county0.length;i++){
		if(county0[i].text=="${deliverInfo.county }")
			county0.selectedIndex=i;
	}
}
function addBlurHandler(){
	var dName = document.getElementById("deliverName");
	if(dName!=undefined)
		hnfealean.addEventHandler(dName,"blur",function(){
		window.event?o=window.event.srcElement:o=this;
			o.value=o.value.replace(trimReg,"$1");
			if(o.value==""){
				document.getElementById("deliverName_error").style.display="block";
			}else{
				document.getElementById("deliverName_error").style.display="none";
			}
		});

	var dStreet_address=document.getElementsByName("street_address");
	if(dStreet_address!=undefined&&dStreet_address.length>0){
		dStreet_address=dStreet_address[0];
		hnfealean.addEventHandler(dStreet_address,"blur",function(){
		window.event?o=window.event.srcElement:o=this;
			o.value=o.value.replace(trimReg,"$1");
			if(o.value==""){
				document.getElementById("street_address_error").style.display="block";
			}else{
				document.getElementById("street_address_error").style.display="none";
			}
		});
	}
	var email = document.getElementsByName("email");
	if(email!=undefined&&email.length>0){
		email=email[0];
		hnfealean.addEventHandler(email,"blur",function(){
		window.event?o=window.event.srcElement:o=this;
			o.value=o.value.replace(trimReg,"$1");
			if(emailTest.test(o.value)==false){
				document.getElementById("email_error").style.display="block";
			}else{
					document.getElementById("email_error").style.display="none";
			}
		});
	}
	var postCode = document.getElementsByName("postCode");
	if(postCode!=undefined&&postCode.length>0){
		postCode=postCode[0];
		hnfealean.addEventHandler(postCode,"blur",function(){
		window.event?o=window.event.srcElement:o=this;
			o.value=o.value.replace(trimReg,"$1");
			if(postCodeTest.test(o.value)==false){
				document.getElementById("postCode_error").style.display="block";
			}else{
					document.getElementById("postCode_error").style.display="none";
			}
		});
	}
	var phone = document.getElementsByName("phone");
	if(phone!=undefined&&phone.length>0){
		phone=phone[0];
		hnfealean.addEventHandler(phone,"blur",function(){
		window.event?o=window.event.srcElement:o=this;
			o.value=o.value.replace(trimReg,"$1");
			if(o.value==""){}else{
				if(phoneTest.test(o.value)==false){
					document.getElementById("phone_error").style.display="block";
				}else{
						document.getElementById("phone_error").style.display="none";
				}
			}
		});
	}
	var mobile = document.getElementsByName("mobile");
	if(mobile!=undefined&&mobile.length>0){
		mobile=mobile[0];
		hnfealean.addEventHandler(mobile,"blur",function(){
		window.event?o=window.event.srcElement:o=this;
			o.value=o.value.replace(trimReg,"$1");
			if(o.value==""){
				document.getElementById("mobile_error").style.display="none";
				}else{
				if(mobileTest.test(o.value)==false){
					document.getElementById("mobile_error").style.display="block";
				}else{
						document.getElementById("mobile_error").style.display="none";
				}
			}
		});
	}	
}
function checkFormat(){
	var ids = document.getElementsByName("deliverInfoId");
	if(ids!=undefined&&ids.length>0){
		for(var i=0;i<ids.length;i++){
			if(ids[i].checked&&ids[i].value>0){
				return true;
			}
			if(ids[i].checked&&ids[i].value<0){
				return check();
				}
		}
		alert("请输入新的收货地址或者从地址簿中选择！");
		return false;
		
	}
}
function check(){
	var dName = document.getElementById("deliverName");
	if(dName.value==""){alert("请输入收货人姓名！");return false;}
	var sex = document.getElementsByName("gender");
	for(var i=0;i<=sex.length;i++){
		if(i==sex.length&&i>0){alert("请选择性别！");return false;}
		if(sex[i].checked)	break;
	}
	var dState = document.getElementById("state");
	if(dState!=undefined){
		var l = dState.length;
		if(dState.selectedIndex==0&&l>1){
				alert("请选择省份");return false;
			}
	}
	var dCity = document.getElementById("city");
	if(dCity!=undefined){
		var l = dCity.length;
		if(dCity.selectedIndex==0&&l>1){
				alert("请选择城市");return false;
			}
	}
	var dCounty = document.getElementById("county");
	if(dCounty!=undefined){
		var l = dCounty.length;
		if(dCounty.selectedIndex==0&&l>1){
				alert("请选择区县");return false;
			}
	}
	var street_address = document.getElementsByName("street_address");
	if(street_address!=undefined&&street_address.length>0)street_address =street_address[0];
	else{alert("不存在街道详细地址输入框！");return false;}
	street_address.value=street_address.value.replace(/^\s*(.*)\s*$/,"$1");
	//alert(street_address.value);
	if(street_address.value==""){
		alert("请填写您的街道详细地址，以保证我们能准确送货，谢谢！");return false;
	}
	var email = document.getElementsByName("email");
	if(email!=undefined&&email.length>0)email=email[0];
	else{alert("不存在email输入框！");return false;}
	if(/^(\w+)@(\w+)\.(\w+)$/.test(email.value)==false){
		alert("电子邮件地址格式不正确");
		return false;
	}
	var postCode = document.getElementsByName("postCode");
	if(postCode!=undefined&&postCode.length>0){
		postCode=postCode[0];
		if(postCodeTest.test(postCode.value)==false){
			alert("请正确填写邮编！");return false;
			}
	}else{alert("不存在邮政编码输入框！");return false;}
	
	var phone = document.getElementsByName("phone");
	if(phone!=undefined&&phone.length>0){
		phone=phone[0];
	}
	var mobile = document.getElementsByName("mobile");
	if(mobile!=undefined&&mobile.length>0){
		mobile=mobile[0];
	}
	if(phone.value==""&&mobile.value==""){
		alert("手机和电话号码至少填写一项！");return false;
	}
	if(phone.value!=""){
		if(phoneTest.test(phone.value)==false){
			alert("电话号码格式不正确！");
			return false;
		}
	}
	if(mobile.value!=""){
	if(mobileTest.test(mobile.value)==false){
		alert("手机号码格式不正确！");
		return false;
	}
	}
}
function initial(){
if(document.getElementById("sel0")==null){
var sel0 = document.createElement("select");
sel0.id="state";
sel0.name="state";
//sel0.onchange="alert(this.value)";
document.getElementById("area").appendChild(sel0);
//document.body.appendChild(sel0);
}else{
document.getElementById("state").innerHTML="";
}
sel0=document.getElementById("state");
sel0.options.add(new Option("Select"));
for(var i=0;i<areaJson.length;i++){
sel0.options.add(new Option(areaJson[i].t,areaJson[i].id));
}
sel0.onchange=function(){initialcities(this./*sel0.*/selectedIndex-1);	}
}


function initialcities(proid){
	if(proid<0)return;
	
	//proid--;
if(document.getElementById("city")==null){
var sel1 = document.createElement("select");
sel1.id="city";
sel1.name="city";
//document.body.appendChild(sel1);
document.getElementById("area").appendChild(sel1);
}else{
document.getElementById("city").innerHTML="";
}
sel1=document.getElementById("city");
//alert(proid);
//alert(areaJson.length);
if(areaJson[proid]==undefined){//
	return;
}
if(typeof(areaJson[proid].s)!=undefined){
sel1.options.add(new Option("Select"));
for(var i=0;i<areaJson[proid].s.length;i++){
sel1.options.add(new Option(areaJson[proid].s[i].t,areaJson[proid].s[i].id));
}
}
if(document.getElementById("county")!=null){
//document.body.removeChild(document.getElementById("sel2"));
if(document.getElementById("county").parentNode!=null)
document.getElementById("county").parentNode.removeChild(document.getElementById("county"));
}
document.getElementById("city").onchange =function(){
		window.event?o=window.event.srcElement:o=this;
//alert(this.value);
if(o.selectedIndex>0)
initialcounties(document.getElementById("state").selectedIndex-1,document.getElementById("city").selectedIndex-1);
}
}
function initialcounties(pid,cid){
if(document.getElementById("county")==null){
var sel2 = document.createElement("select");
sel2.id="county";
sel2.name="county";
//document.body.appendChild(sel2);
document.getElementById("area").appendChild(sel2);
}else{
document.getElementById("county").innerHTML="";
}
sel2=document.getElementById("county");
//alert(areaJson[pid].s[cid].s);
sel2.options.add(new Option("select"));
if(typeof(areaJson[pid].s[cid].s)!=undefined)
for(var i=0;i<areaJson[pid].s[cid].s.length;i++){
//alert(areaJson[pid].s[cid].s.length);
sel2.options.add(new Option(areaJson[pid].s[cid].s[i].t,areaJson[pid].s[cid].s[i].id));
}
}
function attachClick(){

	var ids = document.getElementsByName("deliverInfoId");
	if(ids!=undefined&&ids.length>0){
		for(var i=0;i<ids.length;i++){
			hnfealean.addEventHandler(ids[i],"click",function(){
				window.event?o=window.event.srcElement:o=this;
				var newDeliverInfoTable = document.getElementById("newDeliverInfoTable");
					if(o.value=="-1"){
						newDeliverInfoTable.style.display='block';
					}else{
						newDeliverInfoTable.style.display='none';
						}


				});
			}
		}
}
//-->
</SCRIPT>
</HEAD>
<BODY style="background:#ffffff">
<html:form action="customer/deliver" method="post">
<input type="hidden" name="directUrl" value="${param.directUrl }">
<INPUT TYPE="hidden" NAME="method" value="gotopayment">
<TABLE cellSpacing=0 cellPadding=0 align="center" border=0>
  <TBODY>
  <TR>
    <TD><a href="" title="回到首页"><img src="images/global/logo.gif" border=0 /></a>  &nbsp;&nbsp;<IMG height=36 src="images/buy/az-s-checkout-shipping-bann.gif" > </TD></TR></TBODY></TABLE><BR>

<TABLE cellSpacing=0 cellPadding=0 class="delivertable" align=center border=0>
  <TBODY>
  <tr><td>
<table cellspacing="0" cellpadding="4" border="0" width="100%">
<tbody>
<tr bgcolor="#eeeecc">
<td><strong><span class="h1"><strong>确认收货地址</strong>:</span></strong></td>
<td bgcolor="#eeeecc">&nbsp;</td></tr></tbody></table>
  <ul id="address-collection">
<c:forEach items="${deliverInfos}" var="info" begin="0" end="7">
<li><input type="radio"<c:if test="${deliverInfo.id==info.id}"> checked="checked"</c:if> name="deliverInfoId" value="${info.id }"/>
<span class="buyer-name">${info.name }</span>
<span class="buyer-address"><!-- ${info.country } -->${info.state } ${info.city } ${info.county } ${info.street_address}</span>
</li>
<!-- <div class="latestDeliverInfo">
<input type="radio" name="deliverInfoId" value="${info.id }" />
姓名:<br /> 
地址:${info.country } ${info.state } ${info.city } ${info.county } ${info.street_address} <br />
联系手机:${info.phone}<br />
联系电话:${info.mobile}<br />
邮编:${info.postCode}<br />
邮箱:${info.email}
</div>-->
</c:forEach>
<li><input type="radio" name="deliverInfoId" value="-1"<c:if test="${empty deliverInfos||empty deliverInfo||((!empty deliverInfo)&&(deliverInfo.id==0))}"> checked="checked"</c:if>/><span class="buyer-name">使用其他收货地址</span>
</li>
</ul><!-- 
<script>
function changeNewDeliverInfoTableVisible(){
	var newDeliverInfoTable = document.getElementById("newDeliverInfoTable");
	newDeliverInfoTable.style.display=='none'?newDeliverInfoTable.style.display="block":newDeliverInfoTable.style.display="none";
}
</script>
<a href="javascript:void(0)" onclick="changeNewDeliverInfoTableVisible()">使用其他收货地址</a> -->
  </td>
  </tr><tr><!-- <c:if test="${fn:length(deliverInfos)>0}">none</c:if><c:if test="${empty deliverInfos}">block</c:if> -->
    <TD><div id="newDeliverInfoTable" style="<c:if test="${deliverInfo.id>0&&(fn:length(deliverInfos)>0)}">display:none</c:if>">

      <DIV id="cndivaddress">
  <TABLE cellSpacing=1 cellPadding=1 width="100%" bgColor=#eeeecc 
        border=0><TBODY>

        <TR>
          <TD bgColor="#ffffff">
            <TABLE cellSpacing=0 cellPadding=4 width="100%" border=0>
              <TBODY>
              <TR bgColor="#eeeecc">
                <TD><strong><span 
            class=h1><strong>请输入配送地址</strong>:</span></strong></TD>
                <TD bgColor="#eeeecc">&nbsp;</TD></TR></TBODY></TABLE></TD></TR></TBODY></TABLE>

      <TABLE cellSpacing=1 cellPadding=4 width="100%" border=0 style="background:#F3F3F3 none repeat scroll 0 0;">
        <TBODY>
 
        <TR>
          <TD width=214>
            <DIV align="right">收货人姓名：</DIV></TD>
          <TD><input type="text" name="name" value="${deliverInfo.name}" maxlength="30" size="30" id="deliverName"/><FONT color="#ff0000">*</FONT>&nbsp;
          <div id="deliverName_error" style="display:none" class="error">收货人姓名不能为空</div>
       </TD></TR>
		<TR> <TD><DIV align="right">性别：</DIV> </td>
          <td>  <input type="radio" name="gender" <c:if test="${empty deliverInfo|| deliverInfo.gender=='MAN'}">checked</c:if> value="MAN"/>先生
         <input type="radio" name="gender" <c:if test="${deliverInfo.gender=='WOMAN'}">checked</c:if> value="WOMAN"/>女士</td></TR>
     <TR>             <TD><DIV align="right">国家：</DIV>
          </td>
          
          <td>  <input type="radio" checked="checked" name="country" value="1"> 中国 
          	<!-- <input type="radio" value="0" name="country">其它国家/地区 --></td></TR>
        <TR>
          <TD height="27">
            <DIV align="right">收货人地址： </DIV></TD>

          <TD>
          <div id="area"></div></TD></TR>
          <TR>
          <TD height="27">
            <DIV align="right"> 街道地址： </DIV></TD>

          <TD>
         <input type="text" name="street_address" value="${deliverInfo.street_address }" maxlength="100" size="60"/><FONT 
            color="#ff0000">*</FONT><div id="street_address_error" style="display:none" class="error">收货人地址不能为空</div></TD></TR>          
           
        <TR>
          <TD height="27">
            <DIV align="right">电子邮件：</DIV></TD>
          <TD><input type="text" name="email" value="${deliverInfo.email}"><FONT color="#ff0000">*</FONT>
          <div id="email_error" style="display:none" class="error">电子邮件地址格式不正确</div>
          </TD></TR>
        <TR>
          <TD height="27">
            <DIV align="right">邮政编码：</DIV></TD>
          <TD><input type="text" name="postCode" value="${deliverInfo.postCode }" maxlength="6" size="20"/><FONT 
            color="#ff0000">*</FONT> <font color="#484848">请正确填写邮政编码，以免延误您的订单送达时间。</font>
             <div id="postCode_error" style="display:none" class="error">邮政编码格式不正确,应为6位数字</div>
            </TD></TR>
        <TR>

          <TD>
            <DIV align=right>电话：</DIV></TD>
          <td><input type="text" name="phone" value="${deliverInfo.phone}" />
          <div id="phone_error" style="display:none" class="error">电话号码格式不正确</div>
          </td>
        </TR>
        <TR>
          <TD>
            <DIV align=right>手机：</DIV></TD>

          <TD><input type="text" name="mobile" value="${deliverInfo.mobile }" maxlength="15" size="20"/> 
			<font color="#484848">（手机和电话至少有一项必填）</font>
			<div id="mobile_error" style="display:none" class="error">手机号码格式不正确</div>
			</TD></TR>
   <!--      <TR>
          <TD colSpan=2 align="center"><input type="image"  src="images/buy/az-sfl-shipping-to-this-boo.gif" vspace=5 border=0 style="CURSOR: hand;" id="topayment"></TD></TR>
-->
		  </TBODY></TABLE>
  
		  </div></div>
<table cellspacing="0" cellpadding="4" border="0" width="100%">
<tbody>
<tr bgcolor="#eeeecc">
<td align="center"><input type="image"  src="images/buy/az-sfl-shipping-to-this-boo.gif" vspace=5 border=0 style="CURSOR: hand;" id="topayment"></td></tr></tbody></table>	  
	
      </TD></TR></TBODY></TABLE>
</html:form>


</BODY></HTML>