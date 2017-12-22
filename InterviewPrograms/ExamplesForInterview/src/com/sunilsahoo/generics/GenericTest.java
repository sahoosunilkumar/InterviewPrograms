package com.sunilsahoo.generics;

import java.util.List;

public class GenericTest {
	public static void main(String[] args) {

	}

	public <T extends Gen> void addToPurchase(List<T> saleList) {
		for (T sale : saleList) {
			// purchase.add(sale);
			sale.gen();
		}
	}

}

class Gen1 extends Gen {
	public void gen1() {
		System.out.println("gen1");
	}
}

class Gen2 extends Gen {
	public void gen2() {
		System.out.println("gen2");
	}
}

class Gen {
	public void gen() {
		System.out.println("gen");
	}
}
