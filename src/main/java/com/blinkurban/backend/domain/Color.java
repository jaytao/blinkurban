package com.blinkurban.backend.domain;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class Color{
	@Id private long id;
	private String color;
	
	public Color(){}
}