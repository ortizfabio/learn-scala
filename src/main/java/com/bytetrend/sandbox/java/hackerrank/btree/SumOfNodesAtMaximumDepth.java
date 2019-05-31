package com.bytetrend.sandbox.java.hackerrank.btree;


/**
 * https://www.geeksforgeeks.org/sum-of-nodes-at-maximum-depth-of-a-binary-tree-set-2/
 */
public class SumOfNodesAtMaximumDepth {

    private static int total, max_level = 0;

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

    public static void reverse(int[] a) {
        for (int i = 0; i < (a.length / 2); i++) {
            int t = a[a.length - 1 - i];
            a[a.length - 1 - i] = a[i];
            a[i] = t;
        }
    }


    public static void printTree(int[] a) {
        VisualizeTreeTree bt = new VisualizeTreeTree();
        for (int i = 0; i < a.length; i++) {
            bt.add(a[i]);
        }
        bt.show(bt.getRoot());
    }

    public static void nodeSum(Node node, int level) {
        if (node == null)
            return;
        if (level > max_level) {
            max_level = level;
            total = node.data;
        } else if (level == max_level) {
            total += node.data;
        }

        nodeSum(node.left,level+1);
        nodeSum(node.right, level+1);
    }

    public static void main(String[] args) {
        int[] input = new int[]{9, 7, 8, 5, 6, 4, 3, 1};
        printTree(input);
        int t = 0;
        Node root = null;
        while (t < input.length) {
            int data = input[t];
            root = insert(root, data);
            t++;
        }
        max_level= 0;
        total = 0;
        nodeSum(root, 0);
        System.out.println(String.format("%d == 1 %b", total, 1 == total));

        input = new int[]{8, 4, 9, 1, 2, 3, 6, 5};
        printTree(input);
        t = 0;
        root = null;
        while (t < input.length) {
            int data = input[t];
            root = insert(root, data);
            t++;
        }
        max_level= 0;
        total = 0;
        nodeSum(root, 0);
        System.out.println(String.format("%d == 3 %b", total, 3 == total));

        input = new int[]{3, 1, 5, 8, 2, 4, 7, 6, 9, 0};
        printTree(input);
        t = 0;
        root = null;
        while (t < input.length) {
            int data = input[t];
            root = insert(root, data);
            t++;
        }
        max_level= 0;
        total = 0;
        nodeSum(root, 0);
        System.out.println(String.format("%d == 6 %b", total, 6 == total));

        input = new int[]{4, 2, 3, 1, 7, 6};
        printTree(input);
        t = 0;
        root = null;
        while (t < input.length) {
            int data = input[t];
            root = insert(root, data);
            t++;
        }
        max_level= 0;
        total = 0;
        nodeSum(root, 0);
        System.out.println(String.format("%d == 10 %b", total, 10 == total));

        reverse(input);
        printTree(input);
        t = 0;
        root = null;
        while (t < input.length) {
            int data = input[t];
            root = insert(root, data);
            t++;
        }
        max_level= 0;
        total = 0;
        nodeSum(root, 0);
        System.out.println(String.format("%d == 6 %b", total, 6 == total));

        input = new int[]{1, 2};
        printTree(input);
        t = 0;
        root = null;
        while (t < input.length) {
            int data = input[t];
            root = insert(root, data);
            t++;
        }
        max_level= 0;
        total = 0;
        nodeSum(root, 0);
        System.out.println(String.format("%d == 2 %b", total, 2 == total));

        input = new int[]{5, 3, 8, 2, 4, 6, 7};
        printTree(input);
        t = 0;
        root = null;
        while (t < input.length) {
            int data = input[t];
            root = insert(root, data);
            t++;
        }
        max_level= 0;
        total = 0;
        nodeSum(root, 0);
        System.out.println(String.format("%d == 7 %b", total, 7 == total));

        reverse(input);
        printTree(input);
        t = 0;
        root = null;
        while (t < input.length) {
            int data = input[t];
            root = insert(root, data);
            t++;
        }
        max_level= 0;
        total = 0;
        nodeSum(root, 0);
        System.out.println(String.format("%d == 3 %b", total, 3 == total));


        input = new int[]{8, 4, 2, 1, 3, 6, 5, 7, 10, 14, 15, 9, 12, 11, 13};
        printTree(input);
        t = 0;
        root = null;
        while (t < input.length) {
            int data = input[t];
            root = insert(root, data);
            t++;
        }
        max_level= 0;
        total = 0;
        nodeSum(root, 0);
        System.out.println(String.format("%d == 24 %b", total, 24 == total));

        reverse(input);
        printTree(input);
        t = 0;
        root = null;
        while (t < input.length) {
            int data = input[t];
            root = insert(root, data);
            t++;
        }
        max_level= 0;
        total = 0;
        nodeSum(root, 0);
        System.out.println(String.format("%d == 2 %b", total, 2 == total));

        input = new int[]{3, 4, 5, 9, 7, 8, 6, 1};
        printTree(input);
        t = 0;
        root = null;
        while (t < input.length) {
            int data = input[t];
            root = insert(root, data);
            t++;
        }
        max_level= 0;
        total = 0;
        nodeSum(root, 0);
        System.out.println(String.format("%d == 14 %b", total, 14 == total));

        reverse(input);
        printTree(input);
        t = 0;
        root = null;
        while (t < input.length) {
            int data = input[t];
            root = insert(root, data);
            t++;
        }
        max_level= 0;
        total = 0;
        nodeSum(root, 0);
        System.out.println(String.format("%d == 3 %b", total, 3 == total));

        input = new int[]{5, 2, 7, 6, 4, 3, 1, 9, 8};
        printTree(input);
        t = 0;
        root = null;
        while (t < input.length) {
            int data = input[t];
            root = insert(root, data);
            t++;
        }
        max_level= 0;
        total = 0;
        nodeSum(root, 0);
        System.out.println(String.format("%d == 11 %b", total, 11 == total));

    }
}