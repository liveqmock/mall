package com.hnfealean.sport.managers.impl.search;

import org.compass.spring.CompassDaoSupport;

import com.hnfealean.sport.managers.search.CommentSearchManager;
import com.hnfealean.sport.pageset.PageModel;


public class CommentSearchImpl extends CompassDaoSupport implements CommentSearchManager {

	public PageModel search(int searchObjectId,int searchType, String key, int offset, int pagesize) {
		PageModel pm =(PageModel) getCompassTemplate().execute(new SearchResultCallBack(searchObjectId, searchType, key, offset, pagesize));
		return pm;
	}

	public PageModel search(int searchType, String key, int offset, int pagesize) {
		
		return null;
	}

}
