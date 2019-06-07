package com.bytetrend.sandbox.java.hackerrank.btree;
import com.bytetrend.sandbox.java.hackerrank.btree.BinarySearchTreeHeight.*;

import static com.bytetrend.sandbox.java.hackerrank.btree.BinarySearchTreeHeight.getHeight;

public class Node {
    Node left, right,parent;
    int data;

    Node(int data) {
        this.data = data;
        left = right = null;
    }

    public static Node build(int[] input) {
        Node root = new Node(input[0]);
        int t = 1;
        while (t < input.length) {
            root = root.insert(input[t++]);
        }
        return root;
    }

    @Override
    public String toString() {
        return "Node{" +
                "left=" + (left == null ? "null" : left.data) +
                ", right=" + (right == null ? "null" : right.data) +
                ", data=" + data +
                '}';
    }

    static void print(Node l) {
        if (l != null) {
            print(l.left);
            System.out.println(l);
            print(l.right);
        }
    }


    public Node insert(int data) {

        if (data < data) {
            if (left == null) {
                left = new Node(data);
            } else {
                left.insert(data);
            }
        } else {
            if (right == null) {
                right = new Node(data);

            } else {
                right.insert(data);
            }
        }

        return this;
    }


    public void show() {
        final int height = getHeight(this);
        final int width = Math.max(1,(int)Math.pow(height,2));
        System.out.println(height+" "+width);
        int len = width * height * 2 + 2;
        StringBuilder sb = new StringBuilder(len);
        for (int i = 1; i <= len; i++)
            sb.append(i < len - 2 && i % width == 0 ? "\n" : ' ');

        displayR(sb, width / 2, 1, width / 4, width, this, " ");
        System.out.println(sb);
    }

    static private void displayR(StringBuilder sb, int center, int radius, int d, int w, Node n,
                                 String edge) {
        if (n != null) {
            displayR(sb, center - d, radius + 2, d / 2, w, n.left, " /");

            String s = String.valueOf(n.data);
            int idx1 = radius * w + center - (s.length() + 1) / 2;
            int idx2 = idx1 + s.length();
            int idx3 = Math.max(0,idx1 - w);
            if (idx2 < sb.length())
                sb.replace(idx1, idx2, s).replace(idx3, idx3 + 2, edge);

            displayR(sb, center + d, radius + 2, d / 2, w, n.right, "\\ ");
        }
    }

    public static void reverse(int[] a) {
        for (int i = 0; i < (a.length / 2); i++) {
            int t = a[a.length - 1 - i];
            a[a.length - 1 - i] = a[i];
            a[i] = t;
        }
    }
}
