package com.shopworker.workershop.service.producer;


import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shopworker.workershop.model.Card;
import com.shopworker.workershop.model.Order;
import com.shopworker.workershop.service.CardService;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class OrderProducer {
	
	private final RabbitTemplate rabbitTemplate;	
	private final Queue queue;
    private final ObjectMapper mapper;
    private final CardService cardService;
   
    
    @SneakyThrows
    @PostMapping
    public void sendOrder(Order order) {        
        try {
        	order.setCard(Card.builder()
        			.numero(cardService.newCard())
                    .limiteDisponivel(cardService.newLimit())
            .build());
    rabbitTemplate.convertAndSend(queue.getName(), mapper.writeValueAsString(order));
    log.info("Pedido montado com sucesso em Worker Compras - PedidoProducer: {}", mapper.writeValueAsString(order));

		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}        
    }
}
