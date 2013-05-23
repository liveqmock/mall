package com.hnfealean.sport.web.actions.article;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.velocity.app.Velocity;

import com.hnfealean.sport.managers.article.ArticleManager;
import com.hnfealean.sport.managers.article.PostsManager;
import com.hnfealean.sport.model.article.Article;
import com.hnfealean.sport.model.article.ArticleType;
import com.hnfealean.sport.model.post.Posts;
import com.hnfealean.sport.pageset.PageModel;
import com.hnfealean.sport.web.ChineseToPinyinUtil;
import com.hnfealean.sport.web.SystemException;
import com.hnfealean.sport.web.WebUtil;
import com.hnfealean.sport.web.forms.article.ArticleForm;

public class ArticleAction extends DispatchAction {
	private ArticleManager articleManager;

	private PostsManager postsManager;
	
	public void setPostsManager(PostsManager postsManager) {
		this.postsManager = postsManager;
	}	

	public void setArticleManager(ArticleManager articleManager) {
		this.articleManager = articleManager;
	}

	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ArticleForm af =(ArticleForm) form;
		PageModel pm ;
		if(af.getCategoryId()==0)
			pm = articleManager.searchArticleAll(af.getId(),af.getpId());
		else
			pm = articleManager.searchCategoryArticleAll(af.getId(),af.getCategoryId());
		if(af.getpId()==0)
			request.setAttribute("returnto",0);
		else
			request.setAttribute("returnto", articleManager.getpId( af.getpId()));
		request.setAttribute("pm", pm);
		//request.setAttribute("type", af.getArticleTypeId());
		//request.setAttribute("parent", arg1);
		return mapping.findForward("index"); 
		
	}
	
	public ActionForward addInput(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		ArticleForm af =(ArticleForm) form;
		if(af.getId()==0) throw new SystemException("非法访问。请指定文章类型！");
		//request.setAttribute("type", af.getArticleTypeId());
		getAllTypes(request);
		return mapping.findForward("input"); 
	}
	public ActionForward addArticle(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
	
		ArticleForm af = (ArticleForm) form;
		if((af.getName()==null&&af.getTitle()==null)
				||(af.getName().trim()==""&&af.getTitle().trim()=="")
				||af.getId()==0
			)
			throw new SystemException("非法操作！");
		Article article = new Article();
		//BeanUtils.copyProperties(article, af);
		//articleManager.addArticle(article);
		Posts content = new Posts();
		content.setContent(af.getContent());
		article.setContent(content);
		article.setDescription(af.getDescription());
	
		article.setKeywords(af.getKeywords());
		article.setTitle(af.getTitle());
		article.setTemplateUrl(af.getTemplateUrl());
		article.setCommentPermission(af.getCommentPermission());
		if(af.getName()==null ||af.getName().trim().length()==0){
			article.setName(article.getTitle());
		}else{
			article.setName(af.getName());
		}
		article.setCategoryId(af.getCategoryId());
		if(af.getUrl().length()>0)article.setUrl(WebUtil.generateURL(ChineseToPinyinUtil.getFullSpell(af.getUrl())));
		else article.setUrl(WebUtil.generateURL(ChineseToPinyinUtil.getFullSpell(af.getName())));
		if(af.getpId()>0)
				article.setParent(new Article(af.getpId()));
		ArticleType type = articleManager.getArticleType(af.getId());
		if(type==null||type.getId()==0)throw new SystemException("非法访问，找不到指定的文章类型！");
		article.setType(type);
		postsManager.addPosts(content);
		articleManager.addArticle(article);
		
		request.setAttribute("message", "添加成功！");
		request.setAttribute("id", af.getId());
		request.setAttribute("urladdress", "control/center/article/manage.do?id="+af.getId()+"&pId="+af.getpId());
		return mapping.findForward("success");
	}

