package com.workshop.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.workshop.api.model.Order;
public interface OrderRepository extends JpaRepository<Order, Long>{

}
