package com.imran.prac.service;

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

import com.imran.prac.controller.CategoryController;
import com.imran.prac.dao.CategoriesRepository;
import com.imran.prac.dto.Categories;
import com.imran.prac.service.exception.CategoryNotFountException;

public class CategorieServiceImpl implements CategorieService
{
	@Autowired
	private CategoriesRepository categoriesRepository;

	@Override
	public ResponseEntity<Object> addCategory(Categories categories)
	{
		categoriesRepository.save(categories);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(categories.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}

	@Override
	public Resource<Categories> findById(int id)
	{
		Optional<Categories> categories = categoriesRepository.findById(id);
		if (!categories.isPresent())
			throw new CategoryNotFountException("Id " + id + " is not found");

		Resource<Categories> resource = new Resource<Categories>(categories.get());
		ControllerLinkBuilder link = linkTo(methodOn(CategoryController.class).findById(id));
		resource.add(link.withRel("id"));
		return resource;
	}

	@Override
	public ResponseEntity<Object> deleteById(int id)
	{
		URI location;
		Optional<Categories> categories = categoriesRepository.findById(id);
		if (categories.isPresent())
		{
			categoriesRepository.deleteById(id);
			location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
		} else
			throw new CategoryNotFountException("Id " + id + " is not found");
		return ResponseEntity.created(location).build();
	}

	@Override
	public List<Resource<Categories>> getAllCategories()
	{
		List<Resource<Categories>> listResource = new ArrayList<Resource<Categories>>();
		List<Categories> categories = categoriesRepository.findAll();
		
		if (categories.isEmpty())
			throw new CategoryNotFountException("Not a single data is present");
		
		for (Categories category : categories)
		{
			Resource<Categories> resource = new Resource<Categories>(category);
			ControllerLinkBuilder link = linkTo(methodOn(CategoryController.class).findById(category.getId()));
			resource.add(link.withRel("category").withDeprecation("This is single link category").withMedia("I dont know what is media").withType("application/jason").withTitle("category"));
			listResource.add(resource);
		}
		
		return listResource;
	}

}
