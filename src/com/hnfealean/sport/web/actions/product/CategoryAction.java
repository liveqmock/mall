package com.hnfealean.sport.web.actions.product;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.hnfealean.sport.enums.ConstantString;
import com.hnfealean.sport.managers.product.CategoryAddedProductListsManager;
import com.hnfealean.sport.managers.product.CategoryManager;
import com.hnfealean.sport.managers.product.FrontProductManager;
import com.hnfealean.sport.managers.product.ProductManager;
import com.hnfealean.sport.managers.user_acl_module.ACLManager;
import com.hnfealean.sport.model.product.Category;
import com.hnfealean.sport.model.product.Product;
import com.hnfealean.sport.pageset.PageModel;
import com.hnfealean.sport.web.ChineseToPinyinUtil;
import com.hnfealean.sport.web.SystemException;
import com.hnfealean.sport.web.WebUtil;
import com.hnfealean.sport.web.forms.product.CategoryActionForm;

public class CategoryAction extends DispatchAction {

	private ACLManager myAclManager;
	
	private ProductManager productManager;
	private FrontProductManager frontProductManager;
	
	public void setFrontProductManager(FrontProductManager frontProductManager) {
		this.frontProductManager = frontProductManager;
	}
	public void setProductManager(ProductManager productManager) {
		this.productManager = productManager;
	}
	public void setMyAclManager(ACLManager myAclManager) {
		this.myAclManager = myAclManager;
	}
	private CategoryManager categoryManager ;

	private CategoryAddedProductListsManager categoryAddedProductsListManager;
	public void setCategoryAddedProductsListManager(
			CategoryAddedProductListsManager categoryAddedProductsListManager) {
		this.categoryAddedProductsListManager = categoryAddedProductsListManager;
	}
	public CategoryManager getCategoryManager() {
		return categoryManager;
	}
	public void setCategoryManager(CategoryManager categoryManager) {
		this.categoryManager = categoryManager;
	}
	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		CategoryActionForm caf = (CategoryActionForm) form;

		Category category;

	//	System.out.println(caf.getName());
	//	System.out.println(caf.getParentId());
	//	System.out.println(caf.getId());
		int ppid = 0,pid = 0;
		if(caf.getName() != null && !("".equals(caf.getName()))){
			//如果name存在，则为按名称搜索
				request.setAttribute("pm", categoryManager.searchCategorys(caf.getName()));
		}else{
			if(caf.getParentId() ==0){
				request.setAttribute("pm",categoryManager.searchCategorys(0));
			}else{
				category = categoryManager.findCategory(caf.getParentId());
				Category parent = category.getParent();
				if(parent != null){
					ppid = categoryManager.findCategory(parent.getId()).getId();
				}
				request.setAttribute("pm",categoryManager.searchCategorys(caf.getParentId()));
			}
		}


		if(caf.getParentId() != 0){

		}
		
