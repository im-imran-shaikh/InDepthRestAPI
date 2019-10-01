package com.imran.validation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class LaptopNotFoundException extends RuntimeException
{
	public LaptopNotFoundException(String message)
	{
		super(message);
	}
}
