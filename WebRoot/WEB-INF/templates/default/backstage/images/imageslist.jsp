<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"
%><%@ include file="/WEB-INF/templates/default/share/taglib.jsp" %><%@page import="com.hnfealean.sport.pageset.PageModel"%><%@page import="com.hnfealean.sport.pageset.SystemContext"
%><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<META http-equiv=Content-Language content=zh-CN>
<TITLE>文件列表</title> 
<%@ include file="/WEB-INF/templates/default/share/backstage_location_check.jsp" %>
<link href="css/style.css" rel="stylesheet" type="text/css">
<link href="css/global/header01.css" rel="stylesheet" type="text/css">
<link href="css/product/list.css" rel="stylesheet" type="text/css" />
<link href="css/global/topsell.css" rel="stylesheet" type="text/css">
<LINK href="css/new_cart.css" rel="stylesheet" type="text/css">

<script>
function returnParent(){
var path = "${filePath}";
if(path.substring(path.length-1)=="/")
	path = path.substring(0,path.length-1);
path=path.substring(0,path.lastIndexOf("/")+1);
//alert(path);
//return "control/center/images/manage.do?filePath="+path;
window.location.href="control/center/images/manage.do?filePath="+path;
}
function displayImage(filePath,m,buttonV){
	//alert('here');
	//var image=document.createElement("IMG");
	var image =  document.getElementById("image_"+m);
	image.src="images"+filePath;
	image.style.display=='none'?image.style.display='block':image.style.display='none';
	image.className='sourceImage';
	
	//alert(C.src);
	//alert(m.parentNode.parentNode.childNodes.length);
	//m.parentNode.parentNode.childNodes[1].appendChild(C);
	//m.parentNode.appendChild(image);
	//var container = document.getElementById("images_"+m);
	//container.appendChild(image);
	
	//disDetail(image);
	document.getElementById("image_width_"+m).value=image.width;
	document.getElementById("image_height_"+m).value=image.height;
	document.getElementById("image_url_"+m).value=image.src;
	buttonV.value=='显示'?buttonV.value='隐藏':buttonV.value='显示';
}
function disDetail(image){
	if(image.height==0&&image.width==0){
		alert("无法读取图片");
	}else{
		var s="原图尺寸:<br>高度:"+image.height+"<br>宽度:"+image.width;
		alert(s);
		return;
	}
}
</script>
</head>
<body>
<table class='imagemodule'><tr><td>
<img src="images/folder_edit.gif" /><a href="javascript:returnParent()" title="返回上一层">返回上一层...</a></td><td>&nbsp;</td><td>&nbsp;</td><td>详细信息</td>
</tr><c:forEach items="${list}" var="file"
><c:if test="${file.isDirectory==true}"
><tr><td><img src="images/folder_edit.gif" /><a href="control/center/images/manage.do?filePath=${filePath}${file.name}">${file.name}</a></td><td>&nbsp;</td><td>&nbsp;</td></tr>
</c:if></c:forEach><c:forEach items="${list}" var="file"  varStatus="status"><c:if test="${file.isDirectory==false}"><tr><td>
<a href="control/center/images/manage.do?method=convertSize&filePath=${filePath}${file.name}">${file.name}</a></td>

<td><input type='button' value='显示' onclick="displayImage('${filePath}${file.name}',${status.count},this)"/><!-- <a onclick="" href="javascript:void(0)">显示全部</a></td> -->
<td><div id="images_${status.count}">
<img src="" style="display:none" id="image_${status.count}"/>
</div></td>
<td class='detailtd'>
高度：<input class='noborder' type="text" id="image_height_${status.count}"/>&nbsp;宽度：<input class='noborder' type="text" id="image_width_${status.count}"/><br/>
绝对地址:<input class='noborder' type="text" id="image_url_${status.count}" size=65/></td>
</tr></c:if></c:forEach>
<tr><td>
<img src="images/folder_edit.gif" /><a href="javascript:returnParent()" title="返回上一层">返回上一层...</a></td><td>&nbsp;</td><td>&nbsp;</td></tr>
 </table>
</body>
</html>