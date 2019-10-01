package com.imran.validation.exception;

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
	
	/**
	 *  if any internal server exception occur this method will trigger
	 * @param exception
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ExceptionHandler(Exception.class)
	private final ResponseEntity<Object> handleAllException(Exception exception, WebRequest request) throws Exception
	{
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), request.getDescription(false),
				exception.getMessage());
		
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	/**
	 *  if user not found this method will trigger
	 * @param exception
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ExceptionHandler(LaptopNotFoundException.class)
	private final ResponseEntity<Object> handleUserNotFoundException(Exception exception, WebRequest request) throws Exception
	{
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), request.getDescription(false),
				exception.getMessage());
		
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.NOT_FOUND);
	}
	
	/** 
	 *  If validation fail this handleMethodArgumentNotValid will trigger automatically
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
			HttpHeaders headers, HttpStatus status, WebRequest request)
	{
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), request.getDescription(false),
				exception.getBindingResult().toString());
		return new ResponseEntity<Object>(exceptionResponse,HttpStatus.BAD_REQUEST);
	}
}
