package com.hnfealean.sport.managers.deliver;

import java.util.List;

import com.hnfealean.sport.model.deliver.DeliverType;

public interface DeliverTypeManager {
	public List<DeliverType> getAllDeliverTypes();
	public DeliverType getDeliverTypeById(int id);
	public void addDeliverType(DeliverType deliverType);
	public boolean checkDeliverTypeExists(int id);
	public void updateDeliverType(DeliverType type);
	public void deleteDeliverType(int id);
}
