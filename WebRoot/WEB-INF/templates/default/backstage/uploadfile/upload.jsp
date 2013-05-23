<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/templates/default/share/taglib.jsp" %>
<html>
<head>
<title>文件上传</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<jsp:include page="/WEB-INF/templates/default/share/backstage_location_check.jsp"></jsp:include>
<link rel="stylesheet" href="css/vip.css" type="text/css">

<script language="JavaScript">
function checkfm(form){
	var uploadfile = form.uploadFile.value;
	if(uploadfile!=""){
		var types = ["jpg","gif","bmp","png","exe","doc","pdf","txt","xls","ppt","swf"];
		var ext = uploadfile.substring(uploadfile.length-3).toLowerCase();
		var sing = false;
		for(var i=0; i<types.length;i++){
			if (ext==types[i]){
				sing = true; 
			}
		}
		if(!sing){
			alert("只允许上传图片/flash动画/word文件/exe文件/pdf文件/TxT文件/xls文件/ppt文件");
			return false;
		}		
	}
	return true;
}
</script>
</head>
<body bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<html:form action="control/uploadfile/manage" method="post" enctype="multipart/form-data" onsubmit="return checkfm(this)">
<input type="hidden" name="method" value="upload">
  <table width="90%" border="0" cellspacing="2" cellpadding="3" align="center">
    <tr bgcolor="6f8ac4"><td colspan="2"  > <font color="#FFFFFF">上传文件：</font></td>
    </tr>
	<tr bgcolor="f5f5f5"> 
      <td width="22%" > <div align="right">文件：</div></td>
      <td width="78%"> <input type="file" name="uploadFile" size="50"><br/>
      只允许上传图片/flash动画/word文件/exe文件/pdf文件/TxT文件/xls文件/ppt文件
      </td>
    </tr>
    <tr bgcolor="f5f5f5"> 
      <td colspan="2"> <div align="center"> 
          <input type="submit" name="SYS_SET" value=" 确 定 " class="frm_btn" >&nbsp;&nbsp;&nbsp;
          <input type="button" name="SYS_SET" value=" 返回 " class="frm_btn" onclick="javascript:history.back()">
        </div></td>
    </tr>
  </table>
</html:form>
<br>
</body>
</html>