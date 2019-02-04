package com.ss.estore.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ss.estore.dao.StoreDAO;
import com.ss.estore.model.Store;

public interface StoreService {
	public void add(Store store);
	public Store fetch(int storeId);
	public void addAll(Collection<Store> stores);
	public void delete(long storeId);
	public List<Store> listAll();
}
