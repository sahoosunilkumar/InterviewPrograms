package com.sunilsahoo.linkedlist;

/**
 * Number is represented in linked list such that each digit corresponds to a node in linked list. Add 1 to it.
 * For example 1999 is represented as (1-> 9-> 9 -> 9) and adding 1 to it should change it to (2->0->0->0)
 */
public class Add1Example {
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

        Node head = node5;
        Add1Example example = new Add1Example();
        Node result = example.add(node5);
        LinkedListUtils.print(result);
    }

    private Node add(Node node) {
        Node result = reverse(node);
        Node newResult = result;
        int carry = 1;
        while (true){
            int sum = result.data+carry;
            carry = 0;
            if (sum>9){
                carry = 1;
                result.data = 0;
            }else {
                result.data = sum;
            }
            if (carry != 0){
                result = result.next;
            }else {
                break;
            }
        }
        return reverse(newResult);

    }


    private Node reverse(Node node){
        Node next = null;
        Node prev = null;
        while (node != null) {
            next = node.next;
            node.next = prev;
            prev = node;
            node = next;
            if (node == null){
                return prev;
            }
        }
        return null;

    }
}
