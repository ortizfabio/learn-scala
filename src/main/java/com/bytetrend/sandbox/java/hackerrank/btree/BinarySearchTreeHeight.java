package com.bytetrend.sandbox.java.hackerrank.btree;

import java.util.*;


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
        int[] input = new int[]{4, 2, 3, 1, 7, 6};
        VisualizeTree bt = new VisualizeTree(input);
        Node root = bt.getRoot();
        int height = getHeight(root);
        System.out.println(height);

        input = new int[]{8, 4, 9, 1, 2, 3, 6, 5};
        bt = new VisualizeTree(input);
        root = bt.getRoot();
        height = getHeight(root);
        System.out.println(height);


    }
}
