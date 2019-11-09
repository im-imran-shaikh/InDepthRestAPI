package com.imran.SpringBoot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imran.SpringBoot.dao.CategoriesRepository;
import com.imran.SpringBoot.dto.CombineTable;
import com.imran.SpringBoot.dto.Products;
import com.imran.SpringBoot.service.ProductServiceImpl;

@RestController
public class PageController
{
	@Autowired
	private ProductServiceImpl productService;
	
	@Autowired
	private CategoriesRepository categoriesRepository;
	
	@PutMapping(path = "/update/{id}")
	public List<Products> updateProduct(@PathVariable(value = "id") int id, Products product)
	{
		return productService .updateProduct(id, product);
	}
	
	@GetMapping("/findCombineTable")
	public List<CombineTable> findCombineTable()
	{
		List<CombineTable> combineTabe = categoriesRepository.getCombineTable();
		for (CombineTable combineTable : combineTabe)
		{
			System.out.println(combineTable.getCategoryName() + " " + combineTable.getProductName() + " " + combineTable.getPrice());
		}
		return categoriesRepository.getCombineTable();
	}
}
