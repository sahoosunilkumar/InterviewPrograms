package com.sunilsahoo.linkedlist;

public class LinkedListUtils {
    private LinkedListUtils() {

    }

    public static void print(Node node){
        Node head = node;
        while (head != null){
            System.out.print(""+head.data+",");
            head = head.next;
        }
    }
}
