package com.hnfealean.test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hnfealean.sport.managers.product.CategoryAttributeManager;
import com.hnfealean.sport.managers.product.CategoryManager;
import com.hnfealean.sport.model.product.Category;
import com.hnfealean.sport.model.product.CategoryAttribute;
import com.hnfealean.sport.model.product.CategoryAttributeOption;
public class CAttributeTest {
	private static CategoryAttributeManager sm;
	private static CategoryManager cm ;
@BeforeClass
public static void setUpBeforeClass() throws Exception {

	try {
		AbstractApplicationContext cxt = new ClassPathXmlApplicationContext("applicationContext-*.xml");
		
		sm = (CategoryAttributeManager)cxt.getBean("categoryAttributeManager");
		cm = (CategoryManager)cxt.getBean("categoryManager");
		//countryManager=(CountryManager) cxt.getBean("countryManager");
	} catch (Exception e) {
		e.printStackTrace();
	}
	}
@Test public void addCAttribute(){
	//sm.getAllCategoryAttributes(id)
/*	CategoryAttribute ca = new CategoryAttribute();
	ca.setCategory(cm.findCategory(1));
	ca.setName("网络ssss");
	sm.addCategoryAttribute(ca);*/
	CategoryAttribute ca = sm.getCategoryAttributeByCId(1);
	Set<CategoryAttributeOption> l = ca.getOptions();
	CategoryAttributeOption option = new CategoryAttributeOption();
	option.setAttribute(ca);
	option.setValue("GSssssM");

	if(l==null){
		l=new HashSet<CategoryAttributeOption>();
		l.add(option);
	}

	sm.addCategoryAttributeOption(option);

	sm.updateCategoryAttribute(ca);
	
}
}
