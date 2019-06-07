package com.bytetrend.sandbox.java.hackerrank.btree;



import java.util.Stack;

import static com.bytetrend.sandbox.java.hackerrank.btree.Node.reverse;

/**
 * https://www.geeksforgeeks.org/search-a-node-in-binary-tree/
 *
 * Search for a node in a tree.
 */
public class SearchANode {

    public static boolean recursiveSearch(Node root, int n) {

        if (root != null) {
            if (root.data == n)
                return true;
            else {
                if (recursiveSearch(root.left, n))
                    return true;
                else if (recursiveSearch(root.right, n))
                    return true;
            }

        }
        return false;
    }

    public static boolean nonRecursiveSearch(Node root, int n){

        Stack<Node> st = new Stack();
        st.push(root);
        Node c = null;
        while(!st.empty()){
            c = st.pop();
            if(c.data == n)
                return true;
            if(c.left != null && n < c.data)
                st.push(c.left);
            if(c.right != null && n >= c.data)
                st.push(c.right);
        }
        return false;
    }


    public static void main(String[] args) {
        boolean result = false;
        int[] input = new int[]{9, 7, 8, 5, 6, 4, 3, 1};
        VisualizeTree bt = new VisualizeTree(input);
        Node root = bt.getRoot();
        result = recursiveSearch(root, 5);
        System.out.println(String.format("%b == true", result));
        result = nonRecursiveSearch(root, 5);
        System.out.println(String.format("%b == true", result));

        input = new int[]{8, 4, 9, 1, 2, 3, 6, 5};
        bt = new VisualizeTree(input);
        root = bt.getRoot();
        result = recursiveSearch(root, 1);
        System.out.println(String.format("%b == true", result));
        result = nonRecursiveSearch(root, 1);
        System.out.println(String.format("%b == true", result));

        input = new int[]{3, 1, 5, 8, 2, 4, 7, 6, 9, 0};
        bt = new VisualizeTree(input);
        root = bt.getRoot();
        result = recursiveSearch(root, 3);
        System.out.println(String.format("%b == true", result));
        result = nonRecursiveSearch(root, 3);
        System.out.println(String.format("%b == true", result));

        input = new int[]{4, 2, 3, 1, 7, 6};
        bt = new VisualizeTree(input);
        root = bt.getRoot();
        result = recursiveSearch(root, 6);
        System.out.println(String.format("%b == true", result));
        result = nonRecursiveSearch(root, 6);
        System.out.println(String.format("%b == true", result));

        reverse(input);
        bt = new VisualizeTree(input);
        root = bt.getRoot();
        result = recursiveSearch(root, 10);
        System.out.println(String.format("%b == false", result));
        result = nonRecursiveSearch(root, 10);
        System.out.println(String.format("%b == false", result));

        input = new int[]{1, 2};
        bt = new VisualizeTree(input);
        root = bt.getRoot();
        result = recursiveSearch(root, 2);
        System.out.println(String.format("%b == true", result));
        result = nonRecursiveSearch(root, 2);
        System.out.println(String.format("%b == true", result));

        input = new int[]{5, 3, 8, 2, 4, 6, 7};
        bt = new VisualizeTree(input);
        root = bt.getRoot();
        result = recursiveSearch(root, 8);
        System.out.println(String.format("%b == true", result));
        result = nonRecursiveSearch(root, 8);
        System.out.println(String.format("%b == true", result));

        reverse(input);
        bt = new VisualizeTree(input);
        root = bt.getRoot();
        result = recursiveSearch(root, 9);
        System.out.println(String.format("%b == false", result));
        result = nonRecursiveSearch(root, 9);
        System.out.println(String.format("%b == false", result));


        input = new int[]{8, 4, 2, 1, 3, 6, 5, 7, 10, 14, 15, 9, 12, 11, 13};
        bt = new VisualizeTree(input);
        root = bt.getRoot();
        result = recursiveSearch(root, 13);
        System.out.println(String.format("%b == true", result));
        result = nonRecursiveSearch(root, 13);
        System.out.println(String.format("%b == true", result));

        reverse(input);
        bt = new VisualizeTree(input);
        root = bt.getRoot();
        result = recursiveSearch(root, 0);
        System.out.println(String.format("%b == false", result));
        result = nonRecursiveSearch(root, 0);
        System.out.println(String.format("%b == false", result));

        input = new int[]{3, 4, 5, 9, 7, 8, 6, 1};
        bt = new VisualizeTree(input);
        root = bt.getRoot();
        result = recursiveSearch(root, 6);
        System.out.println(String.format("%b == true", result));
        result = nonRecursiveSearch(root, 6);
        System.out.println(String.format("%b == true", result));

        reverse(input);
        bt = new VisualizeTree(input);
        root = bt.getRoot();
        result = recursiveSearch(root, 2);
        System.out.println(String.format("%b == false", result));
        result = nonRecursiveSearch(root, 2);
        System.out.println(String.format("%b == false", result));

        input = new int[]{5, 2, 7, 6, 4, 3, 1, 9, 8};
        bt = new VisualizeTree(input);
        root = bt.getRoot();
        result = recursiveSearch(root, 7);
        System.out.println(String.format("%b == true", result));
        result = nonRecursiveSearch(root, 7);
        System.out.println(String.format("%b == true", result));


    }
}