package com.hnfealean.sport.managers.shopping;

import com.hnfealean.sport.model.product.AttributeOption;
import com.hnfealean.sport.model.product.ImagesAndStyle;
import com.hnfealean.sport.model.product.Product;
import com.hnfealean.sport.model.product.Size;

public interface ShoppingCartManager {
	//public void addItem(int productId,int imagesAndStyleId,int sizeId,int amount);
	//public void addItem(int productId,String imagesAndStyleName,int sizeId,int amount);
	//public void updateItem(int productId,int imagesAndStyleId,int sizeId,int amount);
//	public void updateAll(int productId,int imagesAndStyleId,int sizeId,int amount);
	//public void updateAll(int productId,int imagesAndStyleId,int sizeId,int amount);
	public Product loadProduct(Integer productId);
	public ImagesAndStyle loadImagesAndStyle(Integer imagesAndStyleId);
	public Size loadSize(Integer sizeId);
	public AttributeOption getAttributeOption(Integer id);
}
