package com.blinkurban.backend.service;

import com.blinkurban.backend.domain.Gender;
import com.blinkurban.backend.domain.Item;
import com.blinkurban.backend.domain.Order;
import com.blinkurban.backend.domain.OrderItems;
import com.blinkurban.backend.domain.User;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;

/**
 * Custom Objectify Service that this application should use.
 */
public class OfyService {
	/**
     * This static block ensure the entity registration.
     */
    static {
        factory().register(User.class);
        factory().register(Gender.class);
        factory().register(Item.class);
        factory().register(Order.class);
        factory().register(OrderItems.class);
    }

    /**
     * Use this static method for getting the Objectify service object in order to make sure the
     * above static block is executed before using Objectify.
     * @return Objectify service object.
     */
    public static Objectify ofy() {
        return ObjectifyService.ofy();
    }

    /**
     * Use this static method for getting the Objectify service factory.
     * @return ObjectifyFactory.
     */
    public static ObjectifyFactory factory() {
        return ObjectifyService.factory();
    }
}
