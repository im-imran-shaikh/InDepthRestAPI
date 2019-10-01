package com.imran.validation.service;

import java.util.List;

import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.imran.validation.dto.Laptop;

@Service
public interface laptopService
{
	Resource<Laptop> findById(int id);
	ResponseEntity<Object> add(Laptop laptop);
	List<Laptop> findAll();
}
