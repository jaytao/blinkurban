package com.blinkurban.backend.domain;

import com.google.api.server.spi.config.AnnotationBoolean;
import com.google.api.server.spi.config.ApiResourceProperty;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class Picture{
	@Id private Long id;
	private byte[] file;
	private Key<Item> item;
	
	@ApiResourceProperty
	private Key<Model> modelID;
	
	public Picture(){}
}