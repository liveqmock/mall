package com.hnfealean.sport.managers.impl.product;

import java.util.List;

import com.hnfealean.sport.managers.product.TagManager;
import com.hnfealean.sport.model.product.Tag;
import com.hnfealean.sport.pageset.PageModel;

public class TagManagerImpl extends CommonManager implements TagManager {

	public void addTag(Tag tag) {
		// TODO Auto-generated method stub

	}

	/**
	 * 根据目标的id和类型查找tag名称
	 */
	public List searchNamesByTarget(int id,int type){
		return getSession().createQuery("select name from Tag where type=? and targetId=?")
		.setParameter(0, type)
		.setParameter(1, id)
		.list();		
	}
	public List searchTargetIdsByTagNames(int thisId,int type,List tagNames){
		StringBuffer names =new StringBuffer();
		for(int i=0;i<tagNames.size();i++){
			names.append((String)tagNames.get(i));
		}
		searchTargetIdsByTagNames(thisId,type,names.toString());
		return null;
	}
	public List searchTargetIdsByTagNames(int thisId,String type,String tagNames){
		
		return getSession().createQuery("select targetId from Tag where targetId<>? and type=? and name in(?)")
		.setParameter(0, thisId)
		.setParameter(1, type)
		.setParameter(2, tagNames)
		.list();	
	}	
	public void addTagFromString(String tagString,int productId,int type) {
		String[] tags = tagString.split(",");
		for(int i=0;i<tags.length;i++){
		List l = getSession().createQuery("select tag.id from Tag tag where tag.type=? and tag.targetId=? and tag.name =?")
			.setParameter(0, type)
			.setParameter(1, productId)
			.setParameter(2, tags[i]).list();
		
		/*if(l==null){
			return;
		}
		*/
		if(l.size()==0)	{
			Tag tag = new Tag();
			tag.setName(tags[i]);
			tag.setTargetId(productId);
			tag.setType(type);
			getHibernateTemplate().save(tag);
		}else{
			/*
			getSession().createQuery("update Tag tag set tag.count=tag.count+1 where tag.product.id=? and tag.name =?")
				.setParameter(0, productId)
				.setParameter(1, tags[i]).executeUpdate();
			 */
		}
		}
		

	}

	public void deleteTag(Tag tag) {
		// TODO Auto-generated method stub

	}

	public void updateTag(Tag tag) {
		// TODO Auto-generated method stub

	}
	public PageModel searchAllTags(){
		return searchPaginated("from Tag");
		
	}


	public boolean updateByTargetKeywords(int targetId, int type,
			String keywords) {
		String[] tags = keywords.split(",");
		StringBuffer keywordString = new StringBuffer();
		for(String tag:tags){
			keywordString.append("'"+tag+"',");
			Tag tempTag = (Tag)getSession().createQuery("from Tag where name=? and type=? and targetId=?")
			.setParameter(0, tag)
			.setParameter(1, type)
			.setParameter(2, targetId)
			.uniqueResult();
			if(tempTag==null){
				tempTag = new Tag(tag,targetId,type);
				getHibernateTemplate().save(tempTag);
			}
		}
		if(keywordString.length()>0)
		if(keywordString.charAt(keywordString.length()-1)==",".charAt(0))keywordString.deleteCharAt(keywordString.length()-1);
		//System.out.println("delete from Tag where type="+type+" and targetId="+targetId+" and name not in ("+keywordString+")");
		getSession().createQuery("delete from Tag where type=? and targetId=? and name not in ("+keywordString.toString()+")")
					.setParameter(0, type)
					.setParameter(1, targetId)
					.executeUpdate();
		return true;
	}

	public List searchTargetIdsByTagNames(int thisId, int tagType,
			String tagNames) {
		// TODO Auto-generated method stub
		return null;
	}
	
//	//根据目标对象的类型，id，keywords，返回其相关对象的id数组
//	public Integer[] getRelatedIdsByKeywords(int type,int targetId,String keywords){
//		StringBuffer words = new StringBuffer();
//		words.append("'");
//		words.append(keywords.replaceAll(",", "','"));
//		words.append("'");
//		System.out.println(words.toString());
//		List<Integer> l = getSession().createQuery("select distinct id from Tag where type=? and targetId<>? and name in("+words.toString()+")")
//		.setParameter(0, type)
//		.setParameter(1, targetId)
//		
//		.list();
//		System.out.println("l.toString():"+l.toString());
//		if(l==null||l.size()==0)return null;
//		Integer[] ids =null;
//		for(int m:l){
//			
//		}
//		
//		System.out.println("ids.toString()"+ids.toString());
//		return (Integer[])ids;
//	}

	public String getRelatedIdsStrByKeywords(int type, int targetId,
			String keywords) {
		StringBuffer words = new StringBuffer();
		words.append("'");
		words.append(keywords.replaceAll(",", "','"));
		words.append("'");
		System.out.println(words.toString());
		List<Integer> l = getSession().createQuery("select distinct id from Tag where type=? and targetId<>? and name in("+words.toString()+")")
		.setParameter(0, type)
		.setParameter(1, targetId)
		.list();
		if(l==null||l.size()==0)	return "";
		words= new StringBuffer();
		for(int id:l){
			words.append(id+",");
		}
		if(words.length()>0)
		if(words.charAt(words.length()-1)==",".charAt(0))words.deleteCharAt(words.length()-1);
		return words.toString();
	}


}
