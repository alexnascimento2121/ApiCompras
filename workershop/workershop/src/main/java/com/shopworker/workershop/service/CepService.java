package com.shopworker.workershop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopworker.workershop.model.Adress;
import com.shopworker.workershop.repository.CepRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CepService {
	
		
	 	@Autowired
	    private CepRepository cepRepository;

	    public void buscarCep(String cep) {
	        Adress adress = cepRepository.buscarPorCep(cep);
	        System.out.println(adress);
	        log.info("Endereco montado com sucesso: {}", adress);
	    }
}