		request.setAttribute("ppid", ppid);
		request.setAttribute("pid",caf.getParentId());
		return mapping.findForward("list");
	}
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CategoryActionForm categoryActionForm = (CategoryActionForm) form;
		if(categoryActionForm.getId()==0)	throw new SystemException("非法访问");
		Category category = categoryManager.findCategory(categoryActionForm.getId());
		if(category==null){
			request.setAttribute("message", "该分类已彻底删除!");
			request.setAttribute("urladdress", "control/product/category/manage.do"
					+request.getParameter("page")==null?"":"&page="+request.getParameter("page")
			);
			return mapping.findForward("add_success");
		}
		int[] ids=categoryManager.getProductIdsArray(categoryActionForm.getId());
		productManager.delProducts(ids);
		categoryManager.delCategoryPermanently(categoryActionForm.getId());
		request.setAttribute("message", "该分类已彻底删除!");
		request.setAttribute("urladdress", "control/product/category/manage.do"
				+request.getParameter("page")==null?"":"?page="+request.getParameter("page")
		);
		return mapping.findForward("add_success");

	}
	public ActionForward addInput(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		return mapping.findForward("addInput");
	}

	//2010.12.11 19:50,已添加中文转拼音功能
	@SuppressWarnings("deprecation")
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		CategoryActionForm categoryActionForm = (CategoryActionForm) form;
		
		Category category = new Category();
		if(categoryActionForm.getImageFile().getFileSize()>0){
			if(!categoryActionForm.getImageFile().getContentType().contains("image")){
				throw new SystemException("图片格式不正确！只允许上传jpg.gif.bmp.png图片");
			}
			String imagePathDir ="images/category/" + new SimpleDateFormat("yyyy/MM/dd/HH").format(new Date());

			//BeanUtils.copyProperties(brand, baf);
			//设置图片上传的地址：日期
			String imageRealPathDir = request.getSession().getServletContext().getRealPath("/".concat(imagePathDir));
			File imageSaveDir = new File(imageRealPathDir);
			if(!imageSaveDir.exists()) imageSaveDir.mkdirs();//如果目录不存在就创建
			//brand.setLogoUrl(baf.getLogoUrl().substring(baf.getLogoUrl().lastIndexOf(".")));
			//brandManager.addBrand(brand,FileForm);
			String imageType = categoryActionForm.getImageFile().getContentType();
			String imageName=UUID.randomUUID().toString() +"." + imageType.substring(imageType.lastIndexOf("/")+ 1).toLowerCase();
			FileOutputStream fos = new FileOutputStream(new File(imageRealPathDir,imageName));
			fos.write(categoryActionForm.getImageFile().getFileData());
			fos.close();
			
			categoryActionForm.setImageUrl(imagePathDir + "/" +imageName);
			
		}
		BeanUtils.copyProperties(category, categoryActionForm);
		if("".equals(category.getName().trim())){
			//category.setName("noName");
			throw new SystemException("名称不能为空");
		}
		
		if(categoryActionForm.getUrl()==null||categoryActionForm.getUrl().trim().length()==0){
			category.setUrl(WebUtil.generateURL(ChineseToPinyinUtil.getFullSpell(category.getName().trim())));
		}else{
			category.setUrl(WebUtil.generateURL(ChineseToPinyinUtil.getFullSpell(categoryActionForm.getUrl().trim())));
		}
		
		categoryManager.addCategory(category, categoryActionForm.getParentId());
		
		request.setAttribute("message", "添加数据成功!");
		request.setAttribute("urladdress", "control/product/category/manage.do");
		return mapping.findForward("add_success");
	}
		
	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CategoryActionForm caf = (CategoryActionForm) form;
		if(caf.getId() != 0){
			Category c =categoryManager.findCategory(caf.getId());
			request.setAttribute("category", c);
//			request.setAttribute("name", c.getName());
//			request.setAttribute("description", c.getDescription());
//			request.setAttribute("meta_KeyWords", c.getMeta_KeyWords());
//			request.setAttribute("meta_Description", c.getMeta_Description());
//			request.setAttribute("titleInPage4category", c.getTitleInPage4category());
//			request.setAttribute("imageUrl", c.getImageUrl());
//			request.setAttribute("url", c.getUrl());
			//System.out.print(c.getImageUrls());
			//System.out.print( c.getMeta_Description());
			//System.out.print(c.getTitleInPage4category());
			//request.setAttribute("folderName", c.getCNameInBrowser());
			//request.setAttribute("TitleInPage4category", c.getTitleInPage4category());
		}else{
			return mapping.findForward("list");
		}
		return mapping.findForward("edit");
	}
	
	public ActionForward editMetaTags(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CategoryActionForm caf = (CategoryActionForm) form;
		if(caf.getId()==0)	throw new SystemException("非法访问！");
		request.setAttribute("category", categoryManager.findCategory(caf.getId()));
		return mapping.findForward("editMetaTags");
	}
	
	public ActionForward addedProductsModuleInput(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CategoryActionForm caf = (CategoryActionForm) form;
		if(caf.getId()==0)	throw new SystemException("非法访问！");
		//String m = frontProductManager.searchAllChildCategoriesId(caf.getId());
		//System.out.println(m);
		PageModel pm = frontProductManager.searchChildProductsAll(caf.getId());
		request.setAttribute("pm", pm);
		request.setAttribute("top10", categoryAddedProductsListManager.searchByCategoryId(caf.getId(), ConstantString.TOPTEN));
		request.setAttribute("hotsell", categoryAddedProductsListManager.searchByCategoryId(caf.getId(), ConstantString.HOTSELL));
		request.setAttribute("recommend", categoryAddedProductsListManager.searchByCategoryId(caf.getId(), ConstantString.RECOMMEND));
		return mapping.findForward("addedProductsModuleInput");
	}
	public ActionForward setAdded(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CategoryActionForm caf = (CategoryActionForm) form;
		if(caf.getId()==0)	throw new SystemException("非法访问！");
		if(caf.getAddedType()==null)	throw new SystemException("非法访问！");
		if(caf.getAddedType().equals(ConstantString.RECOMMEND)==false
				&&
				caf.getAddedType().equals(ConstantString.TOPTEN)==false
				&&
				caf.getAddedType().equals(ConstantString.HOTSELL)==false){
			throw new SystemException("非法访问！");
		}
		categoryAddedProductsListManager.update(caf.getId(), caf.getProductId(), caf.getAddedType(),caf.getAddOrDel());
		response.reset();
		response.getWriter().write("ok");
		response.flushBuffer();
		return null;
	}
