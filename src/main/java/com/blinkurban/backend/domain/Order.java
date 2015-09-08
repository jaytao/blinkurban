package com.blinkurban.backend.domain;
import java.util.List;

import com.google.api.server.spi.config.AnnotationBoolean;
import com.google.api.server.spi.config.ApiResourceProperty;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class Order{
	@Id private Long id;
	
	@ApiResourceProperty(ignored = AnnotationBoolean.TRUE)
	private Key<User> userIdKey;
	private String userId;
	
	private List<OrderItem> orderItems;
	private double price;
	
	public Order(){}
	public Order(String userId, List<OrderItem> orderItems){
		this.userId = userId;
		this.userIdKey = Key.create(User.class, userId);
		this.orderItems = orderItems;
	}
	
	public Key<User> getUserIdKey(){
		return userIdKey;
	}
	
	public List<OrderItem> getOrderItems(){
		return orderItems;
	}
	
	public double getPrice(){
		return price;
	}
	
	public void setPrice(double price){
		this.price = price;
	}
	
}