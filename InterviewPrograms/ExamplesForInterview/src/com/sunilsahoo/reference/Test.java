package com.sunilsahoo.reference;

import java.util.HashMap;
import java.util.WeakHashMap;

public class Test {
	public static void main(String args[]) {
		HashMap<Employee, EmployeeVal> aMap = new HashMap<Employee, EmployeeVal>();

		Employee emp = new Employee("Vinoth");
		EmployeeVal val = new EmployeeVal("Programmer");

		aMap.put(emp, val);

		emp = null;

		System.gc();
		System.out.println("Strong reference Size of Map : " + aMap.size()
				+ " map : " + aMap);

		// WEAK reference
		WeakHashMap<Employee, EmployeeVal> aWeakMap = new WeakHashMap<Employee, EmployeeVal>();

		emp = new Employee("Vinoth");
		val = new EmployeeVal("Programmer");

		aWeakMap.put(emp, val);

		emp = null;

		System.gc();
		System.out.println("Weak reference Size of Map : " + aWeakMap.size()
				+ " map : " + aWeakMap);

	}
}

class Employee {
	private String name;

	Employee(String name) {
		this.name = name;
	}
}

class EmployeeVal {
	private String jobType;

	EmployeeVal(String jobType) {
		this.jobType = jobType;
	}
}
