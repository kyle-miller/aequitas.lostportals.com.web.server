package com.lostportals.aequitas.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class ValidationException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ValidationException() {
        super();
    }

    public ValidationException(String message) {
        super(message);
    }
}
