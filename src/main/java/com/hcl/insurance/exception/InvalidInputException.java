package com.hcl.insurance.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)	
public class InvalidInputException extends Exception {

	private static final long serialVersionUID = -7000578025975626294L;

	public InvalidInputException(Throwable t, String msg) {
		super(msg, t);
	}

	public InvalidInputException(Throwable t) {
		super(t);
	}

	public InvalidInputException(String msg) {
		super(msg);
	}

}
