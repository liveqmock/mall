package com.hnfealean.sport.web.actions.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.hnfealean.sport.managers.product.ProductSearchManager;
import com.hnfealean.sport.pageset.PageModel;
import com.hnfealean.sport.pageset.SystemContext;
import com.hnfealean.sport.web.SystemException;
import com.hnfealean.sport.web.forms.product.front.ProductsSearchForm;

public class ProductsSearchAction extends DispatchAction {

	private ProductSearchManager productSearchManager;
	public void setProductSearchManager(ProductSearchManager productSearchManager) {
		this.productSearchManager = productSearchManager;
	}
	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		return mapping.findForward("index");
	}
	public ActionForward search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ProductsSearchForm searchForm = (ProductsSearchForm)form;
//		System.out.println(searchForm.getKey());
//		System.out.println(new String(searchForm.getKey().getBytes(),"Unicode"));
//		System.out.println(new String(searchForm.getKey().getBytes(),"UTF-8"));
//		System.out.println(new String(searchForm.getKey().getBytes(),"UTF-16"));
//		System.out.println(new String(searchForm.getKey().getBytes(),"GBK"));
		//searchForm.setKey(new String(searchForm.getKey().getBytes(),"UTF-8"));
		
		if(searchForm.getKey()==null||searchForm.getKey().length()==0){
			throw new SystemException("非法参数！");
		}
//		if(searchForm.getOffset()==0){
//			if(request.getParameter("pager.offset")!=null){
//				System.out.println(request.getParameter("pager.offset"));
//				searchForm.setOffset(Integer.valueOf(request.getParameter("pager.offset")));
//			}
//		}
		PageModel pm = productSearchManager.search(searchForm.getKey(),SystemContext.getPage(),SystemContext.getPagesize());// searchForm.getPagesize());
		request.setAttribute("pm", pm);
		return mapping.findForward("searchresult");
	}
}
