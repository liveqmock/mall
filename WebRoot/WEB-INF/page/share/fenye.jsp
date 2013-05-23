<%@ page language="java" pageEncoding="GBK"%>
<%@ taglib prefix="pg"  uri="http://jsptags.com/tags/navigation/pager" %>


<pg:pager items="${pm.total }" url="producttype.do" export="currentPageNumber=pageNumber">
<pg:param name="parentId"/>
	<font color="#FFFFFF">总记录数:${pm.total}条 |</font>
	<pg:first><a href="${pageUrl}">首页</a></pg:first>
	<pg:prev><a href="${pageUrl }">前页</a></pg:prev>
	<pg:pages>
		<c:choose>
		<c:when test="${ currentPageNumber eq pageNumber}">
		
		<font color="#FFFFFF">当前页:第</font><font color="red">${pageNumber}</font><font color="#FFFFFF">页  |</font>
		</c:when>
		<c:otherwise>
		<a href="${pageUrl }">${pageNumber }</a>
		</c:otherwise>
		</c:choose>
	</pg:pages>
	<pg:next><a href="${pageUrl }">下页</a></pg:next>
	<pg:last><a href="${pageUrl }">尾页</a></pg:last>
</pg:pager>   
<font color="#FFFFFF">
    当前页:第${pageView.currentpage}页 | 总记录数:${pageView.totalrecord}条 | 每页显示:${pageView.maxresult}条 | 总页数:${pageView.totalpage}页</font>　
<c:forEach begin="${pageView.pageindex.startindex}" end="${pageView.pageindex.endindex}" var="wp">
    <c:if test="${pageView.currentpage==wp}"><b><font color="#FFFFFF">第${wp}页</font></b></c:if>
    <c:if test="${pageView.currentpage!=wp}"><a href="javascript:topage('${wp}')" class="a03">第${wp}页</a></c:if>
</c:forEach>      