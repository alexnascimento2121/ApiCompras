package com.workshop.api.service.exception;

public class PersonNotFoundException extends EntityNotFoundException {

	private static final long serialVersionUID = -6308620347093100579L;

	public PersonNotFoundException(String msg) {
        super(msg);
    }

    public PersonNotFoundException(Long id) {
        this(String.format("Não existe uma pessoa com o id: %s na base de dados!", id));
    }

}
