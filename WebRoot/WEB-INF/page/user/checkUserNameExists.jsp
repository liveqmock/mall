<%@ page contentType="text/html;charset=GBK" %>
<%@ include file="/WEB-INF/page/share/taglib.jsp" %>

<html>
 <HEAD>
	<script language="JavaScript">
	<!--
	function setinfo(infocode){
		if (window.parent!=null){
			var infolabel = window.parent.document.getElementById("check_username_info");
			if (infocode=="isnull"){
				infolabel.innerHTML = "您的输入不正确，请重新输入！";
				infolabel.className = "ErrorMsg";
			}else if (infocode=="exist"){
				infolabel.innerHTML = "抱歉，该用户名已经被注册了";
				infolabel.className = "ErrorMsg";
			}else if (infocode=="noexist"){
				infolabel.innerHTML = "恭喜，该用户名可以使用";
				infolabel.className = "OkMsg";
			}
			var frameCheckForm = window.parent.document.getElementById("frameCheckForm");
			if(frameCheckForm) frameCheckForm.src="about:blank";
		}
	}
	//-->
	</script>
 </HEAD>

 <BODY onload="javascript:setinfo('${exists}')"></BODY>
</html>