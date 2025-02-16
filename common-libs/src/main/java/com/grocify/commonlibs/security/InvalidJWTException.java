package com.grocify.commonlibs.security;

import lombok.Data;

@Data
public class InvalidJWTException extends RuntimeException{

	private String message;

	public InvalidJWTException(String message) {
		super(message);
		this.message = message;
	}

}
