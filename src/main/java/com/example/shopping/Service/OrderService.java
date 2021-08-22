package com.example.shopping.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.shopping.Model.Order;
import com.example.shopping.Model.Request.CreateOrderRequest;

@Service
public class OrderService {

	public List<Order> getOrders(Long userId) {
		return null;
	}

	public List<Order> getOrderById(Long userId, Long orderId) {
		return null;
	}

	public Order createOrder(Long userId, CreateOrderRequest createOrderRequest) {
		return null;
	}

}
