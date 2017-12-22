package com.sunilsahoo.designpatterns;

/**
 * Type: Creational Intent • Specify the kinds of objects to create using a
 * prototypical instance, and create new objects by copying this prototype. •
 * Co-opt one instance of a class for use as a breeder of all future instances.
 * • The new operator considered harmful. Problem Application "hard wires" the
 * class of object to create in each "new" expression.
 * 
 * A fully initialized instance to be copied or cloned
 * 
 * @author sunilkumarsahoo
 *
 */
public class PrototypeExample {
	// 3. Populate the "registry"
	public static void initializePrototypes() {
		PrototypesModule.addPrototype(new This());
		PrototypesModule.addPrototype(new That());
		PrototypesModule.addPrototype(new TheOther());
	}

	public static void main(String[] args1) {
		initializePrototypes();
		System.out.println(
				"Get Object assigned : " + PrototypesModule.get("This"));
		System.out.println();
		String[] strArr = { "This", "That", "TheOther" };
		Object[] objects = new Object[9];
		int total = 0;

		// 6. Client does not use "new"
		for (int i = 0; i < strArr.length; i++) {
			objects[total] = PrototypesModule.findAndClone(strArr[i]);
			if (objects[total] != null)
				total++;
		}
		for (int i = 0; i < total; i++) {
			((Command) objects[i]).execute();
		}
	}
}

interface Prototype {
	Object clone();

	String getName();
}

// 1. The clone() contract
interface Command {
	void execute();
}

class PrototypesModule {
	// 2. "registry" of prototypical objs
	private static Prototype[] prototypes = new Prototype[9];
	private static int total = 0;

	// Adds a feature to the Prototype attribute of the PrototypesModule class
	// obj The feature to be added to the Prototype attribute
	public static void addPrototype(Prototype obj) {
		prototypes[total++] = obj;
	}

	public static Object findAndClone(String name) {
		// 4. The "virtual ctor"
		for (int i = 0; i < total; i++) {
			if (prototypes[i].getName().equals(name)) {
				return prototypes[i].clone();
			}
		}
		System.out.println(name + " not found");
		return null;
	}

	public static Object get(String name) {
		// 4. The "virtual ctor"
		for (int i = 0; i < total; i++) {
			if (prototypes[i].getName().equals(name)) {
				return prototypes[i];
			}
		}
		return null;
	}
}

// 5. Sign-up for the clone() contract.
// Each class calls "new" on itself FOR the client.
class This implements Prototype, Command {
	@Override
	public Object clone() {
		return new This();
	}

	@Override
	public String getName() {
		return "This";
	}

	@Override
	public void execute() {
		System.out.println("This: execute : " + this);
	}
}

class That implements Prototype, Command {
	@Override
	public Object clone() {
		return new That();
	}

	@Override
	public String getName() {
		return "That";
	}

	@Override
	public void execute() {
		System.out.println("That: execute : " + this);
	}
}

class TheOther implements Prototype, Command {
	@Override
	public Object clone() {
		return new TheOther();
	}

	@Override
	public String getName() {
		return "TheOther";
	}

	@Override
	public void execute() {
		System.out.println("TheOther: execute : " + this);
	}
}
