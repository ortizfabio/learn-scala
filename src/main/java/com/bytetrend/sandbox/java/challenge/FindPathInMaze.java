package com.bytetrend.sandbox.java.challenge;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class FindPathInMaze {
    static public class Point {
        final int row;
        final int column;

        public Point(int r, int c) {
            row = r;
            column = c;
        }

        @Override
        public boolean equals(Object other) {
            Point that = (Point) other;
            return (this.column == that.column && this.row == that.row);
        }

        @Override
        public int hashCode() {
            return 31 + this.row << 3 & this.column;
        }

        @Override
        public String toString() {
            return "Point{" +
                    row + "" + column +
                    '}';
        }
    }

    public static boolean doSearch(Point next, Set<Point> seen, Stack<Point> path, int[][] board) {
        path.add(next);
        seen.add(next);
        boolean found = false;
        if (next.row == board.length - 1 && next.column == board[0].length - 1) {
            return true;
        }
        for (int r = Math.max(0, next.row - 1); r <= Math.min(next.row + 1, board.length - 1); r += 1) {
            for (int c = Math.max(0, next.column - 1); c <= Math.min(next.column + 1, board[0].length - 1); c += 1) {
                Point p = new Point(r, c);
                if (!seen.contains(p)) {
                    if (board[p.row][p.column] == 1) {
                        found = doSearch(p, seen, path, board);
                        if (found)
                            return true;
                    }else {
                        seen.add(p);
                    }
                }
            }
        }
        path.pop();
        return found;
    }


    public static List<Point> findPath(int[][] board) {
        Stack<Point> path = new Stack<>();

        doSearch(new Point(0, 0), new HashSet<>(), path, board);
        return path;
    }

    public static void main(String[] args) {
        int[][] board = new int[][]{
                new int[]{1, 0, 0, 0},
                new int[]{1, 0, 1, 1},
                new int[]{0, 1, 1, 0},
                new int[]{1, 1, 1, 1}};
        List<Point> path = findPath(board);
        path.forEach(x -> System.out.println(x));
    }


}
