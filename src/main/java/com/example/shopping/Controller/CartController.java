package com.example.shopping.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.shopping.Model.Cart;
import com.example.shopping.Model.CartItemRequest;
import com.example.shopping.Model.Request.CreateCartRequest;
import com.example.shopping.Model.Request.UpdateCartRequest;
import com.example.shopping.Service.CartService;

@RestController
public class CartController {
	
	@Autowired
	CartService cartService;

	private static final String USERID_HEADER = "userId";

	
	@GetMapping(path = "/cart", headers = {USERID_HEADER} )
	public List<Cart> getCcart(@RequestHeader(USERID_HEADER) Long userId) {
		// Get all cart items based on user ID
		
		return cartService.getCart(userId);
		
	}
	
	@PostMapping(path = "/cart", headers = {USERID_HEADER} )
	public void createCcart(@RequestHeader(USERID_HEADER) Long userId, @RequestBody List<CartItemRequest> createCartRequest) {
		// Create entries in the Cart based on user ID header and the request body
		
		cartService.createCart(userId, createCartRequest);
		
	}
	
	@PatchMapping(path = "/cart", headers = {USERID_HEADER} )
	public void updateCcart(@RequestHeader(USERID_HEADER) Long userId, @RequestBody List<CartItemRequest> updateCartRequest) {
		
		cartService.updateCart(userId, updateCartRequest);
	}
	
}
