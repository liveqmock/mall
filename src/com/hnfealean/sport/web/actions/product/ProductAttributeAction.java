package com.hnfealean.sport.web.actions.product;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.SystemException;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.hnfealean.sport.managers.shopping.ProductAttributeManager;
import com.hnfealean.sport.model.product.ProductAttribute;
import com.hnfealean.sport.web.forms.product.ProductAttributeForm;

public class ProductAttributeAction extends DispatchAction {
private ProductAttributeManager productAttributeManager;


public void setProductAttributeManager(
		ProductAttributeManager productAttributeManager) {
	this.productAttributeManager = productAttributeManager;
}


@Override
protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
		throws Exception {
	List<ProductAttribute> pm = productAttributeManager.loadAllAttributes();
	request.setAttribute("pm", pm);
	return mapping.findForward("list");
}
public ActionForward addInput(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
		throws Exception {
	
	return mapping.findForward("input");
}
public ActionForward add(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
		throws Exception {
	ProductAttributeForm paf =(ProductAttributeForm) form; 
	if(paf.getName()==null||paf.getName().length()==0||paf.getName().trim().length()==0)
			throw new SystemException("非法参数!");
	ProductAttribute at = new ProductAttribute();
	at.setName(paf.getName().trim());
	productAttributeManager.addAttribute(at);
	request.setAttribute("message", "添加成功！");
	request.setAttribute("urladdress", "control/product/attribute/manage.do");
	return mapping.findForward("success");
}
public ActionForward edit(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
		throws Exception {
	ProductAttributeForm paf =(ProductAttributeForm) form; 
	if(paf.getId()==0) throw new SystemException("非法参数!");
	ProductAttribute at  = productAttributeManager.getById(paf.getId());
	request.setAttribute("attribute", at);
	return mapping.findForward("editinput");
}
public ActionForward update(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
		throws Exception {
	ProductAttributeForm paf =(ProductAttributeForm) form; 
	if(paf.getId()==0) throw new SystemException("非法参数!");
	ProductAttribute at  = new ProductAttribute();
	at.setName(paf.getName());
	at.setId(paf.getId());
	productAttributeManager.updateAttribute(at, paf.getId());
	
	request.setAttribute("message", "更新成功！");
//	request.setAttribute("type", af.getType());
	request.setAttribute("urladdress", "control/product/attribute/manage.do");
	return mapping.findForward("success");
}
public ActionForward delete(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
		throws Exception {
	ProductAttributeForm paf =(ProductAttributeForm) form; 
	if(paf.getId()==0) throw new SystemException("非法参数!");
	productAttributeManager.deleteAttributeById(paf.getId());
	request.setAttribute("message", "属性删除成功！");
//	request.setAttribute("type", af.getType());
	request.setAttribute("urladdress", "control/product/attribute/manage.do");
	return mapping.findForward("success");
}
}
