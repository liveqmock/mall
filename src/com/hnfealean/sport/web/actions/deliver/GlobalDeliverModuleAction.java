package com.hnfealean.sport.web.actions.deliver;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.hnfealean.sport.enums.ConstantString;
import com.hnfealean.sport.managers.area.CountryManager;
import com.hnfealean.sport.managers.area.ZoneManager;
import com.hnfealean.sport.managers.deliver.DeliverTypeManager;
import com.hnfealean.sport.managers.deliver.GlobalDeliverModuleManager;
import com.hnfealean.sport.model.area.Zone;
import com.hnfealean.sport.model.deliver.DeliverType;
import com.hnfealean.sport.model.deliver.GlobalDeliverModule;
import com.hnfealean.sport.model.deliver.GlobalDistributionTemplate;
import com.hnfealean.sport.web.SystemException;
import com.hnfealean.sport.web.forms.deliver.GlobalDeliverModuleActionForm;

public class GlobalDeliverModuleAction extends DispatchAction {

	private GlobalDeliverModuleManager globalDeliverModuleManager;
	
	private ZoneManager zoneManager;
	
	private CountryManager countryManager;
	private DeliverTypeManager deliverTypeManager;
	public void setDeliverTypeManager(DeliverTypeManager deliverTypeManager) {
		this.deliverTypeManager = deliverTypeManager;
	}

	public void setGlobalDeliverModuleManager(
			GlobalDeliverModuleManager globalDeliverModuleManager) {
		this.globalDeliverModuleManager = globalDeliverModuleManager;
	}

	public void setZoneManager(ZoneManager zoneManager) {
		this.zoneManager = zoneManager;
	}

