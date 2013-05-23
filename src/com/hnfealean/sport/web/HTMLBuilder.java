package com.hnfealean.sport.web;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.Set;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import com.hnfealean.sport.enums.ConstantString;
import com.hnfealean.sport.model.article.Article;
import com.hnfealean.sport.model.product.Product;

public  class HTMLBuilder {
	/*	private static final String PRODUCT="product";
	private static final String  PRODUCT_IMAGE="product_image";
	private static final String  PRODUCT_ATTRIBUTES="product_attributes";
	private static final String  PRODUCT_BREADCRUMB="product_breadcrumb";*/	


	
	public static void createProductMain(Product product,File saveDir){
		//产品信息主体部分
		if(!saveDir.exists()) saveDir.mkdirs();
		VelocityContext context = new VelocityContext();
		context.put("product", product);
		//context.put("product", WebProperty.class);
		context.put("constantString", new ConstantString());
		try {
			Template template = Velocity.getTemplate("product/product.vm");
			FileOutputStream outStream = new FileOutputStream(new File(saveDir, product.getShtml_File_Name()+".shtml"));
			OutputStreamWriter writer =  new OutputStreamWriter(outStream,"UTF-8");
			BufferedWriter sw = new BufferedWriter(writer);
			template.merge(context, sw);
			sw.flush();
			sw.close();
			outStream.close();
		} catch (Exception e) {
			throw new SystemException("生成产品主体静态文件失败!");
			//e.printStackTrace();
		}	
		
	}
	public static void createProductBreadCrumbs(List position,File saveDir, Product product){
		if(!saveDir.exists()) saveDir.mkdirs();
		VelocityContext context = new VelocityContext();
		//产品信息的面包屑导航部分
		context.put("position", position);
		context.put("constantString", new ConstantString());
		context.put("product", product);
		try {
			Template template = Velocity.getTemplate("product/product_breadcrumbs.vm");
			FileOutputStream outStream =  new FileOutputStream(new File(saveDir, product.getId()+".html"));
			OutputStreamWriter writer =  new OutputStreamWriter(outStream,"UTF-8");
			BufferedWriter sw = new BufferedWriter(writer);
			template.merge(context, sw);
			sw.flush();
			sw.close();
			outStream.close();		
		}catch (Exception e) {
			throw new SystemException("生成产品面包屑导航静态文件失败!");
			//e.printStackTrace();
		}	
	}
	public static void createProductImages(Set set,File saveDir,Product product){
		if(!saveDir.exists()) saveDir.mkdirs();
		VelocityContext context = new VelocityContext();
		//产品信息的图片部分
		context.put("constantString", new ConstantString());
		try {
			//产品信息的图片部分
			context.put("images", set);
			context.put("product", product);
			Template template = Velocity.getTemplate("product/product_images.vm");
			FileOutputStream	outStream = new FileOutputStream(new File(saveDir, product.getId()+".html"));
			OutputStreamWriter	writer =  new OutputStreamWriter(outStream,"UTF-8");
			BufferedWriter	sw = new BufferedWriter(writer);
			template.merge(context, sw);
			sw.flush();
			sw.close();
			outStream.close();
		}catch (Exception e) {
			throw new SystemException("生成产品图片静态文件失败!");
			//e.printStackTrace();
		}		
	}
	public static void createProductAttributes(Set set,File saveDir,int id){
		if(!saveDir.exists()) saveDir.mkdirs();
		VelocityContext context = new VelocityContext();
		context.put("constantString", new ConstantString());
		try {
		//产品信息的属性部分
			context.put("options", set);
		Template template = Velocity.getTemplate("product/product_attributes.vm");
		FileOutputStream outStream = new FileOutputStream(new File(saveDir, id+".html"));
		OutputStreamWriter writer =  new OutputStreamWriter(outStream,"UTF-8");
		BufferedWriter	sw = new BufferedWriter(writer);
		template.merge(context, sw);
		sw.flush();
		sw.close();
		outStream.close();
		}catch (Exception e) {
			throw new SystemException("生成产品属性静态文件失败!");
		}
	}
	public static void buildRelatedHtml(Object obj, File saveDir,List position,String whatToCreate){
		
	}
	public static void buildHtml(Product product, File saveDir,List position){
		try {
			VelocityContext context = new VelocityContext();
			context.put("product", product);
			context.put("constantString", new ConstantString());
			Template template = Velocity.getTemplate("product/producthtml.vm");
			FileOutputStream outStream = new FileOutputStream(new File(saveDir, product.getShtml_File_Name()+".shtml"));
			OutputStreamWriter writer =  new OutputStreamWriter(outStream,"UTF-8");
			BufferedWriter sw = new BufferedWriter(writer);
			template.merge(context, sw);
			sw.flush();
			sw.close();
			outStream.close();
		} catch (Exception e) {
			throw new SystemException("生成产品主体静态文件失败!");
			
		}	
		
	}
	public static void buildHtml(Object obj, File saveDir,List position,String whatToCreate){
		whatToCreate=whatToCreate.toLowerCase();

		try {
				VelocityContext context = new VelocityContext();
				context.put("constantString", new ConstantString());
			if(whatToCreate.equals(ConstantString.PRODUCT)){
				Product product = (Product) obj;
				createProductMain(product,saveDir);
				createProductBreadCrumbs(position,new File(saveDir+"/breadcrumbs"), product);
				createProductImages(product.getImagesAndStyles(),new File(saveDir+"/images"),product);
				createProductAttributes(product.getOptions(),new File(saveDir+"/attributes"),product.getId());
			}else if(whatToCreate.equals(ConstantString.RELATEDPRODUCT)){
			//	System.out.println(saveDir.getParentFile().getAbsoluteFile());
				//System.out.println(saveDir.getParentFile().exists());
				File parent =saveDir.getParentFile();
				if(parent!=null&&!parent.exists()){  
				 parent.mkdirs();  
				} 
				
				List<Product> related =null;
				if(obj!=null)	related= (List<Product>)obj;
				context.put("products", related);
				Template template = Velocity.getTemplate("related/relatedproduct.vm");
				FileOutputStream outStream = new FileOutputStream(saveDir);
				OutputStreamWriter writer =  new OutputStreamWriter(outStream,"UTF-8");
				BufferedWriter sw = new BufferedWriter(writer);
				template.merge(context, sw);
				sw.flush();
				sw.close();
				outStream.close();
			}else if(whatToCreate.equals(ConstantString.NEWS)){
				Article article = (Article) obj;
				context.put("news", article);
				context.put("position", position);
				Template template = Velocity.getTemplate("article/newscontent.vm");
				FileOutputStream outStream = new FileOutputStream(new File(saveDir, article.getUrl()+ConstantString.NEWSFILESUFFIX));
				OutputStreamWriter writer =  new OutputStreamWriter(outStream,"UTF-8");
				BufferedWriter sw = new BufferedWriter(writer);
				template.merge(context, sw);
				sw.flush();
				sw.close();
				outStream.close();
			}else if(whatToCreate.equals(ConstantString.BLOG)){
				Article article = (Article) obj;
				context.put("news", article);
				context.put("position", position);
				Template template = Velocity.getTemplate("article/blogcontent.vm");
				FileOutputStream outStream = new FileOutputStream(new File(saveDir, article.getUrl()+ ConstantString.BLOGFILESUFFIX));
				OutputStreamWriter writer =  new OutputStreamWriter(outStream,"UTF-8");
				BufferedWriter sw = new BufferedWriter(writer);
				template.merge(context, sw);
				sw.flush();
				sw.close();
				outStream.close();
			}else if(whatToCreate.equals(ConstantString.WIKI)){
				Article article = (Article) obj;
				context.put("wiki", article);
				context.put("position", position);
				Template template = Velocity.getTemplate("article/wikicontent.vm");
				FileOutputStream outStream = new FileOutputStream(new File(saveDir, article.getUrl()+ConstantString.WIKIFILESUFFIX));
				OutputStreamWriter writer =  new OutputStreamWriter(outStream,"UTF-8");
				BufferedWriter sw = new BufferedWriter(writer);
				template.merge(context, sw);
				sw.flush();
				sw.close();
				outStream.close();
			}else if(whatToCreate.equals(ConstantString.QUESTION)){
				Article article = (Article) obj;
				context.put("question", article);
				context.put("position", position);
				Template template = Velocity.getTemplate("article/questioncontent.vm");
				FileOutputStream outStream = new FileOutputStream(new File(saveDir, article.getUrl()+ConstantString.QUESTIONFILESUFFIX));
				OutputStreamWriter writer =  new OutputStreamWriter(outStream,"UTF-8");
				BufferedWriter sw = new BufferedWriter(writer);
				template.merge(context, sw);
				sw.flush();
				sw.close();
				outStream.close();
			}else if(whatToCreate.equals(ConstantString.RELATEDARTICLE)){
				List<Article> articles = (List<Article>) obj;
				context.put("articles", articles);
				Template template = Velocity.getTemplate("related/relatedarticle.vm");
				FileOutputStream outStream = new FileOutputStream(saveDir);
				OutputStreamWriter writer =  new OutputStreamWriter(outStream,"UTF-8");
				BufferedWriter sw = new BufferedWriter(writer);
				template.merge(context, sw);
				sw.flush();
				sw.close();
				outStream.close();

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		/*			if(whatToCreate.equals(PRODUCT)){
		Product product =(Product) obj;
		context.put("product", product);
		
	//	context.put("position", position);
	//	System.out.println(product.getCategory().getTitleInPage4category());
//		product.getOptions();
//		if(product.getOptions()==null||product.getOptions().isEmpty()||product.getOptions().size()==0){
//			
//		}else{
//		Iterator it = product.getOptions().iterator();
//		while(it.hasNext()){
//			AttributeOption option = (AttributeOption) it.next();
//		//	System.out.println(option.getValue());
//		}
//		}
		//产品信息主体部分
		Template template = Velocity.getTemplate("product/product.vm");
		FileOutputStream outStream = new FileOutputStream(new File(saveDir, product.getShtml_File_Name()+".shtml"));
		OutputStreamWriter writer =  new OutputStreamWriter(outStream,"UTF-8");
		BufferedWriter sw = new BufferedWriter(writer);
		template.merge(context, sw);
		sw.flush();
		sw.close();
		outStream.close();*/
	}

}
