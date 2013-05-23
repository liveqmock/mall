<%
String s="/product/manage.do";
s="/product/manage.do?"+request.getQueryString();
request.getRequestDispatcher(s).forward(request,response);
%>
