package com.imran.validation.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.imran.validation.service.LaptopServiceImpl;
import com.imran.validation.service.laptopService;

@Configuration
public class ConfigureBean
{
	@Bean
	public laptopService getlaptopServiceInstance()
	{
		return new LaptopServiceImpl();
	}
}
