package com.hnfealean.sport.managers.impl.deliver;

import com.hnfealean.sport.managers.deliver.DeliverModuleManager;
import com.hnfealean.sport.managers.impl.product.CommonManager;
import com.hnfealean.sport.model.deliver.DeliverModule;
import com.hnfealean.sport.model.deliver.DistributionTemplate;
import com.hnfealean.sport.pageset.PageModel;

public class DeliverModuleManagerImpl extends CommonManager implements
		DeliverModuleManager {
	public void addDeliverModule(DeliverModule module){
		getHibernateTemplate().save(module);
	}
	public void addDeliverModule(DeliverModule module,
			int distributionTemplateId) {
		module.setTemplate(new DistributionTemplate(distributionTemplateId));
		DeliverModule temp = (DeliverModule)getSession().createQuery("from DeliverModule module where module.deliverType.id=? and module.zoneName=? and module.template.id=?")
		.setParameter(0, module.getDeliverType().getId())
		.setParameter(1, module.getZoneName())
		.setParameter(2, distributionTemplateId)
		.setMaxResults(1).uniqueResult();
		if(temp!=null){
			temp.setBaseFee(module.getBaseFee());
			temp.setOneMoreFee(module.getOneMoreFee());
			getHibernateTemplate().update(temp);
			return;
		}
		getHibernateTemplate().save(module);
	}



	public void addDistributionTemplate(DistributionTemplate template) {
		getHibernateTemplate().save(template);

	}
	public PageModel getAllDistributionTemplate(){
		return searchPaginated("from DistributionTemplate");
		
	}
	public void deleteDistributionTemplate(int id) {
		DistributionTemplate m = (DistributionTemplate)getHibernateTemplate().load(DistributionTemplate.class, id);
		if(m==null) return;
		getHibernateTemplate().delete(m);
		getSession().createQuery("delete from DeliverModule where template is null").executeUpdate();
	}
	public void deleteDeliverModule(int id, int templateId) {
		
		getSession().createQuery("delete from DeliverModule where id=? and template.id=?")
		.setParameter(0, id).setParameter(1, templateId).executeUpdate();
		
	}
	public DeliverModule getDeliverModule(int id) {
		return (DeliverModule)getHibernateTemplate().load(DeliverModule.class, id);
		
	}
	public void updateDeliverModule(DeliverModule module) {
		getHibernateTemplate().update(module);
		
	}
	public boolean checkDistributionTemplateExists(int id) {
		Integer temp = (Integer)getSession().createQuery("select id from DistributionTemplate where id=?").setParameter(0, id)
		.uniqueResult();
		if(temp==null)return false;
		if(temp==id&&temp!=0)return true;
		return false;
	}
	public DeliverModule getDeliverModule(String zoneName, int deliverTypeId,int productId) {
		DeliverModule module =(DeliverModule) getSession().createQuery(
"from DeliverModule module where module.deliverType.id=? and module.zoneName=? and module.template.id=(select product.distributionTemplate.id from Product product where product.id=?)")
				
		.setParameter(0, deliverTypeId)
		.setParameter(1, zoneName)
		.setParameter(2, productId).setMaxResults(1).uniqueResult();
/*		if(module==null){
			 module =(DeliverModule) 	getSession().createQuery(
			"from DeliverModule module where module.deliverType.id=? and module.zoneName=? and module.template.id=(select product.distributionTemplate.id from Product product where product.id=?)")
			.setParameter(0, deliverTypeId)
			.setParameter(1, ConstantString.OTHERZONES)
			.setParameter(2, productId).setMaxResults(1).uniqueResult();
			return module;				
		}*/
		return module;
	}
	public DistributionTemplate getDistributionTemplate(int id) {
		return (DistributionTemplate)getSession().createQuery("from DistributionTemplate where id=?")
		.setParameter(0, id)
		.setMaxResults(1)
		.uniqueResult();
	//	return	(DistributionTemplate)getHibernateTemplate().load(DistributionTemplate.class, id);

	}
	public void updateDistributionTemplate(DistributionTemplate template) {
		getHibernateTemplate().update(template);
		
	}
	

}
