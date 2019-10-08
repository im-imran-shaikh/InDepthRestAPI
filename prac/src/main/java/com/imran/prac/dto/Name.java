package com.imran.prac.dto;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class Name
{
	private String firstName;
	private String lastName;
}
