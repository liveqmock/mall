package com.hnfealean.sport.managers.shopping;

import java.util.List;

import com.hnfealean.sport.model.shopping.DeliverInfo;
import com.hnfealean.sport.model.user_acl_module.ContactInfo;
import com.hnfealean.sport.model.user_acl_module.User;

public interface DeliverManager {
	public ContactInfo addContactInfo(ContactInfo contactInfo,int userId);
	public void addDeliverInfo(DeliverInfo info);
	public User loadUser(int userId);
	public User loadUserByEmail(String email);
	public boolean updateUserContactInfo(ContactInfo completeContactInfo,int userId);
	public boolean checkCompletedContactInfo(int userId);
	public List<DeliverInfo> getUserDiliverInfos(int userId);
	public DeliverInfo checkDeliverInfoBelongsToUser(int deliverInfoId,int userId);

}
