package com.ss.estore.service;

import java.util.Collection;
import java.util.List;

import com.ss.estore.model.User;

public interface UserService {
	public void add(User user);
	public User fetch(long userId);
	public void addAll(Collection<User> stores);
	public User deleteUser(long userId);
	public List<User> listAll();
}
