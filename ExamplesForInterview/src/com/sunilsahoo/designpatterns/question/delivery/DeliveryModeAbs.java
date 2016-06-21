package com.sunilsahoo.designpatterns.question.delivery;

public abstract class DeliveryModeAbs {
	
	DeliveryModeAbs(String mode, double price){
		this.mode = mode;
		this.price = price;
	}
	private String mode;
	private double price;
	public String getMode() {
		return mode;
	}
	public double getPrice() {
		return price;
	}
}
