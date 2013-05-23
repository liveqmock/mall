package com.hnfealean.sport.enums;

public enum SearchType {
	 ALL {public int getType(){return 0;}},//搜索所有
	 COMMENTALL{public int getType(){return 1;}},//搜索所有类型的评论
	 ARTICLEALL{public int getType(){return 2;}},//搜索所有的文章(包括新闻，博客，wiki，问答，主题等)
	 PRODUCTONLY{public int getType(){return 3;}},	 //只搜索产品信息
	 NEWSONLY{public int getType(){return 4;}},//只搜索新闻
	 BLOGONLY{public int getType(){return 5;}},//只搜索博客
	 WIKIONLY{public int getType(){return 6;}},//只搜索维基
	 THEMEONLY{public int getType(){return 7;}},//只搜索主题
	 QUESTIONONLY{public int getType(){return 8;}},//只搜索问答
	 NEWSCOMMENT{public int getType(){return 9;}},//只搜索新闻评论
	 BLOGCOMMENT{public int getType(){return 10;}},//只搜索新闻评论
	 WIKICOMMENT{public int getType(){return 11;}},//只搜索维基评论
	 QUESTIONCOMMENT{public int getType(){return 12;}},//只搜索问答评论
	 THEMECOMMENT{public int getType(){return 13;}}//只搜索主题评论
	 ;
	 public abstract int getType();
}
