package com.hnfealean.sport.web.actions.deliver;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.hnfealean.sport.managers.area.ZoneManager;
import com.hnfealean.sport.managers.deliver.DeliverModuleManager;
import com.hnfealean.sport.managers.deliver.DeliverTypeManager;
import com.hnfealean.sport.managers.product.ProductManager;
import com.hnfealean.sport.model.area.Zone;
import com.hnfealean.sport.model.deliver.DeliverModule;
import com.hnfealean.sport.model.deliver.DeliverType;
import com.hnfealean.sport.model.deliver.DistributionTemplate;
import com.hnfealean.sport.model.product.Product;
import com.hnfealean.sport.pageset.PageModel;
import com.hnfealean.sport.web.MD5;
import com.hnfealean.sport.web.SystemException;
import com.hnfealean.sport.web.forms.deliver.DistributionActionForm;

public class DeliverModuleAction extends DispatchAction {

	private DeliverModuleManager deliverModuleManager;
	private ZoneManager zoneManager;
	private ProductManager productManager;

	public void setProductManager(ProductManager productManager) {
		this.productManager = productManager;
	}
	private DeliverTypeManager deliverTypeManager;
	public void setDeliverTypeManager(DeliverTypeManager deliverTypeManager) {
		this.deliverTypeManager = deliverTypeManager;
	}

	public void setZoneManager(ZoneManager zoneManager) {
		this.zoneManager = zoneManager;
	}

