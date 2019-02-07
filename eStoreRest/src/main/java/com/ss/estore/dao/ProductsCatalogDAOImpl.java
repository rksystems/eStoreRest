/**
 * 
 */
package com.ss.estore.dao;

import java.util.List;

import javax.persistence.EntityManagerFactory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ss.estore.model.ProductsCatalog;

/**
 * @author Raghu Kishore
 *
 */
@Component
public class ProductsCatalogDAOImpl implements ProductsCatalogDAO {
	private SessionFactory sessionFactory;

	/*public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}*/
	
	@Autowired
	public void setSessionFactory(EntityManagerFactory factory) {
		if (factory.unwrap(SessionFactory.class) == null) {
			throw new NullPointerException("factory is not a hibernate factory");
		}
		this.sessionFactory = factory.unwrap(SessionFactory.class);
	}

	@Override
	public void save(ProductsCatalog product) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(product);
		tx.commit();
		session.close();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProductsCatalog> list() {
		Session session = this.sessionFactory.openSession();
		List<ProductsCatalog> products = session.createQuery("from ProductsCatalog").list();
		session.close();
		return products;
	}

	@Override
	public ProductsCatalog fetch(long productID) {
		Session session = this.sessionFactory.openSession();
		ProductsCatalog product = (ProductsCatalog) session.load(ProductsCatalog.class, productID);
		session.close();
		return product;
	}

	@Override
	public ProductsCatalog delete(long productID) {
		Session session = this.sessionFactory.openSession();
		ProductsCatalog productsCatalog = (ProductsCatalog) session.load(ProductsCatalog.class, productID);
		session.delete(productsCatalog);
		session.close();
		return productsCatalog;
	}
}
