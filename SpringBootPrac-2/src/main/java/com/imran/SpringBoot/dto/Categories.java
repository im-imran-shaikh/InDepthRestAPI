package com.imran.SpringBoot.dto;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "Category")
public class Categories
{
	@Id
	@GeneratedValue
	private int id;
	
	@Column(name = "Category_Name")
	private String name;
	
	@OneToMany(mappedBy = "categories")
	private List<Products> product;
}
