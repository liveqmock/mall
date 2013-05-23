<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/page/share/taglib.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML><HEAD>
<META http-equiv="pragma" content="no-cache">
<META http-equiv="Cache-Control" content="no-cache, must-revalidate">
<META http-equiv="Content-TYPE" content="text/html;charset=utf-8">
<%@ include file="/location_check.jsp" %>
<link href="css/global/address.css" rel="stylesheet" type="text/css">
<link href="css/global/bottom.css" rel="stylesheet" type="text/css">
<TITLE>菜单列表</TITLE>
<style type="text/css"> 
.current a{
width:80%;
}
.current {
padding-left:20%;
height:auto;
width:80%;
}
*{margin:0;padding:0;} 
/*body{background:#6386DE none repeat scroll 0 0}*/
.menu{font-size:12px;position:relative;z-index:100;width:98%;margin:auto;background:#DFE8F6 none repeat scroll 0 0;height:100%;

} 
.displayNone{
display:none;padding-left:20%;
height:auto;
}
.menu a{color:#ffffff;
display:block;border:1px solid #aaa;background:#6F8DE0 none repeat scroll 0 0;padding:2px 10px;margin:3px;text-decoration:none;} 
.menu .current a:hover{background:#6F8DE0;color:#ffffff;border:1px solid #339900;} 
.menu ul ul{} 
.menu ul ul li {clear:both;text-align:left;font-size:12px;} 
.menu ul ul li a{display:block;width:160px;height:15px;margin:0;border:0;border-bottom:1px solid #6F8DE0;} 
.menu ul ul li a:hover{border:0;background:#fafafa;border-bottom:1px solid #fff;} 

</style> 
<script type="text/javascript">
function change_option(){
	var menuItems = document.getElementsByName("menuItems");
	//alert(menuItems.length);
	 for (var i = 0; i < menuItems.length; i++) {
		 
		 menuItems[i].onmouseover=show;
		 menuItems[i].onmouseout=hidden;
	      //document.getElementById('content' + i).className = '';
	     // document.getElementById('content' + i).style.display = 'none';
	 }
	  //document.getElementById('content' + index).className = 'current';
	 // document.getElementById('content' + index).style.display = 'block';
	}
	//-->
	function hidden(){
		//var menuItems = document.getElementsByName("menuItems");
		//alert(menuItems[i].childNodes.length);
		cleanWhiteSpace(this);
		this.childNodes[1].style.display='none';

	}	
	function show(){
		//var menuItems = document.getElementsByName("menuItems");
		//alert(menuItems[i].childNodes.length);
		cleanWhiteSpace(this);
		this.childNodes[1].style.display='block';
		this.childNodes[1].style.className = 'current';
	}
function cleanWhiteSpace(element){
	for(var i=0;i<element.childNodes.length;i++){
		var node= element.childNodes[i];
		if(node.nodeType==3 && !/\S/.test(node.nodeValue))
			node.parentNode.removeChild(node);
	}
}
</script>
</head>
<body onload="change_option()"> 




<div class="menu"> 

<div name="menuItems"><a>产品管理</a>
<div class="displayNone">
<a target="mainframe" href="<html:rewrite action='control/product/category/manage'/>">产品类别管理</a>
<a target="mainframe" href="<html:rewrite action='control/product/brand/manage'/>">产品品牌管理</a>
<a target="mainframe" href="<html:rewrite action='control/product/product/manage'/>">产品管理</a>
<a target="mainframe" href="<html:rewrite action='control/product/post/manage'/>">产品评论管理</a>
</div> 
</div>
<div name="menuItems"><a>文件管理</a>
<div class="displayNone">
<a target="mainframe" href="<html:rewrite action='control/uploadfile/manage'/>">上传文件管理</a>
<a target="mainframe" href="<html:rewrite action='control/uploadfile/manage'/>">超强文件管理器</a>
</div>
</div> 
<div name="menuItems"><a>订单管理</a>
<div class="displayNone">
<a target="mainframe" href="control/shopping/ordermanage.do">待审核订单</a>
</div>
</div> 
<div name="menuItems"><a>权限管理</a>
<div class="displayNone">
<a target="mainframe" href="control/center/module/manage.do">模块管理</a>
<a target="mainframe" href="control/center/role/manage.do">角色管理</a>
<!-- <a target="mainframe" href="control/center/role-acl/manage.do">角色权限设置</a> -->
<a target="mainframe" href="control/center/administrator/manage.do">网站管理员</a>
<!-- <a target="mainframe" href="control/center/administrator-acl/manage.do">管理员权限设置</a> -->
</div>
</div> 
<div name="menuItems"><a>销售管理</a>
<div class="displayNone">
<a target="mainframe" href="control/center/module/manage.do">销售管理</a>
<a target="mainframe" href="control/center/module/manage.do">销售统计</a>
</div>
</div>
<div name="menuItems"><a>库存管理</a>
<div class="displayNone">
<a target="mainframe" href="control/center/module/manage.do">低库存报告</a>
<a target="mainframe" href="control/center/module/manage.do">库存查看</a>
<a target="mainframe" href="control/center/module/manage.do">库存更新</a>
</div>
</div>
<div name="menuItems"><a>模板管理</a>
<div class="displayNone">
<a target="mainframe" href="control/center/module/manage.do">分类页模板管理</a>
<a target="mainframe" href="control/center/module/manage.do">产品页模板管理</a>
<a target="mainframe" href="control/center/module/manage.do">新闻模板管理</a>
<a target="mainframe" href="control/center/module/manage.do">首页模板管理</a>
</div>
</div> 
<div name="menuItems"><a>日志管理</a>
<div class="displayNone">
<a target="mainframe" href="control/center/module/manage.do">访问日志</a>
<a target="mainframe" href="control/center/module/manage.do">购物日志</a>
<!-- 使用quartz进行每日统计 -->
</div>
</div> 
<div name="menuItems"><a>系统设置</a>
<div class="displayNone">
<a target="mainframe" href="control/center/country/manage.do">国家信息</a>
<a target="mainframe" href="control/center/zone/manage.do">地区信息</a>
<a target="mainframe" href="control/center/module/manage.do">运费设置</a>
<a target="mainframe" href="control/center/module/manage.do">信用卡管理</a>
<a target="mainframe" href="control/center/module/manage.do">代金券管理</a>
</div>
</div> 
<div name="menuItems"><a>资讯管理</a>
<div class="displayNone">
<a target="mainframe" href="control/center/article/manage.do?type=0">新闻管理</a>
<a target="mainframe" href="control/center/article/manage.do?type=1">博客管理</a>
<a target="mainframe" href="control/center/module/manage.do">新闻评论管理</a>
</div>
</div> 
<div name="menuItems"><a>代金券管理</a>
<div class="displayNone">
<a target="mainframe" href="control/center/article/manage.do?type=0">私有代金券管理</a>
<a target="mainframe" href="control/center/article/manage.do?type=1">公有代金券管理</a>

</div>

</div>
<div name="menuItems"><a>客户管理</a>
<div class="displayNone">
<a target="mainframe" href="control/center/article/manage.do?type=0">客户信息管理</a>
<a target="mainframe" href="control/center/article/manage.do?type=1">转化率管理</a>

</div>
</div>  
</div>

</body>
</html>
