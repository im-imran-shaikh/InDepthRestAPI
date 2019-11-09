package com.imran.SpringBoot.service;

import java.util.List;

import com.imran.SpringBoot.dto.Products;

public interface ProductService
{
	List<Products> findAllProducts();
	List<Products> deleteProduct(int id);
	List<Products> updateProduct(int id, Products product);
	List<Products> addProduct(Products product);
}
