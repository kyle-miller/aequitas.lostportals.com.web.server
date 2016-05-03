package com.lostportals.aequitas.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class UnprocessableEntityException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public UnprocessableEntityException() {
		super();
	}

	public UnprocessableEntityException(String message) {
		super(message);
	}

	public UnprocessableEntityException(String message, Throwable t) {
		super(message, t);
	}
}
