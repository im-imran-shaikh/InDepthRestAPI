package com.imran.prac.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CategoryNotFountException extends RuntimeException
{
	public CategoryNotFountException(String message)
	{
		super(message);
	}
}
