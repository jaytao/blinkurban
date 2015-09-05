package com.blinkurban.backend.spi;

import static com.blinkurban.backend.service.OfyService.ofy;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.response.UnauthorizedException;
import com.googlecode.objectify.Key;
import com.blinkurban.backend.Constants;
import com.blinkurban.backend.domain.User;
import com.blinkurban.backend.form.UserForm;

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
	public User createUser(UserForm userForm) throws UnauthorizedException{
		
		//TODO check required fields
		//TODO add a transaction
		//TODO add authentication oauth or oauth2? 
		
		//check if the provided email address already exist
		User user = ofy().load().key(Key.create(User.class, userForm.getEmail())).now();
		if(user == null){
			//create a new user 
			//TODO hash and salt password
			user = new User(userForm.getEmail(), userForm.getPassword(), 
					userForm.getFirstName(), userForm.getLastName(), userForm.getGender());
			// Save the entity in the datastore
			ofy().save().entity(user).now();
		}else{
			throw new UnauthorizedException("User already registered with the provided email");
		}
		return user;
	}
	
}
