package com.bytetrend.sandbox.java.hackerrank.btree;


import java.util.Arrays;

import static com.bytetrend.sandbox.java.hackerrank.btree.BinarySearchTreeHeight.getHeight;

class VisualizeTree {
    static int numWidth = 6;
    private Node root;

    public Node getRoot() {
        return root;
    }

    public VisualizeTree(int[] a) {
        for (int i = 0; i < a.length; i++) {
            add(a[i]);
        }
        show(this.getRoot());
    }

    public VisualizeTree add(int key) {
        if (root == null)
            root = new Node(key);
        else {
            Node n = root;
            Node parent;
            while (true) {
                if (n.data == key)
                    return this;

                parent = n;

                boolean goLeft = key < n.data;
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

    static public void show(Node root) {

        int height = getHeight(root);
        int width = Math.max(2, (int) Math.pow(height, 2));
        int linew = width * numWidth;
        int len = linew * (height + 1);

        char[] chars = new char[len+2];
        Arrays.fill(chars,'-');
        System.out.println(String.valueOf(chars).substring(0,linew));
        Arrays.fill(chars, ' ');
        StringBuilder sb = new StringBuilder(new String(chars));
        if (root != null)
            displayR(sb, 0, 0, linew - 1, linew, root);
        String l = sb.toString();
        for (int i = 0; i < len; i += linew)
            System.out.println(l.substring(i,i+linew)+"\n");

    }

    static private void displayR(StringBuilder sb, int level, int start, int end, int lineWidth, Node n) {
        String s = String.format("%" + numWidth + "d", n.data);
        int idx1 = (level * lineWidth) + start + (end - start) / 2 - (s.length()) / 2;
        int idx2 = idx1 + s.length();
        sb.replace(idx1, idx2, s);

        if (n.left != null) {
            displayR(sb, level + 1, start, start + (end - start) / 2, lineWidth, n.left);
        }
        if (n.right != null) {
            displayR(sb, level + 1, start + (end - start) / 2, end, lineWidth, n.right);
        }

    }
}