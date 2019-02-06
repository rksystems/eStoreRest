package com.ss.estore.service;

import java.util.Collection;
import java.util.List;

import com.ss.estore.model.Orders;

public interface OrdersService {
	public void add(Orders orders);
	public Orders fetch(int orderId);
	public void addAll(Collection<Orders> orders);
	public void delete(long orderId);
	public List<Orders> listAll();
}
