package com.imran.SpringBoot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imran.SpringBoot.dao.ProductRepository;
import com.imran.SpringBoot.dto.Products;

@Service
public class ProductServiceImpl implements ProductService
{
	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public List<Products> findAllProducts()
	{
		return productRepository.findAll();
	}

	@Override
	public List<Products> deleteProduct(int id)
	{
		productRepository.deleteById(id);
		return productRepository.findAll();
	}

	@Override
	public List<Products> updateProduct(int id, Products product)
	{
		productRepository.updateProducts(id, product);
		return productRepository.findAll();
	}

	@Override
	public List<Products> addProduct(Products product)
	{
		productRepository.save(product);
		return productRepository.findAll();
	}

}
