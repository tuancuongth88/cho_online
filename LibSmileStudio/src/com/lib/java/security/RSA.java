package com.lib.java.security;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

import com.lib.Debug;

import android.annotation.SuppressLint;
import android.content.Context;

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
	 * Mã hóa RSA
	 * 
	 * @param source
	 * @param keypublic
	 * @return
	 * @throws Exception
	 */
	@SuppressLint("TrulyRandom")
	public static String encryptPublicKey(String source, String keypublic) throws Exception {
		KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
		PublicKey publickey = keyFactory.generatePublic(new X509EncodedKeySpec(Base64.decode(keypublic)));
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, publickey);
		byte[] b = source.getBytes();
		byte[] b1 = cipher.doFinal(b);
		return Base64.encode(b1);
	}

	/**
	 * Giải mã RSA
	 * 
	 * @param cryptograph
	 * @param keyprivate
	 * @return
	 * @throws Exception
	 */
	public static String decryptPrivateKey(String cryptograph, String keyprivate) throws Exception {
		KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM, "BC");
		PrivateKey privatekey = keyFactory.generatePrivate(new PKCS8EncodedKeySpec(Base64.decode(keyprivate)));
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, privatekey);
		byte[] b1 = Base64.decode(cryptograph);
		byte[] b = cipher.doFinal(b1);
		return new String(b);
	}

	public static String Signature(byte[] data, PrivateKey key) throws SignatureException, InvalidKeyException, NoSuchAlgorithmException {
		Signature signer = Signature.getInstance("SHA1WithRSAEncryption");
		signer.initSign(key);
		signer.sign(data, 0, data.length);
		return signer.sign().toString();
	}

	public static boolean verifySig(byte[] data, PublicKey key, byte[] sig) throws Exception {
		Signature signer = Signature.getInstance("SHA1WithRSAEncryption");
		signer.initVerify(key);
		signer.update(data);
		return (signer.verify(sig));
	}

	public static String GenerateSignature(String source, String keyprivate) throws Exception {
		KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM, "BC");
		PrivateKey publickey = keyFactory.generatePrivate(new PKCS8EncodedKeySpec(Base64.decode(keyprivate)));
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, publickey);
		byte[] b = Base64.decode(source);
		byte[] b1 = cipher.doFinal(b);
		return Base64.encode(b1);
	}

	public static String DeSignature(String source, String keypublic) throws Exception {
		KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
		PublicKey publickey = keyFactory.generatePublic(new X509EncodedKeySpec(Base64.decode(keypublic)));
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, publickey);
		byte[] b = Base64.decode(source);
		byte[] b1 = cipher.doFinal(b);
		return Base64.encode(b1);
	}

	public static String GenerateSignatureKey(String source, String keypublic, String keyprivate) throws Exception {
		KeyPair keyPair = null;
		KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM, "BC");
		PublicKey publicKey = keyFactory.generatePublic(new X509EncodedKeySpec(Base64.decode(keypublic)));
		PrivateKey privatekey = keyFactory.generatePrivate(new PKCS8EncodedKeySpec(Base64.decode(keyprivate)));
		keyPair = new KeyPair(publicKey, privatekey);
		Signature signature = Signature.getInstance("RSA");
		signature.initSign(keyPair.getPrivate());
		signature.update(source.getBytes());
		byte[] signatureBytes = signature.sign();
		signature.initVerify(keyPair.getPublic());
		signature.update(source.getBytes());
		System.out.println(signature.verify(signatureBytes));
		return Base64.encode(signatureBytes);
	}

}
