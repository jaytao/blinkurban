package com.blinkurban.backend.form;

import com.blinkurban.backend.domain.Category;
import com.blinkurban.backend.domain.Gender;

public class ItemForm{
	private String name;
	private String description;
	private Gender gender;
	private Category category;
	
	private double price;
	
	public ItemForm(){}
	
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
	public Gender getGender() {
		return gender;
	}
	public double getPrice(){
		return price;
	}
	public Category getCategory(){
		return category;
	}
}