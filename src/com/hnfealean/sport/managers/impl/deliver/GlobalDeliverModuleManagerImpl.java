package com.hnfealean.sport.managers.impl.deliver;

import java.util.List;

import com.hnfealean.sport.enums.ConstantString;
import com.hnfealean.sport.managers.deliver.GlobalDeliverModuleManager;
import com.hnfealean.sport.managers.impl.product.CommonManager;
import com.hnfealean.sport.model.deliver.GlobalDeliverModule;
import com.hnfealean.sport.model.deliver.GlobalDistributionTemplate;
import com.hnfealean.sport.pageset.PageModel;

public class GlobalDeliverModuleManagerImpl extends CommonManager implements
		GlobalDeliverModuleManager {

	public void addGlobalDeliverModule(GlobalDeliverModule module) {
		getHibernateTemplate().save(module);

	}

	public void deleteGlobalDeliverModule(Integer id) {
		GlobalDeliverModule module = (GlobalDeliverModule)getSession().createQuery("from GlobalDeliverModule module where module.id=? and module.globalTemplate.permanent=false")
		.setParameter(0, id)
		.setMaxResults(1)
		.uniqueResult();
		if(module==null)return;
		getHibernateTemplate().delete(module);
		//getSession().createQuery("delete from GlobalDeliverModule module where module.id=? and module.globalTemplate.permanent=false")
		//.setParameter(0, id).executeUpdate();

	}

	public void deleteGlobalDeliverModules(Integer[] ids) {
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<ids.length;i++){
			sb.append(ids[i]+",");
		}

		if(sb!=null&&sb.length()>0&&sb.charAt(sb.length()-1)==",".charAt(0))
			sb.deleteCharAt(sb.length()-1);
		getSession().createQuery("delete from GlobalDeliverModule module where module.id in("+sb.toString()+")")
		.executeUpdate();
	}

	public List<GlobalDeliverModule> getAllGlobalDeliverModules() {
		return (List<GlobalDeliverModule>)getSession().createQuery("from GobalDeliverModule").list();
		
	}

	public GlobalDeliverModule loadGlobalDeliverModule(Integer id) {
		return (GlobalDeliverModule)getHibernateTemplate().get(GlobalDeliverModule.class, id);
		
	}

	public void updateGlobalDeliverModule(GlobalDeliverModule module) {
		getHibernateTemplate().update(module);

	}



	public PageModel getAllDeliverTypes(boolean ispagemodel) {
		return searchPaginated("from GlobalDeliverModule");
	}

	public GlobalDeliverModule updateglobalDeliverModule(
			GlobalDeliverModule module, Integer id) {
		
		getHibernateTemplate().update(module);
		module.setId(id);
		return module;
	}

	public List<GlobalDeliverModule> getGlobalDeliverModules(Integer[] ids) {
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<ids.length;i++){
			sb.append(ids[i]+",");
		}

		if(sb!=null&&sb.length()>0&&sb.charAt(sb.length()-1)==",".charAt(0))
			sb.deleteCharAt(sb.length()-1);
		return getSession().createQuery("from GlobalDeliverModule module where module.id in("+sb.toString()+")")
		.list();
	}
	public PageModel getGlobalDeliverModules(Integer[] ids,boolean ispagemodel) {
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<ids.length;i++){
			sb.append(ids[i]+",");
		}

		if(sb!=null&&sb.length()>0&&sb.charAt(sb.length()-1)==",".charAt(0))
			sb.deleteCharAt(sb.length()-1);
		PageModel pm = new PageModel();
		int total = ((Long)getSession().createQuery("select count(id) from GlobalDeliverModule where id in("+sb.toString()+")").uniqueResult()).intValue();
		pm.setTotal(total);
		if(total==0)return pm;
		List<GlobalDeliverModule> modules = (List<GlobalDeliverModule>)getSession().createQuery("from GlobalDeliverModule where id in("+sb.toString()+")").list();
		pm.setDatas(modules);
		return pm;
		}

	public void addGlobalDistributionTemplate(
			GlobalDistributionTemplate template) {
			getHibernateTemplate().save(template);
		
	}

	public List<GlobalDistributionTemplate> getAllGlobalDistributionTemplate() {
		return getSession().createQuery("from GlobalDistributionTemplate").list();
	}
	public GlobalDeliverModule getGlobalDeliverModule(String zoneName,int deliverTypeId){
		GlobalDeliverModule globalDeliverModule = (GlobalDeliverModule)getSession()
		.createQuery("from GlobalDeliverModule where zoneName=? and globalTemplate.enable=true and deliverType.id=?")
		.setParameter(0, zoneName)
		.setParameter(1, deliverTypeId)
		.setMaxResults(1)
		.uniqueResult();
	
		return globalDeliverModule;
		
	}
