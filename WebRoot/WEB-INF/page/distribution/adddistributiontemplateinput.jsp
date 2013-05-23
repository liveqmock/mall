<%@ page contentType="text/html;charset=utf-8" %>
<%@ include file="/WEB-INF/page/share/taglib.jsp" %>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<html>
<head>
<title>新增运费模板</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<jsp:include page="/location_check.jsp"></jsp:include>
<link rel="stylesheet" href="css/vip.css" type="text/css">
<link href="css/product/list.css" rel="stylesheet" type="text/css" />
<script>
function hideLocation(m){

	m.parentNode.parentNode.style.display="none";
}
function popupLocation(m){
	document.getElementById("location"+m).style.display="block";
}
function doSelectLocation(m){
var top = document.getElementsByName("ordItems"+m)[0];
var area =document.getElementsByName("zones"+m);
var areastring=new StringBuffer;
for(var i=0;i<area.length;i++){
	if(area[i].checked)areastring.append(area[i].value+",");
}
var zones =areastring.toString("");
zones=zones.substring(0,zones.length-1);
if(zones.length==0)return;
for(var i=0;i<area.length;i++){
	area[i].checked=false;
}
var liItem = document.createElement("LI");
liItem.innerHTML="至"+
			"<textarea name='zone"+m+"'>"+zones+"</textarea>"
			+"的运费:<input type='text' maxlength='6' size='6' value='' name='baseFee"+m+"'>元，&nbsp;每超过一件需要增加运费：<input type='text' maxlength='6' size='6' value='0.0' name='oneMoreFee"+m+"'>元";
top.appendChild(liItem);
}


</script>
</head>

<body bgcolor="#FFFFFF" text="#000000" marginwidth="0" marginheight="0">

<form action="control/center/distribution/manage.do" method="post">
<input type="hidden" value="addDistributionTemplate" name="method" />
<div id="Content">
  <div id="Main">
    <div id="FreTopBox"></div>
  <h1>新增运费模板: <span style="display: block; text-align: right; color: blue; text-decoration: underline;margin:-40px 20px 0 0;"><a href="http://wuliu.taobao.com/user/queryCarriRate.htm" target="_blank" >运费计算器</a></span></h1>
<div id="FreContent">

<h2>请输入运费模板名称:<span>(提示：您可以按照物品重量和体积来命名。比如“小于一公斤的物品”)</span></h2>

<div class="AddBox">
<input type="text" name="distributionTemplateName" value="" size="80" maxlength="50" title="运费模板名称为必填项，并且不能超过25个字"/>

</div>
<hr />
<h2>请选择并添加运费方式:<span>(提示：除指定地区外，其余地区的运费采用“默认运费”)<a href="http://service.taobao.com/support/146-36/help-1745.htm" target="_blank">查看如何设置运费</a></span></h2>
<div class="AddBox">
<ul>
<c:forEach items="${deliverTypes}" var="type">
<li>
<input id="deliverTypeIds${type.id}" name="deliverTypeIds" type="checkbox" value="${type.id}"  onclick="displayArea(this, _$('ordArea'))" />

<label for="deliverTypeIds${type.id}">${type.name}</label>
<div id="ordArea" class="AddFreight">
<div class="AddFre_Top"></div>
<ol id="ordItems" name="ordItems${type.id}">
					  <li id="defaultOrdItem" style="background:#F3FEED;">
请设置默认运费：<input type="text" id="baseFee${type.id}" name="baseFee${type.id}" value="" size="6" maxlength="6" title="运费必须为有效数值，且不得大于999.99元"/>

元，&nbsp;&nbsp;每超过一件需要增加运费：
<input type="text" id="oneMoreFee${type.id}" name="oneMoreFee${type.id}" value="0.0" size="6" maxlength="6" title='宝贝的加收运费必须为小于等于宝贝运费的数值'/>

元</li>
		  </ol>

<div class="AddFre_Bottom">
<p><img src="/images/global/add.gif" />
<a href="#" onClick="popupLocation(${type.id});return false;">为指定地区设置运费</a></p>
<div id="location${type.id}" style="display:none" class="location"> <span><a onclick="hideLocation(this);">
<img src="/images/global/close.gif"></a></span>
<ul id="areas">
<c:forEach items="${zones}" var="zone">
<li><input type="checkbox" onclick="checkDivisions(this.value,this.checked);" value='${zone.name }' name="zones${type.id}">${zone.name }</label></li>
</c:forEach>
<hr>
		  <div>
			<button onclick="doSelectLocation(${type.id});" type="button">确定</button>
			<button onclick="hideLocation(this.parentNode);" type="button">取消</button>
		  </div>
</ul>
</div>
</div>

</div>


</li>
</c:forEach>
	  </ul>
</div>

<hr />
<h2>请添加运费说明:<span>(提示：您可以设置发货时间、到货时间以及快递公司网址等内容，细致的说明有助于减少交易纠纷)</span></h2>
<div class="AddBox">
<textarea id="memo" name="distributionTemplateDescription" class="max-length-100" cols="80" rows="3" onfocus="clearMemo();" onblur="fillMemo();"></textarea>
</div>
<input type="submit" value="保存"/>
</div>
<div id="FreBottomBox"></div>
</div></div>
 </form>
</body>
</html>