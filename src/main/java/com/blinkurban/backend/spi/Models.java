package com.blinkurban.backend.spi;
import com.blinkurban.backend.domain.Model;
import com.blinkurban.backend.form.ModelForm;
import com.googlecode.objectify.Key;

import static com.blinkurban.backend.service.OfyService.ofy;
import static com.blinkurban.backend.service.OfyService.factory;

public class Models{
	public static Model createModel(ModelForm modelForm){
		Key<Model> key = factory().allocateId(Model.class);
		Model m = new Model(key.getId(), modelForm.getName(),modelForm.getDescription(), modelForm.getHeight(), modelForm.getWeight());
		ofy().save().entity(m).now();
		return m;
	}
}