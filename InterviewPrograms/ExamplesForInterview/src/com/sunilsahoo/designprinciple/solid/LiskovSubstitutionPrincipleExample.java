package com.sunilsahoo.designprinciple.solid;

/**
 * 
 * Derived types must be completely substitutable for their base types
 * 
 * It means that the classes fellow developer created by extending your class should be able to fit in application without failure. I.e. if a fellow developer poorly extended some part of your class and injected into framework/ application then it should not break the application or should not throw fatal exceptions.
This can be insured by using strictly following first rule. If your base class is doing one thing strictly, the fellow developer will override only one feature incorrectly in worst case. This can cause some errors in one area, but whole application will not do down.

Violations of LSP cause undefined behaviour. Undefined behaviour means that it works okay during development but blows up in production, or that you spend weeks debugging something that only occurs once per day, or that you have to go through hundreds of megabytes of logs to figure out what went wrong.


eg Set should not extend from ArrayList 
Square should not extend from Rectangle
 */

/**
 * 
 * @author sunilkumarsahoo
 *
 */
public class LiskovSubstitutionPrincipleExample {

}

// =====VIOLATES LSB as bird is not actually Airplane====
class Airplane1 {
	public void fly() {
		// TODO start run
		// TODO start takeoff
		// TODO start fly inclined manner
	}
}

class Bird1 extends Airplane1 {
	@Override
	public void fly() {
		// TODO fly directly
		super.fly();
	}
}

// =====VIOLATES LSB END====

// =====Pass LSB====
interface IFlayer {
	void fly();
}

class Airplane implements IFlayer {
	public void fly() {
		// TODO start run
		// TODO start takeoff
		// TODO start fly inclined manner
	}
}

class Bird implements IFlayer {
	@Override
	public void fly() {
		// TODO fly directly
	}
}

class Eagle extends Bird {
	@Override
	public void fly() {
		// TODO fly directly
	}
}
// =====Pass LSB END====
