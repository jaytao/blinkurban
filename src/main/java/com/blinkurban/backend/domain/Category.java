package com.blinkurban.backend.domain;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class Category{
	@Id private long id;
	private String category;
	
	public Category(){}
}