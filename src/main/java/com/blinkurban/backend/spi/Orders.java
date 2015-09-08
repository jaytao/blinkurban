package com.blinkurban.backend.spi;

import com.blinkurban.backend.domain.Order;
import com.blinkurban.backend.domain.OrderItem;
import com.blinkurban.backend.form.OrderForm;

import static com.blinkurban.backend.service.OfyService.ofy;

public class Orders{
	public static Order createOrder(OrderForm orderForm){
		Order o = new Order(orderForm.getUserId(), orderForm.getOrderItems());
		ofy().save().entities(o.getOrderItems()).now();
		
		double totalPrice = 0.0;
		
		for (OrderItem i : o.getOrderItems()){
			totalPrice += (ofy().load().key(i.getItemIdKey()).now().getPrice() * i.getQuantity());
		}
		o.setPrice(totalPrice);
		ofy().save().entity(o).now();
		return o;
	}
}