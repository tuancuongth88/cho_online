package com.lib.google;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import android.content.Context;
import android.util.Log;

public class TrackingAnalytics {

	private static Tracker tracker = null;

	public static void init(Context context, String Tracking_APP_ID) {
		GoogleAnalytics analytics = GoogleAnalytics.getInstance(context);
		analytics.setLocalDispatchPeriod(300);
		tracker = analytics.newTracker(Tracking_APP_ID);
		tracker.enableExceptionReporting(true);
		tracker.enableAdvertisingIdCollection(true);
		tracker.enableAutoActivityTracking(true);
	}

	public static void send(String category, String action, String label) {
		if (tracker != null) {
			tracker.send(
					new HitBuilders.EventBuilder().setCategory(category).setAction(action).setLabel(label).build());
		} else {
			Log.e(TrackingAnalytics.class.getSimpleName(), "Chưa khởi tạo init()");
		}
	}

	public static void send(String screenname, String category, String action, String label) {
		if (tracker != null) {
			tracker.setScreenName(screenname);
			tracker.send(
					new HitBuilders.EventBuilder().setCategory(category).setAction(action).setLabel(label).build());
		} else {
			Log.e(TrackingAnalytics.class.getSimpleName(), "Chưa khởi tạo init()");
		}
	}

}
