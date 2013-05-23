package com.hnfealean.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularCommentTest {

	public static void main(String[] args){
		String str = "2x^3+3x^4+5x^5";
		String p0="(\\d)x\\^(\\d)";        
		Pattern pt=Pattern.compile(p0);
		Matcher m0=pt.matcher(str);
		while(m0.find())
		{
		     for(int i=0;i<=m0.groupCount();i++)
		     {
		         System.out.println("group "+i+" : "+m0.group(i));
		     }
		}
		
	String input ="<div class='quote'><span class='user'>null</span><span class='ip'>127.0.0.1本机地址</span><span class='date'>2010-12-15&nbsp;09:11:56.0</span><br><div class='content'>hello?wow</div><br><span class='end'><!--33--></span></div><br><div class='quote'><span class='user'>null</span><span class='ip'>127.0.0.1本机地址</span><span class='date'>2010-12-15&nbsp;09:11:45.0</span><br><div class='content'>hello?wow</div><br><span class='end'><!--33--></span></div><br>hello&nbsp;wow";
	Pattern p = Pattern.compile(".+(<div class='quote'>).+?(<span class='end'><!--[0-9]+--></span></div>).+",Pattern.CASE_INSENSITIVE);
	
	Matcher m = p.matcher(input);
	if(m.find()){
		System.out.println(	m.groupCount());
        for(int i = 0; i <= m.groupCount(); i++)  
            System.out.println("group " + i + " :" + m.group(i));  
	}
	
	input ="<div class='quote'><span class='user'>null</span><span class='ip'>127.1本机地址</span><span class='date'>2010-12-15&nbsp;09:11:56.0</span><br><div class='content'>hello?wow</div><br></div><span class='end'><!--33--></span><br><div class='quote'><span class='user'>null</span><span class='ip'>127.0.0.1本机地址</span><span class='date'>2010-12-15&nbsp;09:11:45.0</span><br><div class='content'>hello?wow</div><br></div><span class='end'><!--3555--></span><br>hello&nbsp;wow";
	p = Pattern.compile(".+((<div class='quote'>).+(<span class='end'><!--[0-9]+--></span></div>))?.+",Pattern.CASE_INSENSITIVE);
//	System.out.println(input.indexOf("<div class='quote'>"));
//	System.out.println(input.indexOf("<span class='end'><!--"));
	System.out.println(input.replaceAll("<div class='quote'>.+?</div><span class='end'>", "<span class='end'>"));
//	System.out.println(Pattern.compile("<div class='quote'>.+?<span class='end'><!--([0-9])+--></span></div>").matcher(input).replaceAll("sss"));
Pattern pp =	Pattern.compile("<div class='quote'>.+?</div><span class='end'><!--([0-9]+)--></span>");
	
Matcher mm = pp.matcher(input);//
while(mm.find()){
	System.out.println(	mm.groupCount());
    for(int i = 0; i <= mm.groupCount(); i++)  
        System.out.println("groupgroup " + i + " :" + mm.group(i));  
}
System.out.println("mm.replaceAll('<span class='end'>'):"+mm.replaceAll("<span class='end'>"));

//if(mm.find()){
///	if(mm.groupCount()>0) System.out.println(mm.replaceAll(mm.group(1)));
//}
	
//.replaceAll("sss"));

	m = p.matcher(input);
	while(m.find()){
		System.out.println(	m.groupCount());
        for(int i = 0; i <= m.groupCount(); i++)  
            System.out.println("group " + i + " :" + m.group(i));  
	}

	//System.out.println(m.replaceAll(m.group(1)));
	
	
	

	String teststr = "<span class='end'><!--33--></span><br><span class='end'><!--37--></span><br>hello&nbsp;wow";
	Pattern testp = Pattern.compile("<span class='end'><!--([0-9]+)--></span>");
	Matcher testm = testp.matcher(teststr);
	while(testm.find()){

		System.out.println(	testm.groupCount());
        for(int i = 0; i <= testm.groupCount(); i++)  
            System.out.println("group " + i + " :" + testm.group(i));  
	}
/*		String s="<div class='quote' id='33'><span class='user'>null</span><span class='ip'>127.0.0.1本机地址</div></span><span class='date'>2010-12-14&nbsp;07:09:40.0</span><div class='content'>少时诵诗书是</div></div>hello&nbsp;wow<div class='quote' id='33'><span class='user'>null</span><span class='ip'>127.0.0.1本机地址</span><span class='date'>2010-12-15&nbsp;06:06:43.0</span><div class='content'>     <br /><div id='container'>         <br /><form name='aspnetForm' method='post' action='/post/2009/03/04/200903042238243247.aspx' id='aspnetForm'><br />&nbsp;&nbsp;&nbsp;&nbsp; <br />&nbsp;&nbsp;&nbsp;&nbsp;<div> <input type='hidden' name='__EVENTTARGET' id='__EVENTTARGET' value='' /> <input type='hidden' name='__EVENTARGUMENT' id='__EVENTARGUMENT' value='' /> <input type='hidden' name='__VIEWSTATE' id='__VIEWSTATE' value='/wEPDwUKLTkzNDUyODE2OQ9kFgJmD2QWAgIBD2QWBGYPFgQeBGhyZWYFCy9sb2dpbi5hc3B4Hglpbm5lcmh0bWwFBkxvZyBpbmQCAg9kFgQCAQ8WAh4HVmlzaWJsZWcWBAIBDw8WBh4LTmF2aWdhdGVVcmwFKC9wb3N0LzIwMDkvMDMvMDIvMjAwOTAzMDIyMDQ2MjU4MDM3LmFzcHgeBFRleHQFLSZsdDsmbHQ7IOaIkeeahOWbnuaUtuermeWIsOWTqumHjOWOu+S6hu+8n++8gR4HVG9vbFRpcAUNUHJldmlvdXMgcG9zdGRkAgMPDxYGHwMFKC9wb3N0LzIwMDkvMDMvMDcvMjAwOTAzMDcwMDIzNTg3ODUzLmFzcHgfBAVHRmlyZWJ1Z+KAlOKAlEZpcmVmb3jmtY/op4jlmajnmoTlrqLmiLfnq6/ku6PnoIHosIPor5XnmoTliKnlmaggJmd0OyZndDsfBQUJTmV4dCBwb3N0ZGQCAw8WAh8CZ2Rkg1WO2M5ECBkl3+wAQDSBjIx7WWQ=' /> </div><br />&nbsp;&nbsp;&nbsp;&nbsp;                   <br />&nbsp;&nbsp;&nbsp;&nbsp;<div id='header'>             <br />&nbsp;&nbsp;&nbsp;&nbsp;<h1>                 <a href='http://laoney.net/'>                     草根醉秋意</a></h1><br />&nbsp;&nbsp;&nbsp;&nbsp;             <br />&nbsp;&nbsp;&nbsp;&nbsp;<h2>                 健康 快乐 自由</h2><br />&nbsp;&nbsp;&nbsp;&nbsp;         </div><br />&nbsp;&nbsp;&nbsp;&nbsp;         <br />&nbsp;&nbsp;&nbsp;&nbsp;<div id='menu'>             <br />&nbsp;&nbsp;&nbsp;&nbsp;<ul id='navlist'><br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<li><a href='http://laoney.net/' rel='home'>                     Home</a></li><br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<li><a href='http://laoney.net/archive.aspx'>                     Archive</a></li><br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<li><a href='http://laoney.net/contact.aspx'>                     Contact</a></li><br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<li><a href='http://laoney.net/search.aspx'>                     Search</a></li><br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<li><a href='http://laoney.net/tag.aspx'>                     Tag</a></li><br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<li><a href='http://laoney.net/syndication.axd'>                     Subscribe</a></li><br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<li><a href='http://laoney.net/login.aspx' id='ctl00_aLogin'>Log in</a></li><br />&nbsp;&nbsp;&nbsp;&nbsp;</ul><br />&nbsp;&nbsp;&nbsp;&nbsp;         </div><br />&nbsp;&nbsp;&nbsp;&nbsp;         <br />&nbsp;&nbsp;&nbsp;&nbsp;<div id='content'>             <br />&nbsp;&nbsp;&nbsp;&nbsp;<div id='content-left'>                                   <br />&nbsp;&nbsp;&nbsp;&nbsp;<div class='div_google_ad'>   </div><br />&nbsp;&nbsp;&nbsp;&nbsp;       <br />&nbsp;&nbsp;&nbsp;&nbsp;<div class='post xfolkentry'>   <br />&nbsp;&nbsp;&nbsp;&nbsp;<h1><a href='http://laoney.net/post/2009/03/04/200903042238243247.aspx' class='taggedlink'>FCKEditor的API</a></h1><br />&nbsp;&nbsp;&nbsp;&nbsp;   <span class='categories'>category：<a href='http://laoney.net/category/64f9d3ca-5034-4223-b81f-eea2133e82f6.aspx'>学习笔记</a></span>   <span class='author'>Poster：<a href='http://laoney.net/author/lulaone.aspx'>lulaone</a></span>   <span class='pubDate'>Date：2009-03-04 22:32</span>       <br />&nbsp;&nbsp;&nbsp;&nbsp;<div class='text'>   <br />&nbsp;&nbsp;&nbsp;&nbsp;<p>获取Editor实例的方法：</p><br />&nbsp;&nbsp;&nbsp;&nbsp; <br />&nbsp;&nbsp;&nbsp;&nbsp;<div class='dp-highlighter'><br />&nbsp;&nbsp;&nbsp;&nbsp;<div class='bar'><br />&nbsp;&nbsp;&nbsp;&nbsp;<div class='tools'><a href='http://laoney.net/post/2009/03/04/200903042238243247.aspx#'>纯文本</a><a href='http://laoney.net/post/2009/03/04/200903042238243247.aspx#'>复制</a><a href='http://laoney.net/post/2009/03/04/200903042238243247.aspx#'>打印</a></div><br />&nbsp;&nbsp;&nbsp;&nbsp;</div><br />&nbsp;&nbsp;&nbsp;&nbsp;<ol class='dp-c' start='1'><br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<li class='alt'><span><span class='comment'>//在引用即包含FCKeditor的地方获取其实例</span><span>??</span></span></li><br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<li><span><span class='keyword'>var</span><span>?oEditor?=?FCKeditorAPI.GetInstance(</span><span class='string'>&quot;InstanceName&quot;</span><span>);??</span></span></li><br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<li class='alt'><span>??</span></li><br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<li><span><span class='comment'>//在编辑器内部的弹出窗口中（如菜单的弹出窗口）获取其实例</span><span>??</span></span></li><br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<li class='alt'><span><span class='keyword'>var</span><span>?oEditor?=?window.parent.InnerDialogLoaded().FCK;??</span></span></li><br />&nbsp;&nbsp;&nbsp;&nbsp;</ol><br />&nbsp;&nbsp;&nbsp;&nbsp;</div><br />&nbsp;&nbsp;&nbsp;&nbsp;<pre style='' class='javascript' name='code'>//在引用即包含FCKeditor的地方获取其实例var oEditor = FCKeditorAPI.GetInstance(&quot;InstanceName&quot;);//在编辑器内部的弹出窗口中（如菜单的弹出窗口）获取其实例var oEditor = window.parent.InnerDialogLoaded().FCK;</pre><br />&nbsp;&nbsp;&nbsp;&nbsp; <br />&nbsp;&nbsp;&nbsp;&nbsp;<p>当我们获取了这个实例了以后，便可以访问很多有用的属性及方法。</p><br />&nbsp;&nbsp;&nbsp;&nbsp; <br />&nbsp;&nbsp;&nbsp;&nbsp;<p><strong>属性：</strong>下图是在vs 2008中调试js文件时试用快速窗口查看FCKeditor对象属性时的截图，我们可以看到从这个FCK对象我们可以获取很多属性，有的是对其他对象的 引用，比如Commands和Event等。Commands属性是FCKCommand对象（我们可以从下载的源文件的editor_source internals文件夹的fckcomand.js文件中找到这个对象），他有一个GetCommand方法来获取一个已经注册了的命令，方法返回的 这些定义好的命令一般都会有一个Excute方法，这样我们就可以在外部执行FCK编辑器的命令了。比如我们打开一个添加表格或者是设置表格属性的对话 框：</p><br />&nbsp;&nbsp;&nbsp;&nbsp; <br />&nbsp;&nbsp;&nbsp;&nbsp;<div> <br />&nbsp;&nbsp;&nbsp;&nbsp;<div class='dp-highlighter'><br />&nbsp;&nbsp;&nbsp;&nbsp;<div class='bar'><br />&nbsp;&nbsp;&nbsp;&nbsp;<div class='tools'><a href='http://laoney.net/post/2009/03/04/200903042238243247.aspx#'>纯文本</a><a href='http://laoney.net/post/2009/03/04/200903042238243247.aspx#'>复制</a><a href='http://laoney.net/post/2009/03/04/200903042238243247.aspx#'>打印</a></div><br />&nbsp;&nbsp;&nbsp;&nbsp;</div><br />&nbsp;&nbsp;&nbsp;&nbsp;<ol class='dp-c' start='1'><br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<li class='alt'><span><span class='comment'>//获取编辑器对象</span><span>??</span></span></li><br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<li><span><span class='keyword'>var</span><span>?oEditor?=?FCKeditorAPI.GetInstance(</span><span class='string'>&quot;InstanceName&quot;</span><span>);??</span></span></li><br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<li class='alt'><span>??</span></li><br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<li><span><span class='comment'>//通过Command属性的GetCommand方法获取一个命令对象并调用他的Excute方法执行命令</span><span>??</span></span></li><br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<li class='alt'><span>oEditor.Commands.GetCommand(<span class='string'>&quot;Table&quot;</span><span>).Excute();??</span></span></li><br />&nbsp;&nbsp;&nbsp;&nbsp;</ol><br />&nbsp;&nbsp;&nbsp;&nbsp;</div><br />&nbsp;&nbsp;&nbsp;&nbsp;<pre style='' class='jscript' name='code'>//获取编辑器对象var oEditor = FCKeditorAPI.GetInstance(&quot;InstanceName&quot;);//通过Command属性的GetCommand方法获取一个命令对象并调用他的Excute方法执行命令oEditor.Commands.GetCommand(&quot;Table&quot;).Excute();</pre><br />&nbsp;&nbsp;&nbsp;&nbsp; </div><br />&nbsp;&nbsp;&nbsp;&nbsp; <br />&nbsp;&nbsp;&nbsp;&nbsp;<p><img width='513' height='418' alt='' src='http://laoney.net/uploadfiles/images/2009/0320/fck_property.jpg' /></p><br />&nbsp;&nbsp;&nbsp;&nbsp; <br />&nbsp;&nbsp;&nbsp;&nbsp;<p>以下为几个比较常用的属性：</p><br />&nbsp;&nbsp;&nbsp;&nbsp; <br />&nbsp;&nbsp;&nbsp;&nbsp;<ul><br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<li><b>oEditor.Name? ( string )</b> ：获取实例的名称。</li><br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<li><b>oEditor.Status? ( integer )</b> ：获取实例的状态。</li><br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<li><b>oEditor.EditorDocument? ( object )</b> ：获取编辑区域的document对象。这document对象包括的内容只有编辑区域里面的html代码。</li><br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<li><b>oEditor.EditorWindow? ( object )</b> ：获取编辑区域的window对象。</li><br />&nbsp;&nbsp;&nbsp;&nbsp;</ul><br />&nbsp;&nbsp;&nbsp;&nbsp; <br />&nbsp;&nbsp;&nbsp;&nbsp;<p><strong>方法：</strong>我们在获取了一个编辑器的对象了以后，可以执行很多的方法来实现在编辑器的外部对编辑区域的操作。下图是 在vs 2008中调试js文件时试用快速窗口查看FCKeditor对象属性时的截图，其中画了红线的时我们经常会用到的方法。包括注册当选择的元素发生变化时 的事件处理方法AttachToOnSelectionChange方法（类似于C#中的委托），以及执行已命名的命令 ExecuteNamedCommand方法，还有获取编辑器内的内容GetData，GetHTML，GetXHTML等方法，想编辑区域添加元素和 html代码InsertElement，InsertElementAndGetIt，InsertHtml，设置编辑区域内的的html元素等方法 SetHtml。</p><br />&nbsp;&nbsp;&nbsp;&nbsp; <br />&nbsp;&nbsp;&nbsp;&nbsp;<p><img src='http://laoney.net/uploadfiles/images/2009/0320/fck_fucntion.jpg' alt='' /></p><br />&nbsp;&nbsp;&nbsp;&nbsp; <br />&nbsp;&nbsp;&nbsp;&nbsp;<p>以下为几个常用的方法：</p><br />&nbsp;&nbsp;&nbsp;&nbsp; <br />&nbsp;&nbsp;&nbsp;&nbsp;<ul><br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<li><strong>AttachToOnSelectionChange( functionPointer )：</strong></li><br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<li><strong>ExcuteNamedCommand()：</strong></li><br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<li><strong>GetData()：</strong></li><br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<li><strong>GetHtm()：</strong></li><br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<li><strong>InsertElement()</strong>：</li><br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<li><strong>InsertElementAndGetIt()</strong>：</li><br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<li><strong>InsertHtml()</strong>：在光标处插入html代码</li><br />&nbsp;&nbsp</div>g</div>gg</div>ggg";
		//Pattern p = Pattern.compile("<div class='quote' id='[0-9]+'>*</div>",Pattern.CASE_INSENSITIVE);
		//Pattern p = Pattern.compile("(<[^>]+>)",Pattern.CASE_INSENSITIVE);
		Pattern p = Pattern.compile("(<div class='quote' id='[0-9]+'>).+(</div><span class='end'><!----></span>)",Pattern.CASE_INSENSITIVE);
		
		Matcher m = p.matcher(s);
		if(m.find()){
			System.out.println(	m.groupCount());
            for(int i = 0; i <= m.groupCount(); i++)  
                System.out.println("group " + i + " :" + m.group(i));  
          
		}
	//	System.out.println(m.group(1));
		System.out.println(m.replaceAll(""));
		       String regex = "\\w(\\d\\d)(\\w+)";  
		       String candidate = "x99SuperJava";  
		          
		        p = Pattern.compile(regex);  
		        Matcher matcher = p.matcher(candidate);  
		         if(matcher.find()){  
		            int gc = matcher.groupCount();  
		             for(int i = 0; i <= gc; i++)  
		                 System.out.println("group " + i + " :" + matcher.group(i));  
		         }  */
	}
	
	
}
