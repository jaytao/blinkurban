package com.blinkurban.backend.spi;

import com.blinkurban.backend.domain.Item;
import com.blinkurban.backend.domain.ItemMetric;
import com.blinkurban.backend.domain.Order;
import com.blinkurban.backend.domain.OrderItem;
import com.blinkurban.backend.form.OrderForm;
import com.google.api.server.spi.response.BadRequestException;
import com.googlecode.objectify.Key;

import static com.blinkurban.backend.service.OfyService.ofy;
import static com.blinkurban.backend.service.OfyService.factory;

import java.util.ArrayList;
import java.util.List;

public class Orders {
	public static Order createOrder(OrderForm orderForm) throws BadRequestException {
		Key<Order> key = factory().allocateId(Order.class);
		Order o = new Order(key.getId(), orderForm.getUserId(), orderForm.getOrderItems());
		ofy().save().entities(o.getOrderItems()).now();

		double totalPrice = 0.0;
		List<ItemMetric> saveList = new ArrayList<ItemMetric>();
		for (OrderItem i : o.getOrderItems()) {
			totalPrice += (ofy().load().key(i.getItemIdKey()).now().getPrice() * i.getQuantity());
			List<ItemMetric> list = ofy().load().type(ItemMetric.class).filter("color", i.getColor())
					.filter("size", i.getSize()).filter("itemId", i.getItemIdKey()).list();
			if (list.isEmpty()){
				throw new BadRequestException("No items of this type in datastore");
			}
			ItemMetric im = list.get(0);
			if ((im.getCount() - i.getQuantity()) < 0){
				throw new BadRequestException("Not enough items in stock");
			}
			im.setCount(im.getCount() - i.getQuantity());
			saveList.add(im);
		}
		
		o.setPrice(totalPrice);
		ofy().save().entity(o).now();
		ofy().save().entities(saveList).now();
		// Update ItemMetrics by decrementing count

		return o;
	}
}