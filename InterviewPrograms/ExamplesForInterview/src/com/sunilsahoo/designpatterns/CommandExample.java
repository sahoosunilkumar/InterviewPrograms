package com.sunilsahoo.designpatterns;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Type : Behavioural Encapsulate a command request as an object
 * 
 * Intent :
 * 
 * • Encapsulate a request as an object, thereby letting you parameterize
 * clients with different requests, queue or log requests, and support undoable
 * operations. • Promote "invocation of a method on an object" to full object
 * status • An object-oriented callback
 * 
 * Problem :
 * 
 * Need to issue requests to objects without knowing anything about the 
 * operation being requested or the receiver of the request.
 * 
 * Command decouples the object that invokes the
 * operation from the one that knows how to perform it. To achieve this
 * separation, the designer creates an abstract base class that maps a receiver
 * (an object) with an action (a pointer to a member function). The base class
 * contains an execute method that simply calls the action on the receiver. All
 * clients of Command objects treat each object as a "black box" by simply
 * invoking the object's virtual execute method whenever the client requires the
 * object's "service". A Command class holds some subset of the following: an
 * object, a method to be applied to the object, and the arguments to be passed
 * when the method is applied. The Command's "execute" method then causes the
 * pieces to come together. Sequences of Command objects can be assembled into
 * composite (or macro) commands.
 * 
 * @author sunilkumarsahoo
 *
 */
public class CommandExample {
	
	public static void main(String[] args) {
		CommandExample[] objs = { new CommandExample(1),
				new CommandExample(2) };
		System.out.print("Normal call results: ");
		System.out.print(objs[0].addOne(new Integer(3)) + " ");
		System.out.print(objs[1].addTwo(new Integer(4), new Integer(5)) + " ");
		Command[] cmds = {
				new Command(objs[0], "addOne",
						new Integer[] { new Integer(3) }),
				new Command(objs[1], "addTwo",
						new Integer[] { new Integer(4), new Integer(5) }) };
		System.out.print("\nReflection results:  ");
		for (int i = 0; i < cmds.length; i++)
			System.out.print(cmds[i].execute() + " ");
		System.out.println();
		
		System.out.println("=====Example 2=====");
		
		List<ICommand> queue = produceRequests();
		   workOffRequests( queue );
	}
	
	//===============EXAMPLE1==========
	
	private int state;

	public CommandExample( int in ) {
	      state = in;
	   }

	public int addOne(Integer one) {
		return state + one.intValue();
	}

	public int addTwo(Integer one, Integer two) {
		return state + one.intValue() + two.intValue();
	}

	static class Command {
		private Object receiver; // the "encapsulated" object
		private Method action; // the "pre-registered" request
		private Object[] args; // the "pre-registered" arg list

		public Command(Object obj, String methodName, Object[] arguments) {
			receiver = obj;
			args = arguments;
			Class cls = obj.getClass(); // get the object's "Class"
			Class[] argTypes = new Class[args.length];
			for (int i = 0; i < args.length; i++) // get the "Class" for each
				argTypes[i] = args[i].getClass(); // supplied argument
			// get the "Method" data structure with the correct name and
			// signature
			try {
				action = cls.getMethod(methodName, argTypes);
			} catch (NoSuchMethodException e) {
				System.out.println(e);
			}
		}

		public Object execute() {
			// in C++, you do something like --- return receiver->action( args
			// );
			try {
				return action.invoke(receiver, args);
			} catch (IllegalAccessException e) {
				System.out.println(e);
			} catch (InvocationTargetException e) {
				System.out.println(e);
			}
			return null;
		}
	}


	//===========EXAMPLE2==========
	
	
	
	
	interface ICommand { void execute(); }

	static class DomesticEngineer implements ICommand {
	   @Override
	public void execute() {
	      System.out.println( "take out the trash" );
	}  }

	static class Politician implements ICommand {
	   @Override
	public void execute() {
	      System.out.println( "take money from the rich, take votes from the poor" );
	}  }

	static class Programmer implements ICommand {
	   @Override
	public void execute() {
	      System.out.println( "sell the bugs, charge extra for the fixes" );
	}  }

	public static List<ICommand> produceRequests() {
	   List<ICommand> queue = new ArrayList<>();
	   queue.add( new DomesticEngineer() );
	   queue.add( new Politician() );
	   queue.add( new Programmer() );
	   return queue;
	}

	public static void workOffRequests( List<ICommand> queue ) {
	   for (Iterator it = queue.iterator(); it.hasNext(); )
	      ((ICommand)it.next()).execute();
	}
}
