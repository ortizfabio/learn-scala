package com.bytetrend.sandbox.java.hackerrank.node;

/**
 * https://www.hackerrank.com/challenges/merge-two-sorted-linked-lists/problem
 * <p>
 * You’re given the pointer to the head nodes of two sorted linked lists. The data in both lists will be sorted
 * in ascending order. Change the next pointers to obtain a single, merged linked list which also has data
 * in ascending order. Either head pointer given may be null meaning that the corresponding list is empty.
 * <p>
 * Input Format
 * You have to complete the Node* MergeLists(Node* headA, Node* headB) method which takes two
 * arguments - the heads of the two sorted linked lists to merge. You should NOT read any input from
 * stdin/console.
 * <p>
 * Output Format
 * Change the next pointer of individual nodes so that nodes from both lists are merged into a single list.
 * Then return the head of this merged list. Do NOT print anything to stdout/console.
 * <p>
 * Sample Input
 * <p>
 * 1 -> 3 -> 5 -> 6 -> NULL
 * 2 -> 4 -> 7 -> NULL
 * <p>
 * 15 -> NULL
 * 12 -> NULL
 * <p>
 * NULL
 * 1 -> 2 -> NULL
 * Sample Output
 * <p>
 * 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> NULL
 * 12 -> 15 -> NULL
 * 1 -> 2 -> NULL
 * Explanation
 * 1. We merge elements in both list in sorted order and output.
 */
public class MergeTwoSortedLinkedLists {

    static public Node mergeLists(Node headA, Node headB) {
        Node result = null; //Need a pointer to the first node that would not change.

        if (headA != null) { //Initialize the beginning node.
            if (headB != null && headB.data > headA.data) {
                result = new Node();
                result.data = headA.data;
                headA = headA.next;
            } else {
                result = new Node();
                result.data = headB.data;
                headB = headB.next;
            }
        } else {
            if (headB != null) {
                result = headB;
                headB = headB.next;
            }
        }
        //This is a moving pointer to the current element.
        //The result can't be used otherwise we loose the beginning node.
        Node current = result;
        //Check that there is at least one node that is not null
        while (headA != null || headB != null) {
            if (headA != null) {
                if (headB != null && headB.data < headA.data) {
                    //Add next node to current and initialize with b
                    current.next = new Node();
                    current.next.data = headB.data;
                    headB = headB.next;

                } else {
                    current.next = new Node();
                    current.next.data = headA.data;
                    headA = headA.next;
                }
            } else if (headB != null) {
                //Add next node to current
                current.next = new Node();
                current.next.data = headB.data;
                headB = headB.next;
            }
            current = current.next;
        }
        return result;
    }


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

    public static void main(String args[]) {

        // 1 -> 3 -> 5 -> 6 -> NULL
        // 2 -> 4 -> 7 -> NULL
        Node head1 = new Node(1, new Node(3, new Node(5, new Node(6, null))));
        Node head2 = new Node(2, new Node(4, new Node(7, null)));
        System.out.println(mergeLists(head1, head2));

        head1 = new Node(15, null);
        head2 = new Node(12, null);
        System.out.println(mergeLists(head1, head2));

        head1 = null;
        head2 = new Node(1, new Node(2, null));
        System.out.println(mergeLists(head1, head2));

        head1 = null;
        head2 = null;
        System.out.println(mergeLists(head1, head2));

    }

}
