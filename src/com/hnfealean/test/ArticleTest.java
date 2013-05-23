package com.hnfealean.test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hnfealean.sport.managers.article.FrontArticleManager;
import com.hnfealean.sport.managers.product.CategoryAttributeManager;
import com.hnfealean.sport.managers.product.CategoryManager;
import com.hnfealean.sport.model.article.Article;
import com.hnfealean.sport.model.product.Category;
import com.hnfealean.sport.model.product.CategoryAttribute;
import com.hnfealean.sport.model.product.CategoryAttributeOption;
import com.hnfealean.sport.pageset.PageModel;
public class ArticleTest {
	private static FrontArticleManager sm;
@BeforeClass
public static void setUpBeforeClass() throws Exception {

	try {
		AbstractApplicationContext cxt = new ClassPathXmlApplicationContext("applicationContext-*.xml");
		
		sm = (FrontArticleManager)cxt.getBean("frontArticleManager");
	} catch (Exception e) {
		e.printStackTrace();
	}
	}
@Test
public void  test(){
	Article article =sm.getArticleByUrlAndType("flow", "helpcenter");
	System.out.println(article);
	
	List s = sm.getTopArticles("helpcenter");
	System.out.println(s);
}
}
