package com.bytetrend.sandbox.java.hackerrank.node;

import scala.Array;

/**
 * https://www.hackerrank.com/challenges/get-the-value-of-the-node-at-a-specific-position-from-the-tail/problem
 * <p>
 * You’re given the pointer to the head node of a linked list and a specific position.
 * Counting backwards from the tail node of the linked list, get the value of the node at the given position.
 * A position of 0 corresponds to the tail, 1 corresponds to the node before the tail and so on.
 * <p>
 * Input Format
 * You have to complete the int GetNode(Node* head, int positionFromTail) method which takes
 * two arguments - the head of the linked list and the position of the node from the tail.
 * positionFromTail will be at least 0 and less than the number of nodes in the list.
 * You should NOT read any input from stdin/console.
 * <p>
 * Constraints
 * Position will be a valid element in linked list.
 * <p>
 * Output Format
 * Find the node at the given position counting backwards from the tail. Then return the data contained in this node. Do NOT print anything to stdout/console.
 * <p>
 * Sample Input
 * <p>
 * 1 -> 3 -> 5 -> 6 -> NULL, positionFromTail = 0
 * 1 -> 3 -> 5 -> 6 -> NULL, positionFromTail = 2
 * Sample Output
 * <p>
 * 6
 * 3
 */
public class CurrentBuffer {

    /*
  Get Nth element from the end in a linked list of integers
  Number of elements in the list will always be greater than N.
  Node is defined as */
    public static class Node {
        public Node() {

        }

        public Node(int d, Node n) {
            data = d;
            next = n;
        }

        int data;
        Node next;

        public String toString() {
            return "" + data + " -> " + (next == null ? "NULL" : next.toString());
        }

    }


    static int GetNode(Node head, int n) {
        // This is a "method-only" submission.
        // You only need to complete this method.
        int[] queue = new int[n+1];
        int last = 0;
        queue[last] = head.data;
        Node current = head.next;
        while (current != null) {
            last = (last + 1)==queue.length? 0:last+1;
            queue[last] = current.data;
            current = current.next;
        }
        int index = (last-n)>=0?(last-n):queue.length+(last-n);
        return queue[index];
    }

    public static void main(String args[]) {

        // 1 -> 3 -> 5 -> 6 -> NULL
        Node head1 = new Node(1, new Node(3, new Node(5, new Node(6, null))));
        System.out.println(GetNode(head1, 3));
        System.out.println(GetNode(head1, 0));
        System.out.println(GetNode(head1, 2));
    }
}
