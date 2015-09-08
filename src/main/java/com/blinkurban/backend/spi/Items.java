package com.blinkurban.backend.spi;

import static com.blinkurban.backend.service.OfyService.ofy;

import com.blinkurban.backend.domain.Item;
import com.blinkurban.backend.form.ItemForm;
import com.blinkurban.backend.form.ItemIDForm;
import com.googlecode.objectify.Key;

public class Items{
	public static Item createItem(ItemForm item){
		Item i = new Item(item.getName(), item.getDescription(), item.getGender(), item.getPrice());
		ofy().save().entity(i).now();
		return i;
	}
	public static Item getItem(ItemIDForm id){
		return ofy().load().key(Key.create(Item.class, id.getId())).now();
	}
}