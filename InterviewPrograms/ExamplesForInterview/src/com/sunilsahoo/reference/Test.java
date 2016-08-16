package com.sunilsahoo.reference;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.WeakHashMap;

public class Test {
	public static void main(String args[]) {
		HashMap<String, String> aMap = new HashMap<String, String>();

		String name = new String("sunil");
		String empId = new String("bi217");

		aMap.put(name, empId);

		name = null;
		empId = null;

		System.gc();
		System.out.println("Strong reference Size of Map : " + aMap.size()
		+ " map : " + aMap);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("After 1 seconds Strong reference Size of Map : " + aMap.size()
		+ " map : " + aMap);

		// WEAK reference
		WeakHashMap<String, String> aWeakMap = new WeakHashMap<String, String>();
		System.out.println("size of map : "+aWeakMap.size());

		name = new String("susil");
		empId = new String("bi218");

		aWeakMap.put(name, empId);

		name = null;

		System.gc();
		System.out.println("Weak reference Size of Map : " + aWeakMap.size()
				+ " map : " + aWeakMap);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("After 1 seconds Weak reference Size of Map : " + aWeakMap.size()
		+ " map : " + aWeakMap);
		
		
		
		
		
		
		
		LinkedHashMap<String, String> aLinkedMap = new LinkedHashMap<String, String>();

		name = new String("lipi");
		empId = new String("bi219");

		aMap.put(name, empId);

		name = null;

		System.gc();
		System.out.println("Strong reference Size of linked Map : " + aLinkedMap.size()
		+ " map : " + aLinkedMap);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("After 1 seconds Strong reference Size of linked Map : " + aLinkedMap.size()
		+ " map : " + aLinkedMap);

	}
}

//class Employee {
//	private String name;
//
//	Employee(String name) {
//		this.name = name;
//	}
//}
//
//class EmployeeVal {
//	private String jobType;
//
//	EmployeeVal(String jobType) {
//		this.jobType = jobType;
//	}
//}
