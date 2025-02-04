package com.grocify.storemgmt.exception.handler;



import com.grocify.commonlibs.model.response.StoreErrorResponse;
import com.grocify.storemgmt.exception.InvalidRequestException;
import com.grocify.storemgmt.exception.StoreNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GrocifyExceptionHandler {

    @ExceptionHandler(StoreNotFoundException.class)
    public ResponseEntity<StoreErrorResponse> handleUserNotFoundException(StoreNotFoundException exception) {
        return new ResponseEntity<>(StoreErrorResponse.builder().message(exception.getMessage()).errorCode(HttpStatus.NOT_FOUND.value()).build(), HttpStatusCode.valueOf(HttpStatus.NOT_FOUND.value()));
    }

    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<StoreErrorResponse> handleInvalidRequestException(InvalidRequestException exception) {
        return new ResponseEntity<>(StoreErrorResponse.builder().message(exception.getMessage()).errorCode(HttpStatus.BAD_REQUEST.value()).build(), HttpStatusCode.valueOf(HttpStatus.BAD_REQUEST.value()));
    }
}
