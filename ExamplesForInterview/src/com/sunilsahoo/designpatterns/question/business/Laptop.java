package com.sunilsahoo.designpatterns.question.business;

import java.util.ArrayList;
import java.util.List;

public class Laptop {
private IHardware hardware;
private IModel model;
private List<IHardware> hardwareList = new ArrayList<>();
public IHardware getHardware() {
	return hardware;
}
public void addHardware(IHardware hardware) {
	this.hardwareList.add(hardware);
}
public IModel getModel() {
	return model;
}
public void setModel(IModel model) {
	this.model = model;
}
}
