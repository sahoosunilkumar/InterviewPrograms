package com.sunilsahoo.cryptography;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class ClientServerAES {

	public static void main(String[] args) throws Exception {

		String password = "Sunil Kumar Sahoo";
		byte[] passwordEnc = AESencrp.encrypt(password);
		byte[] passwordDec = AESencrp.decrypt(passwordEnc);

		System.out.println("Plain Text : " + password);
		System.out.println("Encrypted Text : " + new String(passwordEnc));
		System.out.println("Decrypted Text : " + new String(passwordDec));
	}

}

class AESencrp {

	private static final String ALGO = "AES";
	private static final byte[] keyValue = new byte[] { 'T', 'h', 'e', 'B', 'e',
			's', 't', 'S', 'e', 'c', 'r', 'e', 't', 'K', 'e', 'y' };

	public static byte[] encrypt(String Data) throws Exception {
		Key key = generateKey();
		Cipher c = Cipher.getInstance(ALGO);
		c.init(Cipher.ENCRYPT_MODE, key);
		return c.doFinal(Data.getBytes());
	}

	public static byte[] decrypt(byte[] encryptedData) throws Exception {
		Key key = generateKey();
		Cipher c = Cipher.getInstance(ALGO);
		c.init(Cipher.DECRYPT_MODE, key);
		return c.doFinal(encryptedData);
	}

	private static Key generateKey() throws Exception {
		Key key = new SecretKeySpec(keyValue, ALGO);
		return key;
	}

}
