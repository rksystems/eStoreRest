package com.ss.estore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ss.estore.dao.OrdersDAO;
import com.ss.estore.model.Orders;

@Service
public class OrdersServiceImpl implements OrdersService {
	@Autowired
	private OrdersDAO ordersDAO;

	public void setStoreDAO(OrdersDAO ordersDAO) {
		this.ordersDAO = ordersDAO;
	}

	@Transactional
	public void add(Orders order) {
		ordersDAO.save(order);
	}

	@Transactional(readOnly = true)
	public List<Orders> listAll(long userId) {
		return ordersDAO.list(userId);

	}

	@Override
	public Orders fetch(int storeId) {
		ordersDAO.fetch(storeId);
		return null;
	}

	@Override
	public void delete(long storeId) {
		ordersDAO.delete(storeId);

	}

	@Override
	public Orders currentOrder(long userId) {
		return ordersDAO.currentOrder(userId);
	}
}
