package com.blinkurban.backend.spi;

import static com.blinkurban.backend.service.OfyService.ofy;

import com.blinkurban.backend.domain.User;
import com.blinkurban.backend.form.UserLoginForm;
import com.blinkurban.backend.utils.Regex;
import com.google.api.server.spi.response.BadRequestException;
import com.google.api.server.spi.response.UnauthorizedException;
import com.googlecode.objectify.Key;

public class Login{
	
	public static User login(UserLoginForm loginForm) throws BadRequestException, UnauthorizedException{
		if (!Regex.email(loginForm.getEmail())){
			// Save the entity in the datastore
			//throw new BadRequestException("gID = " + userForm.getGender());
			throw new BadRequestException("Invalid email address");
		}
		User user = ofy().load().key(Key.create(User.class, loginForm.getEmail())).now();
		if (user == null){
			throw new UnauthorizedException("Email or Password incorrect.");
		}
		else if (!user.checkPassword(loginForm.getPassword())){
			throw new UnauthorizedException("Email or Password incorrect.");
		}
		else{
			return user;
		}
	}
}