package com.example.shopping.Model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data 
@Entity
@Table(name = "CART")
@Builder
public class Cart {
//This is for the cart items before order is placed.
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long userId;
	private Integer productId;
	private Integer quantity;
	@Enumerated(EnumType.STRING)
	private CartProductStatus cartProductStatus;
}
