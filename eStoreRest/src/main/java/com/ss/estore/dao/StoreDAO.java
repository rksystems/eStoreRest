/**
 * 
 */
package com.ss.estore.dao;

import java.util.List;

import com.ss.estore.model.Store;

/**
 * @author Raghu Kishore
 *
 */
public interface StoreDAO {
	public void save(Store p);
	public void delete(long storeId);
	public List<Store> list();

	public Store fetch(int storeId);
}
