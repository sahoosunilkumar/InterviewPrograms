package com.sunilsahoo.linkedlist;

public class FindNthNodeFromLastExample {
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
        FindNthNodeFromLastExample example = new FindNthNodeFromLastExample();
        Node result = example.find(node5, 2);
        System.out.println("====");
        System.out.println(result.data);
    }

    private Node find(Node node, int n){
        Node slow = node;
        Node fast = node;

        while (n>0 && fast != null){
            fast = fast.next;
            n--;
        }
        while (fast != null){
            fast = fast.next;
            if (fast != null) {
                slow = slow.next;
            }
        }
        return slow;
    }
}
