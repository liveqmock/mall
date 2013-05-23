package com.hnfealean.sport.managers.impl.shopping;

import java.util.List;

import com.hnfealean.sport.managers.impl.product.CommonManager;
import com.hnfealean.sport.managers.shopping.OrderStatusManager;
import com.hnfealean.sport.model.shopping.OrderStatus;

public class OrderStatusManagerImpl extends CommonManager implements OrderStatusManager{

	public void addOrderStatus(OrderStatus status) {
		getHibernateTemplate().save(status);
		
	}

	public void deleteOrderStatus(int id) {
		getSession().createQuery("delete from OrderStatus where id=?")
					.setParameter(0, id).executeUpdate();
		
	}

	public List<OrderStatus> getAllOrderStatus() {
		return (List<OrderStatus>)getSession().createQuery("from OrderStatus").list();
	
	}

	public OrderStatus getOrderStatus(int id) {
		return (OrderStatus)getSession().createQuery("from OrderStatus where id=?")
		.setParameter(0, id)
		.setMaxResults(1)
		.uniqueResult();

	}

	public void updateOrderStatus(OrderStatus status) {
		getHibernateTemplate().update(status);
		
	}
	
}
