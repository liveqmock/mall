package com.hnfealean.sport.web.actions.product;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.hnfealean.sport.managers.product.ProductManager;
import com.hnfealean.sport.managers.product.ProductStyleManager;
import com.hnfealean.sport.model.product.ImagesAndStyle;
import com.hnfealean.sport.model.product.Product;
import com.hnfealean.sport.web.SystemException;
import com.hnfealean.sport.web.forms.product.ImagesAndStyleActionForm;
import com.sun.org.apache.commons.beanutils.BeanUtils;

public class ProductStyleAction extends DispatchAction {

	private ProductStyleManager productStyleManager;
	
	private ProductManager productManager;

	public void setProductStyleManager(ProductStyleManager productStyleManager) {
		this.productStyleManager = productStyleManager;
	}

	public void setProductManager(ProductManager productManager) {
		this.productManager = productManager;
	}

	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		int productId = Integer.valueOf((String)request.getParameter("productId"));
		if(productId==0){
			throw new SystemException("非法操作！");
		}
		/*
		ImagesAndStyleActionForm psaf = (ImagesAndStyleActionForm) form;
		
		if(psaf ==null ||psaf.getProductId() == 0){
			throw new SystemException("非法操作！");
		}*/
		request.setAttribute("pm", productStyleManager.searchProductStyles(productId));
		request.setAttribute("productId", productId);
		return mapping.findForward("list");
	}
	
	public ActionForward addInput(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ImagesAndStyleActionForm psaf = (ImagesAndStyleActionForm) form;
	
		int productId = psaf.getProductId();//Integer.valueOf((String)request.getParameter("productId"));
		if(productId==0){
			throw new SystemException("非法操作！");
		}		request.setAttribute("productId", productId);
		return mapping.findForward("addInput");
	}
	
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ImagesAndStyleActionForm psaf = (ImagesAndStyleActionForm) form;
		ImagesAndStyle productStyle = new ImagesAndStyle();
		if(psaf.getProductId() == 0){
			throw new SystemException("非法操作");
		}
/*		if(psaf.getName().trim().length() == 0 || psaf.getName().trim().equals("")){
			throw new SystemException("非法操作");
		}*/
		if(psaf.getImageFile().getFileSize() == 0){
//			BeanUtils.copyProperties(productStyle, psaf);
//			productStyleManager.addProductStyle(productStyle, psaf.getProductId());
//			request.setAttribute("message", "添加数据成功!");
//			
//			request.setAttribute("urladdress", "control/product/product/manage.do?productId=" + psaf.getProductId());
//			return mapping.findForward("add_success");
			throw new SystemException("图片不能为空！");
		}
		BeanUtils.copyProperties(productStyle, psaf);
		if(!psaf.getImageFile().getContentType().contains("image")){
			throw new SystemException("图片格式不正确！只允许上传jpg.gif.bmp.png图片");
		}
		String imagePathDir ="images/style/" + new SimpleDateFormat("yyyy/MM/dd/HH").format(new Date());

		//设置图片上传的地址：日期
		String imageRealPathDir = request.getSession().getServletContext().getRealPath("/".concat(imagePathDir));
		File imageSaveDir = new File(imageRealPathDir);
		if(!imageSaveDir.exists()) imageSaveDir.mkdirs();//如果目录不存在就创建
		//brand.setLogoUrl(baf.getLogoUrl().substring(baf.getLogoUrl().lastIndexOf(".")));
		//brandManager.addBrand(brand,FileForm);
		String imageType = psaf.getImageFile().getContentType();
		String imageName=UUID.randomUUID().toString() +"." + imageType.substring(imageType.lastIndexOf("/")+ 1).toLowerCase();
		FileOutputStream fos = new FileOutputStream(new File(imageRealPathDir,imageName));
		fos.write(psaf.getImageFile().getFileData());
		fos.close();
		
		productStyle.setImageUrl(imagePathDir + "/" +imageName);
		productStyle.setProduct(productManager.findProduct(psaf.getProductId()));
		productStyleManager.addProductStyle(productStyle , psaf.getProductId());
		request.setAttribute("message", "添加数据成功!");
		request.setAttribute("urladdress", "control/product/style/manage.do?productId=" + psaf.getProductId());
		return mapping.findForward("add_success");
	}
	
	public ActionForward editInput(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ImagesAndStyleActionForm psaf = (ImagesAndStyleActionForm) form;
		request.setAttribute("id", psaf.getId());
		ImagesAndStyle productStyle = productStyleManager.findProductStyle(psaf.getId());
		request.setAttribute("name", productStyle.getName());
		request.setAttribute("imageUrl",productStyle.getImageUrl());
		request.setAttribute("productId",productStyle.getProduct().getId());
		return mapping.findForward("editInput");
	}
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ImagesAndStyleActionForm psaf = (ImagesAndStyleActionForm) form;
		if(psaf.getId() == 0 || psaf.getProductId() == 0){
			throw new SystemException("非法操作！");
		}
