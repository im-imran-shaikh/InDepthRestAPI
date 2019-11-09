package com.imran.SpringBoot.dto;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
public class CombineTable
{
	private String categoryName;
	private String productName;
	private int price;
	
	public CombineTable(String categoryName, String productName, int price)
	{
		this.categoryName = categoryName;
		this.productName = productName;
		this.price = price;
	}
	
	
}
