package com.imran.SpringBoot.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import com.imran.SpringBoot.dto.Products;

@RepositoryRestResource(collectionResourceRel = "product",path = "product")
public interface ProductRepository extends JpaRepository<Products, Integer>
{
	@Modifying
	@Query("UPDATE Products SET price =?1,name = ?2, spec = ?3 WHERE id = ?4")
	@Transactional
	void updateProducts(int id ,Products product);
}
