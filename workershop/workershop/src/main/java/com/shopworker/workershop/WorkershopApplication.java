package com.shopworker.workershop;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableRabbit
@SpringBootApplication
public class WorkershopApplication extends SpringBootServletInitializer{
	//teste rapido de servico,sem usar controller
//	@Autowired
//	private CepService cepService;
	

	public static void main(String[] args) {
		SpringApplication.run(WorkershopApplication.class, args);
	}
	
	// preciso passar o bean para que spring carrege a classe e objeto
//	@Bean
//	void teste() {
//		cepService.buscarCep("76914858");
//	}
}
