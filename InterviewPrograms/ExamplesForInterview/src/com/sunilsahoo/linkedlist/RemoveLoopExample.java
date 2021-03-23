package com.sunilsahoo.linkedlist;

public class RemoveLoopExample {
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

        RemoveLoopExample example = new RemoveLoopExample();
        Node meetNode = example.execute(head);
        LinkedListUtils.print(meetNode);
        System.out.println("=============");

        node10.next = node8;
        meetNode = example.execute(head);
        LinkedListUtils.print(meetNode);


    }

    private Node execute(Node node) {
        Node fastNode = node;
        Node slowNode = node;

        Node crossNode = null;

        while (true){
            if (fastNode == null || fastNode.next == null || fastNode.next.next == null){
                crossNode = null;
                break;
            }
            fastNode = fastNode.next.next;
            slowNode = slowNode.next;
            if (fastNode == slowNode){
                crossNode = slowNode;
                break;
            }
        }
        fastNode = node;
        slowNode = crossNode;
        Node prev = slowNode;
        if (crossNode == null){
            return node;
        }
        while (fastNode != slowNode){
            fastNode = fastNode.next;
            prev = slowNode;
            slowNode = slowNode.next;
        }
        prev.next = null;
        return node;
    }
}
