package com.bytetrend.sandbox.java.algo;

public class ReverseLinkList {

    static class Node {
        int val;
        Node next = null;
        public Node(int i){
            val = i;
        }
        public Node(int i, Node n){
            val = i;
            next = n;
        }
    }

    public static void main(String[] args){
        Node head = new Node(1,new Node(2,new Node(3,new Node(4,null))));

        Node current = head.next;
        Node previous = head;
        previous.next = null;
        while(current != null){
            Node temp = current.next;
            current.next = previous;
            previous = current;
            current = temp;
        }
        head = previous;
        current = head;
        while(current != null){
            System.out.print(""+current.val+" ");
            current = current.next;
        }
    }
}
