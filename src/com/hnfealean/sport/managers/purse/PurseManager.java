package com.hnfealean.sport.managers.purse;

import java.util.List;

import com.hnfealean.sport.model.user_acl_module.PurseRecord;

public interface PurseManager {
	

	public void addPurseRecord(PurseRecord record,int purseId);
	public void deletePurseRecord(int id);
	public void updatePurseRecord(PurseRecord record);
	
	public List<PurseRecord> getRecords(int purseId);
	
	public PurseRecord getRecord(int id);
}
