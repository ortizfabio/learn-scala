package com.bytetrend.sandbox.java.hackerrank.btree;

public class VisualizeTree {
    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();

        tree.add(100);
        for (int i = 0; i < 20; i++)
            tree.add((int) (Math.random() * 200));
        tree.show();
    }
}

class BinarySearchTree {
    private Node root;

    private class Node {
        private int key;
        private Node left, right;

        Node(int k) {
            key = k;
        }
    }

    public BinarySearchTree add(int key) {
        if (root == null)
            root = new Node(key);
        else {
            Node n = root;
            Node parent;
            while (true) {
                if (n.key == key)
                    return this;

                parent = n;

                boolean goLeft = key < n.key;
                n = goLeft ? n.left : n.right;

                if (n == null) {
                    if (goLeft) {
                        parent.left = new Node(key);
                    } else {
                        parent.right = new Node(key);
                    }
                    break;
                }
            }
        }
        return this;
    }

    public void show() {
        final int height = 6, width = 64;

        int len = width * height * 2 + 2;
        StringBuilder sb = new StringBuilder(len);
        for (int i = 1; i <= len; i++)
            sb.append(i < len - 2 && i % width == 0 ? "\n" : ' ');

        displayR(sb, width / 2, 1, width / 4, width, root, " ");
        System.out.println(sb);
    }

    private void displayR(StringBuilder sb, int c, int r, int d, int w, Node n,
                          String edge) {
        if (n != null) {
            displayR(sb, c - d, r + 2, d / 2, w, n.left, " /");

            String s = String.valueOf(n.key);
            int idx1 = r * w + c - (s.length() + 1) / 2;
            int idx2 = idx1 + s.length();
            int idx3 = idx1 - w;
            if (idx2 < sb.length())
                sb.replace(idx1, idx2, s).replace(idx3, idx3 + 2, edge);

            displayR(sb, c + d, r + 2, d / 2, w, n.right, "\\ ");
        }
    }
}