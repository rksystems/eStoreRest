/**
 * 
 */
package com.ss.estore.dao;

import java.util.List;

import com.ss.estore.model.Orders;

/**
 * @author Raghu Kishore
 *
 */
public interface OrdersDAO {
	public void save(Orders order);
	public void delete(long orderId);
	public List<Orders> list();
	public Orders fetch(long orderId);
}
