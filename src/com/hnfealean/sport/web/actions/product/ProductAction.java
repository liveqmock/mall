package com.hnfealean.sport.web.actions.product;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.hnfealean.sport.enums.ConstantString;
import com.hnfealean.sport.managers.product.BrandManager;
import com.hnfealean.sport.managers.product.CategoryManager;
import com.hnfealean.sport.managers.product.ProductManager;
import com.hnfealean.sport.managers.product.ProductStyleManager;
import com.hnfealean.sport.managers.product.TagManager;
import com.hnfealean.sport.model.product.AttributeOption;
import com.hnfealean.sport.model.product.Category;
import com.hnfealean.sport.model.product.ImagesAndStyle;
import com.hnfealean.sport.model.product.Product;
import com.hnfealean.sport.model.product.ProductAttribute;
import com.hnfealean.sport.model.product.ProductDetailAttribute;
import com.hnfealean.sport.model.product.ProductDetailGroup;
import com.hnfealean.sport.model.product.ProductDetailOption;
import com.hnfealean.sport.pageset.PageModel;
import com.hnfealean.sport.web.ChineseToPinyinUtil;
import com.hnfealean.sport.web.HTMLBuilder;
import com.hnfealean.sport.web.ImageTool;
import com.hnfealean.sport.web.SystemException;
import com.hnfealean.sport.web.WebUtil;
import com.hnfealean.sport.web.forms.product.ProductActionForm;
import com.sun.org.apache.commons.beanutils.BeanUtils;

public class ProductAction extends DispatchAction {
	
	private ProductManager productManager;

	private CategoryManager categoryManager;

	private BrandManager brandManager;
	
	private ProductStyleManager productStyleManager;
	private TagManager tagManager;
	
	public void setProductStyleManager(ProductStyleManager productStyleManager) {
		this.productStyleManager = productStyleManager;
	}

	public void setProductManager(ProductManager productManager) {
		this.productManager = productManager;
	}

	public void setCategoryManager(CategoryManager categoryManager) {
		this.categoryManager = categoryManager;
	}

	public void setBrandManager(BrandManager brandManager) {
		this.brandManager = brandManager;
	}

	public void setTagManager(TagManager tagManager) {
		this.tagManager = tagManager;
	}

	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WebUtil.setPage(request);
//		ProductActionForm paf = (ProductActionForm) form;
//		Product product = new Product();
//		//product.setBasePrice(100f);
//		//product.setBrand(
//		productManager.addProduct(product);
		request.setAttribute("pm", productManager.searchProductsAll());
		return mapping.findForward("list");
	}

	public ActionForward addInput(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setAttribute("pm", brandManager.searchBrandAll());
		request.setAttribute("brands", brandManager.searchBrandAll());
		return mapping.findForward("addInput");
	}


	public ActionForward selectCategory(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WebUtil.setPage(request);
		StringBuffer menu = new StringBuffer();
		ProductActionForm paf = (ProductActionForm) form;
		if(paf.getCategoryId() != 0){
		//Product product = new Product();
		//productManager.addProduct(product);
			request.setAttribute("pm", categoryManager.searchCategorys(paf.getCategoryId()));
		
		}else{
			request.setAttribute("pm", categoryManager.searchCategorys(0));
		}
		int id =paf.getCategoryId(); 
		Category p = categoryManager.findCategory(id);
		while(p != null){
			//System.out.print(p.getName());
			menu.append(p.getName());
			p = p.getParent();
		}
		//System.out.println(menu.toString());
		
		request.setAttribute("menu",menu.reverse().toString());
		return mapping.findForward("selectCategory");
		
	}
	
