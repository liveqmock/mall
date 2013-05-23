package com.hnfealean.sport.web.actions.upload;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.hnfealean.sport.enums.ConstantString;
import com.hnfealean.sport.model.file.ImageFile;
import com.hnfealean.sport.web.ImageTool;
import com.hnfealean.sport.web.SystemException;
import com.hnfealean.sport.web.forms.file.ImageFileActionForm;

public class ImageAction extends DispatchAction {

	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ImageFileActionForm ifaf =(ImageFileActionForm) form;
		File imageSourceFolder;
		if(ifaf.getFilePath()!=null&&ifaf.getFilePath().trim().length()>0&&ifaf.getFilePath().trim().startsWith("/")){
			imageSourceFolder = new File(request.getSession().getServletContext().getRealPath(ConstantString.IMAGEFOLDERNAME)+ifaf.getFilePath().trim());
			if(!ifaf.getFilePath().endsWith("/"))ifaf.setFilePath(ifaf.getFilePath()+"/");
		}
		else {
			imageSourceFolder = new File(request.getSession().getServletContext().getRealPath(ConstantString.IMAGEFOLDERNAME));
			ifaf.setFilePath("/");
		}
		if(!imageSourceFolder.exists() || imageSourceFolder.isFile()){
			throw new SystemException("图片文件夹不存在");
		}
	
		List<ImageFile> files =new ArrayList<ImageFile>();
		for(File file:imageSourceFolder.listFiles()){
			ImageFile imageFile = new ImageFile();
			if(file.isDirectory()){
				imageFile.setIsDirectory(true);
			}
			imageFile.setName(file.getName());
			files.add(imageFile);
		}
		request.setAttribute("filePath", ifaf.getFilePath().trim());
			//sb.append(file.getAbsolutePath()+"\t"+file.getName()+"\t"+file.getParent()+"\t"+
	//	file.getPath()	+"\n"	
		//);
		request.setAttribute("list", files);
/*		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("gbk");
		response.getOutputStream().write(sb.toString().getBytes());
		
		response.flushBuffer();
		return null;
		//
*/	
		return mapping.findForward("index");
		}
/*	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
				return null;
		
	}*/
	public ActionForward convertSize(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ImageFileActionForm ifaf =(ImageFileActionForm) form;
		String path = ifaf.getFilePath();
		if(path==null||path.trim().length()==0)	throw new SystemException("非法访问，请指定文件或文件夹的路径");
		System.out.println(request.getSession().getServletContext().getRealPath(ConstantString.IMAGEFOLDERNAME+path));
		File imageSource = new File(request.getSession().getServletContext().getRealPath(ConstantString.IMAGEFOLDERNAME+path));
		if(!imageSource.exists())	throw new SystemException("文件或文件夹不存在！");
		if(imageSource.isFile()){
			ImageTool.changeImge(imageSource, 50, 50, "C:\\ww.jpg");
		}else if(imageSource.isDirectory()){
			
		}
				return null;
		
	}
}
