package com.hnfealean.sport.managers.product;



import java.util.List;
import java.util.Set;

import com.hnfealean.sport.model.deliver.DistributionTemplate;
import com.hnfealean.sport.model.product.AttributeOption;
import com.hnfealean.sport.model.product.Brand;
import com.hnfealean.sport.model.product.Category;
import com.hnfealean.sport.model.product.CategoryAttributeOption;
import com.hnfealean.sport.model.product.ImagesAndStyle;
import com.hnfealean.sport.model.product.Manufacturer;
import com.hnfealean.sport.model.product.Product;
import com.hnfealean.sport.model.product.ProductDetailGroup;
import com.hnfealean.sport.model.product.Size;
import com.hnfealean.sport.pageset.PageModel;

public interface FrontProductManager {
	public Product getProductByUrl(String url);
	public Product getProductById(Integer id);
	public List getPositon(Integer categoyId);
	public Category getCategoryByUrl(String url);
	public Category getCategoryById(Integer id);
	//public List getPositon(String categoryName);
	public String findCategoryName(String title);
	public PageModel searchProductsAll();
	public int getCategoryIdBycName(String categoryName);
	public PageModel searchChildProductsAll(String parentCategoryName,String orderBy);
	
	public PageModel searchProductsAll(String orderBy);
	public PageModel searchProductsAll(String orderBy,Integer offSet,Integer pageSize);	
	public List searchBrandsAll(String orderBy); 
	
	public List searchChildCategories(String categoryName); 
	
	public ImagesAndStyle findProductImagesAndStyleByProductId(Integer productId);
	
	public List searchAllChildProductIdBycUrl(String parentCategoryName);
	
	public List searchChildBrandsAll(String categoryName); 
	
	public List searchChildBrandsAll(List productIds);
	
	/**2010.12.21 上午添加**/
	//查找直接子分类ID
	public List searchDirectChildCategoriesId(String categoryName);
	public int getCategoryIdByTitle(String categoryName);
	
	public Integer getCategoryIdByUrl(String url);
	public List<Category> searchSameLevelCategories(Integer id);
	public List<Category> searchDirectChildCategories(Integer id);
	public List<Category> searchAllTopCategories();
	
	public List<Integer> searchDirectChildCategoryIds(Integer id);
	public PageModel searchProductByCategoryIds(String ids,String filter);
	public PageModel searchProductByCategoryIds(String ids,String filter,String orderBy);
	public PageModel searchProductByCategoryIds(String ids,String filter,String orderBy,Integer offset,Integer pagesize);
	public ImagesAndStyle getImageByProductId(Integer id);
	public List<CategoryAttributeOption> getCatrgoryAttributeOptions(Integer categoyId);	
	public PageModel searchChildProductsAll(Integer categoryId);
	public String searchAllChildCategoriesId(Integer categoryId);//查找所有子类别的ID
	public List<Product> getProductsByIdString(String ids);
	
	public Brand getBrandByProductId(Integer id);
	public Category getCategoryByProductId(Integer productId);
	public Set<Manufacturer> getManufacturersByProductId(Integer productId);
	public Set<ImagesAndStyle> getImagesAndStylesByProductId(Integer productId);
	public Set<Size> getSizesByProductId(Integer productId);
	public List<AttributeOption> getAttributeOptionsByProductId(Integer productId);
	public DistributionTemplate getDistributionTemplateByProductId(Integer productId);
	public List<ProductDetailGroup> getDetailGroupsByProductId(Integer productId);
}
