package com.hnfealean.sport.managers.shopping;

import java.util.List;

import com.hnfealean.sport.model.shopping.OrderStatus;

public interface OrderStatusManager {

	public void addOrderStatus(OrderStatus status);
	public void deleteOrderStatus(int id);
	public void updateOrderStatus(OrderStatus status);
	public OrderStatus getOrderStatus(int id);
	public List<OrderStatus> getAllOrderStatus();
}
