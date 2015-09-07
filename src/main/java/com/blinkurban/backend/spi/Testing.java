package com.blinkurban.backend.spi;

import static com.blinkurban.backend.service.OfyService.ofy;

import java.util.List;

import com.blinkurban.backend.Constants;
import com.blinkurban.backend.domain.Color;
import com.blinkurban.backend.domain.Gender;
import com.blinkurban.backend.domain.Size;
import com.blinkurban.backend.domain.User;
import com.blinkurban.backend.form.UserForm;
import com.blinkurban.backend.utils.Crypto;
import com.blinkurban.backend.utils.Regex;
import com.google.api.server.spi.response.BadRequestException;
import com.google.api.server.spi.response.UnauthorizedException;
import com.googlecode.objectify.Key;
public class Testing{
	public static void initDB(){
//		//init gender
//		Gender male = new Gender("Male");
//		Gender female = new Gender("Female");
//		ofy().save().entities(male, female).now();
//		
//		//init size
//		Size s = new Size("S");
//		Size m = new Size("M");
//		Size l = new Size("L");
//		ofy().save().entities(s,m,l).now();
//		
//		//color
//		Color red = new Color("Red");
//		Color blue = new Color("Blue");
//		Color black = new Color("Black");
//		ofy().save().entities(red, blue, black).now();
		
	}
	public static List<User> getMale(){
		List<User> userList = ofy().load().type(User.class).filter("gender", "Male").list();
		return userList;
	}
}