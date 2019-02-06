/**
 * 
 */
package com.ss.estore.dao;

import java.util.List;

import com.ss.estore.model.User;

/**
 * @author Raghu Kishore
 *
 */
public interface UserDAO {
	public void save(User p);

	public List<User> list();
	public User delete(long userId);
	public User fetch(long userId);
}
