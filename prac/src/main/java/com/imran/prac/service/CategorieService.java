package com.imran.prac.service;

import java.util.List;

import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.imran.prac.dto.Categories;

@Service
public interface CategorieService
{
	ResponseEntity<Object> addCategory(Categories categories);
	Resource<Categories> findById(int id);
	ResponseEntity<Object> deleteById(int id);
	List<Resource<Categories>> getAllCategories();
}
