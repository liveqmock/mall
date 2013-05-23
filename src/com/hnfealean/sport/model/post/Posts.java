package com.hnfealean.sport.model.post;

/**
 * @hibernate.class table="t_post"
 * @author Administrator
 *
 */
public class Posts {
	public Posts() {
		super();
	}
public Posts(int id) {
		super();
		this.id = id;
	}

public static int NEWSCONTENT=0;
public static int PRODUCTDETAIL=1;
	/**
	 * @hibernate.id generator-class="native"
	 */
	private int id;
	
	/**
	 * @hibernate.property
	 */
	private int type;
	
	/**
	 * @hibernate.property length="1000"
	 */
	private String content;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}
