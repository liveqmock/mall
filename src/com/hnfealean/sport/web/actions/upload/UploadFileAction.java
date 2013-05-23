package com.hnfealean.sport.web.actions.upload;

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
import org.apache.struts.upload.FormFile;

import com.hnfealean.sport.managers.impl.ValidateFile;
import com.hnfealean.sport.managers.upload.UploadManager;
import com.hnfealean.sport.model.upload.UploadFile;
import com.hnfealean.sport.web.SystemException;
import com.hnfealean.sport.web.forms.upload.UploadFileActionForm;

public class UploadFileAction extends DispatchAction {

	UploadManager uploadManager;
	public void setUploadManager(UploadManager uploadManager) {
		this.uploadManager = uploadManager;
	}
	
	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		UploadFileActionForm ufaf = (UploadFileActionForm) form;
		
		if(ufaf.getName() == null || ufaf.getName().trim().equals("")){
			//为查询
			request.setAttribute("pm", uploadManager.searchUploadFileAll());
			return mapping.findForward("list");
		}
		
		request.setAttribute("pm", uploadManager.searchUploadFiles(ufaf.getName().trim()));
		return mapping.findForward("list");
	}
	public ActionForward uploadInput(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		return mapping.findForward("uploadInput");
	}
	
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		UploadFileActionForm ufaf = (UploadFileActionForm) form;
		int id[] = ufaf.getId();
		if(id.length == 0){
			throw new SystemException("没有任何删除项！");
		}
		//String filePathDir ="/images/upload/";

		//设置文件上传的地址：日期
		String fileRealPathDir = request.getSession().getServletContext().getRealPath("");//filePathDir);
		if(id.length == 1){
			uploadManager.delFile(id[0], fileRealPathDir);
			request.setAttribute("message", "删除成功！");
			request.setAttribute("urladdress", "control/uploadfile/manage.do");
			return mapping.findForward("delete_success");
		}
		uploadManager.delFiles(id ,fileRealPathDir);
		request.setAttribute("message", "删除成功！");
		request.setAttribute("urladdress", "control/uploadfile/manage.do");
		return mapping.findForward("delete_success");
	}
	
	public ActionForward upload(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		UploadFileActionForm ufaf = (UploadFileActionForm) form;

		if(ufaf.getUploadFile().getFileSize() == 0 || ufaf.getUploadFile() == null || ufaf.getUploadFile().getFileName().length()<1){
			
			throw new SystemException("没有文件上传！");
		}
		String uploadFileType = ufaf.getUploadFile().getFileName().substring(ufaf.getUploadFile().getFileName().lastIndexOf(".")+1).toLowerCase();
		FormFile formFile = ufaf.getUploadFile();
		System.out.println(formFile.getContentType());
//		if(!(ufaf.validateFileType(
//				uploadFileType,
//				ufaf.getUploadFile()))){
//			throw new SystemException("文件格式不正确！");
//		}
		if(!(new ValidateFile().validateFileType(uploadFileType, formFile))){
			throw new SystemException("错误的文件！");
		}
		String filePathDir ="/images/upload/" + new SimpleDateFormat("yyyy/MM/dd/HH").format(new Date());

		//设置文件上传的地址：日期
		String fileRealPathDir = request.getSession().getServletContext().getRealPath(filePathDir);
		File fileSaveDir = new File(fileRealPathDir);
		if(!fileSaveDir.exists()) fileSaveDir.mkdirs();//如果目录不存在就创建
		
		String fileName=UUID.randomUUID().toString() +"." + uploadFileType.toLowerCase();
		FileOutputStream fos = new FileOutputStream(new File(fileRealPathDir,fileName));
		fos.write(ufaf.getUploadFile().getFileData());
		fos.close();
		
		UploadFile uploadFile = new UploadFile();	

		uploadFile.setFileUrl(filePathDir + "/" + fileName);
		uploadFile.setCreateDate(new Date());
		
		uploadManager.addFile(uploadFile);
		
		request.setAttribute("imagepath", uploadFile.getFileUrl());
		request.setAttribute("message", "文件上传成功！");
		request.setAttribute("urladdress", "control/uploadfile/manage.do");
		return mapping.findForward("upload_success");
	}	
}
