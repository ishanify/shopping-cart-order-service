package com.example.shopping.Model.Request;

import java.util.List;

import com.example.shopping.Model.Cart;
import com.example.shopping.Model.CartItemRequest;

import lombok.Data;

@Data
public class CreateCartRequest {

	
	private List<CartItemRequest> cartItems;
}
