package com.newsfeedback.core.controller;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.newsfeedback.core.entity.ErrorResponse;

/**
 * @author 729706
 * Name: Ankur Bose
 * Date: Feb 28, 2019
 */
public class GlobalErrorHandlerController {
	private static final Logger logger = LoggerFactory.getLogger(GlobalErrorHandlerController.class);
	String errorMessage;

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleError(Exception ex) {
		logger.info("inside handleError");
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setTimestamp(LocalDateTime.now().toString());
		errorMessage = "Error: ";
		if (ex instanceof BadCredentialsException) {
			logger.info("leaving handleError with status not acceptable");
			errorResponse.setErrorMessage("Wrong emailId or password");
			errorResponse.setReasonCode(HttpStatus.NOT_ACCEPTABLE.value());
			return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_ACCEPTABLE);
		}
		if (ex instanceof MethodArgumentNotValidException) {
			
			((MethodArgumentNotValidException) ex).getBindingResult().getFieldErrors().forEach(message -> {
				String error = message.getDefaultMessage() + "\r\n";
				errorMessage += error;
			});
			logger.info("leaving searchAllUser with status bad request");
			errorResponse.setErrorMessage(errorMessage);
			errorResponse.setReasonCode(HttpStatus.BAD_REQUEST.value());
			return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
		}
		if (ex instanceof AccessDeniedException) {
			logger.info("leaving searchAllUser with status unauthorized");
			errorResponse.setErrorMessage("You are unauthorized to access this content");
			errorResponse.setReasonCode(HttpStatus.UNAUTHORIZED.value());
			return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.UNAUTHORIZED);
		}
		logger.info("leaving searchAllUser with status bad gateway");
		errorResponse.setErrorMessage("Something went wrong");
		errorResponse.setReasonCode(HttpStatus.BAD_GATEWAY.value());
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_GATEWAY);
	}
}
