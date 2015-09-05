package com.blinkurban.backend.domain;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class Model{
	@Id private long id;
	private String name;
	private int height;
	private int weight;
	
	public Model(){}
}