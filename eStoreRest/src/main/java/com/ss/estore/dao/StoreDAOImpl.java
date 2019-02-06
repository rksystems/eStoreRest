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

import com.ss.estore.model.Store;

/**
 * @author Raghu Kishore
 *
 */
@Component
public class StoreDAOImpl implements StoreDAO {
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
	public void save(Store store) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(store);
		tx.commit();
		session.close();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Store> list() {
		Session session = this.sessionFactory.openSession();
		List<Store> storeList = session.createQuery("from Store").list();
		session.close();
		return storeList;
	}

	@Override
	public Store fetch(long storeId) {
		Session session = this.sessionFactory.openSession();
		Store store = (Store) session.load(Store.class, storeId);
		session.close();
		return store;
	}

	@Override
	public void delete(long storeId) {
		Session session = this.sessionFactory.openSession();
		Store store = (Store) session.load(Store.class, storeId);
		session.delete(store);
		session.close();
	}
}
