package com.blinkurban.backend.spi;

import java.util.List;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.response.BadRequestException;
import com.google.api.server.spi.response.UnauthorizedException;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.blinkurban.backend.Constants;
import com.blinkurban.backend.domain.Debug;
import com.blinkurban.backend.domain.Item;
import com.blinkurban.backend.domain.ItemMetric;
import com.blinkurban.backend.domain.Model;
import com.blinkurban.backend.domain.Order;
import com.blinkurban.backend.domain.User;
import com.blinkurban.backend.form.ItemForm;
import com.blinkurban.backend.form.ItemIDForm;
import com.blinkurban.backend.form.ItemMetricForm;
import com.blinkurban.backend.form.ItemSearchForm;
import com.blinkurban.backend.form.ModelForm;
import com.blinkurban.backend.form.OrderForm;
import com.blinkurban.backend.form.UserForm;
import com.blinkurban.backend.form.UserLoginForm;

/**
 * Defines v1 of a helloworld API, which provides simple "greeting" methods.
 */
@Api(name = "blinkurban", version = "v1", scopes = { Constants.EMAIL_SCOPE }, clientIds = { Constants.WEB_CLIENT_ID,
		Constants.API_EXPLORER_CLIENT_ID }, description = "API for the Blink Urban Backend application.")
public class BlinkUrbanAPI {

	@ApiMethod(name = "createUser", path = "user", httpMethod = HttpMethod.POST)
	public User createUser(UserForm userForm) throws UnauthorizedException, BadRequestException {
		return Register.register(userForm);
	}

	@ApiMethod(name = "login", path = "login", httpMethod = HttpMethod.POST)
	public User login(UserLoginForm loginForm) throws BadRequestException, UnauthorizedException {
		return Login.login(loginForm);
	}

	@ApiMethod(name = "createItem", path = "newItem", httpMethod = HttpMethod.POST)
	public Item createItem(ItemForm itemForm) {
		return Items.createItem(itemForm);
	}

	@ApiMethod(name = "getItem", path = "item", httpMethod = HttpMethod.POST)
	public Item getItem(ItemIDForm idForm) {
		return Items.getItem(idForm);
	}

	@ApiMethod(name = "createModel", path = "newModel", httpMethod = HttpMethod.POST)
	public Model createModel(ModelForm modelForm){
		return Models.createModel(modelForm);
	}
	
	@ApiMethod(name = "createOrder", path = "newOrder", httpMethod = HttpMethod.POST)
	public Order createOrder(OrderForm orderForm) throws BadRequestException{
		return Orders.createOrder(orderForm);
	}
	
	@ApiMethod(name = "addItem", path = "addItems", httpMethod = HttpMethod.POST)
	public ItemMetric addItem(ItemMetricForm metricForm){
		return Items.addItems(metricForm);
	}
	
	@ApiMethod(name = "searchItem", path = "searchItem", httpMethod = HttpMethod.POST)
	public List<Item> searchItem(ItemSearchForm searchForm){
		return Items.searchItem(searchForm.getSearchField());
	}
/* 
 #################### Test Calls #####################
*/
	@ApiMethod(name = "test", path = "queryMale", httpMethod = HttpMethod.GET)
	public List<User> getmale() {
		return Testing.getMale();
	}
	@ApiMethod(name = "uploadImageURL", path = "uploadImageURL", httpMethod = HttpMethod.GET)
	public Debug uploadImageURL() {
		String blobUploadUrl = BlobstoreServiceFactory.getBlobstoreService().createUploadUrl("/item/upload");
		Debug d = new Debug(blobUploadUrl);
		return d;
	}
	
}
