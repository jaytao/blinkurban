package com.blinkurban.backend.form;

import com.blinkurban.backend.domain.Color;
import com.blinkurban.backend.domain.Size;

public class ItemMetricForm{
	private long count;
	private Size size;
	private Color color;
	private long itemId;
	
	public ItemMetricForm(){}
	
	public long getCount() {
		return count;
	}
	public Size getSize() {
		return size;
	}
	public Color getColor() {
		return color;
	}
	public long getItemId() {
		return itemId;
	}
	
	
}