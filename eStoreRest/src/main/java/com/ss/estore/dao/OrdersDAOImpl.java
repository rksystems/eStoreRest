/**
 * 
 */
package com.ss.estore.dao;

import java.util.List;

import javax.persistence.EntityManagerFactory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
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
		session.saveOrUpdate(order);
		tx.commit();
		session.close();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Orders> list(long userId) {
		Session session = this.sessionFactory.openSession();
		List<Orders> orders = session.createQuery("from Orders where Orders.userId="+userId).list();
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

	@Override
	public Orders currentOrder(long userId) {
		Session session = this.sessionFactory.openSession();
		Query query = session.createQuery("from Orders ord where ord.status is null and ord.userId="+userId);
		Orders order = null;
		if(query.list().size()>0) {
			order = (Orders)query.list().get(0);
		}
		session.close();
		return order;
	}
}
