package com.imran.SpringBoot.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.imran.SpringBoot.dto.Categories;
import com.imran.SpringBoot.dto.CombineTable;

@RepositoryRestResource(collectionResourceRel = "categories", path = "categories")
public interface CategoriesRepository extends JpaRepository<Categories, Integer>
{
	@Query("SELECT new com.imran.SpringBoot.dto.CombineTable(c.name, p.name, p.price) "
			+ "FROM Categories c inner join Products p on c.id = p.id ")
	public List<CombineTable> getCombineTable();
}
