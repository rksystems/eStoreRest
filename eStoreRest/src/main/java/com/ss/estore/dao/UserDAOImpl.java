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

import com.ss.estore.model.User;

/**
 * @author Raghu Kishore
 *
 */
@Component
public class UserDAOImpl implements UserDAO {
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
	public void save(User user) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(user);
		tx.commit();
		session.close();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> list() {
		Session session = this.sessionFactory.openSession();
		List<User> userList = session.createQuery("from User").list();
		session.close();
		return userList;
	}

	@Override
	public User fetch(int userId) {
		Session session = this.sessionFactory.openSession();
		User user = (User) session.load(User.class, userId);
		session.close();
		return user;
	}

	@Override
	public User delete(long userId) {
		Session session = this.sessionFactory.openSession();
		User user = (User) session.load(User.class, userId);
		session.delete(user);
		session.close();
		return user;
	}
}
