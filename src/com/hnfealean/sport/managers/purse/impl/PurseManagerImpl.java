package com.hnfealean.sport.managers.purse.impl;

import java.util.List;

import com.hnfealean.sport.managers.impl.product.CommonManager;
import com.hnfealean.sport.managers.purse.PurseManager;
import com.hnfealean.sport.model.user_acl_module.Purse;
import com.hnfealean.sport.model.user_acl_module.PurseRecord;

public class PurseManagerImpl extends CommonManager implements PurseManager {

	public void addPurseRecord(PurseRecord record, int purseId) {
		record.setPurse(new Purse(purseId));
		getHibernateTemplate().save(record);
	}

	public void deletePurseRecord(int id) {
		getSession().createQuery("update PurseRecord record set record.purse=null where record.id=?")
			.setParameter(0, id).executeUpdate();
	}

	public PurseRecord getRecord(int id) {
		return (PurseRecord)getHibernateTemplate().get(PurseRecord.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<PurseRecord> getRecords(int purseId) {
		return getSession().createQuery("from PurseRecord record where record.purse.id=?")
				.setParameter(0, purseId).list();
	}

	public void updatePurseRecord(PurseRecord record) {
		getHibernateTemplate().update(record);
	}

}
