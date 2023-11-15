package com.shopworker.workershop.model;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@ToString
public class Card implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String numero;
    private BigDecimal limiteDisponivel;
	  
}
