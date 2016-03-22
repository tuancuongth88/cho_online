package com.lib.java.security;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import com.lib.Debug;

import android.util.Base64;

public class AES128Cipher {

	public AES128Cipher() {
	}

	public static String AES_Decode(String str, String str2) {
		byte[] bArr = null;
		try {
			Key secretKeySpec = new SecretKeySpec(str2.getBytes(), "AES");
			Cipher instance = Cipher.getInstance("AES/ECB/PKCS5Padding");
			instance.init(2, secretKeySpec);
			bArr = instance.doFinal(Base64.decode(str, 0));
		} catch (Exception e) {
			Debug.e("Lỗi: " + e.toString());
		}
		return new String(bArr);
	}
}
