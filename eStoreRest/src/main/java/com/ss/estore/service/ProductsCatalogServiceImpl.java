package com.ss.estore.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ss.estore.dao.ProductsCatalogDAO;
import com.ss.estore.model.ProductsCatalog;

@Service
public class ProductsCatalogServiceImpl implements ProductsCatalogService {
	@Autowired
	private ProductsCatalogDAO productsCatalogDAO;

	public void setStoreDAO(ProductsCatalogDAO productsCatalogDAO) {
		this.productsCatalogDAO = productsCatalogDAO;
	}

	@Override
	@Transactional
	public void add(ProductsCatalog product) {
		productsCatalogDAO.save(product);
	}

	@Override
	@Transactional
	public void addAll(Collection<ProductsCatalog> products) {
		for (ProductsCatalog product : products) {
			productsCatalogDAO.save(product);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<ProductsCatalog> listAll() {
		return productsCatalogDAO.list();

	}

	@Override
	@Transactional
	public ProductsCatalog fetch(long productId) {
		return productsCatalogDAO.fetch(productId);
	}

	@Override
	@Transactional
	public ProductsCatalog deleteProduct(long productId) {
		return productsCatalogDAO.delete(productId);
	}
}
