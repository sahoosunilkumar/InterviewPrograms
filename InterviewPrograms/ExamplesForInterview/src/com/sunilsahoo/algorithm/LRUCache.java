package com.sunilsahoo.algorithm;

import java.util.HashMap;

/**
 * LRU Cache is designed for finding the location of desired page on disk.It
 * maintains a cache of page number and its location on disk.Whenever there is a
 * request for new page number,Following operation will be performed.–If it is
 * present in cache,it is moved to front of the list and location is
 * returned.–If it is not present,a new page mapping is done.If cache is not
 * full,a new entry is added to front otherwise least recently used entry is
 * removed and then a new entry to front is added..It should support two main
 * operation.set(int key,int value)-To add page number and disk location entry
 * in cache.If cache is full,it should discard least recently used entry and add
 * new one.get(int key)–Return location on disk for given page number else
 * return-1. If entry is present,reorder it to front.Data Structures Used for
 * LRU Cache Implementation DoublyLinkedList We will use DoublyLinkedList for
 * maintaining LRU Cache queue.Least recently used item will be present at end
 * of list and latest used item will be present in front of list.Size of list
 * will be equal to capacity of cache.HashMap
 * 
 * We will use HashMap for efficient reordering and removal of least recently
 * used items.Key will be pageNumber and Value will be DoublyLinkedList node.
 * 
 **/
public class LRUCache {
	// Capacity of LRUCache
	private int capacity;
	// Current size of LRUCache
	private int currentSize;

	// To represent Node of DoublyLinkedList to maintain FIFO operations
	class DoublyLinkedListNode {
		DoublyLinkedListNode prev;
		DoublyLinkedListNode next;
		int key;
		int value;

		public DoublyLinkedListNode(int key, int value) {
			this.key = key;
			this.value = value;
		}
	}

	// First node of DoublyLinkedList
	private DoublyLinkedListNode start;
	// Last node of DoublyLinkedList
	private DoublyLinkedListNode end;
	// Map for key and DoublyLinkedList node mapping
	private HashMap<Integer, DoublyLinkedListNode> nodeMap;

	public LRUCache(int capacity) {
		this.capacity = capacity;
		currentSize = 0;
		nodeMap = new HashMap<Integer, DoublyLinkedListNode>();
	}

	/* To print all current items in LRUCache */
	private void printLRUCache() {
		DoublyLinkedListNode traverseNode = start;
		while (traverseNode != null) {
			System.out.print("key " + traverseNode.key + " value "
					+ traverseNode.value + " ");
			traverseNode = traverseNode.next;
		}
		System.out.println();
	}

	/* To add item in LRUCache */
	private void set(int key, int value) {
		if (nodeMap.containsKey(key)) {
			DoublyLinkedListNode node = nodeMap.get(key);
			node.value = value;
			bringToFront(node);
		} else {
			DoublyLinkedListNode insertNode = new DoublyLinkedListNode(key,
					value);
			if (currentSize < capacity) {
				addToFront(insertNode);
				currentSize++;
			} else {
				removeLastNode();
				addToFront(insertNode);
			}
		}
	}

	/* To get item value in LRUCache */
	private int get(int key) {
		if (nodeMap.containsKey(key)) {
			DoublyLinkedListNode node = nodeMap.get(key);
			bringToFront(node);
			return node.value;
		} else {
			return -1;
		}
	}

	/* To remove last node from queue */
	private void removeLastNode() {
		DoublyLinkedListNode lastNode = nodeMap.remove(end.key);
		end = end.prev;
		if (end != null)
			end.next = null;
		lastNode = null; // make it eligible for GC
	}

	/* To add node in front of queue */
	private void addToFront(DoublyLinkedListNode insertNode) {
		insertNode.next = start;
		insertNode.prev = null;
		if (start != null)
			start.prev = insertNode;
		start = insertNode;
		if (end == null)
			end = insertNode;
		nodeMap.put(insertNode.key, insertNode);
	}

	/* To reorder existing node to front of queue */
	private void bringToFront(DoublyLinkedListNode node) {
		// detach node from list
		DoublyLinkedListNode prevNode = node.prev;
		DoublyLinkedListNode nextNode = node.next;
		// handle next node
		if (prevNode != null)
			prevNode.next = nextNode;
		else
			start = nextNode;
		// hanlde prev node
		if (nextNode != null)
			nextNode.prev = prevNode;
		else
			end = prevNode;
		// add to front of ist
		addToFront(node);
	}

	public static void main(String[] args) {
		LRUCache lru = new LRUCache(5);
		for (int i = 5; i < 11; i++) {
			lru.set(i, i * 2);
		}
		System.out.println("LRU Cache after creation");
		lru.printLRUCache();
		lru.get(7);
		System.out.println("LRU Cache after retrieving 7");
		lru.printLRUCache();
		lru.set(11, 11 * 2);
		System.out.println(
				"LRU cache on adding one more item. It will replace last one");
		lru.printLRUCache();
	}

}
