package com.ss.estore.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ss.estore.dao.OrdersDAO;
import com.ss.estore.model.Address;
import com.ss.estore.model.OrderItems;
import com.ss.estore.model.Orders;
import com.ss.estore.model.ProductsCatalog;
import com.ss.estore.model.User;

@Service
public class OrdersServiceImpl implements OrdersService {
	@Autowired
	private OrdersDAO ordersDAO;
	@Autowired
	ProductsCatalogService productService;

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

	@Override
	public String addToCart(HttpSession session, int productId, int quantity) {
		ProductsCatalog product = productService.fetch(productId);
		User user = (User) session.getAttribute("user");
		if (user != null && product != null) {
			OrderItems orderItem1 = new OrderItems();
			orderItem1.setProductCatalogId(product.getProductID());
			orderItem1.setOrderItemName(product.getProductName());
			List<Address> add = (List<Address>) user.getAddress();
			if (add != null && add.size() > 0) {
				orderItem1.setAddressId(add.get(0).getAddressId());
			}
			orderItem1.setPrice(product.getPrice());
			orderItem1.setQuantity(quantity);
			orderItem1.setAdjustments(10);
			orderItem1.setTotalPrice(orderItem1.getPrice() * orderItem1.getQuantity());
			orderItem1.setUserId(user.getUserId());

			Orders order = currentOrder(user.getUserId());
			List<OrderItems> orderItemsList = null;
			if (order == null) {
				order = new Orders();
				orderItemsList = new ArrayList<OrderItems>();
				orderItemsList.add(orderItem1);
				order.setOrderItems(orderItemsList);
			} else {
				orderItemsList = (List<OrderItems>) order.getOrderItems();
				if (orderItemsList == null) {
					orderItemsList = new ArrayList<OrderItems>();
					orderItemsList.add(orderItem1);
					order.setOrderItems(orderItemsList);
				} else {
					if (orderItemsList.contains(orderItem1)) {
						long pid = product.getProductID();
						OrderItems item = orderItemsList.get(orderItemsList.indexOf(orderItem1));
						if (pid == item.getProductCatalogId()) {
							//int newQuantity = item.getQuantity() + quantity;
							item.setQuantity(quantity);
							item.setTotalPrice(item.getPrice() * item.getQuantity());
						}
					} else
						orderItemsList.add(orderItem1);
				}
			}
			double total = 0.0;
			for (OrderItems i : orderItemsList) {
				total += i.getTotalPrice();
			}
			order.setTotal(total);
			order.setTax(1);
			order.setShipping(1);
			order.setCurrency("INR");
			order.setUserId(user.getUserId());
			Orders orderFinal = order;
			order.getOrderItems().forEach(orderItems -> orderItems.setOrder(orderFinal));
			add(order);
			return "Product Added to cart";
		} else
			return "Please login";
	}

}