	public void setDeliverModuleManager(DeliverModuleManager deliverModuleManager) {
		this.deliverModuleManager = deliverModuleManager;
	}

	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PageModel pm = deliverModuleManager.getAllDistributionTemplate();
		request.setAttribute("pm", pm);
		List<DeliverType> deliverTypes = deliverTypeManager.getAllDeliverTypes();
		request.setAttribute("deliverTypes", deliverTypes);
		return mapping.findForward("distributionTemplateList");
	}
	public ActionForward addDistributionTemplateInput(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		List<Zone> zones = zoneManager.getTopZones();
		request.setAttribute("zones", zones);
		List<DeliverType> deliverTypes = deliverTypeManager.getAllDeliverTypes();
		request.setAttribute("deliverTypes", deliverTypes);
		return mapping.findForward("addDistributionTemplateInput");
	}
	public ActionForward addDistributionTemplate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DistributionActionForm daf =(DistributionActionForm) form;
		int[] deliverTypeIds = daf.getDeliverTypeIds();
		Set<DeliverModule> modules = new HashSet<DeliverModule>();
		try {
			for(int i=0;i<deliverTypeIds.length;i++){
				DeliverType deliverType =deliverTypeManager.getDeliverTypeById(deliverTypeIds[i]);
				
				String[] zone = request.getParameterValues("zone"+deliverTypeIds[i]);
				String[] baseFee = request.getParameterValues("baseFee"+deliverTypeIds[i]);
				String[] oneMoreFee =  request.getParameterValues("oneMoreFee"+deliverTypeIds[i]);
				if(zone==null||baseFee==null||oneMoreFee==null||zone.length!=baseFee.length-1||zone.length!=oneMoreFee.length-1){
					continue;
				}
				DeliverModule moduleBase = new DeliverModule();
				moduleBase.setBaseFee(Float.valueOf(baseFee[0]));
				moduleBase.setDeliverType(deliverType);
				moduleBase.setZoneName("其他地区");
				moduleBase.setOneMoreFee(Float.valueOf(oneMoreFee[0]));
				modules.add(moduleBase);
				if(zone!=null)
					for(int j=0;j<zone.length;j++){
						String[] zoneNames = zone[j].split(",");
						for(int k=0;k<zoneNames.length;k++){
								DeliverModule module = new DeliverModule();
								module.setDeliverType(deliverType);
								module.setZoneName(zoneNames[k]);
								module.setBaseFee(Float.valueOf(baseFee[j+1]));
								module.setOneMoreFee(Float.valueOf(oneMoreFee[j+1]));
								modules.add(module);
						}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new SystemException("您的输入有误，导致系统无法保存配送数据");
		}
		DistributionTemplate template = new DistributionTemplate();
		String templateName =daf.getDistributionTemplateName();
		if(templateName==null||templateName.trim().length()==0){
			templateName= MD5.MD5Encode((new Date()).toGMTString());
		}
		template.setName(templateName);
		template.setDescription(daf.getDistributionTemplateDescription());
		template.setDelievrModules(modules);
		deliverModuleManager.addDistributionTemplate(template);
		request.setAttribute("message", "更新成功！");
		request.setAttribute("urladdress", "control/center/distribution/manage.do");
		return mapping.findForward("success");
	}
	public ActionForward addDeliverTypeInput(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		return mapping.findForward("addDeliverTypeInput");
	}
	public ActionForward addDeliverType(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DistributionActionForm daf =(DistributionActionForm) form;
		if(daf.getDeliverTypeName()==null||daf.getDeliverTypeName().trim().length()==0){
			throw new SystemException("非法访问！");
		}
		DeliverType deliverType = new DeliverType();
		deliverType.setName(daf.getDeliverTypeName().trim());
		deliverTypeManager.addDeliverType(deliverType);
		request.setAttribute("message", "新增送货方式成功！");
		request.setAttribute("urladdress", "control/center/distribution/manage.do");
		
		return mapping.findForward("success");
	}
	public ActionForward deleteDistributionTemplate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DistributionActionForm daf =(DistributionActionForm) form;
		if(daf.getId()==0)			throw new SystemException("非法访问！");
		deliverModuleManager.deleteDistributionTemplate(daf.getId());
		request.setAttribute("message", "删除运费模板成功！");
		request.setAttribute("urladdress", "control/center/distribution/manage.do");
		return mapping.findForward("success");
		
	}
	public ActionForward deleteDeliverModule(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DistributionActionForm daf =(DistributionActionForm) form;
		if(daf.getDeliverModuleId()==0||daf.getDistributionTemplateId()==0)			throw new SystemException("非法访问！");
		deliverModuleManager.deleteDeliverModule(daf.getDeliverModuleId(), daf.getDistributionTemplateId());
		request.setAttribute("message", "删除该地区运费成功！");
		request.setAttribute("urladdress", "control/center/distribution/manage.do");
		return mapping.findForward("success");
		
	}
	public ActionForward editDeliverModule(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DistributionActionForm daf =(DistributionActionForm) form;
		if(daf.getDeliverModuleId()==0||daf.getDistributionTemplateId()==0)			throw new SystemException("非法访问！");
		DeliverModule module = deliverModuleManager.getDeliverModule(daf.getDeliverModuleId());
		request.setAttribute("module", module);

		//List<Zone> zones = zoneManager.getTopZones();
		//request.setAttribute("zones", zones);
		return mapping.findForward("delivermoduleeditinput");
		
	}
	public ActionForward updateDeliverModule(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DistributionActionForm daf =(DistributionActionForm) form;
		if(daf.getDeliverModuleId()==0)			throw new SystemException("非法访问！");
		DeliverModule module = deliverModuleManager.getDeliverModule(daf.getDeliverModuleId());
		module.setBaseFee(daf.getDeliverModuleBaseFee());
		module.setOneMoreFee(daf.getDeliverModuleOneMoreFee());
		deliverModuleManager.updateDeliverModule(module);
		request.setAttribute("message", "更新该地区运费成功！");
		request.setAttribute("urladdress", "control/center/distribution/manage.do");
		return mapping.findForward("success");
		
	}
	
	public ActionForward addDeliverModuleInput(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DistributionActionForm daf =(DistributionActionForm) form;
		if(daf.getDistributionTemplateId()==0)			throw new SystemException("非法访问！");
		List<Zone> zones = zoneManager.getTopZones();
		List<DeliverType> deliverTypes= deliverTypeManager.getAllDeliverTypes();

		request.setAttribute("zones", zones);
		request.setAttribute("deliverTypes", deliverTypes);
		return mapping.findForward("delivermoduleaddinput");
		
	}
	public ActionForward addDeliverModule(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DistributionActionForm daf =(DistributionActionForm) form;
		if(daf.getDistributionTemplateId()==0||daf.getDeliverTypeId()==0)			throw new SystemException("非法访问！");
		if(deliverModuleManager.checkDistributionTemplateExists(daf.getDistributionTemplateId())==false)
			throw new SystemException("不存在这个运费模板！");
		if(deliverTypeManager.checkDeliverTypeExists(daf.getDeliverTypeId())==false)
			throw new SystemException("不存在此送货方式！");
		DeliverModule module = new DeliverModule();
		module.setTemplate(new DistributionTemplate(daf.getDistributionTemplateId()));
		module.setZoneName(daf.getZoneName());
		module.setOneMoreFee(daf.getDeliverModuleOneMoreFee());
		module.setBaseFee(daf.getDeliverModuleBaseFee());
		module.setDeliverType(deliverTypeManager.getDeliverTypeById(daf.getDeliverTypeId()));
		deliverModuleManager.addDeliverModule(module, daf.getDistributionTemplateId());
		request.setAttribute("message", "添加新的地区运费成功！");
		request.setAttribute("urladdress", "control/center/distribution/manage.do");
		return mapping.findForward("success");
		
	}
	public ActionForward manageProductDistribution(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DistributionActionForm daf =(DistributionActionForm) form;
		if(daf.getProductId()==0)	throw new SystemException("非法访问！");
		Product product = productManager.findProduct(daf.getProductId());
		if(product==null)throw new SystemException("不存在此产品！");
		request.setAttribute("product", product);
		PageModel pm = deliverModuleManager.getAllDistributionTemplate();
		request.setAttribute("pm", pm);
		List<DeliverType> deliverTypes = deliverTypeManager.getAllDeliverTypes();
		request.setAttribute("deliverTypes", deliverTypes);
		return mapping.findForward("productDistribution");

	}
	public ActionForward applyProductDistribution(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DistributionActionForm daf =(DistributionActionForm) form;
		if(daf.getProductId()==0||daf.getDistributionTemplateId()==0)	throw new SystemException("非法访问！");
		Product product = productManager.findProduct(daf.getProductId());
		if(product==null)throw new SystemException("不存在指定产品！");
		DistributionTemplate template = deliverModuleManager.getDistributionTemplate(daf.getDistributionTemplateId());
		if(template==null||template.getId()<=0)throw new SystemException("不存在该运费模板！");
		//1.update产品信息
	//	product.setDistributionTemplate(template);
		//productManager.updateProduct(product, product.getId());
	//	productManager.updateDistribution(product.getId(), template.getId());
		//2.update 配送模板信息，1.2选择一种即可
		Set<Product> products = template.getProducts();
		if(products==null)			products = new HashSet<Product>();
		if(!products.contains(product)){
			products.add(product);
		}
		deliverModuleManager.updateDistributionTemplate(template);

		response.sendRedirect("/control/center/distribution/manage.do?method=manageProductDistribution&productId="+product.getId());
		return null;
		
	}
}
