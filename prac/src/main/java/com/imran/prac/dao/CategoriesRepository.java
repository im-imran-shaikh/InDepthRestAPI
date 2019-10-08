package com.imran.prac.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.imran.prac.dto.Categories;

@RepositoryRestResource(collectionResourceRel = "categories", path = "categories")
public interface CategoriesRepository extends JpaRepository<Categories, Integer>
{
	Categories findByName(String name);
}
