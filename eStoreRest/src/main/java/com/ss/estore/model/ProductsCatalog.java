/**
 * 
 */
package com.ss.estore.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.transaction.annotation.Transactional;

/**
 * @author ragrayal
 *
 */
@Entity
@Table(name = "productscatalog")
public class ProductsCatalog {
	public ProductsCatalog() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "productID")
	private long productID;

	@Column(name = "productName", length = 45)
	private String productName;

	@Column(name = "productShortDesc", length = 100)
	private String productShortDesc;

	@Column(name = "productLongDesc", length = 4000)
	private String productLongDesc;

	@Column(name = "price", scale=10, precision=2)
	private int price;

	@Column(name = "discount", scale=10, precision=2)
	private String discount;

	@Column(name = "imageLocation", length = 500)
	private String imageLocation;

	@Column(name = "rating")
	private String rating;

	@Column(name = "quantity")
	private String quantity;

	@Column(name = "category", length = 45)
	private String category;

	@Column(name = "subCategory", length = 45)
	private String subCategory;

	@Column(name = "active", length = 1)
	private String active;

	public long getProductID() {
		return productID;
	}

	public void setProductID(long productID) {
		this.productID = productID;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductShortDesc() {
		return productShortDesc;
	}

	public void setProductShortDesc(String productShortDesc) {
		this.productShortDesc = productShortDesc;
	}

	public String getProductLongDesc() {
		return productLongDesc;
	}

	public void setProductLongDesc(String productLongDesc) {
		this.productLongDesc = productLongDesc;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public String getImageLocation() {
		return imageLocation;
	}

	public void setImageLocation(String imageLocation) {
		this.imageLocation = imageLocation;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

}
