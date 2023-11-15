package com.shop.validador.service.consumer;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop.validador.model.Order;
import com.shop.validador.service.ValidadorService;
import com.shop.validador.service.exceptions.LimiteIndisponivelException;
import com.shop.validador.service.exceptions.SaldoInsuficienteException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Component
public class Consumer {
	private final ObjectMapper mapper;
	private final ValidadorService validadorService;

    @RabbitListener(queues = {"${queue.name}"})
    public void consumer(@Payload Message message) throws IOException {
        var order = mapper.readValue(message.getBody(), Order.class);
        log.info("Pedido recebido no Validador: {}", order);
        
        try {
            validadorService.validarPedido(order);
        } catch (LimiteIndisponivelException | SaldoInsuficienteException e) {
            e.printStackTrace();
        }
    }
}
