package com.blinkurban.backend.form;

import java.util.Date;
import java.util.List;

import com.blinkurban.backend.domain.OrderItem;

public class OrderForm{
	
	private String userId;
	private List<OrderItem> orderItems;
	
	public OrderForm(){}

	public String getUserId() {
		return userId;
	}
	
	public List<OrderItem> getOrderItems(){
		return orderItems;
	}
}