package com.hnfealean.test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommentRegular {
	private static String setReplaceCommentId(String id){
		String returnStr;
		
		returnStr = "<div class='fq'><span class='un'></span>说到:<div class='fqcon'>";
		System.out.println("返回的str是："+returnStr);
		return returnStr;
	}
	public static void main(String[] args){
	String temp = "<span style='color: rgb(153, 204, 0);'>gggggggggg</span>gggg<strong>ggg</strong>ggg<em>gg</em><span style='background-color: rgb(153, 204, 0);'>gg</span><br><div class='fq'><span class='un'>游客</span>说到:<div class='fqcon'><div class='fq'><span class='un'>游客</span>说到:<div class='fqcon'>InnoDB free: 9216 kB; (`replyId`) REFER `angelinthebox/t_comment`(`id`); (`content`) REFER `angelinthebox/comment_content`(`id`)</div></div><br /></div></div><br><div class='fq'><span class='un'>游客</span>说到:<div class='fqcon'><div class='quote'><span class='user'>null</span><span class='ip'>null安徽蚌埠</span><span class='date'>null</span><br><div class='content'>InnoDB free: 9216 kB; (`replyId`) REFER `angelinthebox/t_comment`(`id`); (`content`) REFER `angelinthebox/comment_content`(`id`)</div><br></div><br><span class='end'><!--5--></span><br /></div></div><br><div class='fq'><span class='un'>游客</span>说到:<div class='fqcon'><div class='fq'><span class='un'>游客</span>说到:<div class='fqcon'>InnoDB free: 9216 kB; (`replyId`) REFER `angelinthebox/t_comment`(`id`); (`content`) REFER `angelinthebox/comment_content`(`id`)</div></div><br /></div></div><br />";
	
System.out.println(temp.replaceAll("<span class='end'><!--([0-9]+)--></span>",setReplaceCommentId("$1")));
	//String s = "<span style='color: rgb(153, 204, 0);'>gggggggggg</span>gggg<strong>ggg</strong>ggg<em>gg</em><span style='background-color: rgb(153, 204, 0);'>gg</span><br><div class='fq'><span class='un'>游客</span>说到:<div class='fqcon'><div class='fq'><span class='un'>游客</span>说到:<div class='fqcon'>InnoDB free: 9216 kB; (`replyId`) REFER `angelinthebox/t_comment`(`id`); (`content`) REFER `angelinthebox/comment_content`(`id`)</div></div><br /></div></div><br><div class='fq'><span class='un'>游客</span>说到:<div class='fqcon'><div class='quote'><span class='user'>null</span><span class='ip'>null安徽蚌埠</span><span class='date'>null</span><br><div class='content'>InnoDB free: 9216 kB; (`replyId`) REFER `angelinthebox/t_comment`(`id`); (`content`) REFER `angelinthebox/comment_content`(`id`)</div><br></div><br><span class='end'><!--5--></span><br /></div></div><br><div class='fq'><span class='un'>游客</span>说到:<div class='fqcon'><div class='fq'><span class='un'>游客</span>说到:<div class='fqcon'>InnoDB free: 9216 kB; (`replyId`) REFER `angelinthebox/t_comment`(`id`); (`content`) REFER `angelinthebox/comment_content`(`id`)</div></div><br /></div></div><br />";
		//String s= "<div class='fqcon'><scc><a><a hh><ahh><ahhh/><a hhh/><div class='fq'><span class='un'>游客</span>说到:<div class='fqcon'>InnoDB free: 9216 kB; (`replyId`) REFER `angelinthebox/t_comment`(`id`); (`content`) REFER `angelinthebox/comment_content`(`id`)</div><div></div></div><br /></div>";
		//String pstr = "<^((div( class='.+?'))|(!--[0-9]+?--))>";
		
		String s="<span style='color: rgb(153, 204, 0);'></span><li></li><a><script><script language='javascript'><a hh><ahh><ahhh/><a hhh/>gggggggggg</span>gggg<strong>ggg</strong>ggg<em>gg</em><span style='background-color: rgb(153, 204, 0);'>gg</span><br><div class='fq'><span class='un'>游客</span>说到:<div class='fqcon'><div class='fq'><span class='un'>游客</span>说到:<div class='fqcon'>InnoDB free: 9216 kB; (`replyId`) REFER `angelinthebox/t_comment`(`id`); (`content`) REFER `angelinthebox/comment_content`(`id`)</div></div><br /></div></div><br><div class='fq'><span class='un'>游客</span>说到:<div class='fqcon'><div class='quote'><span class='user'>null</span><span class='ip'>null安徽蚌埠</span><span class='date'>null</span><br><div class='content'>InnoDB free: 9216 kB; (`replyId`) REFER `angelinthebox/t_comment`(`id`); (`content`) REFER `angelinthebox/comment_content`(`id`)</div><br></div><br><span class='end'><!--5--></span><br /></div></div><br><div class='fq'><span class='un'>游客</span>说到:<div class='fqcon'><div class='fq'><span class='un'>游客</span>说到:<div class='fqcon'>InnoDB free: 9216 kB; (`replyId`) REFER `angelinthebox/t_comment`(`id`); (`content`) REFER `angelinthebox/comment_content`(`id`)</div></div><br /></div></div><br />";
		s= s.replaceAll("<(/?(div|span|br|li|ol|b|em|strong|!--)( .+?|[0-9]+--)?)>", "[[$1]]");
		s = s.replaceAll("<[^>]*>", "").replace("[[", "<").replace("]]", ">");
		//s = s.replace("[[", "<").replace("]]", ">");
		System.out.println(s);
		//s =s.replaceAll("[[", ">").replaceAll("]]", ">")
		System.out.println(s.replaceAll("<[^>]*>", ""));
		System.out.println(s.replaceAll("<(/?(div|span|br|li|ol|b|em|strong|!--)( .+?|[0-9]+--)?)>", "[[$1]]"));

		//String pstr = "<div( class='[^>|']+?')?(.+?)?>";
//		String pstr = "<([divspanbrliol]+?)( .+?)?>";匹配
		String pstr = "<((/?div|span|br|li|ol|b||!)( .+?)?)>";
		Pattern pattern = Pattern.compile(pstr);
		Matcher matcher = pattern.matcher(s);
		List<String> replaces = new ArrayList<String>();
		while(matcher.find()){
		//	System.out.println(matcher.group(0)+" "+matcher.group(1));
			//System.out.println(matcher.replaceAll("["+matcher.group(1)+"]"));
			//System.out.println("count: " +matcher.groupCount());
			for(int i = 0;i<matcher.groupCount();i++){
		         System.out.println("group "+i+" : "+matcher.group(i));
				if(!replaces.contains(matcher.group(1)))
					replaces.add(matcher.group(1));
				/**
				 * group 0 :<div class='fqcon'>
					group 1 :div
				 */
			}
		}
		for(String replace:replaces){
			s = s.replace("<"+replace+">", "[["+replace+"]]");
		}

		System.out.println("最终的字符串： "+s);
		System.out.println(matcher.replaceFirst("gugu"));
		/*
	String input ="<div class='quote'><span class='user'>null</span><span class='ip'>127.0.0.1本机地址</span><span class='date'>2010-12-15&nbsp;09:11:56.0</span><br><div class='content'>hello?wow</div><br></div><span class='end'><!--33--></span><br><div class='quote'><span class='user'>null</span><span class='ip'>127.0.0.1本机地址</span><span class='date'>2010-12-15&nbsp;09:11:45.0</span><br><div class='content'>hello?wow</div><br></div><span class='end'><!--38873--></span><br>hello&nbsp;wow";
	input = input.replaceAll("<div class='quote'>.+?</div><span class='end'>", "<span class='end'>");
	
	Pattern p = Pattern.compile("<span class='end'><!--([0-9]+)--></span>",Pattern.CASE_INSENSITIVE);
	
	Matcher m = p.matcher(input);
	StringBuffer st = new StringBuffer();
	//String[] strr = new String[10];
	Hashtable<String,String> table = new Hashtable<String,String>();
	while(m.find()){
		if(!table.containsKey(Integer.valueOf(m.group(1))))
		table.put(m.group(1), m.group(0));
	}
	Enumeration keys=table.keys();

	while(keys.hasMoreElements()){
		String key=(String)keys.nextElement();
		System.out.println(key+"---"+table.get(key));
	} 
	 */
	}
	
}
