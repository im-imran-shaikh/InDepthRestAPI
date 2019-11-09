package com.imran.SpringBoot.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity 
@Data
public class Smartphone
{
	@Id
	@GeneratedValue
	private int id;
	
	private String model;
	
	private String spec;
	
	private String price;
}
