package com.sunilsahoo.designpatterns;

/**
 * categories: Behavioral
 * 
 * ## Also known as Objects for States
 * 
 * ## Intent Allow an object to alter its behavior when its internal state
 * changes. The object will appear to change its class.
 * 
 * 
 * ## Applicability Use the State pattern in either of the following cases
 * 
 * an object's behavior depends on its state, and it must change its behavior at
 * run-time depending on that state operations have large, multipart conditional
 * statements that depend on the object's state. This state is usually
 * represented by one or more enumerated constants. Often, several operations
 * will contain this same conditional structure. The State pattern puts each
 * branch of the conditional in a separate class. This lets you treat the
 * object's state as an object in its own right that can vary independently from
 * other objects.
 * 
 * Best example of Call state in Phone
 * 
 * @author sunilkumarsahoo
 *
 */
public class StateDesignPatternExample {
	public static void main(String[] args) {

		Call Call = new Call();
		Call.observe();
		Call.changeState();
		Call.observe();
		Call.changeState();
		Call.observe();

	}
}

/**
 * 
 * Call has internal state that defines its behavior.
 * 
 */
class Call {

	private State state;

	public Call() {
		state = new IdleState(this);
	}

	/**
	 * Makes time pass for the Call
	 */
	public void changeState() {
		if (state.getClass().equals(IdleState.class)) {
			changeStateTo(new RingingState(this));
		} else if (state.getClass().equals(RingingState.class)) {
			changeStateTo(new InCallState(this));
		} else {
			changeStateTo(new IdleState(this));
		}
	}

	private void changeStateTo(State newState) {
		this.state = newState;
		this.state.onEnterState();
	}

	@Override
	public String toString() {
		return "The Call";
	}

	public void observe() {
		this.state.observe();
	}
}

class IdleState implements State {

	private Call Call;

	public IdleState(Call Call) {
		this.Call = Call;
	}

	@Override
	public void observe() {
		System.out.println(String.format("%s In Idle State.", Call));
	}

	@Override
	public void onEnterState() {
		System.out.println(String.format("%s entering to Idle State.", Call));
	}

}

class InCallState implements State {

	private Call Call;

	public InCallState(Call Call) {
		this.Call = Call;
	}

	@Override
	public void observe() {
		System.out.println(String.format("%s In InCall State.", Call));
	}

	@Override
	public void onEnterState() {
		System.out.println(String.format("%s entering to InCall State.", Call));
	}

}

class RingingState implements State {

	private Call Call;

	public RingingState(Call Call) {
		this.Call = Call;
	}

	@Override
	public void observe() {
		System.out.println(String.format("%s In Ringing State.", Call));
	}

	@Override
	public void onEnterState() {
		System.out.println(String.format("%s entering to Ringing State.", Call));
	}

}

interface State {

	void onEnterState();

	void observe();

}
