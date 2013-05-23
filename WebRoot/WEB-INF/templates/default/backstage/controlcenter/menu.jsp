<%@ page language="java" contentType="text/html;charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/templates/default/share/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh-CN" lang="zh-CN">
<head><meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
	
<%@ include file="/WEB-INF/templates/default/share/backstage_location_check.jsp"%>

		<TITLE>菜单列表</TITLE>
	<link href="css/templates/default/backstage/default.css" media="screen" rel="stylesheet" type="text/css" />
<style type="text/css">.AccordionPanelOpen .AccordionPanelContent {
	height: 120px
}
.column.left {
padding-left:3px;
}
.column .ttitle{
padding-bottom:0
}

</style>
	
	</head>
	<body>

		<div class="column left">
<span class="ttitle">管理系统菜单列表</span>
			<div class="box">
				<div class="box_1">
					<div tabindex="0" class="Accordion" id="Accordion1">
						<div class="AccordionPanel AccordionPanelClosed">
							<div class="AccordionPanelTab">
								产品管理
							</div>
							<div class="AccordionPanelContent">
								<ul>
									<li>
										<a target="mainframe"
											href="<html:rewrite action='control/product/category/manage'/>">产品类别管理</a>
									</li>
									<li>
										<a target="mainframe"
											href="<html:rewrite action='control/product/brand/manage'/>">产品品牌管理</a>
									</li>
									<li>
										<a target="mainframe"
											href="<html:rewrite action='control/product/product/manage'/>">产品管理</a>
									</li>									<li>
										<a target="mainframe"
											href="<html:rewrite action='control/product/attribute/manage'/>">产品属性管理</a>
									</li>
									<li>
										<a target="mainframe"
											href="<html:rewrite action='control/product/categoryattribute/manage'/>">产品类别属性管理</a>
									</li>
								</ul>
							</div>
						</div>
						<div class="AccordionPanel AccordionPanelClosed">
							<div class="AccordionPanelTab">
								批量表操作
							</div>
							<div class="AccordionPanelContent" style="height: 0px;">
								<ul>
									<li>
										<a target="mainframe"
											href="<html:rewrite action='control/product/easypopulate/manage'/>">批量表管理</a>
									</li>
								</ul>
							</div>
						</div>
						<div class="AccordionPanel AccordionPanelClosed">
							<div class="AccordionPanelTab">
								文件管理
							</div>
							<div class="AccordionPanelContent" style="height: 0px;">
								<ul>
									<li>
										<a target="mainframe"
											href="<html:rewrite action='control/uploadfile/manage'/>">上传文件管理</a>
									</li>
									<li>
										<a target="mainframe"
											href="<html:rewrite action='control/center/images/manage'/>">图片文件管理器</a>
									</li>
								</ul>
							</div>
						</div>
						<div class="AccordionPanel AccordionPanelClosed">
							<div class="AccordionPanelTab">
								订单管理
							</div>
							<div class="AccordionPanelContent" style="height: 0px;">
								<ul>
									<li>
										<a target="mainframe" href="control/shopping/ordermanage.do">待审核订单</a>
									</li>
								</ul>
							</div>
						</div>

						<div class="AccordionPanel AccordionPanelClosed">
							<div class="AccordionPanelTab">
								权限管理
							</div>
							<div class="AccordionPanelContent" style="height: 0px;">
								<ul>
									<li>
										<a target="mainframe" href="control/center/module/manage.do">模块管理</a>
									</li>
									<li>
										<a target="mainframe" href="control/center/role/manage.do">角色管理</a>
									</li>
									<!-- <a target="mainframe" href="control/center/role-acl/manage.do">角色权限设置</a> -->
									<li>
										<a target="mainframe"
											href="control/center/administrator/manage.do">网站管理员</a>
										<!-- <a target="mainframe" href="control/center/administrator-acl/manage.do">管理员权限设置</a> -->
									</li>
								</ul>
							</div>
						</div>

						<div class="AccordionPanel AccordionPanelClosed">
							<div class="AccordionPanelTab">
								销售管理
							</div>
							<div class="AccordionPanelContent" style="height: 0px;">
								<ul>
									<li>
										<a target="mainframe" href="control/center/module/manage.do" onclick="return false;">销售管理(待开发)</a>
									</li>
									<li>
										<a target="mainframe" href="control/center/module/manage.do" onclick="return false;">销售统计(待开发)</a>
									</li>
								</ul>
							</div>
						</div>
						<div class="AccordionPanel AccordionPanelOpen">
							<div class="AccordionPanelTab">
								库存管理
							</div>
							<div class="AccordionPanelContent" style="height: 300px;">
								<ul>
									<li>
										<a target="mainframe"
											href="<html:rewrite action='control/product/stock/manage'/>">库存管理</a>
									</li>
								</ul>
							</div>
						</div>
			<!-- 			<div class="AccordionPanel AccordionPanelClosed">
							<div class="AccordionPanelTab">
								模板管理
							</div>
							<div class="AccordionPanelContent" style="height: 0px;">
								<ul>
									<li>
										<a target="mainframe" href="control/center/module/manage.do">分类页模板管理</a>
									</li>
									<li>
										<a target="mainframe" href="control/center/module/manage.do">产品页模板管理</a>
									</li>
									<li>
										<a target="mainframe" href="control/center/module/manage.do">新闻模板管理</a>
									</li>
									<li>
										<a target="mainframe" href="control/center/module/manage.do">首页模板管理</a>
									</li>
								</ul>
							</div>
						</div> -->
						<div class="AccordionPanel AccordionPanelClosed">
							<div class="AccordionPanelTab">
								日志管理
							</div>
							<div class="AccordionPanelContent" style="height: 0px;">
								<ul>
									<li>
										<a target="mainframe" href="control/analysis.do">访问日志</a>
									</li>
									<li>
										<a target="mainframe" href="control/center/module/manage.do" onclick="return false;">购物日志(待开发)</a>
										<!-- 使用quartz进行每日统计 -->
									</li>
								</ul>
							</div>
						</div>
						<div class="AccordionPanel AccordionPanelClosed">
							<div class="AccordionPanelTab">
								运费相关
							</div>
							<div class="AccordionPanelContent" style="height: 0px;">
								<ul>
									<li>
										<a target="mainframe" href="control/center/delivertype/manage.do">送货方式管理</a>
									</li>
									<li>
										<a target="mainframe" href="control/center/globaldistribution/manage.do">全局运费设置</a>
									</li>
									<li>
										<a target="mainframe" href="control/center/distribution/manage.do">产品运费模板管理</a>
									</li>
								</ul>
							</div>
						</div>							
						<div class="AccordionPanel AccordionPanelClosed">
							<div class="AccordionPanelTab">
								系统设置
							</div>
							<div class="AccordionPanelContent" style="height: 0px;">
								<ul>
									<li>
										<a target="mainframe" href="control/center/country/manage.do">国家信息</a>
									</li>
									<li>
										<a target="mainframe" href="control/center/zone/manage.do">地区信息</a>
									</li>
									<li>
										<a target="mainframe" href="/control/shopping/orderstatus/manage.do">订单状态管理</a>
									</li>
									<li>
										<a target="mainframe" href="control/center/coupon/manage.do">代金券管理</a>
									</li>
									
								</ul>
							</div>
						</div>
						<div class="AccordionPanel AccordionPanelClosed">
							<div class="AccordionPanelTab">
								在线支付
							</div>
							<div class="AccordionPanelContent" style="height: 0px;">
								<ul>
								<li>
										<a target="mainframe" href="control/center/paymentmethod/manage.do">支付方式管理</a>
									</li>
									<li><a href="control/payment/alipay/manage.do" target="mainframe">支付宝设置</a>
									</li>
									
								</ul>
							</div>
						</div>
						<div class="AccordionPanel AccordionPanelClosed">
							<div class="AccordionPanelTab">
								资讯管理
							</div>
							<div class="AccordionPanelContent" style="height: 0px;">
								<ul>
									<li>
										<a target="mainframe"
											href="control/center/article/manage.do?method=listType">类型管理</a>
									</li>
									<li>
										<a target="mainframe"
											href="control/center/article/manage.do?id=1">新闻管理</a>
									</li>
									<li>
										<a target="mainframe"
											href="control/center/article/manage.do?id=2">博客管理</a>
									</li>
								<!-- 	<li>
										<a target="mainframe"
											href="control/center/article/manage.do?id=7">产品分类文章管理</a>
									</li> -->
									<li>
										<a target="mainframe"
											href="control/center/article/manage.do?id=3">帮助中心文章管理</a>
									</li>									
									
								</ul>
							</div>
						</div>
						<div class="AccordionPanel AccordionPanelClosed">
							<div class="AccordionPanelTab">
								评论管理
							</div>
							<div class="AccordionPanelContent" style="height: 0px;">
								<ul><li>
										<a target="mainframe" href="control/center/comment/manage.do?method=listComments&type=2">新闻评论管理</a>
									</li>
									<li>
										<a target="mainframe"
											href="<html:rewrite action='control/center/comment/manage.do?method=listComments&type=8'/>">产品评论管理</a>
									</li>
