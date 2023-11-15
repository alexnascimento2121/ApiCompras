package com.shopworker.workershop.service.consumer;

import java.io.IOException;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shopworker.workershop.model.Order;
import com.shopworker.workershop.service.EmailService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@Service
public class Consumer {
	
	@Autowired
	private ObjectMapper mapper;
	@Autowired
	private EmailService emailService;
	
	
	@RabbitListener(queues = {"${queue.name}"})
    public void consumer(@Payload Message message)throws IOException {
		// mapper pega os valores dentro da msg do rabbit e mapeia para respectivos campos do objeto order
		var order=mapper.readValue(message.getBody(), Order.class);
		log.info("Mensagem recebida no Worker shop: "+ order);
        emailService.notificarCliente(order);        
    }
}
