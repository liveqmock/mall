package com.hnfealean.sport.managers.impl.log;

import java.util.Date;
import java.util.List;

import com.hnfealean.sport.managers.impl.product.CommonManager;
import com.hnfealean.sport.managers.log.SystemLogManager;
import com.hnfealean.sport.model.log.AdministratorViewLog;
import com.hnfealean.sport.model.log.UserViewLog;
import com.hnfealean.sport.pageset.PageModel;
import com.hnfealean.sport.pageset.SystemContext;

public class SystemLogManagerImpl extends CommonManager implements SystemLogManager{

	/* (non-Javadoc)
	 * @see com.hnfealean.sport.managers.impl.log.SystemLogManager#addUserLog(com.hnfealean.sport.model.log.UserViewLog)
	 */
	public void addUserLog(UserViewLog log){
		getHibernateTemplate().save(log);
	}
	/* (non-Javadoc)
	 * @see com.hnfealean.sport.managers.impl.log.SystemLogManager#addAdministratorViewLog(com.hnfealean.sport.model.log.AdministratorViewLog)
	 */
	public void addAdministratorViewLog(AdministratorViewLog log){
		getHibernateTemplate().save(log);
	}
	/* (non-Javadoc)
	 * @see com.hnfealean.sport.managers.impl.log.SystemLogManager#deleteUserLog(int)
	 */
	public void deleteUserLog(int id){
		getSession().createQuery("delete from UserViewLog where id=?")
			.setParameter(0, id)
			.executeUpdate();
	}
	/* (non-Javadoc)
	 * @see com.hnfealean.sport.managers.impl.log.SystemLogManager#deleteAdministratorLog(int)
	 */
	public void deleteAdministratorLog(int id){
		getSession().createQuery("delete from AdministratorViewLog where id=?")
		.setParameter(0, id)
		.executeUpdate();
	}
	
	/* (non-Javadoc)
	 * @see com.hnfealean.sport.managers.impl.log.SystemLogManager#getUserLog(int)
	 */
	public UserViewLog getUserLog(int id){
		return (UserViewLog) getHibernateTemplate().get(UserViewLog.class, id);
	}
	
	/* (non-Javadoc)
	 * @see com.hnfealean.sport.managers.impl.log.SystemLogManager#getAdministratorLog(int)
	 */
	public AdministratorViewLog getAdministratorLog(int id){
		return (AdministratorViewLog) getHibernateTemplate().get(AdministratorViewLog.class, id);
	}
	
	/* (non-Javadoc)
	 * @see com.hnfealean.sport.managers.impl.log.SystemLogManager#getUserLog(java.util.Date)
	 */
	public List<UserViewLog> getUserLog(Date date){
		return(List<UserViewLog>)getSession().createQuery("from UserViewLog where inTime=?")
			.setParameter(0, date).list();
			
	}
	public List<UserViewLog> getUserLogsByTime(Date beginDate,Date endDate) {
		return(List<UserViewLog>)getSession().createQuery("from UserViewLog where inTime between ? and ?")
		.setParameter(0, beginDate)
		.setParameter(1, endDate).list();
	}
	public PageModel getUserLogsByDate(Date beginDate,Date endDate) {
		return searchPaginated("from UserViewLog where inTime between ? and ?", new Object[]{beginDate,endDate});
	}
	public Long getIPCount(Date beginDate, Date endDate) {
		try {
			Long count = (Long)getSession().createQuery("select count(distinct ip) from UserViewLog where inTime between ? and ?")
			.setParameter(0, beginDate)
			.setParameter(1, endDate).iterate().next();
			return count;
		} catch (Exception e) {
			
			return 0L;
		} 
		
	}
	public PageModel getDistinctDetail(Date beginDate, Date endDate){
		PageModel pm = new PageModel();
		int total = ((Long)getSession().createQuery("select count(distinct ip) from UserViewLog where inTime between ? and ?")
							.setParameter(0, beginDate)
							.setParameter(1, endDate)
							.uniqueResult()).intValue();
		pm.setTotal(total);
		if(total ==0)	return pm;
		List<UserViewLog> datas = getSession().createQuery("select new UserViewLog(ip,zone,min(inTime),inUrl,referer,userId,userEmail) from UserViewLog where inTime between ? and ? group by ip")
							.setParameter(0, beginDate)
							.setParameter(1, endDate)
							.setMaxResults(SystemContext.getPagesize())
							.setFirstResult(SystemContext.getOffset())
							.list();
		pm.setDatas(datas);
		return	pm;
	}
	public PageModel getDetailByIp(String ip, Date beginDate, Date endDate) {
		return	searchPaginated("from UserViewLog where ip=? and inTime between ? and ?",
				new Object[]{ip,beginDate,endDate},SystemContext.getOffset(),50);
	}
	public PageModel getDetailByUserId(int userId, Date beginDate, Date endDate) {
		return	searchPaginated("from UserViewLog where userId=? and inTime between ? and ?",
				new Object[]{userId,beginDate,endDate},SystemContext.getOffset(),50);
	}
	public Long getPVCount(Date beginDate, Date endDate) {
		try {
			Long count = (Long)getSession().createQuery("select count(id) from UserViewLog where inTime between ? and ?")
			.setParameter(0, beginDate)
			.setParameter(1, endDate).iterate().next();
			return count;
		} catch (Exception e) {
			
			return 0L;
		} 
	}
	public PageModel getAllDetails(Date beginDate, Date endDate) {

		return	searchPaginated("from UserViewLog where inTime between ? and ? order by ip",
				new Object[]{beginDate,endDate},SystemContext.getOffset(),50);
	}
}

