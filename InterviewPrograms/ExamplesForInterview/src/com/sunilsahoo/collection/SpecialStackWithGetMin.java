package com.sunilsahoo.collection;

/**
 * 
 * @author sunilkumarsahoo
 * 
 *         Design a Data Structure SpecialStack that supports all the stack
 *         operations like push(), pop(), isEmpty(), isFull() and an additional
 *         operation getMin() which should return minimum element from the
 *         SpecialStack. All these operations of SpecialStack must be O(1). To
 *         implement SpecialStack, you should only use standard Stack data
 *         structure and no other data structure like arrays, list, .. etc.
 * 
 * 
 *         Solution: Use two stacks: one to store actual stack elements and
 *         other as an auxiliary stack to store minimum values. The idea is to
 *         do push() and pop() operations in such a way that the top of
 *         auxiliary stack is always the minimum. Let us see how push() and
 *         pop() operations work. Push(int x) // inserts an element x to Special
 *         Stack 1) push x to the first stack (the stack with actual elements)
 *         2) compare x with the top element of the second stack (the auxiliary
 *         stack). Let the top element be y. .....a) If x is smaller than y then
 *         push x to the auxiliary stack. .....b) If x is greater than y then
 *         push y to the auxiliary stack. int Pop() // removes an element from
 *         Special Stack and return the removed element 1) pop the top element
 *         from the auxiliary stack. 2) pop the top element from the actual
 *         stack and return it. The step 1 is necessary to make sure that the
 *         auxiliary stack is also updated for future operations. int getMin()
 *         // returns the minimum element from Special Stack 1) Return the top
 *         element of auxiliary stack.
 *
 */
public class SpecialStackWithGetMin {
	public static void main(String[] args) {
		SpecialStackWithGetMin customStackExample = new SpecialStackWithGetMin();
		Stack stack = customStackExample.new Stack(10); // Creation of Stack
		stack.push(31);
		stack.push(21);
		stack.push(31);
		stack.push(19);
		stack.push(51);

		System.out.print("Popped items: ");
		System.out.print(
				"Min value :" + stack.getMin() + " after pop :" + stack.pop()
						+ " 22 : " + stack.pop() + " min : " + stack.getMin());

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
		private int[] auxilaryStackAr;
		private int top; // top of stack

		/**
		 * Constructor for initializing Array.
		 */
		public Stack(int size) {
			this.size = size;
			stackAr = new int[size]; // Creation of Stack array
			auxilaryStackAr = stackAr;
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
			int min = 0;
			if (top == -1) {
				min = value;
			} else {

				min = auxilaryStackAr[top] > value ? value
						: auxilaryStackAr[top];
			}
			stackAr[++top] = value;
			auxilaryStackAr[top] = min;
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

		public int getMin() {
			return auxilaryStackAr[top];
		}
	}
}
