package com.example.shopping.Repository;

import org.springframework.data.repository.CrudRepository;

import com.example.shopping.Model.OrderItems;

public interface OrderItemsRepository extends CrudRepository<OrderItems, Long> {

}
