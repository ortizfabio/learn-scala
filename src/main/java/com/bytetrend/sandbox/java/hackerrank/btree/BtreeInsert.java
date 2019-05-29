package com.bytetrend.sandbox.java.hackerrank.btree;

/**
 * https://www.hackerrank.com/challenges/binary-search-tree-insertion/problem?h_r=internal-search
 */
public class BtreeInsert {


    public static void preOrder(Node root) {
        if (root == null)
            return;
        System.out.print((char) root.data + " ");
        preOrder(root.left);
        preOrder(root.right);
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


    public static void main(String[] args) {
        String input = "423176";
        int t = 0;
        Node root = null;
        while (t < input.length()) {
            int data = input.charAt(t);
            root = insert(root, data);
            t++;
        }
        preOrder(root);

        System.out.print("\n" + "4 2 1 3 7 6");
    }
}
