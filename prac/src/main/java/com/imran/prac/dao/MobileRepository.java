package com.imran.prac.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.imran.prac.dto.Mobile;

@RepositoryRestResource(collectionResourceRel = "mobile", path = "mobile")
public interface MobileRepository extends JpaRepository<Mobile, Integer>
{

}
