package com.sunilsahoo.exception;

public class Test {
	public static void main(String[] args) {
		System.out.println(getData());
	}

	static int getData() {
		int x;
		try {
			x = 5;
			x = x / 0;
			return x;
		} catch (Exception e) {
			x = 6;
		} finally {
			x = 7;

		}
		return x;
	}
}
