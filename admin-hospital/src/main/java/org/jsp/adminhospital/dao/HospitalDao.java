package org.jsp.adminhospital.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.jsp.adminhospital.dto.Admin;
import org.jsp.adminhospital.dto.Hospital;

public class HospitalDao {
	EntityManager manager = Persistence.createEntityManagerFactory("test").createEntityManager();
	
	public Hospital saveHospital(Hospital hospital,int admin_id) {
		Admin admin=manager.find(Admin.class, admin_id);
		if (admin!=null) {
			admin.getHospitals().add(hospital);
			hospital.setAdmin(admin);
			EntityTransaction transaction=manager.getTransaction();
			manager.persist(hospital);
			transaction.begin();
			transaction.commit();
			return hospital;
		} else {
			return null;
		}
	}
	
	public Hospital updateHospital(Hospital hospital,int admin_id) {
		Admin admin=manager.find(Admin.class, admin_id);
		if (admin!=null) {
			admin.getHospitals().add(hospital);
			hospital.setAdmin(admin);
			EntityTransaction transaction=manager.getTransaction();
			manager.merge(hospital);
			transaction.begin();
			transaction.commit();
			return hospital;
		} else {
			return null;
		}
	}
	
	public List<Hospital> findHospitalsByAdminId(int admin_id) {
		Query q=manager.createQuery("select h from Hospital h where h.admin.id=?1");
		q.setParameter(1, admin_id);
		try {
			return q.getResultList();
		} catch (Exception e) {
			return null;
		}
	}
	
	public Hospital findHospitalsByGst(String gst) {
		Query q=manager.createQuery("select h from Hospital h where h.gst_number=?1");
		q.setParameter(1, gst);
		try {
			return (Hospital) q.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}
}
