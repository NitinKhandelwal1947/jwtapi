package com.example.jwt.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.jwt.exception.TokenException;
import com.example.jwt.response.ErrorResponse;

@ControllerAdvice
public class TokenExceptionHandlerController extends ResponseEntityExceptionHandler {
	/**
	 * Token exception handler.
	 *
	 * @param ex
	 *            the ex
	 * @return the response entity
	 */
	@ExceptionHandler(TokenException.class)
	public ResponseEntity<ErrorResponse> handleTokenException(TokenException ex) {
		ErrorResponse error = new ErrorResponse();
		error.setMessage(ex.getErrorMessage());
		error.setErrorCode(ex.getStatusCode());		
		return new ResponseEntity<ErrorResponse>(error, ex.getHttpStatus());
	}
}
