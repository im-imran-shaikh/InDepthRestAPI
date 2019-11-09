package com.imran.practical.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.imran.practical.service.CategoryService;
import com.imran.practical.service.CategoryServiceImpl;

@Configuration
public class BeansConfiguration
{
	@Bean
	public CategoryService getCategoryService()
	{
		return new CategoryServiceImpl();
	}
}
