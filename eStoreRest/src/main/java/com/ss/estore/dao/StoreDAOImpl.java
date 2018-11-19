/**
 * 
 */
package com.ss.estore.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import com.ss.estore.model.Store;

/**
 * @author Raghu Kishore
 *
 */
@Component
public class StoreDAOImpl implements StoreDAO {
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
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
	public Store fetch(int storeId) {
		Session session = this.sessionFactory.openSession();
		Store store = (Store) session.load(Store.class, storeId);
		session.close();
		return store;
	}
}
