package com.workshop.api.service.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.workshop.api.model.Order;

import lombok.SneakyThrows;

/* para teste
@RequestMapping("/producer")
@RestController*/
@Service
public class Producer {
	@Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private Queue queue;
    
    @Autowired
    private ObjectMapper mapper;
    
   /* @PostMapping teste
    public void producer(@RequestBody String payload) {
        rabbitTemplate.convertAndSend(queue.getName(), payload);
    }*/
    
    @SneakyThrows
    @PostMapping
    public void sendOrder(Order order) {        
        try {
        	rabbitTemplate.convertAndSend(queue.getName(), mapper.writeValueAsString(order));
        } catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}        
    }
}
