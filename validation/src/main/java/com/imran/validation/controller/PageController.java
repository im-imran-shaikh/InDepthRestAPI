package com.imran.validation.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.imran.validation.dto.Laptop;
import com.imran.validation.service.laptopService;

import io.swagger.annotations.ApiModel;

@RestController
public class PageController
{
	@Autowired
	private laptopService laptopService;

	@GetMapping(path = "/getAllLaptops")
	public List<Laptop> getAllLaptops()
	{
		return laptopService.findAll();
	}

	@PostMapping(path = "/addLaptop")
	private ResponseEntity<Object> addLaptop(@RequestBody @Valid Laptop laptop)
	{
		return laptopService.add(laptop);
	}

	@GetMapping(path = "/getById/{id}")
	public Resource<Laptop> getById(@PathVariable(value = "id") int id)
	{
		return laptopService.findById(id);
	}
}
