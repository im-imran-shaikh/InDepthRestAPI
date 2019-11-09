package com.imran.practical.service;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.imran.practical.controller.CategoryController;
import com.imran.practical.dao.CategoryRepository;
import com.imran.practical.dto.Category;
import com.imran.practical.exception.CategoryNotFoundException;

public class CategoryServiceImpl implements CategoryService
{
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public List<Resource<Category>> getAllCategory()
	{
		List<Category> categories = categoryRepository.findAll();
		if (categories.isEmpty())
			throw new CategoryNotFoundException("Categories is empty");

		List<Resource<Category>> listOfResource = new ArrayList<>();
		for (Category category : categories)
		{
			Resource<Category> resource = new Resource<Category>(category);
			ControllerLinkBuilder link = linkTo(methodOn(CategoryController.class).getById(category.getId()));
			resource.add(link.withRel("categories").withTitle("Category id " + category.getId())
					.withType("application/json"));
			listOfResource.add(resource);
		}
		return listOfResource;
	}

	@Override
	public ResponseEntity<Object> addCategory(Category category)
	{
		if (category.getName().length() <= 0 || category.getDescription().length() <= 0)
			throw new CategoryNotFoundException("please don't add empty data");

		categoryRepository.save(category);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(category.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}

	@Override
	public Resource<Category> getById(int id)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Object> deleteById(int id)
	{
		Optional<Category> category = categoryRepository.findById(id);
		if (!category.isPresent())
			throw new CategoryNotFoundException("Id " + id + " not found");
		else
			categoryRepository.deleteById(id);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();

		return ResponseEntity.created(location).build();
	}

}
