<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/page/share/taglib.jsp" %>
<html>
<head>
<title>上传文件显示</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<jsp:include page="/location_check.jsp"></jsp:include>
<link rel="stylesheet" href="css/vip.css" type="text/css">

</head>

<body bgcolor="#FFFFFF" text="#000000" marginwidth="0" marginheight="0">
<html:form action="control/uploadfile/manage" method="post">

<input name="method" type="hidden" value="delete">
  <table width="98%" border="0" cellspacing="1" cellpadding="2" align="center">
    <tr ><td colspan="4" bgcolor="6f8ac4" align="right">
   <div class="pagesplit">  <%
  
String pageSplits = ConstantString.generatePageSplit(
		((PageModel)request.getAttribute("pm")).getTotal(),
		"control/uploadfile/manage.do?");
out.println(pageSplits);
%>  </div>
   </td></tr>
    <tr>
      <td width="8%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">选择</font></div></td>
      <td width="10%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">代号</font></div></td>
      <td width="60%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">文件</font></div></td>
	  <td width="22%" nowrap bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">上传时间</font></div></td>
    </tr>
<!---------------------------LOOP START------------------------------>
<c:forEach items="${pm.datas}" var="file">
    <tr>
      <td bgcolor="f5f5f5"> <div align="center"><INPUT TYPE="checkbox" NAME="id" value=${file.id}></div></td>
      <td bgcolor="f5f5f5"> <div align="center">${file.id}</div></td>
      <td bgcolor="f5f5f5"> <div align="center"><a href="<%=request.getContextPath() %>${file.fileUrl}" target="_blank">${file.fileUrl}</a></div></td>
	  <td bgcolor="f5f5f5"> <div align="center">${file.createDate}</div></td>
	</tr>
</c:forEach>
    <!----------------------LOOP END------------------------------->
    <tr>
      <td bgcolor="f5f5f5" colspan="4" align="center"><table width="100%" border="0" cellspacing="1" cellpadding="3">
          <tr> 
            <td width="10%"><INPUT TYPE="checkbox" NAME="all" onclick="javascript:allselect(this, this.form.id)">全选</td>
              <td width="85%">
              <input type="button" class="frm_btn" onClick="javascript:deleteFiles(this.form)" value=" 删 除 "> &nbsp;&nbsp;
              <input type="button" class="frm_btn" onClick="javascript:window.location.href='/control/uploadfile/manage.do?method=uploadInput'" value=" 上传文件"> &nbsp;&nbsp;
            </td>
          </tr>
        </table></td>
    </tr>
  </table>
</html:form>
</body>
</html>