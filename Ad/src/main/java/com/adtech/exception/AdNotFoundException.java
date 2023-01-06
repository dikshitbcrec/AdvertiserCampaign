package com.adtech.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AdNotFoundException extends RuntimeException {
		
	

	private static final long serialVersionUID = 1L;

	public AdNotFoundException(String message) {
		super(message);
	}
}
