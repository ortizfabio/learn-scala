package com.bytetrend.sandbox.java.hackerrank.btree;

import java.util.*;


import static com.bytetrend.sandbox.java.hackerrank.btree.Node.reverse;

/**
 * https://www.geeksforgeeks.org/find-the-number-of-distinct-pairs-of-vertices-which-have-a-distance-of-exactly-k-in-a-tree/
 * https://www.youtube.com/watch?v=nPtARJ2cYrg
 */
public class PairsOfVerticesWithDistanceK {

    public static int pairsWithKDistance(Node root, int k) {

        /**
         * This class holds the two pairs that are connected by k distance
         * it also prevent duplicates that can be created when the first and
         * second node are reversed. For that the equals and hashCode are overriden.
         *
         */
        class NodePair {
            Node first;
            Node second;

            public NodePair(Node f, Node s) {
                first = f;
                second = s;
            }

            @Override
            public boolean equals(Object that) {
                if (that == null)
                    return false;
                if (this == that)
                    return true;
                NodePair other = (NodePair) that;
                if (this.first == other.first && this.second == other.second)
                    return true;
                else if (this.first == other.second && this.second == other.first)
                    return true;
                return false;
            }

            @Override
            public int hashCode() {
                Node one = first == null || second == null || first.data < second.data ? first : second;
                Node two = first == one ? second : first;
                int result = (one != null) ? (new Integer(one.data)).hashCode() : 0;
                result = 31 * result + ((two != null) ? (int) ((new Integer(two.data)) ^ (two.data >>> 32)) : 0);
                return result;
            }
        }
        //Collect all the pair of nodes that are at a distance of k
        Set<NodePair> results = new HashSet();
        Queue<Node> nodes = new LinkedList<>();

        if (root == null || k < 1)
            return 0;
        addNodes(root, nodes);

        /* For each node in the BST find the nodes are a k distance */
        while (nodes.peek() != null) {
            //pull one node from the queue of all nodes in the BST
            Node curr = nodes.poll();
            //Prevent from iterating over nodes that have already been processed
            //by using a set. The set uses the equality based on reference in case
            //that the tree contains duplicates.
            Set<Node> seen = new HashSet<>();
            //This list will be used to hold the neighbors of a node from 0 to k
            Queue<Node> neighbors = new LinkedList();
            seen.add(curr);
            neighbors.add(curr);
            //Find the neighbors of the current node at levels from 0 to k
            for (int i = 0; i <= k; i++) {
                //Check if we are at level k
                if (i != k) {
                    //Copy all the neighbors from the previous loop to nextLevel and clear the
                    //neighbors so we can add the ones at this level.
                    Queue<Node> nextLevel = new LinkedList<>(neighbors);
                    neighbors.clear();
                    //Loop tru the neighbors found at the previous level
                    for (Node n : nextLevel) {
                        Node tmp = n.parent;
                        //Add the parent,right, and left if they have not been seen before and if it is not null
                        if (tmp != null && !seen.contains(tmp)) {
                            neighbors.add(tmp);
                            seen.add(tmp);
                        }
                        tmp = n.left;
                        if (tmp != null && !seen.contains(tmp)) {
                            neighbors.add(n.left);
                            seen.add(tmp);
                        }
                        tmp = n.right;
                        if (tmp != null && !seen.contains(n.right)) {
                            neighbors.add(n.right);
                            seen.add(tmp);
                        }
                    }
                } else {
                    //For the current node if we are at the k level then any left nodes
                    //in the collection neighbors are nodes at k distance. Add and continue
                    //with the next node.
                    for (Node n : neighbors) {
                        results.add(new NodePair(curr, n));
                    }
                    break;
                }
            }
        }

        return results.size();
    }

    /**
     * This function just adds all the nodes on a BST to the queue.
     * It also sets the parent of the current node because it will be
     * needed to navigate up the tree hierarchy.
     *
     * @param root
     * @param nodes
     */
    public static void addNodes(Node root, Queue<Node> nodes) {
        if (root != null) {
            nodes.add(root);
            if (root.left != null) {
                root.left.parent = root;
                addNodes(root.left, nodes);
            }
            if (root.right != null) {
                root.right.parent = root;
                addNodes(root.right, nodes);
            }
        }
    }