/*	public ActionForward addedProductsModule(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
//		CategoryActionForm caf = (CategoryActionForm) form;
//		
//		if(caf.getTop10().trim().length()>0)	
//			addprolist(caf, caf.getTop10().split(","),ConstantString.TOPTEN);
//		if(caf.getRecommend().trim().length()>0)
//			addprolist(caf, caf.getRecommend().split(","),ConstantString.RECOMMEND);
//		if(caf.getBestseller().trim().length()>0)
//			addprolist(caf, caf.getBestseller().split(","),ConstantString.HOTSELL);
//		request.setAttribute("message", "添加数据成功!");
//		request.setAttribute("urladdress", "control/product/category/manage.do?method=listaddedProductsModule&id="+caf.getId());
		return mapping.findForward("add_success");
	}*/
/*	private void addprolist(CategoryActionForm caf, String[] top10ids,String typeName) {
		CategoryAddedProductsListModule m= new CategoryAddedProductsListModule();
		m.setCategoryId(caf.getId());
		m.setName(typeName);
		Set l =new HashSet();
		for(int i=0;i<top10ids.length;i++){
			l.add(new Product(Integer.valueOf(top10ids[i])));
		}
		//l.add(new Category(1));l.add(new Category(2));
		m.setProductIds(l);
		categoryAddedProductsListManager.add(m);
	}	*/
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CategoryActionForm caf = (CategoryActionForm) form;
		if(caf.getName()== null){
			
			throw new SystemException("名称不能为空");
		}
		if(caf.getId() != 0){
			Category c =categoryManager.findCategory(caf.getId());
			c.setName(caf.getName());
			c.setDescription(caf.getDescription());
			c.setMeta_Description(caf.getMeta_Description());
			c.setMeta_KeyWords(caf.getMeta_KeyWords());		
			c.setTitleInPage4category(caf.getTitleInPage4category());
			c.setOrderNo(caf.getOrderNo());
			if(caf.getUrl()==null||caf.getUrl().trim().length()==0){
				c.setUrl(WebUtil.generateURL(ChineseToPinyinUtil.getFullSpell(caf.getName().trim())));
			}else{
				c.setUrl(WebUtil.generateURL(ChineseToPinyinUtil.getFullSpell(caf.getUrl().trim())));
			}		
			if(caf.getImageFile().getFileSize()>0){
				if(!caf.getImageFile().getContentType().contains("image")){
					throw new SystemException("图片格式不正确！只允许上传jpg.gif.bmp.png图片");
				}
				String imagePathDir ="images/category/" + new SimpleDateFormat("yyyy/MM/dd/HH").format(new Date());

				//BeanUtils.copyProperties(brand, baf);
				//设置图片上传的地址：日期
				String imageRealPathDir = request.getSession().getServletContext().getRealPath("/".concat(imagePathDir));
				File imageSaveDir = new File(imageRealPathDir);
				if(!imageSaveDir.exists()) imageSaveDir.mkdirs();//如果目录不存在就创建
				//brand.setLogoUrl(baf.getLogoUrl().substring(baf.getLogoUrl().lastIndexOf(".")));
				//brandManager.addBrand(brand,FileForm);
				String imageType = caf.getImageFile().getContentType();
				String imageName=UUID.randomUUID().toString() +"." + imageType.substring(imageType.lastIndexOf("/")+ 1).toLowerCase();
				FileOutputStream fos = new FileOutputStream(new File(imageRealPathDir,imageName));
				fos.write(caf.getImageFile().getFileData());
				fos.close();
				
				c.setImageUrl(imagePathDir + "/" +imageName);
				
			}
//			if(caf.getUrl()==null||caf.getUrl().trim().length()==0){
//				c.setUrl(WebUtil.generateURL(ChineseToPinyinUtil.getFullSpell(c.getName().trim())));
//			}else{
//				c.setUrl(WebUtil.generateURL(ChineseToPinyinUtil.getFullSpell(caf.getUrl().trim())));
//			}
		//	System.out.println("p.getName()" + p.getName());
		//	System.out.println("p.getDescription:" + p.getDescription());
			categoryManager.updateCategory(c, caf.getId());
		}else{
			return mapping.findForward("list");
		}
		request.setAttribute("message", "更新数据成功!");
		request.setAttribute("urladdress", "control/product/category/manage.do");
		return mapping.findForward("update_success");
	}	
	public ActionForward queryInput(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		return mapping.findForward("queryInput");
	}
	public ActionForward listaddedProductsModule(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CategoryActionForm caf = (CategoryActionForm) form;
		if(caf.getId()==0)	throw new SystemException("非法访问！");
		String pTop10IdStrs = categoryAddedProductsListManager.searchByCategoryId(caf.getId(), ConstantString.TOPTEN);
		if(pTop10IdStrs==null||pTop10IdStrs.trim().length()==0){	
			request.setAttribute("top10",null);
		}else{
			//String[] pTop10Ids = pTop10IdStrs.split(",");
		List<Product> top10 =productManager.getProductsByIdString(pTop10IdStrs);
		request.setAttribute("top10",top10);
		}
		
		String pHotsellIdStrs = categoryAddedProductsListManager.searchByCategoryId(caf.getId(), ConstantString.HOTSELL);
		
		if(pHotsellIdStrs==null||pHotsellIdStrs.trim().length()==0){	
			request.setAttribute("hotsell",null);
		}else{
			//String[] pHotsellIds = pHotsellIdStrs.split(",");
			List<Product> hotsell =productManager.getProductsByIdString(pHotsellIdStrs);
			request.setAttribute("hotsell",hotsell);
		}
		String pRecommendIdStrs = categoryAddedProductsListManager.searchByCategoryId(caf.getId(), ConstantString.RECOMMEND);
		
		if(pRecommendIdStrs==null||pRecommendIdStrs.trim().length()==0){	
			request.setAttribute("recommend",null);
		}else{
			//String[] pRecommendIds = pRecommendIdStrs.split(",");
			List<Product> recommend =productManager.getProductsByIdString(pRecommendIdStrs);
			request.setAttribute("recommend",recommend);
		}		

		return mapping.findForward("addedProductsModuleList");
	}
