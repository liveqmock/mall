package com.hnfealean.sport.managers.log;

import java.util.Date;
import java.util.List;

import com.hnfealean.sport.model.log.AdministratorViewLog;
import com.hnfealean.sport.model.log.UserViewLog;
import com.hnfealean.sport.pageset.PageModel;

public interface SystemLogManager {
//所有(指定 date)
//独立IP访问(date) distinct
//指定IP(指定date、IP)
//指定User(指定date、userId)
	public void addUserLog(UserViewLog log);

	public void addAdministratorViewLog(AdministratorViewLog log);

	public void deleteUserLog(int id);

	public void deleteAdministratorLog(int id);

	public UserViewLog getUserLog(int id);

	public AdministratorViewLog getAdministratorLog(int id);

	public List<UserViewLog> getUserLog(Date date);

	public List<UserViewLog> getUserLogsByTime(Date beginDate,Date endDate);
	public PageModel getUserLogsByDate(Date beginDate,Date endDate);
	
	public Long getIPCount(Date beginDate,Date endDate);
	public PageModel getDistinctDetail(Date beginDate, Date endDate);
	public PageModel getDetailByIp(String ip,Date beginDate, Date endDate);
	public PageModel getAllDetails(Date beginDate, Date endDate);
	public PageModel getDetailByUserId(int userId,Date beginDate, Date endDate);

	public Long getPVCount(Date beginDate, Date endDate);
}