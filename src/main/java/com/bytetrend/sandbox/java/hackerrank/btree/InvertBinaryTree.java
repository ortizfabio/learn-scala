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

    public static void invertBT(Node root) {
        if (root == null)
            return;

        Node tmp = root.left;
        root.left = root.right;
        root.right = tmp;
        invertBT(root.left);
        invertBT(root.right);

    }


    public static void main(String[] args) {
        int[] input = new int[]{9, 7, 8, 5, 6, 4, 3, 1};
        VisualizeTree bt = new VisualizeTree(input);
        int t = 0;
        Node root = bt.getRoot();
        invertBT(root);
        bt.show(root);

        input = new int[]{8, 4, 9, 1, 2, 3, 6, 5};
        bt = new VisualizeTree(input);
        t = 0;
        root = bt.getRoot();
        invertBT(root);
        bt.show(root);

        input = new int[]{3, 1, 5, 8, 2, 4, 7, 6, 9, 0};
        bt = new VisualizeTree(input);
        t = 0;
        root = bt.getRoot();
        invertBT(root);
        bt.show(root);

        input = new int[]{4, 2, 3, 1, 7, 6};
        bt = new VisualizeTree(input);
        t = 0;
        root = bt.getRoot();
        invertBT(root);
        bt.show(root);

        reverse(input);
        bt = new VisualizeTree(input);
        t = 0;
        root = bt.getRoot();
        invertBT(root);
        bt.show(root);

        input = new int[]{1, 2};
        bt = new VisualizeTree(input);
        t = 0;
        root = bt.getRoot();
        invertBT(root);
        bt.show(root);

        input = new int[]{5, 3, 8, 2, 4, 6, 7};
        bt = new VisualizeTree(input);
        t = 0;
        root = bt.getRoot();
        invertBT(root);
        bt.show(root);

        reverse(input);
        bt = new VisualizeTree(input);
        t = 0;
        root = bt.getRoot();
        invertBT(root);
        bt.show(root);

        input = new int[]{8, 4, 2, 1, 3, 6, 5, 7, 10, 14, 15, 9, 12, 11, 13};
        bt = new VisualizeTree(input);
        t = 0;
        root = bt.getRoot();
        invertBT(root);
        bt.show(root);

        reverse(input);
        bt = new VisualizeTree(input);
        t = 0;
        root = bt.getRoot();
        invertBT(root);
        bt.show(root);

        input = new int[]{3, 4, 5, 9, 7, 8, 6, 1};
        bt = new VisualizeTree(input);
        t = 0;
        root = bt.getRoot();
        invertBT(root);
        bt.show(root);

        reverse(input);
        bt = new VisualizeTree(input);
        t = 0;
        root = bt.getRoot();
        invertBT(root);
        bt.show(root);

        input = new int[]{5, 2, 7, 1, 6, 4, 3, 1, 9, 8};
        bt = new VisualizeTree(input);
        t = 0;
        root = bt.getRoot();
        invertBT(root);
        bt.show(root);

    }
}