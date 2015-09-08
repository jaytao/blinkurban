package com.blinkurban.backend.domain;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class Model{
	@Id private Long id;
	private String name;
	private int height;
	private int weight;
	private String description;
	
	public Model(){}
	
	public Model(String name, String description, int height, int weight){
		this.name = name;
		this.description = description;
		this.height = height;
		this.weight = weight;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getHeight() {
		return height;
	}

	public int getWeight() {
		return weight;
	}

	public String getDescription() {
		return description;
	}
	
	
}