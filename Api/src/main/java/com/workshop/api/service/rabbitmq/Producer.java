package com.workshop.api.service.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.workshop.api.model.Order;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class Producer {

	private final RabbitTemplate rabbitTemplate;   
	private final Queue queue;  

	private final ObjectMapper mapper;
    
   /* @PostMapping teste
    public void producer(@RequestBody String payload) {
        rabbitTemplate.convertAndSend(queue.getName(), payload);
    }*/
    
    @SneakyThrows
    @PostMapping
    public void sendOrder(Order order) { 
        	rabbitTemplate.convertAndSend(queue.getName(), mapper.writeValueAsString(order));
    }
}
