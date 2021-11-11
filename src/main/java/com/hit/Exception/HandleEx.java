package com.hit.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class HandleEx {
	
	@ExceptionHandler(NotFoundEx.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorResponse errorResponse(NotFoundEx ex, WebRequest re) {
		return new ErrorResponse(404, ex.getMessage());
	}
	
	@ExceptionHandler(DuplicateEx.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResponse errorResponse(DuplicateEx ex, WebRequest re) {
		return new ErrorResponse(400, ex.getMessage());
	}

}
