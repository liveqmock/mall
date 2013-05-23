package com.hnfealean.sport.web.actions.product;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.hnfealean.sport.managers.product.CategoryAttributeManager;
import com.hnfealean.sport.model.product.CategoryAttribute;
import com.hnfealean.sport.model.product.CategoryAttributeOption;
import com.hnfealean.sport.web.forms.product.front.FrontProductActionForm;

public class CategoryFilter extends DispatchAction {
	private CategoryAttributeManager categoryAttributeManager;
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		FrontProductActionForm fpaf =(FrontProductActionForm) form;
		String filter = fpaf.getFilter();
		String[] filters = filter.split("-", 10);
	StringBuffer outputFilter = new StringBuffer(); 
	List<CategoryAttribute> categoryAttributes = categoryAttributeManager.getAllCategoryAttributes(0);
	for(CategoryAttribute categoryAttribute:categoryAttributes){	//类别分类
		StringBuffer outAttributeStr = new StringBuffer();
		for(int i=0;i<filters.length-1;i++){							//输入的filter，没一个filter
		if(Integer.valueOf(filters[i].split("_")[0])!=categoryAttribute.getId()){
			outAttributeStr.append(filters[i]+"-");
			}
		if(Integer.valueOf(filters[filters.length-1].split("_")[0])!=categoryAttribute.getId()){
			outAttributeStr.append(filters[i]);
			}
		}//outAttributeStr="4_19-6_25"
		StringBuffer innerOptions = new StringBuffer();
		for(CategoryAttributeOption option :categoryAttribute.getOptions()){
			innerOptions.append("<a href='"+outAttributeStr.toString()+"-"+categoryAttribute.getId()+"_"+option.getId()+"'>"+option.getValue()+"</a>");
		}//innerOptions = "<a href='4_19-6_25-1_9'>索尼爱立信</a><a href='4_19-6_25-1_8'>天宇</a><a href='4_19-6_25-1_10'>摩托罗拉</a>"
		//outAttributeStr.append("\">"+categoryAttribute.name+"</a>");
		//需要的是:
		outputFilter.append("<a href='"+outAttributeStr+"'>"+categoryAttribute.getName()+"</a>"+innerOptions);
		//<a href='4_19-6_25'>品牌</a><a href='4_19-6_25-1_9>索尼爱立信</a><a href='4_19-6_25-1_8'>天宇</a><a href='4_19-6_25-1_10'>摩托罗拉</a>
	}
	return null;
	}
	
}
