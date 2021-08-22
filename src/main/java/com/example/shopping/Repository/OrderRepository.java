package com.example.shopping.Repository;

import org.springframework.data.repository.CrudRepository;

import com.example.shopping.Model.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {

}
