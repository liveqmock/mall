package com.hnfealean.sport.pageset;

public class SystemContext {
	private static ThreadLocal offset = new ThreadLocal();
	private static ThreadLocal pagesize = new ThreadLocal();
	private static ThreadLocal page = new ThreadLocal();
	
	public static int getPage() {
		Integer ps = (Integer)page.get();
		if(ps == null||ps==0){
			return 1;
		}
		return ps;
	}
	public static void removePage(){
		page.remove();
	}
	public static void setPage(int pageValue) {
		page.set(pageValue);
	}
	public static int getOffset(){
		Integer os = (Integer)offset.get();
		if(os == null){
			return 0;
		}
		return os;
	}
	
	public static void setOffset(int offsetvalue){
		offset.set(offsetvalue);
	}
	
	public static void removeOffset(){
		offset.remove();
	}
	
	public static int getPagesize(){
		Integer ps = (Integer)pagesize.get();
		if(ps == null){
			return 20;
		}
		return ps;
	}
	
	public static void setPagesize(int pagesizevalue){
		pagesize.set(pagesizevalue);
	}
	
	public static void removePagesize(){
		pagesize.remove();
	}
	
}
