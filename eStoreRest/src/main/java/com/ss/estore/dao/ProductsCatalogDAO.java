/**
 * 
 */
package com.ss.estore.dao;

import java.util.List;

import com.ss.estore.model.ProductsCatalog;

/**
 * @author Raghu Kishore
 *
 */
public interface ProductsCatalogDAO {
	public void save(ProductsCatalog p);

	public List<ProductsCatalog> list();
	public ProductsCatalog delete(long productID);
	public ProductsCatalog fetch(long productID);
}
