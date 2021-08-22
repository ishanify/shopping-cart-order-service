package com.example.shopping.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.shopping.Model.Cart;
import com.example.shopping.Model.Request.CreateCartRequest;
import com.example.shopping.Model.Request.UpdateCartRequest;

@Service
public class CartService {

	public List<Cart> getCart(Long userId) {
		return null;
	}

	public void createCart(Long userId, CreateCartRequest createCartRequest) {
		
	}

	public void updateCart(Long userId, UpdateCartRequest updateCartRequest) {
		
	}

}
