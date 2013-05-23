<%
String s="/frontarticle.do";
s="/frontarticle.do?"+request.getQueryString();
out.println(s);
%>
<jsp:forward page="<%=s%>"></jsp:forward>

<%
/*
if(request.getParameter("titleInPage4category")==null){
	s="/product/manage.do?"+request.getQueryString();
}else{
	s+="?"+request.getQueryString();
}*/
%>