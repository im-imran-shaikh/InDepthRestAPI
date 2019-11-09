package com.imran.springsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PageController
{
	@Autowired
	private UserDetailsService userDetailsService;
	
	@GetMapping("/home")
	public String getMessage()
	{
		return "Greeting, Welcome to my port folio";
	}
	
	@GetMapping("/about")
	public String getAbout()
	{
		return "About Us";
	}
	
	@GetMapping("/userDetails")
	public String getUserDetails()
	{
		String password = userDetailsService.loadUserByUsername("imran").getPassword();
		return "User password is : " + password ;
	}
}
