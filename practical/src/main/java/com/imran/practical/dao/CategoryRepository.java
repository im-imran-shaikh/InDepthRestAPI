package com.imran.practical.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.imran.practical.dto.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>
{

}
