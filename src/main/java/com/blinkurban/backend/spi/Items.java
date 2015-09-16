package com.blinkurban.backend.spi;

import static com.blinkurban.backend.service.OfyService.ofy;
import static com.blinkurban.backend.service.OfyService.factory;

import java.util.ArrayList;
import java.util.List;
import com.blinkurban.backend.domain.Item;
import com.blinkurban.backend.domain.ItemMetric;
import com.blinkurban.backend.form.ItemForm;
import com.blinkurban.backend.form.ItemIDForm;
import com.blinkurban.backend.form.ItemMetricForm;
import com.google.appengine.api.search.Document;
import com.google.appengine.api.search.Field;
import com.google.appengine.api.search.IndexSpec;
import com.google.appengine.api.search.MatchScorer;
import com.google.appengine.api.search.Index;
import com.google.appengine.api.search.SearchServiceFactory;
import com.google.appengine.api.search.SortExpression;
import com.google.appengine.api.search.SortOptions;
import com.google.appengine.api.search.PutException;
import com.google.appengine.api.search.Query;
import com.google.appengine.api.search.QueryOptions;
import com.google.appengine.api.search.Results;
import com.google.appengine.api.search.ScoredDocument;
import com.google.appengine.api.search.StatusCode;
import com.googlecode.objectify.Key;

public class Items {
	public static Item createItem(ItemForm item) {
		Key<Item> key = factory().allocateId(Item.class);
		Item i = new Item(key.getId(), item.getName(), item.getDescription(), item.getGender(), item.getCategory(), item.getPrice());
		ofy().save().entity(i).now();
		
		//Create document from Item
		Document doc = Document.newBuilder()
				.addField(Field.newBuilder().setName("id").setText("" + key.getId()))
				.addField(Field.newBuilder().setName("name").setText(i.getName()))
				.addField(Field.newBuilder().setName("description").setText(i.getDescription()))
				.addField(Field.newBuilder().setName("category").setText(i.getCategory().toString()))
				.build();
	    
	    getIndex().put(doc);
	   
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
			Key<ItemMetric> key = factory().allocateId(ItemMetric.class);
			ItemMetric im = new ItemMetric(key.getId(), metricForm.getItemId(), metricForm.getSize(), metricForm.getColor(),
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
	
	public static List<Item> searchItem(String search){
		List<Long> idList = new ArrayList<Long>();
		
		//Search based on name
		String queryString = "name:" + search;
		Query query = Query.newBuilder().setOptions(getQueryOptions()).build(queryString);
	    Results<ScoredDocument> results = getIndex().search(query);
	    for (ScoredDocument document : results) {
	        idList.add(Long.parseLong(document.getOnlyField("id").getText()));
	    }
	    
	    if (idList.size() < 25){
	    	int searchLimit = 25 - idList.size();
	    	//Search based on description
		    queryString = "description:" + search;
		    query = Query.newBuilder().setOptions(getQueryOptions(searchLimit)).build(queryString);
		    results = getIndex().search(query);
		    for (ScoredDocument document : results) {
		    	Long resultId = Long.parseLong(document.getOnlyField("id").getText());
		    	if (!idList.contains(resultId)){
		    		searchLimit--;
		    		idList.add(resultId);
		    	}
		        if (searchLimit == 0){
		        	break;
		        }
		    }
	    }
	    
	    List<Item> itemList = new ArrayList<Item>();
	    for (Long l : idList){
	    	Key<Item> id = Key.create(Item.class, l);
	    	itemList.add(ofy().load().key(id).now());
	    }
	    return itemList;
	}
	
	private static Index getIndex(){
		IndexSpec indexSpec = IndexSpec.newBuilder().setName("itemindex").build(); 
	    return SearchServiceFactory.getSearchService().getIndex(indexSpec);
	   
	}
	private static QueryOptions getQueryOptions(int limit){
		 QueryOptions options = QueryOptions.newBuilder()
			        .setLimit(limit)
			        .build();
		 return options;
	}
	private static QueryOptions getQueryOptions(){
		return getQueryOptions(25);
	}
	
}