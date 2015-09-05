package com.blinkurban.backend.form;

public class UserLoginForm {
	private String email;
	private String password;
	
	private UserLoginForm(){}
	
	public UserLoginForm(String email, String password){
		this.email = email;
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}
}
