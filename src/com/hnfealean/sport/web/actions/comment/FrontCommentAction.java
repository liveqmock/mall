package com.hnfealean.sport.web.actions.comment;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.hnfealean.sport.enums.SearchType;
import com.hnfealean.sport.managers.article.ArticleManager;
import com.hnfealean.sport.managers.comment.CommentManager;
import com.hnfealean.sport.managers.search.CommentSearchManager;
import com.hnfealean.sport.model.article.Article;
import com.hnfealean.sport.model.comment.Comment;
import com.hnfealean.sport.model.comment.CommentContent;
import com.hnfealean.sport.pageset.PageModel;
import com.hnfealean.sport.pageset.SystemContext;
import com.hnfealean.sport.web.WebUtil;
import com.hnfealean.sport.web.forms.comment.CommentForm;
import com.hnfealean.sport.web.ipaddressutil.IPSeeker;

public class FrontCommentAction extends DispatchAction {
	private CommentManager commentManager;
	private CommentSearchManager commentSearchManager;
	private ArticleManager articleManager;
	public void setArticleManager(ArticleManager articleManager) {
		this.articleManager = articleManager;
	}
	public void setCommentSearchManager(CommentSearchManager commentSearchManager) {
		this.commentSearchManager = commentSearchManager;
	}
	public void setCommentManager(CommentManager commentManager) {
	this.commentManager = commentManager;
}
	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CommentForm cf = (CommentForm) form; 
		if(cf.getOid()==0||cf.getType()==0){
			 response.reset();  
			 response.sendError(404);
			 return null;  
		}
		// System.out.println(SearchType.ALL.getType());
	//	commentManager.
		List comments =commentManager.getComments(cf.getOid(), SearchType.ALL.getType());
		request.setAttribute("comments", comments);
		/*
		PageModel commentspg = commentSearchManager.search(Comment.NEWS,cf.getOid() ,"", SystemContext.getOffset(), SystemContext.getPagesize());
//		for(Comment comment:(List<Comment>)commentspg.getDatas()){
//			System.out.println(comment.getId());
//		}
		request.setAttribute("comments", commentspg);
		*/
		return mapping.findForward("index");
	}
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return null;
		
	}
	private String setReplaceCommentId(String id){
		System.out.println("setReplaceCommentId输入的str是："+id);
		String returnStr=id+"bigbalabeng";
//		Comment c = commentManager.getComment(Integer.valueOf(id), 3, 0);
//		if(c==null)	return "";
//		if(c.getContent()==null) return "";
//		if(c.getContent().getContent()==null||c.getContent().getContent().length()==0) return "";
//		String un;
//		if(c.getUsername()==null)	un="游客";
//		else un= c.getUsername().trim();
//		returnStr = "<div class='fq'><span class='un'>"+un+"</span>说到:<div class='fqcon'>"+
//												c.getContent().getContent()+"</div></div>";
		System.out.println("返回的str是："+returnStr);
		return returnStr;
	}
	private String setContent(String input,int objectId,int objectType){
/*		System.out.println("input是："+input);
		//input =	input.replaceAll("<span class=\"end\"><!--([0-9]+)--></span>", WebUtil.getStr("$1"));//setReplaceCommentId("$1"));
		input =	input.replaceAll("<span class=\"end\"><!--([0-9]+)--></span>", setReplaceCommentId("$1"));
		System.out.println("input替换后是："+input);*/
	//	input = input.replaceAll("<div class=\"quote\">.+?</div>(<br>)?<span class=\"end\">", "<span class=\"end\">");
		
		Pattern p = Pattern.compile("<span class=\"end\"><!--([0-9]+)--></span>",Pattern.CASE_INSENSITIVE);
		
		Matcher m = p.matcher(input);
	//	StringBuffer st = new StringBuffer();
		//String[] strr = new String[10];
		Hashtable<String,String> table = new Hashtable<String,String>();
		while(m.find()){
			if(!table.containsKey(m.group(1)))
			table.put(m.group(1), m.group(0));
		}
		Enumeration keys=table.keys();
		while(keys.hasMoreElements()){
			String key=(String)keys.nextElement();
			Comment c = commentManager.getComment(Integer.valueOf(key), objectId, objectType);
			if(c==null)	{
				input = input.replace(table.get(key),"");
				continue;
			}
			if(c.getContent()==null){input = input.replace(table.get(key),"");continue;}
			if(c.getContent().getContent()==null||c.getContent().getContent().length()==0){input = input.replace(table.get(key),"");continue;}
			String un;
			if(c.getUsername()==null)	un="游客";
			else un= c.getUsername().trim();
			input = input.replace(table.get(key), "<div class='fq'><span class='un'>"+un+"</span>说到:<div class='fqcon'>"+
													c.getContent().getContent()+"</div></div>");
			//input=input.replace(arg0, arg1)
			System.out.println(key+"---"+table.get(key));
		} 
		System.out.println(input);

		return input;
	}
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CommentForm cf = (CommentForm) form; 
		Article article = articleManager.getArticleById(cf.getOid(), cf.getType());
		if(article==null||article.getCommentPermission()==false){
			response.reset();
			response.sendRedirect(cf.getReturnTo());
			return null;
		}
		//System.out.println("cf.getReturnTo()"+cf.getReturnTo());

		Comment comment = new Comment();
		//comment.setAddress("安徽蚌埠");
		comment.setCommentObject(cf.getType());
		comment.setCommentObjectId(cf.getOid());
		CommentContent content = new CommentContent();
