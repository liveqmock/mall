package com.hnfealean.sport.managers.deliver;

import java.util.List;

import com.hnfealean.sport.model.deliver.GlobalDeliverModule;
import com.hnfealean.sport.model.deliver.GlobalDistributionTemplate;
import com.hnfealean.sport.pageset.PageModel;

public interface GlobalDeliverModuleManager {
	public void addGlobalDistributionTemplate(GlobalDistributionTemplate template);
	public GlobalDistributionTemplate getGlobalDistributionTemplate(int id);
	public List<GlobalDistributionTemplate> getAllGlobalDistributionTemplate();
	public void addGlobalDeliverModule(GlobalDeliverModule module);
	public void deleteGlobalDeliverModule(Integer id);
	public void deleteGlobalDeliverModules(Integer[] ids);
	public void updateGlobalDeliverModule(GlobalDeliverModule module);
	public GlobalDeliverModule getGlobalDeliverModuleOfOtherZones(int deliverTypeId,int templateId);
	public GlobalDeliverModule updateglobalDeliverModule(GlobalDeliverModule module,Integer id);
	
	public GlobalDeliverModule loadGlobalDeliverModule(Integer id);
	
	public List<GlobalDeliverModule> getGlobalDeliverModules(Integer[] ids);
	public List<GlobalDeliverModule> getAllGlobalDeliverModules();
	public PageModel getAllDeliverTypes(boolean ispagemodel);
	public PageModel getGlobalDeliverModules(Integer[] ids,boolean ispagemodel);
	public GlobalDeliverModule getGlobalDeliverModule(String zoneName,int deliverTypeId);
	public GlobalDeliverModule getGlobalDeliverModule(int id);
	public GlobalDeliverModule getGlobalDeliverModule(int id,int globalTemplateId);
	public GlobalDeliverModule getGlobalDeliverModule(String zoneName,int deliverTypeId,int templateId);
	public void updateTemplateApply(int templateId);
	//public float computeFeeByZoneNameAndDeliverTypeId(String zoneName,int deliverTypeId) ;
}
