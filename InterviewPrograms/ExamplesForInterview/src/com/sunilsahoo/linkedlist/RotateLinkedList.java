package com.sunilsahoo.linkedlist;

public class RotateLinkedList {
    public static void main(String[] args) {
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
        RotateLinkedList example = new RotateLinkedList();
        Node result = example.rotate(head, 3);
        LinkedListUtils.print(result);
    }

    private Node rotate(Node node, int k){
        Node fast = node;
        Node head = fast;
        Node slow = node;
        Node prev = null;
        while (k>0){
            prev = fast;
            fast = fast.next;
            k--;
        }
        prev.next = null;
        head = fast;
        while (fast.next != null){
            fast = fast.next;
        }
        fast.next = slow;
        return head;
    }
}
