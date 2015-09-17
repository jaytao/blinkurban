package com.blinkurban.backend.form;

import java.util.List;

import com.blinkurban.backend.domain.Category;

public class ItemSearchForm{
	String searchField;
	List<Category> categoryList;
	
	public String getSearchField(){
		return searchField;
	}
	public List<Category> getCategoryList(){
		return categoryList;
	}
}