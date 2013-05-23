package com.hnfealean.sport.web.actions.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.hnfealean.sport.managers.product.TagManager;
import com.hnfealean.sport.pageset.PageModel;

public class TagAction extends DispatchAction {
private TagManager tagManager;
	public void setTagManager(TagManager tagManager) {
	this.tagManager = tagManager;
}
	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PageModel pm = tagManager.searchAllTags();
		request.setAttribute("pm", pm);
		return mapping.findForward("list");
	}

}
