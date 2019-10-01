package com.imran.validation.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@Entity
@ApiModel(description = "All about laptop model")
public class Laptop
{
	@GeneratedValue
	@Id
	@ApiModelProperty(notes = "Id is autoincremented")
	private int id;
	
	@Size(min = 1, max = 10)
	@ApiModelProperty(notes = "brand name should contain at least one letter and atmost 10 letter")
	private String brand;
	
	@Size(max = 256)
	@NotNull
	@ApiModelProperty(notes = "spec should not be null and maximum character allowed for spec is 256")
	private String spec;
	
	@NotNull
	@Digits(fraction = 0, integer = 5)
	@ApiModelProperty(notes = "The Maximum digits of price is 5 and it should be not null")
	private int price;
}
