package com.smile.android.gsm.crypto;

import java.io.IOException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;

import javax.crypto.Cipher;

import com.lib.Debug;
import com.lib.csharp.security.ReadWriteFile;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Base64;

public class RSA {
	
	public static final String ALGORITHM = "RSA";
	public static final String BEGIN_RSA_PRIVATE_KEY = "-----BEGIN RSA PRIVATE KEY-----";
	public static final String END_RSA_PRIVATE_KEY = "-----END RSA PRIVATE KEY-----";
	public static final String BEGIN_RSA_PUBLIC_KEY = "-----BEGIN PUBLIC KEY-----";
	public static final String END_RSA_PUBLIC_KEY = "-----END PUBLIC KEY-----";

	public static final String PUBLICKEYVT = "PublicKeyVT.pem";
	public static final String PRIVATEKEYVT = "PrivateKeyVT.pem";
	public static final String PRIVATEKEYCP = "PrivateKeyCP.pem";
	public static final String PUBLICKEYCP = "PublicKeyCP.pem";

	public String private_key_cp = "";
	public String private_key_vt = "";
	public String public_key_cp = "";
	public String public_key_vt = "";
	private static final String characterEncoding = "UTF-8";

	public RSA(Context context) {
		try {
			ReadWriteFile rwfile = new ReadWriteFile();
			public_key_vt = rwfile.readFileStringAssest(context.getResources().getAssets().open(PUBLICKEYVT), new String[] { BEGIN_RSA_PUBLIC_KEY, END_RSA_PUBLIC_KEY });
			private_key_vt = rwfile.readFileStringAssest(context.getResources().getAssets().open(PRIVATEKEYVT), new String[] { BEGIN_RSA_PRIVATE_KEY, END_RSA_PRIVATE_KEY });
			private_key_cp = rwfile.readFileStringAssest(context.getResources().getAssets().open(PRIVATEKEYCP), new String[] { BEGIN_RSA_PRIVATE_KEY, END_RSA_PRIVATE_KEY });
			public_key_cp = rwfile.readFileStringAssest(context.getResources().getAssets().open(PUBLICKEYCP), new String[] { BEGIN_RSA_PUBLIC_KEY, END_RSA_PUBLIC_KEY });
		} catch (IOException e) {
			Debug.e("Lỗi: " + e.toString());
		}
	}

	/**
	 * Mã hóa
	 * 
	 * @param data
	 * @param pubKey
	 * @return
	 * @throws Exception
	 */
	@SuppressLint("TrulyRandom")
	public byte[] encrypt(String plainText, PublicKey pubKey) throws Exception {
		byte[] data = plainText.getBytes(characterEncoding);
		Cipher ciph = Cipher.getInstance("RSA/ECB/NoPadding");
		ciph.init(Cipher.ENCRYPT_MODE, pubKey);
		return ciph.doFinal(data);
	}

	/**
	 * Giải mã
	 * 
	 * @param data
	 * @param privKey
	 * @return
	 * @throws Exception
	 */
	public byte[] decrypt(String encryptedText, PrivateKey privKey) throws Exception {
		byte[] data = Base64.decode(encryptedText, Base64.DEFAULT);
		Cipher ciph = Cipher.getInstance("RSA/ECB/NoPadding");
		ciph.init(Cipher.DECRYPT_MODE, privKey);
		return ciph.doFinal(data);
	}

	/**
	 * Tạo khóa xác thực
	 * 
	 * @param data
	 * @param privKey
	 * @return
	 * @throws Exception
	 */
	public byte[] sign(byte[] data, PrivateKey privKey) throws Exception {
		Signature sig = Signature.getInstance("SHA1withRSA");
		sig.initSign(privKey);
		sig.update(data);
		byte[] signature = sig.sign();
		return signature;
	}

	/**
	 * Kiểm tra khóa xác thực
	 * 
	 * @param data
	 * @param signature
	 * @param pubKey
	 * @return
	 * @throws Exception
	 */
	public Boolean verifySignature(byte[] data, byte[] signature, PublicKey pubKey) throws Exception {
		Signature sig = Signature.getInstance("SHA1withRSA");
		sig.initVerify(pubKey);
		sig.update(data);
		return sig.verify(signature);
	}

}
