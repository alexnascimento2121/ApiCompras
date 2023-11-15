package com.shopworker.workershop.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

@Service
public class CardService {
	 public String newCard() {
	        var seq1 = new BigDecimal(1000 + (long)(Math.random() * 500));
	        var seq2 = new BigDecimal(2000 + (long)(Math.random() * 500));
	        var seq3 = new BigDecimal(3000 + (long)(Math.random() * 500));
	        var seq4 = new BigDecimal(4000 + (long)(Math.random() * 500));

	        return seq1 + " " + seq2 + " " + seq3 + " " + seq4;
	    }

	    public BigDecimal newLimit() {
	        return new BigDecimal(1000 + (long)(Math.random() * 500));
	    }
}
