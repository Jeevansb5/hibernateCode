package org.jsp.user_foodorder.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.jsp.user_foodorder.dto.User;

public class UserDao {
	EntityManager manager=Persistence.createEntityManagerFactory("jpa").createEntityManager();
	
	public User saveUser(User user) {
		EntityTransaction transaction=manager.getTransaction();
		manager.persist(user);
		transaction.begin();
		transaction.commit();
		return user;
	}
	
	public User updateUser(User user) {
		EntityTransaction transaction=manager.getTransaction();
		manager.merge(user);
		transaction.begin();
		transaction.commit();
		return user;
	}
	
	public User verifyUser(long phone,String password) {
		Query q=manager.createQuery("select u from User u where u.phone=?1 and u.password=?2");
		q.setParameter(1, phone);
		q.setParameter(2, password);
		try {
			return (User) q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public User verifyUser(String email,String password) {
		Query q=manager.createQuery("select u from User u where u.email=?1 and u.password=?2");
		q.setParameter(1, email);
		q.setParameter(2, password);
		try {
			return (User) q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public boolean deleteUser(int id) {
		User u=manager.find(User.class, id);
		if (u!=null) {
			EntityTransaction transaction=manager.getTransaction();
			manager.remove(u);
			transaction.begin();
			transaction.commit();
			return true;
		} else {
			return false;
		}
	}
	public User findUserById(int id) {
		User u=manager.find(User.class, id);
		if (u!=null) {
			return u;
		} else {
			return null;
		}
	}
}
