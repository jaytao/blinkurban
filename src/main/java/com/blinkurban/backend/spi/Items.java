package com.blinkurban.backend.spi;

import static com.blinkurban.backend.service.OfyService.ofy;

import java.util.List;

import com.blinkurban.backend.domain.Item;
import com.blinkurban.backend.domain.ItemMetric;
import com.blinkurban.backend.form.ItemForm;
import com.blinkurban.backend.form.ItemIDForm;
import com.blinkurban.backend.form.ItemMetricForm;
import com.googlecode.objectify.Key;

public class Items {
	public static Item createItem(ItemForm item) {
		Item i = new Item(item.getName(), item.getDescription(), item.getGender(), item.getPrice());
		ofy().save().entity(i).now();
		return i;
	}

	public static Item getItem(ItemIDForm id) {
		return ofy().load().key(Key.create(Item.class, id.getId())).now();
	}

	public static ItemMetric addItems(ItemMetricForm metricForm) {
		// Check if ItemMetric already exists
		List<ItemMetric> list = ofy().load().type(ItemMetric.class).filter("color", metricForm.getColor())
				.filter("size", metricForm.getSize()).filter("itemId", Key.create(Item.class, metricForm.getItemId()))
				.list();
		
		//Create if not
		if (list.isEmpty()) {
			ItemMetric im = new ItemMetric(metricForm.getItemId(), metricForm.getSize(), metricForm.getColor(),
					metricForm.getCount());
			ofy().save().entity(im).now();
			return im;
		//Add to the count if already in datastore
		} else {
			ItemMetric im = list.get(0);
			im.setCount(im.getCount() + metricForm.getCount());
			ofy().save().entity(im).now();
			return im;
		}
	}
}