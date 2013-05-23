package com.hnfealean.sport.web.actions.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.hnfealean.sport.managers.product.StockManager;
import com.hnfealean.sport.pageset.PageModel;
import com.hnfealean.sport.pageset.SystemContext;
import com.hnfealean.sport.web.forms.product.StockForm;

public class StockAction extends DispatchAction {
private StockManager stockManager;
	public void setStockManager(StockManager stockManager) {
	this.stockManager = stockManager;
}
	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SystemContext.setPagesize(100);
		PageModel pm = stockManager.getStocks(
				(SystemContext.getPage()-1)*SystemContext.getPagesize(),
				SystemContext.getPagesize());
		request.setAttribute("pm", pm);
		
		return mapping.findForward("list");
	}
/*	public ActionForward editInput(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		StockForm sf = (StockForm) form;
		if(sf.getId()>0)
			stockManager.getStockById(sf.getId());
		return mapping.findForward("list");
	}*/
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		StockForm sf = (StockForm) form;
		StringBuffer stockjson = new StringBuffer();
		stockjson.append("window.hnfealean.stock({");
		if(sf.getId()>0){
			if(stockManager.updateStock(sf.getId(), sf.getStock()))
				stockjson.append("ok:1,id:"+sf.getId());
		}
		stockjson.append("})");
		//String s = "window.hnfealean.stock(";
	
		response.getOutputStream().write(stockjson.toString().getBytes());
		response.getOutputStream().flush();
		return null;
		//return mapping.findForward("list");
	}
}
