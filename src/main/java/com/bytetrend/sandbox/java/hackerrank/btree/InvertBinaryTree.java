package com.bytetrend.sandbox.java.hackerrank.btree;


/**
 * https://www.hackerrank.com/challenges/binary-search-tree-lowest-common-ancestor/problem?h_r=internal-search
 */
class InvertBinaryTree {




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

    public static void invertBT(Node root){
        if(root == null)
            return;

        Node tmp = root.left;
        root.left = root.right;
        root.right = tmp;
        invertBT(root.left);
        invertBT(root.right);

    }
    
    public static VisualizeTree printTree(int[] a) {
        VisualizeTree bt = new VisualizeTree();
        for (int i = 0; i < a.length; i++) {
            bt.add(a[i]);
        }
        bt.show(bt.getRoot());
        return bt;
    }

    public static void main(String[] args) {
        int[] input = new int[]{9, 7, 8, 5, 6, 4, 3, 1};
        VisualizeTree bt = printTree(input);
        int t = 0;
        Node root = null;
        while (t < input.length) {
            int data = input[t];
            root = insert(root, data);
            t++;
        }
        invertBT(root);
        bt.show(root);

        input = new int[]{8, 4 ,9, 1, 2 ,3 ,6 ,5};
        printTree(input);
        t = 0;
        root = null;
        while (t < input.length) {
            int data = input[t];
            root = insert(root, data);
            t++;
        }
        invertBT(root);
        bt.show(root);

        input = new int[]{3,1,5,8,2,4,7,6,9,0};
        printTree(input);
        t = 0;
        root = null;
        while (t < input.length) {
            int data = input[t];
            root = insert(root, data);
            t++;
        }
        invertBT(root);
        bt.show(root);

        input = new int[]{4, 2, 3, 1, 7, 6};
        printTree(input);
        t = 0;
        root = null;
        while (t < input.length) {
            int data = input[t];
            root = insert(root, data);
            t++;
        }
        invertBT(root);
        bt.show(root);

        reverse(input);
        printTree(input);
        t = 0;
        root = null;
        while (t < input.length) {
            int data = input[t];
            root = insert(root, data);
            t++;
        }
        invertBT(root);
        bt.show(root);

        input = new int[]{1, 2};
        printTree(input);
        t = 0;
        root = null;
        while (t < input.length) {
            int data = input[t];
            root = insert(root, data);
            t++;
        }
        invertBT(root);
        bt.show(root);

        input = new int[]{5, 3, 8, 2, 4, 6, 7};
        printTree(input);
        t = 0;
        root = null;
        while (t < input.length) {
            int data = input[t];
            root = insert(root, data);
            t++;
        }
        invertBT(root);
        bt.show(root);

        reverse(input);
        printTree(input);
        t = 0;
        root = null;
        while (t < input.length) {
            int data = input[t];
            root = insert(root, data);
            t++;
        }
        invertBT(root);
        bt.show(root);

        input = new int[]{8, 4, 2, 1, 3, 6, 5, 7, 10, 14, 15, 9, 12, 11, 13};
        printTree(input);
        t = 0;
        root = null;
        while (t < input.length) {
            int data = input[t];
            root = insert(root, data);
            t++;
        }
        invertBT(root);
        bt.show(root);

        reverse(input);
        printTree(input);
        t = 0;
        root = null;
        while (t < input.length) {
            int data = input[t];
            root = insert(root, data);
            t++;
        }
        invertBT(root);
        bt.show(root);

        input = new int[]{3, 4, 5, 9, 7, 8, 6, 1};
        printTree(input);
        t = 0;
        root = null;
        while (t < input.length) {
            int data = input[t];
            root = insert(root, data);
            t++;
        }
        invertBT(root);
        bt.show(root);

        reverse(input);
        printTree(input);
        t = 0;
        root = null;
        while (t < input.length) {
            int data = input[t];
            root = insert(root, data);
            t++;
        }
        invertBT(root);
        bt.show(root);

        input = new int[]{5, 2, 7, 1, 6, 4, 3, 1,9,8};
        printTree(input);
        t = 0;
        root = null;
        while (t < input.length) {
            int data = input[t];
            root = insert(root, data);
            t++;
        }
        invertBT(root);
        bt.show(root);

    }
}