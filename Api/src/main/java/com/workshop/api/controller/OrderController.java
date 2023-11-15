package com.workshop.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.workshop.api.model.Order;
import com.workshop.api.service.OrderService;


@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;

  
	
	@PostMapping
	public ResponseEntity<Order> saveOrder(@RequestBody @Valid Order order) throws JsonProcessingException{
		return ResponseEntity.ok(orderService.saveOrder(order));
	}
	
	@GetMapping("/{id}")
    public ResponseEntity<Order> getOrderPorId(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.searchOrFailById(id));
    }
}
