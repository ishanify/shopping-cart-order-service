package com.example.shopping.Model.Respone;

import java.util.List;

import com.example.shopping.Model.Order;
import com.example.shopping.Model.OrderItems;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class OrderDetailsResponse {

	private Order order;
	private List<OrderItems> details;
}
