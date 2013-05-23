package com.hnfealean.sport.model.shopping;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Administrator
 *
 */
public class ShoppingCart {
	private int userId;
	private String message;
	private float totalPrice;
	private float totalSavedPrice;
	private float orderTotalPrice;
	private float totalSellPrice;
	private float deliveFee=10;
	private List<ShoppingCartItem> shoppingCartItems =new ArrayList<ShoppingCartItem>();
	
	public void addItem(ShoppingCartItem shoppingCartItem,int amount){
		if(amount==0){
			amount=1;
		}
		if(this.shoppingCartItems.size()==0){
			
			this.shoppingCartItems.add(shoppingCartItem);
		}else{
			for(ShoppingCartItem item:this.shoppingCartItems){
				if(item.equals(shoppingCartItem)){
					item.setAmount(item.getAmount()+amount);
					this.setPrices();
					return ;
				}
			}
			this.shoppingCartItems.add(shoppingCartItem);
		}
	/*	this.totalSellPrice += amount*shoppingCartItem.getProductId().getMarketPrice();
		this.orderTotalPrice += amount*shoppingCartItem.getProductId().getSellPrice();
		this.totalSavedPrice = this.totalSellPrice -this.orderTotalPrice;
		this.totalPrice =this.orderTotalPrice;
*/
		this.setPrices();
	}

	public int getSize(){
		
		return shoppingCartItems.size();
	}

	public List<ShoppingCartItem> getShoppingCartItems() {
		return shoppingCartItems;
	}

	public float getTotalSavedPrice() {
		return totalSavedPrice;
	}

	public void setTotalSavedPrice(float totalSavedPrice) {
		this.totalSavedPrice = totalSavedPrice;
	}

	public void setShoppingCartItems(List<ShoppingCartItem> shoppingCartItems) {
		this.shoppingCartItems = shoppingCartItems;
	}
	
	public ShoppingCartItem update(ShoppingCartItem shoppingCartItem,int amount ){
		shoppingCartItem.setAmount(amount);
		//this.orderTotalPrice
		setPrices();
		return shoppingCartItem;
	}
//	public boolean contains(int productId,int imagesAndStyleId){
//		Iterator i = this.shoppingCartItems.iterator();
//		while(i.hasNext()){
//			ShoppingCartItem s = (ShoppingCartItem) i.next();
//			if( s.getProduct().getId() == imagesAndStyleId){
//				return true;
//			}
//		}
//		return false;
//	}

	public float getOrderTotalPrice() {
		return orderTotalPrice;
	}

	public void setOrderTotalPrice(float orderTotalPrice) {
		this.orderTotalPrice = orderTotalPrice;
	}
	public void setOrderTotalPrice() {
		this.orderTotalPrice = this.totalSellPrice+deliveFee;
	}
	public float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public float getTotalSellPrice() {
		return totalSellPrice;
	}

	public void setTotalSellPrice(float totalSellPrice) {
		this.totalSellPrice = totalSellPrice;
	}

	public float getDeliveFee() {
		return deliveFee;
	}

	public void setDeliveFee(float deliveFee) {
		this.deliveFee = deliveFee;
		setPrices();
	}
	public void setPrices(){
		setTotalPrice();
		setTotalSellPrice();
		setTotalSavedPrice();
		setOrderTotalPrice();
	}
	public void setTotalPrice(){
		float tempTotalPrice =0;
		Iterator i = this.shoppingCartItems.iterator();
		while(i.hasNext()){
			ShoppingCartItem item = (ShoppingCartItem) i.next();
			tempTotalPrice+=item.getAmount()*item.getProduct().getMarketPrice();
		}
		this.totalPrice = tempTotalPrice;
	}
	public void setTotalSellPrice(){
		float tempTotalSellPrice =0;
		Iterator i = this.shoppingCartItems.iterator();
		while(i.hasNext()){
			ShoppingCartItem item = (ShoppingCartItem) i.next();
			tempTotalSellPrice+=item.getAmount()*item.getProduct().getSellPrice();
		}
		this.totalSellPrice = tempTotalSellPrice;	
	}
	public void setTotalSavedPrice(){
		this.totalSavedPrice = this.totalPrice -this.totalSellPrice;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
}
