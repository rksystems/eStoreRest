/**
 * 
 */
package com.ss.estore.model;

import java.io.Serializable;

/**
 * @author Raghu Kishore
 *
 */
public class Catalog  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4180476452975750483L;
	private int catId;
	private int memberId;
	private String identifier;
	private String description;

	public int getCatId() {
		return catId;
	}

	public void setCatId(int catId) {
		this.catId = catId;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
