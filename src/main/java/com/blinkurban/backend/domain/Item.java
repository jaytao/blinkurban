package com.blinkurban.backend.domain;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class Item {
	@Id private long id;
	
	private Item(){}
}
