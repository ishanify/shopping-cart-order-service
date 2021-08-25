package com.example.shopping.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.shopping.Client.ProductServiceClient;
import com.example.shopping.Exception.EmptyCartException;
import com.example.shopping.Exception.InvalidProductException;
import com.example.shopping.Model.Cart;
import com.example.shopping.Model.Order;
import com.example.shopping.Model.OrderItems;
import com.example.shopping.Model.Product;
import com.example.shopping.Model.Request.CreateOrderRequest;
import com.example.shopping.Model.Request.ProductQuantityValidatorRequest;
import com.example.shopping.Model.Respone.OrderDetailsResponse;
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

	@Transactional
	public OrderDetailsResponse createOrder(Long userId, CreateOrderRequest createOrderRequest) {
		// fetch product data from product service
		// calculate amount, product count
		// save in order repo
		// save in order_items table
		// update cart to inactive for this user.

		List<Cart> cartList = cartService.getCart(userId);
		
		//cart empty validation
		if(cartList.isEmpty())
			throw new EmptyCartException("Cannot create order for empty cart");

		HashMap<Integer, Integer> productQuantityMap = cartList.stream()
				.collect(Collectors.toMap(c -> c.getProductId(), c -> c.getQuantity(), (c1, c2) -> c1, HashMap::new));

		List<ProductQuantityValidatorRequest> productValidatorRequestBody = cartList.stream()
				.map(c -> new ProductQuantityValidatorRequest(c.getProductId(), c.getQuantity()))
				.collect(Collectors.toList());

		List<Product> productsResponse = productServiceClient.validateProduct(productValidatorRequestBody);

		//if number of product types is mismatch then remove those products from cart and throw error
		//quantity validation out of scope for now
		if(productsResponse.size()<cartList.size()) {

			throw new InvalidProductException("Mismatch between cart and Product database");
		}
		
		//calculate order total
		BigDecimal orderAmount = productsResponse.stream()
				.map(p -> p.getPrice().multiply(BigDecimal.valueOf(productQuantityMap.get(p.getId()))))
				.reduce(BigDecimal.ZERO, BigDecimal::add);

		//calculate products total
		Integer totalProducts = productQuantityMap.values().stream().reduce(Integer.valueOf(0), (a, b) -> a + b);

		//Create order
		Order order = Order.builder().userId(userId).amount(orderAmount)
				.paymentStatus(createOrderRequest.getPaymentStatus()).productCount(totalProducts)
				.createdDate(LocalDate.now()).build();
		final Order finalOrder = orderRepository.save(order);

		//create order-items list
		List<OrderItems> orderItemsList = new ArrayList<OrderItems>();
		orderItemsList = productsResponse.stream()
				.map(product -> (OrderItems.builder().orderId(finalOrder.getId()).productId(product.getId())
						.quantity(productQuantityMap.get(product.getId()))
						.productAmount(product.getPrice()
								.multiply(BigDecimal.valueOf(productQuantityMap.get(product.getId()))))
						.build()))
				.collect(Collectors.toList());
		final List<OrderItems> finalOrderItemsList = (List<OrderItems>) orderItemsRepository.saveAll(orderItemsList);

		// update stock levels in product service
		productServiceClient.changeStockLevels(productValidatorRequestBody);
		
		//delete cart after successful order confirmation
		cartService.deleteCart(userId);

		return OrderDetailsResponse.builder().order(finalOrder).details(finalOrderItemsList).build();

	}

}