//		String tempContent = cf.getContent().replaceAll("\r\n", "<br>");
//		  System.out.println(tempContent);
//		Pattern p = Pattern.compile(".*(<div class=\"quote\">).+(<span class=\"end\"><!--[0-9]+--></span></div>).*",Pattern.CASE_INSENSITIVE);
//		Matcher m = p.matcher(tempContent);
//		if(m.find()){
//			System.out.println(	m.groupCount());
//            for(int i = 0; i <= m.groupCount(); i++)  
//                System.out.println("group " + i + " :" + m.group(i));  
//          
//		}
		//content.setContent(cf.getContent().replaceAll("\r\n", "<br>"));
		content.setContent(setContent(cf.getContent().replaceAll("\r\n", "<br>"),cf.getOid(),cf.getType()));
		comment.setContent(content);
		String username =WebUtil.getUserName(request);
		if(username==null||username.trim().length()==0){
		}else{
			comment.setUsername(username);
		}
		if(request.getHeader("x-forwarded-for") == null) {
			comment.setIp(request.getRemoteAddr());
		}else{
			comment.setIp(request.getHeader("x-forwarded-for"));
		}
		String address=IPSeeker.getInstance().getAddress(comment.getIp());
		comment.setAddress(WebUtil.getAddressByIp(comment.getIp()));
		commentManager.addComment(cf.getOid(), cf.getType(), comment);
		//unspecified(mapping, form, request, response);
		//return mapping.findForward("index");
		response.sendRedirect(cf.getReturnTo());
		return null;
	}

	/**
	 * 方法返回json数组，用于显示最新的评论，使用ajax获得
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public  ActionForward getLatestComments(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
			CommentForm cf = (CommentForm) form;
			if(cf.getOid()==0){
				 response.sendError(404);
				 return null;
			}
			int latest = 0 ;
			String status304 =request.getHeader("If-None-Match");
			if(status304!=null){
				latest = Integer.valueOf(status304);
			}
				
			int latestId = commentManager.getLatestCommentId(cf.getOid(),  cf.getType());;
			if(latestId<=latest){
				response.reset();
				response.setStatus(304);
				return null;  
				//String nocomments = "hnfealean.com([[]])";
				//response.getOutputStream().write(nocomments.getBytes());
				//response.getOutputStream().flush();
			}
			
			List<Comment> comments= commentManager.getLatestComments(cf.getOid(), cf.getType(), 5);
			StringBuffer jsonBuffer = new StringBuffer();
			jsonBuffer.append("hnfealean.comment([");
			if(comments!=null&&comments.size()>0)
			for(Comment comment:comments){
				jsonBuffer.append("['"+comment.getId()+"','"+(comment.getUsername()==null?"游客":comment.getUsername())+"','"+comment.getIp()+"','"+comment.getAddress()+"','"+comment.getContent().getContent().replaceAll("\'", "\"")
						.replaceAll("\r\n", "<br>")+"','"+comment.getDate()+"'],");
			}
			jsonBuffer.append("["+latestId+"]])");
			String json = jsonBuffer.toString();
			System.out.println(json);
			response.reset();
			//response.setContentType("text/html; charset=utf-8");
			//response.setCharacterEncoding("utf-8");
			response.addHeader("Etag",latestId+"");
			response.getOutputStream().write(json.getBytes());
			response.getOutputStream().flush();
			return null;
	}
}			
