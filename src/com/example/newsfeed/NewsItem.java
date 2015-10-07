package com.example.newsfeed;

import android.media.Image;

public class NewsItem {
	
	private String text;
	private String desc;
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getDesc() {
		return desc;
	}
	public NewsItem(String text, String desc) {
		super();
		this.text = text;
		this.desc = desc;
	}
	public void setImage(String desc) {
		this.desc = desc;
	}

}
