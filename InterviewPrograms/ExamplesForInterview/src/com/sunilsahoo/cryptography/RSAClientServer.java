package com.sunilsahoo.cryptography;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

public class RSAClientServer {
	public static void main(String args[]) {

		int keySize = 512;
		SecureRandom random = new SecureRandom();
		// Choose two distinct prime numbers p and q.
		BigInteger p = BigInteger.probablePrime(keySize / 2, random);
		BigInteger q = BigInteger.probablePrime(keySize / 2, random);
		// Compute n = pq (modulus)
		BigInteger modulus = p.multiply(q);
		// Compute φ(n) = φ(p)φ(q) = (p − 1)(q − 1) = n - (p + q -1), where φ is
		// Euler's totient function.
		// and choose an integer e such that 1 < e < φ(n) and gcd(e, φ(n)) = 1;
		// i.e., e and φ(n) are coprime.
		BigInteger m = (p.subtract(BigInteger.ONE))
				.multiply(q.subtract(BigInteger.ONE));
		BigInteger publicExponent = getCoprime(m, random);
		// Determine d as d ≡ e−1 (mod φ(n)); i.e., d is the multiplicative
		// inverse of e (modulo φ(n)).
		BigInteger privateExponent = publicExponent.modInverse(m);

		try {
			RSAPublicKeySpec spec = new RSAPublicKeySpec(modulus,
					publicExponent);
			RSAPrivateKeySpec privateSpec = new RSAPrivateKeySpec(modulus,
					privateExponent);

			KeyFactory factory = KeyFactory.getInstance("RSA");

			PublicKey pub = factory.generatePublic(spec);
			PrivateKey priv = factory.generatePrivate(privateSpec);

			System.out.println(
					"Public Key : " + byteArrayToHexString(pub.getEncoded()));
			System.out.println(
					"Private Key : " + byteArrayToHexString(priv.getEncoded()));
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	public static BigInteger getCoprime(BigInteger m, SecureRandom random) {
		int length = m.bitLength() - 1;
		BigInteger e = BigInteger.probablePrime(length, random);
		while (!(m.gcd(e)).equals(BigInteger.ONE)) {
			e = BigInteger.probablePrime(length, random);
		}
		return e;
	}

	public static String byteArrayToHexString(byte[] bytes) {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < bytes.length; i++) {
			if ((bytes[i] & 0xff) < 0x10)
				buffer.append("0");
			buffer.append(Long.toString(bytes[i] & 0xff, 16));
		}
		return buffer.toString();
	}

	/*
	 * // Hardcode the RSA key String modulusString =
	 * "00d56047acf652298e3fcdbb8cecbc32214722aa1625f88480cf570cee373ada932b140c29b00dc44f6e59e7018dddca66b2f1c645dacb9d4a45459cfa8f7e33df";
	 * String exponentString =
	 * "18bc01730656bde47476f7cfbd3d8f9e15ede9c389814672dc161e349b08627fc885fe9d2442ae92f0214c7e97cf0b9a9fc876df4f53517ab63d710f997b2779";
	 * String publicExponentString = "65537";
	 * 
	 * // Load the key into BigIntegers BigInteger modulus = new
	 * BigInteger(modulusString, 16); BigInteger exponent = new
	 * BigInteger(exponentString, 16); BigInteger pubExponent = new
	 * BigInteger(publicExponentString);
	 * 
	 * // Create private and public key specs RSAPrivateKeySpec privateSpec =
	 * new RSAPrivateKeySpec(modulus, exponent); RSAPublicKeySpec publicSpec =
	 * new RSAPublicKeySpec(modulus, pubExponent);
	 * 
	 * // Create a key factory KeyFactory factory =
	 * KeyFactory.getInstance("RSA");
	 * 
	 * // Create the RSA private and public keys PrivateKey priv =
	 * factory.generatePrivate(privateSpec); PublicKey pub =
	 * factory.generatePublic(publicSpec);
	 */
}
