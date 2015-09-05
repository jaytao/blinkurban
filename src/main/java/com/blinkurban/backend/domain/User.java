package com.blinkurban.backend.domain;

import com.blinkurban.backend.form.UserForm.Gender;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class User {
	@Id String email;
	String password;
	String firstName;
	String lastName;
	Gender gender;
	
	public User(String email, String password, String firstName, String lastName, Gender gender){
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
	}
	
	private User(){}
	
	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
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
