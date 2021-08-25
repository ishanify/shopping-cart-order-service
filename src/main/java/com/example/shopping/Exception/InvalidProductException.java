package com.example.shopping.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST,reason ="One or more products are invalid in request" )

public class InvalidProductException extends RuntimeException {

	public InvalidProductException(String string) {
		super(string);
	}
	

}
