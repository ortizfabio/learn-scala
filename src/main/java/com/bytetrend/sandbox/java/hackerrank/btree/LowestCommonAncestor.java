package com.bytetrend.sandbox.java.hackerrank.btree;


import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * https://www.hackerrank.com/challenges/binary-search-tree-lowest-common-ancestor/problem?h_r=internal-search
 */
class LowestCommonAncestor {


    public static Node lca2(Node root, int v1, int v2) {
        // Write your code here.
        int largest = v1 > v2 ? v1 : v2;
        int smallest = v1 < v2 ? v1 : v2;
        List<Node> path = new LinkedList<Node>();

        Node current = root;
        while (current != null && current.data != largest) {
            System.out.print(current.data + "->");
            path.add(current);
            if (current.data < largest)
                current = current.right;
            else
                current = current.left;
        }
        System.out.println("   ");
        Node lcd = root;
        Iterator<Node> i = path.iterator();
        while (i.hasNext()) {
            Node n = i.next();
            if (n.left == null)
                return n;
            else if (n.left.data <= smallest || (n.data < largest))
                return n;
        }
        return lcd;
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

    public static void reverse(int[] a) {
        for (int i = 0; i < (a.length / 2); i++) {
            int t = a[a.length - 1 - i];
            a[a.length - 1 - i] = a[i];
            a[i] = t;
        }
    }
    static Node lca(Node root,int v1,int v2)
    {
        Node temp = root; // not necessary, just use root, just a leftover from a different attempt.

        while (true) {
            if (temp.data > v1 && temp.data > v2) {
                temp = temp.left;
            } else if (temp.data < v1 && temp.data < v2) {
                temp = temp.right;
            } else {
                return temp;
            }
        }
    }


    public static Node lca4(Node root, int v1, int v2) {
        // Write your code here.

        Node current = root;
        while (current != null && current.data != v1 && current.data != v2) {
            boolean goLeft = current.left != null && current.data > v2 && current.data > v1;
            boolean goRight = current.right != null && current.data < v1 && current.data < v2;
            if (goLeft && !goRight) {
                if (current.left.data != v1 && current.left.data != v2)
                    current = current.left;
                else return current;
                continue;
            }
            if (goRight && !goLeft) {
                if (current.right.data != v1 && current.right.data != v2)
                    current = current.right;
                else return current;
                continue;
            }
            if(!goLeft&&!goRight){
                if(current.right != null && current.data <  v1 && current.data < v2) {
                    current = current.right;
                    continue;
                }
                if(current.data > v1 && current.data > v2){
                    current = current.left;
                    continue;
                }
            }
            break;
        }
        return current;
    }


    public static void printTree(int[] a) {
        Node bt = new Node(a[0]);
        for (int i = 1; i < a.length; i++) {
            bt.insert(a[i]);
        }
        bt.show();
    }

    public static void main(String[] args) {
        int[] input = new int[]{9, 7, 8, 5, 6, 4, 3, 1};
        VisualizeTree bt = new VisualizeTree(input);
        Node root = bt.getRoot();
        int t = 0;
        int min = 1;
        int max = 6;
        Node ans = lca(root, min, max);
        System.out.println(String.format("\n%d, %d -> %d == 5 %b", min, max, ans.data, 5 == ans.data));

        input = new int[]{8, 4 ,9, 1, 2 ,3 ,6 ,5};
        bt = new VisualizeTree(input);
        root = bt.getRoot();
        min = 1;
        max = 2;
        ans = lca(root, min, max);
        System.out.println(String.format("\n%d, %d -> %d == 1 %b", min, max, ans.data, 1 == ans.data));

        input = new int[]{3,1,5,8,2,4,7,6,9,0};
        bt = new VisualizeTree(input);
        root = bt.getRoot();
        min = 6;
        max = 9;
        ans = lca(root, min, max);
        System.out.println(String.format("\n%d, %d -> %d == 8 %b", min, max, ans.data, 8 == ans.data));

        input = new int[]{4, 2, 3, 1, 7, 6};
        bt = new VisualizeTree(input);
        root = bt.getRoot();
        min = 1;
        max = 7;
        ans = lca(root, min, max);
        System.out.println(String.format("\n%d, %d -> %d == 4 %b", min, max, ans.data, 4 == ans.data));

        reverse(input);
        bt = new VisualizeTree(input);
        root = bt.getRoot();
        min = 1;
        max = 7;
        ans = lca(root, min, max);
        System.out.println(String.format("\n%d, %d -> %d == 6 %b", min, max, ans.data, 6 == ans.data));

        input = new int[]{1, 2};
        bt = new VisualizeTree(input);
        root = bt.getRoot();
        min = 1;
        max = 2;
        ans = lca(root, min, max);
        System.out.println(String.format("\n%d, %d -> %d == 1 %b", min, max, ans.data, 1 == ans.data));

        input = new int[]{5, 3, 8, 2, 4, 6, 7};
        bt = new VisualizeTree(input);
        root = bt.getRoot();
        min = 6;
        max = 7;
        ans = lca(root, min, max);
        System.out.println(String.format("\n%d, %d -> %d == 8 %b", min, max, ans.data, 8 == ans.data));

        reverse(input);
        bt = new VisualizeTree(input);
        root = bt.getRoot();
        min = 7;
        max = 3;
        ans = lca(root, min, max);
        System.out.println(String.format("\n%d, %d -> %d == 7 %b", min, max, ans.data, 7 == ans.data));


        input = new int[]{8, 4, 2, 1, 3, 6, 5, 7, 10, 14, 15, 9, 12, 11, 13};
        bt = new VisualizeTree(input);
        root = bt.getRoot();
        min = 15;
        max = 11;
        ans = lca(root, min, max);
        System.out.println(String.format("\n%d, %d -> %d == 14 %b", min, max, ans.data, 14 == ans.data));

        reverse(input);
        bt = new VisualizeTree(input);
        root = bt.getRoot();
        min = 15;
        max = 11;
        ans = lca(root, min, max);
        System.out.println(String.format("\n%d, %d -> %d == 13 %b", min, max, ans.data, 13 == ans.data));

        input = new int[]{3, 4, 5, 9, 7, 8, 6, 1};
        bt = new VisualizeTree(input);
        root = bt.getRoot();
        min = 6;
        max = 8;
        ans = lca(root, min, max);
        System.out.println(String.format("\n%d, %d -> %d == 7 %b", min, max, ans.data, 7 == ans.data));

        reverse(input);
        bt = new VisualizeTree(input);
        root = bt.getRoot();
        min = 6;
        max = 8;
        ans = lca(root, min, max);
        System.out.println(String.format("\n%d, %d -> %d == 1 %b", min, max, ans.data, 1 == ans.data));

        input = new int[]{5, 2, 7, 1, 6, 4, 3, 1,9,8};
        bt = new VisualizeTree(input);
        root = bt.getRoot();
        min = 6;
        max = 8;
        ans = lca(root, min, max);
        System.out.println(String.format("\n%d, %d -> %d == 7 %b", min, max, ans.data, 7 == ans.data));

    }
}