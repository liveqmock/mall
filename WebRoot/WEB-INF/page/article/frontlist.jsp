<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"
%><%@ include file="/WEB-INF/page/share/taglib.jsp" 
%><%@ taglib uri="http://www.opensymphony.com/oscache" prefix="cache" %><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<meta http-equiv="Content-Language" content="zh-CN"/>
<c:choose>
<c:when test="${!empty article}">
<title>${article.name}</title><meta name="keywords" content="${article.keywords}"/><meta name="description" content="${article.description}"/>
</c:when><c:otherwise>
<title>${type.title }</title><meta name="keywords" content="${type.keywords}"/><meta name="description" content="${type.description}"/>
</c:otherwise>
</c:choose>
<%@ include file="/location_check.jsp" %>
<link rel="canonical" href="news"/>
<link href="css/style.css" rel="stylesheet" type="text/css">
<link href="css/global/header01.css" rel="stylesheet" type="text/css">
<link href="css/product/list.css" rel="stylesheet" type="text/css" />
<link href="css/global/topsell.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="js/jscripts/fckeditor/fckeditor.js"></script>
</head>

<body marginwidth="0" marginheight="0">
<jsp:include page="/WEB-INF/page/share/head.jsp"/>

   <div id="position">您现在的位置: &nbsp;<a href="" name="linkHome" title="回到首页" alt="AngelInTheBox首页">Angel In The Box</a>&nbsp;&gt;<a name="linkHome" href="${type.name }">&nbsp;${type.title }&nbsp;</a>

<c:if test="${!empty breadcrumbs}">

<c:forEach items="${breadcrumbs}" var="art" varStatus="stu">
	&nbsp;&gt;<a href="${type.name }/${art.url }_a_${art.id}">${art.name }</a>
	<!--<c:if test="${stu.index<(fn:length(breadcrumbs)-1)}">
	&gt;&gt;
	</c:if>-->
</c:forEach>
</c:if>
</div>
<div class="main">

<div class="left">
<h2 class="${type.name }">${type.title }</h2>
<div class="side">
<c:forEach items="${tops}" var="article">
<h4>${article.name }</h4>
<ul>
<c:forEach items="${article.children}" var="child">
<li><a href="${type.name}<%=ConstantString.SEPERATORSLASH %>${child.url }_a_${child.id}">${child.name }</a></li>
</c:forEach>
</ul>
</c:forEach>
</div>
</div>
<div class="right">

<!---------------------------LOOP START------------------------------>
<c:choose>


<c:when test="${!empty article}">
<%-- 
<c:if test="${!empty pm.datas}">
<div class="brothers">
<ul>
<c:forEach items="${pm.datas}" var="article">
  <li> <a href="${article.type.name}<%=ConstantString.SEPERATORSLASH %>${article.url }_a_${article.id}">${article.name }</a></li>
  </c:forEach>
</ul>
</div>
</c:if>	
<c:if test="${!empty article.children}">
<div class="children">
<ul>
<li class="thisone">${article.name }</li>
	  <c:forEach items="${article.children}" var="child">  
	  
	  <li>·<a href="${article.type.name}<%=ConstantString.SEPERATORSLASH %>${child.url }_a_${child.id}">${child.name}</a></li>
	  
	  </c:forEach> 
