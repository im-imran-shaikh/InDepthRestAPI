package com.imran.practical.service;



import java.util.List;

import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.imran.practical.dto.Category;

@Service
public interface CategoryService
{
	List<Resource<Category>> getAllCategory();
	ResponseEntity<Object> addCategory(Category category);
	Resource<Category> getById(int id);
	ResponseEntity<Object> deleteById(int id);
}
