package com.sunilsahoo.programs;

/**
 * One solution is to use the bitwise XOR operator.

If you know for a fact that exactly one element is duplicated.

XOR has the following interesting properties:

1. XOR is associative, so (x ^ y) ^ z = x ^ (y ^ z)
2. XOR is commutative: x ^ y = y ^ x
3. XOR is its own inverse: x ^ y = 0 iff x = y
4. XOR has zero as an identity: x ^ 0 = x

 * @author sunilkumarsahoo
 *
 */
public class XOROperatorExample {
public static void main(String[] args){
	int[] array = {0,1,2,3,4,5,6,7,8,4,9};
	getDuplicate(array);
	swap(12, 50);
	encryption(1000, 1059);
}
	private static void getDuplicate(int[] array){
		int dup = 0;
		int offset = 1;
		for(int i = 0; i < array.length; i++) {
			dup = dup ^ (array[i] + offset) ^ i;
		}
		System.out.println((dup - offset));
	
//		for (int i = 0; i < array.length; i++)
//		{
//		   System.out.println(" i "+array[i]);
//		}
	}
	
	private static void swap(int x, int y){
		System.out.println("Before swaping x : "+x+" y : "+y);
		x = x^y;
		y = x^y;
		x = x^y;
		System.out.println("After swaping x : "+x+" y : "+y);
	}
	
	private static void encryption(int data, int encryptionKey){
		System.out.println("Before encryption data : "+data+" encryptionKey : "+encryptionKey);
		data = data^encryptionKey;
		System.out.println("After encryption data : "+data+" encryptionKey : "+encryptionKey);
		data = data^encryptionKey;
		System.out.println("After Decryption data : "+data+" encryptionKey : "+encryptionKey);
		
	}
}


