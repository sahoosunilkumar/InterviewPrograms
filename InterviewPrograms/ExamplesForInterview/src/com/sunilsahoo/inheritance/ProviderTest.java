package com.sunilsahoo.inheritance;

public class ProviderTest {
	public static void main(String[] args) {

		Provider.getInstance(BuyListProvider.class, BuyListProvider.class);
		BuyListProvider buyListProvider = Provider
				.getInstance(BuyListProvider.class, ProviderTest.class);
		Provider.getInstance(BuyListProvider.class, BuyListProvider.class);
		System.out.println(buyListProvider.getName());
		System.out.println(buyListProvider.getData());
		Provider.unregister(BuyListProvider.class, BuyListProvider.class);

		Provider.unregister(BuyListProvider.class, ProviderTest.class);
		System.out.println(buyListProvider);
	}
}
