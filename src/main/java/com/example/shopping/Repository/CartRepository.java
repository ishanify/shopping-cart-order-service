package com.example.shopping.Repository;

import org.springframework.data.repository.CrudRepository;

import com.example.shopping.Model.Cart;

public interface CartRepository extends CrudRepository<Cart, Long>{

}
