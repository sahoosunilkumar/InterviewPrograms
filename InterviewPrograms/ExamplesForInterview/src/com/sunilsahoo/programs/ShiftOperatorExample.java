package com.sunilsahoo.programs;

/**
 * 
 * @author sunilkumarsahoo
 * 
 *         ) >> (Signed right shift) In Java, the operator ‘>>’ is signed right
 *         shift operator. All integers are signed in Java, and it is fine to
 *         use >> for negative numbers. The operator ‘>>’ uses the sign bit
 *         (left most bit) to fill the trailing positions after shift. If the
 *         number is negative, then 1 is used as a filler and if the number is
 *         positive, then 0 is used as a filler. For example, if binary
 *         representation of number is 10….100, then right shifting it by 2
 *         using >> will make it 11…….1.
 * 
 *         >>> (Unsigned right shift) In Java, the operator ‘>>>’ is unsigned
 *         right shift operator. It always fills 0 irrespective of the sign of
 *         the number.
 * 
 * 
 *
 */
public class ShiftOperatorExample {
	public static void main(String[] args) {
		System.out.println(Integer.MIN_VALUE+"-------get signed bit to know even or odd----"+Integer.MAX_VALUE);
		int x = -157;
		System.out.println("x in binary format : " + Integer.toBinaryString(x));
		System.out.println(x >> 31);
		int y = 157;
		System.out.println("y in binary format : " + Integer.toBinaryString(y));

		System.out.println(y >> 31);

		// 20 in binary is: 00000000000000000000000000010100
		// shift all bits 2 positions to right 00000000000000000000000000000101
		// shift all the bits in 20 (in binary form) to the right by 2
		System.out.println("20>>2 = " + (20 >> 2));

		// -1 in binary is: 11111111111111111111111111111111
		//
		// shift all bits 2 positions to the right AND insert 1's to left you
		// obtain
		//
		// 11111111111111111111111111111111
		//
		// This is -1 in binary form
		//
		// NOTE: the >> operator preserves the leftmost bits. The leftmost bits
		// are filled with the previous content. This is to do with sign
		// extension. In this case there is a 1 at the left and it is preserved.
		// If you do not want to keep the 1 to the left, use the >>> operator
		// which shifts 0's into the leftmost bits
		System.out.println("-1>>2 = " + (-1 >> 2));

		int n;

		n = -10;
		System.out.println("Value of n: " + n);
		// multiply by 2
		n = n << 1;
		System.out.println("Value of n after n = n * 2: " + n+" ... ");

		// multiply by 4
		n = n << 2;
		System.out.println("Value of n after n = n * 4: " + n);

		// divide by 2
		n = n >> 1;
		System.out.println("Value of n after n = n / 2: ");

		// divide by 4
		n = n >> 2;
		System.out.println("Value of n after n = n / 4: " + n);

	}
}
