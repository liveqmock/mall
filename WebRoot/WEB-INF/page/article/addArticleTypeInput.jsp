<%@ page contentType="text/html;charset=utf-8" %>
<%@ include file="/WEB-INF/page/share/taglib.jsp" %>
<html>
<head>
<title>添加文章类型</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<jsp:include page="/location_check.jsp"></jsp:include>
<link rel="stylesheet" href="css/vip.css" type="text/css">
<script type="text/javascript" src="js/jscripts/fckeditor/fckeditor.js"></script>

</head>
<body bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<html:form action="control/center/article/manage" method="post" enctype="multipart/form-data"   onsubmit="return checkfm(this)">
<input type="hidden" name="method" value="addArticleType"/>
  <table width="90%" border="0" cellspacing="2" cellpadding="3" align="center">
    <tr bgcolor="6f8ac4"><td colspan="2"  > <font color="#FFFFFF"></font></td>
    </tr>
    <tr bgcolor="f5f5f5"> 
      <td width="22%" > <div align="right">文章类型名称</div></td>
      <td width="78%"> <input type="text" name="name" size="50" maxlength="50"/>
        <font color="#FF0000">*</font></td>
    </tr>
      <tr><td><div align="right">列表显示模板</div></td><td>
    <input type="text" name="templateUrl"/></td>
    </tr>
          <tr><td><div align="right">前台列表页面标题</div></td><td>
    <input type="text" name="title"/></td>
    </tr>
          <tr><td><div align="right">前台列表页面关键词</div></td><td>
    <input type="text" name="keywords"/></td>
    </tr>
          <tr><td><div align="right">前台列表页面描述</div></td><td>
    <input type="text" name="description"/></td>
    </tr>
    <tr bgcolor="f5f5f5"> 
      <td colspan="2"> <div align="center"> 
          <input type="submit" name="SYS_SET" value=" 确  定  " class="frm_btn">
         <input type="button" value=" 后  退   " class="frm_btn" onclick="javascript:history.back(-1)">
        </div></td>
    </tr>
  </table>
</html:form>
 
<br>
</body>
</html>