package com.sunilsahoo.programs;

public class LinkedListProgram {

	public static void main(String args[]) {
		LinkedListProgram linkedListProgram = new LinkedListProgram();
		Node head = linkedListProgram.new Node(50);
		head.next = linkedListProgram.new Node(20);
		head.next.next = linkedListProgram.new Node(15);
		head.next.next.next = linkedListProgram.new Node(4);
		head.next.next.next.next = linkedListProgram.new Node(10);

		// Creating a loop for testing
		head.next.next.next.next.next = head.next.next;
		linkedListProgram.detectLoop(head);

		head = linkedListProgram.new Node(1);
		head.next = linkedListProgram.new Node(3);
		head.next.next = linkedListProgram.new Node(5);
		head.next.next.next = linkedListProgram.new Node(7);
		System.out.println(head + " " + head.next + " " + head.next.next + " "
				+ head.next.next.next);
		head = linkedListProgram.reverseLinkedList(head);
		System.out.println("after reversing :: ");
		System.out.println(head + " " + head.next + " " + head.next.next + " "
				+ head.next.next.next);
	}

	/**
	 * Implement an algorithm to nd the nth to last element of a singly linked
	 * list
	 * 
	 * Assumption: The minimum number of nodes in list is n Algorithm: 1 2 3 4
	 * Create two pointers, p1 and p2, that point to the beginning of the node
	 * Increment p2 by n-1 positions, to make it point to the nth node from the
	 * beginning (to make the distance of n between p1 and p2) Check for
	 * p2->next == null if yes return value of p1, otherwise increment p1 and p2
	 * If next of p2 is null it means p1 points to the nth node from the last as
	 * the distance between the two is n Repeat Step 3
	 * 
	 * @param node
	 * @param n
	 * @return
	 */
	Node nthToLast(Node node, int n) {
		if (node == null || n < 1) {
			return null;
		}
		Node p1 = node;
		Node p2 = node;
		for (int j = 0; j < n - 1; ++j) { // skip n-1 steps ahead
			if (p2 == null) {
				return null; // not found since list size < n
			}
			p2 = p2.next;
		}

		while (p2.next != null) {
			p1 = p1.next;
			p2 = p2.next;
		}
		return p1;

	}

	/*
	 * Given a circular linked list, implement an algorithm which returns node
	 * at the begin- ning of the loop DEFINITION Circular linked list: A
	 * (corrupt) linked list in which a node’s next pointer points to an earlier
	 * node, so as to make a loop in the linked list EXAMPLE Input: A -> B -> C
	 * -> D -> E -> C [the same C as earlier] Output: C
	 * 
	 * SOLUTION
	 * 
	 * If we move two pointers, one with speed 1 and another with speed 2, they
	 * will end up meet- ing if the linked list has a loop Why? Think about two
	 * cars driving on a track—the faster car will always pass the slower one!
	 * The tricky part here is nding the start of the loop Imagine, as an
	 * analogy, two people rac- ing around a track, one running twice as fast as
	 * the other If they start o at the same place, when will they next meet?
	 * They will next meet at the start of the next lap Now, let’s suppose Fast
	 * Runner had a head start of k meters on an n step lap When will they next
	 * meet? They will meet k meters before the start of the next lap (Why? Fast
	 * Runner would have made k + 2(n - k) steps, including its head start, and
	 * Slow Runner would have made n - k steps Both will be k steps before the
	 * start of the loop ) Now, going back to the problem, when Fast Runner (n2)
	 * and Slow Runner (n1) are moving around our circular linked list, n2 will
	 * have a head start on the loop when n1 enters Speci - cally, it will have
	 * a head start of k, where k is the number of nodes before the loop Since
	 * n2 has a head start of k nodes, n1 and n2 will meet k nodes before the
	 * start of the loop So, we now know the following: 1 Head is k nodes from
	 * LoopStart (by de nition) 2 MeetingPoint for n1 and n2 is k nodes from
	 * LoopStart (as shown above) Thus, if we move n1 back to Head and keep n2
	 * at MeetingPoint, and move them both at the same pace, they will meet at
	 * LoopStart
	 * 
	 * @param node
	 */
	void detectLoop(Node node) {
		Node node1 = node.next.next;
		Node node2 = node.next;
		while (true) {
			if (!((node1 != null) && (node1.next != null) && (node2 != null)
					&& (node2.next != null))) {
				System.out.println("There is no loop");
				return;
			}
			node1 = node1.next.next;
			node2 = node2.next;
			System.out.println(node1 + " : " + node2);
			if (node1 == node2) {
				System.out.println(" There is a loop");
				break;
			}

		}
		while (true) {
			node1 = node1.next;
			node2 = node2.next;
			if (node1.key == node2.key) {
				System.out.println("starting point of loop : " + node1.key);
				break;
			}
		}

	}

	private Node reverseLinkedList(Node node) {
		if (node == null) {
			return null;
		}
		Node next = null, previous = null;
		while (true) {
			next = node.next;
			node.next = previous;
			previous = node;
			if (next == null)
				break;
			node = next;
		}
		return previous;
	}

	class Node {
		int key;
		Node previous, next;

		public Node(int item) {
			key = item;
			next = previous = null;
		}

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "" + key;
		}
	}
}
