package com.hnfealean.sport.web.actions.area;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.SystemException;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.hnfealean.sport.managers.area.CountryManager;
import com.hnfealean.sport.model.area.Country;
import com.hnfealean.sport.pageset.PageModel;
import com.hnfealean.sport.web.forms.area.CountryForm;

public class CountryAction extends DispatchAction {
private CountryManager countryManager;

public void setCountryManager(CountryManager countryManager) {
	this.countryManager = countryManager;
}

@Override
protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
		throws Exception {
	PageModel pm =	countryManager.searchAll();
	request.setAttribute("pm", pm);
	return mapping.findForward("list");
}
public ActionForward addInput(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
		throws Exception {
	//PageModel pm =	countryManager.searchAll();
	//request.setAttribute("pm", pm);
	return mapping.findForward("input");
}
public ActionForward add(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
		throws Exception {
	CountryForm cf = (CountryForm) form;
	Country country = new Country();
	country.setCnName(cf.getCnName());
	country.setEnName(cf.getEnName());
	country.setCountries_iso_code_2(cf.getCountries_iso_code_2());
	country.setCountries_iso_code_3(cf.getCountries_iso_code_3());
	countryManager.addCountry(country);
	request.setAttribute("message", "添加成功！");
//	request.setAttribute("type", af.getType());
	request.setAttribute("urladdress", "control/center/country/manage.do");
	return mapping.findForward("success");
}
public ActionForward editInput(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
		throws Exception {
	CountryForm cf = (CountryForm) form;
	if(cf.getId()==0) throw new SystemException("非法参数!");
	Country country = countryManager.getCountryById(cf.getId());
	request.setAttribute("country", country);
	//PageModel pm =	countryManager.searchAll();
	//request.setAttribute("pm", pm);
	return mapping.findForward("editinput");
}
public ActionForward delete(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
		throws Exception {
	CountryForm cf = (CountryForm) form;
if(cf.getId()==0)
		throw new SystemException("非法参数!");
	countryManager.deleteCountry(cf.getId());
	request.setAttribute("message", "删除成功！");
//	request.setAttribute("type", af.getType());
	request.setAttribute("urladdress", "control/center/country/manage.do");
	return mapping.findForward("success");
}
public ActionForward update(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
		throws Exception {
	CountryForm cf = (CountryForm) form;
	if(cf.getId()==0)
		throw new SystemException("非法参数!");
	Country country = new Country();
	country.setId(cf.getId());
	country.setEnName(cf.getEnName());
	country.setCnName(cf.getCnName());
	country.setCountries_iso_code_2(cf.getCountries_iso_code_2());
	country.setCountries_iso_code_3(cf.getCountries_iso_code_3());
	countryManager.updateCountry(cf.getId(), country);
	request.setAttribute("message", "更新成功！");
//	request.setAttribute("type", af.getType());
	request.setAttribute("urladdress", "control/center/country/manage.do");
	return mapping.findForward("success");
}
public ActionForward deleteCountries(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
		throws Exception {
	CountryForm cf = (CountryForm) form;
	if(cf.getIds()==null||cf.getIds().length==0)
		throw new SystemException("非法参数!");
	countryManager.deleteCountries(cf.getIds());
	request.setAttribute("message", "删除成功！");
//	request.setAttribute("type", af.getType());
	request.setAttribute("urladdress", "control/center/country/manage.do");
	return mapping.findForward("success");
}
}
