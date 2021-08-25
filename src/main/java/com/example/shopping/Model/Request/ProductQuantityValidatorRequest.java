package com.example.shopping.Model.Request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductQuantityValidatorRequest {


	private Integer productId;
	private Integer quantity;
}
