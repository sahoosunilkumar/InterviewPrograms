package com.sunilsahoo.linkedlist;

public class MiddleOfLinkedList {

    public static void main(String[] args){
        Node node5 = new Node();
        node5.data = 5;

        Node node6 = new Node();
        node6.data = 6;
        node5.next = node6;

        Node node7 = new Node();
        node7.data = 7;
        node6.next = node7;

        Node node8 = new Node();
        node8.data = 8;
        node7.next = node8;

        Node node9 = new Node();
        node9.data = 9;
        node8.next = node9;

        Node node10 = new Node();
        node10.data = 10;
        node9.next = node10;

        Node head = node5;

        MiddleOfLinkedList example = new MiddleOfLinkedList();
        head = example.findMiddle(head);
        System.out.println(head.data);
    }

    private Node findMiddle(Node node){
        Node fast = node;
        Node slow = node;
        while (fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
}
