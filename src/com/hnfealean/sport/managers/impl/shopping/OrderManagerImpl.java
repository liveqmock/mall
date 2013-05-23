package com.hnfealean.sport.managers.impl.shopping;

import java.util.List;

import com.hnfealean.sport.enums.ConstantString;
import com.hnfealean.sport.managers.impl.product.CommonManager;
import com.hnfealean.sport.managers.shopping.OrderManager;
import com.hnfealean.sport.model.shopping.DeliverInfo;
import com.hnfealean.sport.model.shopping.Order;
import com.hnfealean.sport.model.shopping.OrderItem;
import com.hnfealean.sport.model.shopping.OrderItemAttribute;
import com.hnfealean.sport.model.shopping.PaymentAndDeliverMethod;
import com.hnfealean.sport.model.shopping.ShoppingCart;
import com.hnfealean.sport.model.user_acl_module.ContactInfo;
import com.hnfealean.sport.pageset.PageModel;

public class OrderManagerImpl extends CommonManager implements OrderManager {

	/**
	 * 取消订单,将订单的状态设置为CANCEL
	 */
	public void cancelOrder(String orderId) {
		Order order =(Order) getHibernateTemplate().load(Order.class, orderId);
		if(!order.getOrderState().equals(ConstantString.ORDER_RECEIVED)){
			order.setOrderState(ConstantString.ORDER_CANCELED);
		}
	}

	/**
	 * 根据订单编号加载订单
	 */
	public Order getOrder(String orderId) {
		Order order = (Order)getSession().createQuery("from Order o where o.orderId=?").setParameter(0, orderId).uniqueResult();
		return order;//(Order) getHibernateTemplate().load(Order.class, orderId);
	}

	/**
	 * 确认订单，当用户生成订单后，订单变成WAITCONFIRM状态，经管理员核实之后，转为
	 */
	public void confirmOrder(String orderId) {
//		Order order =(Order) getHibernateTemplate().load(Order.class, orderId);
//		if(order.getOrderState().equals(ConstantString.ORDER_WAITCONFIRM)){
//			order.setOrderState(ConstantString.ORDER_WAITPAYMENT);
//		}
//		getHibernateTemplate().update(order);
		getSession().createQuery("update Order set orderState=? where orderId=? and orderState=?")
				.setParameter(0, ConstantString.ORDER_WAITPAYMENT)
				.setParameter(1, orderId)
				.setParameter(2, ConstantString.ORDER_WAITCONFIRM)
				.executeUpdate();
	}

	public void confirmPayment(String orderId) {

	}

	public void createOrder(ShoppingCart cart, String username) {
		
	}

	public Order updateAndGetLockOrder(String orderId, String administratorName) {
		getSession().createQuery("update Order set administratorName=? where orderId=? and administratorName is null")
		.setParameter(0, administratorName)
		.setParameter(1, orderId)
		.executeUpdate();
		getSession().flush();
		return this.getOrder(orderId);
	}

	public void turnDelivered(String orderId) {

	}

	public void turnReceived(String orderId) {

	}

	public void turnWaitdeliver(String orderid) {

	}

	public void unLock(String orderid) {

	}

	public void updateDeliverFee(Float deliverFee, String orderid) {

	}

	public void updateDeliverWay(DeliverInfo deliverInfo, String orderid) {

	}

	public void updatePaymentWay(PaymentAndDeliverMethod paymentMethod, String orderid) {

	}

	public PageModel searchOrdersByState(String orderState){
		if(orderState==null|| orderState.length()==0)
			orderState= ConstantString.ORDER_WAITCONFIRM;
		//getHibernateTemplate().
		
		//String s=getSession().createQuery("from Order o where o.orderState=?").setParameter(0, orderState).getQueryString().toString();
	//	System.out.println(s);
		//getSession().createQuery("from Order o where o.orderState=?").setParameter(0, orderState).toString();
		return searchPaginated("from Order o where o.orderState=?",orderState);
	}

	public boolean checkExists(int userId, String orderId) {
		if(getSession().createQuery("select orderId from Order where orderId=? and userId=?")
		.setParameter(0, orderId)
		.setParameter(1, userId).list()
		
		==null)
			return false;
		return true;
	}

	public ContactInfo getContactInfo(int userId) {
		return (ContactInfo)getSession().createQuery("select u.contactInfo from User u where u.id=?").setParameter(0, userId).uniqueResult();
	}

	public void updatePaymentState(String orderId,boolean paid) {
		getSession().createQuery("update Order set paymentState=? where orderId=?")
								.setParameter(0, paid)
								.setParameter(1, orderId)
								.executeUpdate();

	}
	public PageModel searchOrders(String sqlLimit,int page,int pagesize){
		if(page>0)page--;
		int count = ((Long)getSession().createQuery("select count(id) from Order "+sqlLimit).uniqueResult()).intValue();
		PageModel pm = new PageModel();
		pm.setTotal(count);
		List<Order> data = getSession().createQuery("from Order "+sqlLimit)
		.setMaxResults(pagesize)
		.setFirstResult(page*pagesize)
		.list();
		pm.setDatas(data);
		return pm;
	}

	public void updateDeliverInfo(DeliverInfo info, String orderId) {
		getHibernateTemplate().update(info);
		
	}

	public void updatePaymentAndDeliverMethod(PaymentAndDeliverMethod m) {
		getHibernateTemplate().update(m);
		
	}

	public OrderItem getOrderItem(int id) {
		return (OrderItem)getHibernateTemplate().load(OrderItem.class, id);
		
	}

	public void updateOrderItem(OrderItem item) {
		getHibernateTemplate().update(item);		
	}

	public void updateOrder(Order order) {
		getHibernateTemplate().update(order);
		
	}

	public OrderItemAttribute getOrderItemAttribute(int id) {
		// TODO Auto-generated method stub
		return (OrderItemAttribute)getHibernateTemplate().get(OrderItemAttribute.class, id);
	}

	public void updateOrderItemAttribute(OrderItemAttribute att) {
		getHibernateTemplate().update(att);
		
	}
}
