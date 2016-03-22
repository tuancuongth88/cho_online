package com.lib;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

public class BaseActivity extends Activity {

	public static Activity instance = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		instance = this;
	}

	public void startNextAcitivty(Class<?> activity) {
		startActivity(new Intent(this, activity));
		System.gc();
		finish();
	}

	@SuppressLint("InlinedApi")
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);

		if (hasFocus) {
			View decorView = this.getWindow().getDecorView();
			if (Build.VERSION.SDK_INT < 19) {
				decorView.setSystemUiVisibility(View.GONE);
			} else {
				int uiOptions = View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
						| View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
						| View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE;
				decorView.setSystemUiVisibility(uiOptions);
			}
		}
	}

}
