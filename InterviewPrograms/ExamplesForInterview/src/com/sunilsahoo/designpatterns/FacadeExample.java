package com.sunilsahoo.designpatterns;

/**
 * ## Intent Provide a unified interface to a set of interfaces in a subsystem.
 * Facade defines a higher-level interface that makes the subsystem easier to
 * use.
 * 
 * categories: Structural
 * 
 * ## Applicability Use the Facade pattern when
 * 
 * you want to provide a simple interface to a complex subsystem. Subsystems
 * often get more complex as they evolve. Most patterns, when applied, result in
 * more and smaller classes. This makes the subsystem more reusable and easier
 * to customize, but it also becomes harder to use for clients that don't need
 * to customize it. A facade can provide a simple default view of the subsystem
 * that is good enough for most clients. Only clients needing more
 * customizability will need to look beyond the facade. there are many
 * dependencies between clients and the implementation classes of an
 * abstraction. Introduce a facade to decouple the subsystem from clients and
 * other subsystems, thereby promoting subsystem independence and portability.
 * you want to layer your subsystems. Use a facade to define an entry point to
 * each subsystem level. If subsystems are dependent, the you can simplify the
 * dependencies between them by making them communicate with each other solely
 * through their facades
 * 
 * ## Credits
 * 
 * @author sunilkumarsahoo
 *
 */
public class FacadeExample {
	public static void main(String args[]) {
		IDatabaseFacade dao = new DatabaseFacadeImpl();
		dao.getSalary();
	}
}

// Facade layer for database query operation
interface IDatabaseFacade {
	double getSalary();

	int getID();

	String getName();
}

class DatabaseFacadeImpl implements IDatabaseFacade {

	@Override
	public double getSalary() {
		System.out.println("create connection");
		System.out.println("execute get salary query");
		System.out.println("close connection");
		return 0;
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		System.out.println("create connection");
		System.out.println("execute get id query");
		System.out.println("close connection");
		return 0;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		System.out.println("create connection");
		System.out.println("execute get name query");
		System.out.println("close connection");
		return null;
	}
}