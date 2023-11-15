package com.shopworker.workershop.repository;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.shopworker.workershop.model.Adress;

@FeignClient(name = "viacep", url = "https://viacep.com.br/ws")
public interface CepRepository {
	
	@GetMapping("/{cep}/json/")
    Adress buscarPorCep(@PathVariable("cep") String cep);
}