/*	private void htmlcreator(HttpServletRequest request, ArticleForm af,
			Article article) {
		String path="";

		if(af.getId()==ConstantString.NEWSVALUE){
			path = request.getSession().getServletContext().getRealPath(ConstantString.SEPERATORSLASH+ConstantString.NEWS);
			
		}else if(af.getId()==ConstantString.BLOGVALUE){
			path = request.getSession().getServletContext().getRealPath(ConstantString.SEPERATORSLASH+ConstantString.BLOG);
		}
		File saveDir = new File(path);
		//此处的position尚未生成			2010.12.12	15:37
		List position = new ArrayList();	
		if(af.getId()==ConstantString.NEWSVALUE){
		HTMLBuilder.buildHtml(article, saveDir, position,ConstantString.NEWS);
		}else if(af.getId()==ConstantString.BLOGVALUE){
			//HTMLBuilder.buildHtml(article, saveDir, position, ConstantString.BLOG, new Object());
		HTMLBuilder.buildHtml(article, saveDir, position,ConstantString.BLOG);
		}
		//下面生成相关文章的html文件,将Article的ID作为附加对象传到业务层.对于相关文章，
		//由于没有参数可以传输Article的id，因此，将html的文件路径直接传输过去
		saveDir = new File(
				request.getSession().getServletContext().getRealPath(ConstantString.RELATEDARTICLE),
				article.getId()+ConstantString.RELATEDARTICLESUFFIX		
				);
		HTMLBuilder.buildHtml(getRelated(article.getId(),article.getType().getId()), saveDir, position, ConstantString.RELATEDARTICLE);
	}*/

	/**
	 * 根据传入的id和type，调用业务层，返回Article的相关文章
	 * @param id
	 * @param type
	 * @return
	 */
	private List<Article> getRelated(int id,int type){
		return articleManager.getRelated(id, type);
	}


/*	public ActionForward deleteArticle(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		ArticleForm af = (ArticleForm) form;
		articleManager.deleteArticleById(((ArticleForm)form).getId());
		request.setAttribute("message", "删除成功！");
		request.setAttribute("id", af.getId());
		request.setAttribute("urladdress", "control/center/Article/manage.do?id="+af.getId()+"&pId="+af.getpId());
		return mapping.findForward("success");
	}*/
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		ArticleForm af = (ArticleForm) form;
		if(af.getId()==0)
			throw new SystemException("非法参数！");
		
		articleManager.deleteArticleById(af.getId());
		request.setAttribute("message", "删除成功！");
		request.setAttribute("id", af.getId());
		request.setAttribute("urladdress", "control/center/article/manage.do?id="+af.getId()+"&pId="+af.getPId());
		return mapping.findForward("success");
	}
	public ActionForward deletes(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		ArticleForm af = (ArticleForm) form;
		if(af.getIds()==null||af.getIds().length==0)
			throw new SystemException("非法参数！");
		//articleManager.deleteArticleById(((ArticleForm)form).getId());
		articleManager.deleteArticlesByIds(af.getIds());
		request.setAttribute("message", "删除成功！");
		request.setAttribute("id", af.getId());
		request.setAttribute("urladdress", "control/center/article/manage.do?id="+af.getId()+"&pId="+af.getpId());
		return mapping.findForward("success");
	}	
	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
	
		ArticleForm af = (ArticleForm) form;
		if(af.getId()>0){
			Article article =articleManager.getArticleById(af.getId());
			getAllTypes(request);
			request.setAttribute("article", article);
			return mapping.findForward("edit");
		}
		return null;
	}
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		ArticleForm af = (ArticleForm) form;
		if(af.getId()==0||(af.getName()==null&&af.getTitle()==null)
				||(af.getName().trim()==""&&af.getTitle().trim()=="")
			)
			throw new SystemException("非法操作！");
		Article article = articleManager.getArticleById(af.getId());
		if(article==null)throw new SystemException("找不到指定文章！");
		//Article article = new Article();
		//BeanUtils.copyProperties(article, af);//a<--b把b的东西给a
		//BeanUtils.copyProperties(article, af);
		Posts post =article.getContent();
		if(post==null)post=new Posts();
