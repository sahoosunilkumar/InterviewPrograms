package com.sunilsahoo.algorithm;

import java.util.LinkedList;
import java.util.Scanner;

/*

A Trie, also called a Prefix Tree, is a tree structure that stores words with a common prefix under the same sequence of edges in the tree eliminating the need for storing the same prefix each time for each word. From Wikipedia:
A trie, or prefix tree, is an ordered tree data structure that is used to store an associative array where the keys are usually strings. Unlike a binary search tree, no node in the tree stores the key associated with that node; instead, its position in the tree shows what key it is associated with. All the descendants of a node have a common prefix of the string associated with that node, and the root is associated with the empty string.
 */

public class TrieDataStructureForAutoSuggestion {

	public static int counter = 0;

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		/* Creating object of AATree */
		Trie t = new Trie();
		System.out.println("Trie Test\n");
		char ch;
		/* Perform tree operations */
		do {
			System.out.println("\nTrie Operations\n");
			System.out.println("1. insert ");
			System.out.println("2. delete");
			System.out.println("3. search");

			int choice = scan.nextInt();
			switch (choice) {
			case 1:
				System.out.println(
						"Enter comma separated string element to insert");
				String rawStr = scan.next();
				String[] strArr = rawStr.split(",");
				for (String str : strArr) {
					t.insert(str);
				}

				break;
			case 2:
				System.out.println("Enter string element to delete");
				try {
					t.remove(scan.next());
				} catch (Exception e) {
					System.out.println(e.getMessage() + " not found ");
				}
				break;
			case 3:
				counter = 0;
				System.out.println("Enter string element to search");
				System.out.println(counter + " Search result : "
						+ t.search(scan.next()) + " counter :" + counter);
				break;
			default:
				System.out.println("Wrong Entry \n ");
				break;
			}

			System.out.println("\nDo you want to continue (Type y or n) \n");
			ch = scan.next().charAt(0);
		} while (ch == 'Y' || ch == 'y');
	}

}

class TrieNode {
	char content;
	boolean isEnd;
	int count;
	LinkedList<TrieNode> childList;

	/* Constructor */
	public TrieNode(char c) {
		childList = new LinkedList<TrieNode>();
		isEnd = false;
		content = c;
		count = 0;
	}

	public TrieNode subNode(char c) {
		if (childList != null)
			for (TrieNode eachChild : childList) {
				TrieDataStructureForAutoSuggestion.counter++;
				if (eachChild.content == c)
					return eachChild;
			}

		return null;
	}
}

class Trie {
	private TrieNode root;

	/* Constructor */
	public Trie() {
		root = new TrieNode(' ');
	}

	/* Function to insert word */
	public void insert(String word) {
		if (search(word) == true)
			return;
		TrieNode current = root;
		for (char ch : word.toCharArray()) {
			TrieNode child = current.subNode(ch);
			if (child != null)
				current = child;
			else {
				current.childList.add(new TrieNode(ch));
				current = current.subNode(ch);
			}
			current.count++;
		}
		current.isEnd = true;
	}

	/* Function to search for word */
	public boolean search(String word) {
		TrieNode current = root;
		for (char ch : word.toCharArray()) {
			current = current.subNode(ch);
			if (current == null) {
				return false;
			}
		}
		if (current.isEnd == true)
			return true;
		return false;
	}

	/* Function to remove a word */
	public void remove(String word) {
		if (search(word) == false) {
			System.out.println(word + " does not exist in trie\n");
			return;
		}
		TrieNode current = root;
		for (char ch : word.toCharArray()) {
			TrieNode child = current.subNode(ch);
			if (child.count == 1) {
				current.childList.remove(child);
				return;
			} else {
				child.count--;
				current = child;
			}
		}
		current.isEnd = false;
	}
}
