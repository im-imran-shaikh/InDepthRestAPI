package com.imran.validation.service;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.imran.validation.controller.PageController;
import com.imran.validation.dao.LaptopRepository;
import com.imran.validation.dto.Laptop;
import com.imran.validation.exception.LaptopNotFoundException;

public class LaptopServiceImpl implements laptopService
{
	@Autowired
	private LaptopRepository laptopRepository;

	@Override
	public Resource<Laptop> findById(int id)
	{
		Optional<Laptop> laptop = laptopRepository.findById(id);
		if (!laptop.isPresent())
			throw new LaptopNotFoundException("The id : " + id + " is not found");

		/*
		 * Using HATEOS(Hypertext as the Engine of Application State) to add
		 * link
		 */
		Resource<Laptop> resource = new Resource<Laptop>(laptop.get());

		// ControllLinkBuilder is used to create a link
		ControllerLinkBuilder link = linkTo(methodOn(PageController.class).getAllLaptops());

		// you can add link as many as you want
		ControllerLinkBuilder getByIdLink = linkTo(methodOn(PageController.class).getById(id));
		resource.add(link.withRel("all-laptop"));
		resource.add(getByIdLink.withRel("getById"));
		return resource;
	}

	@Override
	public ResponseEntity<Object> add(Laptop laptop)
	{
		laptopRepository.save(laptop);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(laptop.getId()).toUri();
		return ResponseEntity.created(location).build();
	}

	@Override
	public List<Laptop> findAll()
	{
		List<Laptop> laptop = laptopRepository.findAll();
		if (laptop.isEmpty())
			throw new LaptopNotFoundException("Laptops not found");

		return laptop;
	}

}
