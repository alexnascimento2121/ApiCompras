package com.shop.validador.service.exceptions;

public class LimiteIndisponivelException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 7891711376945980045L;

	public LimiteIndisponivelException(String message) {
        super(message);
    }
}
