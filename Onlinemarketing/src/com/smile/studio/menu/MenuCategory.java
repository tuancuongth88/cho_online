package com.smile.studio.menu;

public class MenuCategory {

	private String title;
	private int imageRes;
	private int titleRes;

	public MenuCategory() {

	}
 
	public MenuCategory(int titleRes, int imageRes) {
		super();
		this.titleRes = titleRes;
		this.imageRes = imageRes;
	}

	public String getTitle() {
		return title;
	}

	public int getImageRes() {
		return imageRes;
	}

	public int getTitleRes() {
		return titleRes;
	}

}
