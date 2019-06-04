package com.bytetrend.sandbox.java.hackerrank.btree;


/**
 * https://www.geeksforgeeks.org/flatten-a-binary-tree-into-linked-list-set-3/
 */
public class FlattenBinaryTree {


    public static void reverse(int[] a) {
        for (int i = 0; i < (a.length / 2); i++) {
            int t = a[a.length - 1 - i];
            a[a.length - 1 - i] = a[i];
            a[i] = t;
        }
    }

    static Node last = null;

    public static void flatten(Node node) {
        if (node != null) {
            Node left = node.left;
            Node right = node.right;
            if (left != null) {
                node.right = left;
                last = left;
                node.left = null;
                flatten(left);
            }
            if (right != null) {
                last.right = right;
                last = right;
                flatten(right);
            }
        }
    }

    public static void main(String[] args) {
        int[] input = new int[]{9, 7, 8, 5, 6, 4, 3, 1};
        VisualizeTree bt = new VisualizeTree(input);
        Node root = bt.getRoot();
        flatten(root);
        bt.show(root);
        input = new int[]{8, 4, 9, 1, 2, 3, 6, 5};
        bt = new VisualizeTree(input);

        root = bt.getRoot();
        flatten(root);
        bt.show(root);
        input = new int[]{3, 1, 5, 8, 2, 4, 7, 6, 9, 0};
        bt = new VisualizeTree(input);

        root = bt.getRoot();
        flatten(root);
        bt.show(root);
        input = new int[]{4, 2, 3, 1, 7, 6};
        bt = new VisualizeTree(input);

        root = bt.getRoot();
        flatten(root);
        bt.show(root);

        reverse(input);
        bt = new VisualizeTree(input);

        root = bt.getRoot();
        flatten(root);
        bt.show(root);

        input = new int[]{1, 2};
        bt = new VisualizeTree(input);

        root = bt.getRoot();
        flatten(root);
        bt.show(root);
        input = new int[]{5, 3, 8, 2, 4, 6, 7};
        bt = new VisualizeTree(input);

        root = bt.getRoot();
        flatten(root);
        bt.show(root);
        reverse(input);
        bt = new VisualizeTree(input);

        root = bt.getRoot();
        flatten(root);
        bt.show(root);
        input = new int[]{8, 4, 2, 1, 3, 6, 5, 7, 10, 14, 15, 9, 12, 11, 13};
        bt = new VisualizeTree(input);

        root = bt.getRoot();
        flatten(root);
        bt.show(root);
        reverse(input);
        bt = new VisualizeTree(input);

        root = bt.getRoot();
        flatten(root);
        bt.show(root);
        input = new int[]{3, 4, 5, 9, 7, 8, 6, 1};
        bt = new VisualizeTree(input);

        root = bt.getRoot();
        flatten(root);
        bt.show(root);
        reverse(input);
        bt = new VisualizeTree(input);

        root = bt.getRoot();
        flatten(root);
        bt.show(root);
        input = new int[]{5, 2, 7, 1, 6, 4, 3, 1, 9, 8};
        bt = new VisualizeTree(input);

        root = bt.getRoot();
        flatten(root);
        bt.show(root);
    }
}
