<%@ 
page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"
%><%@ 
taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"
%>
<ul><c:forEach items="${recentproducts}" var="product"><li>
<div class="p-img">
<a href="${product.shtml_File_Name }">
<img src="${product.tempImageUrl}"></a>
</div>
<div class="p-name"><a href="${product.shtml_File_Name }">${product.name}</a></div><span class="clr"></span></li>
</c:forEach>
</ul>