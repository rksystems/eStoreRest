package com.ss.estore.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ss.estore.model.Address;
import com.ss.estore.model.OrderItems;
import com.ss.estore.model.Orders;
import com.ss.estore.model.ProductsCatalog;
import com.ss.estore.model.User;
import com.ss.estore.service.OrdersService;
import com.ss.estore.service.ProductsCatalogService;
import com.ss.estore.service.UserService;

/**
 * Handles requests for the Store service.
 */
@Controller
public class OrdersController {

	private static final Logger logger = LoggerFactory.getLogger(OrdersController.class);

	@Autowired
	OrdersService orderService;
	@Autowired
	UserService userService;
	@Autowired
	ProductsCatalogService productService;

	@RequestMapping(value = "/rest/orders/test", method = RequestMethod.GET)
	public @ResponseBody Orders getTestOrders() {
		logger.info("Start get test Orders");
		OrderItems orderItem1 = new OrderItems();
		orderItem1.setPrice(10);
		orderItem1.setQuantity(3);
		orderItem1.setTotalPrice(orderItem1.getPrice() * orderItem1.getQuantity());
		List<OrderItems> orderItemsList = new ArrayList<OrderItems>();
		orderItemsList.add(orderItem1);
		Orders order = new Orders();
		order.setDescription("Test Store");
		order.setOrderItems(orderItemsList);
		double total = 0.0;
		for (OrderItems i : orderItemsList) {
			total = +i.getTotalPrice();
		}
		order.setTotal(total);
		order.setTax(1);
		order.setShipping(1);
		return order;
	}

	@RequestMapping(value = "/rest/orders/{id}", method = RequestMethod.GET)
	public @ResponseBody Orders getOrders(@PathVariable("id") int orderId) {
		logger.info("Start getOrders. ID=" + orderId);
		return orderService.fetch(orderId);
	}

	@RequestMapping(value = "/rest/orders", method = RequestMethod.GET)
	public @ResponseBody List<Orders> getAllOrders(HttpSession session) {
		logger.info("Start getAllOrderss.");
		User user = (User) session.getAttribute("user");
		if (user != null) {
			return orderService.listAll(user.getUserId());
		} else
			return null;
	}

	@RequestMapping(value = "/rest/currentOrder", method = RequestMethod.GET)
	public @ResponseBody Orders getCurrentOrder(HttpSession session) {
		logger.info("Start getCurrentOrder.");
		User user = (User) session.getAttribute("user");
		if (user != null) {
			return orderService.currentOrder(user.getUserId());
		} else
			return null;
	}

	@Transactional
	@RequestMapping(value = "/rest/addToCart/{productId}/{quantity}", method = RequestMethod.POST)
	public @ResponseBody String addToCart(HttpSession session, @PathVariable("productId") int productId,
			@PathVariable("quantity") int quantity) {
		logger.info("Start createOrders.");
		return orderService.addToCart(session, productId, quantity);
	}

	@RequestMapping(value = "/rest/orders/delete/{id}", method = RequestMethod.PUT)
	public @ResponseBody String deleteOrder(@PathVariable("id") int orderId) {
		logger.info("Start deleteOrders.");
		return "Order not deleted";
	}

}
