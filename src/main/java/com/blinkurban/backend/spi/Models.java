package com.blinkurban.backend.spi;
import com.blinkurban.backend.domain.Model;
import com.blinkurban.backend.form.ModelForm;
import static com.blinkurban.backend.service.OfyService.ofy;

public class Models{
	public static Model createModel(ModelForm modelForm){
		Model m = new Model(modelForm.getName(),modelForm.getDescription(), modelForm.getHeight(), modelForm.getWeight());
		ofy().save().entity(m).now();
		return m;
	}
}