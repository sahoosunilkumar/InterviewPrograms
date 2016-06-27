package com.sunilsahoo.collection;

/*
 * Unfortunately, there are drawbacks to the use of a traditional linked list for this purpose. It is unnecessarily inefficient to repeatedly throw away a node from one end of the list, only to create a new node for the same element when reinserting it, not to mention the various updates that are performed to decrement and increment the list’s size and to unlink and relink nodes.
In the remainder of this section, we demonstrate how a slight modification to our singly linked list implementation can be used to provide a more efficient data structure for representing a cyclic order.

We use this model to design and implement a new CircularlyLinkedList class, which supports all of the public behaviors of our SinglyLinkedList class and one additional update method:
rotate(): Moves the first element to the end of the list.
With this new operation, round-robin scheduling can be efficiently implemented by
repeatedly performing the following steps on a circularly linked list C: 1. Give a time slice to process C.first()
2. C.rotate()

In implementing a new class, we make one additional optimization—we no longer explicitly maintain the head reference. So long as we maintain a reference to the tail, we can locate the head as tail.getNext(). Maintaining only the tail reference not only saves a bit on memory usage, it makes the code simpler and more efficient, as it removes the need to perform additional operations to keep a head reference current. In fact, our new implementation is arguably superior to our original singly linked list implementation, even if we are not interested in the new rotate method.
 */
public class CustomCircularLinkedListExample {

	public class SinglyLinkedList<E> {
		private Node<E> head = null;
		private Node<E> tail = null;
		private int size = 0;

		// head node of the list (or null if empty) // last node of the list (or
		// null if empty) // number of nodes in the list
		// constructs an initially empty list (nested Node class goes here)
		// instance variables of the SinglyLinkedList
		public int size() {
			return size;
		}

		public boolean isEmpty() {
			return size == 0;
		}

		public E first() {
			if (isEmpty())
				return null;
			return head.getElement();
		}

		public E last() {
			if (isEmpty())
				return null;
			return tail.getElement();
		}

		// update methods
		public void addFirst(E e) {
			head = new Node<>(e, head);
			if (size == 0)
				tail = head;
			size++;
		}

		public void addLast(E e) {
			Node<E> newest = new Node<>(e, null); // node will eventually be the
													// tail
			if (isEmpty())
				head = newest;
			else
				tail.setNext(newest);
			tail = newest;
			size++;
		}
	}

	public class CircularlyLinkedList<E> {
		private Node<E> tail = null;
		private int size = 0;

		public int size() {
			return size;
		}

		public boolean isEmpty() {
			return size == 0;
		}

		public E first() { // returns (but does not remove) the first element
			if (isEmpty())
				return null;
			return tail.getNext().getElement(); // the head is *after* the tail
		}

		public E last() {
			if (isEmpty())
				return null;
			return tail.getElement();
		}

		// update methods
		public void rotate() {
			if (tail != null)
				tail = tail.getNext();
		}

		public void addFirst(E e) {
			if (size == 0) {
				tail = new Node<>(e, null);
				tail.setNext(tail);
			} else {
				Node<E> newest = new Node<>(e, tail.getNext());
				tail.setNext(newest);
			}
			size++;
		}

		public void addLast(E e) {
			addFirst(e);
			tail = tail.getNext();
		}

		public E removeFirst() {
			// adds element e to the end of the list // insert new element at
			// front of list // now new element becomes the tail
			// removes and returns the first element // nothing to remove
			// must be the only node left // removes ”head” from the list
			if (isEmpty())
				return null;
			Node<E> head = tail.getNext();
			if (head == tail)
				tail = null;
			else {
				tail.setNext(head.getNext());
				size--;
			}

			return head.getElement();
		}
	}

	private static class Node<E> {
		private E element;
		private Node<E> next;

		public Node(E e, Node<E> n) {
			element = e;
			next = n;
		}

		public E getElement() {
			return element;
		}

		public Node<E> getNext() {
			return next;
		}

		public void setNext(Node<E> n) {
			next = n;
		}
	}
}
