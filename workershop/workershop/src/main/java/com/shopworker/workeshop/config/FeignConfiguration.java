package com.shopworker.workeshop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Logger;
import feign.Logger.Level;

@Configuration
public class FeignConfiguration {
	/**
	 * @author alexn
	 * essa classe serve como configuração para usar os logs do feign para tirar metricas de tempo dos endpoints
	 */
	@Bean
	public Logger.Level feignLoggerLevel(){
		feignLoggerLevel();
		return Level.HEADERS;
	}
}
