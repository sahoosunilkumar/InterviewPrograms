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
	int[] array = {4,6,7,3,6,7};
	getNonDuplicate(array);
	getDuplicate(array);
	swap(12, 50);
	encryption(1000, 1059);
	String s = "Sunil";
	System.out.println(reverseWithXOR(s));
}

	/*
	 * Let x and y be the non-repeating elements we are looking for and arr[] be
	 * the input array. First calculate the XOR of all the array elements.
	 * 
	 * xor = arr[0]^arr[1]^arr[2].....arr[n-1] All the bits that are set in xor
	 * will be set in one non-repeating element (x or y) and not in other. So if
	 * we take any set bit of xor and divide the elements of the array in two
	 * sets – one set of elements with same bit set and other set with same bit
	 * not set. By doing so, we will get x in one set and y in another set. Now
	 * if we do XOR of all the elements in first set, we will get first
	 * non-repeating element, and by doing same in other set we will get the
	 * second non-repeating element.
	 * 
	 * Let us see an example. arr[] = {2, 4, 7, 9, 2, 4} 1) Get the XOR of all
	 * the elements. xor = 2^4^7^9^2^4 = 14 (1110) 2) Get a number which has
	 * only one set bit of the xor. Since we can easily get the rightmost set
	 * bit, let us use it. set_bit_no = xor & ~(xor-1) = (1110) & ~(1101) = 0010
	 * Now set_bit_no will have only set as rightmost set bit of xor. 3) Now
	 * divide the elements in two sets and do xor of elements in each set, and
	 * we get the non-repeating elements 7 and 9.
	 * 
	 * Explanation :
	 * 
	 * XOR of two same numbers results in 0(000..00)
	 * 
	 * XOR of two different numbers x and y results in a number which contains
	 * set bits at the places where x and y differ. So if x and y are 10...0100
	 * and 11...1001, then result would be 01...1101.
	 * 
	 * So the idea is to XOR all the elements in set.In the result xor, all
	 * repeating elements would nullify each other. The result would contain the
	 * set bits where two non-repeating elements differ.
	 * 
	 * Now, if we take any set bit of the result xor and again do XOR of the
	 * subset where that particular bit is set, we get the one non-repeating
	 * element.And for other non-repeating element we can take the subset where
	 * that particular bit is not set.
	 * 
	 * We have chosen the rightmost set bit of the xor as it is easy to find
	 * out.
	 * 
	 * Time Complexity: O(n)
	 * Auxiliary Space: O(1)
	 */
	private static void getNonDuplicate(int[] array) {
		int dup = array[0];
		// int offset = 1;
		for (int i = 1; i < array.length; i++) {
			dup = (array[i]) ^ dup;
		}
		int setBit = dup & ~(dup - 1);
		System.out.println("setbit :" + setBit);

		int x = 0, y = 0;

		System.out.println("array :"+XOROperatorExample.toString(array));
		for (int i = 0; i < array.length; i++) {
			if ((setBit & array[i]) == 1) {
				x = x ^ array[i];
			} else {
				y = y ^ array[i];
			}
			
		}

		System.out.println(" x : " + x + " y  : " + y);
	}
	
	
	private static void getDuplicate(int[] array) {
		int dup = array[0];
		// int offset = 1;
		for (int i = 1; i < array.length; i++) {
			dup = (array[i]) ^ dup;
		}
		int setBit = dup & ~(dup - 1);
		System.out.println("setbit :" + setBit);

		int x = 0, y = 0;

		System.out.println("array :"+XOROperatorExample.toString(array));
		for (int i = 0; i < array.length; i++) {
			if ((setBit & array[i]) == 1) {
				x = x ^ array[i];
			} else {
				y = y ^ array[i];
			}
			
		}

		System.out.println("non duplicate  x : " + x + " y  : " + y);
		  for(int i = 0; i < array.length; i++)
		  {
		    if((i & setBit) ==1)
		      x = x ^ i; /*XOR of first set in arr[] and {1, 2, ...n }*/
		    else
		      y = y ^ i; /*XOR of second set in arr[] and {1, 2, ...n } */
		  }
		  
		  System.out.println("\n duplicate "+ x+" y :"+ y);
		
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
	
	static String toString(int[] arr){
		StringBuffer sb = new StringBuffer();
		for(int i =0; i <arr.length; i++){
			if(i ==0){
				sb.append(arr[i]);
			}else{
				sb.append(",").append(arr[i]);
			}
		}
		return sb.toString();
	}
	
	static final String reverseWithXOR(String string) {
        char[] array = string.toCharArray();
        int length = array.length;
        int half = (int) Math.floor(array.length / 2);
        for (int i = 0; i < half; i++) {
            array[i] ^= array[length - i - 1];
            array[length - i - 1] ^= array[i];
            array[i] ^= array[length - i - 1];
        }
        return String.valueOf(array);
    }
}


