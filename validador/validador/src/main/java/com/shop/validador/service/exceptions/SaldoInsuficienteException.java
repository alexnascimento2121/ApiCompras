package com.shop.validador.service.exceptions;

public class SaldoInsuficienteException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1669553668036483382L;

	public SaldoInsuficienteException(String message) {
        super(message);
    }
}
