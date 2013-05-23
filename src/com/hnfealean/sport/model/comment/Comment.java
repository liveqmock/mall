package com.hnfealean.sport.model.comment;

import java.util.Date;
import java.util.Set;

import org.compass.annotations.Searchable;
import org.compass.annotations.SearchableComponent;
import org.compass.annotations.SearchableId;
import org.compass.annotations.SearchableProperty;
import org.compass.annotations.Store;

/**
 * @hibernate.class table="t_comment" 
 * @author Administrator
 *
 */
@Searchable
public class Comment {
	public Comment(){
		super();
	}
	public Comment(int id) {
		super();
		this.id = id;
	}
	/**
	 * 评论对象，id，主体类型，主体标识，（是否允许评论？属于主体的）
	 * 
	 * 评论内容，是否可见（是否在评审之中）,
	 * 评论来源（IP，地址，用户名）
	 * 
	 * 指的是主体类型
	 */
	public static int NEWS =0;
	public static int BLOG =1;
	public static int WIKI =2;
	public static int THEME =3;
	public static int REPLYTYPE=1;
	/**
	 * @hibernate.id generator-class="native"
	 */
	private int id;
	/**
	 * @hibernate.property
	 */
	private int commentObject;
	/**
	 * @hibernate.property
	 */
	private int commentObjectId;
	/**
	 * @hibernate.property
	 */
	private boolean display=true;
	
	/**
	 * @hibernate.many-to-one  unique="true"
	 */
	private CommentContent content;
	/**
	 * @hibernate.property length="20"
	 */
	private String ip;
	/**
	 * @hibernate.property length="30"
	 */
	private String address;
	/**
	 * @hibernate.property length="20"
	 */
	private String username;
	/**
	 * @hibernate.property 
	 */
	private int support;
	/**
	 * @hibernate.property 
	 */
	private float score;
	/**
	 * @hibernate.property 
	 */
	private boolean isReply;//回复
	/**
	 * @hibernate.property 
	 */
	private boolean replyFromAdmin;//管理员回复
	/**
	 * @hibernate.property length="20"
	 */
	private String replyAdminName;//管理员名称
	/**
	 * @hibernate.property
	 */
	private Date date= new Date();
	/**
	 * @hibernate.set order-by="id asc" inverse="true" lazy="true" cascade="delete-orphan" 
	 * @hibernate.key column="replyId"
	 * @hibernate.one-to-many class="com.hnfealean.sport.model.comment.Comment"	
	 */
	private Set<Comment> replis;
	@SearchableId
	public int getId() {
		return id;
	}
	public Set<Comment> getReplis() {
		return replis;
	}
	public void setReplis(Set<Comment> replis) {
		this.replis = replis;
	}
	public void setId(int id) {
		this.id = id;
	}
	@SearchableProperty(store=Store.YES)
	public int getCommentObject() {
		return commentObject;
	}
	public void setCommentObject(int commentObject) {
		this.commentObject = commentObject;
	}
	@SearchableProperty(store=Store.YES)
	public int getCommentObjectId() {
		return commentObjectId;
	}
	public void setCommentObjectId(int commentObjectId) {
		this.commentObjectId = commentObjectId;
	}
	@SearchableProperty(store=Store.YES)
	public boolean isDisplay() {
		return display;
	}
	
	public void setDisplay(boolean display) {
		this.display = display;
	}
	@SearchableComponent
	public CommentContent getContent() {
		return content;
	}
	public void setContent(CommentContent content) {
		this.content = content;
	}
	@SearchableProperty(store=Store.YES)
	public String getIp() {
		return ip;
	}
	
	public void setIp(String ip) {
		this.ip = ip;
	}
	@SearchableProperty(store=Store.YES)
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@SearchableProperty(store=Store.YES)
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getSupport() {
		return support;
	}
	public void setSupport(int support) {
		this.support = support;
	}
	public float getScore() {
		return score;
	}
	public void setScore(float score) {
		this.score = score;
	}
	public boolean isReply() {
		return isReply;
	}
	public void setReply(boolean isReply) {
		this.isReply = isReply;
	}
	@SearchableProperty(store=Store.YES)
	public boolean isReplyFromAdmin() {
		return replyFromAdmin;
	}
	public void setReplyFromAdmin(boolean replyFromAdmin) {
		this.replyFromAdmin = replyFromAdmin;
	}
	public String getReplyAdminName() {
		return replyAdminName;
	}
	public void setReplyAdminName(String replyAdminName) {
		this.replyAdminName = replyAdminName;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
}
