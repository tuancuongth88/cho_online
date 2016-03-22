package com.onlinemarketing.activity;

import java.util.ArrayList;
import java.util.List;

import com.example.onlinemarketing.R;
import com.lib.Debug;
import com.onlinemarketing.config.Constan;
import com.onlinemarketing.config.SystemConfig;
import com.onlinemarketing.fragment.FragmentCategory;
import com.onlinemarketing.json.JsonSearch;
import com.onlinemarketing.json.JsonSetting;
import com.onlinemarketing.object.CategoryVO;
import com.onlinemarketing.object.Output;
import com.onlinemarketing.object.OutputProduct;
import com.onlinemarketing.object.ProductVO;
import com.onlinemarketing.object.SettingVO;

import android.app.ActionBar;
import android.app.ActionBar.OnNavigationListener;
import android.app.Dialog;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

	private Toolbar mToolbar;

	/**
	 * Fragment managing the behaviors, interactions and presentation of the
	 * navigation drawer.
	 */
	ArrayList<SettingVO> listSetting = new ArrayList<SettingVO>();
	public static OutputProduct oOput;
	/**
	 * Used to store the last screen title. For use in
	 * {@link #restoreActionBar()}.
	 */
	private CharSequence mTitle;
	public static int id_category, id_category_search;
	public static int status = SystemConfig.statusHomeProduct;
	String[] action = { "a", "b", "c" };
	ArrayAdapter<String> objSettingAdapter;
	TextView txtlistSaveSearch;
	Button btn_search, btn_addSearch, txt_saveSearch;
	Dialog dialog;
	EditText edit_namSPSearch;
	static Output out;
	Spinner sinpnerPriceSearch, sinpnerCategorySearch, spinnerDatetimeSearch;
	RadioButton rdbSearchOld, rdbSearchNew;
	private NavigationDrawerFragment mNavigationDrawerFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mToolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(mToolbar);
		getSupportActionBar().setDisplayShowHomeEnabled(true);
		getSupportFragmentManager().beginTransaction().replace(R.id.container_body, new FragmentCategory()).commit();

		
	}

	
}
