package com.sunilsahoo.inheritance;

public class BuyListProvider extends BuyListProvider1{
	String data;
//	private BuyListProvider(String data){
//		this.data = data;
//	}

public static void main(String str[]){
	Provider provider = new BuyListProvider1();
	System.out.println(provider.getName());
	
	BuyListProvider bp = (BuyListProvider) new BuyListProvider1();
	System.out.println(bp.getName());
}
@Override
String getName() {
	// TODO Auto-generated method stub
	return "BuyListProvider";
}
}

class BuyListProvider1 extends Provider{
	String data;
	@Override
	String getName() {
		// TODO Auto-generated method stub
		return "BuyListProvider1";
	}
}
