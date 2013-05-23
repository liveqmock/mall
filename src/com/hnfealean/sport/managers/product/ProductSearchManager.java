package com.hnfealean.sport.managers.product;

import com.hnfealean.sport.pageset.PageModel;

public interface ProductSearchManager {


	public PageModel search(String key, int offset, int pagesize);
}
