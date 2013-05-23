<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"
%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.hnfealean.sport.pageset.SystemContext"%><%@ include file="/WEB-INF/page/share/taglib.jsp" 
%><%@ page import="com.hnfealean.sport.web.forms.log.LogForm"%><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<html>
  <head>
    <title>浏览记录</title>
<jsp:include page="/location_check.jsp"></jsp:include>
<link rel="stylesheet" href="css/vip.css" type="text/css">

<SCRIPT language=JavaScript src="js/calendar.js"></SCRIPT>

<link rel="stylesheet" href="js/calendar.css" type="text/css">


  </head>
  
  <body>
  <div class="stat_menu">
<div class="stat_header">概况</div>
<div class="stat_header">SEO 数据</div>
<div class="stat_header">在线访问者</div>
<div class="stat_header"><a href="control/analysis.do?method=getDateDetail&beginDate=<fmt:formatDate value="${logForm.beginDate}" pattern="yyyy-MM-dd"/>">访问明细</a></div>
<div class="stat_header">升降榜</div>
<div class="stat_header">流量分析</div>
<div class="stat_content">时段分析</div>
<div class="stat_content">日段分析</div>
<div class="stat_content">周月分析</div>
<div class="stat_content">历史流量查询</div>
<div class="stat_header">内容分析</div>
<div class="stat_content">搜索引擎</div>
<div class="stat_content">关键词</div>
<div class="stat_content">来路</div>
<div class="stat_content">入口</div>
<div class="stat_content">页面浏览</div>
<div class="stat_header">吸引力分析</div>
<div class="stat_content">回头客</div>
<div class="stat_content">浏览深度</div>
<div class="stat_header">访问者信息</div>
<div class="stat_content">操作系统</div>
<div class="stat_content">浏览器</div>
<div class="stat_content">语言</div>
<div class="stat_content">时区</div>
<div class="stat_content">屏幕色彩</div>
<div class="stat_content">屏幕尺寸</div>
<div class="stat_content">国家/省份</div>
<div class="stat_content">城市</div>
<div class="stat_content">IP头</div>
</div>
  <table width="100%" border="0" cellspacing="1" cellpadding="2" align="center">
     <tr><td>开始日期</td><td>结束日期</td></tr>
     <tr><td><input type="text" name="beginDate" size="35" maxlength="30" onfocus="HS_setDate(this)"/></td>
     <td><input type="text" name="endDate" size="35" maxlength="30" onfocus="HS_setDate(this)"/></td></tr>
     <tr><td>今日流量:</td><td>${ipcount}IP&nbsp;${pm.total}PV</td></tr> 
     
     </table>
       <table width="100%" border="0" cellspacing="1" cellpadding="2" align="center">

         <tr><td colspan="6"  bgcolor="6f8ac4" align="right">
 总记录数:${pm.total}条 | <div class="pagesplit">  <%
 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
 LogForm lf = (LogForm)request.getAttribute("logForm");
 String pageSplits = ConstantString.generatePageSplit(
 		((PageModel)request.getAttribute("pm")).getTotal(),50,
 		"control/analysis.do?method=getIpDetail&ip="+lf.getIp()+"&beginDate="+format.format(lf.getBeginDate())+"&endDate="+format.format(lf.getEndDate())+"&");
out.println(pageSplits);
%>  </div>
   </td></tr>
    <tr>
    	<td bgcolor="#6f8ac4">ID</td><td bgcolor="#6f8ac4">用户ID</td><td bgcolor="#6f8ac4">用户email</td><td bgcolor="#6f8ac4">入口网址</td><td bgcolor="#6f8ac4">来源</td><td bgcolor="#6f8ac4">访问时间</td>
    </tr>
    <c:forEach items="${pm.datas}" var="log">
    <tr>
    	<td bgcolor="#f5f5f5">${log.id }</td><td bgcolor="#f5f5f5">${log.userId }</td>
    	<td bgcolor="#f5f5f5">${log.userEmail }</td><td bgcolor="#f5f5f5">${log.inUrl }</td><td bgcolor="#f5f5f5">${log.referer }</td><td bgcolor="#f5f5f5">${log.inTime }</td>
    </tr>
   </c:forEach>
    
    </table>
  </body>
</html>
