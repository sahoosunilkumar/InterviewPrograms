package com.sunilsahoo.designprinciple.solid;

/**
 * A client should never be forced to implement an interface that it doesn’t use or clients shouldn’t be forced to depend on methods they do not use.
 * When we have non-cohesive interfaces, the ISP guides us to create multiple, smaller, cohesive interfaces.
When you apply ISP, classes and their dependencies communicate using tightly-focused interfaces, minimizing dependencies on unused members and reducing coupling accordingly. Smaller interfaces are easier to implement, improving flexibility and the possibility of reuse. As fewer classes share these interfaces, the number of changes that are required in response to an interface modification is lowered, which increases robustness.

 * @author sunilkumarsahoo
 *
 */
public class InterfaceSegregationExample {
public static void main(String[] args){
	Button1 b1 = new Button1();
	b1.onClick();
	b1.onKeyDown();
	SoftKeyboard1 s1 = new SoftKeyboard1();
	s1.onKeyDown();
	s1.onClick();
	
	//now correct way
	
	Button b = new Button();
	b.onClick();
	SoftKeyboard s = new SoftKeyboard();
	s.onKeyDown();
	
}
}

class Button1 implements OnActionListener {

	@Override
	public void onClick() {
		// TODO implement onclick
		System.out.println(" onclick implemented");
		
	}

	@Override
	public void onKeyDown() {
		//don't implement as it is not required
	}
	
}

class SoftKeyboard1 implements OnActionListener {

	@Override
	public void onClick() {
		//don't implement as it is not required
	}

	@Override
	public void onKeyDown() {
		// TODO implement onclick
		System.out.println(" onKeyDown implemented");
	}
	
}
interface OnActionListener { 
	void onClick();
	void onKeyDown();
}

// ======= RIFGHT WAY OF INTERFACE SEGRGATION START ========
interface OnClickListener{
	void onClick();
}

interface OnKeyListener{
	void onKeyDown();
}
//======= RIFGHT WAY OF INTERFACE SEGRGATION END ========


class Button implements OnClickListener {

	@Override
	public void onClick() {
		// TODO implement onclick
		System.out.println(" onclick implemented");
		
	}
}

class SoftKeyboard implements OnKeyListener{

	@Override
	public void onKeyDown() {
		// TODO Auto-generated method stub
		System.out.println(" onKeyDown implemented");
	}
	
}
//RIFGT WAY OF INTERFACE SEGRGATION