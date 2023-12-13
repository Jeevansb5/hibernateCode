package org.jsp.merchant_products.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.jsp.product_merchant.dto.Merchant;
import org.jsp.product_merchant.dto.Product;

public class Productsdao {
	EntityManager manager = Persistence.createEntityManagerFactory("jpa").createEntityManager();

	public Product saveProduct(Product product, int merchant_id) {
		Merchant m = manager.find(Merchant.class, merchant_id);
		if (m != null) {
//			List<Product> products = m.getProducts();
//			products.add(product);
//			m.setProducts(products);
			m.getProducts().add(product);
			product.setMerchant(m);
			EntityTransaction transaction = manager.getTransaction();
			manager.persist(product);
			transaction.begin();
			transaction.commit();
			return product;
		}
		return null;
	}

	public Product updateProduct(Product product, int merchant_id) {
		Merchant m = manager.find(Merchant.class, merchant_id);
		if (m != null) {
			product.setMerchant(m);
			EntityTransaction transaction = manager.getTransaction();
			manager.merge(product);
			transaction.begin();
			transaction.commit();
			return product;
		} else {
			return null;
		}
	}

	public List<Product> findProductsByBrand(String brand) {
		Query q = manager.createQuery("select p from Product p where p.brand=?1");
		q.setParameter(1, brand);
		return q.getResultList();
	}

	public List<Product> findProductsByCategory(String category) {
		Query q = manager.createQuery("select p from Product p where p.category=?1");
		q.setParameter(1, category);
		return q.getResultList();
	}

	public List<Product> findProductsByMerchantId(int merchant_id) {
		Query q = manager.createQuery("select p from Product p where p.merchant.id=?1");
		q.setParameter(1, merchant_id);
		return q.getResultList();
	}
}
