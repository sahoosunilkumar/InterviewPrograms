package com.sunilsahoo.cryptography;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

public class AESExample {
	public static void main(String[] args) throws Exception {
		byte[] plainText = "Sunil Kumar Sahoo".getBytes();
		//
		// get a AES private key
		System.out.println("\nStart generating AES key");
		KeyGenerator keyGen = KeyGenerator.getInstance("AES");
		keyGen.init(128);
		Key key = keyGen.generateKey();
		System.out.println("Finish generating AES key");
		//
		// get a AES cipher object and print the provider
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		System.out.println("\n" + cipher.getProvider().getInfo());
		//
		// encrypt using the key and the plaintext
		System.out.println("\nStart encryption");
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] cipherText = cipher.doFinal(plainText);
		System.out.println("Finish encryption: ");
		System.out.println(new String(cipherText, "UTF8"));
		//
		// decrypt the ciphertext using the same key
		System.out.println("\nStart decryption");
		cipher.init(Cipher.DECRYPT_MODE, key);
		byte[] newPlainText = cipher.doFinal(cipherText);
		System.out.println("Finish decryption: ");
		System.out.println(new String(newPlainText, "UTF8"));
	}
}
