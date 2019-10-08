package com.imran.prac.dto;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Mobile
{
	@Id
	@GeneratedValue
	private int id;
	
	private String model;
	
	private String spec;
	
	private int price;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Categories categories;
}
