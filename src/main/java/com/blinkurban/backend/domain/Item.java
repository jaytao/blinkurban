package com.blinkurban.backend.domain;

import com.google.api.server.spi.config.AnnotationBoolean;
import com.google.api.server.spi.config.ApiResourceProperty;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

import static com.blinkurban.backend.service.OfyService.ofy;
@Entity
public class Item {
	@Id 
	private Long id;
	private String name;
	private String description;
	private double price;
	
	@ApiResourceProperty
	@Index
	Gender gender;

	private Item(){}
	public Item(String name, String description, String gender, double price){
		this.name = name;
		this.description = description;
		this.gender = Gender.valueOf(gender);
		this.price = price;
	}
	public String getName(){
		return name;
	}
	
	public String getDescription(){
		return description;
	}
	
	public Gender getGender(){
		return gender;
	}
	public double getPrice(){
		return price;
	}
}
