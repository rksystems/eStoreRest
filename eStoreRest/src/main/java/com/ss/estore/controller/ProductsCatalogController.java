package com.ss.estore.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ss.estore.model.ProductsCatalog;
import com.ss.estore.service.ProductsCatalogService;

/**
 * Handles requests for the ProductsCatalog service.
 */
@Controller
public class ProductsCatalogController {

	private static final Logger logger = LoggerFactory.getLogger(ProductsCatalogController.class);

	@Autowired
	ProductsCatalogService service;

	@RequestMapping(value = "/rest/catalog/test", method = RequestMethod.GET)
	public @ResponseBody ProductsCatalog getTestProduct() {
		logger.info("Start getTestProduct");
		ProductsCatalog product = new ProductsCatalog();
		product.setProductName("Fox Tail Millet");
		product.setProductShortDesc("Millet");
		product.setProductLongDesc("Millets are stable food in few centures ago. Rich in Fiber and Protein");
		product.setPrice(80);
		product.setQuantity("100");
		product.setCategory("Millets");
		product.setSubCategory("");
		product.setDiscount("0");
		product.setActive("Y");
		product.setRating("5");
		product.setImageLocation("/images/Foxtail.jpg");
		return product;
	}

	@RequestMapping(value = "/rest/catalog/{id}", method = RequestMethod.GET)
	public @ResponseBody ProductsCatalog getProduct(@PathVariable("id") int productId) {
		logger.info("Start getProduct. ID=" + productId);
		return service.fetch(productId);
	}

	@RequestMapping(value = "/rest/catalog/products", method = RequestMethod.GET)
	public @ResponseBody List<ProductsCatalog> getAllProducts() {
		logger.info("Start getAllProducts.");
		return service.listAll();
	}

	@RequestMapping(value = "/rest/catalog/create", method = RequestMethod.POST)
	public @ResponseBody String createProduct(@RequestBody ProductsCatalog product) {
		logger.info("Start createProduct.");
		service.add(product);
		return "New Product created";
	}

	@RequestMapping(value = "/rest/catalog/delete/{id}", method = RequestMethod.PUT)
	public @ResponseBody String deleteProduct(@PathVariable("id") int productId) {
		logger.info("Start deleteProduct.");
		service.deleteProduct(productId);
		return "Product deleted";
	}

}
