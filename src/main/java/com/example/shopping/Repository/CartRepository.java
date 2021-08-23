package com.example.shopping.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.shopping.Model.Cart;
import com.example.shopping.Model.CartProductStatus;

public interface CartRepository extends CrudRepository<Cart, Long>{
	

	@Query("select c from Cart c where c.userId = ?1 AND cartProductStatus = ?2")
	List<Cart> findAllByUserIdAndCartProductStatus(Long userId, CartProductStatus status);

	@Modifying
	@Query("update Cart c set c.cartProductStatus = ?2 where c.userId = ?1")
	void updateCartProductStatusbyUserId(Long userId, CartProductStatus status);
}
