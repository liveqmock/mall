package com.hnfealean.sport.managers.deliver;

import com.hnfealean.sport.model.deliver.DeliverModule;
import com.hnfealean.sport.model.deliver.DistributionTemplate;
import com.hnfealean.sport.pageset.PageModel;

public interface DeliverModuleManager {
	public void addDistributionTemplate(DistributionTemplate template);
	public void addDeliverModule(DeliverModule module,int distributionTemplateId);
	//public void addDeliverType(DeliverType deliverType);
	public PageModel getAllDistributionTemplate();
	public void deleteDistributionTemplate(int id);
	public void deleteDeliverModule(int id,int templateId);
	public DeliverModule getDeliverModule(int id);
	public boolean checkDistributionTemplateExists(int id);
	public void updateDeliverModule(DeliverModule module);
	public DeliverModule getDeliverModule(String zoneName, int deliverTypeId,int productId) ;
	public DistributionTemplate getDistributionTemplate(int id);
	public void updateDistributionTemplate(DistributionTemplate template);
	//public List<DeliverType> getAllDeliverTypes();
	//public DeliverType getDeliverTypeById(int id);
}
