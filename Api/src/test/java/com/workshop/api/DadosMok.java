package com.workshop.api;

import java.math.BigDecimal;
import java.util.Date;

import com.workshop.api.model.Order;

public class DadosMok {
	public Order getOrder() {
        return Order.builder()
                .name("Lucas Barros")
                .product(1L)
                .price(BigDecimal.TEN)
                .dateBuy(new Date())
                .cpfClient("111.222.333-44")
                .cep("12345678")
                .email("axpraise1515@gmail.com")
                .build();
    }
	
	public Order getOrderSalvo() {
        return Order.builder()
                .id(2L)
                .name("alex nascimento")
                .product(1L)
                .price(BigDecimal.TEN)
                .dateBuy(new Date())
                .cpfClient("111.222.333-44")
                .cep("12345678")
                .email("axpraise1515@gmail.com")
                .build();
    }
}
