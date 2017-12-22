package com.sunilsahoo.designpatterns;

import java.util.ArrayList;
import java.util.List;

/**
 * Type: Behavioral Defines a new operation to a class without change Intent : •
 * Represent an operation to be performed on the elements of an object
 * structure. Visitor lets you define a new operation without changing the
 * classes of the elements on which it operates. • The classic technique for
 * recovering lost type information. • Do the right thing based on the type of
 * two objects. • Double dispatch Problem : Many distinct and unrelated
 * operations need to be performed on node objects in a heterogeneous aggregate
 * structure. You want to avoid "polluting" the node classes with these
 * operations. And, you don't want to have to query the type of each node and
 * cast the pointer to the correct type before performing the desired operation.
 * 
 * @author sunilkumarsahoo
 *
 */

interface Component {
	void traverse();
}

class Leaf implements Component {
	private int number;

	public Leaf(int num) {
		number = num;
	}

	@Override
	public void traverse() {
		System.out.print(number + " ");
	}
}

class Composite implements Component {
	private static char next = 'a';
	private List<Component> children = new ArrayList<Component>();
	private char letter = next++;

	public void add(Component c) {
		children.add(c);
	}

	@Override
	public void traverse() {
		System.out.print(letter + " ");
		for (int i = 0; i < children.size(); i++)
			children.get(i).traverse();
	}
}

public class VisitorExample {
	public static void main(String[] args) {
		Composite[] containers = new Composite[3];

		for (int i = 0; i < containers.length; i++) {
			containers[i] = new Composite();
			for (int j = 1; j < 4; j++)
				containers[i].add(new Leaf(i * containers.length + j));
		}

		for (int i = 1; i < containers.length; i++)
			containers[0].add(containers[i]);

		containers[0].traverse();
		System.out.println();
	}
}
