package com.peixeurbano.pablo.desafio;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DesafioBusinessException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DesafioBusinessException(final String message) {
        super(message);
    }
}
