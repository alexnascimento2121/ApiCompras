package com.workshop.api.service.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfig {
//	@Value("${queue.name}")
//    private String queueName;

    @Bean
    public Queue queue() {
    	return new Queue("COMPRA_EFETUADA");

    }
}
