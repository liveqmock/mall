<%@ 
page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"
%><%@ 
include file="/WEB-INF/page/share/taglib.jsp" 
%><%@ page import="java.util.*,java.text.*,java.lang.StringBuffer,com.hnfealean.sport.model.product.Product,com.hnfealean.sport.model.product.AttributeOption,com.hnfealean.sport.web.MD5" 
%><%--
由于Action中(FrontProductAction)开启了缓存工具类，并且，设置了对象属性的lazy为true。
开启缓存工具类的目的在于：使得前台查询同一对象的sql结果重复利用，提升性能
设置对象属性lazy为true的目的在于：对象属性lazy设为false时，前台jsp页面的确不会出现session关闭取不到对象的异常，
但是这种方式的效率问题在于，所有查找主体的语句，都会派生出查找其对象属性的sql，对于前台效率没有问题，因为已经做了缓存
但是后台，查询Product对象的次数频繁，产品列表，产品信息修改，等等，次数很多。这样的话后台的查询又会出现极大的性能问题
引入缓存工具类的目的在于减少性能开销，因此，我们需要再进一步处理。
处理方法：将页面需要的对象属性查询并缓存起来，设置为request范围的属性，避免jsp的对象.对象属性的操作
因此，在此jsp页面直接取其对象属性
brand：品牌						Brand
category：类别					Category
imagesAndStyles：产品图片样式	ImagesAndStyle
manufacturers:厂商				Manufacturer
options：属性值					AttributeOption
sizes:	尺寸						Size
groups：详细信息组				ProductDetailGroup
distributionTemplate：配送运费模板		DistributionTemplate
会出现session已关闭，不能得到对象的异常
	
--%><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<title>${product.name}</title>
<meta name="Keywords" content="${product.meta_KeyWords}">
<META name="description" content="${product.meta_Description}">
<%@ include file="/location_check.jsp" %>
<!-- 
<link href="/css/global/header01.css" rel="stylesheet" type="text/css">
<link href="/css/product/list.css" rel="stylesheet" type="text/css" />
<link href="/css/global/topsell.css" rel="stylesheet" type="text/css">
<link href="/css/global/topcommend.css" rel="stylesheet" type="text/css"> -->
<script type="text/javascript" src="highslide/highslide-with-gallery.js"></script> 
<link rel="stylesheet" type="text/css" href="highslide/highslide.css" />
</head>

<body>
<jsp:include page="/WEB-INF/page/share/head.jsp"/>
<script type="text/javascript"> 
	hs.graphicsDir = 'highslide/graphics/';
	hs.align = 'center';
	hs.transitions = ['expand', 'crossfade'];
	hs.outlineType = 'rounded-white';
	hs.wrapperClassName = 'controls-in-heading';
	hs.fadeInOut = true;
	//hs.dimmingOpacity = 0.75;
 
	// Add the controlbar
	if (hs.addSlideshow) hs.addSlideshow({
		//slideshowGroup: 'group1',
		interval: 5000,
		repeat: false,
		useControls: true,
		fixedControls: false,
		overlayOptions: {
			opacity: 1,
			position: 'top right',
			hideOnMouseOut: false
		}
	});
function changeUrl(target){
	document.getElementById('main').src=target.src.substring(target.src.indexOf('images/'));
}	
function iniImage(){
if(document.getElementById('main').src==""){
document.getElementById('main').src=document.getElementsByName('toLarge')[0].href;
}
}
</script> 

   
        <div class="w position">您现在的位置: &nbsp;<a href="" name="linkHome" title="回到首页" alt="AngelInTheBox首页">Angel In The Box</a>  

   <c:set var="out" value=""/>
   <c:forEach items="${position}" var="category" varStatus="statu">
  <c:set var="out" value="&nbsp;&gt;&gt;<a href='${category.url}'>${category.name }</a>${out}"/>
   </c:forEach>
    ${out }&nbsp;&gt;&gt;<a href="${product.shtml_File_Name}" title="${product.name}">${product.name}</a>
	</div>
	<div class="w"><!-- 页面主体 -->
	 <div class="left">
 <c:if test="${!empty position[0]}">
 <div id="sortlist" class="m">
<div class="mt">
<h2>相关分类</h2>
</div>
<div class="mc">
<ul>
<c:forEach items="${sameLevelCategories}" var="cat">
<li><a title="${cat.name }" href="${cat.url}">${cat.name }</a></li>
</c:forEach>
</ul>
</div>
</div></c:if>
<div id="recent" class="m" load="true">
	      <div class="mt">最近浏览过的商品</div>
	      <div class="mc" id="recentHistory">
	      
	      </div><script>getHistory(${product.id});</script>
