package com.sunilsahoo.inheritance;

public class Test {
public static void main(String args[]){
	Duck mallardDuck = new MallardDuck(new FlyWithWings(), new Quack());
	mallardDuck.display();
	mallardDuck.performFly();
	mallardDuck.performQuack();
}
}

abstract class Duck{
	IFlyBehaviour iFlyBehaviour;
	IQuackBehaviour iQuackBehaviour;
	Duck(IFlyBehaviour iFlyBehaviour, IQuackBehaviour iQuackBehaviour){
		this.iFlyBehaviour = iFlyBehaviour;
		this.iQuackBehaviour = iQuackBehaviour;
	}
	abstract void display();
	public void performFly(){
		iFlyBehaviour.fly();
	}
	public void performQuack(){
		iQuackBehaviour.quack();
	}
	public void swim(){
		System.out.println("all duck has same swim feature");
	}
}

interface IFlyBehaviour{
	void fly();
}
interface IQuackBehaviour{
	void quack();
}
class FlyWithWings implements IFlyBehaviour{

	@Override
	public void fly() {
		System.out.println("Fly with wings");
		
	}
}

class FlyWithNoWings implements IFlyBehaviour{

	@Override
	public void fly() {
		System.out.println("Cant fly");
		
	}
}

class Quack implements IQuackBehaviour{

	@Override
	public void quack() {
		System.out.println("Quack");
		
	}	
}

class Silent implements IQuackBehaviour{

	@Override
	public void quack() {
		System.out.println("Silent");
		
	}	
}

class MallardDuck extends Duck{
	
	MallardDuck(){
		
	}
	MallardDuck(IFlyBehaviour iFlyBehaviour, IQuackBehaviour iQuackBehaviour){
		super(iFlyBehaviour, iQuackBehaviour);
	}
	@Override
	void display() {
		System.out.println(" I am Mallard Duck");
		
	}
	
}
	

