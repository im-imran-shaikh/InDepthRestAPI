package com.imran.practical.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.imran.practical.dto.Category;
import com.imran.practical.service.CategoryService;

@RestController
public class CategoryController
{
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping(path = "/getAllCategory")
	public List<Resource<Category>> getAllCategory()
	{
		return categoryService.getAllCategory();
	}
	
	@GetMapping(path = "/getById/{id}")
	public Resource<Category> getById(@PathVariable(name = "id") int id)
	{
		return null;
	}
	
	@PostMapping(path = "/addCategory")
	public ResponseEntity<Object> addCategory(@Valid @RequestBody Category category)
	{
		return categoryService.addCategory(category);
	}
	
	@DeleteMapping(path = "/deleteById/{id}")
	public ResponseEntity<Object> deleteById(@PathVariable(name = "id") int id)
	{
		return categoryService.deleteById(id);
	}
}
