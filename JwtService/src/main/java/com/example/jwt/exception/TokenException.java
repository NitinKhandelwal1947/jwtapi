package com.example.jwt.exception;

import org.springframework.http.HttpStatus;

public class TokenException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** The error message. */
	private String errorMessage = " Something went wrong.. \n please contact administrator";
	
	/** The error code. */
	private int statusCode;
	
	/** The http status. */
	private HttpStatus httpStatus;

	/**
	 * Instantiates a new Token exception.
	 */
	public TokenException() {
	}

	/**
	 * Instantiates a new Token exception.
	 *
	 * @param errMsg the err msg
	 */
	public TokenException(String errMsg) {
		this.errorMessage = errMsg;
	}

	/**
	 * Instantiates a new Token exception.
	 *
	 * @param errorMessage the error message
	 * @param statusCode the status code
	 * @param httpStatus the http status
	 */
	public TokenException(String errorMessage, int statusCode, HttpStatus httpStatus) {
		this.statusCode = statusCode;
		this.errorMessage = errorMessage;
		this.httpStatus=httpStatus;
	}
	
	/**
	 * Instantiates a new Token exception.
	 *
	 * @param statusCode the status code
	 * @param httpStatus the http status
	 */
	public TokenException(int statusCode, HttpStatus httpStatus) {
		this.statusCode = statusCode;
		this.httpStatus=httpStatus;
	}

	/**
	 * Gets the error message.
	 *
	 * @return the error message
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * Sets the error message.
	 *
	 * @param errorMessage the new error message
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/**
	 * Gets the status code.
	 *
	 * @return the status code
	 */
	public int getStatusCode() {
		return statusCode;
	}

	/**
	 * Sets the status code.
	 *
	 * @param statusCode the new status code
	 */
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	/**
	 * Gets the http status.
	 *
	 * @return the http status
	 */
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	/**
	 * Sets the http status.
	 *
	 * @param httpStatus the new http status
	 */
	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	}