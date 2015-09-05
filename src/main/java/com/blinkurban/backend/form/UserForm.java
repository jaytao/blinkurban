package com.blinkurban.backend.form;

public class UserForm {

	private String email;
	private String password;
	private String confirmPassword;
	private String firstName;
	private String lastName;
	private Gender gender;
	
	private UserForm(){}
	
	public UserForm(String email, String password, String confirmPassword, String firstName, String lastName, Gender gender){
		this.email = email;
		this.password = password;
		this.confirmPassword = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
	}
	
	public String getConfirmPassword() {
		return confirmPassword;
	}

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

	public static enum Gender {
    	Male,
        Female
    }
}
