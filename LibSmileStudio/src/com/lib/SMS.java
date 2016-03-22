package com.lib;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class SMS {
	/**
	 * Send sms
	 * 
	 * @param string
	 */
	public static void onClickSendSMS(Context context, String phoneNumber, String string) {
		Uri uri = Uri.parse("smsto:" + phoneNumber);
		Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
		intent.putExtra("sms_body", string);
		context.startActivity(intent);
	}
}
