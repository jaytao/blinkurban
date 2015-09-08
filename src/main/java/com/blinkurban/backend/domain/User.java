package com.blinkurban.backend.domain;

import com.blinkurban.backend.utils.Crypto;
import com.google.api.server.spi.config.ApiResourceProperty;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
@Entity
public class User {
	@Id String email;
	private byte[] password;
	private String salt;
	private String firstName;
	private String lastName;
	
	@ApiResourceProperty
	@Index 
	private Gender gender;
	
	public User(String email, byte[] password, String firstName, String lastName, String gender, String salt){
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = Gender.valueOf(gender);
		this.salt = salt;
	}
	
	private User(){}
	 
	public String getEmail() {
		return email;
	}

	public boolean checkPassword(String input) {
		byte[] hash = Crypto.SHA256(salt+input);
		if (java.util.Arrays.equals(hash, password)){
			return true;
		}
		return false;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}
	
	public Gender getGender() {
		return gender;
	}
}
