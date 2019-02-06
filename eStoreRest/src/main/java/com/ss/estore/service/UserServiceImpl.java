package com.ss.estore.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ss.estore.dao.UserDAO;
import com.ss.estore.model.User;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDAO userDAO;

	public void setStoreDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Transactional
	public void add(User user) {
		userDAO.save(user);
	}

	@Transactional
	public void addAll(Collection<User> users) {
		for (User user : users) {
			userDAO.save(user);
		}
	}

	@Transactional(readOnly = true)
	public List<User> listAll() {
		return userDAO.list();

	}

	@Override
	public User fetch(long userId) {
		return userDAO.fetch(userId);
	}

	@Override
	public User deleteUser(long userId) {
		return userDAO.delete(userId);
	}
}
