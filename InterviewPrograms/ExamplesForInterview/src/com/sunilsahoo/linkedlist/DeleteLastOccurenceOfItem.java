package com.sunilsahoo.linkedlist;

public class DeleteLastOccurenceOfItem {
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
        node10.data = 9;
        node9.next = node10;

        Node node11 = new Node();
        node11.data = 10;
        node10.next = node11;

        Node head = node5;
        DeleteLastOccurenceOfItem example = new DeleteLastOccurenceOfItem();
        Node result = example.delete(head, 9);
        LinkedListUtils.print(result);
    }

    private Node delete(Node node, int item){
        Node fast = node;
        Node slow = null;
        Node match = null;
        Node prev = null;
        while (true){
            if (fast.data == item){
                match = fast;
                prev = slow;
            }
            fast = fast.next;
            if (fast == null){
                break;
            }
            if (slow == null){
                slow = node;
            }else {
                slow = slow.next;
            }
        }
        if (match != null){
            if (prev != null){
                prev.next = match.next;
            }else {
                return node.next;
            }
        }
        return node;

    }
}
