package com.example.onlinemarketing;

import java.util.ArrayList;
import java.util.List;

import com.smile.studio.menu.MenuCategory;

public class GlobalApp {

	public static List<MenuCategory> cates;

	public GlobalApp() {
		cates = new ArrayList<MenuCategory>();
		cates.add(new MenuCategory(R.string.app_name, R.drawable.ic_launcher));
		cates.add(new MenuCategory(R.string.app_name, R.drawable.ic_launcher));
		cates.add(new MenuCategory(R.string.app_name, R.drawable.ic_launcher));
		cates.add(new MenuCategory(R.string.app_name, R.drawable.ic_launcher));
		cates.add(new MenuCategory(R.string.app_name, R.drawable.ic_launcher));
		cates.add(new MenuCategory(R.string.app_name, R.drawable.ic_launcher));
	}

}
