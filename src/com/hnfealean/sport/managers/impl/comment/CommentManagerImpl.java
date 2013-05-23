package com.hnfealean.sport.managers.impl.comment;

import java.util.List;

import com.hnfealean.sport.managers.comment.CommentManager;
import com.hnfealean.sport.managers.impl.product.CommonManager;
import com.hnfealean.sport.model.comment.Comment;
import com.hnfealean.sport.pageset.PageModel;

public class CommentManagerImpl extends CommonManager implements CommentManager {

	public boolean addComment(int objectId, int objectType, Comment comment) {

		getHibernateTemplate().save(comment.getContent());
		getHibernateTemplate().save(comment);
		return true;
	}

	public boolean deleteComment(int commentId) {
		getHibernateTemplate().delete(new Comment(commentId));
		return true;
	}

	public boolean deleteComments(int[] commentIds) {
		StringBuffer ids = new StringBuffer();
		ids.append("(");
		int i = 0;
		for (i = 0; i < commentIds.length - 1; i++)
			;
		{
			ids.append(commentIds[i] + ",");
		}
		ids.append(commentIds[i] + ")");
		getSession().createQuery("delete from Comment where id in ?")
				.setParameter(0, ids.toString()).executeUpdate();
		return true;
	}

	public List<Comment> getComments(int objectId, int objectType) {
		List<Comment> comments = getSession().createQuery(
				"from Comment where commentObjectId=? and commentObject=?")
				.setParameter(0, objectId).setParameter(1, objectType).list();
		comments = getSession().createQuery("from Comment").list();
		return comments;
	}

	public PageModel getComments(int objectId, int objectType, boolean yes) {
		return searchPaginated(
				"from Comment where commentObjectId=? and commentObject=?",
				new Object[] { objectId, objectType });
	}

	public boolean updateComment(int objectId, int objectType, Comment comment) {
		getHibernateTemplate().update(comment);
		return false;
	}

	// 用于生成json数组，传入对象类型，对象ID，以及获取的最新的评论条数，返回评论数组
	public List<Comment> getLatestComments(int objectId, int objectType,
			int number) {
		//
		List<Comment> comments = getSession()
				.createQuery(
						"from Comment where commentObjectId=? and commentObject=? and isReply=false and display=true order by id desc")
				.setParameter(0, objectId).setParameter(1, objectType)
				.setMaxResults(number).list();
		return comments;
	}

	public int getLatestCommentId(int objectId, int objectType) {
		int maxId=0;
		try {
			maxId = (Integer) getSession()
					.createQuery(
							"select max(id) from Comment where commentObjectId=? and commentObject=? and display=true")
					.setParameter(0, objectId).setParameter(1, objectType)
					.uniqueResult();
		} catch (Exception e) {
			
			return 0;
		}
		return maxId;
	}

	public boolean isLatestCommentId(int objectId, int objectType, int latestId) {
		int maxId=0;
		try {
			maxId = (Integer) getSession()
					.createQuery(
							"select max(id) from Comment where commentObjectId=? and commentObject=? and display=true")
					.setParameter(0, objectId).setParameter(1, objectType)
					.uniqueResult();
		} catch (Exception e) {
			return false;
		}
		if (latestId == maxId)
			return true;
		return false;
	}

	public Comment getComment(int id, int objectId, int objectType) {

		return (Comment) getSession()
				.createQuery(
						"from Comment where id=? and commentObjectId=? and commentObject=? and display=true")
				.setParameter(0, id).setParameter(1, objectId).setParameter(2,
						objectType).uniqueResult();
		// return null;
	}

	public PageModel getComments(int objectType) {
		return searchPaginated("from Comment where commentObject=?", objectType);
	}

	public boolean deleteComment(int commentId, int type) {
		getSession()
				.createQuery(
						"delete from Comment where commentObjectId=? and commentObject=?")
				.setParameter(0, commentId).setParameter(1, type)
				.executeUpdate();
		return false;
	}
}
