package com.sunilsahoo.designpatterns;

/**
 * Define the skeleton of an algorithm in an operation, deferring some steps to
 * subclasses. Lets subclasses redefine certain steps of an algorithm without
 * changing the algorithm's structure.
 * 
 * In Template pattern, an abstract class defines way(s)/template(s) to execute
 * its methods. Its subclasses can override the method implementation as per
 * need but the invocation is to be in the same way as defined by an abstract
 * class. This pattern comes under behavior pattern category.
 * 
 * Template Method defines a skeleton for an algorithm. The algorithm subclasses
 * provide implementation for the blank parts.
 * 
 * @author sunilkumarsahoo
 *
 */
public class TemplateMethodExample {
	public static void main(String[] args) {
		DataBatabaseQuery insertQuery = new InsertQuery();
		insertQuery.execute();

		DataBatabaseQuery deleteQuery = new DeleteQuery();
		deleteQuery.execute();
	}

}

abstract class DataBatabaseQuery {
	private void init() {
		// Initialize DB
		System.out.println("Databse Initialized");
	}

	private void release() {
		// Initialize DB
		System.out.println("Databse released");
	}

	abstract void query();

	// Template Method
	final public void execute() {
		init();
		query();
		release();
	}
}

class InsertQuery extends DataBatabaseQuery {

	@Override
	void query() {
		System.out.println("inserted data");

	}

}

class DeleteQuery extends DataBatabaseQuery {

	@Override
	void query() {
		System.out.println("deleted data");

	}

}
