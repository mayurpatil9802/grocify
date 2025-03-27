

package com.grocify.commonlibs.security;

import com.grocify.commonlibs.model.response.GrocifyErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AuthExceptionHandler {

	@ExceptionHandler(InvalidJWTException.class)
	public ResponseEntity<GrocifyErrorResponse> handleInvalidRequestException(InvalidJWTException exception) {
		GrocifyErrorResponse errorResponse = GrocifyErrorResponse.builder()
				.message(exception.getMessage())
				.errorCode(HttpStatus.UNAUTHORIZED.value())
				.build();

		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
	}
}
