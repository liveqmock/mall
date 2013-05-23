<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/page/share/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<META http-equiv=Content-Language content=zh-CN>
<TITLE>评论列表</title> 
<%@ include file="/location_check.jsp" %>
<link rel="canonical" href="news"/>
<link href="css/style.css" rel="stylesheet" type="text/css">
<link href="css/global/header01.css" rel="stylesheet" type="text/css">
<link href="css/product/list.css" rel="stylesheet" type="text/css" />
<link href="css/global/topsell.css" rel="stylesheet" type="text/css">
<LINK href="css/new_cart.css" rel="stylesheet" type="text/css">

</head>
<body marginwidth="0" marginheight="0">
<jsp:include page="/WEB-INF/page/share/head.jsp"/>

     <div id="position">您现在的位置: &nbsp;<a href="" name="linkHome" title="回到首页" alt="AngelInTheBox首页">Angel In The Box</a>&nbsp;&gt;&gt;<a name="linkHome" href="news">&nbsp;新闻列表&nbsp;</a>

</div>
<table width="100%" cellspacing="1" cellpadding="2" border="0" align="center">
    <tbody>

<!---------------------------LOOP START------------------------------>
<c:forEach items="${comments.datas}" var="comment">
    <tr>
      <td bgcolor="#f5f5f5"> </td>
	  <td bgcolor="#f5f5f5"> </td>
	  <td bgcolor="#f5f5f5"> </td>	  
	  <td bgcolor="#f5f5f5"> <div align="center">  
	  ${comment.id }<br>
	 ${comment.commentObject }<br>
	${comment.commentObjectId}<br>
	${ comment.display}<br>
	<c:if test="${!empty comment.replis}">	
	<c:forEach items="${comment.replis}" var="reply">
		  ${reply.id }<br>
	 ${reply.commentObject }<br>
	${reply.commentObjectId}<br>
	${ reply.display}<br>
	${ reply.content}<br>
	</c:forEach>
	</c:if>
	<c:if test="${!empty comment.content}">
	${comment.content.content }
	</c:if>
	${ comment.ip}<br>
${	 comment.address}<br>
	${ comment.username}<br>
	${ comment.support}<br>
	${ comment.score}<br>

	${ comment.replyFromAdmin}<br>
	${ comment.replyAdminName}<br>
	 

	  </div></td>
</tr>
</c:forEach>
<tr><td bgcolor="#6f8ac4" align="right" colspan="4">
 总记录数:${comments.total}条  <div class="pagesplit">  <%
  
String pageSplits = ConstantString.generatePageSplit(
		((PageModel)request.getAttribute("comments")).getTotal(),
		"news?");
out.println(pageSplits);
%>  </div>
  <pg:pager items="${comments.total }" url="news" export="currentPageNumber=pageNumber">
	<font color="#FFFFFF">总记录数:${comments.total}条 |</font>
	<pg:first><a href="${pageUrl}&type=${frontArticleForm.type}">首页</a></pg:first>
	<pg:prev><a href="${pageUrl }&type=${frontArticleForm.type}">前页</a></pg:prev>
	<pg:pages>
		<c:choose>
		<c:when test="${ currentPageNumber eq pageNumber}">
		
		<font color="#FFFFFF">当前页:第</font><font color="red">${pageNumber}</font><font color="#FFFFFF">页  |</font>
		</c:when>
		<c:otherwise>
		<a href="${pageUrl }&type=Article">${pageNumber }</a>
		</c:otherwise>
		</c:choose>
	</pg:pages>
	<pg:next><a href="${pageUrl }&type=${frontArticleForm.type}">下页</a></pg:next>
	<pg:last><a href="${pageUrl }&type=${frontArticleForm.type}">尾页</a></pg:last>
</pg:pager> 
   </td></tr>
  </table>
 
</body>
</html>