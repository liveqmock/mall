package com.hnfealean.test;

import java.util.Iterator;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hnfealean.sport.managers.product.StockManager;
import com.hnfealean.sport.model.product.Product;
public class StockTest {
	private static StockManager sm;
@BeforeClass
public static void setUpBeforeClass() throws Exception {

	try {
		AbstractApplicationContext cxt = new ClassPathXmlApplicationContext("applicationContext-*.xml");
		
		sm = (StockManager)cxt.getBean("stockManager");
		//countryManager=(CountryManager) cxt.getBean("countryManager");
	} catch (Exception e) {
		e.printStackTrace();
	}
	}
@Test public void getStock(){
	List l = sm.getAllStock();
	System.out.println(l.size());
	Iterator<Product>  pi =l.iterator();
	while(pi.hasNext()){
		Product p = pi.next();
		System.out.println(p.getId()+"\t"+p.getQuantity());
	}
}
}