//	public float computeFeeByZoneNameAndDeliverTypeId(String zoneName,int deliverTypeId) {
//		GlobalDeliverModule globalDeliverModule = (GlobalDeliverModule)getSession()
//		.createQuery("from GlobalDeliverModule where zoneName=? and globalTemplate.enable=true and deliverType.id=?")
//		.setParameter(0, zoneName)
//		.setParameter(1, deliverTypeId)
//		.setMaxResults(1)
//		.uniqueResult();
//		;
//		if(globalDeliverModule==null)
//		return 0;
//		if(globalDeliverModule.getGlobalTemplate().isIgnoreProductDeliverFee()){
//			
//		}
//		return 0;
//	}

	public GlobalDistributionTemplate getGlobalDistributionTemplate(int id) {
		return (GlobalDistributionTemplate)	getSession().createQuery("from GlobalDistributionTemplate where id=?")
		.setParameter(0, id)
		.setMaxResults(1)
		.uniqueResult();
	//	return (GlobalDistributionTemplate)getHibernateTemplate().load(GlobalDistributionTemplate.class, id);
		
	}

public GlobalDeliverModule getGlobalDeliverModule(String zoneName,
		int deliverTypeId, int templateId) {
	return (GlobalDeliverModule)getSession().createQuery("from GlobalDeliverModule module where module.globalTemplate.id=? and module.zoneName=? and module.deliverType.id=?")
	.setParameter(0, templateId)
	.setParameter(1, zoneName)
	.setParameter(2, deliverTypeId)
	.setMaxResults(1)
	.uniqueResult();
	
}

public GlobalDeliverModule getGlobalDeliverModule(int id) {
	return (GlobalDeliverModule)getSession().createQuery("from GlobalDeliverModule where id=?")
	.setParameter(0,id)
	.setMaxResults(1)
	.uniqueResult();
}

public GlobalDeliverModule getGlobalDeliverModule(int id, int globalTemplateId) {
	return (GlobalDeliverModule)getSession().createQuery("from GlobalDeliverModule module where module.globalTemplate.id=? and module.id=?")
	.setParameter(0, globalTemplateId)
	.setParameter(1, id)
	.setMaxResults(1)
	.uniqueResult();
}

public GlobalDeliverModule getGlobalDeliverModuleOfOtherZones(int deliverTypeId,int templateId) {
	return (GlobalDeliverModule)getSession().createQuery("from GlobalDeliverModule module where module.globalTemplate.id=? and module.deliverType.id=? and module.zoneName=?")
	.setParameter(0, templateId)
	.setParameter(1, deliverTypeId)
	.setParameter(2, ConstantString.OTHERZONES)
	.setMaxResults(1)
	.uniqueResult();
}

public void updateTemplateApply(int templateId) {
	getSession().createQuery("update GlobalDistributionTemplate set enable=true where id=?")
	.setParameter(0, templateId).executeUpdate();
	getSession().createQuery("update GlobalDistributionTemplate set enable=false where id!=?")
	.setParameter(0, templateId).executeUpdate();
}


}
