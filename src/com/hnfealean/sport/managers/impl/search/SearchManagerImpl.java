package com.hnfealean.sport.managers.impl.search;

import org.compass.spring.CompassDaoSupport;

import com.hnfealean.sport.managers.impl.product.ProductSearchResultCallBack;
import com.hnfealean.sport.pageset.PageModel;

public class SearchManagerImpl extends CompassDaoSupport{
	public PageModel search(String key,int offset,int pagesize) {
		PageModel pm =(PageModel) getCompassTemplate().execute(new ProductSearchResultCallBack(key, offset, pagesize));
		return pm;
	}
}
