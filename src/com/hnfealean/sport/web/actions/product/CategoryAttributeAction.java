package com.hnfealean.sport.web.actions.product;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.hnfealean.sport.managers.product.CategoryAttributeManager;
import com.hnfealean.sport.managers.product.CategoryManager;
import com.hnfealean.sport.managers.product.ProductManager;
import com.hnfealean.sport.model.product.Category;
import com.hnfealean.sport.model.product.CategoryAttribute;
import com.hnfealean.sport.model.product.CategoryAttributeOption;
import com.hnfealean.sport.model.product.Product;
import com.hnfealean.sport.web.SystemException;
import com.hnfealean.sport.web.forms.product.CategoryAttributeForm;

public class CategoryAttributeAction extends DispatchAction {

	private CategoryAttributeManager categoryAttributeManager;
	private CategoryManager categoryManager;
	private ProductManager productManager;
	public void setCategoryManager(CategoryManager categoryManager) {
		this.categoryManager = categoryManager;
	}
	public void setProductManager(ProductManager productManager) {
		this.productManager = productManager;
	}
	public void setCategoryAttributeManager(
			CategoryAttributeManager categoryAttributeManager) {
		this.categoryAttributeManager = categoryAttributeManager;
	}

	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CategoryAttributeForm caf = (CategoryAttributeForm) form;
		request.setAttribute("pm", categoryAttributeManager.getAllCategoryAttributes(caf.getCategoryId()));
		return mapping.findForward("list");
	}
	public ActionForward listoptions(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CategoryAttributeForm caf = (CategoryAttributeForm) form;
		request.setAttribute("pm", categoryAttributeManager.getCategoryAttributeOptionByAttributeId(caf.getAttributeId()));
		return mapping.findForward("listoptions");
	}
	public ActionForward addoptioninput(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//CategoryAttributeForm caf = (CategoryAttributeForm) form;
		return mapping.findForward("addcategoryattributeoptioninput");
	}
	public ActionForward addcategoryattributeinput(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CategoryAttributeForm caf = (CategoryAttributeForm) form;
		Category category = categoryManager.findCategory(caf.getCategoryId());
		if(category==null)	throw new SystemException("不存在此分类！");
		request.setAttribute("category",category);
		return mapping.findForward("addcategoryattributeinput");
	}
	public ActionForward addcategoryattribute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CategoryAttributeForm caf = (CategoryAttributeForm) form;
		if(caf.getName()==null||caf.getName().trim().length()==0)
			throw new SystemException("非法操作！");
		CategoryAttribute categoryAttribute = new CategoryAttribute();
		categoryAttribute.setName(caf.getName());
		categoryAttribute.setCategory(new Category(caf.getCategoryId()));
		categoryAttributeManager.addCategoryAttribute(categoryAttribute);
		request.setAttribute("message", "添加数据成功!");
		request.setAttribute("urladdress", "control/product/categoryattribute/manage.do?categoryId="+caf.getCategoryId());
		return mapping.findForward("add_success");
	}
	public ActionForward addcategoryattributeoption(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CategoryAttributeForm caf = (CategoryAttributeForm) form;
		if(caf.getAttributeId()==0)	throw new SystemException("非法操作！");
		CategoryAttributeOption option  = new CategoryAttributeOption();
		option.setAttribute(new CategoryAttribute(caf.getAttributeId()));
		option.setValue(caf.getValue());
		categoryAttributeManager.addCategoryAttributeOption(option);
		request.setAttribute("message", "添加数据成功!");
		request.setAttribute("urladdress", "control/product/categoryattribute/manage.do?categoryId="+caf.getCategoryId()+"&attributeId="+caf.getAttributeId());
		return mapping.findForward("add_success");
	}
	public ActionForward editcategoryattributeoption(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CategoryAttributeForm caf = (CategoryAttributeForm) form;
		if(caf.getOptionId()==0)	throw new SystemException("非法操作！");
		CategoryAttributeOption option = categoryAttributeManager.getCategoryAttributeOptionById(caf.getOptionId());
		request.setAttribute("option", option);
		return mapping.findForward("editcategoryattributeoption");
	}
	public ActionForward updatecategoryattributeoption(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CategoryAttributeForm caf = (CategoryAttributeForm) form;
		if(caf.getOptionId()==0||caf.getAttributeId()==0)	throw new SystemException("非法操作！");
		CategoryAttributeOption option = new CategoryAttributeOption();
		option.setAttribute(new CategoryAttribute(caf.getAttributeId()));
		option.setId(caf.getOptionId());
		option.setValue(caf.getValue());
		categoryAttributeManager.updateCategoryAttributeOption(option);
		request.setAttribute("message", "更新属性值成功!");
		request.setAttribute("urladdress", "control/product/categoryattribute/manage.do?categoryId="+caf.getCategoryId()+"&attributeId="+caf.getAttributeId());
		return mapping.findForward("add_success");
	}
	public ActionForward deletecategoryattributeoption(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CategoryAttributeForm caf = (CategoryAttributeForm) form;
		if(caf.getOptionId()==0)	throw new SystemException("非法操作！");
		categoryAttributeManager.deleteCategoryAttributeOption(caf.getOptionId());
		request.setAttribute("message", "删除属性值成功!");
		request.setAttribute("urladdress", "control/product/categoryattribute/manage.do?categoryId="+caf.getCategoryId()+"&attributeId="+caf.getAttributeId());
		return mapping.findForward("add_success");
	
	}
	public ActionForward deletecategoryattribute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CategoryAttributeForm caf = (CategoryAttributeForm) form;
		if(caf.getAttributeId()==0||caf.getCategoryId()==0) 	throw new SystemException("非法操作！");
		categoryAttributeManager.deleteCategoryAttribute(caf.getAttributeId(),caf.getCategoryId());
		request.setAttribute("message", "删除属性成功,该属性下所有属性值也已删除!");
		request.setAttribute("urladdress", "control/product/categoryattribute/manage.do?categoryId="+caf.getCategoryId()+"&attributeId="+caf.getAttributeId());
		return mapping.findForward("add_success");

	}
	public ActionForward attributeset(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CategoryAttributeForm caf = (CategoryAttributeForm) form;
		if(caf.getProductId()==0||caf.getCategoryId()==0)throw new SystemException("非法操作！");
		Product p =productManager.getProductOnlyNameAndCategory(caf.getProductId());
		if(p.getCategory().getId()!=caf.getCategoryId())throw new SystemException("该产品不属于此分类！");
		request.setAttribute("product", p);
		request.setAttribute("pm", categoryAttributeManager.getAllCategoryAttributes(caf.getCategoryId()));
		return mapping.findForward("product_filter");
	}
	/**
	 * 更新指定产品的类别属性
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward updateproductcategoryattribute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CategoryAttributeForm caf = (CategoryAttributeForm) form;
		if(caf.getProductId()==0||caf.getFilter().trim().length()==0)throw new SystemException("非法操作！");
		String inputFilter = caf.getFilter().trim();
		String[] inputArray = inputFilter.split("-");
		//Arrays.sort(inputArray, new CompareUtil());
		 StringBuffer inputStr = new StringBuffer();
		 if(inputArray.length>0){
		 for (int i = 0; i < inputArray.length-1; i++) {  
	        // System.out.println(inputArray[i]); 
	         inputStr.append(inputArray[i]).append("-");
	     }  
		 inputStr.append(inputArray[inputArray.length-1]);
		 }
		 categoryAttributeManager.updateproductcategoryattribute(caf.getProductId(), inputStr.toString());
		 response.reset();
		 response.getOutputStream().write("更新成功".getBytes());
		 response.getOutputStream().flush();
		 return null;
	}
	/**
	 * 初始化指定分类下的所有产品的 类别属性
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward initAllProductCategoryAttributeByCategoryId(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CategoryAttributeForm caf = (CategoryAttributeForm) form;
		if(caf.getCategoryId()==0)throw new SystemException("非法操作！");
		StringBuffer filter =new StringBuffer();
		List<CategoryAttribute> list =categoryAttributeManager.getAllCategoryAttributes(caf.getCategoryId());
		for(CategoryAttribute attribute:list){
			filter.append(attribute.getId()+"_0-");
		}
		if(filter.length()>0)
		if(filter.charAt(filter.length()-1)=="-".charAt(0))filter.deleteCharAt(filter.length()-1);
		categoryAttributeManager.updateAllProductCategoryAttributeByCategoryId(caf.getCategoryId(),filter.toString());
		request.setAttribute("message", "初始化当前分类下所有产品的类别属性成功!");
		request.setAttribute("urladdress", "control/product/categoryattribute/manage.do?categoryId="+caf.getCategoryId());
		return mapping.findForward("add_success");
	}
}
