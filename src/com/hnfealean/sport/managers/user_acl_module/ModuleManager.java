package com.hnfealean.sport.managers.user_acl_module;

import java.util.List;

import com.hnfealean.sport.model.user_acl_module.Module;
import com.hnfealean.sport.model.user_acl_module.ModuleFunction;
import com.hnfealean.sport.pageset.PageModel;

public interface ModuleManager {

	public void addModule(Module module);
	
	public PageModel searchAllModules();
	
	public PageModel searchChildModules(int parentId);
	
	public Module findModuleById(int id);
	
	public void updateModule(Module module,int id);
	
	public PageModel searchModulesByChildId(int id);
	
	public void deleteModule(int id);
	
	public List searchTopModules() ;
	
	public boolean addModuleFunction(ModuleFunction function);
	public boolean updateModuleFunction(ModuleFunction function);
	public boolean deleteModuleFunction(int functionId);
	
	public ModuleFunction getModuleFunctionById(int id);
}

