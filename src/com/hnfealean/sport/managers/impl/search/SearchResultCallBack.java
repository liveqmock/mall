package com.hnfealean.sport.managers.impl.search;


import java.util.ArrayList;
import java.util.List;

import org.compass.core.CompassCallback;
import org.compass.core.CompassException;
import org.compass.core.CompassHits;
import org.compass.core.CompassQueryBuilder;
import org.compass.core.CompassSession;

import com.hnfealean.sport.enums.SearchType;
import com.hnfealean.sport.managers.impl.product.CommonManager;
import com.hnfealean.sport.model.comment.Comment;
import com.hnfealean.sport.pageset.PageModel;

public class SearchResultCallBack extends CommonManager implements CompassCallback  {
	private String key;
	private int offset=0;//从第几条记录开始查询
	private int pagesize=10;//每页显示多少行
	private int searchType=SearchType.ALL.getType();
	private int searchObjectId;
	public SearchResultCallBack() {
		
	}
	public SearchResultCallBack(int searchType,int searchObjectId,String key, int offset, int pagesize) {
		this(searchType,key,offset,pagesize);
		this.searchObjectId = searchObjectId;
	}
	public SearchResultCallBack(int searchType,String key, int offset, int pagesize) {
		this.searchType =searchType;
		this.key = key;
		this.offset = offset;
		this.pagesize = pagesize;
	}
	public Object doInCompass(CompassSession session) throws CompassException {
		
/*		CompassQueryBuilder queryBuilder = session.queryBuilder();
		hits = queryBuilder.bool()
		.addMust( queryBuilder.alias("Product"))
		//.addMust( queryBuilder.term("Comment.commentObject", Comment.NEWS))
		//.addMust( queryBuilder.term("Comment.commentObjectId", searchObjectId))
		.toQuery().hits();
		int length = hits.length();*/
		/**
		 * 评论对象，id，主体类型，主体标识，（是否允许评论？属于主体的）
		 * 
		 * 评论内容，是否可见（是否在评审之中）,
		 * 评论来源（IP，地址，用户名）
		 * 
		 * 指的是主体类型
		
		public static int NEWS =0;
		public static int BLOG =1;
		public static int WIKI =2;
		public static int THEME =3;
		public static int REPLYTYPE=1;
		 */
		switch(searchType){
		case 1:
			break;
		
		}
		PageModel pm  = new PageModel();
		CompassHits hits;
		//	查询方式：采用compassQueryBuilder，许多查询可以直接工作在mapping的层次上。
			CompassQueryBuilder queryBuilder = session.queryBuilder();//.createQueryBuilder();
		//	CompassHits hits = queryBuilder.term("a.familyName.family-name", "london").hits();
			// 采用类属性的元数据id, 在上面的例子中将采用第一个元数据.
		//	CompassHits hits = queryBuilder.term("a.familyName", "london").hits();
			//查询编辑器将会采用相应的转化器转换数据。
		//	CompassHits hits = queryBuilder.term("a.date.date-sem", new Date()).hits();
			if(searchType==Comment.NEWS){	//0
				hits = queryBuilder.bool()
			.addMust( queryBuilder.alias("Comment"))
			//.addMust( queryBuilder.term("Comment.commentObject", Comment.NEWS))
			//.addMust( queryBuilder.term("Comment.commentObjectId", searchObjectId))
			.toQuery().hits();
			}else if(searchType==Comment.BLOG){	//1
				hits = queryBuilder.bool()
				.addMust( queryBuilder.alias("Blog"))
				.addMust( queryBuilder.term("Blog.name", "ugg") )
				//.addMust( queryBuilder.term("Product.name", "ugg") )
				.toQuery().hits();
			}else{
				hits=null;
			}
			
			int length = hits.length();
			if(length>=offset+pagesize){
				List<Comment> comments = new ArrayList<Comment>();
				//if(length>this.offset+this.pagesize)
				for(int i=this.offset; i<this.offset+this.pagesize; i++){
				//	System.out.println("第"+i+"条："+i);
					if(this.offset+i<length){
						
						String s = 	hits.hit(this.offset+i).getAlias();
						//System.out.println("hits.hit(i).getAlias():"+hits.hit(i).getAlias());
						System.out.println("hits.hit(i).getClass():"+hits.hit(this.offset+i).getClass());
						if(hits.hit(this.offset+i).getAlias().equals("Comment")){
							Comment comment = (Comment) hits.data(this.offset+i);

						System.out.println("id:"+comment.getId());
					//	hits.hit(i).getData().getClass();
						if(!comments.contains(comment)){
							comments.add(comment);
						}
					}
					}
				}
				
				pm.setDatas(comments);//设置查询的结果	
			}else if(length>offset&&length<offset+pagesize){
				List<Comment> comments = new ArrayList<Comment>();
				for(int i=this.offset; i<length-this.offset; i++){						
					String s = 	hits.hit(this.offset+i).getAlias();
					System.out.println("hits.hit(i).getAlias():"+hits.hit(i).getAlias());
					System.out.println("hits.hit(i).getClass():"+hits.hit(this.offset+i).getClass());
					if(hits.hit(this.offset+i).getAlias().equals("Comment")){
						Comment comment = (Comment) hits.data(this.offset+i);

					System.out.println("id:"+comment.getId());
					if(!comments.contains(comment)){
						comments.add(comment);
					}

					}
				}	
				pm.setDatas(comments);
			}else{
				pm.setDatas(null);
			}
			

		return pm;
	}

}
