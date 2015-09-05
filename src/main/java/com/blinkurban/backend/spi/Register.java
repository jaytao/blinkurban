package com.blinkurban.backend.spi;

import static com.blinkurban.backend.service.OfyService.ofy;

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
		// TODO check required fields
		// TODO add a transaction
		// TODO add authentication oauth or oauth2?

		// Validations
		if (!Regex.email(userForm.getEmail())) {
			// Save the entity in the datastore
			// throw new BadRequestException("gID = " + userForm.getGender());
			throw new BadRequestException("Invalid email address");
		}
		if (!userForm.getConfirmPassword().equals(userForm.getPassword())) {
			throw new BadRequestException("Passwords do not match");
		}

		// check if the provided email address already exist
		User user = ofy().load().key(Key.create(User.class, userForm.getEmail())).now();
		if (user == null) {
			// create a new user
			// TODO hash and salt password

			user = new User(userForm.getEmail(), Crypto.SHA256(Constants.SALT + userForm.getPassword()),
					userForm.getFirstName(), userForm.getLastName(), userForm.getGenderID(), Constants.SALT);

			ofy().save().entity(user).now();
		} else {
			throw new UnauthorizedException("User already registered with the provided email");
		}
		return user;
	}
}