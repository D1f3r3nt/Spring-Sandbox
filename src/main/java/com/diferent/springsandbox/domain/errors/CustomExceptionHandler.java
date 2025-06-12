package com.diferent.springsandbox.domain.errors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

	@ExceptionHandler(ServiceException.class)
	public ResponseEntity<ErrorBody> handlerServiceException(ServiceException exception) {
		ErrorBody error = ErrorBody.builder()
			.code(exception.getCode())
			.message(exception.getMessage())
			.build();

		return new ResponseEntity(error, exception.getHttpStatus());
	}
}
