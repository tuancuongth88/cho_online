package com.lib;

import android.content.Context;

public class Clipbroad {

	/**
	 * Copy to ClipBoard
	 * 
	 * @param position
	 */
	public static void onClickCopy(Context context, String string) {
		int sdk_Version = android.os.Build.VERSION.SDK_INT;
		if (sdk_Version < android.os.Build.VERSION_CODES.HONEYCOMB) {
			android.text.ClipboardManager clipboard = (android.text.ClipboardManager) context
					.getSystemService(Context.CLIPBOARD_SERVICE);
			clipboard.setText(string);
			Debug.showAlert(context, "Copied to Clipboard!");
		} else {
			android.content.ClipboardManager clipboard = (android.content.ClipboardManager) context
					.getSystemService(Context.CLIPBOARD_SERVICE);
			android.content.ClipData clip = android.content.ClipData.newPlainText("Text Label", string);
			clipboard.setPrimaryClip(clip);
			Debug.showAlert(context, "Copied to Clipboard!");
		}
	}

}
