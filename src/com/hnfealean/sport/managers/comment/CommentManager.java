package com.hnfealean.sport.managers.comment;

import java.util.List;

import com.hnfealean.sport.model.comment.Comment;
import com.hnfealean.sport.pageset.PageModel;

public interface CommentManager {

	public boolean addComment(int objectId,int objectType,Comment comment);
	public boolean updateComment(int objectId,int objectType,Comment comment);
	public boolean deleteComment(int commentId);
	public boolean deleteComment(int commentId,int type);
	public boolean deleteComments(int[] commentIds);
	public List<Comment> getComments(int objectId,int objectType);
	public PageModel getComments(int objectId, int objectType,boolean yes);
	public PageModel getComments(int objectType);
	public List<Comment> getLatestComments(int objectId,int objectType,int number);
	
	public boolean isLatestCommentId(int objectId,int objectType,int latestId );
	public int getLatestCommentId(int objectId,int objectType );
	public Comment getComment(int id,int objectId,int objectType );
}
