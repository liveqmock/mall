package com.hnfealean.sport.model.shopping;


import java.util.HashSet;
import java.util.Set;

import com.hnfealean.sport.model.product.AttributeOption;
import com.hnfealean.sport.model.product.Product;
/**
 * @hibernate.class table="t_shopping_cart_item"
 * @author Administrator
 *
 */
public class ShoppingCartItem {

	/**
	 * @hibernate.id 
	 * 		generator-class="native"
	 */
	private int id=0;
	
	/**
	 * @hibernate.many-to-one
	 */
	private Product product=new Product();
	
	/**
	 * @hibernate.property
	 */
	private int amount=1;
	private Set<AttributeOption> attributes = new HashSet<AttributeOption>();



	@Override
	public boolean equals(Object obj) {
		if(obj==null)
			return false;
		if(this==null){
			return false;
		}
	//	if(this==obj)
	//		return true;
	//	System.out.println(this);
	//	System.out.println(this.getClass().getName());
	//	if(getClass() != obj.getClass()){
	//		return false;
	//	}
		if(!(obj instanceof ShoppingCartItem)||!(this instanceof ShoppingCartItem))
			return false;
		ShoppingCartItem item = (ShoppingCartItem) obj;
	//	System.out.println(this.hashCode());
	//	System.out.println("item.productId.getId()"+item.productId.getId());
	//	System.out.println("this.productId.getId():"+this.productId.getId());
	//	System.out.println("item.imagesAndStyleId.getId():"+item.imagesAndStyleId.getId());
	//	System.out.println("this.imagesAndStyleId.getId():"+this.imagesAndStyleId.getId());
	//	if(item.productId.getId() == this.productId.getId()&& (item.attributes==this.attributes||(item.attributes==null&&this.attributes==null)||
	//	(item.attributes.size()==0&&this.attributes.size()==0)		
	//	)){//item.imagesAndStyleId.getId() == this.imagesAndStyleId.getId()
		/* && item.sizeId.getId() == this.sizeId.getId()*/ 
	//		return true;
		//}
		//return false;
/*		for(AttributeOption option : this.attributes){
			System.out.println("this:\toption.value:"+option.getValue()+"\noption.id:"+option.getId()+"\n");
			System.out.println("\titem.attributes.contains:"+(item.attributes.contains(option)));
		//	if(!item.attributes.contains(option))	return false;
		}
		for(AttributeOption option : item.attributes){
			System.out.println("item:\toption.value:"+option.getValue()+"\noption.id:"+option.getId()+"\n");
		//	System.out.println("\titem.attributes.contains:"+(item.attributes.contains(option)));
		//	if(!item.attributes.contains(option))	return false;
		}*/
		//System.out.println("item.attributes:"+item.attributes);
		//System.out.println("this.attributes:"+this.attributes);
		if(item.getProduct()!=null&&this.getProduct()!=null){
			//if(item.getProduct()==this.getProduct())
			//return true;
		
		if(item.product.getId() == this.product.getId()){
			if(item.attributes.size()!=this.attributes.size())	return false;
			if(item.attributes==this.attributes)	return true;

			//for(AttributeOption option : this.attributes){
		//		System.out.println("\toption.value:"+(option).getValue());
			//	System.out.println("\titem.attributes.contains:"+(item.attributes.contains(option)));
			//	if(!item.attributes.contains(option))	return false;
			//	if(option.id==item.attributes.
			//}
			if(!item.attributes.equals(this.attributes))	return false;
			return true;
		}
		}
		//return false;
		if(this.getId()==item.getId())	return true;
		return false;
	}
	@Override
	public int hashCode() {
		if(this ==null){
			return 0;
		}
		// TODO Auto-generated method stub
		return product.hashCode()+attributes.hashCode();//+sizeId.hashCode()+imagesAndStyleId.hashCode();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public Set<AttributeOption> getAttributes() {
		return attributes;
	}
	public void setAttributes(Set<AttributeOption> attributes) {
		this.attributes = attributes;
	}
	
	

}
