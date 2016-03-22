package com.lib;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Random;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.provider.Settings;
import android.util.TypedValue;

public class Utils {
	
	private static Random rand = new Random();

	public static int random(int from, int to) {
		if (from >= to)
			return from;
		else
			return from + (rand.nextInt(to - from));
	}

	public static int randomabs(int n) {
		return Math.abs(random(n));
	}

	public static int random(int n) {
		return rand.nextInt(n);
	}

	public static void changeWifiToMobileData(Context context, boolean flag) {
		onWifi(context, flag);
		onMobileData(context, !flag);
	}

	public static boolean isNetwork(Context context) {
		final ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		boolean val = false;
		Debug.e("Checking for WI-FI Network");
		final android.net.NetworkInfo wifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		if (wifi != null && wifi.isAvailable() && wifi.isConnected()) {
			Debug.e("Found WI-FI Network");
			val = true;
		} else {
			Debug.e("WI-FI Network not Found");
		}
		Debug.e("Checking for Mobile Internet Network");
		final android.net.NetworkInfo mobile = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		if (mobile != null && mobile.isAvailable() && mobile.isConnected()) {
			Debug.e("Found Mobile Internet Network");
			val = true;
		} else {
			Debug.e("Mobile Internet Network not Found");
		}
		return val;
	}

	public static void onWifi(Context context, boolean flag) {
		WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
		wifiManager.setWifiEnabled(flag);
	}

	public static void onMobileData(Context context, boolean enabled) {
		ConnectivityManager dataManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		Method dataMobile;
		try {
			dataMobile = ConnectivityManager.class.getDeclaredMethod("setMobileDataEnabled", boolean.class);
			dataMobile.setAccessible(enabled);
			dataMobile.invoke(dataManager, enabled);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	@Deprecated
	public static String AndriodID(Context context) {
		return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
	}
	
	public static String version(Context context) throws NameNotFoundException {
		PackageManager manager = context.getPackageManager();
		PackageInfo packageinfo;
		packageinfo = manager.getPackageInfo(context.getPackageName(), 0);
		return packageinfo.versionName;
	}

	public static void marketStore(Context context, String store) {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setData(Uri.parse("market://search?q=pub:" + store));
		context.startActivity(intent);
	}

	public static void marketApp(Context context, String packageName) {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setData(Uri.parse("market://details?id=" + packageName));
		context.startActivity(intent);
	}

	public static float dpToPx(Context context, float dp) {
		float result = 0;
		Resources r = context.getResources();
		result = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics());
		return result;
	}

	public static float pxToDp(Context context, float px) {
		float result = 0;
		Resources r = context.getResources();
		result = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, px, r.getDisplayMetrics());
		return result;
	}
}
