<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/page/share/taglib.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML><HEAD>
<META http-equiv="pragma" content="no-cache">
<META http-equiv="Cache-Control" content="no-cache, must-revalidate">
<META http-equiv="Content-TYPE" content="text/html;charset=utf-8">
<%@ include file="/location_check.jsp" %>
<link rel="icon" href="images/favicon.ico" type="image/x-icon" /> 
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon" />	
<title>网站业务系统_管理平台</title>
<style type="text/css">
<!--
.main{
width:100%;
}
.main table{
width:100%;height:100%
}
.main table .l{
width:180px;
height:100%
}
.main table td{
vertical-align:top;width:100%
}
#left{float: left; width: 160px;height:100%;border:1px solid #99BBE8
}
#right{
float:left;width:100%;height:100%;border:1px solid #99BBE8;
}
.expand{
float:left;
width:3px;
height:100%;
background-color:#D2E0F2;
border:1px solid #98C0F4;
width:15px
}
.expandspanr1{
background:#98C0F4 url(/images/r1.jpg) repeat scroll 0 0;
width:14px
}
.expandspanr2{
background:#98C0F4 url(/images/r3.jpg) repeat scroll 0 0;
width:14px
}
.expandspanl1{
background:#98C0F4 url(/images/l1.jpg) repeat scroll 0 0;
width:14px
}
.expandspanl2{
background:#98C0F4 url(/images/l2.jpg) repeat scroll 0 0;
width:14px
}

//-->
</style>
<script type="text/javascript">
function changeBg(mm){
	return true;
	//alert(mm.className);
	if(mm.className=="expandspanl1"){
		mm.className = "expandspanl2";
		return true;
	}
	
	if(mm.className=="expandspanl2"){
		mm.className = "expandspanl1";
		return true;
	}

	if(mm.className=="expandspanr1"){
		mm.className = "expandspanr2";
		return true;
	}

	if(mm.className=="expandspanr2"){
		mm.className = "expandspanl1";
		return true;
	}

}
function change(mm){
	if(mm.className=="expandspanl2"||mm.className=="expandspanl1"){
		document.getElementById("left").style.width="0px";
		mm.className = "expandspanr1";
		return true;
	}
	if(mm.className=="expandspanr2"||mm.className=="expandspanr1"){
		document.getElementById("left").style.width="160px";
		mm.className = "expandspanl1";
		return true;
	}	
}
</script>
</head>

<frameset framespacing="0" border="0" rows="70,*,15" frameborder="0">

  <frame name="banner" scrolling="no" noresize target="contents" src="<html:rewrite action='/control/center/top'/>" marginwidth="0" marginheight="0">
    <frameset cols="195,*"> 
 <frame src="control/center/left.do" scrolling="no" noresize target="mainframe"/>
 <frame name="mainframe" scrolling="auto" noresize src="<html:rewrite action='/control/center/right'/>" marginwidth="0" marginheight="0">
  </frameset>
  <frame name="menuframe" target="mainframe" src="<html:rewrite action='/control/center/end'/>" scrolling="no" marginwidth="0" marginheight="0">

</frameset><noframes></noframes>

</html>