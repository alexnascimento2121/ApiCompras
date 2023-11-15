package com.shopworker.workershop.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.shopworker.workershop.model.Order;
import com.shopworker.workershop.service.producer.OrderProducer;

import lombok.extern.slf4j.Slf4j;



@Slf4j
@Service
public class EmailService {
	
	@Autowired
    private JavaMailSender javaMailSender;
	
	@Autowired
	private OrderProducer orderProducer;
	

    public void notificarCliente(Order order) {
        var msg = new SimpleMailMessage();
        msg.setTo(order.getEmail());
        msg.setSubject("Compra recebida");
        msg.setText("Este é um e-mail de confirmação de compra recebida. " +
                "Agora vamos aprovar sua compra e brevemente você receberá um novo e-mail de confirmação." +
                "\nObrigado por comprar com a gente!!");
        javaMailSender.send(msg);
        log.info("Cliente notificado com sucesso!!");
        
        log.info("Preparando Pedido no OrderProducer...");
        orderProducer.sendOrder(order);
    }
}
