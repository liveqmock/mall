package com.hnfealean.sport.managers.product;

import java.util.List;
import java.util.Set;

import com.hnfealean.sport.model.product.ImagesAndStyle;
import com.hnfealean.sport.pageset.PageModel;

public interface ProductStyleManager {

	public void addProductStyle(ImagesAndStyle productStyle, int productId);
	
	public void updateProductStyle(ImagesAndStyle productStyle, int productId);
	
	public void delProductStyle(ImagesAndStyle productStyle, int productId);
	
	public PageModel searchProductStyles(int productId);
	
	public ImagesAndStyle findProductStyle(int productStyleId);
	
	public Set getProductStylesByProuctId(int id);
	public void updateVisible(int productStyleId);
	
	public void updateVisible(int[] productStyleIds, boolean visible);
	public String getImageUrl(int imagestyleid);
	
	public List<String> getAllImages();
}
