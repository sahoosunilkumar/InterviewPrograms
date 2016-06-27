package com.sunilsahoo.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class Student {
	String name;
	String id;

	public Student() {
	}

	public Student(String name, String id) {
		this.name = name;
		this.id = id;
	}

	@Override
	public String toString() {
		return "Student{" + "name=" + name + ", id=" + id + '}';
	}

	// Inner class
	class ComparatorName implements Comparator<Student> {
		@Override
		public int compare(Student obj1, Student obj2) {
			// sort Student on basis of name(ascending order)
			return obj1.name.compareTo(obj2.name);
		}
	}

	// static nested class
	static class ComparatorId implements Comparator<Student> {
		@Override
		public int compare(Student obj1, Student obj2) {
			// sort Student on basis of id(ascending order)
			return obj1.id.compareTo(obj2.id);
		}
	}

}

/**
 * @author AnkitMittal Copyright (c), AnkitMittal JavaMadeSoEasy.com Main class
 */
public class ComparatorExample {
	public static void main(String[] args) {

		Student emp1 = new Student("sam", "4");
		Student emp2 = new Student("amy", "2");
		Student emp3 = new Student("brad", "1");

		ArrayList<Student> list = new ArrayList<Student>();
		list.add(emp1);
		list.add(emp2);
		list.add(emp3);

		System.out.println("list Before sorting : \n" + list);

		Collections.sort(list, new Student().new ComparatorName());
		System.out.println(
				"\nlist after sorting on basis of name(ascending order), "
						+ "using inner class : \n" + list);

		Collections.sort(list, new Student.ComparatorId());
		System.out.println(
				"\nlist after sorting on basis of id(ascending order), "
						+ "using static nested class : \n" + list);

	}
}
