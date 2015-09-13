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
	
	@Index
	private String name;
	
	private String description;
	
	@Index
	private double price;
	
	@Index
	private Gender gender;

	private Item(){}
	public Item(final long id, String name, String description, String gender, double price){
		this.id = id;
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