//	public ActionForward selectStyle(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response)
//			throws Exception {
//		WebUtil.setPage(request);
//		//ProductActionForm paf = (ProductActionForm) form;
//		//Product product = new Product();
//		//productManager.addProduct(product);
//		request.setAttribute("pm", productManager.searchProductsAll());
//		return mapping.findForward("selectStyle");
//	}
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ProductActionForm paf = (ProductActionForm) form;
		Product product = new Product();
		product.setShtml_File_Name(WebUtil.generateURL(ChineseToPinyinUtil.getFullSpell(paf.getShtml_File_Name())));
		BeanUtils.copyProperties(product, paf);
		ImagesAndStyle productStyle = new ImagesAndStyle();
		productStyle.setName(paf.getProductImagesAndStylesName());
		
		
		if(!paf.getProductImagesAndStylesImageFile().getContentType().contains("image")){
			throw new SystemException("图片格式不正确！只允许上传jpg.gif.bmp.png图片");
		}
		String imagePathDir ="images/style/" + new SimpleDateFormat("yyyy/MM/dd/HH").format(new Date());

		//设置图片上传的地址：日期
		String imageRealPathDir = request.getSession().getServletContext().getRealPath("/".concat(imagePathDir));
		File imageSaveDir = new File(imageRealPathDir);
		if(!imageSaveDir.exists()) imageSaveDir.mkdirs();//如果目录不存在就创建
		//brand.setLogoUrl(baf.getLogoUrl().substring(baf.getLogoUrl().lastIndexOf(".")));
		//brandManager.addBrand(brand,FileForm);
		String imageType = paf.getProductImagesAndStylesImageFile().getContentType();
		String imageName=UUID.randomUUID().toString() +"." + imageType.substring(imageType.lastIndexOf("/")+ 1).toLowerCase();
		FileOutputStream fos = new FileOutputStream(new File(imageRealPathDir,imageName));
		fos.write(paf.getProductImagesAndStylesImageFile().getFileData());
		fos.close();
		
		productStyle.setImageUrl(imagePathDir + "/" +imageName);
		//productStyle.setImageUrl("/images/doctemp.gif");
		
		//product.setBrand()
		//product.setProductStyles(productStyles)
		Set<ImagesAndStyle> style= new HashSet<ImagesAndStyle>();
		style.add(productStyle);
		product.setImagesAndStyles(style);
		productManager.addProduct(product, paf.getBrandId(), paf.getCategoryId());
		//productStyle.setProduct(product);
		productStyleManager.addProductStyle(productStyle , product.getId());
		updateTag(product);
		createHtml(request, product);
		request.setAttribute("pm", productManager.searchProductsAll());
		return mapping.findForward("list");
	}
	public ActionForward attributelist(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WebUtil.setPage(request);
		ProductActionForm pf = (ProductActionForm) form;
		List<ProductAttribute> pm = productManager.getAttributes();
		request.setAttribute("pm", pm);
		if(pf.getProductId()>0)
			request.setAttribute("options", productManager.getAttributeOptionsByproductId(pf.getProductId()));
		return mapping.findForward("attributelist");
	}
	public ActionForward attributeoptionlist(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		WebUtil.setPage(request);
		ProductActionForm pf = (ProductActionForm) form;
		if(pf.getAttributeId()>0)
		request.setAttribute("options", productManager.getAttributeOptions(pf.getProductId(), pf.getAttributeId()));
		return mapping.findForward("attributeoptionlist");
	}
	public ActionForward attributeoptionadd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ProductActionForm pf = (ProductActionForm) form;
		if(pf.getAttributeId()==0)
			throw new SystemException("非法参数!");
		AttributeOption option = new AttributeOption();
		option.setAttribute(new ProductAttribute(pf.getAttributeId()));
		option.setProduct(new Product(pf.getProductId()));
		option.setValue(pf.getAttributeOptionValue());
		productManager.addAttributeOption(option);
		//	request.setAttribute("options", productManager.getAttributeOptions(pf.getProductId(), pf.getAttributeId()));
	//	request.setAttribute("product", productManager.getAttributeOptions(pf.getProductId(), pf.getAttributeId()));
		request.setAttribute("message", "属性添加成功!");
		request.setAttribute("urladdress", "control/product/product/manage.do");
		return mapping.findForward("success");
	//	return mapping.findForward("attributeoptionlist");
	}
	
	public ActionForward updateattributeoption(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ProductActionForm pf = (ProductActionForm) form;
		if(pf.getAttributeOptionId()==0)
			throw new SystemException("非法参数!");
		AttributeOption option = productManager.getAttributeOptionById(pf.getAttributeOptionId());
		//request.setAttribute("option", option);
		//AttributeOption option = new AttributeOption();
		option.setValue(pf.getAttributeOptionValue());
		productManager.updateAttributeOption(option);
		request.setAttribute("message", "更新成功!");
		request.setAttribute("urladdress", "control/product/product/manage.do?method=attributelist&productId="+pf.getProductId());
		return mapping.findForward("success");
	}
	public ActionForward attributeoptionaddinput(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ProductActionForm pf = (ProductActionForm) form;
		if(pf.getAttributeId()==0)
			throw new SystemException("非法参数!");

		//request.setAttribute("product", productManager.findProduct(1));
		return mapping.findForward("attributeoptionaddinput");
	}	
	public ActionForward attributeoptioneditinput(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		ProductActionForm pf = (ProductActionForm) form;
		AttributeOption option = productManager.getAttributeOptionById(pf.getAttributeOptionId());
		request.setAttribute("option", option);
		return mapping.findForward("attributeoptioneditinput");
	}	
	private void createHtml(HttpServletRequest request, Product product) {
		
		List<Category> position = productManager.getPositon(product.getCategory().getUrl());
		File saveDir = new File(request.getSession().getServletContext().getRealPath("/product"));
		HTMLBuilder.buildHtml(product, saveDir, position, ConstantString.PRODUCT);
		List<Product> products = productManager.getProductsByIdString(
				tagManager.getRelatedIdsStrByKeywords(ConstantString.PRODUCTVALUE, product.getId(), product.getMeta_KeyWords())
				);
		//if(products==null||products.size()==0)	return;
		saveDir = new File(request.getSession().getServletContext().getRealPath("/product/related"),product.getId()+ConstantString.RELATEDPRODUCTFILESUFFIX);
		HTMLBuilder.buildHtml(products, saveDir, null, ConstantString.RELATEDPRODUCT);
	}	

	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//int productId = ((Integer)request.getAttribute("productId")).intValue();
		
		ProductActionForm paf = (ProductActionForm) form;
		if(paf.getProductId() ==0){
			throw new SystemException("错误的请求参数！");
		}
		request.setAttribute("brands", brandManager.searchBrandAll());
		Product product = productManager.findProduct(paf.getProductId());
		request.setAttribute("product", product);
