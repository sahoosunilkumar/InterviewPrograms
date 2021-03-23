package com.sunilsahoo.linkedlist;

public class RemoveDuplicateFromSortedLinkedListExample {
    public static void main(String[] args) {
        Node node5 = new Node();
        node5.data = 5;

        Node node6 = new Node();
        node6.data = 5;
        node5.next = node6;

        Node node7 = new Node();
        node7.data = 6;
        node6.next = node7;

        Node node8 = new Node();
        node8.data = 6;
        node7.next = node8;

        Node node9 = new Node();
        node9.data = 7;
        node8.next = node9;

        Node head = node5;

        RemoveDuplicateFromSortedLinkedListExample example = new RemoveDuplicateFromSortedLinkedListExample();
        head = example.remove(head);
        LinkedListUtils.print(head);
    }

    private Node remove(Node node){
        if (node == null){
            return null;
        }

        Node slow = null;
        Node head = null;
        Node fast = node.next;
        while (fast != null){
            while (fast != null && fast.next != null && fast.data == fast.next.data){
                fast = fast.next;
            }
            if (slow != null) {
                slow.next = fast;
            }
            slow = fast;
            if (head == null){
                head = slow;
            }
            System.out.println(fast.data);
            fast = fast.next;
        }
        return head;
    }
}
