package com.example.shopping.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shopping.Model.Cart;
import com.example.shopping.Model.CartItemRequest;
import com.example.shopping.Model.CartProductStatus;
import com.example.shopping.Repository.CartRepository;

@Service
public class CartService {
	
	@Autowired
	private CartRepository cartRepository;

	public List<Cart> getCart(Long userId) {
		
		return cartRepository.findAllByUserIdAndCartProductStatus(userId, CartProductStatus.ACTIVE);
	}

	@Transactional
	public void createCart(Long userId, List<CartItemRequest> createCartRequest) {
		//TODO
		//update all previous cart items to inactive
		//delete old cart items for the user OR change to INACTIVE
		//check if productIds are valid
		//check if product quantity is sufficient
		//check if product is not duplicate in cart
		
		cartRepository.updateCartProductStatusbyUserId(userId, CartProductStatus.INACTIVE);
		
		List<Cart> cartList = new ArrayList<Cart>();
		
		cartList = createCartRequest.stream()
		.map(item-> Cart.builder()
				.userId(userId)
				.productId(item.getProductId())
				.quantity(item.getQuantity())
				.cartProductStatus(CartProductStatus.ACTIVE)
				.build())
		.collect(Collectors.toList());
		
		cartRepository.saveAll(cartList);
		
	}

	public void updateCart(Long userId, List<CartItemRequest> updateCartRequest) {
		
		//check if cart exists for user with ACTIVE items
		//check if all items exist in cart 
		//check if updated product quantity are available
		
		//all ACTIVE cart items for the user 
		List<Cart> userCart =  cartRepository.findAllByUserIdAndCartProductStatus(userId, CartProductStatus.ACTIVE);
		
		//cart has no active items, a post request must be created, create custom exception class
		if(userCart.size()==0)
			throw new RuntimeException();
		
		//filter the cart from DB and keep only the product id that are to be updated
		Map<Integer, Cart> updatedCart  = userCart.stream()
				.filter(cart -> updateCartRequest.stream()
						.anyMatch(updateCartItem -> updateCartItem.getProductId() == cart.getProductId()))
				.collect(Collectors.toMap(Cart::getProductId,Function.identity()));
		
		//if size not same meaning some extra product id are passed in request which were not in DB, throw custom 400 error
		if (updatedCart.size() != updateCartRequest.size())
			throw new RuntimeException();
		
		for(CartItemRequest cartItem: updateCartRequest) {
			//make items inactive for <= quantity
			if(cartItem.getQuantity()<=0)
			{
				updatedCart.get(cartItem.getProductId()).setCartProductStatus(CartProductStatus.INACTIVE);
				continue;
			}
			
			updatedCart.get(cartItem.getProductId()).setQuantity(cartItem.getQuantity());
			
		}
		cartRepository.saveAll(updatedCart.values());
		
		
	}

}
