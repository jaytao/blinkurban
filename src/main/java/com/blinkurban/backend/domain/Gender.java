package com.blinkurban.backend.domain;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class Gender{
	@Id private long id;
	public long getId() {
		return id;
	}

	private String gender;

	private Gender(){}
	
	public String getGender() {
		return gender;
	}
}