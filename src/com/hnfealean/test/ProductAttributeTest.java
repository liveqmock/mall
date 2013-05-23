package com.hnfealean.test;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hnfealean.sport.managers.product.ProductManager;
import com.hnfealean.sport.managers.shopping.ProductAttributeManager;
import com.hnfealean.sport.model.product.AttributeOption;
import com.hnfealean.sport.model.product.ProductAttribute;

public class ProductAttributeTest {
	public static ProductManager productManager;
	public static ProductAttributeManager productAttributeManager;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

		try {
			AbstractApplicationContext cxt = new ClassPathXmlApplicationContext("applicationContext-*.xml");
			productManager = (ProductManager)cxt.getBean("productManager");
			productAttributeManager = (ProductAttributeManager) cxt.getBean("productAttributeManager");
			//zm = (ZoneManager)cxt.getBean("zoneManager");
			//countryManager=(CountryManager) cxt.getBean("countryManager");
		} catch (Exception e) {
			e.printStackTrace();
		}
		}
	@Test public void initialAttribute(){
		ProductAttribute att = new ProductAttribute();
		att.setName("质地");
		productAttributeManager.addAttribute(att);
		//productAttributeManager.getById(id)
		AttributeOption option = new AttributeOption();
		option.setAttribute(att);
		//option.setProduct(new Product(1));
		option.setProduct(productManager.findProduct(1));
		option.setValue("优质");
		productManager.addAttributeOption(option);
		AttributeOption option0 = new AttributeOption();
		option0.setAttribute(att);
		option0.setProduct(productManager.findProduct(1));
		option0.setValue("劣质");
	//	System.out.println(option.getProduct().getId());
		//productManager.addAttributeOption(option);
	//	att.setName("失败");
		//productManager.
	}
}
