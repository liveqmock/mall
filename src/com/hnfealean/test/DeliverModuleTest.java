package com.hnfealean.test;

import java.util.HashSet;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hnfealean.sport.managers.deliver.DeliverModuleManager;
import com.hnfealean.sport.model.deliver.DeliverModule;
import com.hnfealean.sport.model.deliver.DeliverType;
import com.hnfealean.sport.model.deliver.DistributionTemplate;
public class DeliverModuleTest {
	private static DeliverModuleManager deliverModuleManager;
@BeforeClass
public static void setUpBeforeClass() throws Exception {

	try {
		AbstractApplicationContext cxt = new ClassPathXmlApplicationContext("applicationContext-*.xml");
		
		deliverModuleManager = (DeliverModuleManager)cxt.getBean("deliverModuleManager");
		//countryManager=(CountryManager) cxt.getBean("countryManager");
	} catch (Exception e) {
		e.printStackTrace();
	}
	}
@Test public void addAModule(){
	DeliverType type= new DeliverType();
	type.setName("EMS测试");
	DeliverModule module = new DeliverModule();
	module.setDeliverType(type);
	module.setZoneName("浙江");
	module.setBaseFee(12.0f);
	module.setOneMoreFee(1.0f);
	deliverModuleManager.addDeliverModule(module, 0);
	
}
@Test public void addATemplate(){
	DeliverType type= new DeliverType();
	type.setName("EMS测试");
	DeliverModule module = new DeliverModule();
	module.setDeliverType(type);
	module.setZoneName("浙江");
	module.setBaseFee(12.0f);
	module.setOneMoreFee(1.0f);
	DistributionTemplate template = new DistributionTemplate();
	template.setName("测试的第一个配送模板");
	template.setDescription("模板测试添加，能否级联");
	Set<DeliverModule> modules =new HashSet<DeliverModule>();
	modules.add(module);
	template.setDelievrModules(modules);
	deliverModuleManager.addDistributionTemplate(template);
	
}
}
