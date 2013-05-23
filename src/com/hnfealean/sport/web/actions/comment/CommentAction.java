package com.hnfealean.sport.web.actions.comment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.hnfealean.sport.enums.ConstantString;
import com.hnfealean.sport.managers.comment.CommentManager;
import com.hnfealean.sport.web.SystemException;
import com.hnfealean.sport.web.forms.comment.CommentForm;

public class CommentAction extends DispatchAction {

	private CommentManager commentManager;
	public void setCommentManager(CommentManager commentManager) {
		this.commentManager = commentManager;
	}
	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//CommentForm cf = (CommentForm) form;
		
		//request.setAttribute("comments", commentManager.getComments(cf.getOid(), cf.getType(),true));
		return mapping.findForward("index");
	}
	public ActionForward listNewsComments(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		getComments(request,ConstantString.NEWSVALUE);
		return mapping.findForward("index");
	}
	public ActionForward listProductComments(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		getComments(request,ConstantString.PRODUCTVALUE);
		return mapping.findForward("index");
	}
	public ActionForward listCategoryComments(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		getComments(request,ConstantString.CATEGORYVALUE);
		return mapping.findForward("index");
	}
	public ActionForward listBlogComments(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		getComments(request,ConstantString.BLOGVALUE);
		return mapping.findForward("index");
	}
	public ActionForward listWikiComments(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		getComments(request,ConstantString.WIKIVALUE);
		return mapping.findForward("index");
	}
	public ActionForward listQuestionComments(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		getComments(request,ConstantString.QUESTIONVALUE);
		return mapping.findForward("index");
	}
	public ActionForward listThemeComments(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		getComments(request,ConstantString.THEMEVALUE);
	
		return mapping.findForward("index");
	}
	public ActionForward listComments(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CommentForm cf = (CommentForm) form;
		getComments(request,cf.getType());
		return mapping.findForward("index");
	}
	private void getComments(HttpServletRequest request,int type){
		request.setAttribute("comments", commentManager.getComments(type));
		request.setAttribute("type", type);
	}
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CommentForm cf = (CommentForm) form;
		if(cf.getId()==0)	throw new SystemException("非法访问！");
		commentManager.deleteComment(cf.getId());
		request.setAttribute("message", "删除成功！");
		String methodName;
		if(cf.getType()==ConstantString.NEWSVALUE)	methodName = "listNewsComments";
		else if(cf.getType()==ConstantString.BLOGVALUE)	methodName = "listBlogComments";
		else if(cf.getType()==ConstantString.PRODUCTVALUE)	methodName = "listProductComments";
		else if(cf.getType()==ConstantString.THEMEVALUE)	methodName = "listThemeComments";
		else if(cf.getType()==ConstantString.QUESTIONVALUE)	methodName = "listQuestionComments";
		else if(cf.getType()==ConstantString.WIKIVALUE)	methodName = "listWikiComments";
		else 	methodName = "listNewsComments";
		request.setAttribute("urladdress", "control/center/comment/manage.do?method="+methodName+"&page="+(request.getParameter("page")==null?"":request.getParameter("page")));
		return mapping.findForward("success");
	}
	public ActionForward deletes(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CommentForm cf = (CommentForm) form;
		if(cf.getIds()==null||cf.getIds().length==0)	throw new SystemException("非法访问！");
		commentManager.deleteComments(cf.getIds());
		request.setAttribute("message", "删除成功！");
		request.setAttribute("urladdress", "control/center/comment/manage.do?method=listNewsComments&page="+(request.getParameter("page")==null?"":request.getParameter("page")));
		return mapping.findForward("success");
	}
}
