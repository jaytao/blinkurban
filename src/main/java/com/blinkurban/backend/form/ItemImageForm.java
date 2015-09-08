package com.blinkurban.backend.form;

public class ItemImageForm{
	private byte[] image;
	private Long itemId;
	private Long modelId;
	
	public ItemImageForm(){}
	
	public byte[] getImage(){
		return image;
	}
	
	public Long getModelId(){
		return modelId;
	}
	
	public Long getItemId(){
		return itemId;
	}
}