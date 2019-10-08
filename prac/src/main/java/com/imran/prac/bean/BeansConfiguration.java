package com.imran.prac.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.imran.prac.service.CategorieService;
import com.imran.prac.service.CategorieServiceImpl;

@Configuration
public class BeansConfiguration
{
	@Bean
	public CategorieService getCategoriesInstance()
	{
		return new CategorieServiceImpl();
	}
}
