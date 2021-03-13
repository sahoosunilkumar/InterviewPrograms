package com.sunilsahoo.linkedlist;

public class DetectCycleExample {
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

        DetectCycleExample example = new DetectCycleExample();
        boolean isCycleExist = example.execute(head);
        System.out.println(isCycleExist);

        node10.next = node8;
        isCycleExist = example.execute(head);
        System.out.println(isCycleExist);


    }

    private boolean execute(Node node) {
        Node fastNode = node;
        Node slowNode = node;

        while (true){
            if (fastNode == null || fastNode.next == null || fastNode.next.next == null){
                return false;
            }
            fastNode = fastNode.next.next;
            slowNode = slowNode.next;
            if (fastNode == slowNode){
                return true;
            }

        }
    }
}
