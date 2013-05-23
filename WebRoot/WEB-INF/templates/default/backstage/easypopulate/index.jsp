<%@ page contentType="text/html;charset=utf-8" %>
<%@ include file="/WEB-INF/templates/default/share/taglib.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Easy Populate Page</title>
    
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script>
function checkfm(something){
//	if(something.type)
}
</script>
  </head>
  
  <body>
    请选择您的操作种类.(请谨慎操作批量表) <br>
<form action="control/product/easypopulate/manage.do?method=upload" method="post" enctype="multipart/form-data" onsubmit="return checkfm(this)">
   
    <input name="type" type="radio" value="category"/>产品分类
    <input name="type" type="radio" value="product"/>产品
    <input name="type" type="radio" value="image"/>图片
    <input name="type" type="radio" value="attribute"/>属性
    <input name="type" type="radio" value="quantity"/>库存
    <input name="type" type="radio" value="commend"/>分类页设置推荐产品
    <input name="type" type="radio" value="related"/>设置相关产品<br/>
    上传批量表文件:  <input type="file" name="file"><input type="submit" name="SYS_SET" value="上传" class="frm_btn">
    下载批量表文件：<input type="button" value="下载" onclick=""/>
</form>
  </body>
</html>
