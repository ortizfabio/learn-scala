package com.bytetrend.sandbox.java.hackerrank.btree;

import java.util.*;

class Node {
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
}

/**
 * https://www.hackerrank.com/challenges/30-binary-search-trees/problem
 */
public class BinarySearchTreeHeight {


    public static int getHeight(Node root) {
        //Write your code here
        if (root == null)
            return 0;
        else
            return Math.max(root.left == null ? 0 : 1 + getHeight(root.left), root.right == null ? 0 : 1 + getHeight(root.right));
    }

    public static Node insert(Node root, int data) {
        if (root == null) {
            return new Node(data);
        } else {
            Node cur;
            if (data <= root.data) {
                cur = insert(root.left, data);
                root.left = cur;
            } else {
                cur = insert(root.right, data);
                root.right = cur;
            }
            return root;
        }
    }

    public static void main(String args[]) {
        String input = "423176";
        int T = 0;
        Node root = null;
        while (T < input.length()) {
            int data = input.charAt(T);
            root = insert(root, data);
            T++;
        }
        int height = getHeight(root);
        System.out.println(height);
    }
}
