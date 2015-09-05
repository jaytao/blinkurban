package com.blinkurban.backend.spi;

import static com.blinkurban.backend.service.OfyService.ofy;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.response.BadRequestException;
import com.google.api.server.spi.response.UnauthorizedException;
import com.googlecode.objectify.Key;
import com.blinkurban.backend.Constants;
import com.blinkurban.backend.domain.User;
import com.blinkurban.backend.form.UserForm;
import com.blinkurban.backend.form.UserLoginForm;
import com.blinkurban.backend.utils.Crypto;
import com.blinkurban.backend.utils.Regex;

/**
 * Defines v1 of a helloworld API, which provides simple "greeting" methods.
 */
@Api(
    name = "blinkurban",
    version = "v1",
    scopes = {Constants.EMAIL_SCOPE},
    clientIds = {Constants.WEB_CLIENT_ID, Constants.API_EXPLORER_CLIENT_ID},
    description = "API for the Blink Urban Backend application."
)
public class BlinkUrbanAPI {

	@ApiMethod(name = "createUser", path = "user", httpMethod = HttpMethod.POST)
	public User createUser(UserForm userForm) throws UnauthorizedException, BadRequestException{
		
		//TODO check required fields
		//TODO add a transaction
		//TODO add authentication oauth or oauth2? 
		
		//Validations
		if (!Regex.email(userForm.getEmail())){
			// Save the entity in the datastore
			//throw new BadRequestException("gID = " + userForm.getGender());
			throw new BadRequestException("Invalid email address");
		}
		if (!userForm.getConfirmPassword().equals(userForm.getPassword())){
			throw new BadRequestException("Passwords do not match");
		}
		
		//check if the provided email address already exist
		User user = ofy().load().key(Key.create(User.class, userForm.getEmail())).now();
		if(user == null){
			//create a new user 
			//TODO hash and salt password
			
			user = new User(userForm.getEmail(), Crypto.SHA256(Constants.SALT+userForm.getPassword()), 
					userForm.getFirstName(), userForm.getLastName(), userForm.getGenderID(), Constants.SALT);
			
			ofy().save().entity(user).now();
		}else{
			throw new UnauthorizedException("User already registered with the provided email");
		}
		return user;
	}
	
	@ApiMethod(name = "login", path = "login", httpMethod = HttpMethod.POST)
	public User login(UserLoginForm loginForm) throws BadRequestException, UnauthorizedException{
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
