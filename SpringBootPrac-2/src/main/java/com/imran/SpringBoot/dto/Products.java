package com.imran.SpringBoot.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;

@Data
@Entity
@Table(name = "Product")
public class Products
{
	@Id
	@GeneratedValue
	private int id;
	
	@Column(name = "Model")
	private String name;
	
	@Column(name = "Specification")
	private String spec;
	
	private int price;
	
	@Transient
	private String soldTo;
	
	@ManyToOne
	private Categories categories;
}