</div>
<div id="recommend" class="m" load="true">
	      <div class="mt">精品推荐</div>
	      <div class="mc">
	      </div>
</div>

 </div>
 <div class="right m_cnt">
 <div class="product-name">
<h1>${product.name}</h1>
</div>

<div class="productdetail">
<form action="<html:rewrite action="user/shoppingcart"/>" method="post" name="cart">
 <div class="product-images">
<div class="main-image">
<c:forEach items="${images}" var="image" begin="0" end="1">
		<img class="productImage" id="main" src="${image.imageUrl}"/>	
</c:forEach>
</div>
<div class="min-images highslide-gallery">
<c:forEach items="${images}" var="image">
<a name='toLarge' title="${product.name}--${image.name}" class="highslide productImage" onclick="return hs.expand(this)" href='/${image.imageUrl}'>
<img name="min-image" onmouseover="javascript:changeUrl(this)" src="${image.imageUrl}" class="productImage"/></a>
		<div class="highslide-heading">${product.name}--${image.name}</div>
							
		<input type="hidden" name="imagesAndStyleId" value="${image.id}"/>  
		
</c:forEach>
</div>

<%
List attributeList = new ArrayList();
List<AttributeOption> options =(List<AttributeOption>)request.getAttribute("attributeoptions");
for(AttributeOption option:options)
if(!attributeList.contains(option.getAttribute().getName())){
	attributeList.add(option.getAttribute().getName());
}
request.setAttribute("attributeList",attributeList);
%>
<c:forEach items="${attributeList}" var="att">
	${att }<select name="option"><option>请选择</option>
	<c:forEach items="${attributeoptions}" var="option">
	
	<c:if test="${option.attribute.name == att}">
	<option value="${option.id}">${option.value}</option>
	</c:if>
	
	</c:forEach>
	</select><br/><br/>
</c:forEach>					
</div>							


<input type="hidden" name="method" value="add"/> 
<input type="hidden" name="productId" value="${product.id}"/> 
<input type="hidden" name="sizeId" value="1"/>
<div class="product-info">
<ul>
<li>市场价：<s>${product.marketPrice}</s> 元 </li>
<li>本站价：<span class="product-price"><b>${product.sellPrice} </b></span>元	</li>
<li class="li2">商品编号：A${product.id}<font color="#CC0000"></font></li>

<c:if test="${!empty brand}"><li>品牌：${brand.name}</li> </c:if>
<c:if test="${!empty position}"><li>类别：
<a title='${position[0].name}' class='position' href='${position[0].url}'>${position[0].name}</a>
</li> </c:if>
<li>加入购物车： <input type="text" size="4" maxlength="6" value="1" name="amount" value="1"></li>
<li class="right_img"><INPUT TYPE="image" SRC="images/global/sale.gif"></li>

</ul>
</div>									
								
					
</form>
<div class='product-description'>${product.detail}</div>
<div class="detail">
<ul class="tab">
<c:forEach items="${detailgroups}" var="group" varStatus="stu">
<li id="two${stu.index+1}" onclick="setTab('two',${stu.index+1},${fn:length(detailgroups)})" <c:if test="${stu.index==0}">class="hover"</c:if>><a>${group.name}</a></li>
</c:forEach>
</ul>
<div id="content">
<c:forEach items="${detailgroups}" var="group" varStatus="stu">
<div class="show" id="con_two_${stu.index+1}">
      <c:if test="${empty group.post}">
      <table cellspacing="1" cellpadding="0" border="0" width="100%" class="Ptable"><tbody>
      <c:forEach items="${group.detailAttributes}" var="attribute">

<tr><th colspan="2" class="tdTitle">${attribute.name }</th></tr>
 <c:forEach items="${attribute.options}" var="option">
<tr><td class="tdTitle">${option.name }</td><td>${option.value}</td></tr>
</c:forEach>

</c:forEach>


</tbody></table>
      </c:if>
      <c:if test="${!empty group.post}">
      ${group.post}
      </c:if>
</div>
</c:forEach>
</div>
</div>
<script type="text/javascript">
window.onload = function()
{
//var oFCKeditor = new FCKeditor( 'content','700','200','Comment') ;
//oFCKeditor.BasePath = "js/jscripts/fckeditor/" ;
//oFCKeditor.Config["CustomConfigurationsPath"] = "js/jscripts/fckeditor/commentconfig.js"  ;
//oFCKeditor.ReplaceTextarea() ;
iniImage();
}
</script>
 </div> </div><!-- 页面主体 右边 end -->

</div><!-- 页面主体 end -->

<jsp:include page="/WEB-INF/page/share/foot.jsp"/>
</body>
</html>