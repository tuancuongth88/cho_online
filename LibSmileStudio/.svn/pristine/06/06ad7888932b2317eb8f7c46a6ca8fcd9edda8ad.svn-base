package com.lib.view;

import com.lib.R;

import android.content.Context;
import android.widget.TextView;

public class DialogAboutApplication extends BaseDialog {

	private TextView textView = null;

	public DialogAboutApplication(Context context) {
		super(context);
		setTitle(context.getString(R.string.about));
		init();
	}

	@Override
	public void init() {
		setContentView(R.layout.dialog_about_application);
		textView = (TextView) findViewById(
				context.getResources().getIdentifier("tv_message", "id", context.getPackageName()));
	}

	public void setMessage(String message) {
		textView.setText(message);
	}

	public void setMessage(int resid) {
		textView.setText(resid);
	}

}
