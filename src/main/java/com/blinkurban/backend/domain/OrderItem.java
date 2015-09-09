package com.blinkurban.backend.domain;

import com.google.api.server.spi.config.AnnotationBoolean;
import com.google.api.server.spi.config.ApiResourceProperty;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Ignore;

@Entity
public class OrderItem{
	@Id private Long id;
	private int quantity;
	
	@ApiResourceProperty(ignored = AnnotationBoolean.TRUE)
	private Key<Item> itemIdKey;
	private Long itemId;
	
	@Ignore
	private Size size;
	@Ignore
	private Color color;
	
	public OrderItem(){}
	
	public OrderItem(Long itemId, int quantity){
		this.itemId = itemId;
		this.itemIdKey = Key.create(Item.class, itemId);
		this.quantity = quantity;
	}
	
	public void setItemId(Long id){
		itemId = id;
		itemIdKey = Key.create(Item.class, id);
	}
	
	public void setQuantity(int quantity){
		this.quantity = quantity;
	}
	public int getQuantity(){
		return quantity;
	}
	
	public void setSize(String size){
		this.size = Size.valueOf(size);
	}
	public void setColor(String color){
		this.color = Color.valueOf(color);
	}
	
	@ApiResourceProperty(ignored = AnnotationBoolean.TRUE)
	public Key<Item> getItemIdKey(){
		return itemIdKey;
	}
	public Size getSize(){
		return size;
	}
	
	public Color getColor(){
		return color;
	}
}