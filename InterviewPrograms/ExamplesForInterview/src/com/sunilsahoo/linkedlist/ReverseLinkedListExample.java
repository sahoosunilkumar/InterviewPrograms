package com.sunilsahoo.linkedlist;

public class ReverseLinkedListExample {
    public static void main(String args[]){
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

        ReverseLinkedListExample example = new ReverseLinkedListExample();
        LinkedListUtils.print(head);
        System.out.println("===== AFTER CHANGES =====");
        Node newHead = example.reverse(head);
        LinkedListUtils.print(newHead);

    }

    // 5 6 7 8 9 10
//    node next = pev
//    node = node.next
//    make head = when node.next = null
//    until node = null
    private Node reverse(Node node) {
        Node prev = null;
        Node next = null;
        Node head = null;
        while (node != null){
            next = node.next;
            node.next = prev;
            prev = node;
            node = next;
            if (node == null){
                head = prev;
            }

        }
        return head;
    }
}
