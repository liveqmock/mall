package com.hnfealean.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hnfealean.sport.managers.product.ProductSearchManager;
import com.hnfealean.sport.model.product.Product;
import com.hnfealean.sport.pageset.PageModel;
public class TestBase64{
	private static ProductSearchManager productSearchManager;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("sssss");
		try {
			AbstractApplicationContext cxt = new ClassPathXmlApplicationContext("applicationContext-*.xml");
			
			productSearchManager = (ProductSearchManager)cxt.getBean("productSearchManager");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	@Test public void encode(){
		String url = "编码后的字符串";//原文
		String code = new String(Base64.encodeBase64(url.getBytes()));//编码后的字符串
		
		//String url = new String(Base64.encodeBase64("/customer/shopping/comfirm.do?id=xxx&name=xxx".getBytes()));
		System.out.println(code);
		System.out.println(Base64.decodeBase64(code.getBytes()));
	}
	
	@Test public void decode(){
		String url = new String(Base64.decodeBase64("L2N1c3RvbWVyL3Nob3BwaW5nL29yZGVyY29uZmlybS5kbz9pZD0xJm5hbWU9eHh4".getBytes()));
		System.out.println(url);
	}
	
	@Test public void xxx(){
		SimpleDateFormat format = new SimpleDateFormat("yyMMddHH");
		String time = format.format(new Date());
		
		//Date todayDate = format.parse(time.replaceAll("\\d{2}\\b", "00"), new ParsePosition(0));
		System.out.println(time);
	}
@Test 
public void aa(){
	//System.out.println("time");
	PageModel pm = productSearchManager.search("456",100,0);
	System.out.println(pm.getTotal());
	List<Product> list = pm.getDatas();
	System.out.println("list.size():"+list.size());
	for(Product p :list){
		System.out.println(p.getName());
		System.out.println(p.getImagesAndStyles().size());
	}
	System.out.println(pm.getDatas());
	
	
}
}
