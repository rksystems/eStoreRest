package com.ss.estore.service;

import java.util.Collection;
import java.util.List;

import com.ss.estore.model.ProductsCatalog;

public interface ProductsCatalogService {
	public void add(ProductsCatalog product);
	public ProductsCatalog fetch(long productID);
	public void addAll(Collection<ProductsCatalog> products);
	public ProductsCatalog deleteProduct(long productID);
	public List<ProductsCatalog> listAll();
}
