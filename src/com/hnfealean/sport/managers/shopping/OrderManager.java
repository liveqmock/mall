package com.hnfealean.sport.managers.shopping;

import com.hnfealean.sport.model.shopping.DeliverInfo;
import com.hnfealean.sport.model.shopping.Order;
import com.hnfealean.sport.model.shopping.OrderItem;
import com.hnfealean.sport.model.shopping.OrderItemAttribute;
import com.hnfealean.sport.model.shopping.PaymentAndDeliverMethod;
import com.hnfealean.sport.model.shopping.ShoppingCart;
import com.hnfealean.sport.model.user_acl_module.ContactInfo;
import com.hnfealean.sport.pageset.PageModel;

public interface OrderManager {
	
	/**
	 * 对于在线支付，需要查询订单是否存在。输入订单ID和用户ID，返回布尔值
	 * @param id
	 * @param orderId
	 * @return
	 */
	public boolean checkExists(int userId,String orderId);
	/**
	 * 按订单状态查询订单
	 * @param orderState
	 * @return
	 */
	public PageModel searchOrdersByState(String orderState);
	/**
	 * 解锁订单
	 * @param orderid 订单号
	 */
	public void unLock(String orderid);
	/**
	 * 加载订单
	 * @param orderId
	 * @return
	 */
	public Order getOrder(String orderId);
	/**
	 * 加锁获取订单
	 * @param orderid 订单号
	 * @param employee 员工账号
	 * @return
	 */
	public Order updateAndGetLockOrder(String orderid, String employee);
	/**
	 * 取消订单
	 * @param orderid 订单号
	 */
	public void cancelOrder(String orderid);
	/**
	 * 审核通过订单
	 * @param orderid 订单号
	 */
	public void confirmOrder(String orderid);
	/**
	 * 确认订单已支付
	 * @param orderid 订单号
	 */
	public void confirmPayment(String orderid);
	/**
	 * 把订单转为等待发货状态
	 * @param orderid 订单号
	 */
	public void turnWaitdeliver(String orderid);
	/**
	 * 把订单转为已发货状态
	 * @param orderid 订单号
	 */
	public void turnDelivered(String orderid);
	/**
	 * 把订单转为已收货状态
	 * @param orderid 订单号
	 */
	public void turnReceived(String orderid);
	/**
	 * 更新配送费用
	 * @param deliverFee 配送费
	 * @param orderid 订单号
	 */
	public void updateDeliverFee(Float deliverFee, String orderid);
	/**
	 * 创建订单
	 * @param cart 购物车
	 * @param username 顾客用户名
	 */
	public void createOrder(ShoppingCart cart, String username);
	/**
	 * 更新支付方式
	 * @param paymentWay 支付方式
	 * @param orderid 订单号
	 */
	public void updatePaymentWay(PaymentAndDeliverMethod paymentMethod, String orderid);
	/**
	 * 更新支付状态
	 * @param paid
	 * @return
	 */
	public void updatePaymentState(String orderId,boolean paid);
	/**
	 * 更新配送方式
	 * @param deliverWay 配送方式
	 * @param orderid 订单号
	 */
	public void updateDeliverWay(DeliverInfo deliverInfo, String orderid);
	
	public ContactInfo getContactInfo(int userId);
	public PageModel searchOrders(String sqlLimit,int page,int pagesize);
	
	public void updateDeliverInfo(DeliverInfo info,String orderId);
	public void updatePaymentAndDeliverMethod(PaymentAndDeliverMethod m);
	public void updateOrderItem(OrderItem item);
	public OrderItem getOrderItem(int id);
	public void updateOrder(Order order);
	public OrderItemAttribute getOrderItemAttribute(int id);
	public void updateOrderItemAttribute(OrderItemAttribute att);
}
