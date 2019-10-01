package com.imran.validation.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.imran.validation.dto.Laptop;

@RepositoryRestResource(collectionResourceRel = "laptop", path = "laptop")
public interface LaptopRepository extends JpaRepository<Laptop, Integer>
{
	
}
