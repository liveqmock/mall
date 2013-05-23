package com.hnfealean.sport.managers.impl.product;

import java.util.List;

import com.hnfealean.sport.managers.product.StockManager;
import com.hnfealean.sport.pageset.PageModel;
import com.hnfealean.sport.pageset.SystemContext;

public class StockManagerImpl extends CommonManager implements StockManager {

	public List getAllStock() {
		return getSession().createQuery("from Product p ").list();
	}

	public PageModel getStocks(){
		SystemContext.setPagesize(Integer.MAX_VALUE);
		return searchPaginated("from Product p ");
	}
	public int getStockById(int pId) {
		return Integer.valueOf(getSession().createQuery("from Product where id=?").setParameter(0, pId).uniqueResult().toString());
	}

	public boolean setStockById(int stock, int pId) {
		getSession().createQuery("update Product set quantity=? and id=?")
		.setParameter(0, stock).setParameter(1, pId).executeUpdate();
		return false;
	}

	public boolean updateStock(int id, int value) {
		getSession().createQuery("update Product set quantity=? where id=?").setParameter(0, value).setParameter(1, id)
		.executeUpdate();
		return true;
	}

	public PageModel getStocks(int offset, int pagesize) {
		int total = ((Long)getSession().createQuery("select count(id) from Product")
			.uniqueResult()).intValue();
		if(total==0)return new PageModel();
		List datas = getSession().createQuery("from Product")
		.setFirstResult(offset)
		.setMaxResults(pagesize)
		.list();
		PageModel pm = new PageModel();
		pm.setTotal(total);
		pm.setDatas(datas);
		return pm;
	}

}
