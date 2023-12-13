package org.jsp.adminhospital.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.jsp.adminhospital.dto.Admin;

public class AdminDao {
	EntityManager manager = Persistence.createEntityManagerFactory("test").createEntityManager();

	public Admin saveAdmin(Admin admin) {
		EntityTransaction transaction = manager.getTransaction();
		manager.persist(admin);
		transaction.begin();
		transaction.commit();
		return admin;
	}

	public Admin updateAdmin(Admin admin) {
		EntityTransaction transaction = manager.getTransaction();
		manager.merge(admin);
		transaction.begin();
		transaction.commit();
		return admin;
	}

	public Admin findAdminById(int id) {
		Admin admin = manager.find(Admin.class, id);
		if (admin != null) {
			return admin;
		} else {
			return null;
		}
	}

	public Admin verifyAdmin(long phone, String password) {
		Query q = manager.createQuery("select a from Admin a where a.phone=?1 and where a.password=?2");
		q.setParameter(1, phone);
		q.setParameter(2, password);
		try {
			return (Admin) q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public Admin verifyAdmin(String email, String password) {
		Query q = manager.createQuery("select a from Admin a where a.email=?1 and where a.password=?2");
		q.setParameter(1, email);
		q.setParameter(2, password);
		try {
			return (Admin) q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

}
