package com.sunilsahoo.designpatterns;

/**
 * ## Also known as Policy Type : Behavioral ## Intent Define a family of
 * algorithms, encapsulate each one, and make them interchangeable. Strategy
 * lets the algorithm vary independently from clients that use it.
 * 
 * ![alt text](./etc/strategy_1.png "Strategy")
 * 
 * ## Applicability Use the Strategy pattern when
 * 
 * many related classes differ only in their behavior. Strategies provide a way
 * to configure a class either one of many behaviors you need different variants
 * of an algorithm. for example, you might define algorithms reflecting
 * different space/time trade-offs. Strategies can be used when these variants
 * are implemented as a class hierarchy of algorithms an algorithm uses data
 * that clients shouldn't know about. Use the Strategy pattern to avoid exposing
 * complex, algorithm-specific data structures a class defines many behaviors,
 * and these appear as multiple conditional statements in its operations.
 * Instead of many conditionals, move related conditional branches into their
 * own Strategy class
 * 
 * 
 * 
 * In Strategy pattern, a class behavior or its algorithm can be changed at run
 * time. This type of design pattern comes under behavior pattern. In Strategy
 * pattern, we create objects which represent various strategies and a context
 * object whose behavior varies as per its strategy object. The strategy object
 * changes the executing algorithm of the context object.
 * 
 * @author sunilkumarsahoo
 *
 */
public class StrategyPatternExample {
	public static void main(String args[]) {
		Duck mallardDuck = new MallardDuck();
		mallardDuck.display();
		mallardDuck.performFly();
		mallardDuck.performQuack();

		Duck modelDuck = new ModelDuck();
		modelDuck.display();
		modelDuck.performFly();
		modelDuck.performQuack();
	}
}

abstract class Duck {
	IFlyBehaviour iFlyBehaviour;
	IQuackBehaviour iQuackBehaviour;

	abstract void display();

	public void performFly() {
		iFlyBehaviour.fly();
	}

	public void performQuack() {
		iQuackBehaviour.quack();
	}

	public void swim() {
		System.out.println("all duck has same swim feature");
	}
}

interface IFlyBehaviour {
	void fly();
}

interface IQuackBehaviour {
	void quack();
}

class FlyWithWings implements IFlyBehaviour {

	@Override
	public void fly() {
		System.out.println("Fly with wings");

	}
}

class FlyWithNoWings implements IFlyBehaviour {

	@Override
	public void fly() {
		System.out.println("Cant fly");

	}
}

class Quack implements IQuackBehaviour {

	@Override
	public void quack() {
		System.out.println("Quack");

	}
}

class Silent implements IQuackBehaviour {

	@Override
	public void quack() {
		System.out.println("Silent");

	}
}

class MallardDuck extends Duck {
	MallardDuck() {
		iFlyBehaviour = new FlyWithWings();
		iQuackBehaviour = new Quack();
	}

	@Override
	void display() {
		System.out.println(" I am Mallard Duck");

	}

}

class ModelDuck extends Duck {
	ModelDuck() {
		iFlyBehaviour = new FlyWithNoWings();
		iQuackBehaviour = new Silent();
	}

	@Override
	void display() {
		System.out.println(" I am Model Duck");

	}

}
