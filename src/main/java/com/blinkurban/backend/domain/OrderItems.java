package com.blinkurban.backend.domain;

import com.google.api.server.spi.config.AnnotationBoolean;
import com.google.api.server.spi.config.ApiResourceProperty;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class OrderItems{
	@Id private Long id;
	private int quantity;
	
	@ApiResourceProperty(ignored = AnnotationBoolean.TRUE)
	private Key<Order> orderID;
	
	@ApiResourceProperty(ignored = AnnotationBoolean.TRUE)
	private Key<Item> itemID;
	
	public OrderItems(){}
}