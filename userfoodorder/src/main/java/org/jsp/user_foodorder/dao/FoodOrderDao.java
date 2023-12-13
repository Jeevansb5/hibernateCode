package org.jsp.user_foodorder.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.jsp.user_foodorder.dto.FoodOrder;
import org.jsp.user_foodorder.dto.User;

public class FoodOrderDao {
	EntityManager manager = Persistence.createEntityManagerFactory("jpa").createEntityManager();

	public FoodOrder saveFoodOrder(FoodOrder order, int user_id) {
		User u = manager.find(User.class, user_id);
		if (u != null) {
			u.getOrders().add(order);
			order.setU(u);
			EntityTransaction transaction = manager.getTransaction();
			manager.persist(order);
			transaction.begin();
			transaction.commit();
			return order;
		} else {
			return null;
		}
	}

	public FoodOrder updateOrder(FoodOrder order, int user_id) {
		User u = manager.find(User.class, user_id);
		if (u != null) {
			FoodOrder dbOrder=manager.find(FoodOrder.class, order.getId());
			dbOrder.setFoodItem(order.getFoodItem());
			dbOrder.setCost(order.getCost());
			dbOrder.setAddress(order.getAddress());
			EntityTransaction transaction = manager.getTransaction();
			transaction.begin();
			transaction.commit();
			return order;
		} else {
			return null;
		}
	}

	public List<FoodOrder> findFoodOrdersByUserId(int user_id) {
		Query q=manager.createQuery("select o from FoodOrder o where o.u.id=?1");
		q.setParameter(1, user_id);
		try {
			return (List<FoodOrder>) q.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public boolean cancelOrder(int id) {
		FoodOrder order=manager.find(FoodOrder.class, id);
		if (order != null) {
			manager.remove(order);
			EntityTransaction transaction=manager.getTransaction();
			transaction.begin();
			transaction.commit();
			return true;
		} else {
			return false;
		}
	}
}
