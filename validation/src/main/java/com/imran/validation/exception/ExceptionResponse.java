package com.imran.validation.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ExceptionResponse
{
	private Date timeStamp;
	private String detail;
	private String message;
}
