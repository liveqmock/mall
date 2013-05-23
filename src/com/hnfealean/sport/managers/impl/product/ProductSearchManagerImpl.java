package com.hnfealean.sport.managers.impl.product;

import org.compass.spring.CompassDaoSupport;

import com.hnfealean.sport.managers.product.ProductSearchManager;
import com.hnfealean.sport.pageset.PageModel;

public class ProductSearchManagerImpl extends CompassDaoSupport implements
		ProductSearchManager {

	public PageModel search(String key,int offset,int pagesize) {
		PageModel pm =(PageModel) getCompassTemplate().execute(new ProductSearchResultCallBack(key, offset, pagesize));
		return pm;
	}
}
