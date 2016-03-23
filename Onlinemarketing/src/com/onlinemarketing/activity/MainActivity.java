package com.onlinemarketing.activity;

import java.util.ArrayList;

import com.example.onlinemarketing.R;
import com.onlinemarketing.config.SystemConfig;
import com.onlinemarketing.fragment.FragmentCategory;
import com.onlinemarketing.object.Output;
import com.onlinemarketing.object.OutputProduct;
import com.onlinemarketing.object.SettingVO;
import com.smile.studio.menu.FragmentDrawerLeft.FragmentDrawerListener;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity implements FragmentDrawerListener{

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
	public static int id_category, id_category_search;
	public static int status = SystemConfig.statusHomeProduct;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mToolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(mToolbar);
		getSupportActionBar().setDisplayShowHomeEnabled(true);		
		getSupportActionBar().setShowHideAnimationEnabled(true);
		getSupportFragmentManager().beginTransaction().replace(R.id.container_body, new FragmentCategory()).commit();
	}

	@Override
	public void onDrawerItemSelected(View view, int position) {
		
	}
	 


	
}
