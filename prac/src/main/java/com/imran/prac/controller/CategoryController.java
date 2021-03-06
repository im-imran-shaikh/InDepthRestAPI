package com.imran.prac.controller;

import java.net.URI;
import java.util.ArrayList;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.imran.prac.dao.CategoriesRepository;
import com.imran.prac.dao.MobileRepository;
import com.imran.prac.dto.Categories;
import com.imran.prac.dto.Mobile;
import com.imran.prac.dto.Name;
import com.imran.prac.dto.PersonV1;
import com.imran.prac.dto.PersonV2;
import com.imran.prac.service.CategorieService;

@RestController
public class CategoryController
{
	// private List<Categories> categoriesList = new ArrayList<>();

	@Autowired
	private Categories mobileCategories;

	@Autowired
	private CategorieService categorieService;

	@Autowired
	private CategoriesRepository categoriesReposiotry;

	@Autowired
	private Categories categories;

	@Autowired
	private PersonV1 personV1;

	@Autowired
	private PersonV2 personV2;

	@Autowired
	private Name name;

	@Autowired
	private MobileRepository mobileRepository;

	// private Map<String, Categories> storedCategories = new TreeMap<>();

	@PostMapping(path = "/addCategory")
	public ResponseEntity<Object> addCategory(@Valid @RequestBody Categories categories)
	{
		return categorieService.addCategory(categories);
	}

	@DeleteMapping(path = "/deleteById/{id}")
	public ResponseEntity<Object> deleteById(@PathVariable(name = "id") int id)
	{
		return categorieService.deleteById(id);
	}

	@GetMapping(path = "/getById/{id}")
	public Resource<Categories> findById(@PathVariable(name = "id") int id)
	{
		return categorieService.findById(id);
	}

	@GetMapping(path = "/getAllCategories")
	public List<Resource<Categories>> findAllCategories()
	{
		return categorieService.getAllCategories();
	}

	@GetMapping("/test")
	public Categories testCategory()
	{
		categories.setId(1);
		categories.setName("laptop");
		categories.setDescription("This is laptop category");

		return categories;
	}

	/**
	 * Versioning in api, The following is param version To access param version
	 * fetch with localhost:8888/person/parm?version=1 url
	 */
	@GetMapping(path = "/person/param", params = "version=1")
	public PersonV1 getPersonV1()
	{
		personV1.setName("Imran Shaikh");
		personV1.setAge(23);
		return personV1;
	}

	@GetMapping(path = "/person/param", params = "version=2")
	public PersonV2 getPersonV2()
	{
		name.setFirstName("Imran");
		name.setLastName("Shaikh");
		personV2.setName(name);
		personV2.setAge(23);
		return personV2;
	}

	/**
	 * header versioning
	 */
	@GetMapping(path = "/person/header", headers = "version=1")
	public PersonV1 getPersonByHeaderV1()
	{
		personV1.setName("Imran Shaikh");
		personV1.setAge(23);
		return personV1;
	}

	@GetMapping(path = "/person/header", headers = "version=2")
	public PersonV2 getPersonByHeaderV2()
	{
		name.setFirstName("Imran");
		name.setLastName("Shaikh");
		personV2.setName(name);
		personV2.setAge(23);
		return personV2;
	}

	@PostMapping(path = "/addmobile")
	public ResponseEntity<Object> addMobile(@RequestBody Mobile mobile)
	{
		if (mobile.getCategories() == null)
		{
			if (mobileCategories.getId() == 0) 
				mobileCategories = categoriesReposiotry.findByName("Mobile");

			mobile.setCategories(mobileCategories);
		}

		if (mobile != null)
			mobileRepository.save(mobile);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(mobile.getId())
				.toUri();
		return ResponseEntity.created(location).build();

	}

}