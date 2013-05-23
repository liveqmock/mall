package com.hnfealean.sport.managers.upload;

import com.hnfealean.sport.model.upload.UploadFile;
import com.hnfealean.sport.pageset.PageModel;

public interface UploadManager {
	
	//public void addFile(UploadFile uploadFile,String fileUrl);

	public void addFile(UploadFile uploadFile);	
	
	public void delFile(int id ,String filePathDir);
	
	public void delFiles(int[] id ,String filePathDir);
	
	public void updateFile(UploadFile uploadFile, int id);
	
	public UploadFile findBUploadFile(int id);
	
	public PageModel searchUploadFiles(String likeName);
	
	public PageModel searchUploadFileAll();
}
