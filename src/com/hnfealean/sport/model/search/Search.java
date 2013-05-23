package com.hnfealean.sport.model.search;

/**
 * @hibernate.class table="t_search"
 * @author Administrator
 *
 */
public class Search {

	/**
	 * @hibernate.id generator-class="native"
	 */
	private int id;
	
	/**
	 * @hibernate.property length="30"
	//搜索字符串
	 */
	private String input;
	
	/**
	 * @hibernate.property length="500"
	 */
	private String meta_description;
	
	/**
	 * @hibernate.property length="200"
	 */
	private String meta_keywords;
	
	/**
	 * @hibernate.property length="100" not-null="false"
	 */
	private String title;
	
	/**
	 * @hibernate.property length="100"
	 */
	private String url;
	
	/**
	 * @hibernate.many-to-one
	 */
	private SearchOutput searchoutput;
	
	/**
	 * @hibernate.property
	 */
	private boolean indexPermission;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public String getMeta_description() {
		return meta_description;
	}

	public void setMeta_description(String meta_description) {
		this.meta_description = meta_description;
	}

	public String getMeta_keywords() {
		return meta_keywords;
	}

	public void setMeta_keywords(String meta_keywords) {
		this.meta_keywords = meta_keywords;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public SearchOutput getSearchoutput() {
		return searchoutput;
	}

	public void setSearchoutput(SearchOutput searchoutput) {
		this.searchoutput = searchoutput;
	}

	public boolean isIndexPermission() {
		return indexPermission;
	}

	public void setIndexPermission(boolean indexPermission) {
		this.indexPermission = indexPermission;
	} 
}
