package com.hnfealean.sport.managers.impl.shopping;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.dao.DataAccessResourceFailureException;

import com.hnfealean.sport.enums.ConstantString;
import com.hnfealean.sport.managers.impl.product.CommonManager;
import com.hnfealean.sport.managers.shopping.FrontOrderManager;
import com.hnfealean.sport.model.product.AttributeOption;
import com.hnfealean.sport.model.shopping.DeliverInfo;
import com.hnfealean.sport.model.shopping.Order;
import com.hnfealean.sport.model.shopping.OrderItem;
import com.hnfealean.sport.model.shopping.OrderItemAttribute;
import com.hnfealean.sport.model.shopping.PaymentAndDeliverMethod;
import com.hnfealean.sport.model.shopping.ShoppingCart;
import com.hnfealean.sport.model.shopping.ShoppingCartItem;
import com.hnfealean.sport.model.user_acl_module.User;
import com.hnfealean.sport.pageset.PageModel;

public class FrontOrderManagerImpl extends CommonManager implements FrontOrderManager {

	public void addOrder(ShoppingCart shoppingCart, String userName) {
		// TODO Auto-generated method stub
		
	}
	public PageModel searchAllOrders(String userName) {
		
		Query query = getSession().createQuery("from Order o where o.user.username=?")
		.setParameter(0, userName);
		//.setParameter(1, OrderState.CANCEL.toString());
	
		return searchPaginated("from Order o where o.user.username='"+ userName +"'");
		
	}
	public PageModel searchOrders(String hql){
		return searchPaginated("from Order o");
	}

	public void updateOrder(String orderId){
	
		Order order =(Order)getHibernateTemplate().load(Order.class, orderId);
		getSession().createQuery("update ");
	}
	public Order addOrder(ShoppingCart shoppingCart, DeliverInfo deliverInfo,int userId,
			PaymentAndDeliverMethod paymentMethod,String deliverMethod,String timeLimit) {
				return null;

	}
	public Order addOrder(ShoppingCart shoppingCart, Order order,DeliverInfo deliverInfo,int userId,PaymentAndDeliverMethod paymentMethod) {
		User u = new User();
		u.setId(userId);
		
/*		ContactInfo c =new ContactInfo();
		c.setStreet_address(deliverInfo.getStreet_address());
		c.setMobile(deliverInfo.getMobile());
		c.setPhone(deliverInfo.getPhone());
		c.setPostCode(deliverInfo.getPostCode());
		//c.setUser(u);
		getHibernateTemplate().save(c);*/
		getHibernateTemplate().save(paymentMethod);//存储支付信息
	
		if(order==null)	order = new Order();//新建订单
		//   order.setContactInfo(c);
		Set<OrderItem> orderItems = new HashSet<OrderItem>();//订单项集合
			List shoppingCartItems = shoppingCart.getShoppingCartItems();
			Iterator i = shoppingCartItems.iterator();
			while(i.hasNext()){
				ShoppingCartItem shoppingCartItem = (ShoppingCartItem)i.next();
				OrderItem item = new OrderItem();
			//	ImagesAndStyle style = getHibernateTemplate().load(ImagesAndStyle.class, shoppingCartItem.getImagesAndStyleId());
			//	item.setImagesAndStyleId(shoppingCartItem.getImagesAndStyleId().getId());
			//	item.setImagesAndStyleName(shoppingCartItem.getImagesAndStyleId().getName());
				item.setProductId(shoppingCartItem.getProduct().getId());
				item.setProductName(shoppingCartItem.getProduct().getName());
				item.setProductPrice(shoppingCartItem.getProduct().getSellPrice());
				item.setAmount(shoppingCartItem.getAmount());
				Set<OrderItemAttribute> orderItemAttributes = new HashSet<OrderItemAttribute>();
				Iterator atts = shoppingCartItem.getAttributes().iterator();
				while(atts.hasNext()){
					//ProductAttribute pAtt =(ProductAttribute) atts.next();
					AttributeOption option = (AttributeOption) atts.next();
					OrderItemAttribute orderItemAttribute = new OrderItemAttribute();
					orderItemAttribute.setAttributeName(option.getAttribute().getName());
					orderItemAttribute.setAttributeValue(option.getValue());
					getHibernateTemplate().save(orderItemAttribute);
					//getHibernateTemplate().save(orderItemAttribute);
					orderItemAttributes.add(orderItemAttribute);
					//OrderItemAttribute att = new OrderItemAttribute();
					//att.setAttributeName(pAtt.getName());
					//att.setAttributeValue(pAtt.)
				}
				item.setOrderItemAttribues(orderItemAttributes);
				getHibernateTemplate().save(item);
				orderItems.add(item);
			}
		order.setItems(orderItems);//设置订单的订单项集合
		order.setCreateDate(new Date());//设置生成时间
		order.setDeliverFee(shoppingCart.getDeliveFee());//配送费
		order.setDeliverInfo(deliverInfo);//设置订单的配送信息
		order.setMessage(shoppingCart.getMessage());
		order.setOrderState(ConstantString.ORDER_WAITCONFIRM);
	//	order.setPayAbleFee(10);
		order.setPaymentState(false);
		order.setPaymentAndDeliverMethod(paymentMethod);
	//	order.setTotalOrderPrice(shoppingCart.getOrderTotalPrice());
	//	order.setTotalPrice(shoppingCart.getTotalSellPrice());
		order.setFee();
		order.setUser(u);
		//order.setCustomerName(customerName);
		//order.setContactInfo((ContactInfo)getHibernateTemplate().load(ContactInfo.class, 1));
		if(deliverInfo.getId()==0)//如果配送信息的ID为0，说明是新的配送信息，不为0，说明来自于数据库，来自数据库的配送信息不需保存
		getHibernateTemplate().save(deliverInfo);//存储配送信息
		try {
			getHibernateTemplate().save(order);
		} catch (DataAccessResourceFailureException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return order;
	}

}
