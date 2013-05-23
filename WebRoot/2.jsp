<%@ page import="com.hnfealean.sport.web.MD5" 
%><%@ 
page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"
%><%@ page import="com.hnfealean.sport.web.cache.*"%><%@ 
include file="/WEB-INF/templates/default/share/taglib.jsp" 
%><%@ page import="java.util.*"
%><%@ page import="java.text.*"
%><%@ page import="java.lang.StringBuffer" 
%><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%
Hashtable map = CacheOperation.getInstance().getCacacheMap();
for(Iterator itr = map.keySet().iterator(); itr.hasNext();){
	String key = (String) itr.next();
	Object value = (Object) map.get(key);
	out.println(key+"--"+value);
	}

%>
