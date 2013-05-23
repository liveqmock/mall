<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/page/share/taglib.jsp" %><%@page import="com.hnfealean.sport.pageset.PageModel"%><%@page import="com.hnfealean.sport.pageset.SystemContext"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<META http-equiv=Content-Language content=zh-CN>
<TITLE>评论列表</title> 
<%@ include file="/location_check.jsp" %>
<link href="css/style.css" rel="stylesheet" type="text/css">
<link href="css/global/header01.css" rel="stylesheet" type="text/css">
<link href="css/product/list.css" rel="stylesheet" type="text/css" />
<link href="css/global/topsell.css" rel="stylesheet" type="text/css">
<LINK href="css/new_cart.css" rel="stylesheet" type="text/css">

</head>
<body marginwidth="0" marginheight="0">
<style type="text/css">
textarea{width:600px}
</style>
<script>
function changeExpand(vid){
	var d = document.getElementById("commentdetail_"+vid);
	d.style.display=="none"?d.style.display="block":d.style.display="none";
	var s = document.getElementById("span_commentdetail_"+vid);
	s.innerHTML=="展开"?s.innerHTML="收起":s.innerHTML="展开";
}
function changeExpandAll(vname){
	var n =document.getElementsByName(vname);
	for(var i=0;i<n.length;i++){
		var d = n[i];
		d.style.display=="none"?d.style.display="inline":d.style.display="none";
	}
	var s  = document.getElementsByName("span_commentdetails");
	for(var i=0;i<s.length;i++){
	s[i].innerHTML=="展开全部"?s[i].innerHTML="收起全部":s[i].innerHTML="展开全部";
		}
}
function areUsure(){
return confirm('确定吗？')
	
}
</script>

<table width="100%" cellspacing="1" cellpadding="2" border="0" align="center">
    <tbody><tr><td colspan="12"><div class="pagesplit"><%

PageModel pm = (PageModel)request.getAttribute("comments");
Integer pageStr = (Integer)request.getAttribute("page");
int pageNow=0;
if(pageStr==null||pageStr==0){
	pageNow=1;
}else{
	pageNow=pageStr;
}
int total = pm.getTotal();
//out.println(pageNow);
String pageSplits = ConstantString.generatePageSplit(total,SystemContext.getPagesize(),pageNow,"/control/center/comment/manage.do?method=listComments&type="+request.getParameter("type")+"&");
out.println(pageSplits);

%> 
    </div>
    总共：${comments.total }条${pageNumber}</td></tr>
 <tr>
      <td bgcolor="#f5f5f5">ID</td><td bgcolor="#f5f5f5">评论对象</td><td bgcolor="#f5f5f5">评论对象ID </td><td bgcolor="#f5f5f5">是否显示 </td>
	  <td bgcolor="#f5f5f5">评论者IP</td><td bgcolor="#f5f5f5">评论者所在地 </td>	<td bgcolor="#f5f5f5">
	 评论者用户名 </td>	<td bgcolor="#f5f5f5">
	支持数  </td>	<td bgcolor="#f5f5f5">
	 	得分 </td><td bgcolor="#f5f5f5">操作</td>
	  </tr>
<!---------------------------LOOP START------------------------------>
<c:forEach items="${comments.datas}" var="comment">

    <tr>
      <td bgcolor="#f5f5f5">  ${comment.id }</td>
	  <td bgcolor="#f5f5f5">${comment.commentObject } </td>
	  <td bgcolor="#f5f5f5"> ${comment.commentObjectId}</td>	  
	  <td bgcolor="#f5f5f5"> <div align="center">  
	 
	${ comment.display}<br>
	<c:if test="${!empty comment.replis}">	
	<c:forEach items="${comment.replis}" var="reply">
		  ${reply.id }<br>
	 ${reply.commentObject }<br>
	${reply.commentObjectId}<br>
	${ reply.display}<br>
	${ reply.content}<br>
	</c:forEach>
	</c:if></td>	
	<td bgcolor="#f5f5f5">
	${ comment.ip}</td>	<td bgcolor="#f5f5f5">
	${comment.address}</td>	<td bgcolor="#f5f5f5">
	${ comment.username}</td>	<td bgcolor="#f5f5f5">
	${ comment.support}</td>	<td bgcolor="#f5f5f5">
	${ comment.score}</td>	<td bgcolor="#f5f5f5">

	<a href="javascript:void(0)" onclick="javascript:changeExpand('${comment.id}')"><span id="span_commentdetail_${comment.id}">展开</span></a>
		<a href="control/center/comment/manage.do?method=delete&id=${comment.id}&type=${type }" onclick="javascript:return areUsure()">删除</a>
	<a href="javascript:void(0)" onclick="javascript:changeExpandAll('commentdetails')" name="span_commentdetails">展开全部</a></td>	
	  </div></td>
</tr><tr><td colspan="12">
<div name="commentdetails" class="commentdetails" id="commentdetail_${comment.id}" style="display:none">
<c:if test="${!empty comment.content}">
	${comment.content.content }
	</c:if><br />
	回复：<textarea></textarea><input type="button" value="回复"/><br style="clear:both"/>
</div>
	</td></tr>
</c:forEach>
<tr></tr>
  </table>
 
</body>
</html>