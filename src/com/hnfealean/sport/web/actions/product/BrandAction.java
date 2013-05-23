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

import com.hnfealean.sport.managers.product.BrandManager;
import com.hnfealean.sport.model.product.Brand;
import com.hnfealean.sport.web.SystemException;
import com.hnfealean.sport.web.forms.product.BrandActionForm;
import com.sun.org.apache.commons.beanutils.BeanUtils;

public class BrandAction extends DispatchAction {

	BrandManager brandManager;
	public void setBrandManager(BrandManager brandManager) {
		this.brandManager = brandManager;
	}
	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BrandActionForm baf =(BrandActionForm) form;
		if(baf.getName() != null && !baf.getName().trim().equals("")){
			request.setAttribute("pm",brandManager.searchBrands(baf.getName()));
		}else{
			request.setAttribute("pm",brandManager.searchBrandAll());
		}

		return mapping.findForward("list");
	}
	public ActionForward addInput(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("addInput");
	}
	
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//System.out.println("aaaaaaaaaaaaaaaaaaaaaa");
		
		BrandActionForm baf =(BrandActionForm) form;
		if(baf.getName() == null){
			throw new SystemException("品牌名称不能为空！");
			
		}
		Brand brand = new Brand();
		
		if(baf.getImageFile().getFileSize() == 0){
			BeanUtils.copyProperties(brand, baf);
			brandManager.addBrand(brand);
			request.setAttribute("message", "添加数据成功!");
			request.setAttribute("urladdress", "control/product/brand/manage.do");
			return mapping.findForward("add_success");
		}
		BeanUtils.copyProperties(brand, baf);
		if(!baf.getImageFile().getContentType().contains("image")){
			throw new SystemException("图片格式不正确！只允许上传jpg.gif.bmp.png图片");
		}
		String imagePathDir ="images/brand/" + new SimpleDateFormat("yyyy/MM/dd/HH").format(new Date());

		//BeanUtils.copyProperties(brand, baf);
		//设置图片上传的地址：日期
		String imageRealPathDir = request.getSession().getServletContext().getRealPath("/".concat(imagePathDir));
		File imageSaveDir = new File(imageRealPathDir);
		if(!imageSaveDir.exists()) imageSaveDir.mkdirs();//如果目录不存在就创建
		//brand.setLogoUrl(baf.getLogoUrl().substring(baf.getLogoUrl().lastIndexOf(".")));
		//brandManager.addBrand(brand,FileForm);
		String imageType = baf.getImageFile().getContentType();
		String imageName=UUID.randomUUID().toString() +"." + imageType.substring(imageType.lastIndexOf("/")+ 1).toLowerCase();
		FileOutputStream fos = new FileOutputStream(new File(imageRealPathDir,imageName));
		fos.write(baf.getImageFile().getFileData());
		fos.close();
		
		brand.setLogoUrl(imagePathDir + "/" +imageName);
		brandManager.addBrand(brand);
		request.setAttribute("message", "添加数据成功!");
		request.setAttribute("urladdress", "control/product/brand/manage.do");
		return mapping.findForward("add_success");
	}	
	
	public ActionForward queryInput(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
//		BrandActionForm baf = (BrandActionForm) form;
//		if(baf.getId() == null){
//			return mapping.findForward("list");
//		}
//		Brand brand =brandManager.findBrand(baf.getId());
//		request.setAttribute("name", brand.getName());
//		request.setAttribute("imageUrl", brand.getLogoUrl());
//		request.setAttribute("id", brand.getId());
		return mapping.findForward("queryInput");
	}	
	
	public ActionForward query(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BrandActionForm baf = (BrandActionForm) form;
		if(baf.getName() == null || baf.getName().trim().equals("")){
			throw new SystemException("查询参数错误！！！！！");
		}
		
		request.setAttribute("pm", brandManager.searchBrands(baf.getName()));
		return mapping.findForward("list");
	}	
	public ActionForward editInput(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		BrandActionForm baf = (BrandActionForm) form;
		if(baf.getId() == null){
			return mapping.findForward("list");
		}
		Brand brand =brandManager.findBrand(baf.getId());
		request.setAttribute("name", brand.getName());
		request.setAttribute("imageUrl", brand.getLogoUrl());
		request.setAttribute("id", brand.getId());
		return mapping.findForward("editInput");
	}	
	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		BrandActionForm baf =(BrandActionForm) form;
		if(baf.getId().trim() == null || baf.getId().trim().equals("")){
			throw new SystemException("非法参数！");
		}
		if(baf.getName() == null || baf.getName().trim().equals("")){
			throw new SystemException("品牌名称不能为空！");
			
		}
		Brand brand = new Brand();
		
		if(baf.getImageFile() == null || baf.getImageFile().getFileSize() == 0){
			brand.setName(baf.getName());
			brand.setId( baf.getId());
			//brandManager.addBrand(brand);
			System.out.println(brand.getName());
			System.out.println(brand.getId());
			System.out.println(brand.getLogoUrl());
			brandManager.updateBrand(brand, baf.getId());
			request.setAttribute("message", "更新数据成功!");
			request.setAttribute("urladdress", "control/product/brand/manage.do");
			return mapping.findForward("update_success");
		}
		
		//BeanUtils.copyProperties(brand, baf);
		if(!baf.getImageFile().getContentType().contains("image")){
			throw new SystemException("图片格式不正确！只允许上传jpg.gif.bmp.png图片");
		}
		String imagePathDir ="images/brand/" + new SimpleDateFormat("yyyy/MM/dd/HH").format(new Date());

		//BeanUtils.copyProperties(brand, baf);
		//设置图片上传的地址：日期
		String imageRealPathDir = request.getSession().getServletContext().getRealPath("/".concat(imagePathDir));
		File imageSaveDir = new File(imageRealPathDir);
		if(!imageSaveDir.exists()) imageSaveDir.mkdirs();//如果目录不存在就创建
		//brand.setLogoUrl(baf.getLogoUrl().substring(baf.getLogoUrl().lastIndexOf(".")));
		//brandManager.addBrand(brand,FileForm);
		String imageType = baf.getImageFile().getContentType();
		String imageName=UUID.randomUUID().toString() +"." + imageType.substring(imageType.lastIndexOf("/")+ 1).toLowerCase();
		FileOutputStream fos = new FileOutputStream(new File(imageRealPathDir,imageName));
		fos.write(baf.getImageFile().getFileData());
		fos.close();
		
		brand.setLogoUrl(imagePathDir + "/" +imageName);
		brand.setName(baf.getName());
		//brandManager.addBrand(brand);
		brandManager.updateBrand(brand, baf.getId());
		request.setAttribute("message", "更新数据成功!");
		request.setAttribute("urladdress", "control/product/brand/manage.do");
		return mapping.findForward("update_success");
	}		
}