<li>
										<a target="mainframe"
											href="<html:rewrite action='control/center/comment/manage.do?method=listComments&type=1'/>">博客评论管理</a>
									</li>
									<li>
										<a target="mainframe"
											href="<html:rewrite action='control/center/comment/manage.do?method=listComments&type=3'/>">帮助中心评论管理</a>
									</li>
									<li>
										<a target="mainframe"
											href="<html:rewrite action='control/center/comment/manage.do?method=listComments&type=7'/>">产品类别新闻评论管理</a>
									</li>
								</ul>
								</div>
								</div>
						<div class="AccordionPanel AccordionPanelClosed">
							<div class="AccordionPanelTab">
								邮件管理
							</div>
							<div class="AccordionPanelContent" style="height: 0px;">
								<ul>
									<li>
										<a target="mainframe"
											href="control/center/email/manage.do">管理员邮件帐号</a>
									</li>
								</ul>
							</div>
						</div>
						<div class="AccordionPanel AccordionPanelClosed">
							<div class="AccordionPanelTab">
								客户管理
							</div>
							<div class="AccordionPanelContent" style="height: 0px;">
								<ul>
									<li>
										<a target="mainframe"
											href="control/center/user/manage.do">客户信息管理</a>
									</li>
								</ul>
							</div>
						</div>
					</div>
				</div>
				<div class="clearer"></div>
			</div>


		</div>
		<script src="js/SpryAccordion.js" type="text/javascript"></script>
		<script type="text/javascript">
	var Accordion1 = new Spry.Widget.Accordion("Accordion1");
</script>

	</body>
</html>
