package com.example.shopping.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST,reason ="This operation cannot be performed on empty cart" )
public class EmptyCartException extends RuntimeException {

	public EmptyCartException(String string) {
		super(string);
	}

}
