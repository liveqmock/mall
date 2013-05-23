package com.hnfealean.sport.managers.impl.user_acl_module;

import java.util.List;

import com.hnfealean.sport.managers.impl.product.CommonManager;
import com.hnfealean.sport.managers.user_acl_module.ModuleManager;
import com.hnfealean.sport.model.user_acl_module.Module;
import com.hnfealean.sport.model.user_acl_module.ModuleFunction;
import com.hnfealean.sport.pageset.PageModel;

public class ModuleManagerImpl extends CommonManager implements ModuleManager {

	public void addModule(Module module) {
		getHibernateTemplate().save(module);
	}

	public Module findModuleById(int id) {
		if(id==0){
			return null;
		}
		return (Module) getHibernateTemplate().load(Module.class, id);
	}

	public PageModel searchAllModules() {
		return null;
	}

	public PageModel searchChildModules(int parentId) {
		PageModel pm = new PageModel();
		if(parentId==0){
			pm = searchPaginated("from Module module where module.parent is null");
		}else{
			Object o = parentId;
			pm = searchPaginated("from Module module where module.parent.id=?",o);
		}
		return pm;
	}
	public PageModel searchModulesByChildId(int id) {
		PageModel pm = new PageModel();
		if(id==0){
			pm = searchPaginated("from Module module where module.parent is null");
			return pm;
		}else{	
			//Module module =(Module)getSession().createQuery("from Module module where module.id=?").setParameter(0, id).uniqueResult();
			//Object o = module.getParent().getId();
			//pm= searchPaginated("from Module module where module.parent.id =?",o);
			pm = searchPaginated("from Module module where module.parent.id=?",id);
			return pm;
		}
	}
	public void updateModule(Module module, int id) {
		Module m = (Module) getHibernateTemplate().load(Module.class, id);
		m.setName(module.getName());
		m.setUrl(module.getUrl());
		getHibernateTemplate().update(m);
	}

	public void deleteModule(int id) {
//		getSession().createQuery("update Module set parent=null where parent.id=?")
//					.setParameter(0, id)
//					.executeUpdate();
//		getSession().flush();
//		getSession().createQuery("update Module set parent=null where id=?")
//					.setParameter(0, id)
//					.executeUpdate();
//		getSession().flush();
		getHibernateTemplate().delete(getHibernateTemplate().load(Module.class, id));
	//	getSession().createQuery("delete Module where id=?").setParameter(0, id)
	//				.executeUpdate();
		
	}

	public List searchTopModules() {
		return 	(List)getSession().createQuery("from Module where parent is null").list();
	}

	public boolean addModuleFunction(ModuleFunction function) {
		getHibernateTemplate().save(function);
		return true;
	}

	public boolean deleteModuleFunction(int functionId) {
		getSession().createQuery("delete from ModuleFunction where id=?").setParameter(0, functionId).executeUpdate();
		return true;
	}

	public boolean updateModuleFunction(ModuleFunction function) {
		getHibernateTemplate().update(function);
		return true;
	}

	public ModuleFunction getModuleFunctionById(int id) {
		return (ModuleFunction)getHibernateTemplate().get(ModuleFunction.class, id);
		
	}
}
