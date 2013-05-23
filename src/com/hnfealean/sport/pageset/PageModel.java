package com.hnfealean.sport.pageset;

import java.util.List;

public class PageModel {

	/**
	 * 总记录数
	 */
	private int total;
	
	/**
	 * 当前结果集
	 */
	private List datas;
	
	public int getTotal() {
		return total;
	}
	
	public void setTotal(int total) {
		this.total = total;
	}
	
	public List getDatas() {
		return datas;
	}
	
	public void setDatas(List datas) {
		this.datas = datas;
	}
}
