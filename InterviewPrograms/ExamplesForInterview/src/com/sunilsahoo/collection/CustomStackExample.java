package com.sunilsahoo.collection;

/**
 * A stack is a collection of objects that are inserted and removed according 
 * to the last-in, first-out (LIFO)
 * 
 * Example 6.1: Internet Web browsers store the addresses of recently visited
 * sites on a stack. Each time a user visits a new site, that site’s address is
 * “pushed” onto the stack of addresses. The browser then allows the user to
 * “pop” back to previously visited sites using the “back” button.
 * Example 6.2: Text editors usually provide an “undo” mechanism that cancels recent
 * editing operations and reverts to former states of a document. This undo
 * oper-ation can be accomplished by keeping text changes in a stack.
 * 
 * Stacks are the simplest of all data structures, yet they are also among the
 * most important, as they are used in a host of different applications, and as
 * a tool for many more sophisticated data structures and algorithms. Formally,
 * a stack is an abstract data type (ADT) that supports the following two update
 * methods: push(e): Adds element e to the top of the stack. pop(): Removes and
 * returns the top element from the stack (or null if the stack is empty).
 * Additionally, a stack supports the following accessor methods for
 * convenience: top(): Returns the top element of the stack, without removing it
 * (or null if the stack is empty). size(): Returns the number of elements in
 * the stack. isEmpty(): Returns a boolean indicating whether the stack is
 * empty. By convention, we assume that elements added to the stack can have
 * arbitrary type and that a newly created stack is empty.
 * 
 * size() = O(1)
 * isEmpty() = O(1)
 * top() = O(1)
 * push() = O(1)
 * pop() = O(1)
 * 
 * The space usage is O(N)
 */
public class CustomStackExample {
	public static void main(String[] args) {
		CustomStackExample customStackExample = new CustomStackExample();
		Stack stack = customStackExample.new Stack(10); // Creation of Stack
		stack.push(11);
		stack.push(21);
		stack.push(31);
		stack.push(41);
		stack.push(51);

		System.out.print("Popped items: ");
		System.out.print(stack.pop() + " ");
		System.out.print(stack.pop() + " ");
		System.out.print(stack.pop() + " ");
		System.out.print(stack.pop() + " ");
		System.out.print(stack.pop() + " ");

	}

	/**
	 * Exception to indicate that Stack is full.
	 */
	class StackFullException extends RuntimeException {

		/**
		 * 
		 */
		private static final long serialVersionUID = 7615913434079640170L;

		public StackFullException() {
			super();
		}

		public StackFullException(String message) {
			super(message);
		}

	}

	/**
	 * Exception to indicate that Stack is empty.
	 */
	class StackEmptyException extends RuntimeException {

		/**
		 * 
		 */
		private static final long serialVersionUID = 7268719048068907003L;

		public StackEmptyException() {
			super();
		}

		public StackEmptyException(String message) {
			super(message);
		}

	}

	class Stack {
		private int size;
		private int[] stackAr;
		private int top; // top of stack

		/**
		 * Constructor for initializing Array.
		 */
		public Stack(int size) {
			this.size = size;
			stackAr = new int[size]; // Creation of Stack array
			top = -1; // initialize Stack to with -1
		}

		/**
		 * Push items in stack, it will put items on top of Stack.
		 */
		public void push(int value) {
			if (isFull()) {
				throw new StackFullException(
						"Cannot push " + value + ", Stack is full");
			}
			stackAr[++top] = value;
		}

		/**
		 * Pop items in stack, it will remove items from top of Stack.
		 */
		public int pop() {
			if (isEmpty()) {
				throw new StackEmptyException("Stack is empty");
			}
			return stackAr[top--]; // remove item and decrement top as well.
		}

		/**
		 * @return true if Stack is empty
		 */
		public boolean isEmpty() {
			return (top == -1);
		}

		/**
		 * @return true if stack is full
		 */

		public boolean isFull() {
			return (top == size - 1);
		}

	}

}
