package com.hnfealean.test;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hnfealean.sport.managers.product.ProductManager;
public class PTest {
	private static ProductManager sm;
@BeforeClass
public static void setUpBeforeClass() throws Exception {

	try {
		AbstractApplicationContext cxt = new ClassPathXmlApplicationContext("applicationContext-*.xml");
		
		sm = (ProductManager)cxt.getBean("productManager");
		//countryManager=(CountryManager) cxt.getBean("countryManager");
	} catch (Exception e) {
		e.printStackTrace();
	}
	}
@Test public void getStock(){

//	Product p = sm.findProduct(10);
	//if(p==null)	return;
	sm.delProduct(11);
}
}
