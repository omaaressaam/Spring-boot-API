package com.SpringBoot.api.SpringApi.Exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.SpringBoot.api.SpringApi.Users.UserNotFoundException;


@ControllerAdvice
public class ErorrHandling extends ResponseEntityExceptionHandler {
	
	
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<ErrorDetails> HandleUserExistance (Exception ex , WebRequest req) {
		
		ErrorDetails details = new ErrorDetails(LocalDateTime.now(),ex.getMessage(),req.getDescription(false));
		
		return new ResponseEntity<ErrorDetails>(details , HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
