package com.hnfealean.test;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hnfealean.sport.managers.article.FrontArticleManager;
import com.hnfealean.sport.managers.comment.CommentManager;
import com.hnfealean.sport.model.article.Article;
import com.hnfealean.sport.model.article.ArticleType;
import com.hnfealean.sport.model.comment.Comment;
import com.hnfealean.sport.model.comment.CommentContent;
import com.hnfealean.sport.web.WebUtil;
import com.hnfealean.sport.web.ipaddressutil.IPSeeker;
public class CommentTest {
	private static CommentManager sm;
	private static FrontArticleManager fm ;
@BeforeClass
public static void setUpBeforeClass() throws Exception {

	try {
		AbstractApplicationContext cxt = new ClassPathXmlApplicationContext("applicationContext-*.xml");
		
		sm = (CommentManager)cxt.getBean("commentManager");
		fm = (FrontArticleManager) cxt.getBean("frontArticleManager");
		//countryManager=(CountryManager) cxt.getBean("countryManager");
	} catch (Exception e) {
		e.printStackTrace();
	}
	}
@Test public void addComment(){
	Article article = new Article();//fm.getArticle(0, 1);
	article.setType(new ArticleType(0));
	article.setId(1);
	Comment comment = new Comment();
	comment.setAddress("安徽蚌埠");
	comment.setCommentObject(0);
	comment.setCommentObjectId(1);
	CommentContent content = new CommentContent();
	content.setContent("InnoDB free: 9216 kB; (`replyId`) REFER `angelinthebox/t_comment`(`id`); (`content`) REFER `angelinthebox/comment_content`(`id`)");
	comment.setContent(content);
	String address=IPSeeker.getInstance().getAddress(comment.getIp());
	comment.setAddress(WebUtil.getAddressByIp(comment.getIp()));
	comment.setAddress(address);

	sm.addComment(1, 0, comment);
}
@Test public void getLatestComments(){
	List<Comment> comments=sm.getLatestComments(1, 0, 5);

	for(Comment comment:comments){
		System.out.println(comment.getId());
	}
	StringBuffer jsonBuffer = new StringBuffer();
	jsonBuffer.append("hnfealean.comment([");

	for(Comment comment:comments){
		jsonBuffer.append("['"+comment.getId()+"','"+comment.getContent().getContent().replaceAll("\'", "\"")
				.replaceAll("\r\n", "\\r\\n")
				+"','"+comment.getAddress()+"','"+comment.getIp()+"'],");
	}
	jsonBuffer.append("[]])");
	String json = jsonBuffer.toString();
	System.out.println(json);
}
}
