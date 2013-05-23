package com.hnfealean.sport.managers.impl.upload;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;

import com.hnfealean.sport.managers.impl.product.CommonManager;
import com.hnfealean.sport.managers.upload.UploadManager;
import com.hnfealean.sport.model.upload.UploadFile;
import com.hnfealean.sport.pageset.PageModel;
import com.hnfealean.sport.web.SystemException;

public class UploadManagerImpl extends CommonManager implements UploadManager {


	public void addFile(UploadFile uploadFile) {
		
		getHibernateTemplate().save(uploadFile);

	}
	public void delFile(int id, String filePathDir) {
		if(id == 0){
			throw new SystemException("没有该项！");
		}
		UploadFile uploadFile = (UploadFile)getHibernateTemplate().load(UploadFile.class, id);
		
		File deletefile = new File(filePathDir + uploadFile.getFileUrl());
		if(deletefile.exists()) deletefile.delete();
		Query query = getSession().createQuery("delete from UploadFile uf where uf.id=" + id);
		query.executeUpdate();
	}

	public void delFiles(int[] id, String filePathDir) {
		if(id.length<1){
			throw new SystemException("没有任何选中项！");
		
		}
		StringBuffer temp = new StringBuffer();
		for(int i=0; i<id.length; i++){
			temp.append(id[i]+",");
		}
		System.out.println(temp.toString());
		//String temp2 = temp.
		temp.deleteCharAt(temp.length()-1);
		//delete from t_upload where id in ()
		Query query =getSession().createQuery("select fileUrl from UploadFile up where up.id in (" + temp.toString() +")");
		List list = query.list();
		for(Iterator<String> it = list.iterator(); it.hasNext();){
			String s= it.next();
			System.out.println(s);
			//System.out.println(filePathDir +s.replace("/", "\\"));
			File deletefile = new File(filePathDir + s);
			if(deletefile.exists()) deletefile.delete();
		}

		query = getSession().createQuery("delete from UploadFile uf where uf.id in (" + temp.toString() +")");
		query.executeUpdate();
	}

	public UploadFile findBUploadFile(int id) {

		return null;
	}

	public PageModel searchUploadFileAll() {

		return searchPaginated("from UploadFile");
	}

	public PageModel searchUploadFiles(String likeName) {

		return null;
	}

	public void updateFile(UploadFile uploadFile, int id) {


	}

}
