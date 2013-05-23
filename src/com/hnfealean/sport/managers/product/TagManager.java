package com.hnfealean.sport.managers.product;

import java.util.List;

import com.hnfealean.sport.model.product.Tag;
import com.hnfealean.sport.pageset.PageModel;

public interface TagManager {

	public void addTag(Tag tag);
	public void addTagFromString(String tagString,int targetId,int tagType);
	
	public void updateTag(Tag tag);
	public void deleteTag(Tag tag);
	public PageModel searchAllTags();
	public List searchNamesByTarget(int id,int tagType);
	public List searchTargetIdsByTagNames(int thisId,int tagType,List tagNames);
	public List searchTargetIdsByTagNames(int thisId,int tagType,String tagNames);
	
	//根据目标对象的keywords，更新其tag。多则删，少则加，有则跳过
	public boolean updateByTargetKeywords(int targetId,int type,String keywords);
	//根据目标对象的类型，id，keywords，返回其相关对象的id数组
	//public Integer[] getRelatedIdsByKeywords(int type,int targetId,String keywords);	
	//根据目标对象的类型，id，keywords，返回其相关对象的id串，如1,2,3,4
	public String getRelatedIdsStrByKeywords(int type,int targetId,String keywords);	
}
