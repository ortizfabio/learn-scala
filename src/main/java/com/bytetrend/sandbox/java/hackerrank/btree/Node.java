package com.bytetrend.sandbox.java.hackerrank.btree;


public class Node {
    Node left, right;
    int data;

    Node(int data) {
        this.data = data;
        left = right = null;
    }

    @Override
    public String toString() {
        return "Node{" +
                "left=" + (left == null ? "null" : left.data) +
                ", right=" + (right == null ? "null" : right.data) +
                ", data=" + data +
                '}';
    }
    static void print(Node l)
    {
        if (l != null) {
            print(l.left);
            System.out.println(l);
            print(l.right);
        }
    }



    public static Node insert(Node root, int data) {
        if (root == null) {
            root = new Node(data);
        } else {
            if (data < root.data) {
                if (root.left == null) {
                    Node n = new Node(data);
                    root.left = n;
                } else {
                    insert(root.left, data);
                }
            } else {
                if (root.right == null) {
                    Node n = new Node(data);
                    root.right = n;
                } else {
                    insert(root.right, data);
                }
            }
        }
        return root;
    }
}
