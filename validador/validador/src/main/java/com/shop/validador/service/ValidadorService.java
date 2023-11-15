package com.shop.validador.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.validador.model.Card;
import com.shop.validador.model.Order;
import com.shop.validador.service.exceptions.LimiteIndisponivelException;
import com.shop.validador.service.exceptions.SaldoInsuficienteException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ValidadorService {
	
	@Autowired
    private EmailService emailService;
	
	public void validarPedido(Order order){
        validarLimiteDisponivel(order.getCard());
        validarCompraComLimite(order);
        emailService.notificarClienteCompraComSucesso(order.getEmail());
    }

    private void validarCompraComLimite(Order order) {
        if (order.getPrice().longValue() > order.getCard().getLimiteDisponivel().longValue())
            log.error("Valor do order: {}. Limite disponivel: {}", order.getCard(), order.getCard().getLimiteDisponivel());
        throw new SaldoInsuficienteException("Voce nao tem limite para efetuar essa compra!");
    }

    private void validarLimiteDisponivel(Card card) {
        if (card.getLimiteDisponivel().longValue() <= 0)
        	 throw new LimiteIndisponivelException("Limite indisponivel!");
    }
}
