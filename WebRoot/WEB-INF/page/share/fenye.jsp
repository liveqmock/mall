<%@ page language="java" pageEncoding="GBK"%>
<%@ taglib prefix="pg"  uri="http://jsptags.com/tags/navigation/pager" %>


<pg:pager items="${pm.total }" url="producttype.do" export="currentPageNumber=pageNumber">
<pg:param name="parentId"/>
	<font color="#FFFFFF">�ܼ�¼��:${pm.total}�� |</font>
	<pg:first><a href="${pageUrl}">��ҳ</a></pg:first>
	<pg:prev><a href="${pageUrl }">ǰҳ</a></pg:prev>
	<pg:pages>
		<c:choose>
		<c:when test="${ currentPageNumber eq pageNumber}">
		
		<font color="#FFFFFF">��ǰҳ:��</font><font color="red">${pageNumber}</font><font color="#FFFFFF">ҳ  |</font>
		</c:when>
		<c:otherwise>
		<a href="${pageUrl }">${pageNumber }</a>
		</c:otherwise>
		</c:choose>
	</pg:pages>
	<pg:next><a href="${pageUrl }">��ҳ</a></pg:next>
	<pg:last><a href="${pageUrl }">βҳ</a></pg:last>
</pg:pager>   
<font color="#FFFFFF">
    ��ǰҳ:��${pageView.currentpage}ҳ | �ܼ�¼��:${pageView.totalrecord}�� | ÿҳ��ʾ:${pageView.maxresult}�� | ��ҳ��:${pageView.totalpage}ҳ</font>��
<c:forEach begin="${pageView.pageindex.startindex}" end="${pageView.pageindex.endindex}" var="wp">
    <c:if test="${pageView.currentpage==wp}"><b><font color="#FFFFFF">��${wp}ҳ</font></b></c:if>
    <c:if test="${pageView.currentpage!=wp}"><a href="javascript:topage('${wp}')" class="a03">��${wp}ҳ</a></c:if>
</c:forEach>      