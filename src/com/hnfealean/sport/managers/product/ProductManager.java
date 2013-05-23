package com.hnfealean.sport.managers.product;


import java.util.List;

import com.hnfealean.sport.model.product.AttributeOption;
import com.hnfealean.sport.model.product.Category;
import com.hnfealean.sport.model.product.ImagesAndStyle;
import com.hnfealean.sport.model.product.Product;
import com.hnfealean.sport.model.product.ProductAttribute;
import com.hnfealean.sport.model.product.ProductDetailAttribute;
import com.hnfealean.sport.model.product.ProductDetailGroup;
import com.hnfealean.sport.model.product.ProductDetailOption;
import com.hnfealean.sport.pageset.PageModel;

public interface ProductManager {
	public Product getProductMeta(int id);
	public void updateProductMeta(Product p);
	public void updateProductMeta(int id, String meta_Description, String meta_KeyWords,
			String name, String shtml_File_Name, String titleInPage);
	public List<AttributeOption> getAttributeOptions(int productId,int attributeId);
	public List<AttributeOption> getAttributeOptionsByproductId(int productId);
	public List<ProductAttribute> getAttributes();
	public AttributeOption getAttributeOptionById(int id);
	public void addAttributeOption(AttributeOption option) ;
	public void addProduct(Product product);
	public boolean updateAttributeOption(AttributeOption option);
	public void addProduct(Product product,String brandId,int categoryId);
	public void deleteProductFile(String shtml_File_Name);
	public void updateProduct(Product product , int id);
	public void enableProduct(int id);
	public void enableProducts(int[] id);
	public void disableProduct(int id);
	public void disableProducts(int[] id);
	public void delProduct(int id);
	
	public void delProducts(int[] id);
	
	public Product findProduct(int id);
	public Product getProductOnlyNameAndCategory(int id);
	public PageModel searchProducts(String likeName);
	
	public PageModel searchProducts(String likeName, int categoryId,
									String brandId, String code, float startBasePrice,
									float endBasePrice, float startSellPrice, float endSellPrice);
	
	public PageModel searchProductsAll();
	
	public void updateVisible(int id, boolean visible);
	
	public void updateVisibles(int[] ids, boolean visible);
	
	public void updateCommend(int id, boolean commend);
	
	public void updateCommends(int[] ids, boolean commend);
	public List<Category> getPositon(String categoryName);
	public PageModel searchProductsNameAndLinksByIds(int[] ids);
	public PageModel searchProductsNameAndLinksByIds(List ids);
	public int checkExist(String code);
	public int checkExist(int id);
	public Product findProduct(String code);
	public void addImagesAndStyle(ImagesAndStyle image);
	public boolean checkImage(int productId,String imageUrl);
	public boolean updateQuantity(int productId,int quantity);
	public int updateCheckExistAttribute(String attributeName);
	public int checkExistAttributeOption(String option,int productId,int attributeId);
	public List searchAllProducts();
	public List<Product> getProductsByIds(Integer[] integers);
	public List<Product> getProductsByIdString(String ids);
	public void updateDistribution(int id, int distributionId);
	public void updateall();
	/**
	 * 添加产品详情组
	 * @param id
	 * @param group
	 */
	public void addProductDetailGroup(ProductDetailGroup group);
	/**
	 * 根据组ID加载产品详情组
	 * @param groupId
	 * @return
	 */
	public ProductDetailGroup getProductDetailGroup(int groupId);
	/**
	 * 获得所有产品详情组
	 * @return
	 */
	public List<ProductDetailGroup> getAllProductDetailGroups();
	/**
	 * 更新产品详情组
	 * @param group
	 */
	public void updateProductDetailGroup(ProductDetailGroup group);
	/**
	 * 根据ID删除产品详情组
	 * @param groupId
	 */
	public void deleteProductDetailGroup(int groupId);
	/**
	 * 根据IDs删除产品详情组,如"1,2,3,4,5"
	 * @param groupIds
	 */
	public void deleteProductDetailGroups(String groupIds);
	/**
	 * 添加产品详情属性
	 * @param attribute
	 */
	public void addProductDetailAttribute(ProductDetailAttribute attribute);
	/**
	 * 删除产品详情属性
	 * @param id
	 */
	public void deleteProductDetailAttribute(int id);
	/**
	 * 删除多个产品详情属性
	 * @param ids
	 */
	public void deleteProductDetailAttributes(String ids);
	/**
	 * 更新产品详情属性
	 * @param attribute
	 */
	public void updateProductDetailAttribute(ProductDetailAttribute attribute);
	/**
	 * 根据ID加载产品详情属性
	 * @param attributeId
	 * @return
	 */
	public ProductDetailAttribute getProductDetailAttribute(int attributeId);
	/**
	 * 根据IDs加载产品详情属性
	 * @param attributeIds
	 * @return
	 */
	public List<ProductDetailAttribute> getProductDetailAttributes(String attributeIds);
	/**
	 * 添加产品详情属性选项
	 * @param option
	 */
	public void addProductDetailOption(ProductDetailOption option);
	/**
	 * 删除产品详情属性选项
	 * @param id
	 */
	public void deleteProductDetailOption(int id);
	/**
	 * 更新产品详情属性选项
	 * @param option
	 */
	public void updateProductDetailOption(ProductDetailOption option);
	/**
	 * 根据ID加载产品详情属性选项
	 * @param optionId
	 * @return
	 */
	public ProductDetailOption getProductDetailOption(int optionId);
	/**
	 * 根据IDs加载产品详情属性选项
	 * @param optionIds
	 * @return
	 */
	public List<ProductDetailOption> getProductDetailOptions(String optionIds);
	
}
