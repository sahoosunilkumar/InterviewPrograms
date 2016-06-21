package com.sunilsahoo.designpatterns.question;

import com.sunilsahoo.designpatterns.question.business.EcomomicModel;
import com.sunilsahoo.designpatterns.question.business.HardDisk;
import com.sunilsahoo.designpatterns.question.business.IHardware;
import com.sunilsahoo.designpatterns.question.business.IModel;
import com.sunilsahoo.designpatterns.question.business.Laptop;
import com.sunilsahoo.designpatterns.question.business.Memory;
import com.sunilsahoo.designpatterns.question.database.DAO;

public class CustomerTest {
	public static void main(String[] args){
		//get laptop
		Laptop laptop = new Laptop();
		//set model
		IModel model = new EcomomicModel();
		laptop.setModel(model);
		//set hardware
		IHardware memory = new Memory(); 
		IHardware harddisk = new HardDisk();
		laptop.addHardware(memory);
		laptop.addHardware(harddisk);
		
		DAO.getInstance().getItem();
	}

}
