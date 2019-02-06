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

import com.ss.estore.model.Orders;

/**
 * @author Raghu Kishore
 *
 */
@Component
public class OrdersDAOImpl implements OrdersDAO {
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
	public void save(Orders order) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(order);
		tx.commit();
		session.close();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Orders> list() {
		Session session = this.sessionFactory.openSession();
		List<Orders> orders = session.createQuery("from Orders").list();
		session.close();
		return orders;
	}

	@Override
	public Orders fetch(long orderId) {
		Session session = this.sessionFactory.openSession();
		Orders order = (Orders) session.load(Orders.class, orderId);
		session.close();
		return order;
	}

	@Override
	public void delete(long orderId) {
		Session session = this.sessionFactory.openSession();
		Orders order = (Orders) session.load(Orders.class, orderId);
		session.delete(order);
		session.close();
	}
}
