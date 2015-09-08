package com.blinkurban.backend.domain;

import com.google.api.server.spi.config.AnnotationBoolean;
import com.google.api.server.spi.config.ApiResourceProperty;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

import static com.blinkurban.backend.service.OfyService.ofy;

@Entity
public class ItemMetric{
	@Id private Long id;
	private long count;
	
	@ApiResourceProperty(ignored = AnnotationBoolean.TRUE)
	@Index
	Key<Item> itemID;
	
	@ApiResourceProperty
	@Index
	Size size;
	
	@ApiResourceProperty
	@Index
	Color color;
	
	public ItemMetric(){}
	
	public Color getColor(){
		return color;
	}
	public Size getSize(){
		return size;
	}
	public long getCount(){
		return count;
	}
}