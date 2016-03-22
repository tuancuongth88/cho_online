package com.lib.java.security;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import com.lib.Debug;

public class AES256Cipher {
	public static byte[] ivBytes;

	static {
		AES256Cipher.ivBytes = new byte[16];
	}

	public static String AES_Decode(final String s, final String s2) {
		try {
			final byte[] decodeBase64 = Base64.decodeBase64(s.getBytes());
			final IvParameterSpec ivParameterSpec = new IvParameterSpec(AES256Cipher.ivBytes);
			final SecretKeySpec secretKeySpec = new SecretKeySpec(s2.getBytes("UTF-8"), "AES");
			final Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
			instance.init(2, secretKeySpec, ivParameterSpec);
			return new String(instance.doFinal(decodeBase64), "UTF-8");
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public static String AES_Encode(final String s, final String s2) {
		try {
			final byte[] bytes = s.getBytes("UTF-8");
			final IvParameterSpec ivParameterSpec = new IvParameterSpec(AES256Cipher.ivBytes);
			final SecretKeySpec secretKeySpec = new SecretKeySpec(s2.getBytes("UTF-8"), "AES");
			final Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
			instance.init(1, secretKeySpec, ivParameterSpec);
			return new String(Base64.encodeBase64(instance.doFinal(bytes)));
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public static void Test() {
		final String aes_Encode = AES_Encode(
				"imcore.netimcore.netimcore.netimcore.netimcore.netimcore.netimcore.netimcore.netimcore.netimcore.netimcore.netimcore.netimcore.netimcore.net",
				"4bcfefg&ijklmn%p");
		Debug.e("AES256_Encode : " + aes_Encode);
		Debug.e("AES256_Decode : " + AES_Decode(aes_Encode, "4bcfefg&ijklmn%p"));
	}
}