<%@ 
taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"
%><%@ 
taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"
%><%@ 
taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"
%><%@ 
taglib prefix="html"  uri="http://struts.apache.org/tags-html" 
%><%
/*
String Suffix;
if(request.getAttribute("currentCategory")==null||request.getAttribute("currentCategory").toString().trim().length()==0){
	Suffix="";
}else{
	Suffix="-wholesale/";
}
request.setAttribute("Suffix",Suffix);
String suffix4ChildCategory="-wholesale/";
request.setAttribute("suffix4ChildCategory",suffix4ChildCategory);
*/
%><%@
page import="com.hnfealean.sport.enums.ConstantString,com.hnfealean.sport.web.WebProperty"
%><%@page import="com.hnfealean.sport.pageset.PageModel"%>