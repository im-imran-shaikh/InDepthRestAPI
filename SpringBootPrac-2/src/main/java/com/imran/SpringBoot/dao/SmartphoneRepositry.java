package com.imran.SpringBoot.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.imran.SpringBoot.dto.Smartphone;

@RepositoryRestResource(collectionResourceRel = "SmartPhone", path = "SmartPhone")
public interface SmartphoneRepositry extends JpaRepository<Smartphone, Integer>
{
	
}
