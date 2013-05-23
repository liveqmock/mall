package com.hnfealean.sport.managers.email;

import java.util.List;

import com.hnfealean.sport.model.email.Email;

public interface EmailManager {

	public boolean addEmail(Email email);
	public boolean deleteEmail(int id);
	public boolean deleteEmails(String ids);
	public boolean updateEmail(Email email);
	public Email getEmail(int id);
	public List<Email> getEmails(String ids);
	public List<Email> getAllEmails();
	public int getDefaultEmail();
}