	public void setCountryManager(CountryManager countryManager) {
		this.countryManager = countryManager;
	}

	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {	
		List<GlobalDistributionTemplate> templates = globalDeliverModuleManager.getAllGlobalDistributionTemplate();
		if(templates==null||templates.size()==0)	return initial(mapping, form, request, response);
		request.setAttribute("templates", templates);

		List<DeliverType> deliverTypes= deliverTypeManager.getAllDeliverTypes();
		request.setAttribute("deliverTypes", deliverTypes);
		return mapping.findForward("index");
	}
	public ActionForward initial(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GlobalDistributionTemplate template = new GlobalDistributionTemplate();
		template.setName("全局，按地区固定运费，启用产品自身运费");
		template.setIgnoreProductDeliverFee(false);
		template.setPermanent(false);
		globalDeliverModuleManager.addGlobalDistributionTemplate(template);
		template =null;
		template = new GlobalDistributionTemplate();
		template.setName("全局，按地区固定运费，禁用产品自身运费");
		template.setPermanent(false);
		template.setIgnoreProductDeliverFee(true);
		globalDeliverModuleManager.addGlobalDistributionTemplate(template);
		template=null;
		template = new GlobalDistributionTemplate();
		template.setName("全局，各地区统一运费，启用产品自身运费");
		Set<GlobalDeliverModule> modules = new HashSet<GlobalDeliverModule>();

		//module.setDeliverType(deliverType)
		List<DeliverType> types = deliverTypeManager.getAllDeliverTypes();
		for(DeliverType type:types){
			GlobalDeliverModule module = new GlobalDeliverModule();
			module.setZoneName("所有地区");
			module.setDeliverType(type);
			globalDeliverModuleManager.addGlobalDeliverModule(module);
			modules.add(module);
		}
		template.setModules(modules);
		template.setIgnoreProductDeliverFee(false);
		template.setPermanent(true);
		globalDeliverModuleManager.addGlobalDistributionTemplate(template);
		
		template = null;
		template = new GlobalDistributionTemplate();
		template.setName("全局，各地区统一运费，禁用产品自身运费");
		modules = new HashSet<GlobalDeliverModule>();
		for(DeliverType type:types){
			GlobalDeliverModule module = new GlobalDeliverModule();
			module.setZoneName("所有地区");
			module.setDeliverType(type);
			globalDeliverModuleManager.addGlobalDeliverModule(module);
			modules.add(module);
		}
		template.setModules(modules);
		template.setPermanent(true);
		template.setIgnoreProductDeliverFee(true);
		globalDeliverModuleManager.addGlobalDistributionTemplate(template);
		//return mapping.findForward("index");
		return	unspecified(mapping, form, request, response);

	}	
	public ActionForward applyGlobalDeliverTemplate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GlobalDeliverModuleActionForm gdmaf = (GlobalDeliverModuleActionForm) form;
		if(gdmaf.getGlobalDistributionTemplateId()==0)throw new SystemException("非法访问！");
		globalDeliverModuleManager.updateTemplateApply(gdmaf.getGlobalDistributionTemplateId());
		request.setAttribute("message", "成功！");
		request.setAttribute("urladdress", "control/center/globaldistribution/manage.do");
		return mapping.findForward("success");
		
	}
	public ActionForward addGlobalDeliverModuleInput(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {	
		GlobalDeliverModuleActionForm gdmaf = (GlobalDeliverModuleActionForm) form;
		if(gdmaf.getGlobalDistributionTemplateId()==0)throw new SystemException("非法访问！");
		GlobalDistributionTemplate template = globalDeliverModuleManager.getGlobalDistributionTemplate(gdmaf.getGlobalDistributionTemplateId());
		if(template==null)throw new SystemException("不存在此运费模板！");
		request.setAttribute("template", template);
		List<Zone> zones = zoneManager.getTopZones();
		request.setAttribute("zones", zones);
		List<DeliverType> deliverTypes= deliverTypeManager.getAllDeliverTypes();
		request.setAttribute("deliverTypes", deliverTypes);
		return mapping.findForward("globaldelivermoduleaddinput");
	}
	public ActionForward addGlobalDeliverModule(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {	
		GlobalDeliverModuleActionForm gdmaf = (GlobalDeliverModuleActionForm) form;
		if(gdmaf.getGlobalDistributionTemplateId()==0||gdmaf.getDeliverTypeId()==0||gdmaf.getZoneId()==0)throw new SystemException("非法访问！");
		GlobalDistributionTemplate template = globalDeliverModuleManager.getGlobalDistributionTemplate(gdmaf.getGlobalDistributionTemplateId());
		if(template==null)throw new SystemException("不存在此运费模板！");
		DeliverType deliverType = deliverTypeManager.getDeliverTypeById(gdmaf.getDeliverTypeId());
		if(deliverType==null){
			throw new SystemException("不存在指定的配送方式！");
		}
		Zone zone =zoneManager.getZoneById(gdmaf.getZoneId());
		if(zone==null)	throw new SystemException("不存在指定的地区！");
		GlobalDeliverModule module = globalDeliverModuleManager.getGlobalDeliverModule(zone.getName(), deliverType.getId(),template.getId());
		if(module==null){ module = new GlobalDeliverModule();
		module.setDeliverFee(gdmaf.getDeliverFee());
		module.setDeliverType(deliverType);
		module.setZoneName(zone.getName());
		module.setFreeShippingOrderMinTotal(gdmaf.getFreeShippingOrderMinTotal());
		module.setGlobalTemplate(template);
		globalDeliverModuleManager.addGlobalDeliverModule(module);
		}else{
			module.setDeliverFee(gdmaf.getDeliverFee());
			module.setDeliverType(deliverType);
			module.setZoneName(zone.getName());
			module.setFreeShippingOrderMinTotal(gdmaf.getFreeShippingOrderMinTotal());
			globalDeliverModuleManager.updateGlobalDeliverModule(module);
		}
		//addGlobalDeliverModuleInput(mapping, form, request, response);
		response.sendRedirect("/control/center/globaldistribution/manage.do?method=addGlobalDeliverModuleInput&globalDistributionTemplateId="+
				gdmaf.getGlobalDistributionTemplateId());
		return null;
	}
	public ActionForward deleteGlobalDeliverModule(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {	
		GlobalDeliverModuleActionForm gdmaf = (GlobalDeliverModuleActionForm) form;
		if(gdmaf.getGlobalDeliverModuleId()==0)	throw new SystemException("非法访问！");
		globalDeliverModuleManager.deleteGlobalDeliverModule(gdmaf.getGlobalDeliverModuleId());
		response.sendRedirect("/control/center/globaldistribution/manage.do?method=addGlobalDeliverModuleInput&globalDistributionTemplateId="+
				gdmaf.getGlobalDistributionTemplateId());
		return null;
	}
	public ActionForward editGlobalDeliverModule(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {	
		GlobalDeliverModuleActionForm gdmaf = (GlobalDeliverModuleActionForm) form;
		if(gdmaf.getGlobalDeliverModuleId()==0||gdmaf.getGlobalDistributionTemplateId()==0)
			throw new SystemException("非法访问！");
		
		GlobalDeliverModule module =globalDeliverModuleManager.getGlobalDeliverModule(gdmaf.getGlobalDeliverModuleId(),gdmaf.getGlobalDistributionTemplateId());
		if(module==null)throw new SystemException("不存在此地区配送模块！");
		request.setAttribute("module", module);
		
		return mapping.findForward("globaldelivermoduleeditinput");
	}
	public ActionForward updateGlobalDeliverModule(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {	
		GlobalDeliverModuleActionForm gdmaf = (GlobalDeliverModuleActionForm) form;
		if(gdmaf.getGlobalDeliverModuleId()==0||gdmaf.getGlobalDistributionTemplateId()==0)
			throw new SystemException("非法访问！");
		
		GlobalDeliverModule module =globalDeliverModuleManager.getGlobalDeliverModule(gdmaf.getGlobalDeliverModuleId(),gdmaf.getGlobalDistributionTemplateId());
		if(module==null)throw new SystemException("不存在此地区配送模块！");
		module.setDeliverFee(gdmaf.getDeliverFee());
		module.setFreeShippingOrderMinTotal(gdmaf.getFreeShippingOrderMinTotal());
		globalDeliverModuleManager.updateGlobalDeliverModule(module);
		response.sendRedirect("/control/center/globaldistribution/manage.do?method=addGlobalDeliverModuleInput&globalDistributionTemplateId="
				+gdmaf.getGlobalDistributionTemplateId());
		return null;
	}
	public ActionForward checkOtherZonesFee(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {	
		List<DeliverType> types= deliverTypeManager.getAllDeliverTypes();
		List<GlobalDistributionTemplate> templates = globalDeliverModuleManager.getAllGlobalDistributionTemplate();
		for(GlobalDistributionTemplate template:templates)
		for(DeliverType type:types){
			GlobalDeliverModule module = globalDeliverModuleManager.getGlobalDeliverModuleOfOtherZones(type.getId(),template.getId());
			if(module==null){
				module = new GlobalDeliverModule();
				module.setDeliverFee(0);
				module.setDeliverType(type);
				module.setGlobalTemplate(template);
				module.setZoneName(ConstantString.OTHERZONES);
				globalDeliverModuleManager.addGlobalDeliverModule(module);
			}
		}
		request.setAttribute("message", "成功！");
		request.setAttribute("urladdress", "control/center/globaldistribution/manage.do");
		
		return mapping.findForward("success");
	}
}
