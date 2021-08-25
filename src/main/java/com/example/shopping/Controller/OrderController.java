package com.example.shopping.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.shopping.Model.Cart;
import com.example.shopping.Model.Order;
import com.example.shopping.Model.Request.CreateOrderRequest;
import com.example.shopping.Model.Respone.OrderDetailsResponse;
import com.example.shopping.Service.OrderService;

@RestController
public class OrderController {

	private static final String USERID_HEADER = "userId";
	
	@Autowired
	OrderService orderService;

	@GetMapping(path = "/orders", headers = {USERID_HEADER} )
	public List<Order> getOorders(@RequestHeader(USERID_HEADER) Long userId) {
		// Get all orders based on user ID
		return orderService.getOrders(userId);
		
	}
	
	@GetMapping(path = "/order/{ID}", headers = {USERID_HEADER} )
	public List<Order> getOrderByOrderId(@RequestHeader(USERID_HEADER) Long userId, @PathVariable Long orderId) {
		// Get order based on user ID and orderID 
		return orderService.getOrderById(userId, orderId);
		
	}
	
	@PostMapping(path = "/order", headers = {USERID_HEADER} )
	public OrderDetailsResponse createOorder(@RequestHeader(USERID_HEADER) Long userId, @RequestBody CreateOrderRequest createOrderRequest) {
		// Create new order and return the order confirmation details.
		
		return orderService.createOrder(userId,createOrderRequest);
	}
	
	
}
