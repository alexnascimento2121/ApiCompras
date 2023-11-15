package com.workshop.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workshop.api.model.Order;
import com.workshop.api.repository.OrderRepository;
import com.workshop.api.service.exception.EntityNotFoundException;
import com.workshop.api.service.rabbitmq.Producer;

@Service
public class OrderService {
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private Producer producer;

   	
	public Order saveOrder(Order order){	
		order = orderRepository.save(order);
		producer.sendOrder(order);
		return order;
	}
	
	// metodo para teste do mockito com expressao lambda
	 public Order searchOrFailById(Long id) {
	        return orderRepository.findById(id)
	        		.orElseThrow(()-> new EntityNotFoundException("O pedido de id: " + id + " nao existe na base de dados!"));
	    }
	 
	 public void delete(Long id) {
	        Order order = searchOrFailById(id);
	        orderRepository.deleteById(order.getId());
	    }

}
