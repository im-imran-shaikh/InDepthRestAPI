package com.imran.prac.dto;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Component
public class Categories
{
	@Id
	@GeneratedValue
	private int id;
	
	@Size(min = 2, max = 20)
	private String name;
	
	@NotBlank
	private String description;
	
	@OneToMany(mappedBy = "categories")
	private List<Mobile> mobiles;
}