    public static void main(String[] args) {
        int[] input = new int[]{9, 7, 8, 5, 6, 4, 3, 1};
        VisualizeTree bt = new VisualizeTree(input);
        Node root = bt.getRoot();
        int k = 2;
        int ans = pairsWithKDistance(root, k);
        System.out.println(String.format("\nk = %d -> %d == 8 %b", k, ans, 8 == ans));

        input = new int[]{8, 4, 9, 1, 2, 3, 6, 5};
        bt = new VisualizeTree(input);
        root = bt.getRoot();
        k = 2;
        ans = pairsWithKDistance(root, k);
        System.out.println(String.format("\nk = %d -> %d == 7 %b", k, ans, 7 == ans));

        input = new int[]{3, 1, 5, 8, 2, 4, 7, 6, 9, 0};
        bt = new VisualizeTree(input);
        root = bt.getRoot();
        k = 2;
        ans = pairsWithKDistance(root, k);
        System.out.println(String.format("\nk = %d -> %d == 11 %b", k, ans, 11 == ans));

        input = new int[]{4, 2, 3, 1, 7, 6};
        bt = new VisualizeTree(input);
        root = bt.getRoot();
        k = 2;
        ans = pairsWithKDistance(root, k);
        System.out.println(String.format("\nk = %d -> %d == 5 %b", k, ans, 5 == ans));

        reverse(input);
        bt = new VisualizeTree(input);
        root = bt.getRoot();
        k = 2;
        ans = pairsWithKDistance(root, k);
        System.out.println(String.format("\nk = %d -> %d == 5 %b", k, ans, 5 == ans));

        input = new int[]{1, 2};
        bt = new VisualizeTree(input);
        root = bt.getRoot();
        k = 2;
        ans = pairsWithKDistance(root, k);
        System.out.println(String.format("\nk = %d -> %d == 0 %b", k, ans, 0 == ans));

        input = new int[]{5, 3, 8, 2, 4, 6, 7};
        bt = new VisualizeTree(input);
        root = bt.getRoot();
        k = 2;
        ans = pairsWithKDistance(root, k);
        System.out.println(String.format("\nk = %d -> %d == 6 %b", k, ans, 6 == ans));

        reverse(input);
        bt = new VisualizeTree(input);
        root = bt.getRoot();
        k = 2;
        ans = pairsWithKDistance(root, k);
        System.out.println(String.format("\nk = %d -> %d == 6 %b", k, ans, 6 == ans));


        input = new int[]{8, 4, 2, 1, 3, 6, 5, 7, 10, 14, 15, 9, 12, 11, 13};
        bt = new VisualizeTree(input);
        root = bt.getRoot();
        k = 2;
        ans = pairsWithKDistance(root, k);
        System.out.println(String.format("\nk = %d -> %d == 19 %b", k, ans, 19 == ans));

        reverse(input);
        bt = new VisualizeTree(input);
        root = bt.getRoot();
        k = 2;
        ans = pairsWithKDistance(root, k);
        System.out.println(String.format("\nk = %d -> %d == 18 %b", k, ans, 18 == ans));

        input = new int[]{3, 4, 5, 9, 7, 8, 6, 1};
        bt = new VisualizeTree(input);
        root = bt.getRoot();
        k = 2;
        ans = pairsWithKDistance(root, k);
        System.out.println(String.format("\nk = %d -> %d == 7 %b", k, ans, 7 == ans));

        reverse(input);
        bt = new VisualizeTree(input);
        root = bt.getRoot();
        k = 2;
        ans = pairsWithKDistance(root, k);
        System.out.println(String.format("\nk = %d -> %d == 8 %b", k, ans, 8 == ans));

        input = new int[]{5, 2, 7, 1, 6, 4, 3, 1, 9, 8};
        bt = new VisualizeTree(input);
        root = bt.getRoot();
        k = 2;
        ans = pairsWithKDistance(root, k);
        System.out.println(String.format("\nk = %d -> %d == 9 %b", k, ans, 9 == ans));

    }
}
