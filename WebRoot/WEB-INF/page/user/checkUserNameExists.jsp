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
				infolabel.innerHTML = "�������벻��ȷ�����������룡";
				infolabel.className = "ErrorMsg";
			}else if (infocode=="exist"){
				infolabel.innerHTML = "��Ǹ�����û����Ѿ���ע����";
				infolabel.className = "ErrorMsg";
			}else if (infocode=="noexist"){
				infolabel.innerHTML = "��ϲ�����û�������ʹ��";
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