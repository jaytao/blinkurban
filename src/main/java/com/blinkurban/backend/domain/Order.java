package com.blinkurban.backend.domain;

import com.google.api.server.spi.config.AnnotationBoolean;
import com.google.api.server.spi.config.ApiResourceProperty;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class Order{
	@Id private Long id;
	
	@ApiResourceProperty(ignored = AnnotationBoolean.TRUE)
	private Key<User> userID;
	
	public Order(){}
	
}