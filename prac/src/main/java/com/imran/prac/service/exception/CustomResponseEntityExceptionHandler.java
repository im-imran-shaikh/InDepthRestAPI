package com.imran.prac.service.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler
{
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllException(Exception exception, WebRequest request) throws Exception
	{
		return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(CategoryNotFountException.class)
	public final ResponseEntity<Object> handleNotFoundException(Exception exception, WebRequest request) throws Exception
	{
		return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
		System.out.println("getContextPath " + request.getContextPath());
		System.out.println("getCause" + ex.getCause());
		System.out.println("status " + status.getReasonPhrase());

		return new ResponseEntity<Object>(exceptionResponse,HttpStatus.BAD_REQUEST);
	}
	
	
}
