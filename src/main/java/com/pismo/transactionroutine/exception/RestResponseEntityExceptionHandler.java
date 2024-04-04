package com.pismo.transactionroutine.exception;

import com.pismo.transactionroutine.http.response.FailureResponse;
import lombok.NonNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {ResourceNotFoundException.class})
    protected ResponseEntity<Object> handleResourceNotFoundException(@NonNull final RuntimeException ex, @NonNull final WebRequest request) {

        return handleExceptionInternal(ex, new FailureResponse(ex.getMessage()), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = {FailedValidationException.class})
    protected ResponseEntity<Object> handleBadRequestException(@NonNull final RuntimeException ex, @NonNull final WebRequest request) {

        return handleExceptionInternal(ex, new FailureResponse(ex.getMessage()), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}
