package com.example.shopping.Model;

import lombok.Data;

@Data
public class CartItemRequest {
	private Integer productId;
	private Integer quantity;
}
