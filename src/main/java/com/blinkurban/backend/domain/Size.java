package com.blinkurban.backend.domain;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class Size{
	@Id private long id;
	private String size;
	
	public Size(){}
}