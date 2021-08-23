package com.example.shopping.Model.Request;

import com.example.shopping.Model.PaymentStatus;

import lombok.Data;
@Data
public class CreateOrderRequest {
	private PaymentStatus paymentStatus;

}
