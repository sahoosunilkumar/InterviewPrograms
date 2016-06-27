package com.sunilsahoo.inheritance;

public class AbstractTest extends AbstractConstructor {
	public static void main(String[] args) {

	}

	@Override
	void printName() {
		// TODO Auto-generated method stub

	}
}

abstract class AbstractConstructor {
	private String str;

	AbstractConstructor(String str) {
		this.str = str;
	}

	abstract void printName();
}