/*	public ActionForward addTOP10(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CategoryActionForm caf = (CategoryActionForm) form;
		CategoryAddedProductsListModule m= new CategoryAddedProductsListModule();
		m.setCategoryId(caf.getId());
		m.setName(ConstantString.TOPTEN);
		
		categoryAddedProductsListManager.add(m);
		return null;
	}*/
/*	public ActionForward addRecommend(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CategoryActionForm caf = (CategoryActionForm) form;
//		int[] ids = caf.getCategoryAddedProductList();
		
//		CategoryAddedProductsListModule m = new CategoryAddedProductsListModule();
//		m.setName(ConstantString.RECOMMEND);
//		m.setId(caf.getId());
//		
//		categoryAddedProductsListManager.add(m);
		request.setAttribute("message", "添加数据成功!");
		request.setAttribute("urladdress", "control/product/category/manage.do");
		return mapping.findForward("update_success");
	}*/
/*	public ActionForward setRecommend(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		return null;
	}*/
	public ActionForward updateMetaTags(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CategoryActionForm caf = (CategoryActionForm) form;
		if(caf.getId()==0)	throw new SystemException("非法访问！");
		Category category = categoryManager.findCategory(caf.getId());
		if(category ==null) throw new SystemException("不存在此类别！");
		category.setMeta_Description(caf.getMeta_Description());
		category.setMeta_KeyWords(caf.getMeta_KeyWords());
		category.setName(caf.getName());
		category.setTitleInPage4category(caf.getTitleInPage4category());
		category.setUrl(WebUtil.generateURL(caf.getUrl()));
		categoryManager.updateCategory(category, category.getId());
		request.setAttribute("message", "更新成功!");
		
		request.setAttribute("urladdress", "control/product/category/manage.do?parentId="+caf.getParentId());
		return mapping.findForward("update_success");
	}
}
