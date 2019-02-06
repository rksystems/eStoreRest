package com.ss.estore.service;

import java.util.Collection;
import java.util.List;

import com.ss.estore.model.Store;

public interface StoreService {
	public void add(Store store);
	public Store fetch(int storeId);
	public void addAll(Collection<Store> stores);
	public void delete(long storeId);
	public List<Store> listAll();
}
