package com.blinkurban.backend.spi;

import static com.blinkurban.backend.service.OfyService.ofy;

import java.security.SecureRandom;

import com.blinkurban.backend.Constants;
import com.blinkurban.backend.domain.User;
import com.blinkurban.backend.form.UserForm;
import com.blinkurban.backend.utils.Crypto;
import com.blinkurban.backend.utils.Regex;
import com.google.api.server.spi.response.BadRequestException;
import com.google.api.server.spi.response.UnauthorizedException;
import com.googlecode.objectify.Key;

public class Register {
	public static User register(UserForm userForm) throws BadRequestException, UnauthorizedException {
		// Validations
		if (!Regex.email(userForm.getEmail())) {
			throw new BadRequestException("Invalid email address");
		}
		if (!userForm.getConfirmPassword().equals(userForm.getPassword())) {
			throw new BadRequestException("Passwords do not match");
		}
		if(userForm.getFirstName() == null || userForm.getFirstName().isEmpty()){
			throw new BadRequestException("First name is required");
		}
		if(userForm.getLastName() == null || userForm.getLastName().isEmpty()){
			throw new BadRequestException("Last name is required");
		}

		// check if the provided email address already exist
		User user = ofy().load().key(Key.create(User.class, userForm.getEmail().toLowerCase())).now();
		if (user == null) {
			// create a new user
			SecureRandom random = new SecureRandom();
			byte[] salt = random.generateSeed(32);
			//convert email to lowercase characters before storing data
			user = new User(userForm.getEmail().toLowerCase(), Crypto.SHA256(salt + userForm.getPassword()),
					userForm.getFirstName(), userForm.getLastName(), userForm.getGender(), salt);

			ofy().save().entity(user).now();
		} else {
			throw new UnauthorizedException("User already registered with the provided email");
		}
		return user;
	}
}