package com.blinkurban.backend.spi;

import static com.blinkurban.backend.service.OfyService.ofy;

import java.util.List;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.response.BadRequestException;
import com.google.api.server.spi.response.UnauthorizedException;
import com.googlecode.objectify.Key;
import com.blinkurban.backend.Constants;
import com.blinkurban.backend.domain.Item;
import com.blinkurban.backend.domain.User;
import com.blinkurban.backend.form.ItemForm;
import com.blinkurban.backend.form.ItemIDForm;
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
		return Register.register(userForm);
	}
	
	@ApiMethod(name = "login", path = "login", httpMethod = HttpMethod.POST)
	public User login(UserLoginForm loginForm) throws BadRequestException, UnauthorizedException{
		return Login.login(loginForm);
	}
	
	@ApiMethod(name = "createItem", path = "newItem", httpMethod = HttpMethod.POST)
	public Item createItem(ItemForm itemForm){
		return Items.createItem(itemForm);
	}
	
	@ApiMethod(name = "getItem", path = "item", httpMethod = HttpMethod.POST)
	public Item getItem(ItemIDForm idForm){
		return Items.getItem(idForm);
	}
	
	@ApiMethod(name = "test", path = "queryMale", httpMethod = HttpMethod.GET)
	public List<User> getmale(){
		return Testing.getMale();
	}
}
