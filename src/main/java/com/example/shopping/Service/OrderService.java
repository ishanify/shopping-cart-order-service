package com.example.shopping.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.shopping.Client.ProductServiceClient;
import com.example.shopping.Model.Cart;
import com.example.shopping.Model.Order;
import com.example.shopping.Model.PaymentStatus;
import com.example.shopping.Model.Request.CreateOrderRequest;
import com.example.shopping.Repository.OrderItemsRepository;
import com.example.shopping.Repository.OrderRepository;

@Service
public class OrderService {
	OrderRepository orderRepository;
	OrderItemsRepository orderItemsRepository;
	CartService cartService;
	ProductServiceClient productServiceClient;
	
	public OrderService(OrderRepository orderRepository, OrderItemsRepository orderItemsRepository,
			CartService cartService, ProductServiceClient productServiceClient) {
		super();
		this.orderRepository = orderRepository;
		this.orderItemsRepository = orderItemsRepository;
		this.cartService = cartService;
		this.productServiceClient = productServiceClient;
	}


	
	public List<Order> getOrders(Long userId) {
		return null;
	}

	public List<Order> getOrderById(Long userId, Long orderId) {
		return null;
	}

	public Order createOrder(Long userId, CreateOrderRequest createOrderRequest) {
		List<Cart> cartList=cartService.getCart(userId);
		List<Integer> productList = cartList.stream().map(c->c.getProductId()).collect(Collectors.toList());
		
		//fetch product data from product service
		//calculate amount, product count
		//save in order repo
		//save in order_items table
		//update cart to inactive for this user.
		
		//return dummy data for now			
		Order o = Order.builder()
				.userId(userId)
				.amount(BigDecimal.valueOf(20))
				.paymentStatus(createOrderRequest.getPaymentStatus())
				.productCount(2)
				.createdDate(LocalDate.now())
				.build();
		return orderRepository.save(o);
	}

}
