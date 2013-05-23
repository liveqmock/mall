package com.hnfealean.sport.managers.search;

import com.hnfealean.sport.pageset.PageModel;

public interface CommentSearchManager {
	public PageModel search(int searchType,String key, int offset, int pagesize);

	public PageModel search(int objectType, int objectId, String string, int offset,int pagesize);
}