//		if(af.getContentId()>0){
//			
//			post =new Posts(af.getContentId());
//		}else{
//			post = new Posts();
//		}
		post.setContent(af.getContent());
		article.setContent(post);
		article.setDate(new Date());
		article.setDescription(af.getDescription());
		article.setId(af.getId());
		article.setKeywords(af.getKeywords());
		article.setTitle(af.getTitle());
		article.setCommentPermission(af.getCommentPermission());
		ArticleType type = articleManager.getArticleType(af.getTypeId());
		if(type==null||type.getId()==0)throw new SystemException("非法访问，找不到指定的文章类型！");
		article.setType(type);
		article.setName(af.getName());
		article.setTemplateUrl(af.getTemplateUrl());
		if(af.getUrl().length()>0)article.setUrl(WebUtil.generateURL(ChineseToPinyinUtil.getFullSpell(af.getUrl())));
		else article.setUrl(WebUtil.generateURL(ChineseToPinyinUtil.getFullSpell(af.getName())));	
		articleManager.updateArticle(article, af.getId());
		request.setAttribute("message", "更新成功！");
		//htmlcreator(request, af, article);
		request.setAttribute("urladdress", "control/center/article/manage.do?id="+af.getId()+"&pId="+af.getpId());
		return mapping.findForward("success");
	}
	
	public ActionForward searchArticleAll(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		ArticleForm af = (ArticleForm) form;
		PageModel pm = articleManager.searchArticleAll(af.getId());
		
		request.setAttribute("pm", pm);
		request.setAttribute("id", af.getId());
		//request.setAttribute("urladdress", "control/product/Article/manage.do");
		return mapping.findForward("success");
	}
	public ActionForward editTemplate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		ArticleForm af = (ArticleForm) form;
		if(af.getId()>0&&af.getTemplateUrl()!=null&&af.getTemplateUrl().trim().length()>0){
			//编辑article模板
			File file = new File((String)Velocity.getProperty(Velocity.FILE_RESOURCE_LOADER_PATH)+"\\article\\"+af.getTemplateUrl());
			if(file.getParentFile()!=null&&!file.getParentFile().exists())file.getParentFile().mkdirs();
			if(!file.exists())file.createNewFile();
			FileInputStream stram = new FileInputStream(file);
			InputStreamReader in =new InputStreamReader(stram,"UTF-8");
			BufferedReader reader=new BufferedReader(in);  
		//	BufferedReader reader = new BufferedReader(new FileReader(file));   
			StringBuffer sb = new StringBuffer();
			
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
			    sb.append(tempString);
			}
			reader.close();
			request.setAttribute("content", sb.toString());
		//}
		request.setAttribute("pId", af.getpId());
		request.setAttribute("id", af.getId());
		request.setAttribute("id", af.getId());
		request.setAttribute("templateUrl", af.getTemplateUrl());
		return mapping.findForward("editTemplate");
		}else if(af.getId()>0&&af.getTemplateUrl()!=null&&af.getTemplateUrl().trim().length()>0){
			//编辑articletype模板
			File file = new File((String)Velocity.getProperty(Velocity.FILE_RESOURCE_LOADER_PATH)+"\\article\\"+af.getTemplateUrl());
			if(file.getParentFile()!=null&&!file.getParentFile().exists())file.getParentFile().mkdirs();
			if(!file.exists())file.createNewFile();
			
			FileInputStream stram = new FileInputStream(file);
			InputStreamReader in =new InputStreamReader(stram,"UTF-8");
			BufferedReader reader=new BufferedReader(in);  
		//	BufferedReader reader = new BufferedReader(new FileReader(file));   
			StringBuffer sb = new StringBuffer();
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
			    sb.append(tempString);
			}
			reader.close();
			request.setAttribute("content", sb.toString());
		//}
	
		request.setAttribute("id", af.getId());
		request.setAttribute("templateUrl", af.getTemplateUrl());
		return mapping.findForward("editTemplate");
		}
		throw new SystemException("非法操作");
	/*	if(af.getId()==0||af.getTemplateUrl()==null||af.getTemplateUrl().trim().length()==0||af.getId()==0){
			throw new SystemException("非法操作");
		}
		//if(af.getTemplateUrl().endsWith(".vm")){

			File file = new File((String)Velocity.getProperty(Velocity.FILE_RESOURCE_LOADER_PATH)+"\\article\\"+af.getTemplateUrl());
			if(file.getParentFile()!=null&&!file.getParentFile().exists())file.getParentFile().mkdirs();
			if(!file.exists())file.createNewFile();
			BufferedReader reader = new BufferedReader(new FileReader(file));   
			StringBuffer sb = new StringBuffer();
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
			    sb.append(tempString);
			}
			reader.close();
			request.setAttribute("content", sb.toString());
		//}
		request.setAttribute("pId", af.getpId());
		request.setAttribute("id", af.getId());
		request.setAttribute("id", af.getId());
		request.setAttribute("templateUrl", af.getTemplateUrl());
		return mapping.findForward("editTemplate");*/
	}
	public ActionForward updateTemplate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {

		ArticleForm af = (ArticleForm) form;
		if(af.getId()>0&&af.getTemplateUrl()!=null&&af.getTemplateUrl().trim().length()>0){
			//更新的是文章的模板
			Article article = articleManager.getArticleById(af.getId());
			String base =(String)Velocity.getProperty(Velocity.FILE_RESOURCE_LOADER_PATH)+"\\article\\";
			String url = base+af.getTemplateUrl();
			if(article.getTemplateUrl().equals(af.getTemplateUrl())==false){
				File iniFile =	new File(base+article.getTemplateUrl());
				if(iniFile.exists()) iniFile.delete();
			}
			if(article==null||article.getId()==0)throw new SystemException("不存在指定文章！");
			File file = new File(url);
			if(file.getParentFile()!=null&&!file.getParentFile().exists())file.getParentFile().mkdirs();
			if(!file.exists())file.createNewFile();
			FileOutputStream outStream = new FileOutputStream(file);
			OutputStreamWriter outputStreamWriter =  new OutputStreamWriter(outStream,"UTF-8");
			BufferedWriter	writer = new BufferedWriter(outputStreamWriter);
			writer.write(af.getContent());
			writer.close();
			article.setTemplateUrl(af.getTemplateUrl());
			articleManager.updateArticle(article, article.getId());
			request.setAttribute("message", "更新成功！");
			request.setAttribute("urladdress", "control/center/article/manage.do?id="+af.getId()+"&pId="+af.getpId());
			return mapping.findForward("success");
		}else if(af.getId()>0&&af.getTemplateUrl()!=null&&af.getTemplateUrl().trim().length()>0){
			//更新的是文章类型的模板
			ArticleType type = articleManager.getArticleType(af.getId());
			if(type==null)
				throw new SystemException("非法操作,找不到指定的文章类型！");
			String url = af.getTemplateUrl().trim();
			type.setTemplateUrl(url);
			articleManager.updateArticleTye(type);
			String base =(String)Velocity.getProperty(Velocity.FILE_RESOURCE_LOADER_PATH)+"\\article\\";
			File file = new File(base+url);
			if(file.getParentFile()!=null&&!file.getParentFile().exists())file.getParentFile().mkdirs();
			if(!file.exists())file.createNewFile();
			FileOutputStream outStream = new FileOutputStream(file);
			OutputStreamWriter outputStreamWriter =  new OutputStreamWriter(outStream,"UTF-8");
			BufferedWriter	writer = new BufferedWriter(outputStreamWriter);
			
			writer.write(af.getContent());
			writer.close();
			request.setAttribute("message", "更新成功！");
			request.setAttribute("urladdress", "control/center/article/manage.do?method=listType");
			return mapping.findForward("success");
		}
		throw new SystemException("非法访问");
		/*	if(af.getId()==0||af.getTemplateUrl()==null||af.getTemplateUrl().trim().length()==0||af.getId()==0){
			throw new SystemException("非法操作");
		}
		Article article = articleManager.getArticleById(af.getId());
		String base =(String)Velocity.getProperty(Velocity.FILE_RESOURCE_LOADER_PATH)+"\\article\\";
		String url = base+af.getTemplateUrl();
		if(article.getTemplateUrl().equals(af.getTemplateUrl())==false){
			File iniFile =	new File(base+article.getTemplateUrl());
			if(iniFile.exists()) iniFile.delete();
		}
		if(article==null||article.getId()==0)throw new SystemException("不存在指定文章！");
		File file = new File(url);
		if(file.getParentFile()!=null&&!file.getParentFile().exists())file.getParentFile().mkdirs();
		if(!file.exists())file.createNewFile();
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		writer.write(af.getContent());
		writer.close();
		article.setTemplateUrl(af.getTemplateUrl());
		articleManager.updateArticle(article, article.getId());
		request.setAttribute("message", "更新成功！");
		request.setAttribute("urladdress", "control/center/article/manage.do?id="+af.getId()+"&pId="+af.getpId());
		return mapping.findForward("success");
		*/
	}
	public ActionForward listType(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		getAllTypes(request);
		return mapping.findForward("listType");
	}

	private void getAllTypes(HttpServletRequest request) {
		List<ArticleType> types = articleManager.getAllArticleTypes();
		request.setAttribute("types", types);
	}
	public ActionForward addArticleTypeInput(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		
		return mapping.findForward("addArticleTypeInput");
	
	}
	public ActionForward addArticleType(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		ArticleForm af = (ArticleForm) form;
		String templateUrl = af.getTemplateUrl();
		if(templateUrl==null||templateUrl.trim().length()==0)
			throw new SystemException("非法操作，请指定文章类型列表模板！");
		if(af.getName()==null||af.getName().trim().length()==0)throw new SystemException("非法访问！");
		ArticleType type= new ArticleType();
		type.setName(af.getName().trim());
		type.setTemplateUrl(templateUrl);
		type.setKeywords(af.getKeywords());
		type.setDescription(af.getDescription());
		articleManager.addArticleType(type);
		request.setAttribute("message", "添加文章类型成功!");
		request.setAttribute("urladdress", "control/center/article/manage.do?method=listType");
		return mapping.findForward("success");
	}
	public ActionForward editArticleType(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		ArticleForm af = (ArticleForm) form;
		if(af.getId()==0)throw new SystemException("非法访问！");
		ArticleType type = articleManager.getArticleType(af.getId());
		if(type==null||type.getId()==0)throw new SystemException("非法访问,找不到指定的文章类型！");
		request.setAttribute("type", type);
		return mapping.findForward("editArticleType");
	}
	public ActionForward updateArticleType(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		ArticleForm af = (ArticleForm) form;
		String templateUrl = af.getTemplateUrl();
		if(templateUrl==null||templateUrl.trim().length()==0){
			//throw new SystemException("必须指定列表显示模板！");
		}
		if(af.getId()==0||af.getName()==null||
				af.getName().trim().length()==0
				)throw new SystemException("非法访问！");
		ArticleType type = articleManager.getArticleType(af.getId());
		if(type==null||type.getId()==0)throw new SystemException("非法访问,找不到指定的文章类型！");
		type.setName(af.getName());
		
		type.setTemplateUrl(templateUrl);
		type.setKeywords(af.getKeywords());
		type.setTitle(af.getTitle());
		type.setDescription(af.getDescription());
		articleManager.updateArticleTye(type);
		request.setAttribute("message", "更新文章类型成功!");
		request.setAttribute("urladdress", "control/center/article/manage.do?method=listType");
		return mapping.findForward("success");
	}
	public ActionForward deleteArticleType(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		ArticleForm af = (ArticleForm) form;
		if(af.getId()==0)throw new SystemException("非法访问！");
		articleManager.deleterArticleType(af.getId());
		request.setAttribute("message", "删除文章类型成功!");
		request.setAttribute("urladdress", "control/center/article/manage.do?method=listType");
		return mapping.findForward("success");
	}

}