/*		if(psaf.getName().trim().length() == 0 || psaf.getName().trim().equals("")){
			throw new SystemException("样式名称不能为空！");
		}*/
		if(psaf.getImageFile().getFileSize() == 0){
			
			ImagesAndStyle productStyle =new ImagesAndStyle();
			productStyle.setImageUrl(productStyleManager.getImageUrl(psaf.getId()));
			productStyle.setId(psaf.getId());
			productStyle.setName(psaf.getName().trim());
			Product p = new Product();
			p.setId(psaf.getProductId());
			productStyle.setProduct(p);
				//productStyleManager.findProductStyle(psaf.getId());
			//productStyle.setName(psaf.getName().trim());
			productStyleManager.updateProductStyle(productStyle, psaf.getProductId());
			request.setAttribute("message", "修改数据成功!");
			request.setAttribute("urladdress", "control/product/style/manage.do?productId=" + psaf.getProductId());
			return mapping.findForward("update_success");	
		}
		ImagesAndStyle productStyle = productStyleManager.findProductStyle(psaf.getId());
		
		productStyle.setName(psaf.getName().trim());


		if(!psaf.getImageFile().getContentType().contains("image")){
			throw new SystemException("图片格式不正确！只允许上传jpg.gif.bmp.png图片");
		}
		String imagePathDir ="images/style/" + new SimpleDateFormat("yyyy/MM/dd/HH").format(new Date());

		//设置图片上传的地址：日期
		String imageRealPathDir = request.getSession().getServletContext().getRealPath("/".concat(imagePathDir));
		File imageSaveDir = new File(imageRealPathDir);
		if(!imageSaveDir.exists()) imageSaveDir.mkdirs();//如果目录不存在就创建
		//brand.setLogoUrl(baf.getLogoUrl().substring(baf.getLogoUrl().lastIndexOf(".")));
		//brandManager.addBrand(brand,FileForm);
		String imageType = psaf.getImageFile().getContentType();
		String imageName=UUID.randomUUID().toString() +"." + imageType.substring(imageType.lastIndexOf("/")+ 1).toLowerCase();
		FileOutputStream fos = new FileOutputStream(new File(imageRealPathDir,imageName));
		fos.write(psaf.getImageFile().getFileData());
		fos.close();
		
		productStyle.setImageUrl(imagePathDir + "/" +imageName);
		Product p =new Product();
		p.setId(psaf.getProductId());
		productStyle.setProduct(p);
		productStyleManager.updateProductStyle(productStyle, psaf.getProductId());	
		request.setAttribute("message", "修改数据成功!");
		request.setAttribute("urladdress", "control/product/style/manage.do?productId=" + psaf.getProductId());
		return mapping.findForward("update_success");	
	}
	public ActionForward updateVisible(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ImagesAndStyleActionForm psaf = (ImagesAndStyleActionForm) form;
		if(psaf.getId() == 0 && psaf.getIds() == null){
			throw new SystemException("非法操作！");
		}
		if(psaf.getId() != 0){
			productStyleManager.updateVisible(psaf.getId());
			request.setAttribute("message", "修改数据成功!");
			request.setAttribute("urladdress", "control/product/style/manage.do?productId=" + psaf.getProductId());
			return mapping.findForward("update_success");
		}
		
		productStyleManager.updateVisible(psaf.getIds(),psaf.isVisible());
		System.out.println("asdasd" + psaf.isVisible());
		request.setAttribute("message", "修改数据成功!");
		request.setAttribute("urladdress", "control/product/style/manage.do?productId=" + psaf.getProductId());
		return mapping.findForward("update_success");	
	}
}
