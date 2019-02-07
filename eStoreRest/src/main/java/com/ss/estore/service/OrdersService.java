package com.ss.estore.service;

import java.util.List;

import com.ss.estore.model.Orders;

public interface OrdersService {
	public void add(Orders orders);
	public Orders fetch(int orderId);
	public void delete(long orderId);
	public List<Orders> listAll(long userId);
	public Orders currentOrder(long userId);
}
