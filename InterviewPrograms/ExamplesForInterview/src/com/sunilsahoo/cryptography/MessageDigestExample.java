package com.sunilsahoo.cryptography;

import java.security.MessageDigest;

//
// Generate a Message Digest
public class MessageDigestExample {
	public static void main(String[] args) throws Exception {
		byte[] plainText = "Sunil Kumar Sahoo".getBytes();
		//
		// get a message digest object using the MD5 algorithm
		MessageDigest messageDigest = MessageDigest.getInstance("MD5");
		//
		// print out the provider used
		System.out.println("\n" + messageDigest.getProvider().getInfo());
		//
		// calculate the digest and print it out
		messageDigest.update(plainText);
		System.out.println("\nDigest: ");
		System.out.println(new String(messageDigest.digest(), "UTF8"));
	}
}
