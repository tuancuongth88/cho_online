package com.smile.android.gsm.crypto;

import java.math.BigInteger;
import java.security.MessageDigest;

import com.lib.Debug;

public class MD5 {

	public MD5() {
	}

	public static String encrypt(String string) {
		try {
			MessageDigest localMessageDigest;
			(localMessageDigest = MessageDigest.getInstance("MD5")).update(string.getBytes(), 0, string.length());
			return new BigInteger(1, localMessageDigest.digest()).toString(16);
		} catch (Exception e) {
			Debug.e(e.toString());
		}
		return "";
	}

	public static String encrypt(String string, int nLoop) {
		if (nLoop <= 0)
			return encrypt(string);
		return encrypt(string, nLoop -= 1);
	}
}
