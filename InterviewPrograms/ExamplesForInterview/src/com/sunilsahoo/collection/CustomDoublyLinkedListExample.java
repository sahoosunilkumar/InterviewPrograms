package com.sunilsahoo.collection;

/**
 *Exception to indicate that Doubly LinkedList is empty.
 */
class LinkedListEmptyException extends RuntimeException{
       public LinkedListEmptyException(){
         super();
       }
      
     public LinkedListEmptyException(String message){
         super(message);
       }  
}
 
/**
 *Node class, which holds data and contains next which points to next Node.
 */
class Node {
    public int data; // data in Node.
    public Node next; // points to next Node in list.
    public Node previous; // points to previous Node in list.
 
    /**
     * Constructor
     */
    public Node(int data){
           this.data = data;
    }
 
    /**
     * Display Node's data
     */
    public void displayNode() {
           System.out.print( data + " ");
    }
}
 
 
/**
 * Doubly LinkedList class
 */
class LinkedList {
    private Node first; // ref to first link on LinkedList
    private Node last; // ref to last link on LinkedList
 
    /**
     * Doubly LinkedList constructor
     */
    public LinkedList(){
           first = null;
    }
  
    
    /**
     * Insert New Node at first position of Doubly LinkedList
     */
    
    public void insertFirst(int data){ // insert at front of list
           Node newNode = new Node(data); // creation of new node.
           if (first == null) // means LinkedList is empty.
                  last = newNode; //  newNode <--- last
           else
                  first.previous = newNode; // newNode <-- old first
           newNode.next = first; // newNode --> old first
           first = newNode; // first --> newNode
    }
 
    /**
     * Delete first node of Doubly linkedList.
     */
    public Node deleteFirst() { 
                
           if(first==null){  //means LinkedList in empty, throw exception.              
                  throw new LinkedListEmptyException("LinkedList doesn't contain any Nodes.");
           }

           Node tempNode = first;
           if (first.next == null) // if only one item
                  last = null; // null <-- last
           else
                  first.next.previous = null; // null <-- old next
           first = first.next; // first --> old next
           return tempNode;
    }
 
    
    /*
     * Display Doubly LinkedList in forward direction
     */
    public void displayFrwd() {
           System.out.print("Displaying in forward direction [first--->last] : ");
           Node tempDisplay = first; // start at the beginning of linkedList
           while (tempDisplay != null){ // Executes until we don't find end of list.
                  tempDisplay.displayNode();
                  tempDisplay = tempDisplay.next; // move to next Node
           }
           System.out.println("");
    }
 
 
    /*
     * Display Doubly LinkedList in backward direction
     */
    public void displayBckwrd() {
           System.out.print("Displaying in backward direction [last-->first] : ");
           Node tempDisplay = last; // start at the end of linkedList
           while (tempDisplay != null) {// Executes until we don't find start of list.    
                  tempDisplay.displayNode();
                  tempDisplay = tempDisplay.previous; // move to previous Node
           }
           System.out.println("");
    }
    
 
}
 
 
  
  
/** Copyright (c), AnkitMittal www.JavaMadeSoEasy.com */ 
/**
 * DoublyLinkedListInsertDeleteAtFirstExample - Main class - To test Doubly LinkedList.
 */
public class CustomDoublyLinkedListExample {
    public static void main(String[] args) {
           LinkedList linkedList = new LinkedList(); // creation of Linked List
           
           linkedList.insertFirst(11);
           linkedList.insertFirst(21);
           linkedList.insertFirst(59);
           linkedList.insertFirst(14);
           linkedList.insertFirst(39);
 
           linkedList.displayFrwd(); // display DoublyLinkedList
           linkedList.displayBckwrd();
                        
           System.out.print("Deleted Nodes: ");
           Node deletedNode = linkedList.deleteFirst(); //delete Node
           deletedNode.displayNode();                                 //display deleted Node.
           deletedNode = linkedList.deleteFirst();      //delete Node.
           deletedNode.displayNode();                                 //display deleted Node.
           
           System.out.println();// sysout used to format output
           
           linkedList.displayFrwd(); // display DoublyLinkedList
           linkedList.displayBckwrd();
           
           
 
    }
}
 