</ul>
</div>
</c:if>	 --%>
<c:if test="${!empty article.content}">
<div class="thiscontent">
${article.content.content}
</div>
</c:if>
<c:if test="${article.commentPermission}">
<script type="text/javascript">
window.onload = function()
{
var oFCKeditor = new FCKeditor( 'content','700','200','Comment') ;
oFCKeditor.BasePath = "/js/jscripts/fckeditor/" ;
oFCKeditor.Config["CustomConfigurationsPath"] = "/js/jscripts/fckeditor/commentconfig.js"  ;
oFCKeditor.ReplaceTextarea() ;
updatecomment();
document.getElementById("returnto").value=window.location;
}
</script>
<div>评论：</div>
<div id="comments"></div>
<script language="JavaScript">
<!--
function quote(_floor){
var f = FCKeditorAPI.GetInstance('content');
//alert(f);  
var str = "&nbsp;<div class='quote'>";
//var insert=document.createElement("DIV");
//	insert.className="quote";
//	insert.id=33;
//	alert("50");  
	//insert.innerHTML = document.getElementById(_floor).innerHTML;
	//insert.innerHTML = document.getElementById(_floor).innerHTML+"<span class=\"end\"></span>";
str=str+document.getElementById(_floor).innerHTML+"<\/div><span class='end'><!--"+_floor.replace(/[a-zA-Z]+/,"")+"-"+"-><\/span>&nbsp;";
	//alert(str);
	//alert(insert.innerHTML);
//var m = document.getElementById(_floor).cloneNode(true);
f.InsertHtml(str);
//alert("ok");
}
function StringBuffer(){
this.strings = new Array;
}
StringBuffer.prototype.append = function(str){
this.strings.push(str);
};
StringBuffer.prototype.toString = function(){
return this.strings.join("<br>");
};
StringBuffer.prototype.length =function(){
return this.strings.length;
}
if (typeof hnfealean != "object" || hnfealean == null) {
    hnfealean = {}
}
hnfealean.comment = {};
hnfealean.comment =function(s){
var comment = eval(s);
var sugstr=new StringBuffer;
//alert(sugg[1].length);alert(sugstr.length());
//if(sugg[1].length==10&&sugstr.length()<30){
for(var i=0;i<comment.length-1;i++){
	sugstr.append(
	"<div id='floor"+comment[i][0]+"' class='floor'><span class='user'>"+comment[i][1]+"</span><span class='ip'>"
	+comment[i][2]+"</span><span class='address'>"+comment[i][3]+"</span><span class='date'>"+comment[i][5]
	+"</span><div class='content'>"+comment[i][4]
	+"</div></div><div class=\"bar\">"
	+"<span class=\"quotespan\"><a href=\"javascript:(void(0))\" onclick=\"quote('floor"+comment[i][0]+"');"
	+"location.hash='#commentform';return false\">回复</a></span>"
	+"<span class='support' href=\"javascript:(void(0))\">支持</span>"
	+"</div>"
	);
//alert(sugstr);
}
//alert(comment);
//alert(comment.length);


document.getElementById("comments").innerHTML=sugstr;
}
function updatecomment(){
	C=document.createElement("SCRIPT");
	C.src="comment.do?method=getLatestComments&oid=${article.id}&type=${article.type.id}";
	C.charset="gb2312";
	//C.id="script"+id;
	document.body.appendChild(C);
	//alert(C.src);
	//window.open(C.src);
	//document.getElementById("updatebutton"+id).innerHTML+=
}
//-->
</script>
<script type="text/javascript">
function f(){
//alert("31");
//alert(FCKeditorAPI.Instances);
 var oEditor = FCKeditorAPI.GetInstance('content');  
alert(oEditor.GetData());
var p = document.getElementById('Right');
		oEditor.InsertElement(p);
return;
        var doc,inner;

        if (document.all){//IE
                doc = document.frames[0].document;
        }else{//Firefox    
                doc = document.getElementById("content___Frame").contentDocument;
        }
	//alert(doc.getElementById("xEditingArea").innerHTML);
		if (document.all){//IE
			alert("IE");
			alert(doc.frames[0]);
            inner = doc.frames[0].document;
			
        }else{//Firefox    
				alert("firefox");
				//var no = doc.getElementById("xEditingArea").firstChild;
				//alert(no.getAttribute(""));
				//alert(doc.document.body.innerHTML);
                inner = doc.getElementsByTagName("iframe")[0].contentDocument;//
				//alert(inner[0].contentDocument);
				//alert(inner.length);//.contentDocument;
        }
		//alert(inner.body.innerHTML);
		inner.body.innerHTML="hhhhh";
        //doc.getElementById("s").style.color="blue";
}
function check(){
var s = document.getElementById("commentcontent").value;
var f = FCKeditorAPI.GetInstance('content');  
alert(f.GetData());
//var m = document.getElementById(_floor).cloneNode(true);
alert(document.getElementById("commentcontent").value);
alert(document.getElementById("commentcontent").value);
}
</script>
<!--<input type="button" onclick="f()">-->
	 <form action="comment.do" method="post" id="commentform">
	 <input type="hidden" name="method" value="add" />
	 <input type="hidden" name="type" value="${article.type.id}"/>
	 <input type="hidden" name="oid" value="${article.id}" />
	 <input type="hidden" name="returnTo" value="" id="returnto"/>
	 <textarea name="content"> </textarea>
	 <input type="submit" value="发表评论"/> 
	 </form>
	 </c:if>
</c:when><%-- 
 <c:otherwise>
 <div class="listall">
 <c:forEach items="${pm.datas}" var="article">
    <div class="topart">
	<a href="${article.type.name}<%=ConstantString.SEPERATORSLASH %>${article.url }_a_${article.id}">${article.name }</a></div>
<c:if test="${!empty article.children}">
	  <div class="chart"> <c:forEach items="${article.children}" var="child">  <li><a href="${article.type.name}<%=ConstantString.SEPERATORSLASH %>${child.url }_a_${child.id}">${child.name}</a></li></c:forEach> 
	  <br class="clear"/>
	  </div>
</c:if>	
</c:forEach>
 </div>
 </c:otherwise>--%>
</c:choose>
</div>
</div>
<div class="clearboth">
<jsp:include page="/WEB-INF/page/share/foot.jsp"/>
</div>

</body>
</html>