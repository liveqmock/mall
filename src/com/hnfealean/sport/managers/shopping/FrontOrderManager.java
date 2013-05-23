package com.hnfealean.sport.managers.shopping;

import com.hnfealean.sport.model.shopping.DeliverInfo;
import com.hnfealean.sport.model.shopping.Order;
import com.hnfealean.sport.model.shopping.PaymentAndDeliverMethod;
import com.hnfealean.sport.model.shopping.ShoppingCart;
import com.hnfealean.sport.pageset.PageModel;

public interface FrontOrderManager{
	public void addOrder(ShoppingCart shoppingCart,String userName);
	public Order addOrder(ShoppingCart shoppingCart, Order order,DeliverInfo deliverInfo,int userId,PaymentAndDeliverMethod paymentMethod);
	public PageModel searchAllOrders(String userName);
}