/*		
		//BeanUtils.copyProperties(paf , product);
		//System.out.println(product.getCategory().getId());
		paf.setCategoryId(product.getCategory().getId());
		Category category = product.getCategory();
		request.setAttribute("category", category.getName());
		request.setAttribute("categoryId", category.getId());
		//product.setBrand()http://127.0.0.1:8080/babasport/images/doctemp.gif
		//product.setProductStyles(productStyles)
		//productManager.addProduct(product, paf.getBrandId(), paf.getCategoryId());
		//request.setAttribute("pm", productManager.searchProductsAll());
		request.setAttribute("brands", brandManager.searchBrandAll());
		request.setAttribute("productId",paf.getProductId());
		if(product.getBrand()!=null){
		request.setAttribute("brandId", product.getBrand().getId());
		request.setAttribute("brandName", product.getBrand().getName());
		}*/
		return mapping.findForward("edit");
	}
	public ActionForward createHtml(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ProductActionForm paf = (ProductActionForm) form;
		if(paf.getProductId() ==0){
			throw new SystemException("错误的请求参数！");
		}		
		Product p = productManager.findProduct(paf.getProductId());
		createHtml(request, p);
		request.setAttribute("message", "静态文件更新成功!");
		request.setAttribute("urladdress", "control/product/product/manage.do");
		return mapping.findForward("update_success");
		
	}
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//int productId = ((Integer)request.getAttribute("productId")).intValue();
		
		ProductActionForm paf = (ProductActionForm) form;
		if(paf.getProductId() ==0){
			throw new SystemException("错误的请求参数！");
		}
		
		Product product = productManager.findProduct(paf.getProductId());
		String fileName= product.getShtml_File_Name();
		/*
		product.setAdditionInfo(paf.getAdditionInfo());
		product.setBasePrice(paf.getBasePrice());
		
		product.setBrand(new Brand(paf.getBrandId()));
		
		product.setCategory(new Category(paf.getCategoryId()));
		product.setCode(paf.getCode());
		product.setDetail(paf.getDetail());
		product.setImagesAndStyles(productStyleManager.getProductStylesByProuctId(paf.getProductId()));
		product.setMarketPrice(paf.getMarketPrice());
		product.setMeta_Description(paf.getMeta_Description());
		product.setMeta_KeyWords(paf.getMeta_KeyWords());
		product.setModel(paf.getModel());
		product.setName(paf.getName());
		product.set*/
		BeanUtils.copyProperties(product , paf);
		product.setShtml_File_Name(WebUtil.generateURL(ChineseToPinyinUtil.getFullSpell(product.getShtml_File_Name())));
		if(!product.getShtml_File_Name().equals(fileName)){
			productManager.deleteProductFile(fileName);
		}
		product.setId(paf.getProductId());
		if(paf.getBrandId()!=null&&paf.getBrandId().length()>0){
			//product.setBrand(new Brand(paf.getBrandId()));
			product.setBrand(brandManager.findBrand(paf.getBrandId()));
		}
	//	product.setCategory(new Category(paf.getCategoryId()));
		if(paf.getCategoryId()>0)
		product.setCategory(categoryManager.findCategory(paf.getCategoryId()));
	//	System.out.println(product.getCategory());
	//	product.setBrand(brandManager.findBrand(paf.getBrandId()));
		product.setImagesAndStyles(productStyleManager.getProductStylesByProuctId(paf.getProductId()));
		//product.setBrand(brandManager.findBrand(paf.getBrandId()));
		productManager.updateProduct(product, paf.getProductId());
		
		updateTag(product);
	//	tagManager.addTagFromString(paf.getMeta_KeyWords(), product.getId(), ConstantString.PRODUCT);
		searchTags(request, product.getId());
		createHtml(request, product);
		//File saveDir = new File(request.getSession().getServletContext().getRealPath("/product"));
		//List<Category> position = productManager.getPositon(product.getCategory().getTitleInPage4category());
		//HTMLBuilder.buildHtml(product, saveDir, position,"Product");
		
		
		//product.setBrand()
		//product.setProductStyles(productStyles)
		//productManager.addProduct(product, paf.getBrandId(), paf.getCategoryId());
		//request.setAttribute("pm", productManager.searchProductsAll());
		request.setAttribute("brands", brandManager.searchBrandAll());

		request.setAttribute("message", "修改成功!");
		request.setAttribute("urladdress", "control/product/product/manage.do");
		return mapping.findForward("update_success");
	}

	private void updateTag(Product product) {
		tagManager.updateByTargetKeywords(product.getId(), ConstantString.PRODUCTVALUE, product.getMeta_KeyWords());
	}
	public ActionForward updateTag(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ProductActionForm paf =(ProductActionForm) form;
		if(paf.getProductId() == 0 && paf.getProductIds() == null){
			throw new SystemException("非法操作！");
		}
		Product product = productManager.findProduct(paf.getProductId());
		tagManager.updateByTargetKeywords(product.getId(), ConstantString.PRODUCTVALUE, product.getMeta_KeyWords());
		request.setAttribute("message", "产品tag更新成功!");
		request.setAttribute("urladdress", "control/product/product/manage.do");
		return mapping.findForward("update_success");
	}
	public ActionForward createAllProductHTML(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		List<Product> products = productManager.searchAllProducts();
		for(Product product:products){
			createHtml(request, product);
		}
		request.setAttribute("message", "静态文件生成成功!");
		request.setAttribute("urladdress", "control/product/product/manage.do");
		
		return mapping.findForward("update_success");
		
	}
	/**
	 * 根据当前产品的id，取得相关产品链接
	 * @param request
	 * @param productId
	 */
	private void searchTags(HttpServletRequest request, int productId) {
		/**
		 * 查找当前产品的所有Tag名称
		 */
		List tagNames = tagManager.searchNamesByTarget(productId, ConstantString.PRODUCTVALUE);
		/**
		 * 查找其他拥有这些tag的产品ID
		 */
		List otherProductsId =	tagManager.searchTargetIdsByTagNames(productId,ConstantString.PRODUCTVALUE, tagNames);
		
		/**
		 * 根据这些产品的id查找产品的名称和链接
		 */
		PageModel others ;
		if(otherProductsId!=null && otherProductsId.size()>0){
			others = productManager.searchProductsNameAndLinksByIds(otherProductsId);
		
		}else{
			others = new PageModel();
		}
		request.setAttribute("others", others);
	}	

	public ActionForward queryInput(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		request.setAttribute("brands", brandManager.searchBrandAll());	
		return mapping.findForward("queryInput");
	}	

	public ActionForward query(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ProductActionForm paf = (ProductActionForm) form;
		request.setAttribute("pm", productManager.searchProducts(paf.getName(), paf.getCategoryId(), 
				paf.getBrandId(), paf.getCode(), paf.getStartBasePrice(), 
				paf.getEndBasePrice(), paf.getStartSellPrice(), paf.getEndSellPrice())
				);
		
		return mapping.findForward("list");
	}
	public ActionForward updateVisible(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ProductActionForm paf =(ProductActionForm) form;
		if(paf.getProductId() == 0 && paf.getProductIds() == null){
			throw new SystemException("非法操作！");
		}
		if(paf.getProductId() != 0){
			productManager.updateVisible(paf.getProductId(), paf.isYesOrNo());
			request.setAttribute("message", "修改数据成功!");
			request.setAttribute("urladdress", "control/product/product/manage.do");
			return mapping.findForward("update_success");
		}
		productManager.updateVisibles(paf.getProductIds(), paf.isYesOrNo());
		request.setAttribute("message", "修改数据成功!");
		request.setAttribute("urladdress", "control/product/product/manage.do");
		return mapping.findForward("update_success");		
	}	
	
	public ActionForward createHTML(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ProductActionForm paf =(ProductActionForm) form;
		if(paf.getProductId()>0){
			
		}
		return null;
		
	}
	public ActionForward updateCommend(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ProductActionForm paf =(ProductActionForm) form;
		if(paf.getProductId() == 0 && paf.getProductIds() == null){
			throw new SystemException("非法操作！");
		}
		if(paf.getProductId() != 0){
			
			productManager.updateCommend(paf.getProductId(), paf.isYesOrNo());
			request.setAttribute("message", "修改数据成功!");
			request.setAttribute("urladdress", "control/product/product/manage.do");
			return mapping.findForward("update_success");
		}
		productManager.updateCommends(paf.getProductIds(), paf.isYesOrNo());
		request.setAttribute("message", "修改数据成功!");
		request.setAttribute("urladdress", "control/product/product/manage.do");
		return mapping.findForward("update_success");		
	}
	public ActionForward disable(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ProductActionForm paf =(ProductActionForm) form;
		if(paf.getProductId() == 0 && paf.getProductIds() == null){
			throw new SystemException("非法操作！");
		}
		productManager.disableProduct(paf.getProductId());
		request.setAttribute("message", "该产品已取消可见!");
		request.setAttribute("urladdress", "control/product/product/manage.do");

		return mapping.findForward("update_success");		
	}
	public ActionForward enable(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ProductActionForm paf =(ProductActionForm) form;
		if(paf.getProductId() == 0 ){
			throw new SystemException("非法操作！");
		}
		productManager.enableProduct(paf.getProductId());
		Product product = productManager.findProduct(paf.getProductId());
		createHtml(request, product);
		request.setAttribute("message", "该产品已恢复可见!");
		request.setAttribute("urladdress", "control/product/product/manage.do");
		return mapping.findForward("update_success");		
	}
	public ActionForward enables(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ProductActionForm paf =(ProductActionForm) form;
		if(paf.getProductIds() == null||paf.getProductIds().length == 0){
			throw new SystemException("非法操作！");
		}
		productManager.enableProducts(paf.getProductIds());
		for(int i:paf.getProductIds()){
			Product product = productManager.findProduct(i);
			createHtml(request, product);	
		}
		request.setAttribute("message", "这些产品已恢复可见!");
		request.setAttribute("urladdress", "control/product/product/manage.do");
		return mapping.findForward("update_success");		
	}
	public ActionForward disables(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ProductActionForm paf =(ProductActionForm) form;
		if(paf.getProductIds() ==null || paf.getProductIds().length == 0){
			throw new SystemException("非法操作！");
		}
		productManager.disableProducts(paf.getProductIds());
		request.setAttribute("message", "这些产品已取消可见!");
		request.setAttribute("urladdress", "control/product/product/manage.do");

		return mapping.findForward("update_success");		
	}
	public ActionForward deletePermanently(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ProductActionForm paf =(ProductActionForm) form;
		if(paf.getProductId() == 0 && paf.getProductIds() == null){
			throw new SystemException("非法操作！");
		}
		productManager.delProduct(paf.getProductId());
		request.setAttribute("message", "该产品已彻底删除!");
		request.setAttribute("urladdress", "control/product/product/manage.do");
		return mapping.findForward("update_success");
	}
	public ActionForward deletesPermanently(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ProductActionForm paf =(ProductActionForm) form;
		if(paf.getProductIds() == null||paf.getProductIds().length == 0){
			throw new SystemException("非法操作！");
		}
		productManager.delProducts(paf.getProductIds());
		request.setAttribute("message", "这些产品已彻底删除!");
		request.setAttribute("urladdress", "control/product/product/manage.do");
		return mapping.findForward("update_success");
	}
	public ActionForward editMetaTags(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ProductActionForm paf =(ProductActionForm) form;
		if(paf.getProductId() == 0){
			throw new SystemException("非法操作！");
		}
		Product p = productManager.getProductMeta(paf.getProductId());
		request.setAttribute("product", p);
		return mapping.findForward("metainput");
	}
	public ActionForward updateMetaTags(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ProductActionForm paf =(ProductActionForm) form;
		if(paf.getProductId() == 0){
			throw new SystemException("非法操作！");
		}
		String fileName =paf.getShtml_File_Name();
		if(fileName==null||fileName.trim().length()==0)
				fileName=WebUtil.generateURL(ChineseToPinyinUtil.getFullSpell(paf.getName()));
		else	fileName=WebUtil.generateURL(ChineseToPinyinUtil.getFullSpell(fileName));
		productManager.updateProductMeta(paf.getProductId(), paf.getMeta_Description(), paf.getMeta_KeyWords(), paf.getName(),fileName , paf.getTitleInPage());
		request.setAttribute("message", "产品meta已更新，您需要重建产品的shtml文件!");
		request.setAttribute("urladdress", "control/product/product/manage.do");
		return mapping.findForward("update_success");
 
	}
	public ActionForward generateSmallImagesForAllProduct(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ProductActionForm paf =(ProductActionForm) form;
		List<String> urls = productStyleManager.getAllImages();
		String basePath = request.getSession().getServletContext().getRealPath("");
		for(String url:urls){
			if(url.startsWith("images/")){
				ImageTool.changeImge(new File(basePath+"/"+url), paf.getOutputWidth(), paf.getOutputHeight(), 
						new File(basePath+url.replaceFirst("images/", 
						"/"+paf.getOutputWidth()+"_"+paf.getOutputHeight()+"/images/"))
				);
			}
		}
		request.setAttribute("message", "产品图片已更新!");
		request.setAttribute("urladdress", "control/product/product/manage.do");
		return mapping.findForward("update_success");
	}
	
	public ActionForward getProductDetails(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ProductActionForm paf =(ProductActionForm) form;
		if(paf.getProductId() == 0){
			throw new SystemException("非法操作！");
		}
		request.setAttribute("product", productManager.findProduct(paf.getProductId()));
		return mapping.findForward("product_detail_list");
	}
	public ActionForward addProductDetailGroupInput(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ProductActionForm paf =(ProductActionForm) form;
		if(paf.getProductId() == 0){
			throw new SystemException("非法操作！");
		}
		return mapping.findForward("product_detail_group_input");
	}
	public ActionForward addProductDetailGroup(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ProductActionForm paf =(ProductActionForm) form;
		if(paf.getProductId() == 0){
			throw new SystemException("非法操作！");
		}
		Product product = productManager.findProduct(paf.getProductId());
		if(product==null)throw new SystemException("找不到指定的数据!");
		ProductDetailGroup group = new ProductDetailGroup();
		group.setName(paf.getName());
		if(paf.getDetail()==null||paf.getDetail().trim().length()==0){
			
		}else{
			group.setPost(paf.getDetail().trim());
		}
		Set<ProductDetailGroup> groups = product.getGroups();
		if(groups==null){
			groups = new HashSet<ProductDetailGroup>();
			
		}
		if(groups.contains(group)==false){
			groups.add(group);
			productManager.addProductDetailGroup(group);
			productManager.updateProduct(product, product.getId());	
		}
		
		request.setAttribute("message", "添加成功!");
		request.setAttribute("urladdress", "control/product/product/manage.do?method=getProductDetails&productId="+product.getId());
		return mapping.findForward("update_success");
		}
	public ActionForward addProductDetailAttributeInput(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ProductActionForm paf =(ProductActionForm) form;
		if(paf.getId() == 0){
			throw new SystemException("非法操作！");
		}
		ProductDetailGroup group = productManager.getProductDetailGroup(paf.getId());
		if(group==null)throw new SystemException("非法操作,找不到指定数据！");
		request.setAttribute("group", group);
		
		return mapping.findForward("product_detail_attribute_input");
	}
	public ActionForward addProductDetailAttribute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ProductActionForm paf =(ProductActionForm) form;
		if(paf.getId() == 0){
			throw new SystemException("非法操作！");
		}
		ProductDetailGroup group = productManager.getProductDetailGroup(paf.getId());
		if(group==null)throw new SystemException("非法操作,找不到指定数据！");
		Set<ProductDetailAttribute> attributes = group.getDetailAttributes();
		ProductDetailAttribute attribute = new ProductDetailAttribute();
		attribute.setName(paf.getName());
		
		if(attributes==null){
			attributes=new HashSet<ProductDetailAttribute>();
		}
		if(attributes.contains(attribute)==false)attributes.add(attribute);

		productManager.addProductDetailAttribute(attribute);
		productManager.updateProductDetailGroup(group);
		request.setAttribute("message", "添加成功!");
		request.setAttribute("urladdress", "control/product/product/manage.do?method=getProductDetails&productId="+paf.getProductId());
		return mapping.findForward("update_success");
	}
	public ActionForward addProductDetailOptionInput(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ProductActionForm paf =(ProductActionForm) form;
		if(paf.getId() == 0){
			throw new SystemException("非法操作！");
		}
		ProductDetailAttribute attribute = productManager.getProductDetailAttribute(paf.getId());
		if(attribute==null)throw new SystemException("非法操作,找不到指定数据！");
		request.setAttribute("attribute", attribute);
		
		return mapping.findForward("product_detail_option_input");
	}
	public ActionForward addProductDetailOption(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ProductActionForm paf =(ProductActionForm) form;
		if(paf.getId() == 0){
			throw new SystemException("非法操作！");
		}
		ProductDetailAttribute attribute = productManager.getProductDetailAttribute(paf.getId());
		if(attribute==null)throw new SystemException("非法操作,找不到指定数据！");
		ProductDetailOption option = new ProductDetailOption();
		option.setName(paf.getName());
		option.setValue(paf.getValue());
		productManager.addProductDetailOption(option);
		Set<ProductDetailOption> options = attribute.getOptions()==null?new HashSet<ProductDetailOption>():attribute.getOptions();
		if(options.contains(option)==false){
			options.add(option);
			productManager.updateProductDetailOption(option);
		}
		
		request.setAttribute("message", "添加成功!");
		request.setAttribute("urladdress", "control/product/product/manage.do?method=getProductDetails&productId="+paf.getProductId());
		return mapping.findForward("update_success");
	}
	public ActionForward editProductDetailAttribute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ProductActionForm paf =(ProductActionForm) form;
		if(paf.getId() == 0){
			throw new SystemException("非法操作！");
		}
		ProductDetailAttribute attribute  = productManager.getProductDetailAttribute(paf.getId());
		if(attribute==null)	throw new SystemException("非法操作,找不到指定数据！");
		request.setAttribute("attribute", attribute);
		return mapping.findForward("product_detail_attribute_edit");
	}
	
		public ActionForward updateProductDetailAttribute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ProductActionForm paf =(ProductActionForm) form;
		if(paf.getId() == 0){
			throw new SystemException("非法操作！");
		}
		ProductDetailAttribute attribute  = productManager.getProductDetailAttribute(paf.getId());
		if(attribute==null)	throw new SystemException("非法操作,找不到指定数据！");
		attribute.setName(paf.getName().trim());
		productManager.updateProductDetailAttribute(attribute);
		request.setAttribute("message", "更新成功!");
		request.setAttribute("urladdress", "control/product/product/manage.do?method=getProductDetails&productId="+paf.getProductId());
		return mapping.findForward("update_success");
	}
		public ActionForward deleteProductDetailAttribute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ProductActionForm paf =(ProductActionForm) form;
		if(paf.getId() == 0){
			throw new SystemException("非法操作！");
		}
		productManager.deleteProductDetailAttribute(paf.getId());
		request.setAttribute("message", "删除成功!");
		request.setAttribute("urladdress", "control/product/product/manage.do?method=getProductDetails&productId="+paf.getProductId());
		return mapping.findForward("update_success");
	}
		public ActionForward editProductDetailOption(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ProductActionForm paf =(ProductActionForm) form;
		if(paf.getId() == 0){
			throw new SystemException("非法操作！");
		}
		ProductDetailOption option = productManager.getProductDetailOption(paf.getId());
		if(option==null)	throw new SystemException("非法操作,找不到指定数据！");
				request.setAttribute("option", option);
		
		return mapping.findForward("product_detail_option_edit");
	}
		public ActionForward updateProductDetailOption(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ProductActionForm paf =(ProductActionForm) form;
		if(paf.getId() == 0){
			throw new SystemException("非法操作！");
		}
		ProductDetailOption option = productManager.getProductDetailOption(paf.getId());
		if(option==null)	throw new SystemException("非法操作,找不到指定数据！");
		option.setName(paf.getName().trim());
		option.setValue(paf.getValue().trim());
		productManager.updateProductDetailOption(option);
		request.setAttribute("message", "更新成功!");
		request.setAttribute("urladdress", "control/product/product/manage.do?method=getProductDetails&productId="+paf.getProductId());
		return mapping.findForward("update_success");
	}
		public ActionForward deleteProductDetailOption(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ProductActionForm paf =(ProductActionForm) form;
		if(paf.getId() == 0){
			throw new SystemException("非法操作！");
		}
		productManager.deleteProductDetailOption(paf.getId());
		request.setAttribute("message", "删除成功!");
		request.setAttribute("urladdress", "control/product/product/manage.do?method=getProductDetails&productId="+paf.getProductId());
		return mapping.findForward("update_success");
	}
		public ActionForward editProductDetailGroup(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ProductActionForm paf =(ProductActionForm) form;
		if(paf.getId() == 0){
			throw new SystemException("非法操作！");
		}
		ProductDetailGroup group = productManager.getProductDetailGroup(paf.getId());
		if(group==null)	throw new SystemException("非法操作,找不到指定数据！");
		
		request.setAttribute("group", group);
		return mapping.findForward("product_detail_group_edit");
	}
			public ActionForward updateProductDetailGroup(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ProductActionForm paf =(ProductActionForm) form;
		if(paf.getId() == 0){
			throw new SystemException("非法操作！");
		}
		ProductDetailGroup group = productManager.getProductDetailGroup(paf.getId());
		if(group==null)	throw new SystemException("非法操作,找不到指定数据！");
		group.setName(paf.getName().trim());
		if(paf.getDetail()!=null)group.setPost(paf.getDetail());
		productManager.updateProductDetailGroup(group);
		request.setAttribute("message", "更新成功!");
		request.setAttribute("urladdress", "control/product/product/manage.do?method=getProductDetails&productId="+paf.getProductId());
		return mapping.findForward("update_success");
	}
		public ActionForward deleteProductDetailGroup(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ProductActionForm paf =(ProductActionForm) form;
		if(paf.getId() == 0){
			throw new SystemException("非法操作！");
		}
		ProductDetailGroup group = productManager.getProductDetailGroup(paf.getId());
		if(group==null)	throw new SystemException("非法操作,找不到指定数据！");
		productManager.deleteProductDetailGroup(paf.getId());
		request.setAttribute("message", "删除成功!");
		request.setAttribute("urladdress", "control/product/product/manage.do?method=getProductDetails&productId="+paf.getProductId());
		return mapping.findForward("update_success");
	}
//	public ActionForward formatall(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response)
//			throws Exception {
//		productManager.updateall();
//				return null;
//		
//	}
}
