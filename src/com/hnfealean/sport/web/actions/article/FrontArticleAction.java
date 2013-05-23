package com.hnfealean.sport.web.actions.article;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.hnfealean.sport.managers.article.FrontArticleManager;
import com.hnfealean.sport.model.article.Article;
import com.hnfealean.sport.model.article.ArticleType;
import com.hnfealean.sport.pageset.PageModel;
import com.hnfealean.sport.web.SeoUrl;
import com.hnfealean.sport.web.forms.article.FrontArticleForm;


public class FrontArticleAction extends DispatchAction {
	private FrontArticleManager frontArticleManager;

	public void setFrontArticleManager(FrontArticleManager frontArticleManager) {
		this.frontArticleManager = frontArticleManager;
	}

	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FrontArticleForm faf = (FrontArticleForm) form;
		Article article;
		if(faf.getId()==0&&(faf.getType()==null||faf.getType().trim().length()==0||faf.getUrl()==null||faf.getUrl().trim().length()==0)){
			response.reset();
			response.sendError(404);
			return null;
			
		}
		if(faf.getId()>0)article =frontArticleManager.getArticle(faf.getId());
		else		article =frontArticleManager.getArticleByUrlAndType(faf.getType(), faf.getUrl());
			
		faf.setType(article.getType().getName());
        if(article.getSeod()==false) article.setUrl(SeoUrl.createArticleUrl(article, faf.getType().trim()));
		request.setAttribute("tops", frontArticleManager.getTopArticles(faf.getType().trim()));
		PageModel pm = frontArticleManager.searchArticleAll(faf.getType().trim(),faf.getId());
                for(Article m:(List<Article>)pm.getDatas()){
                    if(m.getSeod()==false) m.setUrl(SeoUrl.createArticleUrl(m, faf.getType().trim()));

                }
		request.setAttribute("pm",pm );
		
		ArticleType type =frontArticleManager.getArticleType(faf.getType().trim());
		request.setAttribute("type", type);
		if(article!=null){
			List<Article> breadcrumbs = frontArticleManager.getCrumbs(article);
			for(Article m:(List<Article>)breadcrumbs){
                         if(m.getSeod()==false) m.setUrl(SeoUrl.createArticleUrl(m, faf.getType().trim()));

                        }
			request.setAttribute("breadcrumbs", breadcrumbs);
		}
		if(article!=null&&article.getId()>0){
			request.setAttribute("article", article);
/*			if(article.getTemplateUrl()!=null&&article.getTemplateUrl().trim().length()>0){
				File f = new File(request.getRealPath(request.getContextPath())+"\\WEB-INF\\vm\\article\\"+article.getTemplateUrl().trim());
				if(f.exists()&&f.isFile()){
					request.setCharacterEncoding("utf-8");
					response.setCharacterEncoding("utf-8");
					request.getRequestDispatcher("/WEB-INF/vm/article/"+article.getTemplateUrl().trim()).include(request, response);
					return null;
				}
			}
			return mapping.findForward("index");*/
		}
/*		if(type!=null&&type.getTemplateUrl()!=null&&type.getTemplateUrl().trim().length()>0){
			File f = new File(request.getRealPath(request.getContextPath())+"\\WEB-INF\\vm\\article\\"+type.getTemplateUrl().trim());
			if(f.exists()&&f.isFile()){
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			request.getRequestDispatcher("/WEB-INF/vm/article/"+type.getTemplateUrl().trim()).include(request, response);
		
			return null;
			}
		}*/
		return mapping.findForward("index");
	}	
	
}
